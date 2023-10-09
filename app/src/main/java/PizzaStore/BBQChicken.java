package PizzaStore;

/**
 Creates a BBQChicken flavored pizza
 @author Jiebin Liang, Anthony Mathis
 */
public class BBQChicken extends Pizza{
    private static final double SM_PRICE = 13.99;
    private static final double MD_PRICE = 15.99;
    private static final double LG_PRICE = 17.99;

    /**
     Creates a pizza with the flavor BBQChicken.
     @param crust used depending on Chicago or New York Style.
     */
    public BBQChicken(Crust crust){
        setCrust(crust);
        initializeToppings();
        add(Topping.BBQ_Chicken);
        add(Topping.Green_Pepper);
        add(Topping.Provolone);
        add(Topping.Cheddar);
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
