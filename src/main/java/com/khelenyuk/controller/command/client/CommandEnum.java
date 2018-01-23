package com.khelenyuk.controller.command.client;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.command.commands.*;

public enum CommandEnum {
    LOGIN{
        {
            this.command = new LoginCommand();
        }
    },
    TO_LOGIN_PAGE{
        {
            this.command = new ToLoginPageCommand();
        }
    },
    TO_MAIN_PAGE{
        {
            this.command = new ToMainPageCommand();
        }
    },
    TO_ADMIN_PAGE{
        {
            this.command = new ToAdminPageCommand();
        }
    },
    TO_REGISTRATION_PAGE{
        {
            this.command = new ToRegistrationPageCommand();
        }
    },
    TO_ADD_PRODUCT_PAGE{
        {
            this.command = new ToAddProductPageCommand();
        }
    },
    TO_PREVIOUS_PAGE{
        {
            this.command = new ToPreviousPageCommand();
        }
    },
    REGISTER_NEW_USER{
        {
            this.command = new RegisterNewUserCommand();
        }
    },
    SET_LOCALE {
        {
            this.command = new SetLocaleCommand();
        }
    },
    ADD_PRODUCT{
        {
            this.command = new AddProductCommand();
        }
    },
    ADD_MEAL{
        {
            this.command = new AddMealCommand();
        }
    },
    ADD_ACTIVITY{
        {
            this.command = new AddToActivityDiaryCommand();
        }
    },
    SELECT_DATE{
        {
            this.command = new SelectDateCommand();
        }
    },
    UPDATE_USER_INFO{
        {
            this.command = new UpdateUserInfoCommand();
        }
    },
    CANCEL{
        {
            this.command = new CancelCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
