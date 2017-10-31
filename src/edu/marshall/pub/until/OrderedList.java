package edu.marshall.pub.until;

import java.util.Iterator;

import edu.marshall.pub.bean.Entry;

public interface OrderedList {
	public int size();
	public Entry get(int index) throws IndexOutOfBoundsException;
	public void add(Entry element) throws NullPointerException;
	public boolean contains(Entry element);
	public boolean isEmpty();
	public Entry search(String entryName);
	public Entry remove(String entryName);
	public Iterator<Entry> iterator();
}
