/**
 * Singly-linked list implementation of the List interface with head pointer.
 * Each node contains data and a reference to the next node.
 * 
 * @author Valarie Trinh
 */
public class LinkedList<T> implements List<T> {
    
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    /**
     * Constructs an empty LinkedList with head set to null.
     */
    public LinkedList() {
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
        } else {
            Node current = head;
            for(int i = 0; i < index - 1; i++){
                current = current.next;
            }
            newNode.next = current.next;
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
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
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
        } else {
            Node current = head;
            for(int i =  0; i < index - 1; i++){
                current = current.next;
            }
            removed = current.next.data;
            current.next = current.next.next;
        }

        size--;
        return removed;
    }

    public int size() {
        return size;
    }
}
