package edu.marshall.pub.bean;

public class Techreport extends Entry{
	public Techreport(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	//private String name;

	private String url;
	private String urldate;
	private String institution;
	private String[] author;

	private String number;
	private String address;
	private String month;
	private String note;
	private String type;
	private String keywords;
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrldate() {
		return urldate;
	}
	public void setUrldate(String urldate) {
		this.urldate = urldate;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String[] getAuthor() {
		return author;
	}
	public void setAuthor(String[] author) {
		this.author = author;
	}

	
}
