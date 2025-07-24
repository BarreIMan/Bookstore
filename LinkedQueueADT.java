public class LinkedQueueADT<E> implements AbstractLinkedQueue<E> {
    private class Node<E>{
        // attributes
        private E element;
        private Node<E> next;

        // constructor
        public Node ( E element ) {
            this.element = element;
            this.next = null;
        }
    }

    // attributes
    private Node<E> head; // Reference to the first node in the queue
    private Node<E> tail; // Reference to the last node in the queue
    private int size; // The current number of elements in the queue

    // constructor
    public LinkedQueueADT (){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void offer ( E element ) {
        Node<E> newNode = new Node<>(element);

        // if the queue is empty
        if (head == null && tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else { // if the queue is not empty
            this.tail.next = newNode;
            this.tail = newNode;
        }

        this.size++;
    }

    @Override
    public E poll() {
        // if the queue is empty
        if (head == null && tail == null) {
            throw new IllegalStateException("Queue is empty");
        }

        E oldElement = this.head.element;

        if (head == tail){ // if the queue has only one element
            this.head = null;
            this.tail = null;
        } else { // if the queue has more than one element
            Node<E> tempNode = this.head;
            this.head = this.head.next;
            tempNode.next = null;
        }

        this.size--;
        return oldElement;
    }

    @Override
    public E peek() {
        // if the queue is empty
        if (head == null && tail == null) {
            throw new IllegalStateException("Queue is empty");
        }

        return this.head.element;
    }
    public int findElement(E element){
        Node<E> tempNode = this.head;
        int index_count = 1;

        if (this.size != 0) {
            while (tempNode != null) {
                if (tempNode.element == element){
                    return index_count;
                }
                tempNode = tempNode.next;
                index_count++;
            }
        }
        return -1;
    }
    @Override
    public int size () {
        return this.size;
    }

    @Override
    public boolean isEmpty () {
        if (this.head == null && this.tail == null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString () {
        // StringBuilder result = new StringBuilder();
        // result.append("delivery <--");
        // Node<E> tempNode = this.head;
        // while (tempNode != null) {
        //     result.append(tempNode.element);
        //     if (tempNode.next != null) {
        //         result.append(" <-- ");
        //     }
        //     tempNode = tempNode.next;
        // }
        // return result.toString();
        
        // StringBuilder result = new StringBuilder();
        // result.append("delivery <-- ");
        // Node<E> tempNode = this.head;

        // while (tempNode != null) {
        //     result.append(tempNode.element);
        //     if (tempNode.next != null) {
        //         result.append(" <-- ");
        //     }
        //     tempNode = tempNode.next;
        // }

        // return result.toString();
        return "test";
    }
    public String viewQueue () {
        
        StringBuilder result = new StringBuilder();
        Node<E> tempNode = this.head;

        while (tempNode != null) {
            result.append(tempNode.element);
            if (tempNode.next != null) {
                result.append(" <-- ");
            }
            tempNode = tempNode.next;
        }

        return result.toString();
    }
}

