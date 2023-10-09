package PizzaStore;

/**
 Interface that adds must have methods when creating a specific style pizza.
 @author Jiebin Liang, Anthony Mathis
 */
public interface PizzaFactory {

    /**
     Creates a Pizza and the flavor is Deluxe.
     @return a Deluxe Pizza.
     */
    Pizza createDeluxe();

    /**
     Creates a Pizza and the flavor is Meatzza.
     @return a Meatzza Pizza.
     */
    Pizza createMeatzza();

    /**
     Creates a Pizza and the flavor is BBQ Chicken.
     @return a BBQ Chicken Pizza.
     */
    Pizza createBBQChicken();

    /**
     Creates a Pizza and the flavor is Build Your Own.
     @return a Build Your Own Pizza.
     */
    Pizza createBuildYourOwn();
}
