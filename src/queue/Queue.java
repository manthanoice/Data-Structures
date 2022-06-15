package queue;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue <T> implements Iterable<T> {

    private final java.util.LinkedList <T> list = new LinkedList<T>();
    public Queue() {}
    public Queue(T firstElem){
        offer(firstElem);
    }

    // return the size of the Queue
    public int size(){
        return list.size();
    }

    // return if the queue is empty or not
    public boolean isEmpty(){
        return size()==0;
    }

    // peek the element in front of the queue and the method throws an error is Queue is Empty -_-
    public T peek(){
        if(isEmpty())
            throw new RuntimeException("Queue is Empty");
        return list.peekFirst();
    }

    // poll an element from the front of the queue and the method throws an error if ...
    public T poll(){
        if(isEmpty())
            throw new RuntimeException("Queue is Empty");
        return list.removeFirst();
    }

    // add an element at the back of the queue
    public void offer(T elem){
        list.addLast(elem);
    }

    // return an iterator to allow users to traverse through the elements inside the queue!
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
