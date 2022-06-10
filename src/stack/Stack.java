package stack;

import java.util.Iterator;

public class Stack <T> implements Iterable<T>{
    private final java.util.LinkedList <T> list = new java.util.LinkedList<T>();

    // create an empty stack
    public Stack(){
    }

    // create a stack with an initial element
    public Stack(T firstElem){
        push(firstElem);
    }

    // returns the numbers of element in stack
    public int size(){
        return list.size();
    }

    // checks if the stack is empty or not
    public boolean isEmpty(){
        return size() == 0;
    }

    // push an element on the stack
    public void push(T elem){
        list.addLast(elem);
    }

    // pop an element off the stack which throws an error if stack is empty -_- bruh
    public T pop(){
        if(isEmpty())
            throw new java.util.EmptyStackException();
        return list.removeLast();
    }

    // peek at the top element of the stack and error if stack is empty -_-
    public T peek(){
        if(isEmpty())
            throw new java.util.EmptyStackException();
        return list.peekLast();
    }

    //allows users to iterate through the stack using iterator ;)
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}