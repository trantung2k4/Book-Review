/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Account;
import Model.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author admin
 */
public class ManagerController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        Account user = (Account) session.getAttribute("loginedUser");

        if (user != null && user.getIsAdmin()== 1) {

            BookDAO bookDAO = new BookDAO();
            int userId = user.getId();
            if (user.getIsAdmin() == 1) {
                List<Book> listBook = bookDAO.getAllBook();

                request.setAttribute("listBook", listBook);
                request.getRequestDispatcher("/manager.jsp").forward(request, response);
                return;
            }
            List<Book> listBook = bookDAO.getAllBookBySeller(userId);

            request.setAttribute("listBook", listBook);
            request.getRequestDispatcher("/manager.jsp").forward(request, response);
        } else {

            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
