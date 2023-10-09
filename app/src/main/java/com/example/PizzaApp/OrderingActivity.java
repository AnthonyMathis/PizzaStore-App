package com.example.PizzaApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import PizzaStore.ChicagoPizza;
import PizzaStore.NYPizza;
import PizzaStore.Pizza;
import PizzaStore.PizzaFactory;
import PizzaStore.Size;

/**
 Creates the ordering activity for both NY and Chicago style pizza.
 @author Jiebin Liang, Anthony Mathis
 */
public class OrderingActivity extends AppCompatActivity {
    private ArrayList<Pizza> pizzaList = new ArrayList<>();
    private int[] pizzaImages = {R.drawable.deluxe, R.drawable.bbqchicken, R.drawable.meatzza,
            R.drawable.byopizza};
    private PizzaFactory pizzaFactory;
    private RadioGroup radioGroup;


    /**
     Initializes the activity based on the pizzaType given.
     Sets up the RecyclerView and radio buttons for display.
     @param savedInstanceState to restore the previous state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("pizzaType").equals("NY")){
            pizzaFactory = new NYPizza();
            setTitle(R.string.ny_title);
        }
        else{
            pizzaFactory = new ChicagoPizza();
            setTitle(R.string.chicago_title);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_pizza_ordering);
        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener((radioGroup, i) ->
                changeSize((String)((RadioButton)findViewById(i)).getText()));
        RecyclerView rcview = findViewById(R.id.pizza_menu);
        setupMenuItems();
        PizzaMenuAdapter adapter = new PizzaMenuAdapter(this, pizzaList, pizzaImages, this);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     Finishes the activity if the back button is selected.
     @param item MenuItem which is selected
     @return result of the superclass
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     Adds all the pizza options to be displayed in the RecyclerView.
     Sets default size to small.
     */
    private void setupMenuItems() {
        pizzaList.add(pizzaFactory.createDeluxe());
        pizzaList.add(pizzaFactory.createBBQChicken());
        pizzaList.add(pizzaFactory.createMeatzza());
        pizzaList.add(pizzaFactory.createBuildYourOwn());
        changeSize("SMALL");
    }

    /**
     Changes size for all the pizza flavors presented.
     @param size size to change to.
     */
    private void changeSize(String size){
        for(Pizza pizza : pizzaList){
            pizza.setSize(Size.valueOf(size.toUpperCase()));
        }
    }
}