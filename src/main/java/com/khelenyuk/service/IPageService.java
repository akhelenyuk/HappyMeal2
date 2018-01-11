package com.khelenyuk.service;

import javax.servlet.http.HttpSession;

public interface IPageService {
    void updatePageData(HttpSession session, int userId);
}

