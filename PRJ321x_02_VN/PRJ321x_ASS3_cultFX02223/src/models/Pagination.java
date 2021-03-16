package models;

import java.util.List;

public class Pagination<E> {
	// list items
	public final List<E> items;
	// number current page
	public final int current;
	// count items per size
	public final int size;
	// count of rows
	private final int count;
	
	public List<E> getItems(){
		return items;
	}
	public int getCurrent() {
		return current;
	}
	public int getSie() {
		return size;
	}
	// get count of page
	public int getCountPage() {
		return (int) Math.ceil((double)count/size);
	}
	
	public Pagination(List<E> items, int size, int current, int count){
		this.items = items;
		this.size = size;
		this.current = current;
		this.count = count;
	}
	
	public int getCountItem() {
		return items.size();
	}
}
