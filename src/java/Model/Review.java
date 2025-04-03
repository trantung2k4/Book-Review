package Model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Review {

	private int id;
	private int userId;
	private String username;
	private int bookId;
	private String content;
	private int rating;
	private Timestamp date;
	
	public Review(int id, int userId, String username, int bookId, String content, int rating, Timestamp date) {
		this.id = id;
		this.userId = userId;
		this.setUsername(username);
		this.bookId = bookId;
		this.content = content;
		this.rating = rating;
		this.setDate(date);
	}
	
	public Review(int id, int userId, String username, int bookId, String content, int rating) {
		this.id = id;
		this.userId = userId;
		this.setUsername(username);
		this.bookId = bookId;
		this.content = content;
		this.rating = rating;
	}
	
	public String getTimeString() {
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		return dateFormat.format(date);
	}
	
	public String getDateString() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
		return dateFormat.format(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public String toString() {
		return "Review [id=" + id + ", userId=" + userId + ", username=" + username + ", bookId=" + bookId
				+ ", content=" + content + ", rating=" + rating + ", date=" + date.getTime() + "]";
	}
	
}
