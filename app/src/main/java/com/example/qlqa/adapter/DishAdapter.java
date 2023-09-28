package com.example.qlqa.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlqa.R;
import com.example.qlqa.model.Dish;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    private List<Dish> dishList;
    private ItemClickListener itemClickListener;
    public DishAdapter(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Dish> dishList){
        this.dishList = dishList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dish_layout, parent, false);
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        Dish dish = dishList.get(position);
        holder.tv_dish.setText(dish.getName());
        holder.tv_quantity_dish.setText(String.valueOf(dish.getQuantity()));
        holder.tv_price_dish.setText(String.valueOf(dish.getPrice()));

        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onClick(dishList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_dish;
        private TextView tv_quantity_dish;

        private TextView tv_price_dish;
        public DishViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_dish = (TextView) itemView.findViewById(R.id.tv_dish_item);
            tv_quantity_dish = (TextView) itemView.findViewById(R.id.tv_quantity_dish);
            tv_price_dish = (TextView) itemView.findViewById(R.id.tv_price_dish);
        }
    }

    public interface ItemClickListener{
        void onClick(Dish dish);
    }
}
