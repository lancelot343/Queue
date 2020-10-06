package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    Object[] values;
    int size;

    public QueueImpl() {
        values = new Object[0];
        size = 0;
    }

    @Override
    public void clear() {
        values = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return values[index++];
        }

    }

    @Override
    public void enqueue(Object element) {
        Object[] tmp = values;
        values = new Object[values.length + 1];
        System.arraycopy(
                tmp, 0, // source
                values, 0, // destination
                tmp.length // amount
        );
        values[values.length - 1] = element;
        size++;
    }

    @Override
    public Object dequeue() {
        if (size != 0) {
            Object[] tmp = values;
            values = new Object[values.length - 1];
            System.arraycopy(
                    tmp, 1, // source
                    values, 0, // destination
                    tmp.length - 1 // amount
            );
            size--;
            return tmp[0];
        } else {
            return null;
        }
    }

    @Override
    public Object top() {
        if (values.length != 0) {
            return values[0];
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        int index = 0;
        StringBuilder toReturn = new StringBuilder();
        if (this.size() >= 0) {
            toReturn.append("[");

            for (Object obj : values) {
                index++;
                if (index == this.size()) {
                    toReturn.append(obj);
                } else {
                    toReturn.append(obj);
                    toReturn.append(", ");
                }
            }
            toReturn.append("]");
        }
        return toReturn.toString();
    }

    public static void main(String[] args) {
        Queue queue = new QueueImpl();
        queue.enqueue(1);
        queue.dequeue();
        System.out.println(queue.top());
        //queue.enqueue("A");
        //queue.enqueue("B");
        //queue.enqueue("C");
        //System.out.println(queue.top());
        //System.out.println(queue.size());
        queue.forEach(System.out::print);

        queue.clear();
    }
}