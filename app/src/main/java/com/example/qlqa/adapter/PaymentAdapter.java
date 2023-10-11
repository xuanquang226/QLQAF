package com.example.qlqa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlqa.R;
import com.example.qlqa.model.DishOrder;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {
    private Context mContext;
    private List<DishOrder> dishOrderList;


    public PaymentAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setDishOrderList(List<DishOrder> dishOrderList){
        this.dishOrderList = dishOrderList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_payment_layout, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        DishOrder dishOrder = dishOrderList.get(position);
        holder.tvNameDish.setText(dishOrder.getName());
        holder.tvQuantityDish.setText(String.valueOf((dishOrder.getQuantity())));
        holder.tvPriceDish.setText(String.valueOf(dishOrder.getPrice()));
        if(dishOrder.getNote() != null){
            holder.tvNoteDish.setText(dishOrder.getNote());
        }else{
            holder.tvNoteDish.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return dishOrderList.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameDish, tvQuantityDish, tvPriceDish, tvNoteDish;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameDish = itemView.findViewById(R.id.tv_dish_item);
            tvQuantityDish = itemView.findViewById(R.id.tv_quantity_dish);
            tvPriceDish = itemView.findViewById(R.id.tv_price_dish);
            tvNoteDish = itemView.findViewById(R.id.tv_note);
        }
    }
}
