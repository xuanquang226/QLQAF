package com.example.qlqa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlqa.R;
import com.example.qlqa.model.DinnerTable;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private Context mContext;
    private List<DinnerTable> lTable;

    public TableAdapter(Context context) {
        this.mContext = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<DinnerTable> lTable){
        this.lTable = lTable;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ltb_layout, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        DinnerTable table = lTable.get(position);
        holder.tv_oNumber.setText(String.valueOf(table.getoNumber()));
        if(table.isStt()){
            holder.tv_stt.setText("Đầy");
        }else{
            holder.tv_stt.setText("Trống");
        }
    }

    @Override
    public int getItemCount() {
        return lTable.size();
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv_oNumber;
        private final TextView tv_stt;


        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_oNumber = itemView.findViewById(R.id.tv_onumber);
            tv_stt = itemView.findViewById(R.id.tv_status);
        }
    }
}
