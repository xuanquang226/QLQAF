package com.example.qlqa.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlqa.R;
import com.example.qlqa.model.DinnerTable;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private List<DinnerTable> lTable;
    private ItemClickListener onItemClickListener;

    public TableAdapter(ItemClickListener onItemClickListener) {
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
        if(table.isStt()){
            holder.tv_stt.setText("Đầy");
            holder.linearLayout.setBackgroundColor(R.color.grey);
        }else{
            holder.tv_stt.setText("Trống");
            holder.linearLayout.setBackgroundColor(R.color.back_ground);
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
        private LinearLayout linearLayout;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_oNumber = itemView.findViewById(R.id.tv_onumber);
            tv_stt = itemView.findViewById(R.id.tv_status);
            linearLayout = itemView.findViewById(R.id.lLayout);
        }
    }

    public interface ItemClickListener{
        void onItemClick(DinnerTable dinnerTable);
    }
}
