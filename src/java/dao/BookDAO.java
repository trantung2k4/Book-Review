package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Book;
import Model.BookByCategory;
import Model.BookDetail;
import Model.Review;
import dal.DBContext;

public class BookDAO {

    Connection conn = null; //kết nối sql
    PreparedStatement ps = null; //thực hiện lệnh
    ResultSet rs = null; //kết quả

    public List<Review> getAllReviewByBook(String bookId) {

        List<Review> list = new ArrayList<>();
        String query = "select * from Review where bookID = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, bookId);
            rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Review(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getTimestamp(7)));
            }
        } catch (Exception e) {

        }

        return list;
    }

    public List<Review> getReviewPagingByBook(String bookId, int page, int total) {

        List<Review> list = new ArrayList<>();
        String query = "select * from Review\r\n"
                + "where bookID = ?\r\n"
                + "order by id\r\n"
                + "offset ? rows fetch next ? rows only";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, bookId);
            ps.setInt(2, (page - 1) * total);
            ps.setInt(3, total);
            rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Review(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getTimestamp(7)));
            }
        } catch (Exception e) {

        }

        return list;
    }

    public int getTotalReviewByBook(String bookId) {

        String query = "select count(*) from Review where bookID = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, bookId);
            rs = ps.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public int getReviewTotalRatingByBook(String bookId) {

        String query = "select sum(rating) from review where bookId = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, bookId);
            rs = ps.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public void setRating(List<Book> list) {

        for (Book b : list) {

            String id = String.valueOf(b.getId());
            b.setRating(getReviewTotalRatingByBook(id), getTotalReviewByBook(id));
        }
    }

    public void review(Review review) {

        String query = "insert into Review values(?, ?, ?, ?, ?, getdate());";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, review.getUserId());
            ps.setString(2, review.getUsername());
            ps.setInt(3, review.getBookId());
            ps.setString(4, review.getContent());
            ps.setInt(5, review.getRating());
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public List<Book> getAllBook() {

        List<Book> list = new ArrayList<>();
        String query = "select b.*,bd.bDate from Book b join BookDetail bd on b.id = bd.bookID";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                temp.setSellerId(rs.getString(11));
                temp.setbDate(rs.getTimestamp(12));
                list.add(temp);
            }
        } catch (Exception e) {

        }

        setRating(list);
        return list;
    }

    public List<BookByCategory> getAllBookGroupByCategory() {

        List<BookByCategory> list = new ArrayList<>();
        String query = "SELECT COUNT(*) AS bookCount, Category.categoryName, Category.id, (Select [image] from Category c1 where c1.id = Category.id) FROM Book JOIN Category ON Book.cateID = Category.id GROUP BY Category.categoryName, Category.id;";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                BookByCategory temp;
                temp = new BookByCategory(rs.getInt(1),
                        rs.getString(3),
                        rs.getString(2),rs.getString(4));
                
                list.add(temp);
            }
        } catch (Exception e) {

        }

        return list;
    }

    public int getTotalBook() {

        String query = "select count(*) from Book";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public List<Book> getAllBookPaging(int page, int total) {

        List<Book> list = new ArrayList<>();
        String query = "SELECT b.*, bd.bDate,  (SELECT SUM(r.rating) FROM Review r WHERE r.bookID = b.id) AS total_rating, (SELECT COUNT(r.rating) FROM Review r WHERE r.bookID = b.id) AS total_rating1 FROM Book b JOIN BookDetail bd ON b.id = bd.bookID ORDER BY b.id"
                + " OFFSET " + (page - 1) * total + " ROWS FETCH NEXT " + total + " ROWS ONLY;";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(
                                10));
                temp.setbDate(rs.getTimestamp(12));
                int totalRating = rs.getInt(13);
                int totalReview = rs.getInt(14);
                int averageReview = 0;
                if (totalRating != 0 && totalReview != 0) {
                    averageReview = totalRating / totalReview;
                }

                temp.setAverageReview(averageReview);

                list.add(temp);
            }
        } catch (Exception e) {

        }

        setRating(list);
        return list;
    }

    public int getBookCount() {
        int total = 0;
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement("SELECT COUNT(*) AS TotalCount FROM Book b JOIN BookDetail bd ON b.id = bd.bookID");

            rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return total;
    }

    public List<Book> getRandomBook() {

        List<Book> list = new ArrayList<>();
        String query = "select top 8 * FROM Book order by newid()";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public List<Book> getRecommendBook() {

        List<Book> list = new ArrayList<>();
        String query = "SELECT b.*, bd.bDate,  (SELECT SUM(r.rating) FROM Review r WHERE r.bookID = b.id) AS total_rating, \n"
                + "(SELECT COUNT(r.rating) FROM Review r WHERE r.bookID = b.id) AS total_rating1 \n"
                + "FROM Book b JOIN BookDetail bd ON b.id = bd.bookID ORDER BY total_rating DESC";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                int totalRating = rs.getInt(13);
                int totalReview = rs.getInt(14);
                int averageReview = 0;
                if (totalRating != 0 && totalReview != 0) {
                    averageReview = totalRating / totalReview;
                }

                temp.setTotalReview(totalReview);
                temp.setAverageReview(averageReview);
                list.add(temp);
            }
        } catch (Exception e) {
             System.out.print("BOOK DAO ERROR 366");
               System.out.print(e);
            e.printStackTrace();
        }

        return list;
    }

    public List<Book> getNextBookByCategory(String cId, int amount) {

        List<Book> list = new ArrayList<>();
        String query = "select * from book where cateID = ?\r\n"
                + "order by id\r\n"
                + "offset ? rows\r\n"
                + "fetch next 4 rows only";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, cId);
            ps.setInt(2, amount);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public List<Book> getNextBook(int amount) {

        List<Book> list = new ArrayList<>();
        String query = "select * from book\r\n"
                + "order by id\r\n"
                + "offset ? rows\r\n"
                + "fetch next 3 rows only";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        setRating(list);
        return list;
    }

    public List<Book> getBookByCategory(String categoryId, int page, int total) {

        List<Book> list = new ArrayList<>();
        String query = "select *, (select count(*) from Review r where  r.bookID = b.id group by r.bookID), (select sum(r.rating) from Review r where  r.bookID = b.id group by r.bookID) from book b join BookDetail bd "
                + "on b.id = bd.bookID where b.cateID = " + categoryId
                + " order by bd.bDate offset " + (page - 1) * total + " rows fetch next 5 rows only";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
            System.out.println(rs);
            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                temp.setbDate(rs.getTimestamp(16));
                temp.setTotalReview(rs.getInt(19));

                int totalRating = rs.getInt(20);
                int totalReview = rs.getInt(19);
                int averageReview = 0;
                if (totalRating != 0 && totalReview != 0) {
                    averageReview = totalRating / totalReview;
                }

                temp.setAverageReview(averageReview);

                list.add(temp);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        setRating(list);
        return list;
    }

    public List<Book> getTopBook() {

        List<Book> list = new ArrayList<>();
        String query = "select *, (select count(*) from Review r where  r.bookID = b.id group by r.bookID), \n"
                + "(select sum(r.rating) from Review r where  r.bookID = b.id group by r.bookID) as rating\n"
                + "from book b join BookDetail bd on b.id = bd.bookID  order by rating DESC offset 0 rows fetch next 5 rows only";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
            System.out.println(rs);
            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                temp.setbDate(rs.getTimestamp(16));
                temp.setTotalReview(rs.getInt(19));

                int totalRating = rs.getInt(20);
                int totalReview = rs.getInt(19);
                int averageReview = 0;
                if (totalRating != 0 && totalReview != 0) {
                    averageReview = totalRating / totalReview;
                }

                temp.setAverageReview(averageReview);

                list.add(temp);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        setRating(list);
        return list;
    }

    public int getTotalBookByCategory(String categoryId) {

        String query = "select count(*) from Book b join BookDetail bd on b.id = bd.bookID where cateId = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, categoryId);
            rs = ps.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public int getTotalBookByPublisher(String pubId) {

        String query = "select count(*) from Book where publisher = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, pubId);
            rs = ps.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public List<Book> getBookPagingByCategory(String categoryId, int page, int total) {

        List<Book> list = new ArrayList<>();
        String query = "select * from book\r\n"
                + "where cateID = ?\r\n"
                + "order by id\r\n"
                + "offset ? rows fetch next ? rows only";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, categoryId);
            ps.setInt(2, (page - 1) * total);
            ps.setInt(3, total);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

        }

        setRating(list);
        return list;
    }

    public List<Book> getBookPagingByPublisher(String pubId, int page, int total) {

        List<Book> list = new ArrayList<>();
        String query = "select * from book\r\n"
                + "where publisher = ?\r\n"
                + "order by id\r\n"
                + "offset ? rows fetch next ? rows only";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, pubId);
            ps.setInt(2, (page - 1) * total);
            ps.setInt(3, total);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

        }

        setRating(list);
        return list;
    }

    public List<Book> getBookFromSearch(String searchString) {

        List<Book> list = new ArrayList<>();
        String query = "select * from Book where title like ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

        }

        setRating(list);
        return list;
    }

    public int getTotalBookFromSearch(String searchString) {

        String query = "select count(*) from Book where title like ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public List<Book> getBookPagingFromSearch(String searchString, int page, int total) {

        List<Book> list = new ArrayList<>();
        String query = "select * from book\r\n"
                + "where title like ?\r\n"
                + "order by id\r\n"
                + "offset ? rows fetch next ? rows only";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            ps.setInt(2, (page - 1) * total);
            ps.setInt(3, total);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

        }

        setRating(list);
        return list;
    }

    public Book getBookById(String id) {

        String query = "select * from Book where id = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                temp.setRating(getReviewTotalRatingByBook(id), getTotalReviewByBook(id));
                return temp;
            }
        } catch (Exception e) {

        }

        return null;
    }

    public List<Book> getAllBookBySeller(int id) {

        List<Book> list = new ArrayList<>();

        String query = "select * from Book where sellID = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

        }

        return list;
    }

    public int getTotalBookBySeller(int id) {

        String query = "select count(*) from Book where sellID = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public List<Book> getBookPagingBySeller(int id, int page, int total) {

        List<Book> list = new ArrayList<>();
        String query = "select * from book\r\n"
                + "where sellID = ?\r\n"
                + "order by id\r\n"
                + "offset ? rows fetch next ? rows only";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (page - 1) * total);
            ps.setInt(3, total);
            rs = ps.executeQuery();

            while (rs.next()) {

                Book temp = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));

                list.add(temp);
            }
        } catch (Exception e) {

        }

        return list;
    }

    public void deleteBook(String id) {

        String query = "delete from BookDetail where bookID = ?\r\n"
                + "alter table Review nocheck constraint all\r\n"
                + "alter table OrderDetail nocheck constraint all \r\n"
                + "delete from Book where id = ?\r\n"
                + "alter table Review check constraint all \r\n"
                + "alter table OrderDetail check constraint all";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void deleteAllBookBySeller(int seller) {

        String query = "delete from bookdetail where bookID in\r\n"
                + "(select bookID from Book b join bookdetail b2 on b.id = b2.bookID where sellID = ?)\r\n"
                + "alter table Review nocheck constraint all\r\n"
                + "alter table OrderDetail nocheck constraint all \r\n"
                + "delete from Book where sellID = ?\r\n"
                + "alter table Review check constraint all \r\n"
                + "alter table OrderDetail check constraint all";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, seller);
            ps.setInt(2, seller);
            ps.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void deleteAllBook() {

        String query = "delete from bookdetail where bookID in\r\n"
                + "(select bookID from Book b join bookdetail b2 on b.id = b2.bookID)\r\n"
                + "alter table Review nocheck constraint all\r\n"
                + "alter table OrderDetail nocheck constraint all \r\n"
                + "delete from Book\r\n"
                + "alter table Review check constraint all \r\n"
                + "alter table OrderDetail check constraint all";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void insertBook(Book book, int seller) {

        String query = "insert into Book(image, price, amount, title, description, type, author, publisher, cateID, sellID) \r\n"
                + "values (?, ? ,? ,? ,? ,? ,? ,? ,? ,?);";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, book.getImage());
            ps.setDouble(2, book.getPrice());
            ps.setInt(3, book.getQuantity());
            ps.setString(4, book.getTitle());
            ps.setString(5, book.getDescription());
            ps.setString(6, book.getType());
            ps.setString(7, book.getAuthor());
            ps.setString(8, book.getPublisher());
            ps.setString(9, book.getCateId());
            ps.setInt(10, seller);
            ps.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void insertBookDetail(String weight, String size, String pages, Timestamp date, String language, int bookId) {

        String query = "insert into BookDetail(bookID, bWeight, size, pages, bDate, bLanguage) \r\n"
                + "values (?, ? ,? ,? ,?, ?);";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.setString(2, weight);
            ps.setString(3, size);
            ps.setString(4, pages);
            ps.setTimestamp(5, date);
            ps.setString(6, language);
            ps.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void addBook(Book book, String weight, String size, String pages, Timestamp date, String language, int seller) {

        String query = "select IDENT_CURRENT('Book')"; //chọn id của order vừa dc insert vào
        insertBook(book, seller);
        int insertedId = -1;

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                insertedId = rs.getInt(1);
            }

            insertBookDetail(weight, size, pages, date, language, insertedId);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public BookDetail getBookDetailByBook(String id) {

        String query = "select * from BookDetail where bookID = ?";
        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                BookDetail temp = new BookDetail(rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getTimestamp(5),
                        rs.getString(6));

                return temp;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void updateBook(Book book) {

        String query = "update Book\r\n"
                + "set [image] = ?,\r\n"
                + "price = ?,\r\n"
                + "amount = ?,\r\n"
                + "title = ?,\r\n"
                + "[description] = ?,\r\n"
                + "[type] = ?,\r\n"
                + "author = ?,\r\n"
                + "publisher = ?,\r\n"
                + "cateID = ?\r\n"
                + "where id = ?";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setString(1, book.getImage());
            ps.setDouble(2, book.getPrice());
            ps.setInt(3, book.getQuantity());
            ps.setString(4, book.getTitle());
            ps.setString(5, book.getDescription());
            ps.setString(6, book.getType());
            ps.setString(7, book.getAuthor());
            ps.setString(8, book.getPublisher());
            ps.setString(9, book.getCateId());
            ps.setInt(10, book.getId());
            ps.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void updateBookDetail(BookDetail bookDetail, int id) {

        String query = "update BookDetail\r\n"
                + "set bWeight = ?,\r\n"
                + "size = ?,\r\n"
                + "pages = ?,\r\n"
                + "bDate = ?,\r\n"
                + "bLanguage = ?\r\n"
                + "where bookID = ?";

        try {

            conn = new DBContext().getConnection(); //mở kết nối sql server
            ps = conn.prepareStatement(query);
            ps.setDouble(1, bookDetail.getWeight());
            ps.setString(2, bookDetail.getSize());
            ps.setInt(3, bookDetail.getPages());
            ps.setTimestamp(4, bookDetail.getDate());
            ps.setString(5, bookDetail.getLanguage());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void updateQuantityProduct(int bookId, int orderedQuantity) throws Exception {
        // Tìm số lượng sản phẩm hiện có trong kho
        String selectQuery = "SELECT amount FROM Book WHERE id = ?";
        String updateQuery = "UPDATE Book SET amount = ? WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement selectPs = conn.prepareStatement(selectQuery);
            PreparedStatement updatePs = conn.prepareStatement(updateQuery);

            // Lấy số lượng hiện có của sách
            selectPs.setInt(1, bookId);
            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {
                int currentQuantity = rs.getInt("amount");
                int remainingQuantity = currentQuantity - orderedQuantity;

                if (remainingQuantity >= 0) {
                    // Cập nhật số lượng sản phẩm còn lại trong kho
                    updatePs.setInt(1, remainingQuantity);
                    updatePs.setInt(2, bookId);
                    updatePs.executeUpdate();
                    System.out.println("Số lượng sản phẩm đã được cập nhật trong kho.");
                } else {
                    System.out.println("Không đủ sản phẩm trong kho để đáp ứng đơn hàng.");
                }
            } else {
                System.out.println("Không tìm thấy sách với ID đã cho.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật số lượng sản phẩm trong kho: " + e.getMessage());
        }
    }

    public void editBook(Book book, BookDetail bookDetail) {

        updateBook(book);
        updateBookDetail(bookDetail, book.getId());
    }

    public static void main(String[] args) {

        BookDAO dao = new BookDAO();

        List<Book> list2 = dao.getBookPagingFromSearch("", 1, 15);
        for (Book o : list2) {

            System.out.println(o);
        }

        List<Review> list3 = dao.getAllReviewByBook("1");
        for (Review o : list3) {

            System.out.println(o);
        }
    }

}
