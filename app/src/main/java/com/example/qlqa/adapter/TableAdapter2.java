package com.example.qlqa.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlqa.R;
import com.example.qlqa.model.DinnerTable;

import java.util.List;

public class TableAdapter2 extends RecyclerView.Adapter<TableAdapter2.TableViewHolder> {
    private Context mContext;
    private List<DinnerTable> dinnerTableList;

    private ItemClickListener itemClickListener;

    public TableAdapter2(Context mContext, ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
    }

    public void setDinnerTableList(List<DinnerTable> dinnerTableList) {
        this.dinnerTableList = dinnerTableList;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_payment_table_layout, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        DinnerTable dinnerTable = dinnerTableList.get(position);
        holder.tvIdTable.setText(dinnerTable.getId() + "");
        if (dinnerTable.getIdOrder() != 0) {
            holder.tvStatusTable.setText("Đầy");
            holder.cardView.setBackgroundTintList(mContext.getColorStateList(R.color.moderate_blue));
        } else {
            holder.cardView.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onClick(dinnerTable);
        });
    }

    @Override
    public int getItemCount() {
        return dinnerTableList.size();
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIdTable, tvStatusTable;

        private CardView cardView;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdTable = itemView.findViewById(R.id.tv_id_table);
            tvStatusTable = itemView.findViewById(R.id.tv_status_table);
            cardView = itemView.findViewById(R.id.cv_payment_table_layout);
        }
    }

    public interface ItemClickListener {
        void onClick(DinnerTable dinnerTable);
    }
}
