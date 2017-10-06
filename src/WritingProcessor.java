import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WritingProcessor {
private BufferedWriter bWriter;
	public WritingProcessor(String filePath) {
		try {
			File file=new File(filePath);
			//System.out.println(file.exists());
			if(!file.exists()){
				if(!file.getParentFile().exists()){//dir not exists
					//create dir
					file.getParentFile().mkdir();
				}
				
				file.createNewFile();
				
			}
			FileWriter fileWriter=new FileWriter(file);
			bWriter=new BufferedWriter(fileWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int writeEntries(Entry[] entries,int entrySize){
		int succeedEntry=0;
		Class<Entry> claz;
		for(int i=0;i<entrySize;i++){
			Entry entry=entries[i];
			int fieldNum=0;
			String longestField="";
			int longestLength=0;
			String[] authors = null;
			claz=(Class<Entry>) entry.getClass();
			Field[] fields=claz.getDeclaredFields();//all fields this objecct has
			
			try {
				//write empty row in the beginning of each entry
				bWriter.append("\n");
				//write the header of entry like '@article{name,'
				String typeName=(String) claz.getDeclaredMethod("getName").invoke(entry);
				//System.out.println("TypeNmae: "+typeName);
				bWriter.append("@"+Untils.lastChar2Low(claz.getName())+"{"+typeName+",\n");
				longestField="name";
				longestLength=typeName.length();
				fieldNum++;
				//scan all fields
				for(Field field:fields){
					String key=field.getName();
					//System.out.println("Key is :"+key);
					Method getter=claz.getDeclaredMethod("get"+Untils.FirstChar2Up(key));//getter
					Object value=getter.invoke(entry);

					if(!key.equals("name")&&value!=null){//name field has been written. 
						if(field.getType()==String[].class){//author array
							authors=(String[]) value;
							value=authors[0];
							for(int j=1;j<authors.length;j++){
								value=value+" and "+authors[j];
							}
						}
						bWriter.append(key+"={"+value+"},\n");
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
				bWriter.append("counts={fields:"+fieldNum+"; authors:"
						+ ""+authorNum+"; longest field:"+longestField+"; length:"+longestLength+"}\n}\n");
				//writing end
				bWriter.flush();
				succeedEntry++;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		}
		try {
			
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return succeedEntry;
	}
	

}
