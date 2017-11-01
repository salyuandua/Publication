package edu.marshall.pub.until;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import edu.marshall.pub.bean.Entry;

public class Untils {

public static String FirstChar2Up(String str){
	str=str.trim();
	if(str.equals("")||str==null){
		return null;
	}
	str=str.substring(0, 1).toUpperCase()+str.substring(1);
	return str;
}

public static String lastChar2Low(String str){
	str=str.trim();
	if(str.equals("")||str==null){
		return null;
	}
	str=str.substring(0, 1).toLowerCase()+str.substring(1);
	return str;
}
//to convert single entry to string
public static String entryToString(Entry entry){
	StringBuilder str=new StringBuilder();
	int fieldNum=0;
	String longestField="";
	int longestLength=0;
	String[] authors = null;
	try{
	Class<Entry> clz=(Class<Entry>) entry.getClass();
	Field[] fields=clz.getDeclaredFields();//all fields this objecct has
	//write empty row in the beginning of each entry
	str.append("\n");
	//write the header of entry like '@article{name,'
	String typeName=entry.getName();
	//System.out.println("TypeNmae: "+typeName);
	str.append("@"+Untils.lastChar2Low(clz.getSimpleName())+"{"+typeName+",\n");
	longestField="name";
	longestLength=typeName.length();
	fieldNum++;
	//scan all fields
	
	for(Field field:fields){
		String key=field.getName().toLowerCase();
		//System.out.println("Key is :"+key);
		Method getter=clz.getMethod("get"+Untils.FirstChar2Up(key));//getter
		Object value=getter.invoke(entry);

		if(!key.equals("name")&&value!=null){//name field has been written. 
			if(field.getType()==String[].class){//author array
				authors=(String[]) value;
				value=authors[0];
				for(int j=1;j<authors.length;j++){
					value=value+" and "+authors[j];
				}
			}
			str.append("    "+key+"={"+value+"},\n");
			if(value.toString().length()>longestLength){
				longestLength=value.toString().length();
				longestField=key;
			}
			fieldNum++;

		}
	}
	//add summary for this entry
	//System.out.println(fieldNum);
	int authorNum=0;
	if(authors!=null){
		authorNum=authors.length;
	}
	str.append("    counts={fields:"+fieldNum+"; authors:"
			+ ""+authorNum+"; longest field:"+longestField+"; length:"+longestLength+"}\n}\n");
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return str.toString();
}

}
