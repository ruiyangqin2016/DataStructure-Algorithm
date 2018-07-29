import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed queue.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (e.g. gburdell3)
 * @GTID YOUR GT ID HERE (e.g. 900000000)
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        super();
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you should
     * explicitly reset front to 0.
     *
     * You should replace any spots that you dequeue from with null. Failure to
     * do so can result in a loss of points.
     *
     * See the homework pdf for more information on implementation details.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("queue is empty");
        }
        T holdElement = backingArray[front];
        backingArray[front] = null;
        if (front == backingArray.length - 1) {
            front = 0;
        } else {
            front++;
        }
        size--;
        return holdElement;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (size < backingArray.length) {
            if (backingArray[backingArray.length - 1] == null) {
                backingArray[(size + front) % backingArray.length] = data;
            } else {
                int i = 0;
                while (backingArray[i] != null) {
                    i++;
                }
                backingArray[i] = data;
            }
        } else {
            Object[] newArr = new Object[backingArray.length * 2];
            for (int i = 0, j = front; i < size - front; i++, j++) {
                newArr[i] = backingArray[j];
            }
            for (int i = 0, j = size - front; i < front; i++, j++) {
                newArr[j] = backingArray[i];
            }
            newArr[size] = data;
            front = 0;
            backingArray = (T[]) newArr;
        }
        size++;
    }

    @Override
    public T peek() {
        return backingArray[front];
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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}