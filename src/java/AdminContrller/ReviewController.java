/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Account;
import Model.Review;
import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class ReviewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String currentURL = request.getHeader("referer"); //láº¥y trang hiá»‡n táº¡i

        int userId = -1;
        String username = "";
        int bookId = -1;
        String content = "";
        int rating = 1;
        Account user = (Account) session.getAttribute("loginedUser"); //láº¥y ngÆ°á»�i dÃ¹ng tá»« session

        if (user != null) { //náº¿u cÃ³ ng dÃ¹ng

            userId = user.getId();
            username = user.getUsername();
            content = request.getParameter("content");
            try {
                bookId = Integer.parseInt(request.getParameter("pid"));
            } catch (Exception e) {

            }
            try {
                rating = Integer.parseInt(request.getParameter("rating"));
            } catch (Exception e) {

            }

            if (bookId == -1) {

                response.sendRedirect("404.jsp");
                return;
            }

            if (content != "") {

                Review temp = new Review(-1, userId, username, bookId, content, rating); //táº¡o review
                BookDAO bookDAO = new BookDAO();
                bookDAO.review(temp);

                response.sendRedirect(currentURL);
            } else {

                response.sendRedirect(currentURL);
            }
        } else {

            response.sendRedirect(currentURL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
