/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Account;
import dao.AccountDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author admin
 */
public class SignUpController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String username = "";
        String password = "";
        String repassword = "";
        String email = "";

        //Láº¥y thÃ´ng tin nháº­p tá»« view
        username = request.getParameter("userR").trim();
        password = request.getParameter("passR");
        repassword = request.getParameter("repassR");
        email = request.getParameter("emailR").trim();

        if (username != "" && password != "" && repassword != "" && email != "") {

            if (!password.equals(repassword)) {

                request.setAttribute("signUpMessage", "Mật khẩu không trùng khớp !");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {

                AccountDAO accountDAO = new AccountDAO();
                Account user = accountDAO.checkAccountIsExists(username);

                if (user == null) {

                    accountDAO.signUp(username, password, email);
                    request.setAttribute("signUpSuccess", "Tạo tài khoản thành công !");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {

                    request.setAttribute("signUpMessage", "Tạo tài khoản thất bại !");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
        } else {

            request.setAttribute("signUpMessage", "Vui lòng nhập đầy đủ thông tin !");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
