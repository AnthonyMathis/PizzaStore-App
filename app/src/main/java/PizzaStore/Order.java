package PizzaStore;

import java.io.Serializable;
import java.util.ArrayList;

/**
 Creates an order for a customer.
 @author Jiebin Liang, Anthony Mathis
 */
public class Order implements Customizable {
    private int serial;
    private ArrayList<Pizza> items;

    /**
     Creates a new order.
     @param serial the order number for the new order.
     */
    public Order(int serial){
        this.serial = serial;
        items = new ArrayList<>();
    }

    /**
     Gets the serial number of the current order.
     @return the current serial number.
     */
    public int getSerial(){
        return serial;
    }

    /**
     Gets all the pizzas in the current order
     @return an Arraylist of pizzas.
     */
    public ArrayList<Pizza> getItems(){
        return items;
    }

    /**
     Adds a pizza to the current order.
     @param obj new pizza being added.
     @return true if added successfully, false if not added.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Pizza){
            return items.add((Pizza) obj);
        }
        return false;
    }

    /**
     Removes a pizza from the current order.
     @param obj pizza being removed.
     @return true if removed successfully, false if not removed.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Pizza){
            return items.remove((Pizza) obj);
        }
        return false;
    }
}
