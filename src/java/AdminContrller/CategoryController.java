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
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO bookDAO = new BookDAO();
        CategoryDAO cateDAO = new CategoryDAO();

        response.setContentType("text/html;charset=UTF-8");
        String categoryID = request.getParameter("cid");
        String selectedPage = request.getParameter("selectedPage");

        int page = 1;
        int total = 15;

        if (selectedPage != null) {

            page = Integer.parseInt(selectedPage); //trang hiá»‡n Ä‘ang chá»�n
        }

        //phÃ¢n trang
        int count = bookDAO.getTotalBookByCategory(categoryID); //sá»‘ lÆ°á»£ng tá»•ng sá»‘ sÃ¡ch duyá»‡t
        int endPage = count / total; //tÃ­nh sá»‘ trang tá»‘i Ä‘a

        if (count % total != 0) {

            endPage++;
        }

        List<Book> listBook = bookDAO.getBookPagingByCategory(categoryID, page, total);
        
        List<Category> listCategory = cateDAO.getAllCategory();
        List<Publisher> listPublisher = cateDAO.getAllPublisher();
        String selectedCategory = cateDAO.getSelectedCategoryName(categoryID);

        request.setAttribute("currentPage", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listBook", listBook);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listPublisher", listPublisher);
        request.setAttribute("selectedCID", categoryID);
        request.setAttribute("selectedCategory", selectedCategory);

        request.getRequestDispatcher("/shop.jsp").forward(request, response);
    }

}
