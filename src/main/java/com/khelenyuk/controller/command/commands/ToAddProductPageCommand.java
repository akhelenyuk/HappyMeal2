package com.khelenyuk.controller.command.commands;

import com.khelenyuk.model.User;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToAddProductPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * @Return addProduct page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.addProduct");
        pageService.updateUser(request.getSession(), ((User) request.getSession().getAttribute("user")).getId());

        return page;
    }
}
