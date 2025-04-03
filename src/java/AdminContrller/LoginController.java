/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminContrller;

import Model.Account;
import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.AppUtils;


/**
 *
 * @author admin
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
		request.setAttribute("redirectId", request.getParameter("redirectId"));
		request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  	response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		AccountDAO accountDAO = new AccountDAO();
		
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		Account user = accountDAO.login(username, password); 
		
		if (user == null) {
			
			request.setAttribute("loginMessage", "Sai tài khoản hoặc mật khẩu !");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		} else {
			
	
			HttpSession session = request.getSession();
			session.setAttribute("loginedUser", user);
			
			int redirectId = -1;
			
			try {
				redirectId = Integer.parseInt(request.getParameter("redirectId"));
			} catch (NumberFormatException e) {
			}
			
			String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
			
			if (requestUri != null) {
				
				response.sendRedirect(requestUri);
				return;
			} else {
				
				if (user.getIsAdmin() == 1) {
					
					response.sendRedirect("admin");
				} else {
					
					response.sendRedirect("home");
				}
			}
		}
	}

}
