package servlet;

import command.ActionCommand;
import command.factory.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = servlet.ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}


//    EntityDAO userTestDAO = new UserDAOImpl();
//    DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
//    Date date = new Date();
//        System.out.println(format.format(date));
//
////        User user = new User("Соня", "Сотник", "sonya@gmail.com", new Date(1988-10-31), Sex.FEMALE, 57, 174, Lifestyle.AVERAGE);
////        System.out.println(user);
////
////
////        System.out.println(userTestDAO.update(8, user));
//
//
//                // ----- test for getAllUsers ------
////        for (User user : (List<User>)userTestDAO.getAll()) {
////            System.out.println(user);
////
////        }
////        System.out.println(userTestDAO.get(3));
////        int result = userTestDAO.add(new User("Саша", "Белый", "sanya@gmail.com", new Date(198822L), Sex.MALE, 87, 193, Lifestyle.LAZY, 2700, Role.USER));
////        System.out.println(result);
//
//                System.out.println();
//
////        ProductDao productDao = new ProductDAOImpl();
//// ----- test for ADDProduct ------

//        System.out.println(productDao.add(new Product("Гречка зеленая3", 150, 18, 7, 45)));


// ----- test for DELETEProduct ------
//        System.out.println("productDao.delete(3) = " + productDao.delete(3));


// ----- test for UPDATEProduct ------
//        System.out.println("NUMBER OF UPDATED ROWS: " +productDao.update(1, new Product(3,"Икра2",1, 2, 3, 4)));


// ----- test for getProduct ------
//          System.out.println(productDao.getProduct(0););

// ----- test for getAll ------
//        for (Product product : productDao.getAll()
//                ) {
//            System.out.println(product);
//        }