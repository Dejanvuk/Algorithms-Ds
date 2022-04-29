package com.dejanvuk.ds.lists;

import java.util.Iterator;

public interface ListIterator<E> extends Iterator<E>{
	/**
	 * @return the current
	 */
	public int getCurrent();
	/**
	 * @return the previous
	 */
	public int getPrevious();
}
