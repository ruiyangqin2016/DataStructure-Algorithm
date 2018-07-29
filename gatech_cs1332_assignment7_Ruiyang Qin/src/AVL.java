import java.util.Collection;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Ruiyang Qin
 * @userid rqin37
 * @GTID 903146737
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {

    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("the data is null");
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
     *
     * @param data which is from add() method
     * @param node which doing recursively to
     *             locate the leaf where add a new node
     * @return return the node's parent should point
     * to after the operation
     */
    private AVLNode<T> addHelper(AVLNode<T> node, T data) {
        if (node == null) {
            size++;
            return new AVLNode<>(data);
        } else {
            if (data.compareTo(node.getData()) < 0) { // go left
                node.setLeft(addHelper(node.getLeft(), data));
            } else if (data.compareTo(node.getData()) > 0) { //go right
                node.setRight(addHelper(node.getRight(), data));
            }
            update(node);
            return checkFactor(node);
        }
    }

    /**
     *
     * @param node check if this node need a rotation
     * @return return the node which work will pointer reinforcement
     */
    private AVLNode<T> checkFactor(AVLNode<T> node) {
        if (node.getBalanceFactor() == 2) {
            // Left-Right Rotation
            if (node.getLeft().getBalanceFactor() == -1) {
                node.setLeft(leftRotationHelper(node.getLeft()));
                return rightRotationHelper(node);
            } else { // Right Rotation
                return rightRotationHelper(node);
            }
        } else if (node.getBalanceFactor() == -2) {
            //Right-Left Rotation
            if (node.getRight().getBalanceFactor() == 1) {
                node.setRight(rightRotationHelper(node.getRight()));
                return leftRotationHelper(node);
            } else { //Left Rotation
                return leftRotationHelper(node);
            }
        } else {
            return node;
        }
    }
    /**

     * This method works when the balance factor is -2
     * @param node the node point to the starting access
     *             node of the branch
     * @return the parent node of the rotation branch
     */
    private AVLNode<T> leftRotationHelper(AVLNode<T> node) {

        AVLNode<T> holdNode = node.getRight();
        //left rotation
        node.setRight(holdNode.getLeft());
        holdNode.setLeft(node);
        update(node);
        update(holdNode);
        return holdNode;
    }

    /**
     * This method works when the balance factor is 2
     * @param node the node point to the starting access
     *             node of the branch
     * @return the parent node of the rotation branch
     */
    private AVLNode<T> rightRotationHelper(AVLNode<T> node) {
        AVLNode<T> holdNode = node.getLeft();
        //right rotation
        node.setLeft(holdNode.getRight());
        holdNode.setRight(node);
        update(node);
        update(holdNode);
        return holdNode;
    }

    /**
     *
     * @param node start from root
     */
    private void update(AVLNode<T> node) {
        if (node != null) {
            node.setHeight(Math.max(
                    updateHelper(node.getLeft()),
                    updateHelper(node.getRight())) + 1);
            if (node.getRight() == null && node.getLeft() == null) {
                node.setBalanceFactor(0);
            } else if (node.getRight() == null) {
                node.setBalanceFactor(node.getLeft().getHeight()
                        - (-1));
            } else if (node.getLeft() == null) {
                node.setBalanceFactor((-1)
                        - node.getRight().getHeight());
            } else {
                node.setBalanceFactor(node.getLeft().getHeight()
                        - node.getRight().getHeight());
            }
        }
    }

    /**
     *
     * @param node the node which is checked height and Balance factor
     * @return height or -1 if the node == null
     */
    private int updateHelper(AVLNode<T> node) {
        return node == null ? -1 : node.getHeight();
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null");
        }
        if (root != null) {
            AVLNode<T> removedNode = new AVLNode<>(null);
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
     * @param removedNode a node used to be as demi node.
     *                 It similar to the pointer and
     *                 reference in C++
     * @return the method actually will always return the root. However,
     * with the help of demi node, the node which will be removed will be marked
     */
    private AVLNode<T> removeHelper(
            AVLNode<T> node, T data, AVLNode<T> removedNode) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The data is not found");
        }

        if (node.getData().equals(data)) {
            removedNode.setData(node.getData());
            //no children
            if (node.getRight() == null && node.getLeft() == null) {
                return null;
            } else if (node.getRight() != null && node.getLeft() == null) {
                return node.getRight();
            } else if (node.getLeft() != null && node.getRight() == null) {
                return node.getLeft();
            } else {
                // two children
                AVLNode<T> holdNode = new AVLNode<>(null);
                node.setRight(successor(node.getRight(), holdNode));
                node.setData(holdNode.getData());
                update(node);
                return checkFactor(node);
            }
        } else {
            if (data.compareTo(node.getData()) < 0) { // go left
                node.setLeft(removeHelper(node.getLeft(), data, removedNode));
            } else { // go right
                node.setRight(removeHelper(node.getRight(), data, removedNode));
            }
            update(node);
            return checkFactor(node);
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
     * node which supposed to be removed from AVLTree
     */
    private AVLNode<T> successor(AVLNode<T> node, AVLNode<T> demo) {
        if (node.getLeft() == null) {
            demo.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(successor(node.getLeft(), demo));
            update(node);
            return checkFactor(node);
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
    private AVLNode<T> getHelper(AVLNode<T> node, T data) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The data is not found");
        }
        if (data.compareTo(node.getData()) < 0) { // go left
            return getHelper(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) { // go right
            return getHelper(node.getRight(), data);
        } else { // data found!
            return node;
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null");
        }
        AVLNode<T> holdNode = new AVLNode<>(null);
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
     */
    private void containHelper(AVLNode<T> node, T data, AVLNode<T> demo) {
        if (node == null) {
            demo.setData(null);
        } else {
            if (data.compareTo(node.getData()) > 0) { // go right
                containHelper(node.getRight(), data, demo);
            } else if (data.compareTo(node.getData()) < 0) { // go left
                containHelper(node.getLeft(), data, demo);
            } else { // data found
                demo.setData(node.getData());
            }
        }
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() {
        if (size <= 1) {
            throw new java.util.NoSuchElementException(
                    "there isn't enough data in the tree"
                            + " for there to be a second largest element");
        }

        if (root.getLeft() != null && root.getRight() == null) {
            return root.getLeft().getData();
        } else {
            AVLNode<T> holdNode = new AVLNode<>(null);
            getSecondLargestHelper(root, holdNode);
            return holdNode.getData();
        }
    }

    /**
     *
     * @param node pass in root and check the second largest node
     * @param holdNode pass out the second largest node
     * @return root
     */
    private AVLNode<T> getSecondLargestHelper(AVLNode<T> node,
                                              AVLNode<T> holdNode) {
        if (node.getRight().getRight() == null) {
            if (node.getRight().getLeft() == null) {
                holdNode.setData(node.getData());
                return node;
            } else {
                holdNode.setData(node.getRight().getLeft().getData());
                return node;
            }

        } else {
            getSecondLargestHelper(node.getRight(), holdNode);
            return node;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AVL)) {
            return false;
        }
        AVL<T> tree = (AVL<T>) obj;
        return equalsHelper(tree.root, root);
    }


    /**
     *
     * @param nFT1 node from pass in object
     * @param nFT2 node from this object
     * @return boolean value
     */
    private boolean equalsHelper(
            AVLNode<T> nFT1, AVLNode<T> nFT2) {
        if (nFT1 == null && nFT2 == null) {
            return true;
        } else if ((nFT1 == null && nFT2 != null)
                || (nFT1 != null && nFT2 == null)) {
            return false;
        } else if (nFT1.getData().equals(nFT2.getData())) {
            return equalsHelper(nFT1.getLeft(), nFT2.getLeft())
                    && equalsHelper(nFT1.getRight(), nFT2.getRight());
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
