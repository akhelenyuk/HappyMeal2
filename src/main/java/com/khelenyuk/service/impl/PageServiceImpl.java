package com.khelenyuk.service.impl;


import com.khelenyuk.model.MealFull;
import com.khelenyuk.service.*;
import com.khelenyuk.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class PageServiceImpl implements IPageService {
    private static final Logger logger = LogManager.getLogger(LoginRegistrationServiceImpl.class);
    private static PageServiceImpl instance = new PageServiceImpl();

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

        /**
         * writes updated list of products into session
         */
        session.setAttribute("products", productService.getAllProducts());

        /**
         * writes chosenDate to session. If attribute is not set then
         * setting chosenDate to now() value.
         */
        LocalDate chosenDate = (LocalDate) session.getAttribute("chosenDateSession");
        if (chosenDate == null) {
            chosenDate = LocalDate.now();
            session.setAttribute("chosenDateSession", chosenDate);
            logger.debug("Setting chosenDate to session: " + session.getAttribute("chosenDateSession"));
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


}
