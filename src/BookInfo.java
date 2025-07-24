public class BookInfo {
    private class bookCopy{
        public String name;
        public String author;
        public bookCopy(String name, String author){
            this.name = name;
            this.author = author;
        }
    }
    String name;
    String author;
    // int initialQuantity = 0;
    double price;
    BookInfo next;
    LinkedStackADT<bookCopy> bookStock;

    public BookInfo(String name, String author, int initialQuantity, double price){
        this.name = name;
        this.author = author;
        // this.initialQuantity = initialQuantity;
        this.price = price;
        this.next = null;
        this.bookStock = new LinkedStackADT<>();
        this.createInitialStack(initialQuantity);
    }

    private void createInitialStack(Integer initialAmountOfBooks){
        for(int i = 0; i < initialAmountOfBooks; i++){
            bookCopy newBookCopy = new bookCopy(this.name, this.author);
            bookStock.push(newBookCopy);
        }
    }

    public void restock(Integer amount){
        for(int i = 0; i < amount; i++){
            bookCopy newBookCopy = new bookCopy(this.name, this.author);
            bookStock.push(newBookCopy);
        }
    }

    @Override
        public String toString() {
            return(this.name + " - " + this.author + ", "+ "Price: " + this.price + " Quantity: " + this.bookStock.size());
    }
}