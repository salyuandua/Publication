

public class Bootstrap {
public static void main(String[] args) {
	System.out.println("Reading...");
	ReadingProcessor readingProcessor=new ReadingProcessor("E:\\work\\ProjectConformantBibFile.bib");
	Entry[] entrys=readingProcessor.read();
	System.out.println("**********************************************************************");
	System.out.println("Reading completed....");
	System.out.println("Entry numbers: "+readingProcessor.entrySize());
	System.out.println("**********************************************************************");
	try {
		for(int i=5;i>=0;i--){
			System.out.println("Writing starts in "+i+"s");
			Thread.sleep(1000);
		}
		
	} catch (InterruptedException e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Writing...");
	WritingProcessor wProcessor=new WritingProcessor("E:\\project1111111\\projectWrite.bib");
	int successedEntry=wProcessor.writeEntries(entrys, readingProcessor.entrySize());
	System.out.println("**********************************************************************");
	System.out.println("Writing completed....");
	System.out.println("Entries written: "+successedEntry);
	System.out.println("**********************************************************************");

}

}
