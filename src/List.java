public interface List<T> {

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index position where the element should be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, T element);

    /**
     * Appends the specified element to the end of this list.
     * @param element the element to be added
     * @return true if the element was successfully added
     */
    public boolean add(T element);

    /**
     * Retrieves the element at the specified index in this list.
     * @param index position where the element should be retrieved
     * @return element at specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index);

    /**
     * Removes the element at the specified index in this list.
     * @param index position where the element should be removed
     * @return element removed at specified index
     * @throws IndexOutOFBoundsException if the index is out of range
     */
    public T remove(int index);
    
    /**
     * Returns the number of elements in this list.
     * @return the size of the list
     */
    public int size();
}
