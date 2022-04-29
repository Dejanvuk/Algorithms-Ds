package com.dejanvuk.ds.lists;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;


public class MyLinkedList<E> implements MyList<E>{
	
	private static final long serialVersionUID = 539041068406842104L;

	private Node<E> head = null;
	
	private Node<E> tail = null;
	
	private int size = 0;

	private class Node<E> {
		private Node<E> next = null;
		private E value;

		Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}
	}
	
	/**
	 * Returns the element at the specified position in this linked list
	 */
	@Override
	public E get(int index) {
		validateIndex(index);
		return getNode(index).value;
	}
	
	/**
	 * Returns the node at the specified position in this linked list
	 */
	private Node<E> getNode(int index) {
		validateIndex(index);
		Node<E> n = head;
		for(int i = 0; i < index; i++) {
			n = n.next;
		}
		return n;
	}

	/**
	 * Appends the specified element to the end of this array list
	 */
	@Override
	public boolean add(E e) {
		if(e != null) {
			addAtTail(e);
			return true;
		}
		return false;
	}
	
	private void addAtTail(E e) {
		Node<E> n = new Node<E>(e, null);
		Node<E> prevTail = tail;
		tail = n;
		if (head == null) {
			head = n;
		} else {
			prevTail.next = n;
		}
		size++;
	}
	
	private void addAtHead(E e) {
		Node<E> n = new Node<E>(e, head);
		head = n;
		size++;
	}
	
	/**
	 * Inserts the specified element at the specified position in this linked list
	 */
	@Override
	public void add(int index, E e) {
		validateIndex(index);
		if(index == size) {
			addAtTail(e);
		}
		else if (index == 0) {
			addAtHead(e);
		}
		else {
			Node<E> prevNode = getNode(index-1);
			Node<E> nextNode = prevNode.next;
			Node<E> newNode = new Node<E>(e, nextNode);
			prevNode.next = newNode;
			size++;
		}
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this array list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if(c != null) {
			E[] a = (E[]) c.toArray();
			int lengthA = a.length;
			if(lengthA != 0) {
				for(E e : a) {
					add(e);
				}
				return true;
			}
			else return false;
		}
		else throw new NullPointerException();

		return false;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Returns true if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	/**
	 * Returns true if this array list contains all of the elements in the specified collection
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		if(c != null) {
			Object[] a = c.toArray();
			
			for(Object o : a) {
				if(!contains(o)) return false;
			}
		}
		else throw new NullPointerException();

		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from this array list, if it is present
	 */
	@Override
	public boolean remove(Object o) {
		if(o != null) {
			return remove(indexOf(o));
		}
		else throw new NullPointerException();
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
		else throw new NullPointerException();

		return true;
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
		else throw new NullPointerException();
		
		return true;
	}

	/**
	 * 
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an array containing all of the elements in this array list 
	 */
	@Override
	public Object[] toArray() {
		Object[] a = new Object[size];
		
		Node<E> curr = head;
		
		for(int i = 0; curr != null; i++) {
			a[i] = curr.value;
			curr = curr.next;
		}
		
		return a;
	}

	/**
	 * Store the array lists elements into the given array if it's big enough
	 * If the given array is not big enough returns an array containing all of the elements in this list as type T 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] t) {
		
		if(t.length > size) { // make a new array
			t = (T[]) Array.newInstance(t.getClass().getComponentType(), size);
		}
		
		Node<E> curr = head;
		
		for(int i = 0; curr != null; i++) {
			t[i] = (T) t.getClass().cast(curr.value);
			curr = curr.next;
		}
		
		return t;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this array list, or -1 if this array list does not contain the element
	 */
	@Override
	public int indexOf(Object o) {
		int index = -1;
		
		if(o != null) {
			ListIterator<E> itr = listIterator();
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
	 * Returns the index of the last occurrence of the specified element in this array list, or -1 if this array list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object o) {
		int index = -1;
		
		if(o != null) {
			Node<E> curr = head;
			for(int i = 0; curr != null; i++) {
				if(curr.value.equals(o)) 
					index = i;				
				curr = curr.next;
			}
			return index;
		}
		
		return index;
	}

	/**
	 * Removes the element at the specified position in this array list
	 */
	@Override
	public E remove(int index) {
		validateIndex(index);
		E e;
		if(index == 0) {
			e = head.value;
			head = head.next;
		}
		else {
			Node<E> prev = getNode(index - 1);
			e = prev.next.value;
			prev.next = prev.next.next;
		}
		
		size--;
		return e;
	}

	/**
	 * Replaces the element at the specified position in this array list with the specified element
	 * @return old value of e
	 */
	@Override
	public E set(int index, E e) {
		validateIndex(index);
		Node<E> n = getNode(index);
		E oldValue = n.value;
		n.value = e;
		return oldValue;
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
		
		int current = 0;
		Node<E> currentNode;
		Node<E> previousNode;
		
		public CustomIterator(int current) {
			super();
			this.current = current;
			this.currentNode = getNode(current);
		}

		@Override
		public boolean hasNext() {
			return current < MyLinkedList.this.size;
		}

		@Override
		public E next() {
			E e = currentNode.value;
			previousNode = currentNode;
			currentNode = currentNode.next;
			current++;
			return e;
		}

		@Override
		public int getCurrent() {
			return current;
		}

		@Override
		public int getPrevious() {
			return current - 1;
		}
		
		public Node<E> getCurrentNode() {
			return currentNode;
		}

		
		public Node<E> getPreviousNode() {
			return previousNode;
		}
		
	}
	
	private void validateIndex(int index) {
        if (index < 0 || index > size )
            throw new IndexOutOfBoundsException(index + " is out of bounds!");
    }
}
