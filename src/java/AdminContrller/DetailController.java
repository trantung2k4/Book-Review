package AdminContrller;

import Model.Book;
import Model.BookDetail;
import Model.Category;
import Model.Publisher;
import Model.Review;
import dao.BookDAO;
import dao.CategoryDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class DetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = new BookDAO();

        response.setContentType("text/html;charset=UTF-8");
        String bookID = request.getParameter("pid");
         request.setAttribute("bookID", bookID);
        String selectedPage = request.getParameter("selectedPage");

        Book book = bookDAO.getBookById(bookID);
        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;

        String totalParam = request.getParameter("total");
        int total = (totalParam != null && !totalParam.isEmpty()) ? Integer.parseInt(totalParam) : 6;

        List<Review> listReview = bookDAO.getReviewPagingByBook(bookID, currentPage, total);
        int reviewCount = bookDAO.getTotalReviewByBook(bookID);
        if (book != null) {

            BookDetail bookDetail = bookDAO.getBookDetailByBook(bookID);

            request.setAttribute("book", book);
            request.setAttribute("bookDetail", bookDetail);
            request.setAttribute("listReview", listReview);
            request.setAttribute("reviewCount", reviewCount);
            request.getRequestDispatcher("/product-details.jsp").forward(request, response);
        } else {
            response.sendRedirect("404.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
