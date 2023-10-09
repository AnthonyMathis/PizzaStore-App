package PizzaStore;

/**
 Creates a New York style Pizza.
 @author Jiebin Liang, Anthony Mathis
 */
public class NYPizza implements PizzaFactory{

    /**
     Creates a New York Pizza and the flavor is Deluxe.
     @return a Deluxe, New York style Pizza.
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.Brooklyn);
    }

    /**
     Creates a New York Pizza and the flavor is Meatzza.
     @return a Meatzza, New York style Pizza.
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.Hand_Tossed);
    }

    /**
     Creates a New York Pizza and the flavor is BBQ Chicken.
     @return a BBQ Chicken, New York style Pizza.
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.Thin);
    }

    /**
     Creates a New York Pizza and the flavor is Build Your Own.
     @return a Build Your Own, New York style Pizza.
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.Hand_Tossed);
    }
}
