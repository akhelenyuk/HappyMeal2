package com.khelenyuk.service;

import com.khelenyuk.model.MealType;
import com.khelenyuk.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface IPageService {
    /**
     * Checks if request shall be redirected
     * @return true - if shall be, false - if not (will be forwarded)
     */
    boolean isRedirect();

    /**
     * Updates data on main page
     * @param session
     * @param userId
     */
    void updateMainPageData(HttpSession session, int userId);

    /**
     * Setter for isRedirect field
     */
    void setRedirect(boolean isRedirected);

    /**
     * Prepares data needed on registration page
     * @param request
     */
    void updateRegistrationPageData(HttpServletRequest request);

    /**
     * Prepares and updates data needed on Admin page
     * @param request
     */
    void updateAdminPageData(HttpServletRequest request);

    /**
     * Gets current user from db and puts it in session
     * @param session
     * @param userId
     */
    void updateUser(HttpSession session, Integer userId);
}

