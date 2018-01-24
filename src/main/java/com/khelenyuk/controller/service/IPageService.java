package com.khelenyuk.controller.service;

import com.khelenyuk.model.MealType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface IPageService {
    boolean isRedirect();

    void updateMainPageData(HttpSession session, int userId);

    void setRedirect(boolean isRedirected);

    void updateRegistrationPageData(HttpServletRequest request);

    void updateAdminPageData(HttpSession session);
}

