package Model;

import java.security.Timestamp;
import java.util.Arrays;
import java.util.Date;

public class Book {

    private int id;
    private String image;
    private double price;
    private int quantity;
    private String title;
    private String description;
    private String type;
    private String author;
    private String publisher;
    private String cateId;
    private String sellerId;
    private Date bDate;
    private int averageReview;
    private int totalReview;
    private int[] rating = new int[3];

    public Book() {
    }

    public Book(int id, String image, double price, int quantity, String title, String description, String type, String author, String publisher, String cateId) {

        this.id = id;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.title = title;
        this.description = description;
        this.type = type;
        this.author = author;
        this.publisher = publisher;
        this.cateId = cateId;
    }

    public int getId() {
        return id;
    }

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public int getAverageReview() {
        return averageReview;
    }

    public void setAverageReview(int averageReview) {
        this.averageReview = averageReview;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int[] getRating() {
        return rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSellerId(String id) {
        this.sellerId = id;
    }

    public String getSellerId() {
        return this.sellerId;
    }

    public double roundHalf(double number) {

        double diff = number - (int) number;
        if (diff < 0.25) {
            return (int) number;
        } else if (diff < 0.75) {
            return (int) number + 0.5;
        } else {
            return (int) number + 1;
        }
    }

    public void setRating(int rating, int total) {

        if (rating == 0 || total == 0) { //trả về 0 review

            this.rating = new int[]{0, 0, 0};
        } else {

            int[] temp = new int[3];
            temp[0] = total; //tổng số lượt review
            temp[1] = (int) Math.floor(rating / total); //tổng số sao full

            if (rating % total != 0) { //nửa sao

                temp[2] = 1;
            } else {

                temp[2] = 0;
            }

            this.rating = temp;
        }
    }

    public String toString() {
        return "Book [id=" + id + ", image=" + image + ", price=" + price + ", quantity=" + quantity + ", title="
                + title + ", description=" + description + ", type=" + type + ", author=" + author + ", publisher="
                + publisher + ", cateId=" + cateId + ", rating=" + Arrays.toString(rating) + "]";
    }
}
