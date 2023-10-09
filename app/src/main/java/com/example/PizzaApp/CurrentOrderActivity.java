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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import PizzaStore.Pizza;


/**
 Creates the activity for the current order screen
 @author Jiebin Liang, Anthony Mathis
 */
public class CurrentOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final double SALES_TAX = 0.06625;
    private ListView currentOrderView;
    private List<String> allList = new ArrayList<String>();
    private ArrayAdapter<String> listViewAdapter;
    private Button placeOrderButton;
    private Button clearOrderButton;
    private TextView currentOrdersText;
    private TextView currentOrderText;
    private TextView subtotalText;
    private TextView totalPriceText;
    private TextView salesTaxText;

    /**
     Setups up all the text fields and adds the String value
     */
    private void setUpStrings(){
        currentOrdersText = findViewById(R.id.current_order);
        currentOrdersText.setText(R.string.current_order_text);
        currentOrderText = findViewById(R.id.current_order_text);
        int currentOrderNum = MainActivity.storeOrder.getCurrentOrder().getSerial();
        currentOrderText.setText(getResources().getString(R.string.num_of_orders_text)+ currentOrderNum);
        subtotalText = findViewById(R.id.sub_total_text);
        subtotalText.setText(getResources().getString(R.string.subtotal_text) + String.format("$%.2f",getSubtotal()));
        salesTaxText = findViewById(R.id.sales_tax_text);
        salesTaxText.setText(getResources().getString(R.string.sales_tax_text) + String.format("$%.2f",getSalesTax()));
        totalPriceText = findViewById(R.id.total_text);
        totalPriceText.setText(getResources().getString(R.string.total_price_tax) + String.format("$%.2f",getTotalPrice()));
    }

    /**
     Adds orders to list to be displayed
     */
    private void addToList(){
        for(int i = 0; i < MainActivity.storeOrder.getCurrentOrder().getItems().size(); i++){
            allList.add(MainActivity.storeOrder.getCurrentOrder().getItems().get(i).toString());
        }
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
     Calculates the sales tax of the current order.
     @return the sales tax
     */
    private double getSalesTax(){
        double subtotal = getSubtotal();
        double tax = subtotal * SALES_TAX;
        return tax;
    }

    /**
     Calculates the total price of the current order.
     @return the sales tax
     */
    private double getTotalPrice(){
        double subtotal = getSubtotal();
        double tax = subtotal * SALES_TAX;
        double price = subtotal + tax;
        return price;
    }

    /**
     Calculates the subtotal of the current order.
     @return the sales tax
     */
    private double getSubtotal(){
        double subtotal = 0;
        for(Pizza pizza : MainActivity.storeOrder.getCurrentOrder().getItems()){
            subtotal += pizza.price();
        }
      return subtotal;
    }

    /**
     Sets up the activity for it to be used.
     @param savedInstanceState to restore the previous state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_current_order);
        currentOrderView = findViewById(R.id.current_order_list);
        currentOrderView.setOnItemClickListener(this);
        addToList();
        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allList);
        currentOrderView.setAdapter(listViewAdapter);
        configurePlaceOrderButton();
        configureClearButton();
        setUpStrings();
    }

    /**
     Determines the outcome when you click a pizza in the order.
     @param adapterView the adapter for the list view.
     @param view within the adapter.
     @param i position of element that was clicked on in the listview.
     @param l the row id of the element that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        String selectedItem = adapterView.getAdapter().getItem(i).toString();
        alert.setTitle(R.string.delete_action);
        alert.setPositiveButton(R.string.no_text, (dialog, which) ->
                Toast.makeText(getApplicationContext(), R.string.cancelled_action, Toast.LENGTH_LONG).show()
        ).setNegativeButton(R.string.yes_text, (dialog, which) -> {
            allList.remove(selectedItem);
            MainActivity.storeOrder.getCurrentOrder().getItems().remove(i);
            setUpStrings();
            listViewAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), R.string.success_action, Toast.LENGTH_LONG).show();
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     Configures the clear button and its alert.
     */
    private void configureClearButton() {
        clearOrderButton = findViewById(R.id.clear_order_button);
        clearOrderButton.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(CurrentOrderActivity.this)
                    .setTitle(R.string.clear_action)
                    .setPositiveButton(R.string.no_text, (dialog, which) ->
                            Toast.makeText(getApplicationContext(), R.string.cancel_order_action, Toast.LENGTH_LONG).show())
                    .setNegativeButton(R.string.yes_text, (dialog, which) -> {
                        listViewAdapter.notifyDataSetChanged();
                        if (MainActivity.storeOrder.getCurrentOrder().getItems().size() == 0) {
                            Toast.makeText(getApplicationContext(), R.string.error_clear_action, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.success_action, Toast.LENGTH_LONG).show();
                            MainActivity.storeOrder.getCurrentOrder().getItems().clear();
                            finish();
                        }
                    });
            alert.show();
        });
    }

    /**
     Configures the place order button and its alert.
     */
    private void configurePlaceOrderButton(){
        placeOrderButton = findViewById(R.id.place_order_button);
        placeOrderButton.setOnClickListener(view -> {
                AlertDialog.Builder alert = new AlertDialog.Builder(CurrentOrderActivity.this)
                        .setTitle(R.string.place_action)
                        .setPositiveButton(R.string.no_text,(dialog,id) -> {
                            Toast.makeText(getApplicationContext(), R.string.cancel_order_action, Toast.LENGTH_LONG).show();
                        }).setNegativeButton(R.string.yes_text,(dialog,id) -> {
                                listViewAdapter.notifyDataSetChanged();
                                if(MainActivity.storeOrder.getCurrentOrder().getItems().size() == 0){
                                    Toast.makeText(getApplicationContext(),R.string.error_cancel_order, Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(getApplicationContext(), R.string.success_action, Toast.LENGTH_LONG).show();
                                    MainActivity.storeOrder.startNewOrder();
                                    finish();
                                }
                        });
                alert.show();
        });
    }
}
