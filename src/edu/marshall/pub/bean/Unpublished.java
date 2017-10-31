package edu.marshall.pub.bean;

public class Unpublished extends Entry{
	public Unpublished(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	//private String name;

	private String[] author;
	private String month;

	private String note;

	public String[] getAuthor() {
		return author;
	}
	public void setAuthor(String[] author) {
		this.author = author;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	

}
