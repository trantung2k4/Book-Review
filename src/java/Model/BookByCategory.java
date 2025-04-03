package Model;

public class BookByCategory {

    private int bookCount;
    private String id;
    private String categoryName;
    private String img;

    public BookByCategory() {
    }

    public BookByCategory(int bookCount, String id, String categoryName,String img) {
        this.bookCount = bookCount;
        this.id = id;
        this.categoryName = categoryName;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
