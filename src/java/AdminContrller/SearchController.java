/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Book;
import Model.Category;
import Model.Publisher;
import dao.BookDAO;
import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author admin
 */
public class SearchController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO bookDAO = new BookDAO();
        CategoryDAO cateDAO = new CategoryDAO();

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String searchString = request.getParameter("searchBar"); //lấy string trong searchbar
        String selectedPage = request.getParameter("selectedPage");
        int page = 1;
        int total = 15;

        if (selectedPage != null) {

            page = Integer.parseInt(selectedPage); //trang hiện đang chọn
        }

        //phân trang
        int count = bookDAO.getTotalBookFromSearch(searchString);
        int endPage = count / total;

        if (count % total != 0) {

            endPage++;
        }

        List<Book> listBook = bookDAO.getBookPagingFromSearch(searchString, page, total);
        List<Category> listCategory = cateDAO.getAllCategory();
        List<Publisher> listPublisher = cateDAO.getAllPublisher();

        request.setAttribute("currentPage", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listBook", listBook);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listPublisher", listPublisher);
        request.setAttribute("searchBarString", searchString);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

}
