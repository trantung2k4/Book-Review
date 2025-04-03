/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Account;
import Model.Book;
import Model.BookDetail;
import Model.Category;
import Model.Publisher;
import dao.BookDAO;
import dao.CategoryDAO;
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
@WebServlet(name = "EditProductController", urlPatterns = {"/editproduct"})
public class EditProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); // Sửa đổi encoding của request

        CategoryDAO cateDAO = new CategoryDAO();
        BookDAO bookDAO = new BookDAO();

        String bookID = request.getParameter("pid");
        Book book = bookDAO.getBookById(bookID);
        List<Category> listCategory = cateDAO.getAllCategory();
        List<Publisher> listPublisher = cateDAO.getAllPublisher();

        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("loginedUser");

        if (user != null && (user.getIsSeller() == 1 || user.getIsAdmin() == 1)) {

            if (request.getParameter("editBtn") != null) { // nhấn xác nhận

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

                //optional
                String weight = "";
                String size = "";
                String pages = "";
                String date = "";
                String language = "";
                String username = "";

                bookID = request.getParameter("pid");
                title = request.getParameter("title");
                image = request.getParameter("image");
                price = request.getParameter("price");
                quantity = request.getParameter("quantity");
                author = request.getParameter("author");
                description = request.getParameter("description");
                type = request.getParameter("type");
                publisher = request.getParameter("publisher");
                username = request.getParameter("username");
                category = request.getParameter("category");

                weight = request.getParameter("weight");
                size = request.getParameter("size");
                pages = request.getParameter("pages");
                date = request.getParameter("date");
                language = request.getParameter("language");

                if (title == "" || description == "" || username == ""
                        || category == null) {

                    response.sendRedirect(request.getHeader("referer"));
                } else {

                    //book
                    Book newBook = new Book(book.getId(), image, 0, 0, title, description, type, username, null, category);

                    BookDetail newBookDetail = new BookDetail(0, "",
                            0, new Timestamp(new Date().getTime()), "");

                    bookDAO.editBook(newBook, newBookDetail);

                    //load lại book
                    book = bookDAO.getBookById(bookID);
                    BookDetail bookDetail = bookDAO.getBookDetailByBook(bookID);

                    request.setAttribute("book", book);
                    request.setAttribute("bookDetail", bookDetail);
                    request.setAttribute("listCategory", listCategory);
                    request.setAttribute("listPublisher", listPublisher);

                    request.setAttribute("managerMessage", "Đã chỉnh sửa sản phẩm");
                    request.getRequestDispatcher("/manager-edit-product.jsp").forward(request, response);
                }

            } else { //chuyển trang edit

                if (book != null) {

                    BookDetail bookDetail = bookDAO.getBookDetailByBook(bookID);

                    request.setAttribute("book", book);
                    request.setAttribute("bookDetail", bookDetail);
                    request.setAttribute("listCategory", listCategory);
                    request.setAttribute("listPublisher", listPublisher);
                    request.getRequestDispatcher("/manager-edit-product.jsp").forward(request, response);
                } else {

                    response.sendRedirect("404.html");
                }
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
