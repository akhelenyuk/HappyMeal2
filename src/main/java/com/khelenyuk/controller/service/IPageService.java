package com.khelenyuk.controller.service;

import javax.servlet.http.HttpSession;

public interface IPageService {
    boolean toBeForwarded();

    void updatePageData(HttpSession session, int userId);

    void setIsForwarded(boolean isRedirected);

    void toMainPageAttributesUpdate(HttpSession session);
}

