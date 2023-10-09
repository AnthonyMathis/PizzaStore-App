package com.example.PizzaApp;


import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import PizzaStore.BuildYourOwn;
import PizzaStore.Pizza;
import PizzaStore.Topping;

/**
 The Object layout to populate the rows in RecyclerView with.
 @author Jiebin Liang, Anthony Mathis
 */
public class PizzaItemHolder extends RecyclerView.ViewHolder {
    private static final int MAX_TOPPINGS = 7;
    private TextView pizzaName;
    private ImageView pizzaImage;
    private Pizza pizza;
    private Activity activity;

    /**
     Constructor for PizzaItemHolder.
     @param itemView is the view for each row.
     */
    public PizzaItemHolder(@NonNull View itemView) {
        super(itemView);
        pizzaName = itemView.findViewById(R.id.pizzaText);
        pizzaImage = itemView.findViewById(R.id.pizzaImage);
        setImageOnClick(itemView);
    }

    /**
     Set the onClickListener for the image on each row.
     Determines which alert to give depending on the pizza flavor.
     @param itemView The view to be clicked.
     */
    private void setImageOnClick(@NonNull View itemView) {
        itemView.setOnClickListener(view -> {
            if(pizza instanceof BuildYourOwn){
                makeToppingsAlert();
            }
            else{
                makeConfirmationAlert();
            }
        });
    }

    /**
     Presents the alert for the user to select toppings for BYO pizza.
     */
    private void makeToppingsAlert(){
        AlertDialog.Builder builder
                = new AlertDialog.Builder(itemView.getContext());
        builder.setTitle(R.string.topping_title);
        View customLayout
                = activity.getLayoutInflater()
                .inflate(
                        R.layout.toppings_dialogue,
                        null);
        LinearLayout linearLayout = customLayout.findViewById(R.id.toppings_list);
        int id = 0;
        for(Topping topping : Topping.values()){
            CheckBox checkBox = new CheckBox(itemView.getContext());
            checkBox.setText(topping.toString().replaceAll("_", " "));
            checkBox.setId(id++);
            checkBox.setOnClickListener(view -> updateToppings(linearLayout));
            linearLayout.addView(checkBox);
        }
        builder.setView(customLayout);
        builder.setPositiveButton("Next", (dialog, which) -> {
            setToppings(linearLayout);
            makeConfirmationAlert();
        });
        builder.setNegativeButton("Cancel",(dialog, which) -> {
            Toast.makeText(itemView.getContext(),
                    pizzaName.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     Counts the number of toppings selected to ensure it does not go over MAX_TOPPINGS.
     @param view the linear layout that contains all the checkboxes.
     */
    private void updateToppings(View view){
        int checkedCounter = 0;
        for(int i = 0; i < Topping.values().length; i++){
            if(((CheckBox)view.findViewById(i)).isChecked()){
                checkedCounter++;
            }
        }
        if(checkedCounter == MAX_TOPPINGS){
            for(int i = 0; i < Topping.values().length; i++){
                if(!((CheckBox)view.findViewById(i)).isChecked()){
                    view.findViewById(i).setEnabled(false);
                }
            }
        }
        else{
            for(int i = 0; i < Topping.values().length; i++){
                view.findViewById(i).setEnabled(true);
            }
        }
    }

    /**
     Adds the selected toppings to the BYO pizza.
     @param view the linear layout that contains all the checkboxes.
     */
    private void setToppings(View view){
        for(int i = 0; i < Topping.values().length; i++){
            if(((CheckBox)view.findViewById(i)).isChecked()){
                pizza.add(Topping.valueOf(((String) ((CheckBox) view.findViewById(i)).getText())
                        .replaceAll(" ", "_")));
            }
        }
    }

    /**
     Creates alert to serve as final confirmation before adding the pizza to order.
     */
    private void makeConfirmationAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
        alert.setTitle("Add to order");
        alert.setMessage(pizza.toString());
        alert.setPositiveButton("Add", (dialog, which) -> {
            MainActivity.storeOrder.getCurrentOrder().add(pizza);
            activity.finish();
            Toast.makeText(itemView.getContext(),
                    pizzaName.getText().toString() + " added.", Toast.LENGTH_LONG).show();
        }).setNegativeButton("Cancel", (dialog, which) -> {
            if(pizza instanceof BuildYourOwn){
                pizza.getToppings().clear();
            }
            Toast.makeText(itemView.getContext(),
                    pizzaName.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     Populates each row with the appropriate image and pizza name.
     @param pizza pizza to be presented.
     @param image image to be attached to the pizza.
     */
    public void setPizzaInfo(Pizza pizza, int image){
        this.pizza = pizza;
        pizzaName.setText(pizza.getClass().getSimpleName());
        pizzaImage.setImageResource(image);
    }

    /**
     Sets the activity variable so that the activity can be ended upon pizza selection.
     @param activity activity which includes the RecyclerView.
     */
    public void setActivity(Activity activity){
        this.activity = activity;
    }
}
