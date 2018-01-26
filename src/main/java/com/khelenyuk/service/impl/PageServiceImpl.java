package com.khelenyuk.service.impl;


import com.khelenyuk.model.*;
import com.khelenyuk.service.*;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

/*TODO check if I need this class to be singleton or just class with static methods
 */

public class PageServiceImpl implements IPageService {
    private static final Logger logger = LogManager.getLogger(PageServiceImpl.class);
    private static PageServiceImpl instance = new PageServiceImpl();
    private HttpSession session = null;
    private static final String PARAM_NAME_LOGIN = "pageNumber";


    private boolean redirect = false;

    private IUserService userService = ServiceFactory.getUserService();
    private IProductService productService = ServiceFactory.getProductService();
    private IMenuService menuService = ServiceFactory.getMenuService();
    private IMealTypeService mealTypeService = ServiceFactory.getMealTypeService();
    private IActivityService activityService = ServiceFactory.getActivityService();
    private IActivityDiaryService activityDiaryService = ServiceFactory.getActivityDiaryService();


    private PageServiceImpl() {
    }

    public static PageServiceImpl getInstance() {
        return instance;
    }


    @Override
    public void updateMainPageData(HttpSession session, int userId) {
        User user = userService.getUser(userId);
        logger.debug("##############" + UtilManager.getProperty("role.admin"));
        logger.debug(user.getRoleId());

        /**
         * writes updated user(with updated info) into session
         */
        session.setAttribute("user", user);
        logger.info("Attribute user is set");

        /**
         * gets list of current products from db and writes them into session
         */
        session.setAttribute("products", productService.getAllProducts());
        logger.info("Attribute products is set");

        /**
         * gets list of current meal types from db and writes them into session
         */
        List<MealType> mealTypes = mealTypeService.getAll();
        session.setAttribute("mealTypes", mealTypes);
        logger.info("Attribute meal_types is set");

        /**
         * writes chosenDate to session. If attribute is not set then
         * setting chosenDate to now() value.
         */
        LocalDate chosenDate = (LocalDate) session.getAttribute("chosenDateSession");
        if (chosenDate == null) {
            chosenDate = LocalDate.now();
            session.setAttribute("chosenDateSession", chosenDate);
            logger.info("Setting chosenDate to session: " + session.getAttribute("chosenDateSession"));
        }


        // FOOD Tab
        /**
         * writes updated menu,
         * totals(menu weight, calories, proteins, fats, carbs)
         * into session
         */
        logger.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$ 1");
        List<MealToDisplay> userMealToDisplay = menuService.getUserMenu(userId, chosenDate);
        for (MealToDisplay m : userMealToDisplay) {
            logger.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$ 2" + m);
        }

        Map<String, MealToDisplay> totalsByMealTypeMap = makeMap2(userId, chosenDate, mealTypes);

        Map<String, List<MealToDisplay>> mealsSplittedByType = makeMap(mealTypes, userMealToDisplay);


        session.setAttribute("meals", mealsSplittedByType);

        session.setAttribute("totalsByMealType", totalsByMealTypeMap);
        session.setAttribute("totalDayFoodWeight", menuService.getTotalWeight(userMealToDisplay));
        session.setAttribute("totalDayCalories", menuService.getTotalCalories(userMealToDisplay));
        session.setAttribute("totalDayProteins", menuService.getTotalProteins(userMealToDisplay));
        session.setAttribute("totalDayFat", menuService.getTotalFat(userMealToDisplay));
        session.setAttribute("totalDayCarbs", menuService.getTotalCarbs(userMealToDisplay));

//        ACTIVITY Tab
        /**
         * gets list of current activity types from db and writes them into session
         */
        session.setAttribute("activities", activityService.getAll());
        session.setAttribute("activitiesList", activityDiaryService.getUserActivityDiary(userId, chosenDate));
        session.setAttribute("activitiesListTotals", activityDiaryService.getUserActivityDiaryTotals(userId, chosenDate));


//        BODY STATS tab
        session.setAttribute("genders", userService.getGenders());
        session.setAttribute("lifestyles", userService.getLifestyles());
        // will be used in jsp to check that birthday is not in future
        session.setAttribute("currentDate", LocalDate.now());

        // FORMULA
        Integer remaining = user.getCalorieNorm() - menuService.getUserFoodTotal(userId, chosenDate).getCalories() + activityDiaryService.getUserActivityDiaryTotals(userId, chosenDate).getCalories();
        session.setAttribute("remaining", remaining);

//        GOALS
        session.setAttribute("kgToGoal", user.getWeight() - user.getGoalWeight());
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
        this.session = request.getSession();

        session.setAttribute("genders", userService.getGenders());
        session.setAttribute("lifestyles", userService.getLifestyles());
    }

    @Override
    public void updateAdminPageData(HttpServletRequest request) {
        int size = userService.getUsersCount();
        int offset = 0;
        int limit = 5;
        int pages = 1;

        logger.debug("requested page number: " + request.getParameter(PARAM_NAME_LOGIN));
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
        logger.debug("Total number of pages: " + pages);
        request.setAttribute("pages", pages);

        int currentPage = offset / limit + 1;
        logger.debug("Current page number: " + currentPage);
        request.setAttribute("currentPage", currentPage);
    }


    /**
     * Makes map with key = MealType, value = list of meals within MealType
     * to be displayed on jsp page.
     *
     * @param mealTypes
     * @param meals     - list of all meals of certain user on certain date
     * @return
     */
    private Map<String, List<MealToDisplay>> makeMap(List<MealType> mealTypes, List<MealToDisplay> meals) {
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

    private Map<String, MealToDisplay> makeMap2(int userId, LocalDate chosenDate, List<MealType> mealTypes) {
        Map<String, MealToDisplay> map = new HashMap<>();
        for (MealType type : mealTypes) {
            map.put(type.getName(), menuService.getTotalsByMealType(userId, chosenDate, type.getId()));
        }
        return map;
    }

}




