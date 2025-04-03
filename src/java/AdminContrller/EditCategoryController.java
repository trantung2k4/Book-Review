/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminContrller;

import Model.Account;
import Model.BookDetail;
import Model.Category;
import Model.Publisher;
import dao.BookDAO;
import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class EditCategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); // Sửa đổi encoding của request

        CategoryDAO cateDAO = new CategoryDAO();

        String id = request.getParameter("pid");
        Category cate = cateDAO.getById(id);

        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("loginedUser");

        if (user != null && (user.getIsSeller() == 1 || user.getIsAdmin() == 1)) {

            if (request.getParameter("editBtn") != null) { // nhấn xác nhận

                String title = "";
                String image = "";
                title = request.getParameter("title");
                image = request.getParameter("image");

                if (title == "") {
                    response.sendRedirect("home");
                } else {

                    Category category = new Category(cate.getcId(), title, image);
                  String query =  cateDAO.update(category);
                          request.setAttribute("managerMessage", "Đã chỉnh sửa sản phẩm");
                       
        
                  
              
                  response.sendRedirect("manageCategory");
                }

            } else { //chuyển trang edit

                if (cate != null) {

                    request.setAttribute("cate", cate);

                    request.getRequestDispatcher("/EditCate.jsp").forward(request, response);
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
