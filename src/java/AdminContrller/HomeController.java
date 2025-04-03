/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Book;
import Model.BookByCategory;
import Model.Category;
import Model.Publisher;
import dao.BookDAO;
import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author admin
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // Sửa thành UTF-8
        //b1: lấy data trong DAO
        BookDAO bookDAO = new BookDAO();
        CategoryDAO cateDAO = new CategoryDAO();
        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;

        List<Book> listBook = bookDAO.getAllBookPaging(currentPage, 6);
        int bookCount = bookDAO.getBookCount();
        List<Category> listCategory = cateDAO.getAllCategory();
        List<Category> listRandomCategory = cateDAO.getRandomCategory();
        List<Publisher> listPublisher = cateDAO.getAllPublisher();
        List<Book> listRandom = bookDAO.getRandomBook();
        Collections.sort(listBook, (Book b1, Book b2) -> Integer.compare(b2.getRating()[0], b1.getRating()[0]) // Sắp xếp giảm dần
        );
	List<Book> listRecommend = bookDAO.getRecommendBook();
        List<BookByCategory> bookByCategory = bookDAO.getAllBookGroupByCategory();
        //b2: set data vào jsp

        request.setAttribute("listBookByCategory", bookByCategory);
        request.setAttribute("listBook", listBook);
        request.setAttribute("bookCount", bookCount);
        request.setAttribute("listRandom", listRandom);
        request.setAttribute("listRecommend", listRecommend);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listRandomCategory", listRandomCategory);
        request.setAttribute("listPublisher", listPublisher);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
