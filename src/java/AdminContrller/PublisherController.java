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
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class PublisherController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookDAO bookDAO = new BookDAO();
        CategoryDAO cateDAO = new CategoryDAO();

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8"); // Thêm dòng này để đảm bảo mã chữ được ghi ra response là UTF-8
        String pubID = request.getParameter("pid");
        String selectedPage = request.getParameter("selectedPage");

        int page = 1;
        int total = 15;

        if (selectedPage != null) {

            page = Integer.parseInt(selectedPage); //trang hiện đang chọn
        }

        //phân trang
        int count = bookDAO.getTotalBookByPublisher(pubID);
        int endPage = count / total;

        if (count % total != 0) {

            endPage++;
        }

        List<Book> listBook = bookDAO.getBookPagingByPublisher(pubID, page, total);
        List<Category> listCategory = cateDAO.getAllCategory();
        List<Publisher> listPublisher = cateDAO.getAllPublisher();
        String selectedPublisher = cateDAO.getPublisherName(pubID);

        request.setAttribute("currentPage", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listBook", listBook);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listPublisher", listPublisher);
        request.setAttribute("selectedPID", pubID);
        request.setAttribute("selectedPublisher", selectedPublisher);

        request.getRequestDispatcher("/shop.jsp").forward(request, response);
    }

}
