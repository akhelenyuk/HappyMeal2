package com.khelenyuk.controller.service;

import com.khelenyuk.model.MealType;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IPageService {
    boolean isRedirect();

    void updatePageData(HttpSession session, int userId);

    void setRedirect(boolean isRedirected);

//    void toMainPageAttributesUpdate(HttpSession session);
}

