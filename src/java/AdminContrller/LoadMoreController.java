/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Book;
import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoadMoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8"); // Đảm bảo encoding là UTF-8 để ghi ra response

        BookDAO bookDAO = new BookDAO();

        String cId = request.getParameter("cateId");
        int amount = Integer.parseInt(request.getParameter("total"));

        if (cId != null) { //load random
            List<Book> listBook = bookDAO.getNextBookByCategory(cId, amount);
            PrintWriter out = response.getWriter();
            for (Book book : listBook) {
                out.println("<div class=\"col-xs-12 col-sm-6 col-md-3 product-random-" + cId + "\">\r\n"
                        + "    <div class=\"product-image-wrapper\">\r\n"
                        + "        <div class=\"single-products\">\r\n"
                        + "            <div class=\"productinfo text-center\">\r\n"
                        + "                <a href = \"details?pid=" + book.getId() + "\"><img src=\"" + book.getImage() + "\" alt=\"\" /></a>\r\n"
                        + "                <h2>" + book.getPrice() + "đ</h2>\r\n"
                        + "                <p><a href = \"details?pid=" + book.getId() + "\">" + book.getTitle() + "</a></p>\r\n"
                        + "                <button type=\"button\" onclick=\"addToCart(" + book.getId() + ", 1)\" class=\"btn btn-default add-to-cart\"><i class=\"fa fa-shopping-cart\"></i>Thêm vào giỏ</button>\r\n"
                        + "            </div>\r\n"
                        + "        </div>\r\n"
                        + "    </div>\r\n"
                        + "</div>");
            }
        } else { //load sản phẩm
            List<Book> listBook = bookDAO.getNextBook(amount);
            PrintWriter out = response.getWriter();
            for (Book book : listBook) {

                String content = """
                                 <div class="col-sm-4 product-show">\r
                                                                     <div class="product-image-wrapper">\r
                                                                         <div class="single-products">\r
                                                                             <div class="productinfo text-center">\r
                                                                                 <a href = "details?pid=""" + book.getId() + "\"><img src=\"" + book.getImage() + "\" alt=\"\" /></a>\r\n"
                        + "                                                <h2>" + book.getPrice() + " đ</h2>\r\n"
                        + "                                                <p><a href = \"details?pid=" + book.getId() + "\">" + book.getTitle() + "</a></p>\r\n"
                        + "                                                <a onclick=\"addToCart(" + book.getId() + ", 1)\" class=\"btn btn-default add-to-cart\"><i class=\"fa fa-shopping-cart\"></i>Thêm vào giỏ</a>\r\n"
                        + "                                            </div>\r\n"
                        + "                                        </div>\r\n"
                        + "                                        <div class=\"choose\">\r\n"
                        + "                                            <ul class=\"nav nav-pills nav-justified\">\r\n"
                        + "                                                <li><a href=\"\"><i class=\"fa fa-plus-square\"></i>Yêu thích</a></li>\r\n"
                        + "                                                <li>\r\n"
                        + "                                                <a class =\"rating\">\r\n";

                for (int i = 1; i <= book.getRating()[1]; i++) {
                    content += "<i class=\"fa fa-star\"></i>";
                }
                if (book.getRating()[2] != 0) {

                    content += "<i class=\"fa fa-star-half-o\"></i>";

                    for (int j = 1; j <= (5 - book.getRating()[1] - 1); j++) {
                        content += "<i class=\"fa fa-star-o\"></i>";
                    }
                } else {

                    for (int j = 1; j <= (5 - book.getRating()[1]); j++) {
                        content += "<i class=\"fa fa-star-o\"></i>";
                    }
                }
                content += "(" + book.getRating()[0] + ")\r\n"
                        + "                                                </a>\r\n"
                        + "                                                </li>\r\n"
                        + "                                            </ul>\r\n"
                        + "                                        </div>\r\n"
                        + "                                    </div>\r\n"
                        + "                                </div>";

                out.println(content);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
