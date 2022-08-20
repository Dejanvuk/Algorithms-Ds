package com.dejanvuk.ds;

import com.dejanvuk.ds.lists.MyArrayList;
import com.dejanvuk.ds.lists.MyList;

/*
Using an Array List
* */
public class MinHeap<T> {
    private T[] heap;
    private int currentSize = 0;
    private int maxSize = 15; // default

    public MinHeap() {
        heap = new int[maxSize];
    }

    /**
     * ***************
     * Helper methods
     * ***************
     */
    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private int getLeft(int i) {
        return 2 * i + 1;
    }

    private int getRigh(int i) {
        return 2 * i + 2;
    }

    private void swap(int to, int from) {
        T temp = heap[to];
        heap[to] = heap[from];
        heap[from] = temp;
    }

    @Override
    public String toString() {
        StringBuilder values = new StringBuilders();

        for(T val : heap) {
            values.append(val); // T must implement toString aswell
        }

        return values.toString();
    }

    /**
     * Unformatted, prints the whole array heap
     */
    public void printAll() {
        for(T val : heap)
            System.out.println(val);
    }

    /**
     * Formatted print
     */
    public void print() {
        for (int i = 0; i < size / 2; i++)
            System.out.println("parent: " + heap[i] + "left child: " + heap[getLeft(i)] + "right child: " + heap[getRigh(i)]);
    }

    /**
     * ***************
     * /Helper methods
     * ***************
     */

    /**
     * Appends the new element to the heap
     *
     * @param  e is the new element to be added
     * @throws NullPointerException if the element is null
     */
    public void add(T e) {
        if(e == null)
            throw new NullPointerException("Cannot add empty element!");

        if(currentSize == (maxSize - 1)) {
            maxSize *= 2; // double the currentSize
            T[] newHeap = new int[maxSize];
            System.arraycopy(heap, 0, newHeap, 0, currentSize);
            heap = newHeap;
        }

        heap[currentSize] = e;

        int i = currentSize;
        // TODO: add custom comparator
        while(i != 0 && heap[getParent(i)] > heap[i]) {
            swap(getParent(i), i);
            i = getParent(i);
        }

        currentSize++;
    }

    /**
     * Removes the root element and returns it
     *
     * @throws IllegalArgumentException if the heap is empty
     *
     * @return the root element
     */
    public T remove() {
        if(currentSize == 0)
            throw new IllegalArgumentException("Cannot remove from empty heap!");

        // 1st: remove the root element from the heap and replace it with the last element
        T popped = heap[0];
        heap[0] = heap[--currentSize];

        // 2nd: Then, we bubble down this element, swapping it with one of its children until the min heap property is restored

        heapify(0);

        return popped;
    }

    /**
     * heapifies the root to restore the min heap property
     * @param parentIndex is start point
     */
    public void heapify(int parentIndex) {
        int smallestIndex = parentIndex;
        int leftChildIndex = getLeft(parentIndex);
        int rightChildIndex = getRigh(parentIndex);

        // TODO: Add custom comparator
        if(leftChildIndex < currentSize && heap[leftChildIndex] < heap[smallestIndex]) {
            smallestIndex = leftChildIndex;
        }

        if(rightChildIndex < currentSize && heap[rightChildIndex] < heap[smallestIndex]) {
            smallestIndex = rightChildIndex;
        }

        if(smallestIndex != parentIndex) { // also takes care of base cases
            swap(parentIndex, smallestIndex); // swap the parent with the smaller child
            heapify(smallestIndex); // heapify next child
        }
    }
}