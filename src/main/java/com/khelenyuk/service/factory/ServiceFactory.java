package com.khelenyuk.service.factory;

import com.khelenyuk.service.*;
import com.khelenyuk.service.impl.*;

public class ServiceFactory {
    public static IUserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public static IProductService getProductService() {
        return ProductServiceImpl.getInstance();
    }

    public static IPageService getPageService() {
        return PageServiceImpl.getInstance();
    }

    public static IMenuService getMenuService() {
        return MenuServiceImpl.getInstance();
    }

    public static ILoginRegistrationService getLoginRegistrationService() {
        return LoginRegistrationServiceImpl.getInstance();
    }
}
