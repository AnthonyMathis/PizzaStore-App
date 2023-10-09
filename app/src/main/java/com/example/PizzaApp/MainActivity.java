package com.example.PizzaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import PizzaStore.StoreOrder;

/**
 Creates the activity for the main screen of the app
 @author Jiebin Liang, Anthony Mathis
 */
public class MainActivity extends AppCompatActivity {
    private View new_york_card;
    private View chicago_card;
    private View shopping_cart_card;
    private View store_orders_card;
    public static StoreOrder storeOrder;

    /**
     Sets up the activity for it to be used.
     @param savedInstanceState to restore the previous state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeOrder = new StoreOrder();
        storeOrder.startNewOrder();
        setContentView(R.layout.activity_main);
        allOnClicks();
    }

    /**
     Sets onClickListeners for all the cards in the main view.
     */
    private void allOnClicks(){
        new_york_card = findViewById(R.id.new_york_card);
        new_york_card.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,OrderingActivity.class);
            intent.putExtra("pizzaType", "NY");
            startActivity(intent);
        });
        chicago_card = findViewById(R.id.chicago_card);
        chicago_card.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,OrderingActivity.class);
            intent.putExtra("pizzaType", "Chicago");
            startActivity(intent);
        });
        store_orders_card = findViewById(R.id.store_orders_card);
        store_orders_card.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,StoreOrdersActivity.class);
            startActivity(intent);
        });
        shopping_cart_card = findViewById(R.id.shopping_cart_card);
        shopping_cart_card.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,CurrentOrderActivity.class);
            startActivity(intent);

        });
    }
}