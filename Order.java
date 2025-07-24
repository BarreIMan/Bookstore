public class Order{
    String customerName;
    String shippingAddress;
    String status;
    Order next;
    int ID;
    
    // I wanted to use a Dictionary at first but this will make a good alternative
    public AbstractListADT<BookInfo> bookList = new AbstractListADT<>();
    public AbstractListADT<Integer> bookQuantityList = new AbstractListADT<>();

    public Order(String customerName, String shippingAddress, int ID){
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.next = null;
        this.ID = ID;
    }

    private void sortBookListByPrice(){
        for (int i = 0; i < bookList.size(); i++){
            for (int j = 0; j < bookList.size(); j++){
                if (bookList.get(i).price > bookList.get(j).price) {
                    BookInfo tempBook = bookList.get(i);

                    bookList.set(i, bookList.get(j));
                    bookList.set(j, tempBook);
                }
            }
        }   
    }

    public void orderBook(BookInfo bookOject){
        int bookQuantityInteger = 1;
        Boolean bookIsAlreadyInList = false;
        int tempIndex = 0;

        // Find out if the book is already in the customer's order.
        // This condition is meant to ensure that the list has at least one object within it before we use the get() method.
        System.out.println(bookList.size());
        if (bookList.size() > 0) {
        
            for (int i = 0; i < bookList.size(); i++){
                // System.out.println(bookList.get(i).equals(bookOject));
                if (bookList.get(i) == (bookOject)) {
                    bookIsAlreadyInList = true;
                    tempIndex = i; // gets the index position of the found item for later use
                    break;
                }
            }
        }

        if (bookIsAlreadyInList == false) {
            bookList.add(bookOject);
            

            bookQuantityList.add(bookQuantityInteger);
            bookOject.bookStock.pop();
        }

        // Else add another amount to its order amount
        else if(bookIsAlreadyInList == true){ // keep this condition like this so that the condition is clear
            int sumOfOldQuantityAndNewQuantity = bookQuantityList.get(tempIndex) + bookQuantityInteger;

            bookQuantityList.set(tempIndex, sumOfOldQuantityAndNewQuantity);
            bookOject.bookStock.pop();
        }

        this.sortBookListByPrice();
    }
    public void dropOrder(){
        // return undelivered book first
        for (int i = 0; i < bookList.size(); i++){
            bookList.get(i).restock(bookQuantityList.get(i));
        }

        this.bookList = new AbstractListADT<>();
        this.bookQuantityList = new AbstractListADT<>();
    }

    @Override
    public String toString(){
        String res = "";
        res += this.ID;
        return res;
    }

}
