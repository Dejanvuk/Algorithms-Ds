package com.dejanvuk.ds;

public class MyQueue<E> {
    public class MyQueueNode<T> {
        T data;
        MyQueueNode<T> next;

        MyQueueNode(T data){
            this.data = data;
        }
    }

    private MyQueueNode<E> first;
    private MyQueueNode<E> last;
    private int size = 0;

    public void add(E data) {
        MyQueueNode<E> t = new MyQueueNode(data);
        if(last != null) {
            last.next = t;
        }
        last = t;
        if(first == null) {
            first = t;
        }
        size++;
    }

    public boolean isEmpty() {
        return size != 0;
    }

    public int size() {
        return size;
    }

    public E remove() {
        if(first == null)
            throw new IllegalArgumentException();
        E data = first.data;
        first = first.next;
        if(first == null)
            last = null;
        size--;
        return data;
    }

    public E peek() {
        if(first == null)
            throw new IllegalArgumentException();
        return first.data;
    }
}

