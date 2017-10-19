package edu.marshall.pub.bean;

public class Book extends Entry{

public Book(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
private String address;
private String url;
private String urldate;
private String title;
private String isbn;
private String shorttitle;
private String language;
private String publisher;
private String[] author;
private String note;
private String year;
private String editor;
private String volume;
private String number;
private String month;
private String series;
private String copyright;
private String keywords;
private String edition;

public String getEdition() {
	return edition;
}
public void setEdition(String edition) {
	this.edition = edition;
}
public String getSeries() {
	return series;
}
public void setSeries(String series) {
	this.series = series;
}
public String getCopyright() {
	return copyright;
}
public void setCopyright(String copyright) {
	this.copyright = copyright;
}
public String getKeywords() {
	return keywords;
}
public void setKeywords(String keywords) {
	this.keywords = keywords;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public String getVolume() {
	return volume;
}
public void setVolume(String volume) {
	this.volume = volume;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}
public String getShorttitle() {
	return shorttitle;
}
public void setShorttitle(String shorttitle) {
	this.shorttitle = shorttitle;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public String[] getAuthor() {
	return author;
}
public void setAuthor(String[] author) {
	this.author = author;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getEditor() {
	return editor;
}
public void setEditor(String editor) {
	this.editor = editor;
}
public String getUrldate() {
	return urldate;
}
public void setUrldate(String urldate) {
	this.urldate = urldate;
}


}
