package com.dejanvuk.ds;

public class MyPriorityQueue<E extends Comparable<E>> {
    private Heap<E> heap = new Heap<>();

    public void enqueue(E e) {
        heap.add(e);
    }

    public E dequeue() {
        return heap.remove();
    }

    public int size() {
       return heap.getSize();
    }
}
