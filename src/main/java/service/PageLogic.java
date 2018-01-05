package service;


import dao.mysql.MealDAO;
import entity.Meal;
import entity.MealFull;
import entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public class PageLogic {



    public static void updatePageData(HttpSession session, int userId){
        // и достаю из базы данных актуальный объект user и записываю его в сессию
        session.setAttribute("user", UserLogic.getUser(userId));

        // обновленный список продуктов
        session.setAttribute("products", ProductLogic.getAllProducts());

        // список блюд пользователя и общая количество б\ж\у, веса и калорий
        List<MealFull> menu = MenuLogic.getUserMenu(userId);
        session.setAttribute("menu", menu);
        session.setAttribute("userTotalWeight", MenuLogic.getTotalWeight(menu));
        session.setAttribute("userTotalCalories", MenuLogic.getTotalCalories(menu));
        session.setAttribute("userTotalProteins", MenuLogic.getTotalProteins(menu));
        session.setAttribute("userTotalFat", MenuLogic.getTotalFat(menu));
        session.setAttribute("userTotalCarbs", MenuLogic.getTotalCarbs(menu));
    }


}
