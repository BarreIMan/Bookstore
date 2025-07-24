public class OrderManager {
    LinkedQueueADT<Order> orderQueue = new LinkedQueueADT<>();
    AbstractList<Order> deliveredOrderList = new AbstractListADT<Order>();

    public void enQueue(Order order){
        orderQueue.offer(order);
    }

    public Integer deliver(){
        Order deliveredOrder = orderQueue.poll();
        deliveredOrderList.add(deliveredOrder);
        return deliveredOrder.ID;
    }

    public void view(){
        System.out.println(orderQueue.viewQueue());
    }
    public String getOrderPosition(Order order){
        String res = "";

        int PositionInQueue =  orderQueue.findElement(order);
        // Look through the list
        for(int i = 0; i < deliveredOrderList.size(); i++){
            if (order == deliveredOrderList.get(i)) {
                res+= "Order with ID: ["+ order.ID +"] has been delivered";
                return res;
            }
        }
        // Look through the queue
       
        if (PositionInQueue == -1) {
            res += "Order with ID: ["+ order.ID +"] hasn't been sent to queue yet or does not exist.";
        }
        else{
            res += "Order with ID: ["+ order.ID +"] is in queue at position: " + PositionInQueue;
        }
        return res;
    }
}

