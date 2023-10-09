package PizzaStore;

/**
 Creates a Chicago style Pizza
 @author Jiebin Liang, Anthony Mathis
 */
public class ChicagoPizza implements PizzaFactory{

    /**
     Creates a Chicago Pizza and the flavor is Deluxe.
     @return a Deluxe Pizza, Chicago style Pizza.
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.Deep_Dish);
    }

    /**
     Creates a Chicago Pizza and the flavor is Meatzza.
     @return a Meatzza Pizza, Chicago style Pizza.
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.Stuffed);
    }

    /**
     Creates a Chicago Pizza and the flavor is BBQ Chicken.
     @return a BBQ Chicken, Chicago style Pizza.
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.Pan);
    }

    /**
     Creates a Chicago Pizza and the flavor is Build Your Own.
     @return a Build Your Own, Chicago style Pizza.
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.Pan);
    }


}
