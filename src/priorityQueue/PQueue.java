package priorityQueue;

import java.util.*;

public class PQueue <T extends Comparable <T>>{

    // the number of elements currently inside the heap
    private int heapSize = 0;

    // the internal capacity of the heap
    private int heapCapacity = 0;

    // a dynamic list to track the elements inside the heap
    private List<T> heap = null;

    // this map keeps track of possible indices of a particular
    // node value is found in the heap. Having these mapping lets
    // us have O(log(n)) removals and O(1) element containment check
    // at the cost of some additional space and minor overhead
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // construct an initially empty priority queue
    public PQueue(){
        this(1);
    }

    // construct a priority queue with an initial capacity
    public PQueue(int size){
        heap = ArrayList<>(size);
    }

    // using heapify
    public PQueue(T[] elems){
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<>(heapCapacity);
        for(int i=0; i<heapSize; i++){
            mapAdd(elems[i], i);
            heap.add(elems[i]);
        }
        //heapify process, O(n)
        for(int i=Math.max(0, (heapSize/2)-1); i>=0; i--)
            sink(i);
    }

    // priority queue construction using O(nlog(n))
    public PQueue(Collection<T> elems){
        this(elems.size());
        for(T elem : elems)
            add(elem);
    }

    // returns true or false depending on if the priority queue is empty
    public boolean isEmpty(){
        return heapSize == 0;
    }

    // clears everything inside the heap, O(n)
}