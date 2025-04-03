package AdminContrller;

import Model.Account;
import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DeleteAccountController", urlPatterns = {"/delete-account"})
public class DeleteAccountController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String rUserID = request.getParameter("removedUser");
        String rSelectedAll = request.getParameter("selectedAll");
        String[] rSelectedUserID = request.getParameterValues("selectedUser");
        String currentURL = request.getHeader("referer");

        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("loginedUser");

        if (user != null && user.getIsAdmin() == 1) {

            AccountDAO accountDAO = new AccountDAO();

            // Hành động
            if (rUserID != null) { // xoá tài khoản

                accountDAO.deleteAccount(rUserID);
                session.setAttribute("managerAccountMessage", "Đã xoá tài khoản khỏi danh sách");

            } else if (request.getParameter("rSelectedUserBtn") != null) { // Xoá nhiều account

                if (rSelectedUserID != null) {

                    for (int i = 0; i < rSelectedUserID.length; i++) {

                        if (rSelectedUserID[i] != null) {

                            accountDAO.deleteAccount(rSelectedUserID[i]);
                            session.setAttribute("managerAccountMessage", "Đã xoá các tài khoản khỏi danh sách");
                        }
                    }
                }
            } else if (rSelectedAll != null && Integer.parseInt(rSelectedAll) == 1) { // Xoá tất cả

                accountDAO.deleteAllAccount();
                session.removeAttribute("loginedUser");
                response.sendRedirect("home");
                return;
            }

            response.sendRedirect(currentURL);
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
