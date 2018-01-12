package com.khelenyuk.service.command.client;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.service.command.commands.*;

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
    SELECT_DATE{
        {
            this.command = new SelectDateCommand();
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
