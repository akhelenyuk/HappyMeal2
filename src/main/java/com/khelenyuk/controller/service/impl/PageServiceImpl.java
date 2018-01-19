package com.khelenyuk.controller.service.impl;


import com.khelenyuk.model.MealToDisplay;
import com.khelenyuk.controller.service.*;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.model.MealType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*TODO check if I need this class to be singleton or just class with static methods
 */

public class PageServiceImpl implements IPageService {
    private static final Logger logger = LogManager.getLogger(PageServiceImpl.class);
    private static PageServiceImpl instance = new PageServiceImpl();

    private boolean redirect = false;

    private IUserService userService = ServiceFactory.getUserService();
    private IProductService productService = ServiceFactory.getProductService();
    private IMenuService menuService = ServiceFactory.getMenuService();
    private IMealTypeService mealTypeService = ServiceFactory.getMealTypeService();


    private PageServiceImpl() {
    }

    public static PageServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void updatePageData(HttpSession session, int userId) {
        /**
         * writes updated user(with updated info) into session
         */
        session.setAttribute("user", userService.getUser(userId));
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

        /**
         * writes updated menu,
         * totals(menu weight, calories, proteins, fats, carbs)
         * into session
         */
        List<MealToDisplay> userMealToDisplay = menuService.getUserMenu(userId, chosenDate);
        Map<String, List<MealToDisplay>> mealsSplittedByType = makeMap(mealTypes, userMealToDisplay);



//        session.setAttribute("meals", userMealToDisplay);
        session.setAttribute("meals", mealsSplittedByType);
        session.setAttribute("totalDayFoodWeight", menuService.getTotalWeight(userMealToDisplay));
        logger.debug("------------- Total Food weight: " + menuService.getTotalWeight(userMealToDisplay));
        session.setAttribute("totalDayCalories", menuService.getTotalCalories(userMealToDisplay));
        session.setAttribute("totalDayProteins", menuService.getTotalProteins(userMealToDisplay));
        session.setAttribute("totalDayFat", menuService.getTotalFat(userMealToDisplay));
        session.setAttribute("totalDayCarbs", menuService.getTotalCarbs(userMealToDisplay));


    }


    // Test 1

    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    private Map<String, List<MealToDisplay>> makeMap(List<MealType> mealTypes, List<MealToDisplay> meals){
        Map<String, List<MealToDisplay>> map = new LinkedHashMap<>();
        List list = new ArrayList();
        list.isEmpty();

        for (MealType type: mealTypes
             ) {
            map.put(type.getName(), new ArrayList<>());
        }

        for (MealToDisplay meal: meals
             ) {
            map.get(meal.getMealType()).add(meal);
        }

        return map;
    }

//    @Override
//    public void toMainPageAttributesUpdate(HttpSession session) {
//        session.setAttribute("products", productService.getAllProducts());
//        logger.info("Attribute 'products' is added to session");
//
//        session.setAttribute("mealTypes", menuService.getMealTypes());
//        logger.info("Attribute mealTypes is set");
//    }


}
