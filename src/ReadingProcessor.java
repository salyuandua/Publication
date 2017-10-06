import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * This is a reader for reading the .bib file line by line and 
 * analyze the content of .bib file into a set of entries.
 * @author
 *
 */


public class ReadingProcessor {
private Scanner sc;
private Class<Entry> claz;
private Entry[] entrys=new Entry[20001];
private Entry entry;
private Field[] fields;
private int size;
/**
 * 
 * @param filePath The absolute path of existing .bib file 
 */
	public ReadingProcessor(String filePath) {
		File file=new File(filePath);
		try {
			sc=new Scanner(file);
			claz=null;
			entry=null;
			size=0;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int entrySize(){
		return size;
	}
	
	public Entry[] read(){
		String key="";
		while(sc.hasNext()){
			String thisLine=sc.nextLine().trim();
			//System.out.println("This Line is "+thisLine);
			if(thisLine.startsWith("@")){//entry start
				//if entry is not null, add it to set
				if(entry!=null){
					entrys[size++]=entry;
					entry=null;
				}
				//phrase "article@{name," to "article" and "name"
				
				String[] strArray=thisLine.split("\\{");
				String entryType=Untils.FirstChar2Up(strArray[0].substring(1));
				String entryName=strArray[1];
				//remove ',' if it exists
				if(entryName.endsWith(",")){
					entryName=entryName.substring(0, entryName.length()-1);
				}
				System.out.println("entry name is "+entryName+", entry type is "+entryType);

				try {
						claz=(Class<Entry>) Class.forName(entryType);
						Object o=claz.newInstance();
					
						if(o instanceof Entry){
							entry=(Entry) o;
							fields=claz.getFields();
						}else{
							System.out.println("The type '"+claz.getTypeName()+"' is not a implemention of entry");
						}
						//set value property for name of every entry
						setter("name", entryName);
					} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}catch(InstantiationException e){
						e.printStackTrace();
					} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
				}
				
			}else if(thisLine.trim().equals("")||thisLine==null||thisLine.equals("}")){// no information row

				
			}else{//read fields
				String value="";
				if(thisLine.contains("=")){//the beginning of the field like 'title=value' 
					String[] strArray=thisLine.split("=");
					if(strArray[0].trim().length()<=15){
						//prepare key and value
						key=strArray[0].trim();
							value=strArray[1].trim();
							if(value.startsWith("{")){//reomve '{'
								value=value.substring(1);
							}
					}else{
						value=(String) getter(key);//get current value
						value=value+" "+thisLine;//build new value
					}

					
					

				}else{//not the beginning of the field
					value=(String) getter(key);//get current value
					value=value+" "+thisLine;//build new value
					
				}
				//System.out.println("oldVal :"+value);
				//remove probably '}' and ','
				if(value.charAt(value.length()-1)==','&&value.charAt(value.length()-2)=='}'){//the end of this field contains ','
					value=value.substring(0, value.length()-2);
					
				}else if(value.charAt(value.length()-1)=='}'){//the end of this field not contains ','
					value=value.substring(0, value.length()-1);
				}
				//System.out.println("newVal :"+value);
				//process author array
					if(key.equals("author")){
						String[] authors=value.split("and"); 
						setter(key, authors);
					}else{
						setter(key, value);
					}
			}
		}
		if(entry!=null){//probably this is end of the file
			entrys[size++]=entry;
		}
		sc.close();
		return entrys;
	}
	
	
	private Object getter(String key){
		Object o=null;
		try {
			Method getter=claz.getDeclaredMethod("get"+Untils.FirstChar2Up(key));
			o=getter.invoke(entry);
		}  catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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
		return o;
	}
	
	
	
	
	private void setter(String key,Object value){
		try {
			//Field field=claz.getDeclaredField(key);
			Class fieldType=String.class;
			if(key.equals("author")){
				fieldType=String[].class;
			}
			Method setter=claz.getDeclaredMethod("set"+Untils.FirstChar2Up(key), fieldType);
			setter.invoke(entry, value);
		}  catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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
	}
	private boolean checkField(String key){
		System.out.println("Key is "+key);
		for(Field f:fields){
			if(f.getName().toLowerCase().equals(key)){
				return true;
			}
		}
		return false;
	}
	
}
