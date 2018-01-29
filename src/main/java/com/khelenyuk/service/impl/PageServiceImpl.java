package com.khelenyuk.service.impl;


import com.khelenyuk.model.*;
import com.khelenyuk.service.*;
import com.khelenyuk.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

public class PageServiceImpl implements IPageService {
    private static final Logger logger = LogManager.getLogger(PageServiceImpl.class);

    private static final String PARAM_NAME_LOGIN = "pageNumber";

    private boolean redirect = false;

    private IUserService userService = ServiceFactory.getUserService();
    private IProductService productService = ServiceFactory.getProductService();
    private IMenuService menuService = ServiceFactory.getMenuService();
    private IMealTypeService mealTypeService = ServiceFactory.getMealTypeService();
    private IActivityService activityService = ServiceFactory.getActivityService();
    private IActivityDiaryService activityDiaryService = ServiceFactory.getActivityDiaryService();

    private static PageServiceImpl instance;

    private PageServiceImpl() {
    }

    /**
     * Singleton: double-checked locking
     * @return
     */
    public static PageServiceImpl getInstance() {
        if(instance == null){
            synchronized (PageServiceImpl.class){
                if(instance == null){
                    instance = new PageServiceImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public void updateMainPageData(HttpSession session, int userId) {
        LocalDate chosenDate = getChosenDate(session);

        updateUser(session, userId);
        updateFoodTab(session, userId, chosenDate);
        updateActivityTab(session, userId, chosenDate);
        updateBodyStatsTab(session);
        updateFormulaAndGoals(session, userId, chosenDate);
    }


    private void updateFormulaAndGoals(HttpSession session, int userId, LocalDate chosenDate) {
        User user = userService.getUser(userId);
        // FORMULA
        Integer remaining = user.getCalorieNorm() - menuService.getUserFoodTotal(userId, chosenDate).getCalories() + activityDiaryService.getUserActivityDiaryTotals(user.getId(), chosenDate).getCalories();
        session.setAttribute("remaining", remaining);

//        GOALS
        session.setAttribute("kgToGoal", user.getWeight() - user.getGoalWeight());
    }

    /**
     * writes chosenDate to session. If attribute is not set then
     * setting chosenDate to 'today' value.
     */
    private LocalDate getChosenDate(HttpSession session) {
        LocalDate chosenDate = (LocalDate) session.getAttribute("chosenDateSession");
        if (chosenDate == null) {
            chosenDate = LocalDate.now();
            session.setAttribute("chosenDateSession", chosenDate);
            logger.info("Setting chosenDate to session: " + session.getAttribute("chosenDateSession"));
        }
        return chosenDate;
    }

    /**
     * FOOD Tab
     * writes updated menu,
     * totals(menu weight, calories, proteins, fats, carbs)
     * into session
     */
    private void updateFoodTab(HttpSession session, int userId, LocalDate chosenDate) {

        List<MealToDisplay> userMealToDisplay = menuService.getUserMenu(userId, chosenDate);
//        for (MealToDisplay m : userMealToDisplay) {
//        }

        /**
         * gets list of current meal types from db and writes them into session
         */
        List<MealType> mealTypes = mealTypeService.getAll();

        /**
         * gets list of current products from db and writes them into session
         */

        Map<String, MealToDisplay> totalsByMealTypeMap = makeMapMealTypesTotalsForMealType(userId, chosenDate, mealTypes);
        Map<String, List<MealToDisplay>> mealsSplittedByType = makeMapMealTypesAndListsOfMeals(mealTypes, userMealToDisplay);

        session.setAttribute("mealTypes", mealTypes);
        session.setAttribute("products", productService.getAllProducts());
        session.setAttribute("meals", mealsSplittedByType);
        session.setAttribute("totalsByMealType", totalsByMealTypeMap);
        session.setAttribute("totalDayFoodWeight", menuService.getTotalWeight(userMealToDisplay));
        session.setAttribute("totalDayCalories", menuService.getTotalCalories(userMealToDisplay));
        session.setAttribute("totalDayProteins", menuService.getTotalProteins(userMealToDisplay));
        session.setAttribute("totalDayFat", menuService.getTotalFat(userMealToDisplay));
        session.setAttribute("totalDayCarbs", menuService.getTotalCarbs(userMealToDisplay));
    }

    /**
     * ACTIVITY Tab
     * gets list of current activity types from db and writes them into session
     */
    private void updateActivityTab(HttpSession session, int userId, LocalDate chosenDate) {

        session.setAttribute("activities", activityService.getAll());
        session.setAttribute("activitiesList", activityDiaryService.getUserActivityDiary(userId, chosenDate));
        session.setAttribute("activitiesListTotals", activityDiaryService.getUserActivityDiaryTotals(userId, chosenDate));
    }

    private void updateBodyStatsTab(HttpSession session) {
        //        BODY STATS tab
        session.setAttribute("genders", userService.getGenders());
        session.setAttribute("lifestyles", userService.getLifestyles());
        // will be used in jsp to check that birthday is not in future
        session.setAttribute("currentDate", LocalDate.now());
    }

    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    @Override
    public void updateRegistrationPageData(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("genders", userService.getGenders());
        session.setAttribute("lifestyles", userService.getLifestyles());
        session.setAttribute("maxBirth", LocalDate.now());
        session.setAttribute("minBirth", LocalDate.parse("1900-01-01"));
    }

    @Override
    public void updateAdminPageData(HttpServletRequest request) {
        int size = userService.getUsersCount();
        int offset = 0;
        int limit = 5;
        int pages = 1;

        if (request.getParameter(PARAM_NAME_LOGIN) != null) {
            int requestedPage = Integer.valueOf(request.getParameter(PARAM_NAME_LOGIN));
            offset = (requestedPage - 1) * limit;
        }

        List<User> users1 = userService.getUsers(limit, offset);
        request.setAttribute("users", users1);

        /**
         * finding out how many pagination buttons (pages) shall be shown based on number of users (size) and
         * number of entries to be shown on one page (limit).
         */
        if (size > limit) {
            pages = size / limit;
            if (size % limit > 0) {
                pages++;
            }
        }
        request.setAttribute("pages", pages);

        int currentPage = offset / limit + 1;
        request.setAttribute("currentPage", currentPage);
    }

    /**
     * writes updated user(with updated info) into session
     */
    @Override
    public void updateUser(HttpSession session, Integer userId) {
        session.setAttribute("user", userService.getUser(userId));
    }


    /**
     * Makes map with key = MealType, value = list of MealToDisplay within MealType
     * to be displayed on jsp page.
     *
     * @param mealTypes
     * @param meals     - list of all meals of certain user on certain date
     * @return Map<String, List<MealToDisplay>>
     */
    private Map<String, List<MealToDisplay>> makeMapMealTypesAndListsOfMeals(List<MealType> mealTypes, List<MealToDisplay> meals) {
        Map<String, List<MealToDisplay>> map = new LinkedHashMap<>();

        for (MealType type : mealTypes
                ) {
            map.put(type.getName(), new ArrayList<>());
        }

        for (MealToDisplay meal : meals
                ) {
            map.get(meal.getMealType()).add(meal);
        }
        return map;
    }

    /**
     * Makes map with key = MealType, value = MealToDisplay with totals on each field
     * to be displayed on jsp page.
     * @param userId
     * @param chosenDate
     * @param mealTypes
     * @return Map<String, MealToDisplay>
     */
    private Map<String, MealToDisplay> makeMapMealTypesTotalsForMealType(int userId, LocalDate chosenDate, List<MealType> mealTypes) {
        Map<String, MealToDisplay> map = new HashMap<>();
        for (MealType type : mealTypes) {
            map.put(type.getName(), menuService.getTotalsByMealType(userId, chosenDate, type.getId()));
        }
        return map;
    }

}




