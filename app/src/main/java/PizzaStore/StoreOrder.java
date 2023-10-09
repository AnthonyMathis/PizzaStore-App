package PizzaStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 An object where all orders for the store are added.
 @author Jiebin Liang, Anthony Mathis
 */
public class StoreOrder implements Customizable {
    private ArrayList<Order> orders;
    private int serialCounter;

    /**
     Creates an object where all orders for the store are added.
     */
    public StoreOrder(){
        orders = new ArrayList<>();
        serialCounter = 0;
    }

    /**
     Creates an order object that creates a new order.
     */
    public void startNewOrder(){
        orders.add(new Order(++serialCounter));
    }

    /**
     Get all the orders from the store
     @return a list of all the orders a store have received.
     */
    public List<Order> getOrders() {
        return orders.subList(0, orders.size() - 1);
    }

    /**
     Gets an order by the order number.
     @param index order number that you want to find.
     @return the order that matches the order number, null if the order does not match.
     */
    public Order getByOrderNumber(int index){
        for(Order order : orders){
            if(order.getSerial() == index){
                return order;
            }
        }
        return null;
    }

    /**
     Gets the current order being worked on.
     @return the current order.
     */
    public Order getCurrentOrder(){
        return orders.get(orders.size() - 1);
    }

    /**
     Adds an order to the overall store orders.
     @param obj new order being added.
     @return true if added successfully, false if not added.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            orders.add((Order) obj);
            return true;
        }
        return false;
    }

    /**
     Removes an order to the overall store orders.
     @param obj new order being removed.
     @return true if removed successfully, false if not removed.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            return orders.remove((Order) obj);
        }
        return false;
    }

    /**
     Exports all the store orders.
     @return true if exported successfully, false if not.
     */
    public boolean export(){
        try {
            File file = new File("OrderExport.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter("OrderExport.txt");
            for(Order order : getOrders()){
                writer.write(String.format("---Order %d---\n", order.getSerial()));
                double price = 0.0;
                for(Pizza pizza : order.getItems()){
                    writer.write(pizza.toString() + "\n");
                    price += pizza.getPrice();
                }
                writer.write(String.format("Order total: $%.2f\n", price));
            }
            writer.close();
            return true;
        } catch(IOException e) {
            return false;
        }
    }
}
