/**
 * Your implementation of an ArrayList.
 *
 * @author Ruiyang Qin
 * @userid rqin37
 * @GTID 903146737
 * @version A5
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size = 0;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        super();
        if (INITIAL_CAPACITY < 0) {
            throw new IllegalStateException("Illegal Capacity: "
                    + INITIAL_CAPACITY);
        }
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(
                "Index cannot be less than zero!");
        }
        if (data == null) {
            throw new IllegalStateException("Illegal!");
        }

        Object[] newArr;
        if (size < backingArray.length) {
            if (index == 0) {
                addToFront(data);
            } else {
                for (int i = size; i > index; i--) {
                    backingArray[i] = backingArray[i - 1];
                }
                backingArray[index] = data;
                size++;
            }
        } else {
            newArr = new Object[backingArray.length * 2];
            for (int i = 0; i < index; i++) {
                newArr[i] = backingArray[i];
            }
            newArr[index] = data;
            for (int i = index; i < size; i++) {
                newArr[i + 1] = backingArray[i];
            }
            size++;
            backingArray = (T[]) newArr;
        }

    }

    @Override
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalStateException("Illegal!");
        }
        Object[] newArr;
        if (size < backingArray.length) {
            for (int i = size; i > 0; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[0] = data;
        } else {
            newArr = new Object[backingArray.length * 2];
            newArr[0] = data;
            for (int i = 0; i < size; i++) {
                newArr[i + 1] = backingArray[i];
            }
            backingArray = (T[]) newArr;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalStateException("Illegal!");
        }
        if (size < backingArray.length) {
            backingArray[size] = data;
        } else {
            Object[] newArr = new Object[backingArray.length * 2];
            for (int i = 0; i < size; i++) {
                newArr[i] = backingArray[i];
            }
            newArr[size] = data;
            backingArray = (T[]) newArr;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index cannot be less than zero!");
        }
        T holdElement = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        size--;
        return holdElement;
    }

    @Override
    public T removeFromFront() {
        T holdElem = backingArray[0];
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        size--;
        return holdElem;
    }

    @Override
    public T removeFromBack() {
        T holdElement = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return holdElement;
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index has to be bigger than 0");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        boolean empty = true;
        if (size == 0) {
            empty = true;
        } else {
            empty = false;
        }
        return empty;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Object[] newArr = new Object[backingArray.length];
        backingArray = (T[]) newArr;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
