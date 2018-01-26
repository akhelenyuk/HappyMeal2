package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.model.Product;
import com.khelenyuk.model.User;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.IProductService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddNewProductCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddNewProductCommand.class);

    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_CALORIES = "calories";
    private static final String PARAM_NAME_PROTEIN = "protein";
    private static final String PARAM_NAME_FAT = "fat";
    private static final String PARAM_NAME_CARBS = "carbs";

    private IPageService pageService = ServiceFactory.getPageService();
    private IProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.addProduct");

        Product newProduct = getProductFromRequest(request);
        request.setAttribute("newProduct", newProduct);


        if (productService.checkProductExist(newProduct.getName())) {
            logger.info("Such product already exists!");
            request.setAttribute("errorProductExistMessage", MessageManager.getProperty("message.productexist"));
            return page;
        }

        if (productService.addProduct(newProduct)) {
            session.setAttribute("successAddProductMessage", MessageManager.getProperty("message.addproductsuccess"));
            pageService.updateMainPageData(session, ((User) session.getAttribute("user")).getId());
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            session.setAttribute("errorAddProductMessage", MessageManager.getProperty("message.addproducterror"));
            page = ConfigurationManager.getProperty("path.page.addProduct");
        }


        return page;
    }

    private Product getProductFromRequest(HttpServletRequest request) {
        Product product = new Product(
                request.getParameter(PARAM_NAME_NAME),
                Float.valueOf(request.getParameter(PARAM_NAME_CALORIES)),
                Float.valueOf(request.getParameter(PARAM_NAME_PROTEIN)),
                Float.valueOf(request.getParameter(PARAM_NAME_FAT)),
                Float.valueOf(request.getParameter(PARAM_NAME_CARBS))
        );
        return product;
    }
}
