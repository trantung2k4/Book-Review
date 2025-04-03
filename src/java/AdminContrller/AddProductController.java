/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Account;
import Model.Book;
import Model.Category;
import Model.Publisher;
import dao.BookDAO;
import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class AddProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("loginedUser");
        int seller = user.getId();

        if (user != null && (user.getIsSeller() == 1 || user.getIsAdmin() == 1)) {

            if (request.getParameter("addBtn") != null) { // nháº¥n xÃ¡c nháº­n

                //required
                String title = "";
                String image = "";
                String price = "";
                String quantity = "";
                String author = "";
                String description = "";
                String type = "";
                String publisher = "";
                String category = "";
                String username = "";

                //optional
                String weight = "";
                String size = "";
                String pages = "";
                String date = "";
                String language = "";

                title = request.getParameter("title");
                image = request.getParameter("image");
                price = request.getParameter("price");
                quantity = request.getParameter("quantity");
                author = request.getParameter("author");
                description = request.getParameter("description");
                type = request.getParameter("type");
                publisher = request.getParameter("publisher");
                category = request.getParameter("category");
                username = request.getParameter("username");
                
                weight = request.getParameter("weight");
                size = request.getParameter("size");
                pages = request.getParameter("pages");
                date = request.getParameter("date");
                language = request.getParameter("language");

                if (title == "" || description == "" || username == "" 
                        || category == null) {

                    request.getRequestDispatcher("/manager-add-product.jsp").forward(request, response);
                } else {

                    //book
                    Book book = new Book(-1, image, 0, 0, title, description, null, username, null, category);
                    BookDAO bookDAO = new BookDAO();
                    CategoryDAO cateDAO = new CategoryDAO();

                    List<Category> listCategory = cateDAO.getAllCategory();
                    List<Publisher> listPublisher = cateDAO.getAllPublisher();

                    bookDAO.addBook(book, weight, size, pages, new Timestamp(new Date().getTime()), language, seller);
                    request.setAttribute("managerMessage", "Đã thêm sản phẩm !");
                    request.setAttribute("listCategory", listCategory);
                    request.setAttribute("listPublisher", listPublisher);
                    request.getRequestDispatcher("/manager-add-product.jsp").forward(request, response);
                }
            } else { //chuyá»ƒn trang add

                CategoryDAO cateDAO = new CategoryDAO();

                List<Category> listCategory = cateDAO.getAllCategory();
                List<Publisher> listPublisher = cateDAO.getAllPublisher();

                request.setAttribute("listCategory", listCategory);
                request.setAttribute("listPublisher", listPublisher);
                request.getRequestDispatcher("/manager-add-product.jsp").forward(request, response);
            }
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
