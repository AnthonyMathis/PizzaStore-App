package PizzaStore;

/**
 Creates a Build Your Own type pizza
 @author Jiebin Liang, Anthony Mathis
 */
public class BuildYourOwn extends Pizza{
    private static final double SM_PRICE = 8.99;
    private static final double MD_PRICE = 10.99;
    private static final double LG_PRICE = 12.99;
    private static final double EXTRA_TOPPING_PRICE = 1.59;

    /**
     Creates a pizza that the customer can add their own toppings
     @param crust used depending on Chicago or New York Style.
     */
    public BuildYourOwn(Crust crust){
        setCrust(crust);
        initializeToppings();
    }

    /**
     Calculates the price of the pizza depending on the size.
     @return the price of the pizza.
     */
    @Override
    public double price() {
        double cost;
        if(getSize() == Size.SMALL){
            cost = SM_PRICE;
        }
        else if(getSize() == Size.MEDIUM) {
            cost = MD_PRICE;
        }
        else {
            cost = LG_PRICE;
        }
        cost += EXTRA_TOPPING_PRICE * numOfToppings();
        return cost;
    }
}
