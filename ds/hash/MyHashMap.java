package com.dejanvuk.ds.hash;

import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K,V> {
    private LinkedList<K,V>[] list;
    private int size;

    public MyHashMap(int size) {
        this.size = size;
        list = new LinkedList[size];
    }

    public V get(K key) {
        int hash = hashCode(key);
        return list[hash].getValueAtKey(key);
    }

    public void put(K key, V value) {
        int hash = hashCode(key);

        if(list[hash] == null) {
            LinkedList<K,V> tempList = new LinkedList<>();
            tempList.insert(key, value);
            list[hash] = tempList;
        }
        else {
            list[hash].insert(key, value);
        }
    }

    private int hashCode(K key) {
        return (int) key % size;
    }

    public Set<V> values() {
        Set<V> set = new HashSet<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                LinkedList<K, V> bucket = list[i];
                // overkill for an Iterator
                LinkedList<K, V>.Node<K, V> curr = bucket.getHead();
                while(curr != null) {
                    set.add(curr.getValue());
                    curr = curr.getNext();
                }
            }
        }
        return set;
    }

    private class LinkedList<K,V>{
        private Node<K, V> head = null;

        private class Node<K, V> {
            private Node<K, V> next = null;
            private K key;
            private V value;

            Node(K key, V value, Node<K, V> next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }

            public Node<K, V> getNext() {
                return next;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }
        }

        public Node<K, V> getHead() {
            return head;
        }

        public void insert(K key, V value) {
            Node<K, V> newNode = new Node<>(key, value, null);

            if (head == null) {
                head = newNode;
            } else {
                insertLast(key, value);
            }
        }

        public void insertLast(K key, V value) {
            Node<K, V> last = head;
            if (last.key == key) {
                last.value = value;
                return;
            }
            while (last.next != null) {
                if (last.key == key) {
                    last.value = value;
                    return;
                }
                last = last.next;
            }
            Node<K, V> newNode = new Node<>(key, value, null);
            last.next = newNode;
        }

        public void printAll() {
            Node<K, V> last = head;
            while (last != null) {
                System.out.println(last.value);
                last = last.next;
            }
        }

        public void insertAt(int index, K key, V value) {
            Node<K, V> newNode = new Node<>(key, value, null);

            if (index == 0) {
                insertFirst(key, value);
            } else {
                Node<K, V> temp = head;
                for (int i = 0; i < index; i++) {
                    if (i == index - 1) {
                        newNode.next = temp.next.next;
                        temp.next = newNode;
                    } else temp = temp.next;
                }
            }
        }

        public void insertFirst(K key, V value) {
            Node<K, V> newNode = new Node<>(key, value, head);
            head = newNode;
        }

        public void delete(int index) {
            if (index == 0) {
                head = head.next;
            } else {
                Node<K, V> last = head;
                for (int i = 0; i < index; i++) {
                    if (i == index - 1) {
                        last.next = last.next.next;
                    } else last = last.next;
                }
            }
        }

        // get value at key
        public V getValueAtKey(K key) {
            Node<K, V> tempNode = head;
            while (tempNode != null) {
                if (tempNode.key.equals(key)) {
                    return tempNode.value;
                } else tempNode = tempNode.next;
            }
            return null;
        }
    }

}





