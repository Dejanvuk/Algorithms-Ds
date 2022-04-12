package com.dejanvuk.ds;

import com.dejanvuk.ds.lists.MyArrayList;
import com.dejanvuk.ds.lists.MyList;

public class Heap<E extends Comparable<E>>{
    private MyList<E> list = new MyArrayList<>();

    private boolean min = true; // MinHeap or MaxHeap

    public Heap() {
    }

    public Heap(boolean min) {
        this.min = min;
    }

    public E get(int index) {
        return list.get(index);
    }

    public Heap(boolean min, E[] objects) {
        this.min = min;
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    public void add(E e) {
        if(list.isEmpty()) {
            list.add(e);
        }
        else {
            // add it at the back
            list.add(e);
            // continue moving it up
            int currentPosition = list.size() - 1;
            if(min) { // MinHeap
                while(currentPosition > 0) {
                    int parent = (currentPosition -1) / 2;
                    if(list.get(currentPosition).compareTo(list.get(parent)) <0) {
                        // swap current with parent
                        E temp = list.get(parent);
                        list.set(parent, list.get(currentPosition));
                        list.set(currentPosition, temp);
                        currentPosition = parent;
                    }
                    else
                        break;
                }
            }
            else { //MaxHeap
                while(currentPosition > 0) {
                    int parent = (currentPosition -1) / 2;
                    if(list.get(currentPosition).compareTo(list.get(parent)) > 0) {
                        // swap current with parent
                        E temp = list.get(parent);
                        list.set(parent, list.get(currentPosition));
                        list.set(currentPosition, temp);
                        currentPosition = parent;
                    }
                    else
                        break;
                }
            }
        }
    }

    public E remove() {
        if(list.isEmpty())
            throw new IllegalArgumentException("Heap is empty!");

        E e = list.get(0);

        list.set(0, list.get(list.size() -1));
        list.remove(list.size() -1);

        // heapify root
        heapify(list, list.size(), 0);

        return e;
    }

    private void heapify(MyList<E> arr, int l, int parent) {
        int mostPriority = parent;
        int leftChild = 2*parent + 1;
        int rightChild = 2*parent + 2;

        if(min) { // MinHeap
            if(leftChild < l && arr.get(leftChild).compareTo(arr.get(mostPriority)) < 0) {
                mostPriority = leftChild;
            }
            if(rightChild < l && arr.get(rightChild).compareTo(arr.get(mostPriority)) < 0) {
                mostPriority = rightChild;
            }
        }
        else { // MaxHeap
            if(leftChild < l && arr.get(leftChild).compareTo(arr.get(mostPriority)) > 0) {
                mostPriority = leftChild;
            }
            if(rightChild < l && arr.get(rightChild).compareTo(arr.get(mostPriority)) > 0) {
                mostPriority = rightChild;
            }
        }

        if(mostPriority != parent) { // the tree is not a heap
            // swap parent with largest
            E temp = list.get(parent);
            list.set(parent, list.get(mostPriority));
            list.set(mostPriority, temp);
            //call heapify again
            heapify(arr, l, mostPriority);
        }
    }

    public int getSize() {
        return list.size();
    }
}
