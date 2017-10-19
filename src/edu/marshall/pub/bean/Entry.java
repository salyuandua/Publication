package edu.marshall.pub.bean;

public class Entry implements Comparable<Entry>{
	private String name;
	public Entry(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Entry o) throws NullPointerException{
		if(this.name==null||o.getName()==null){
			throw new NullPointerException("Name of entry is null");
		}
		return name.compareTo(o.getName());
		
	}
	public String toString(){
		return "Entry name:"+name;
	}
	
}
