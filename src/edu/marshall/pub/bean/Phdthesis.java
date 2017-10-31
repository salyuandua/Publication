package edu.marshall.pub.bean;

public class Phdthesis extends Entry{
public Phdthesis(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
//private String name;

private String type;
private String Abstract;
private String shorttitle;
private String url;
private String urldate;
private String school;
private String[] author;

private String address;
private String language;
private String month;
private String note;

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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}

public String getShorttitle() {
	return shorttitle;
}
public void setShorttitle(String shorttitle) {
	this.shorttitle = shorttitle;
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
public String getSchool() {
	return school;
}
public void setSchool(String school) {
	this.school = school;
}
public String[] getAuthor() {
	return author;
}
public void setAuthor(String[] author) {
	this.author = author;
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getAbstract() {
	return Abstract;
}
public void setAbstract(String abstract1) {
	Abstract = abstract1;
}


}
