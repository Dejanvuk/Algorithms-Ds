package com.dejanvuk.ds;

public class MyStack<E> {
    private class StackNode<T> {
        private T data;
        private StackNode<T> prev;

        public StackNode(StackNode<T> prev, T data) {
            this.data = data;
        }

    }

    private StackNode<E> top;

    private int size = 0;

    private static final long serialVersionUID = 539647068406852104L;

    MyStack() {
    }

    MyStack(StackNode<E> top) {
        this.top = top;
    }

    public void push(E value) {
        this.top = new StackNode<E>(this.top, value);
        size++;
    }

    public E pop() {
        if (isEmpty())
            throw new IllegalArgumentException("Stack is empty!");

        E top = this.top.data;
        this.top = this.top.prev;

        size--;
        return top;
    }

    public E peek() {
        return this.top.data;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public int size() {
        return this.size;
    }

}
