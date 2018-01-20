package com.khelenyuk.controller.service.factory;

import com.khelenyuk.controller.service.*;
import com.khelenyuk.controller.service.impl.*;

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

    public static IMealTypeService getMealTypeService() {
        return MealTypeServiceImpl.getInstance();
    }

    public static IActivityService getActivityService() {
        return ActivityServiceImpl.getInstance();
    }
}
