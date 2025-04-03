/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package AdminContrller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.Account;
import dao.AccountDAO;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class EditAccountController
 */
public class EditAccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        AccountDAO accountDAO = new AccountDAO();
        String userID = request.getParameter("uid");
        Account account = accountDAO.getAccountById(userID);
        String currentURL = request.getHeader("referer");
        
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("loginedUser");
        
        if (user != null && user.getIsAdmin() == 1) {
            
            if (request.getParameter("rUpdateRole") != null) { //ấn áp dụng trong admin.jsp >> chỉ update role
                
                String[] rSellerList = request.getParameterValues("isSeller");
                String[] rAdminList = request.getParameterValues("isAdmin");
                
                accountDAO.resetRole();
                
                if (rSellerList != null) {
                    
                    for (int i = 0; i < rSellerList.length; i++) {

                        if (rSellerList[i] != null) {
                            
                            accountDAO.setSeller(rSellerList[i]);
                        }
                    }
                }
                
                if (rAdminList != null) {
                    
                    for (int i = 0; i < rAdminList.length; i++) {

                        if (rAdminList[i] != null) {
                            
                            accountDAO.setAdmin(rAdminList[i]);
                        }
                    }
                }
                
                session.setAttribute("managerMessage", "Đã thay đổi vai trò của các tài khoản!");
                response.sendRedirect(currentURL);
                
            } else if (request.getParameter("editBtn") != null) { // nhấn xác nhận trong trang edit
                
                //required
                String username = "";
                String password = "";
                String email = "";
                int isSeller = -1;
                int isAdmin = -1;
                
                username = request.getParameter("username").trim();
                password = request.getParameter("password");
                email = request.getParameter("email").trim();
                isSeller = Integer.parseInt(request.getParameter("isSeller"));
                isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
                
                if (username == null || password == null || email == null || isSeller == -1 || isAdmin == -1) {
                    
                    response.sendRedirect(request.getHeader("referer"));
                } else {
                    
                    //account
                    Account newAccount = new Account(account.getId(), username, password, email, isSeller, isAdmin);
                    
                    //updated
                    accountDAO.editAccount(newAccount);
                    
                    //load lại account và page
                    account = accountDAO.getAccountById(userID);
                    request.setAttribute("user", account);
                    request.setAttribute("userEditMessage", "Đã chỉnh sửa tài khoản");
                    request.getRequestDispatcher("/edit-user.jsp").forward(request, response);
                }
            } else { //chuyển trang edit
                
                request.setAttribute("user", account);
                request.getRequestDispatcher("/edit-user.jsp").forward(request, response);
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
