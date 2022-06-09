package doublyLinkedList;

import java.util.Iterator;

public class DoublyLinkedList <T> implements Iterable<T>{

    private int size = 0;
    private Node <T> head = null;
    private Node <T> tail = null;
    private static class Node <T>{
        T data;
        Node <T> prev, next;
        public Node(T data, Node <T> prev, Node <T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        @Override public String toString(){
            return data.toString();
        }
    }
    // Empty the linked list, O(n)
    public void clear(){
        Node <T> trav = head;
        while(trav!=null){
            Node <T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    //return the size of the linked list
    public int size(){
        return size;
    }

    //return boolean value of whether the linked list is empty or not
    public boolean isEmpty(){
        return size() == 0;
    }

    // add an element to the tail of the linked list O(1)
    public void add(T elem){
        addLast(elem);
    }

    // add an element at the beginning of the linked list O(1)
    public void addFirst(T elem){
        if(isEmpty())
            head = tail = new Node<T>(elem, null, null);
        else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;
    }

    // add an element at the tail of the linked list O(1)
    public void addLast(T elem){
        if(isEmpty())
            head = tail = new Node<T>(elem, null, null);
        else{
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    // check the value of first node, if it exists O(1)
    public T peekFirst(){
        if(isEmpty())
            throw new RuntimeException("The list is Empty");
        return head.data;
    }

    // check the value of last node, if it exists --> O(1)
    public T peekLast(){
        if(isEmpty())
            throw new RuntimeException("The list is Empty");
        return tail.data;
    }

    // Remove the value at first node of linked list (head). O(1)
    public T removeFirst(){
        // really? Do I need to explain this -_-
        if(isEmpty())
            throw new RuntimeException("The list is Empty");

        //Extract the data at the head and move the head pointer forward one node
        T data = head.data;
        head = head.next;
        size--;

        //If the list is empty now set the tail to null as well
        if(isEmpty())
            tail = null;

        //Do the memory clean up of the previous node
        else
            head.prev = null;

        //return the data that was at first node and the one we removed
        return data;
    }

    // Remove the last value of the linked list (tail), O(1)
    public T removeLast(){
        //Really?? -_-
        if(isEmpty())
            throw new RuntimeException("The list is Empty and do you really want to remove elements from an empty list pal?");

        //Extract the data at tail and move tail pointer to backward one node
        T data = tail.data;
        tail = tail.prev;
        size--;

        //If list is empty set the head to null
        if(isEmpty())
            head = null;

        //Do memory clean up of node we just removed
        else
            tail.next = null;

        //Return the data that was at last node which we removed
        return data;
    }

    //Remove an arbitrary node from the linked list, O(1)
    private T remove(Node <T> node){
        //if the node to remove is somewhere at head or tail handle them independently
        if (node.prev==null)
            return removeFirst();
        if (node.next==null)
            return removeLast();

        //make the pointers of adjacent nodes skip over 'node'
        node.next.prev = node.prev;
        node.prev.next = node.next;

        //temporary store the data we want to return
        T data = node.data;

        //memory clean up
        node.data = null;
        node = node.next = node.prev = null;
        size--;
        return data;
    }

    // Remove the node at a particular index, O(n)
    public T removeAt(int index){
        if(index<0 || index>=size)
            throw new IllegalArgumentException();
        Node<T> trav;
        int i;
        //search from the front of list
        if(index<size/2){
            for(i=0, trav = head; i!=index; i++)
                trav = trav.next;
        }

        //search from the back of the list
        else{
            for(i=size-1, trav = tail; i!=index; i--)
                trav = trav.prev;
        }
        return remove(trav);
    }

    //Remove a particular value in the linked list, O(n)
    public boolean remove(Object obj){
        Node<T> trav;
        // support searching for null
        if(obj==null){
            for(trav=head; trav!=null; trav=trav.next){
                if(trav.data==null){
                    remove(trav);
                    return true;
                }
            }
        }
        // search for non null object
        else{
            for(trav=head; trav!=null; trav=trav.next){
                if(obj.equals(trav.data)){
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // Find the index of a particular value in Linked list, O(n)
    public int indexOf(Object obj){
        int index = 0;
        Node<T> trav;
        if(obj==null){
            for(trav=head; trav!=null; trav=trav.next, index++){
                if(trav.data==null)
                    return index;
            }
        }
        else{
            for(trav=head; trav!=null; trav=trav.next, index++){
                if(obj.equals(trav.data))
                    return index;
            }
        }
        return -1;
    }

    // Check if value is in the linked list or not
    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator(){
        return new java.util.Iterator<T>(){
            private Node<T> trav = head;
            @Override
            public boolean hasNext(){
                return trav != null;
            }
            @Override
            public T next(){
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }
    @Override public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node <T> trav = head;
        while (trav!=null){
            sb.append(trav.data).append(", ");
            trav = trav.next;
        }
        sb.append("]");
        return sb.toString();
    }
}