/**
 * Doubly-linked list implementation of the List interface with head pointer.
 * Each node contains data and references to both next and previous nodes.
 * 
 * @author Valarie Trinh
 */
public class DoublyLinkedList<T> implements List<T> {
    
    private class Node {
        T data;
        Node next;
        Node prev;
        
        Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private int size;

    /**
     * Constructs an empty DoublyLinkedList with head set to null.
     */
    public DoublyLinkedList() {
        head = null;
        size = 0;
    }

    public void add(int index, T element) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        
        Node newNode = new Node(element);
        if(index == 0){
            newNode.next = head;
            head = newNode;
            if(head != null){
                head.prev = newNode;
            }
        } else {
            Node current = head;
            for(int i = 0; i < index - 1; i++){
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            if(current.next != null){
                current.next.prev = newNode;
            }
            current.next = newNode;
        }

        size++;
    }

    public boolean add(T element) {
        Node newNode = new Node(element);

        if(head == null){
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }

        size++;
        return true;
    }

    public T get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node current = head;
        for(int i = 0; i < index; i ++){
            current = current.next;
        }

        return current.data;
    }

    public T remove(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        T removed;
        if(index == 0){
            removed = head.data;
            head = head.next;
            if(head != null){
                head.prev = null;
            }
        } else {
            Node current = head;
            for(int i = 0; i < index - 1; i ++){
                current = current.next;
            }

            Node toRemove = current.next;
            removed = toRemove.data;
            current.next = toRemove.next;
            
            if(toRemove.next != null){
                toRemove.next.prev = current;
            }
        }

        size--;
        return removed;
    }

    public int size() {
        return size;
    }
}
