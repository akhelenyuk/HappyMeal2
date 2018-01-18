package com.khelenyuk.controller.service.impl;


import com.khelenyuk.dao.MealTypeDAO;
import com.khelenyuk.dao.mysql.factory.DAOFactory;
import com.khelenyuk.model.MealFull;
import com.khelenyuk.controller.service.*;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.model.MealType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/*TODO check if I need this class to be singleton or just class with static methods
 */

public class PageServiceImpl implements IPageService {
    private static final Logger logger = LogManager.getLogger(LoginRegistrationServiceImpl.class);
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
        session.setAttribute("mealTypes", mealTypeService.getAll());
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
        List<MealFull> menu = menuService.getUserMenu(userId, chosenDate);
        session.setAttribute("menu", menu);
        session.setAttribute("userTotalWeight", menuService.getTotalWeight(menu));
        session.setAttribute("userTotalCalories", menuService.getTotalCalories(menu));
        session.setAttribute("userTotalProteins", menuService.getTotalProteins(menu));
        session.setAttribute("userTotalFat", menuService.getTotalFat(menu));
        session.setAttribute("userTotalCarbs", menuService.getTotalCarbs(menu));


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

//    @Override
//    public void toMainPageAttributesUpdate(HttpSession session) {
//        session.setAttribute("products", productService.getAllProducts());
//        logger.info("Attribute 'products' is added to session");
//
//        session.setAttribute("mealTypes", menuService.getMealTypes());
//        logger.info("Attribute mealTypes is set");
//    }


}
