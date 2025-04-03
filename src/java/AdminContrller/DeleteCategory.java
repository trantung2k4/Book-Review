package AdminContrller;

import Model.Account;
import Model.Category;
import dao.BookDAO;
import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class DeleteCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String rBookID = request.getParameter("id");
        String rSelectedAll = request.getParameter("selectedAll");
        String[] rSelectedBookId = request.getParameterValues("selectedBook");


        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("loginedUser");

        if (user != null && (user.getIsSeller() == 1 || user.getIsAdmin() == 1)) {
            boolean isAdmin = user.getIsAdmin() == 1;
            CategoryDAO cateDao = new CategoryDAO();
            int userId = user.getId();
            List<Category> listCategory = cateDao.getAllCategory();

            // Hành động
            if (rBookID != null) { // Xoá sản phẩm
                if (isAdmin) {
                    cateDao.delete(rBookID);
                    session.setAttribute("managerMessage", "Đã xoá khỏi danh sách");
                } else {
                    for (Category cate : listCategory) {
                        if (Integer.parseInt(rBookID) == cate.getcId()) {
                            cateDao.delete(rBookID);
                            session.setAttribute("managerMessage", "Đã xoá khỏi danh sách");
                        }
                    }
                }
            } else if (request.getParameter("rSelectedBookBtn") != null) { // Xoá nhiều sản phẩm
                if (rSelectedBookId != null) {
                    for (int i = 0; i < rSelectedBookId.length; i++) {
                        if (rSelectedBookId[i] != null) {
                            if (isAdmin) {
                                cateDao.delete(rSelectedBookId[i]);
                                session.setAttribute("managerMessage", "Đã xoá khỏi danh sách");
                            } else {
                               for (Category cate : listCategory) {
                                    if (Integer.parseInt(rSelectedBookId[i]) == cate.getcId()) {
                                       cateDao.delete(rSelectedBookId[i]);
                                        session.setAttribute("managerMessage", "Đã xoá khỏi danh sách");
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (rSelectedAll != null && Integer.parseInt(rSelectedAll) == 1) { // Xoá tất cả sản phẩm
               
            }
            response.sendRedirect("manageCategory");
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

