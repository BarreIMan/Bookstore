public class AbstractListADT<E> implements AbstractList<E> {
    private E[] elements;
    private int nextIndex;

    public AbstractListADT(){
        this.elements = (E[]) new Object[5];
        this.nextIndex = 0;
    }

    @Override
    public boolean add(E element) {
        if (nextIndex == elements.length){
            E[] newElements = (E[]) new Object[this.elements.length * 2];
            

            for(int i = 0; i < elements.length; i++){
                newElements[i] = elements[i];
            }
            elements = newElements;
        }

        elements[nextIndex] = element;
        nextIndex++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (nextIndex == elements.length){
            E[] newElements = (E[]) new Object[this.elements.length * 2];
            

            for(int i = 0; i < elements.length; i++){
                newElements[i] = elements[i];
            }
            elements = newElements;
        }

        for(int i = nextIndex; i > index; i--){
            elements[i] = elements[i-1];
        }

        elements[index] = element;
        nextIndex++;

        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }
    @Override
    public E set(int index, E element) {
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        E oldElement = elements[index];

        for (int i = index; i < nextIndex; i++){
            elements[i] = elements[i + 1];
        }

        elements[nextIndex - 1] = null;
        nextIndex--;

        if (nextIndex < elements.length / 3) {
            E[] largerElements = (E[]) new Object[elements.length / 2];

            for ( int i = 0; i < nextIndex; i++ ) {
                largerElements[i] = elements[i];
            }

            elements = largerElements;
        }

        return oldElement;
    }

    @Override
    public int size() {
       return nextIndex;
    }

    @Override
    public int indexOf(E element) {
         for ( int i = 0; i < nextIndex; i++ ) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        for ( int i = 0; i < nextIndex; i++ ) {
            if (elements[i].equals(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
         if (nextIndex == 0) {
            return true;
        }

        return false;
    }
    
    @Override
        public String toString() {
            // Option 1
            String result = "[";
            for ( int i = 0; i < nextIndex; i++ ) {
               result += elements[i];
            
               if (i < nextIndex - 1) {
                   result += ", ";
               }
            }
            result += "]";
            return result;

            // Option 2
            // StringBuilder result = new StringBuilder();
            // result.append("[");
            // for ( int i = 0; i < nextIndex; i++ ) {
            //     result.append(elements[i]);

            //     if (i < nextIndex - 1) {
            //         result.append(", ");
            //     }
            // }
            // result.append("]");
            // return result.toString();
        }
    }
