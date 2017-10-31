package edu.marshall.pub.bean;

public class Entry implements Comparable<Entry>{
	public static final int ignoreOrder=-1;
	public static final int OrderByName=0;
	public static final int OrderByTitle=1;
	public static final int OrderByYear=2;
	private String name;
	private String title="";
	private String year="0";
	private int order=OrderByName;
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Entry(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	/**
	 * 
	 */
	public int compareTo(Entry o) throws NullPointerException{
		if(order==OrderByName){//compare by name
			if(this.name==null||o.getName()==null){
				throw new NullPointerException("Name of entry is null");
			}
			return name.compareTo(o.getName());
		}else if(order==OrderByTitle){//compare by title
			return title.compareTo(o.getTitle());
			
			
		}else{//compare by year
			//return year.compareTo(o.getYear());
			
			int year1=Integer.parseInt(year);
			int year2=Integer.parseInt(o.getYear());
			if(year1==year2){
				return 0; 
			}else if(year1>year2){
				return 1;
			}else{
				return -1;
			}
		}
		
		


		
	}
	public String toString(){
		return "Entry name:"+name;
	}
	
}
