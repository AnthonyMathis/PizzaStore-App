package PizzaStore;

/**
 Creates a Meatzza type pizza.
 @author Jiebin Liang, Anthony Mathis
 */
public class Meatzza extends Pizza{
    private static final double SM_PRICE = 15.99;
    private static final double MD_PRICE = 17.99;
    private static final double LG_PRICE = 19.99;

    /**
     Creates a pizza that is Meatzza flavored.
     @param crust used depending on Chicago or New York Style.
     */
    public Meatzza(Crust crust){
        setCrust(crust);
        initializeToppings();
        add(Topping.Sausage);
        add(Topping.Pepperoni);
        add(Topping.Beef);
        add(Topping.Ham);
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
