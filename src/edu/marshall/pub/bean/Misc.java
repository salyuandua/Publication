package edu.marshall.pub.bean;

public class Misc extends Entry{
public Misc(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
//private String name;

private String url;
private String[] author;

public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String[] getAuthor() {
	return author;
}
public void setAuthor(String[] author) {
	this.author = author;
}

}
