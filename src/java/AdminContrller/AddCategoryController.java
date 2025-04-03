package AdminContrller;

import Model.Account;
import Model.Book;
import Model.Category;
import Model.Publisher;
import dao.BookDAO;
import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class AddCategoryController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("loginedUser");
        int seller = user.getId();
            System.out.println("OH NO");
        if (user != null && (user.getIsSeller() == 1 || user.getIsAdmin() == 1)) {
            
            if (request.getParameter("addBtn") != null) { // nháº¥n xÃ¡c nháº­n

                //required
                String title = "";
                String image = "";
                String price = "";
                String quantity = "";
                String author = "";
                String description = "";
                String type = "";
                String publisher = "";
                String category = "";
                String username = "";

                //optional
                String weight = "";
                String size = "";
                String pages = "";
                String date = "";
                String language = "";
                
                title = request.getParameter("title");
                image = request.getParameter("image");
                
                if (title == "") {
                    request.getRequestDispatcher("/addCategory.jsp").forward(request, response);
                } else {

                    
                    Category cate = new Category(-1, title,image);
                    CategoryDAO cateDAO = new CategoryDAO();
                    
                    cateDAO.insert(cate);
                    request.setAttribute("managerMessage", "Đã thêm danh mục !");
                  
                    request.getRequestDispatcher("/addCategory.jsp").forward(request, response);
                }
            } else { //chuyá»ƒn trang add

                CategoryDAO cateDAO = new CategoryDAO();
                
                List<Category> listCategory = cateDAO.getAllCategory();
                List<Publisher> listPublisher = cateDAO.getAllPublisher();
                
                request.setAttribute("listCategory", listCategory);
                request.setAttribute("listPublisher", listPublisher);
                request.getRequestDispatcher("/addCategory.jsp").forward(request, response);
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
