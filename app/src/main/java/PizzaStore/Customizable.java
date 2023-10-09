package PizzaStore;

/**
 Interface that adds must have methods to Order and StoreOrder.
 @author Jiebin Liang, Anthony Mathis
 */
public interface Customizable {

    /**
     Adds an object to an ArrayList
     @param obj new object being added
     @return true if added successfully, false if not added.
     */
    boolean add(Object obj);

    /**
     Removes an object to an ArrayList
     @param obj new object being removed
     @return true if removed successfully, false if not removed.
     */
    boolean remove(Object obj);
}
