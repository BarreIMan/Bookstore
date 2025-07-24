public class customerOrderList {
    AbstractListADT<Order> orderList = new AbstractListADT<>();
    String customerName;

    public customerOrderList(String customerName){
        this.customerName = customerName;
    }

    public void add(Order order){
        this.orderList.add(order);
    }

    public String view(){
        String res = "";
        int ORDER_LIST_SIZE = orderList.size();
         
        for(int i = 0; i < ORDER_LIST_SIZE; i++){
            Order tempOrder = orderList.get(i);
            res += "Order ID: "+ orderList.get(i).ID + "\n";
            for (int j = 0; j < tempOrder.bookList.size(); j++) {
                res += tempOrder.bookList.get(j).name + ", ";
                res += tempOrder.bookQuantityList.get(j);
                res += "\n";
            }
        }
        return res;
    }
}
