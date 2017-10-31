package edu.marshall.pub.processor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import edu.marshall.pub.bean.Entry;
import edu.marshall.pub.until.OrderedArrayList;
import edu.marshall.pub.until.OrderedList;
import edu.marshall.pub.until.Untils;

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

	
	
	
	public void writeEntries(OrderedList entryList){
		Iterator<Entry> it=	entryList.iterator();
		try {
		while(it.hasNext()){
			String entryString=Untils.entryToString(it.next());
			bWriter.append(entryString);
			bWriter.flush();
		}
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
