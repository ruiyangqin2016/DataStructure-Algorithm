import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Ruiyang Qin
 * @userid rqin37
 * @GTID  903146737
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {

    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null!");
        }
        for (T toAdd: data) {
            add(toAdd);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("the data is null");
        }

        root = addHelper(root, data);
    }

    /**
     * add node to the binary search tree by recursion
     *
     * @param data which is from add() method
     * @param node which doing recursively to
     *             locate the leaf where add a new node
     * @return return the node's parent should point
     * to after the operation
     */
    private BSTNode<T> addHelper(BSTNode<T> node, T data) {
        if (node == null) { // add new node
            size++;
            return new BSTNode<>(data);
        } else {
            if (data.compareTo(node.getData()) < 0) { // go left
                node.setLeft(addHelper(node.getLeft(), data));
            } else if (data.compareTo(node.getData()) > 0) { // go right
                node.setRight(addHelper(node.getRight(), data));
            }
            return node;
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("the data is null");
        }
        if (root != null) {
            BSTNode<T> removedNode = new BSTNode<>(null);
            root = removeHelper(root, data, removedNode);
            size--;
            return removedNode.getData();
        } else {
            throw new java.util.NoSuchElementException("The data is not found");
        }
    }

    /**
     *
     * @param node the node which need to be removed
     * @param data the value which is contained by
     *             the node which will be removed
     * @param holdData a node used to be as demi node.
     *                 It similar to the pointer and reference in C++
     *
     * @return the method actually will always return the root. However,
     * with the help of demi node, the node which will be removed will be marked
     */
    private BSTNode<T> removeHelper(BSTNode<T> node,
                                    T data, BSTNode<T> holdData) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The data is not found");
        }
        if (node.getData().equals(data)) {
            holdData.setData(node.getData());
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getRight() != null && node.getLeft() == null) {
                return node.getRight();
            } else if (node.getLeft() != null && node.getRight() == null) {
                return node.getLeft();
            } else { // two children
                BSTNode<T> holdNode = new BSTNode<>(null);
                node.setLeft(predecessor(node.getLeft(), holdNode));
                node.setData(holdNode.getData());
                return node;
            }
        } else {
            if (data.compareTo(node.getData()) < 0) { // go left
                node.setLeft(removeHelper(node.getLeft(), data, holdData));
            } else {                                    // go right
                node.setRight(removeHelper(node.getRight(), data, holdData));
            }
            return node;
        }
    }

    /**
     *
     * Description: The predecessor will be used in two children situation
     * it will help the removeHelper() when the removehelper() finds the
     * location but this parent has two child
     * @param demo a node used to be as demi node.
     *             It similar to the pointer and reference in C++
     *             Pass the value out of the method
     * @param node the node from removeHelper
     * @return the node which will replace the
     * node which supposed to be removed from BST
     */
    private BSTNode<T> predecessor(BSTNode<T> node, BSTNode<T> demo) {
        if (node.getRight() == null) {
            demo.setData(node.getData());
            return node.getLeft();
        } else {
            node.setRight(predecessor(node.getRight(), demo));
            return node;
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("the data is null");
        }

        return getHelper(root, data).getData();
    }

    /**
     * Explanation: this method is help the get()
     * method by recursion to find the
     * value and return the value from the node
     *
     * @param node start from root, using to search the data by recursively way
     * @param data the data from get() method which
     *             is required to be found in the BST
     * @return the node with the data found!
     */
    private BSTNode<T> getHelper(BSTNode<T> node, T data) {
        if (node == null) {
            throw new java.util.NoSuchElementException("the data is not found");
        } else {
            if (data.compareTo(node.getData()) < 0) { // go left
                return getHelper(node.getLeft(), data);
            } else if (data.compareTo(node.getData()) > 0) { // go right
                return getHelper(node.getRight(), data);
            } else { // data found!
                return node;
            }
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("the data is null");
        }
        BSTNode<T> holdNode = new BSTNode<>(null);
        containHelper(root, data, holdNode);
        return data.equals(holdNode.getData());
    }

    /**
     *
     * Helper method for contains()
     * @param node start from root, work with recursion
     * @param data check it the BST contain this element
     * @param demo a node used to be as demi node.
     *             It similar to the pointer and reference in C++
     * @return always return root because of the pointer reinforcement
     * but because of the demo, the value will be able to pass out
     */
    private BSTNode<T> containHelper(BSTNode<T> node, T data, BSTNode<T> demo) {
        if (node == null) {
            demo.setData(null);
            return null;
        } else {
            if (data.compareTo(node.getData()) > 0) { // go right
                containHelper(node.getRight(), data, demo);
            } else if (data.compareTo(node.getData()) < 0) { // go left
                containHelper(node.getLeft(), data, demo);
            } else { // data found
                demo.setData(node.getData());
            }
            return node;
        }
    }

    @Override
    public List<T> preorder() {
        List<T> arr = new ArrayList<>(size);
        preorderHelper(root, arr);
        return arr;
    }

    /**
     * helper method for preorder
     * @param node start from root, for recursion
     * @param arr arraylist which will be pass out
     */
    private void preorderHelper(BSTNode<T> node, List<T> arr) {
        if (node != null) {
            arr.add(node.getData());
            preorderHelper(node.getLeft(), arr);
            preorderHelper(node.getRight(), arr);
        } else {
            return;
        }
    }

    @Override
    public List<T> postorder() {
        List<T> arr = new ArrayList<>(size);
        postorderHelper(root, arr);
        return arr;
    }

    /**
     * helper method for postorder
     * @param node start from root, for recursion
     * @param arr arraylist which will be pass out
     */
    private void postorderHelper(BSTNode<T> node, List<T> arr) {
        if (node != null) {
            postorderHelper(node.getLeft(), arr);
            postorderHelper(node.getRight(), arr);
            arr.add(node.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> arr = new ArrayList<>(size);
        inorderHelper(root, arr);
        return arr;
    }

    /**
     * helper method for inorder
     * @param node start from root, for recursion
     * @param arr arraylist which will be pass out
     */
    private void inorderHelper(BSTNode<T> node, List<T> arr) {
        if (node != null) {
            inorderHelper(node.getLeft(), arr);
            arr.add(node.getData());
            inorderHelper(node.getRight(), arr);
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> arr = new ArrayList<>(size);
        Queue<BSTNode<T>> q = new LinkedList<>();
        q.add(root);
        levelorderHelper(arr, q);
        return arr;
    }

    /**
     * helper method for levelorder()
     * @param arr the arraylist which will be pass out
     * @param q Queue which is used for implement levelorder by dequeue and
     *          enqueue.
     */
    private void levelorderHelper(List<T> arr, Queue<BSTNode<T>> q) {
        while (q.peek() != null) {
            BSTNode<T> n = q.peek();
            if (n != null) {
                if (n.getLeft() != null) {
                    q.add(n.getLeft());
                }
                if (n.getRight() != null) {
                    q.add(n.getRight());
                }
                arr.add(q.remove().getData());
            }
        }
    }

    @Override
    public int distanceBetween(T data1, T data2) {
        if (data1 == null) {
            throw new IllegalArgumentException("data " + data1 + " is null");
        }
        if (data2 == null) {
            throw new IllegalArgumentException("data " + data2 + " is null");
        }
        if (data1 == null && data2 == null) {
            throw new IllegalArgumentException("data "
                    + data1 + ", " + data2 + " are null");
        }

        /*
        make sure that data1 is always smaller than data2
         */
        T smallerData;
        T holdData = data1;
        if (data1.compareTo(data2) > 0) {
            smallerData = data2;
            data1 = smallerData;
            data2 = holdData;
        }

        BSTNode<T> lowestAncestor = lowestAns(root, data1, data2);
        if (lowestAncestor.getData().equals(data1)
                && lowestAncestor.getData().equals(data2)) {
            return 0;
        }
        return distanceHelper(lowestAncestor, data1)
                + distanceHelper(lowestAncestor, data2);
    }

    /**
     *
     * @param node  start from root, work with recursion to figure out
     *              which node is the lowest ancester
     * @param data1 data1 from distance method
     * @param data2 data2 from distance method
     * @return return the lowest ancester
     */
    private BSTNode<T> lowestAns(BSTNode<T> node, T data1, T data2) {
        if (node == null) {
            throw new java.util.NoSuchElementException(
                    "data 1 or data 2 or both is not in the tree");
        }
        if (node.getData().equals(data1) || node.getData().equals(data2)) {
            return node;
        } else {
            if (!((node.getData().compareTo(data1) > 0)
                    && (node.getData().compareTo(data2) < 0))) {
                if (node.getData().compareTo(data1) < 0) {
                    return lowestAns(node.getRight(), data1, data2);
                } else {
                    return lowestAns(node.getLeft(), data1, data2);
                }
            } else {
                return node;
            }
        }
    }

    /**
     *
     * @param lowestAncestor the lowest ancestor which count
     *                       the both side of its node
     * @param data data1 and data2
     * @return return distance
     */
    private int distanceHelper(BSTNode<T> lowestAncestor, T data) {
        if (lowestAncestor == null) {
            throw new java.util.NoSuchElementException("the data is not found");
        }
        if (data.compareTo(lowestAncestor.getData()) < 0) {
            return distanceHelper(lowestAncestor.getLeft(), data) + 1;
        } else if (data.compareTo(lowestAncestor.getData()) > 0) {
            return distanceHelper(lowestAncestor.getRight(), data) + 1;
        } else {
            return 0;
        }
    }



    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return heightHelper(root);
    }


    /**
     *
     * @param node start from root for access the tree
     * @return return the height in each branch
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(heightHelper(node.getLeft()),
                    heightHelper(node.getRight())) + 1;
        }
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
