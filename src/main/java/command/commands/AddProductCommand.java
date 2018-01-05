package command.commands;

import command.ActionCommand;
import entity.Product;
import entity.User;
import service.PageLogic;
import service.ProductLogic;
import servlet.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddProductCommand implements ActionCommand {
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_CALORIES = "calories";
    private static final String PARAM_NAME_PROTEIN = "protein";
    private static final String PARAM_NAME_FAT = "fat";
    private static final String PARAM_NAME_CARBS = "carbs";
    private static final String PARAM_NAME_BUTTON = "button";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = null;

        if(request.getParameter(PARAM_NAME_BUTTON).equals("Отменить")){
            page = servlet.ConfigurationManager.getProperty("path.page.main");
            return page;
        }

        Product product = new Product(
                request.getParameter(PARAM_NAME_NAME),
                Float.valueOf(request.getParameter(PARAM_NAME_CALORIES)),
                Float.valueOf(request.getParameter(PARAM_NAME_PROTEIN)),
                Float.valueOf(request.getParameter(PARAM_NAME_FAT)),
                Float.valueOf(request.getParameter(PARAM_NAME_CARBS))
        );



        if (ProductLogic.addProduct(product)) {
            session.setAttribute("successAddProductMessage", MessageManager.getProperty("message.addproductsuccess"));
            PageLogic.updatePageData(session, ((User)session.getAttribute("user")).getId());
            page = servlet.ConfigurationManager.getProperty("path.page.main");
        } else {
            session.setAttribute("errorAddProductMessage", MessageManager.getProperty("message.addproducterror"));
            page = servlet.ConfigurationManager.getProperty("path.page.addProduct");
        }


        return page;
    }
}
