package PizzaStore;

/**
 Creates a Deluxe type pizza.
 @author Jiebin Liang, Anthony Mathis
 */
public class Deluxe extends Pizza{
    private static final double SM_PRICE = 14.99;
    private static final double MD_PRICE = 16.99;
    private static final double LG_PRICE = 18.99;

    /**
     Creates a pizza that is Deluxe flavored.
     @param crust used depending on Chicago or New York Style.
     */
    public Deluxe(Crust crust){
        setCrust(crust);
        initializeToppings();
        add(Topping.Sausage);
        add(Topping.Pepperoni);
        add(Topping.Green_Pepper);
        add(Topping.Onion);
        add(Topping.Mushroom);
    }

    /**
     Calculates the price of the pizza depending on the size.
     @return the price of the pizza.
     */
    @Override
    public double price() {
        if(getSize() == Size.SMALL){
            return SM_PRICE;
        }
        if(getSize() == Size.MEDIUM) {
            return MD_PRICE;
        }
        return LG_PRICE;
    }
}
