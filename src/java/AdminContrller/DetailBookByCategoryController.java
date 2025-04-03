/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminContrller;

import Model.Book;
import Model.BookDetail;
import Model.Category;
import Model.Publisher;
import Model.Review;
import dao.BookDAO;
import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "DetailBookByCategoryController", urlPatterns = {"/detailBookByCategory"})
public class DetailBookByCategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = new BookDAO();
        CategoryDAO cateDAO = new CategoryDAO();

        response.setContentType("text/html;charset=UTF-8");
        String categoryID = request.getParameter("pid");
        request.setAttribute("categoryID", categoryID);

        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
        String categoryName = cateDAO.getSelectedCategoryName(categoryID);
        List<Book> listBook = bookDAO.getBookByCategory(categoryID, currentPage, 5);
        List<Book> listTopBook = bookDAO.getTopBook();
        int totalBook = bookDAO.getTotalBookByCategory(categoryID);
        if (listBook != null) {

            request.setAttribute("currentPage", currentPage);

            request.setAttribute("listBook", listBook);
            request.setAttribute("listTopBook", listTopBook);
            request.setAttribute("totalBook", totalBook);
            request.setAttribute("categoryName", categoryName);

            request.getRequestDispatcher("/BookByCategory.jsp").forward(request, response);
        } else {
            response.sendRedirect("404.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
