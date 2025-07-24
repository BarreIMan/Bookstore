import java.util.Scanner;

public class BookStore {
    public static void main(String[] args) throws Exception {
        System.out.println("Loading.");

        BookInfo book1 = new BookInfo("The Shadow over Innsmouth","H.P. Lovecraft",4,11.99);
        BookInfo book2 = new BookInfo("Plato's Republic","Plato",7,12.99);
        BookInfo book3 = new BookInfo("At the Mountains of Madness","H.P. Lovecraft",6,11.99);
        BookInfo book4 = new BookInfo("Echo","Akira",3,15.89);
        BookInfo book5 = new BookInfo("Moby Dick","Herman Melville", 10,12.99);

        AbstractListADT<BookInfo> Library = new AbstractListADT<>();
        BookInfo[] Books = {book1, book2, book3, book4, book5};
        
        for (int i = 0; i < Books.length; i++){
            Library.add(Books[i]);
        }

         System.out.println("Finished Loading.");

        System.out.println("Welcome to the local shady Bookstore!");

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name:");
        String userName = scanner.nextLine();

        System.out.print("Enter your address:");
        String userAddress = scanner.nextLine();

        Customer customer = new Customer(userName, userAddress);
        int currentOrderID = 1;
        Order currentOrder = new Order(customer.name, userAddress, currentOrderID);
        
        OrderManager orderManager = new OrderManager();
        currentOrderID++;
        
        customerOrderList customerOrderIDList = new customerOrderList(userName);
        customerOrderIDList.add(currentOrder);
        
        System.out.println("Hello " + customer.name);
        boolean userExited = false;

        // SAMPLE TEST
        // -----------------------------------------------------------------------------------------------------------------------
        Customer dummyCustomer = new Customer("Josh", "NYC");
        BookInfo dummyBook1 = new BookInfo("dummyBook", "John Doe", 5, 20.0);
        BookInfo dummyBook2 = new BookInfo("dummyBook", "John Doe", 5, 20.0);

        Order dummyOrder = new Order(dummyCustomer.name, dummyCustomer.address, currentOrderID);
        currentOrderID++;

        dummyOrder.orderBook(dummyBook1);
        dummyOrder.orderBook(dummyBook2);

        orderManager.enQueue(dummyOrder);
        orderManager.deliver();
        // -----------------------------------------------------------------------------------------------------------------------
        while (userExited == false) {
            System.out.println("===========================================MENU=============================================");
            System.out.println("Here are what you can do:");
            System.out.println("1. View library.\n2. Add to order.\n3. Finish order. (Puts your current order into queue and creates a new order) \n4. Drop order. (Renews current order)\n5. View queue. \n6. View your orders. \n7. View your order's status. \n8. Deliver order. \n9. Exit.");
            System.out.println("============================================================================================");
            System.out.println("Pick an option:");
            Integer userOption = scanner.nextInt();

            switch (userOption) {
                case 1:
                    System.out.println("=======================================LIBRARY==============================================");
                    for(int i = 0; i < Library.size(); i++){
                        System.out.println(i+1+". "+ Library.get(i));
                    }
                    System.out.println("============================================================================================");
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Enter the name of the book that you want to order:");
                    String bookName = scanner.nextLine();
                    Boolean bookFound = false;
                    BookInfo bookOjectFound = new BookInfo(userName, bookName, 0, 0); // Dummy bookInfo obj

                    for(int i = 0; i < Library.size(); i++){
                        if (Library.get(i).name.toLowerCase().equals(bookName.toLowerCase())) {
                            // System.out.println("found " + bookName + " !");
                            bookFound = true;
                            bookOjectFound = Library.get(i);
                            break;
                        }
                    }

                    if (bookFound == true && bookOjectFound.bookStock.size() > 0) {
                    // add to order
                    // System.out.println(bookOjectFound);
                        currentOrder.orderBook(bookOjectFound);
                    
                        System.out.println("========================================YOUR ORDER ID:" + currentOrder.ID +"======================================");
                        System.out.println("(Sorted by price from most expensive to least).");

                        for (int j = 0; j < currentOrder.bookList.size(); j++){
                            System.out.println(j+1+". " + currentOrder.bookList.get(j).name + " by " + currentOrder.bookList.get(j).author + " "+ currentOrder.bookList.get(j).price +" - " + currentOrder.bookQuantityList.get(j));
                        }

                        System.out.println("============================================================================================");
                    }
                    else if (bookOjectFound.bookStock.size() == 0) {
                        System.out.println("===========================================ERROR============================================");
                        System.out.println("No book in stock.");
                        System.out.println("============================================================================================");
                    }

                    else if (bookFound == false) {
                        System.out.println("===========================================ERROR============================================");
                        System.out.println("No book found.");
                        System.out.println("============================================================================================");
                    }
                    break;
                case 3:
                    // queues current order
                    // currentOrder.setID(bookOrderQueue.curretNodeID);
                    // bookOrderQueue.offer(currentOrder); // TODO: REPLACE
                    orderManager.enQueue(currentOrder);
                    // customerOrderIDList.add(currentOrder);

                    System.out.println("===========================================QUEUE SUCCESSFUL=================================");
                    System.out.println("Your order has been put into our queue!");
                    System.out.println("============================================================================================");

                    // opens a new order
                    
                    currentOrder = new Order(userName, userAddress, currentOrderID);
                    customerOrderIDList.add(currentOrder);
                    currentOrderID++;
                    break;
                case 4:
                    
                    currentOrder.dropOrder();
                    System.out.println("===========================================NEW ORDER========================================");
                    System.out.println("You have renewed your order!");
                    System.out.println("============================================================================================");
                    break;
                
                case 5:
                    // TODO: REPLACE
                    // TODO: FIGURE OUT WHY THE TO_STRING IS BAD
                    System.out.println("===========================================QUEUE============================================");
                    // System.out.println(bookOrderQueue.view());
                    orderManager.view();
                    System.out.println("============================================================================================");
                    break;
                case 6:
                    System.out.println("=======================================YOUR ORDER IDS======================================");
                    System.out.println(customerOrderIDList.view());
                    System.out.println("============================================================================================");
                    break;
                case 7:
                    Order customerOrder = new Order(userName, userAddress, -1); // temp order 
                    System.out.println("Enter your order ID: ");
                    scanner.nextLine();
                    int ID_Input = scanner.nextInt();
                    boolean customerOrderIDisValid = false; 

                    // Linear search
                    for(int i = 0; i < customerOrderIDList.orderList.size(); i++){
                        if (ID_Input == customerOrderIDList.orderList.get(i).ID) {
                            customerOrderIDisValid = true;
                            customerOrder = customerOrderIDList.orderList.get(i);
                            break;
                        }
                    }
                    // TODO: REPLACE
                    if (customerOrderIDisValid == true) {
                        System.out.println("=============================YOUR ORDER ["+ ID_Input +"] STATUS=======================================");
                        // System.out.println(bookOrderQueue.findOrderByOrderID(ID_Input).getStatusOrPositionInQueue());
                        
                        System.out.println(orderManager.getOrderPosition(customerOrder)); // the method name does not fully describe what it does
                        System.out.println("============================================================================================");
                        System.out.println();
                    }
                    else{
                        System.out.println("===========================================ERROR============================================");
                        System.out.println("ID not found or that ID was not your Order ID.");
                        System.out.println("===============================");
                    }
                    
                    break;
                case 8:
                    // TODO: REPLACE
                    System.out.println("===========================================DELIVERY============================================");
                    System.out.println("Order with ID [" + orderManager.deliver() + "] has been delivered.");
                    System.out.println("===============================================================================================");
                    break;
                case 9:
                    System.out.println("============================================================================================");
                    System.out.println("===========================================GOODBYE==========================================");
                    System.out.println("============================================================================================");
                    userExited = true;
                    break;
                default:
                    break;
            }
        }
    }
}
        
    
