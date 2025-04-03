package AdminContrller;

import Model.Account;
import Model.Book;
import dao.BookDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "DeleteProductController", urlPatterns = {"/delete-product"})
public class DeleteProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String rBookID = request.getParameter("removedBook");
        String rSelectedAll = request.getParameter("selectedAll");
        String[] rSelectedBookId = request.getParameterValues("selectedBook");
        String currentURL = request.getHeader("referer");

        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("loginedUser");

        if (user != null && (user.getIsSeller() == 1 || user.getIsAdmin() == 1)) {
            boolean isAdmin = user.getIsAdmin() == 1;
            BookDAO bookDAO = new BookDAO();
            int userId = user.getId();
            List<Book> listBook = bookDAO.getAllBookBySeller(userId);

            // Hành động
            if (rBookID != null) { // Xoá sản phẩm
                if (isAdmin) {
                    bookDAO.deleteBook(rBookID);
                    session.setAttribute("managerMessage", "Đã xoá sản phẩm khỏi danh sách");
                } else {
                    for (Book book : listBook) {
                        if (Integer.parseInt(rBookID) == book.getId()) {
                            bookDAO.deleteBook(rBookID);
                            session.setAttribute("managerMessage", "Đã xoá sản phẩm khỏi danh sách");
                        }
                    }
                }
            } else if (request.getParameter("rSelectedBookBtn") != null) { // Xoá nhiều sản phẩm
                if (rSelectedBookId != null) {
                    for (int i = 0; i < rSelectedBookId.length; i++) {
                        if (rSelectedBookId[i] != null) {
                            if (isAdmin) {
                                bookDAO.deleteBook(rSelectedBookId[i]);
                                session.setAttribute("managerMessage", "Đã xoá các sản phẩm khỏi danh sách");
                            } else {
                                for (Book book : listBook) {
                                    if (Integer.parseInt(rSelectedBookId[i]) == book.getId()) {
                                        bookDAO.deleteBook(rSelectedBookId[i]);
                                        session.setAttribute("managerMessage", "Đã xoá các sản phẩm khỏi danh sách");
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (rSelectedAll != null && Integer.parseInt(rSelectedAll) == 1) { // Xoá tất cả sản phẩm
                if (isAdmin) {
                    bookDAO.deleteAllBook();
                    session.setAttribute("managerMessage", "Đã xoá tất cả sản phẩm khỏi danh sách");
                } else {
                    bookDAO.deleteAllBookBySeller(user.getId());
                    session.setAttribute("managerMessage", "Đã xoá tất cả sản phẩm khỏi danh sách");
                }
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
