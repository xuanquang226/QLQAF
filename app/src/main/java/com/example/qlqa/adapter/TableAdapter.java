package com.example.qlqa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlqa.R;
import com.example.qlqa.model.DinnerTable;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private List<DinnerTable> lTable;
    private ItemClickListener onItemClickListener;

    private Context context;

    public TableAdapter(Context context, ItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<DinnerTable> lTable){
        this.lTable = lTable;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_order_layout, parent, false);
        return new TableViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        DinnerTable table = lTable.get(position);
        holder.tv_oNumber.setText(String.valueOf(table.getoNumber()));
        if(table.getOrder() != null){
            holder.tv_stt.setText("Đầy");
            holder.cardView.setBackgroundTintList(context.getColorStateList(R.color.moderate_blue));
        }else{
            holder.tv_stt.setText("Trống");
            holder.cardView.setBackgroundTintList(context.getColorStateList(R.color.gray));
        }
        holder.itemView.setOnClickListener(view -> {
            onItemClickListener.onItemClick(lTable.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return lTable.size();
    }


    public static class TableViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv_oNumber;
        private final TextView tv_stt;
        private CardView cardView;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_oNumber = itemView.findViewById(R.id.tv_onumber);
            tv_stt = itemView.findViewById(R.id.tv_status);
            cardView = itemView.findViewById(R.id.cv_order_layout);
        }
    }

    public interface ItemClickListener{
        void onItemClick(DinnerTable dinnerTable);
    }
}
