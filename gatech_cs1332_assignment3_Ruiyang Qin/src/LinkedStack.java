import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (e.g. gburdell3)
 * @GTID YOUR GT ID HERE (e.g. 900000000)
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("stack is empty");
        }
        T holdElement = head.getData();
        if (size == 1) {
            head = null;
        } else {
            head = head.getNext();
        }
        size--;
        return holdElement;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data, head);
        head = newNode;
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        } else {
            return head.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }
}