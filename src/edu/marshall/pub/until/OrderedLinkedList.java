package edu.marshall.pub.until;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import edu.marshall.pub.bean.Book;
import edu.marshall.pub.bean.Entry;


public class OrderedLinkedList implements OrderedList{
private DoubleLinkedNode head;
private DoubleLinkedNode tail;
private int count;
private int orderModel=Entry.OrderByName;
	public OrderedLinkedList() {
		head=null;
		tail=null;
		count=0;
	}
	public OrderedLinkedList(int orderModel) {
		this();
		this.orderModel=orderModel;
		
	}
	/**
	 * rearrange order for 
	 */
//	public void reArrange(){
//		DoubleLinkedNode current=head;
//		while(current!=null){
//			
//		}
//	}
	
	/**
	 * get entry by index
	 * @param index
	 * @return
	 */
	public Entry get(int index) throws IndexOutOfBoundsException{
		if(index<0||index>=count){
			throw new IndexOutOfBoundsException("Index '"+index+"' is out of bounds");
		}
		DoubleLinkedNode current=head;
		int flag=0;
		while(flag!=index){
			current=current.getNext();
			flag++;
		}
		return current.getElement();
	}
	/**
	 * search entry by entry's name, return null if entry not exist
	 */
	@Override
	public Entry search(String entryName) {
		DoubleLinkedNode current=head;
		while(current!=null){
			if(current.getElement().getName().trim().equals(entryName)){
				return current.getElement();
			}
			current=current.next;
			
		}
		return null;
	}
	/**
	 * add entry by order model
	 * @param element
	 */
	public void add(Entry element) throws NullPointerException{
		
		element.setOrder(orderModel);
		//title or year not exist
//		if((element.getOrder()==Entry.OrderByTitle&&element.getTitle().trim().equals(""))
//				||(element.getOrder()==Entry.OrderByYear&&element.getYear().trim().equals("0"))){
//			element.setOrder(Entry.ignoreOrder);
//		}
//		
//		
//		
//		if(orderModel==Entry.ignoreOrder||count==0){//no order needed or empty set
//			addLast(element);
//			return;
//		}
		if(count==0){//no order needed or empty set
			addLast(element);
			return;
		}
		//add node by specific order
		DoubleLinkedNode current=head;
		

		while(current!=null){
			//equal and replace if names are same
//			if(element.compareTo(current.getElement())==0&&element.getOrder()==Entry.OrderByName){
//				current.element=element;
//				return;
//			}	
			if(element.getName().equals(current.getElement().getName())){
				current.element=element;
				return;
			}
			//current is last one
			if(current.next==null){
				if(element.compareTo(current.getElement())>0){
					addLast(element);
				}else{
					addFirst(element);
				}
				return;
			}
			//element should be previous of head
			if(current.previous==null&&element.compareTo(current.element)<0){
				addFirst(element);
				return;
			}
			

			if((element.compareTo(current.element)>=0)&&(element.compareTo(current.next.element)<0)){
				//insert new node between current and current's next
				DoubleLinkedNode newNode=new DoubleLinkedNode(element, current, current.next);
				
				current.next.previous=newNode;
				current.next=newNode;
				count++;
				return;
			}
			current=current.getNext();

		}
		throw new NullPointerException("Add failed: can not find right position for element expected to insert "+element.getName());
		
	}
	/**
	 * add element into index position
	 * @param element
	 * @return
	 */
	public void add(int index,Entry element) throws IndexOutOfBoundsException{
		if(index<0||index>=count){
			throw new IndexOutOfBoundsException("Index '"+index+"' is out of bounds");
		}
		DoubleLinkedNode current=head;
		int flag=0;
		while(flag!=index){
			current=current.getNext();
		}
		//set element to current's parent	
		DoubleLinkedNode newNode=new DoubleLinkedNode(element, current.getPrevious(), current);
		//set previous's next to new node; 
		current.getPrevious().setNext(newNode);
		//set current's previous to new node; 
		current.setPrevious(newNode);
		count++;
	}
	
	public boolean contains(Entry element) {
		DoubleLinkedNode currentNode=head;
		while(currentNode!=null){
			if(currentNode.getElement().equals(element)){
				return true;
			}
			currentNode=currentNode.getNext();
		}
		return false;
	}


	public boolean isEmpty() {
		
		return count==0;
	}
	/**
	 * remove entry by entry's name, if entry not exists, return null;
	 */
	@Override
	public Entry remove(String entryName) {
		DoubleLinkedNode currentNode=head;
		while(currentNode!=null){
			if(currentNode.getElement().getName().trim().equals(entryName)){
				Entry e=currentNode.getElement();
				if(currentNode.previous==null){
					return removeFirst();
				}else if(currentNode.next==null){
					return removeLast();
				}else{
					currentNode.previous.next=currentNode.next;
					currentNode.next.previous=currentNode.previous;
					count--;
					return e;
				}
				
			}
			currentNode=currentNode.getNext();
		}
		return null;
	}
	public Entry remove(int index) throws IndexOutOfBoundsException{
		if(index<0||index>=count){
			throw new IndexOutOfBoundsException();
		}
		if(count==0){
			
		}
		DoubleLinkedNode currentNode=head;
		Entry element=null;
		int currentIndex=0;
		while(currentNode!=null){
			if(currentIndex==index){
				if(currentNode.getPrevious()==null){
					return removeFirst();
				}else if(currentNode.getNext()==null){
					return removeLast();
				}else{
					 element=currentNode.getElement();
					DoubleLinkedNode pre=currentNode.getPrevious();
					pre.setNext(currentNode.getNext());
					count--;
					return element;
				}
				
			}
			currentIndex++;
			currentNode=currentNode.getNext();
			
		}
		count--;
		return element;
				
	}
	


//	@Override
//	public boolean equals(SetADEntry<Entry> set) {
//		if(count!=set.size()){
//			return false;
//		}
//		DoubleLinkedNode<Entry> currentNode=head;
//		while(currentNode!=null){
//			if(!set.contains(currentNode.getElement())){
//				return false;
//			}
//			currentNode=currentNode.getNext();
//		}
//		
//		
//		return true;
//	}

	public Entry[] getElements() {
		Object[] elements=new Object[count];
		DoubleLinkedNode doubleLinkedNode=head;
		int currentCount=0;
		while(doubleLinkedNode!=null){
			elements[currentCount]=doubleLinkedNode;
			doubleLinkedNode=doubleLinkedNode.getNext();
			currentCount++;
		}
		
		return (Entry[])elements;
	}

	public Iterator<Entry> iterator() {
		return new DoubleLinkedSetIterator();
	}

//	@Override
//	public void addAll(SetADEntry<Entry> set) {
//		Iterator<Entry> it=set.iterator();
//		while(it.hasNext()){
//			Entry element=it.next();
//			this.addLast(element);
//		}
//	}



	public void addFirst(Entry element) {
		//System.out.println("add First"+element);
		if(contains(element)){
			return;
		}
		
		DoubleLinkedNode newNode=new DoubleLinkedNode(element);
		if(count==0){
			tail=newNode;
			//head=newNode;
		}else{
			newNode.setNext(head);
			head.setPrevious(newNode);
			//head=newNode;
		}
		head=newNode;
		count++;
	}


	public void addLast(Entry element) {
		
		DoubleLinkedNode newNode=new DoubleLinkedNode(element);
		if(count==0){
			head=newNode;
		}else{
			newNode.setPrevious(tail);
			tail.setNext(newNode);
		}
		tail=newNode;
		count++;
		
	}


	public Entry removeFirst() {
		if(count==0){
			//throw new EmptySetException();
		}
		Entry element=head.getElement();
		if(head.getNext()==null){
			head=null;
			tail=null;
		}else{
			head=head.getNext();
			head.setPrevious(null);
		}
		count--;
		return element;
		
	}


	public Entry removeLast() { 
//		if(count==0){
//			throw new EmptySetException();
//		}
		Entry element=tail.getElement();
		if(tail.getPrevious()==null){
			head=null;
			tail=null;
		}else{
			tail=tail.getPrevious();
			tail.setNext(null);
		}
		count--;
		return element;
	}


	private class DoubleLinkedSetIterator implements Iterator<Entry>{
		private DoubleLinkedNode currentNode=head;
		@Override
		public boolean hasNext() {
			
			return currentNode!=null;
		}

		@Override
		public Entry next() throws NoSuchElementException{
			if(currentNode==null){
				throw new NoSuchElementException();
			}
			Entry element=currentNode.getElement();
			currentNode=currentNode.getNext();
			return element;
		}
		
	}

	public class DoubleLinkedNode {
		private Entry element;
		private DoubleLinkedNode previous;
		private DoubleLinkedNode next;
			public DoubleLinkedNode(Entry element) {
				this.element=element;
				setPrevious(null);
				setNext(null);
			}
			public DoubleLinkedNode(Entry element,DoubleLinkedNode previous,DoubleLinkedNode next) {
				this.element=element;
				this.previous=previous;
				this.next=next;
			}
			public Entry getElement() {
				return element;
			}
			public void setElement(Entry element) {
				this.element = element;
			}
			public DoubleLinkedNode getPrevious() {
				return previous;
			}
			public void setPrevious(DoubleLinkedNode previous) {
				this.previous = previous;
			}
			public DoubleLinkedNode getNext() {
				return next;
			}
			public void setNext(DoubleLinkedNode next) {
				this.next = next;
			}
			
		}


	
//	public void add(int index,Entry element) throws IndexOutOfBoundsException{
//		DoubleLinkedNode<Entry> newNode=new DoubleLinkedNode<Entry>(element);
//		if(count==0){
//			if(index!=0){
//				throw new IndexOutOfBoundsException();
//			}else{
//				addFirst(element);
//			}
//		}
//		if(index<0||index>count){
//			throw new IndexOutOfBoundsException();
//		}
//
//		DoubleLinkedNode<Entry> currentNode=head;
//		
//		int currentIndex=0;
//		while(currentNode!=null){
//			if(currentIndex==index){
//				if(currentNode.getPrevious()==null){
//					addFirst(element);
//				}else if(currentNode.getNext()==null){
//					addLast(element);
//				}else{
//					DoubleLinkedNode<Entry> pres=currentNode.getPrevious();
//					
//					pres.setNext(newNode);
//					newNode.setPrevious(pres);
//					newNode.setNext(currentNode);
//					currentNode.setPrevious(newNode);
//				}
//				
//			}
//			currentIndex++;
//			currentNode=currentNode.getNext();
//			
//		}
//		count++;
//		
//	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return count;
	}
	public String toString(){
		StringBuilder str=new StringBuilder();
		DoubleLinkedNode current=head;
		while(current!=null){
			str.append(current.getElement().getName()+"  ");
			current=current.next;
		}
		return str.toString();
	}
	public static void main(String[] args) {
		Book book1=new Book("book1");
		book1.setYear("100");
		//book1.setOrder(2);
		Book book2=new Book("book2");
		book2.setYear("200");
		Book book3=new Book("book3");
		//book3.setYear("4000");
		//book3.setOrder(2);
		Book book4=new Book("book4");
		book4.setYear("14000");
		//book4.setOrder(2);
		//System.out.println(book3.compareTo(book1)>0);
		OrderedLinkedList l=new OrderedLinkedList(Entry.OrderByYear);
		l.add(book4);
		l.add(book1);
		l.add(book3);
		l.add(book2);
		System.out.println(l.toString());
//		System.out.println(l.search("book9"));
//		System.out.println(l.remove("book9"));
//		System.out.println(l.toString());
		
	}




}
