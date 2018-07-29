public class DoublyLinkedList<T> {

    private DoublyLinkedNode<T> head;
    private DoublyLinkedNode<T> tail;
    private int size;

    /**
     * @param data data
     * @param index index
     */
    public void addAtIndex(T data, int index) {
        if (index == 0) {
            addToFront(data);
        } else {
            DoublyLinkedNode<T> node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            DoublyLinkedNode<T> newNode = new DoublyLinkedNode<T>(data, node.getNext(), node);
            newNode.getNext().setPrev(newNode);
            node.setNext(newNode);
            size++;
        }
    }

    /**
     * @param index index
     */
    public T removeAtIndex(int index) {
        if (size > 0 && (index >= 0 && index < size)) {
            DoublyLinkedNode<T> node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            T holdData = node.getNext().getData();
            node.getNext().getNext().setPrev(node);
            node.setNext(node.getNext().getNext());
            size--;
            return holdData;
        } else {
            throw new IllegalArgumentException("index is wrong");
        }

    }

    /**
     *
     * @param data data
     */
    public void addToFront(T data) {
        if (head == null) {
            DoublyLinkedNode<T> newNode
                    = new DoublyLinkedNode<T>(data, null, null);
            head = newNode;
            tail = head;
        } else {
            DoublyLinkedNode<T> newNode
                    = new DoublyLinkedNode<T>(data, head, null);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    /**
     *
     * @param data data
     */
    public void addToBack(T data) {
        if (head == null) {
            DoublyLinkedNode<T> newNode
                    = new DoublyLinkedNode<T>(data, null, null);
            tail = newNode;
            head = tail;
        } else {
            DoublyLinkedNode<T> newNode
                    = new DoublyLinkedNode<T>(data, null, tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * @return element which is removed from front
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new IllegalArgumentException("No data!");
        } else {
            T elem = head.getData();
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                head.setPrev(null);
            }
            size--;
            return elem;
        }
    }

    /**
     * @return element which is removed from back
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new IllegalArgumentException("No node!");
        } else {
            T elem = tail.getData();
            if (size == 1) {
                tail = null;
                head = null;
            } else {
                tail = tail.getPrev();
                tail.setNext(null);
            }
            size--;
            return elem;
        }
    }

    /**
     *
     * @return T[]
     */
    public Object[] toArray() {
        Object[] arr = (T[]) new Object[size];
        DoublyLinkedNode<T> node = head;
        int i = 0;
        while (node.getNext() != null) {
            arr[i] = (T) node.getData();
            node = node.getNext();
            i++;
        }
        arr[i] = (T) node.getData();
        return arr;
    }

}
