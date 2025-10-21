/**
 * Singly-linked list implementation of the List interface with a dummy head node.
 * The dummy node simplifies operations by eliminating special cases for empty lists.
 * Real data starts at dummy.next.
 * 
 * @author Valarie Trinh
 */
public class DummyHeadLinkedList<T> implements List<T> {
    
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node dummy;
    private int size;

    /**
     * Constructs an empty DummyHeadLinkedList with a dummy head node containing null data.
     */
    public DummyHeadLinkedList() {
        dummy = new Node(null);
        size = 0;
    }

    public void add(int index, T element) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        Node current = dummy;
        for(int i = 0; i < index; i++){
            current = current.next;
        }

        Node newNode = new Node(element);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public boolean add(T element) {
        Node newNode = new Node(element);
        Node current = dummy;

        while(current.next != null){
            current = current.next;
        }

        current.next = newNode;
        size++;
        return true;
    }

    public T get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node current = dummy.next;
        for(int i = 0; i < index; i++){
            current = current.next;
        }

        return current.data;
    }

    public T remove(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node current = dummy;
        for(int i = 0; i < index; i++){
            current = current.next;
        }

        T removed = current.next.data;
        current.next = current.next.next;
        size--;
        return removed;
    }

    public int size() {
        return size;
    }
}
