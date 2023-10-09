package com.example.PizzaApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import PizzaStore.Order;
import PizzaStore.Pizza;

/**
 Creates the activity for the store orders screen
 @author Jiebin Liang, Anthony Mathis
 */
public class StoreOrdersActivity extends AppCompatActivity{
    private static final double SALES_TAX = 0.06625;
    private Button cancelOrderButton;
    private List<String> pizzaList = new ArrayList<String>();
    private List<String> ordersList = new ArrayList<String>();
    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private ListView storeOrdersView;
    private ArrayAdapter<String> listViewAdapter;
    private TextView currentOrderText;
    private TextView ordersText;
    private TextView totalPriceText;

    /**
     Everytime the activity is called, this function creates the activity.
     @param savedInstanceState creates
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_store_orders);
        storeOrdersView = findViewById(R.id.store_orders_list);
        spinner = findViewById(R.id.orders_spinner);
        spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, ordersList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             @param adapterView the adapter for the spinner.
             @param view within the adapter.
             @param i position of element that was clicked on in the spinner.
             @param l the row id of the element that was clicked.
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pizzaList.clear();
                listViewAdapter.notifyDataSetChanged();
                int spinnerValue = Integer.parseInt(spinner.getSelectedItem().toString());
                addSingleOrder(spinnerValue);
                totalPriceText = findViewById(R.id.order_total);
                totalPriceText.setText(getResources().getString(R.string.order_total) + String.format("$%.2f",getTotalPrice()));
            }

            /**
             Called when nothing is selected.
             @param adapterView the adapter for the spinner.
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addOrdersToList();
        configureElements();
        listViewAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pizzaList);
        storeOrdersView.setAdapter(listViewAdapter);
    }


    /**
     Calculates the total price of the order being shown.
     @return the total price.
     */
    private double getTotalPrice(){
        double subtotal = 0;
        if(spinner.getSelectedItem() == null){
            return 0;
        }
        String spinnerValue = spinner.getSelectedItem().toString();
         for(Pizza pizza : MainActivity.storeOrder
                 .getByOrderNumber(Integer.parseInt(spinnerValue)).getItems()){
            subtotal += pizza.price();
        }
        subtotal += subtotal * SALES_TAX;
        return subtotal;
    }


    /**
     Adds all the pizzas of the order into the listview.
     @param orderNumber of the order that is being shown.
     */
    private void addSingleOrder(int orderNumber){
        for(int i = 0; i < MainActivity.storeOrder.
                getByOrderNumber(orderNumber).getItems().size(); i++){
            pizzaList.add(MainActivity.storeOrder.
                    getByOrderNumber(orderNumber).getItems().get(i).toString());
        }
        listViewAdapter.notifyDataSetChanged();
    }

    /**
     Adds all the available orders to the spinner.
     */
    private void addOrdersToList(){
        for(Order order : MainActivity.storeOrder.getOrders()){
            ordersList.add(String.valueOf(order.getSerial()));
        }
        spinnerAdapter.notifyDataSetChanged();
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
     Configures all the text elements and buttons on the screen.
     */
    private void configureElements() {
        cancelOrderButton = findViewById(R.id.cancel_order_button);
        cancelOrderButton.setText(getResources().getString(R.string.cancel_order));
        cancelOrderButton.setOnClickListener(view -> {
                createAlert(R.string.cancel_order_action);

        });
        currentOrderText = findViewById(R.id.order_num_text);
        currentOrderText.setText(getResources().getString(R.string.num_of_orders_text));
        ordersText = findViewById(R.id.orders_text);
        ordersText.setText(getResources().getString(R.string.orders_text));
        totalPriceText = findViewById(R.id.order_total);
        totalPriceText.setText(getResources().getString(R.string.order_total) + String.format("$%.2f",getTotalPrice()));
    }

    /**
     Creates an alert that pops up for the user.
     @param message that you want to be displayed.
     */
    private void createAlert(int message){
        AlertDialog.Builder alert = new AlertDialog.Builder(StoreOrdersActivity.this)
                .setTitle(message)
                .setPositiveButton(R.string.no_text, (dialog, which) ->
                    Toast.makeText(getApplicationContext()
                            , R.string.cancelled_action, Toast.LENGTH_LONG).show())
                .setNegativeButton(R.string.yes_text,(dialog, which) ->{
                       if(MainActivity.storeOrder.getOrders().size() == 0){
                           Toast.makeText(getApplicationContext()
                                   ,R.string.error_cancel_order, Toast.LENGTH_LONG).show();
                       }else {
                           Toast.makeText(getApplicationContext()
                                   , R.string.success_action, Toast.LENGTH_LONG).show();
                           int i = 0;
                           for(Order order : MainActivity.storeOrder.getOrders()){
                               i = order.getSerial();
                               break;
                           }
                           if(i == Integer.parseInt(spinner.getSelectedItem().toString())){
                               ordersList.remove(spinner.getSelectedItem().toString());
                               MainActivity.storeOrder
                                       .getOrders().remove(spinner.getSelectedItemPosition());
                               for(Order order : MainActivity.storeOrder.getOrders()){
                                   i = order.getSerial();
                                   break;
                               }
                           }else{
                               ordersList.remove(spinner.getSelectedItem().toString());
                               MainActivity.storeOrder
                                       .getOrders().remove(spinner.getSelectedItemPosition());
                           }
                           spinnerAdapter.notifyDataSetChanged();
                           pizzaList.clear();
                           listViewAdapter.notifyDataSetChanged();
                           if(MainActivity.storeOrder.getOrders().size() == 0){
                               finish();
                           }else{
                               addSingleOrder(i);
                               totalPriceText = findViewById(R.id.order_total);
                               spinner.setSelection(0);
                               totalPriceText.setText(getResources().
                                       getString(R.string.order_total) + String.format("$%.2f",getTotalPrice()));
                           }
                       }
                });
        alert.show();
    }
}