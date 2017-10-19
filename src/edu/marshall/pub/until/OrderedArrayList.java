package edu.marshall.pub.until;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.marshall.pub.bean.Entry;

public class OrderedArrayList {
	protected Entry[] varlables;
	protected int count;
	public OrderedArrayList(int initCapicty) {
		varlables=new Entry[initCapicty]; 
		count=0;
	}
	public OrderedArrayList(){
		
		this(100);
	}

	
	protected void doubleArray() {
		// EntryODO Auto-generated method stub
		Entry[] temp = (Entry[]) new Entry[2 * varlables.length];  //BIG-O:1

		for (int i = 0; i < varlables.length; i++) {  //BIG-O:n
			
			temp[i] = varlables[i];                   //BIG-O:1
		}
		varlables = temp;                           //BIG-O:1
		//Entryotal = 1+1*n+1 = 2+ n = O(n)
	}
	//shrink array to half length
	protected void shrinkArray(){
		Entry[] newArray=(Entry[]) new Entry[varlables.length/2];
		for(int i=0;i<count;i++){
			newArray[i]=varlables[i];
		}
		varlables=newArray;
	}
	
	public int size() {   //O(1)
		return count;
	}
	public boolean isEmpty() {   //O(1)
		// EntryODO Auto-generated method stub

		return count == 0;
	}
	public Iterator<Entry> iterator() {

		return new OrderedArrayListIterator();
	}
	public boolean contains(Entry element) {
		
		return (search(element) != -1);
	}
	public Entry search(String entryName){
		Entry tempEntry=new Entry(entryName);
		int index=search(tempEntry);
		if(index<0){
			return null;
		}else{
			return varlables[index];
		}
	}
	
	public int search(Entry element){
		return bSearch(element, 0, count-1); 
		
				
	}
	//b search 
	private int bSearch(Entry element,int low,int high){
		if(low>high){
			return -1;
		}
		int mid=low+(high-low)/2;
		
		if(varlables[mid].compareTo(element)==0){//exist
			return mid;
		}else if(varlables[mid].compareTo(element)>0){//var[mid]>element
			return bSearch(element,low,mid-1);
		}else{//var[mid]<element
			return bSearch(element, mid+1, high);
		}
	}
	//add an element orderly
	//ArrayList<E>
	public void add(Entry element) {
		if(count==varlables.length){
			doubleArray();
		}
		if(count==0||element.compareTo(varlables[count-1])>=0){//element is biggest
			varlables[count++]=element;
		}else if(element.compareTo(varlables[0])<=0){//element is small
			for(int i=count-1;i>=0;i--){
				varlables[i+1]=varlables[i];
			}
			varlables[0]=element;
			count++;
		}else{
			//System.out.println(element);
			for(int i=0;i<=count-1;i++){
				if(element.compareTo(varlables[i])>0&&element.compareTo(varlables[i+1])<=0){
					System.out.println(element);
					for(int j=count-1;j>=i+1;j--){
						varlables[j+1]=varlables[j];
						
					}
					varlables[i+1]=element;
					count++;
					return;
				}
			}
		}
	}
	public Entry remove(String entryName) throws NoSuchElementException {
			
			int i=search(new Entry(entryName));
			Entry tempEntry=varlables[i];
			if(i<0){//not exist
				throw new NoSuchElementException("Entryhe element (name is: "+entryName+") required to deleted not exists");
			}
				if(i==count-1){
					
					varlables[i]=null;
				}else{
					for(int j=i;j<count-1;j++){//change position 
						varlables[j]=varlables[j+1];
					}
				}
				count--;
				double capcityRatio=(double)count/varlables.length;
				if(capcityRatio<=0.25){
					shrinkArray();
				}
				
				return tempEntry;
	}
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");    //add value after str
		
		for(int i = 0; i< count;i++)
		{
			str.append(varlables[i]);
			if(i == count - 1)
			{
				break;
			}
			str.append(", ");
		}
		
		//[1, 9, 5, 2, 7, 8]
		str.append("]");
		
		return str.toString();
	}
	private class OrderedArrayListIterator implements Iterator<Entry>{
		private int position;
		@Override
		public boolean hasNext() {
			
			if(position < count) //mean already have element
				return true;
				
			return false;
		}
		@Override
		public Entry next() {
			
			
			position++;
				
			return varlables[position - 1];
		}
		
		
	}
	
}
