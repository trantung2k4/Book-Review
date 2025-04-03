package Model;

import java.sql.Timestamp;

public class BookDetail {
	
	private double weight;
	private String size;
	private int pages;
	private Timestamp date;
	private String language;
	
	public BookDetail(double weight, String size, int pages, Timestamp date, String language) {
		
		this.weight = weight;
		this.size = size;
		this.pages = pages;
		this.date = date;
		this.language = language;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
