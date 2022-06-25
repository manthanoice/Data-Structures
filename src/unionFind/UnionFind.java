package unionFind;

public class UnionFind {

    //the number of elements in union find
    private final int size;

    //used to track the sizes of each of the components
    private final int[] sz;

    //id[i] points to the parent of i, if id[i] == i then we know i is the root node
    private final int[] id;

    //track of number of components in union find
    private int numComponents;

    public UnionFind(int size){
        if(size<=0)
            throw new IllegalArgumentException("Size <=0 is not allowed");
        this.size = numComponents = size;
        sz = new int[size];
        id = new int[size];
        for(int i=0; i<size; i++){
            id[i] = i; //link to itself (self root)
            sz[i] = i; //each component is originally of size one
        }
    }

    //find which component/set 'p' belongs to, take amortized constant time.
    public int find(int p){

        //find the root of component/set.
        int root = p;
        while (root != id[root])
            root = id[root];

        //compress the path leading back to the root.
        //doing this operation is called "path compression".
        //and that is what gives us amortized time complexity
        while(p!=root){
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    //return weather or not the elements 'p' and 'q' are in the same component/set.
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    //return the size of component/set 'p' belongs to
    public int componentSize(int p){
        return sz[find(p)];
    }

    //returns the number of element in the uniform/disjoint set
    public int size(){
        return size;
    }

    //returns the number of remaining components/sets
    public int components(){
        return numComponents;
    }

    //unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q){
        int root1 = find(p);
        int root2 = find(q);

        //if these are already in same group
        if(root1 == root2)
            return;

        //merge two components/sets together
        //merge smaller component/set into larger one
        if(sz[root1] < sz[root2]){
            sz[root2] += sz[root1];
            id[root1] = root2;
        }
        else{
            sz[root1] += sz[root2];
            id[root2] = root1;
        }

        //since the roots are found are different we know that the number of component/set has been decreased by one
        numComponents--;
    }
}