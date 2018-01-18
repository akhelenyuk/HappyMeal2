package com.khelenyuk.controller.service;

import javax.servlet.http.HttpSession;

public interface IPageService {
    boolean isRedirect();

    void updatePageData(HttpSession session, int userId);

    void setRedirect(boolean isRedirected);

    void toMainPageAttributesUpdate(HttpSession session);
}

