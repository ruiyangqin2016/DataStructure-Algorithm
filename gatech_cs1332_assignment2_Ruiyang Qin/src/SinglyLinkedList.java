/**
 * Your implementation of a circular singly linked list.
 *
 * @author Ruiyang Qin
 * @userid rqin37
 * @GTID 903146737
 * @version 1.0
 */
public class  SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > size || data == null) {
            if (index < 0 || index > size) {
                if (index < 0) {
                    throw new IndexOutOfBoundsException(
                            "Your index is "
                                    + index
                                    + " which is less than zero");
                } else {
                    throw new IndexOutOfBoundsException(
                            "Your index is "
                                    + index
                                    + " which is greater than size "
                                    + size);
                }
            }
            if (data == null) {
                throw new IllegalArgumentException("Data is null!");
            }
        } else {
            if (index == 0) {
                addToFront(data);
            } else if (index == size - 1) {
                addToBack(data);
            } else {
                LinkedListNode<T> node = head;
                for (int i = 0; i < index - 1; i++) {
                    node = node.getNext();
                }
                LinkedListNode<T> newNode = new LinkedListNode<T>(data, null);
                newNode.setNext(node.getNext());
                node.setNext(newNode);
                size++;
            }
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        } else {
            if (head != null) {
                LinkedListNode<T> node
                        = new LinkedListNode<T>(head.getData(), null);
                head.setData(data);
                node.setNext(head.getNext());
                head.setNext(node);
            } else {
                LinkedListNode<T> newNode = new LinkedListNode<T>(data, null);
                head = newNode;
                newNode.setNext(head);
            }
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        } else {
            if (head != null) {
                LinkedListNode<T> newNode = new LinkedListNode<>(data, null);
                LinkedListNode<T> holdHeadNode = head;
                newNode.setNext(holdHeadNode.getNext());
                holdHeadNode.setNext(newNode);
                T holdData = (T) holdHeadNode.getData();
                holdHeadNode.setData(newNode.getData());
                newNode.setData(holdData);
                head = newNode;
            } else {
                LinkedListNode<T> newNode = new LinkedListNode<T>(data, null);
                head = newNode;
                newNode.setNext(head);
            }
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            if (index < 0) {
                throw new IndexOutOfBoundsException(
                        "Your index is "
                                + index
                                + " which is less than zero");
            } else {
                if (index == size) {
                    throw new IndexOutOfBoundsException(
                            "Your index is "
                                    + index
                                    + " which is equal to size "
                                    + size);
                } else {
                    throw new IndexOutOfBoundsException(
                            "Your index is "
                                    + index
                                    + " which is greater than size "
                                    + size);
                }
            }

        }
        T data = head.getData();
        if (index == 0) {
            removeFromFront();
        } else {
            LinkedListNode<T> holdNode = head;
            for (int i = 0; i < index - 1; i++) {
                holdNode = holdNode.getNext();
            }
            data = holdNode.getNext().getData();
            holdNode.setNext(holdNode.getNext().getNext());
            size--;
        }
        return data;
    }

    @Override
    public T removeFromFront() {
        if (head == null) {
            return null;
        } else {
            T holdData = head.getData();
            if (size == 1) {
                head = null;
            } else {
                head.setData(head.getNext().getData());
                head.getNext().setData(holdData);
                head.setNext(head.getNext().getNext());
            }
            size--;
            return  holdData;
        }
    }

    @Override
    public T removeFromBack() {
        if (head == null) {
            return null;
        } else {
            T holdData;
            if (size == 1) {
                holdData = head.getData();
                head = null;
            } else {
                LinkedListNode<T> removeNode = head;
                while (removeNode.getNext().getNext() != head) {
                    removeNode = removeNode.getNext();
                }
                holdData = (T) removeNode.getNext().getData();
                removeNode.setNext(head);
            }
            size--;
            return holdData;
        }
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        } else {
            int pos = 0;
            boolean exist = false;
            LinkedListNode<T> node = head;
            for (int i = 0; i < size; i++) {
                if (node.getData() == data) {
                    pos = i;
                    exist = true;
                }
                node = node.getNext();
            }

            if (exist) {
                T holdData;
                if (pos == 0) {
                    holdData = removeFromFront();
                    return holdData;
                } else {
                    LinkedListNode<T> removeNode = head;
                    for (int i = 0; i < pos - 1; i++) {
                        removeNode = removeNode.getNext();
                    }
                    holdData = removeNode.getNext().getData();
                    removeNode.setNext(removeNode.getNext().getNext());
                    size--;
                    return holdData;
                }
            } else {
                return null;
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            if (index < 0) {
                throw new IndexOutOfBoundsException(
                        "Your index is "
                                + index
                                + " which is less than 0");
            } else {
                if (index == size) {
                    throw new IndexOutOfBoundsException(
                            "Your index is "
                                    + index
                                    + " which is equal to size "
                                    + size);
                } else {
                    throw new IndexOutOfBoundsException(
                            "Your index is "
                                    + index
                                    + " which is greater than size "
                                    + size);
                }
            }
        } else {
            LinkedListNode node = head;
            if (index == 0) {
                return (T) node.getData();
            } else {
                for (int i = 0; i < index; i++) {
                    node = node.getNext();
                }
                return (T) node.getData();
            }
        }
    }

    @Override
    public Object[] toArray() {
        Object[] nodesArray = (T[]) new Object[size];
        LinkedListNode<T> node = head;
        for (int i = 0; i < size; i++) {
            nodesArray[i] = (T) node.getData();
            node = node.getNext();
        }
        return  nodesArray;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}