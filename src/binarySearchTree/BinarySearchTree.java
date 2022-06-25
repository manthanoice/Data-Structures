package binarySearchTree;

public class BinarySearchTree <T extends Comparable<T>>{

    //tracks the number of nodes in binary tree
    private int nodeCount = 0;

    //this BST is rooted tree, so we maintain a handle on the root node
    private Node root = null;

    //internal node containing node references and actual node data
    private class Node{
        T data;
        Node left, right;
        public Node(Node left, Node right, T elem){
            this.data = elem;
            this.left = left;
            this.right = right;
        }
    }

    //check if binary tree is empty
    public boolean isEmpty(){
        return size() == 0;
    }

    //Get the numbers of node in binary tree
    public int size(){
        return nodeCount;
    }

    //Add an element to binary tree, and it will return true if we have successfully added the element
    public boolean add(T elem){
        //if value already exists in binary search tree, ignore it, don't add it.
        if(contains(elem))
            return false;
        //if value doesn't exist add it to binary tree
        else{
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    //private method to recursively add a value to binary tree
    private Node add(Node node, T elem){
        //base case: found a leaf node
        if(node==null)
            node = new Node(null, null, elem);
        else{
            if(elem.compareTo(node.data)<0)
                node.left = add(node.left, elem);
            else
                node.right = add(node.right, elem);
        }
        return node;
    }
}