package edu.marshall.pub.bootstrap;

import java.io.File;
import java.util.Scanner;

import edu.marshall.pub.bean.Entry;
import edu.marshall.pub.processor.ReadingProcessor;
import edu.marshall.pub.processor.WritingProcessor;
import edu.marshall.pub.until.OrderedArrayList;
import edu.marshall.pub.until.OrderedLinkedList;
import edu.marshall.pub.until.OrderedList;
import edu.marshall.pub.until.Untils;

public class Bootstrap {
private static String filePath;	
private static Scanner sc;	
private static void checkFilePath(){
	while(true){
		System.out.println("Please enter the path of file:");
		filePath=sc.next();
		File f=new File(filePath);
		if(!f.exists()){
			System.out.println("The file '"+filePath+"' not exists, please try again");
			
		}else{
			return;
		}
	}
}
public static void main(String[] args) {
	sc=new Scanner(System.in);
	checkFilePath();
	//confirm order model
	int orderModel=0;
	while(true){
		System.out.println("Please select the order you like:\n1. Name Order\n2. year Order\n3. Title Order");
		orderModel=sc.nextInt();
		if(orderModel>3||orderModel<1){//unsupported operation
			System.out.println("Unsupported operation, please enter again");
		}else{//right operation
			orderModel--;
			break;
		}
	}
			
			
			// read file with order model
			System.out.println("Reading '"+filePath+"'...");
			ReadingProcessor readingProcessor=new ReadingProcessor(new OrderedLinkedList(orderModel));
			OrderedList entryList=readingProcessor.read(filePath);
			System.out.println("**********************************************************************");
			System.out.println("Reading completed....");
			System.out.println("Entry numbers: "+entryList.size());
			System.out.println("**********************************************************************");
			while(true){
				System.out.println("Avaliable operations:\nS. Search\nD. Delete\nW. Print\nM. Merge\nP. Purge\nE. Exit");
				String operation=sc.next();
				if(operation.equals("S")){//search
					System.out.println("Enter name:");
					String entryName=sc.next();
					Entry entry=entryList.search(entryName);
					if(entry==null){//NOT FOOUND
						System.out.println("The entry you search not found");
						continue;
					}
					//found
					System.out.println(entry.getName());
					System.out.println(Untils.entryToString(entry));
					
				}else if(operation.equals("D")){//delete
					System.out.println("Enter name:");
					String entryName=sc.next();
					Entry entry=entryList.search(entryName);
					if(entry==null){//NOT FOOUND
						System.out.println("The entry you delete not found");
						continue;
					}
					System.out.println(Untils.entryToString(entry));
					System.out.println("Are you sure delete '"+entryName+"'? Y/N");
					String confirm=sc.next();
					if(confirm.equals("Y")){
						Entry e=entryList.remove(entryName);
						if(e!=null){
							System.out.println("Delete successful!");
						}else{
							System.out.println("Delete failed!");
						}
					}
				}else if(operation.equals("W")){//print
					System.out.println("Pleas enter file path:");
					String OutPutFile=sc.next();
					File f=new File(OutPutFile);
					if(!f.exists()){
						System.out.println("The file '"+OutPutFile+"' not exist.");
						continue;
					}
					System.out.println("Are you sure overwrite '"+OutPutFile+"' ? Y/N");
					String confirm=sc.next();
					if(confirm.equals("Y")){//write
						System.out.println("Writing...");
						WritingProcessor writingProcessor=new WritingProcessor(OutPutFile);
						writingProcessor.writeEntries(entryList);
						System.out.println("Writing end");
					}
				}else if(operation.equals("M")){//merge	
					checkFilePath();
					entryList=readingProcessor.read(filePath);
					System.out.println("Merge successfully!");
				}else if(operation.equals("P")){//purge
					
					
					
				}else if(operation.equals("E")){//exit
					System.out.println("Bye");
					break;
				}else{
					System.out.println("Unsupported operation.");
				}
			}
	}


	
//	Entry[] entrys=readingProcessor.read();

//	try {
//		for(int i=5;i>=0;i--){
//			System.out.println("Writing starts in "+i+"s");
//			Thread.sleep(1000);
//		}
//		
//	} catch (InterruptedException e) {
//	// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	System.out.println("Writing...");
//	WritingProcessor wProcessor=new WritingProcessor("E:\\project1111111\\projectWrite.bib");
//	int successedEntry=wProcessor.writeEntries(entrys, readingProcessor.entrySize());
//	System.out.println("**********************************************************************");
//	System.out.println("Writing completed....");
//	System.out.println("Entries written: "+successedEntry);
//	System.out.println("**********************************************************************");



}
