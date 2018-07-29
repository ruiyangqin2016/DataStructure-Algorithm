public class DoublyLinkedNode<T> {

    private T data;
    private DoublyLinkedNode<T> next;
    private DoublyLinkedNode<T> prev;

    /**
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * fsge
     * @param data data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     *
     * @return next
     */
    public DoublyLinkedNode<T> getNext() {
        return next;
    }

    /**
     * @param next next
     * @return
     */
    public void setNext(DoublyLinkedNode<T> next) {
        this.next = next;
    }

    /**
     *
     * @return prev
     */
    public DoublyLinkedNode<T> getPrev() {
        return prev;
    }

    /**
     * @param prev prev
     * @return
     */
    public void setPrev(DoublyLinkedNode<T> prev) {
        this.prev = prev;
    }

    /**
     * @param data data
     * @param prev prev
     * @param next next
     * @return
     */
    public DoublyLinkedNode(T data,
                            DoublyLinkedNode<T> next,
                            DoublyLinkedNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    /**
     *
     * @param data data
     */
    public DoublyLinkedNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
