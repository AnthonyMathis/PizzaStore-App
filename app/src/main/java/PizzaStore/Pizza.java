package PizzaStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Creates a new pizza.
 @author Jiebin Liang, Anthony Mathis
 */
public abstract class Pizza implements Customizable {
    private static final List<Crust> nyCrusts = Arrays.asList(Crust.Brooklyn, Crust.Thin, Crust.Hand_Tossed);
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     Calculates the price of the pizza depending on the size.
     @return the price of the pizza.
     */
    public abstract double price();

    /**
     Sets the crust of the pizza.
     @param crust that be used.
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     Gets the type of crust of a specific pizza.
     @return the crust of a pizza.
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     Sets the size of the pizza.
     @param size being either Small, Medium or Large.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     Gets the size of a specific pizza.
     @return the size of the pizza.
     */
    public Size getSize(){
        return this.size;
    }

    /**
     Sets up toppings to be added to a pizza.
     */
    public void initializeToppings() {
        this.toppings = new ArrayList<>();
    }

    /**
     Gets the toppings of a specific pizza.
     @return an ArrayList of Toppings that is on the pizza.
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }
    /**
     Gets the price of a single pizza.
     @return the price of a single pizza.
     */
    public double getPrice(){
       return price();
    }

    /**
     Gets the number of toppings that a pizza has.
     @return the number of toppings on the pizza.
     */
    public int numOfToppings() {
        return toppings.size();
    }

    /**
     Adds a topping to the current pizza
     @param obj new topping being added.
     @return true if added successfully, false if not added.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Topping){
            toppings.add((Topping) obj);
            return true;
        }
        return false;
    }

    /**
     Removes a topping from the current pizza.
     @param obj topping being removed.
     @return true if removed successfully, false if not removed.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Topping){
            return toppings.remove((Topping) obj);
        }
        return false;
    }

    /**
     Returns the information about a specific pizza.
     @return the pizza style, crust, all the toppings, price and size of a pizza as a string.
     */
    @Override
    public String toString(){
        String style;
        if(nyCrusts.contains(crust)){
            style = "New York Style";
        } else {
            style = "Chicago Style";
        }

        if(toppings.size() != 0 ){
            return String.format("%s (%s - %s), %s, %s, $%.2f",
                    getClass().getSimpleName().replaceAll("(.)([A-Z][a-z])", "$1 $2"), style, crust,
                    toppings.toString().replaceAll("[\\[\\]]", ""), size,
                    price()).replaceAll("_", " ");
        }else{
            return String.format("%s (%s - %s), %s, $%.2f",
                    getClass().getSimpleName().replaceAll("(.)([A-Z][a-z])", "$1 $2"), style, crust, size,
                    price()).replaceAll("_", " ");
        }

    }
}