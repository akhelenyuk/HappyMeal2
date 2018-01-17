package com.khelenyuk.controller.service.impl;


import com.khelenyuk.model.MealFull;
import com.khelenyuk.controller.service.*;
import com.khelenyuk.controller.service.factory.ServiceFactory;
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

    private boolean isForwarded = false;

    private IUserService userService = ServiceFactory.getUserService();
    private IProductService productService = ServiceFactory.getProductService();
    private IMenuService menuService = ServiceFactory.getMenuService();


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
         * writes updated list of products into session
         */
        session.setAttribute("products", productService.getAllProducts());
        logger.info("Attribute products is set");
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
    public boolean toBeForwarded(){
        return isForwarded;
    }

    @Override
    public void setIsForwarded(boolean isForwarded) {
        this.isForwarded = isForwarded;
    }

    @Override
    public void toMainPageAttributesUpdate(HttpSession session) {
        session.setAttribute("products", productService.getAllProducts());
        logger.info("Attribute 'products' is added to session");

        session.setAttribute("mealTypes", menuService.getMealTypes());
        logger.info("Attribute mealTypes is set");
    }
}
