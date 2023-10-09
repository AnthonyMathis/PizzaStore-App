package com.example.PizzaApp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import PizzaStore.Pizza;

/**
 Populates the RecyclerView with templates of PizzaItemHolder.
 @author Jiebin Liang, Anthony Mathis
 */
class PizzaMenuAdapter extends RecyclerView.Adapter<PizzaItemHolder> {
    private Context context;
    private ArrayList<Pizza> pizzaList;
    private int[] images;
    private Activity myActivity;

    /**
     Creates the adapter with the given parameters.
     @param context The context from which the view is created.
     @param pizzaList The list of pizzas to populate the views.
     @param images The array of images to populate the views.
     @param myActivity The activity which calls the adapter.
     */
    public PizzaMenuAdapter(Context context, ArrayList<Pizza> pizzaList, int[] images,
                            Activity myActivity) {
        this.context = context;
        this.pizzaList = pizzaList;
        this.myActivity = myActivity;
        this.images = images;
    }

    /**
     This method will inflate the row layout for the items in the RecyclerView
     @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     @param viewType The view type of the new View.
     @return PizzaItemHolder created from the given parameters.
     */
    @NonNull
    @Override
    public PizzaItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new PizzaItemHolder(view);
    }

    /**
     Assign data values for each row according to their "position" (index) when the item becomes
     visible on the screen.
     @param holder The instance of PizzaItemHolder.
     @param position The index of the item in the list of Pizzas.
     */
    @Override
    public void onBindViewHolder(@NonNull PizzaItemHolder holder, int position) {
        holder.setPizzaInfo(pizzaList.get(position), images[position]);
        holder.setActivity(myActivity);
    }

    /**
     Get the number of items in the ArrayList.
     @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }
}
