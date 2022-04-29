package com.dejanvuk.ds.lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements MyList<E> {
	
	public static final int INITIAL_CAPACITY = 20;
	
	/**
	 * Current size of the array list
	 */
	private int size;
	
	// private E[] data 
	private Object[] data; // perform unchecked casts only when returning elements
	
	private static final long serialVersionUID = -7590071206715605804L;

	
	/**
	 * Creates an empty array list
	 */
	public MyArrayList() {
		data = new Object[INITIAL_CAPACITY];
	}
	
	
	/**
	 * Creates a new array list with the same elements as its argument
	 */
	public MyArrayList(Collection<? extends E> c) {
		Object[] arr = c.toArray();
		data = arr;
		size = arr.length;
	}
	
	/**
	 * Doubles the list capacity and then expands it to hold extra elements
	 */
	private void increaseCapacity(int extra) {
		int newSize = (size*2) + extra;
		Object[] newData = Arrays.copyOf(data, newSize, Object[].class);
		data = newData;
	}
	
	
	/**
	 * Returns an array containing all the objects in this array list
	 */
	@Override
	public Object[] toArray() {
		if(size == 0) 
			return new Object[0];
		else 
			return Arrays.copyOf(data, size, Object[].class);
	}
	
	/**
	 * Populate the given array with all of the objects in this array list as T type
	 * or if the size of the given array is to short return an array with all of the
	 * objects in this array list as T type
	 * 
	 * @throws ArrayStoreException if an element in this array list could not be stored into the given array because of a type mismatch during runtime
	 * @throws NullPointerException − if this array list or the given array are null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		
		if(size <= a.length) { 
			// Simply copy the objects to the given array
			System.arraycopy(data, 0, a, 0, size);
			return a;
		}
			// return a copy of this array lists objects
		else 
			return (T[]) Arrays.copyOf(data, size, a.getClass()); 
	}


	/**
	 * Removes all of the elements from this collection while keeping the size
	 */
	@Override
	public void clear() {
		Object[] clearedData = new Object[size];
		
		data = clearedData;
	}
	
	/**
	 * Returns the number of elements in this array list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Appends the specific object at the specified position
	 */
	@Override
	public void add(int index, E e) {
		if(e != null) {
			validateIndex(index);
			if(size == data.length) {
				increaseCapacity(1);
				shiftDataRight(index);
				data[index] = e;
			}
			else {
				shiftDataRight(index);
				data[index] = e;
			}
			size += 1;
		}
	}
	
	/**
	 * Shifts all the elements of the array to the right by 1 starting with index
	 */
	public void shiftDataRight(int index) {
		Object currObject = data[index];
		for(int i = index;i < data.length && data[i] != null; i++) {
			Object tempObject = data[i+1];
			data[i+1] = currObject;
			currObject = tempObject;
		}
	}
	
	/**
	 * Shifts all the elements of the array to the left by 1 starting from the end up until the index
	 */
	public void shiftDataLeft(int index) {
		Object currObject = data[size-1];
		for(int i = size-1; i > index; i--) {
			Object tempObject = data[i-1];
			data[i-1] = currObject;
			currObject = tempObject;
		}
	}
	
	/**
	 * Appends the specified element to the end of this list
	 */
	@Override
	public boolean add(E e) {
		if(e != null) {
			if(size == data.length) {
				int prevSize = size;
				increaseCapacity(1);
				data[prevSize] = e;
			}
			else {
				data[size] = e;
			}
			size += 1;
			return true;
		}
		else return false;
	}
	
	/**
	 * Adds all of the elements in the specified collection to this array list
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if(c != null) {
			Object[] arr = c.toArray();
			if(arr.length + size > data.length) {
				increaseCapacity(arr.length);
			}
			System.arraycopy(arr, 0, data, size, arr.length);
			size += arr.length;
			
			return true;
		}
		
		return false;
	}

	/**
	 * Returns the element at the specified position in this array list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		validateIndex(index);
		return (E) data[index];
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element in this array list
	 * or -1 if this array list does not contain the element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object o) {
		int index = -1;
		/*
		if(o != null) {
			for(int i = 0; i < data.length; i++) {
				if(o.equals(data[i])) {
					index = i;
					break;
				}
			}
		}
		return index;
		*/
		ListIterator<E> itr = listIterator();
		if(o != null) {
			while(itr.hasNext()) {
				E e = itr.next();
				if(e.equals(o)) {
					index = itr.getPrevious(); 
					break;
				}
			}
		}
		
		return index;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this array list
	 * or -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object o) {
		int index = -1;
		if(o != null) {
			for(int i = data.length - 1; i >= 0; i--) {
				if(o.equals(data[i])) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	/**
	 * Replaces the element at the specified position in this array list with the specified element
	 * returns the replaced element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E e) {
		validateIndex(index);
		E prevElement = (E) data[index];
		data[index] = e;
		return prevElement;
	}

	/**
	 * Returns true if this array list contains all of the elements in the specified collection.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean containsAll(Collection<?> c) {
		if(c != null) {
			Iterator<E> itr = (Iterator<E>) c.iterator();
			while(itr.hasNext()) {
				if(!contains(itr.next())) return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	/**
	 * Retains only the elements in this array list that are contained in the specified collection 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c) {
		if(c != null) {
			Iterator<E> itr =  (Iterator<E>) c.iterator();
			while(itr.hasNext()) {
				E e = itr.next();
				if(!contains(e)) remove(e);
			}
		}
		
		return true;
	}
	
	/**
	 * Removes a single instance of the specified element from this array list, if it is present
	 */
	@Override
	public boolean remove(Object o) {
		remove(indexOf(o));
		return true;
	}
	
	@Override
	public E remove(int index) {
		validateIndex(index);
		E e = get(index);
		shiftDataLeft(index);
		size--;
		return e;
	}
	

	/**
	 * Removes all of this array lists elements that are also contained in the specified collection
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> c) {
		if(c != null) {
			Iterator<E> itr = (Iterator<E>) c.iterator();
			while(itr.hasNext()) {
				E e = itr.next();
				if(contains(e)) remove(e);
			}
		}
		
		return true;
	}
	
	public ListIterator<E> listIterator() {
        return listIterator(0);
    }
	
	public ListIterator<E> listIterator(int index) {
        return new CustomIterator(index);
    }
	
	@Override
	public Iterator<E> iterator() {
	    return new CustomIterator(0);
	}
	
	public Iterator<E> iterator(int index) {
	    return new CustomIterator(index);
	}
	
	private class CustomIterator implements ListIterator<E>, Iterator<E> {
		private int current = 0;
		private int previous = -1;
		
		/**
		 * @return the current
		 */
		@Override
		public int getCurrent() {
			return current;
		}
		/**
		 * @return the previous
		 */
		@Override
		public int getPrevious() {
			return previous;
		}

		public CustomIterator(int current) {
			super();
			this.current = current;
		}

		@Override
		public boolean hasNext() {
			return current < MyArrayList.this.size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (!hasNext()) {
                throw new NoSuchElementException();
            }
			previous = current;
            return (E) MyArrayList.this.data[current++];
		}

		@Override
		public void remove() {
			if (previous != -1) {
            	MyArrayList.this.remove(previous);
            }
		} 
	}

	/**
	 * only store objects that are of equal type or a subclass
	 * NullPointerException - if the0 parameter is null.
	 
	private void validateElement(Object o) {
		// use isInstance to avoid throwing errors
		if(!data.getClass().getComponentType().isAssignableFrom(o.getClass())) {
			throw new AssertionError("Cannot cast to " + o.getClass());
		}
	}
	*/
	
	private void validateIndex(int index) {
        if (index < 0 || index > size )
            throw new IndexOutOfBoundsException(index + " is out of bounds!");
    }
}
