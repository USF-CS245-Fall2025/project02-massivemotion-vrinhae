/**
 * Array-based implementation of the List interface with dynamic resizing.
 * Initial capacity is 10 and doubles when full.
 * 
 * @author Valarie Trinh
 */
public class ArrayList<T> implements List<T> {
    private Object[] data;
    private int size;

    /**
     * Constructs an empty ArrayList with an initial capacity of 10.
     */
    public ArrayList() {
        data = new Object[10];
        size = 0;
    }

    public void add (int index, T element) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        
        if(size == data.length){
            Object[] temp = new Object[data.length * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }

        for(int j = size; j > index ; j--){
            data[j] = data[j - 1];
        }
        
        data[index] = element;
        size++;
    }

    public boolean add (T element) {
        if(size == data.length){
            Object[] temp = new Object[data.length * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }

        data[size] = element;
        size++;
        return true;
    }

    public T get (int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        
        return (T)data[index];
    }

    public T remove (int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        T removed = (T)data[index];
        for(int i = index; i < size - 1; i++){
            data[i] = data[i + 1];
        }

        size--;
        return removed;
    }

    public int size() {
        return size;
    }
}
