package com.example.qlqa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlqa.R;
import com.example.qlqa.model.Payroll;
import com.example.qlqa.model.PayrollStaff;

import java.util.List;

public class PayrollStaffAdapter extends RecyclerView.Adapter<PayrollStaffAdapter.PayrollStaffViewHolder> {
    private List<PayrollStaff> payrollStaffList;
    private Context mContext;

    public PayrollStaffAdapter() {
    }

    public PayrollStaffAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setPayrollStaffList(List<PayrollStaff> payrollStaffList) {
        this.payrollStaffList = payrollStaffList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PayrollStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_payroll_layout, parent, false);
        return new PayrollStaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayrollStaffViewHolder holder, int position) {
        PayrollStaff payrollStaff = payrollStaffList.get(position);
        holder.tvName.setText(payrollStaff.getStaff().getNameStaff());
        holder.tvSTT.setText(String.valueOf(payrollStaff.getStaff().getIdStaff()));
        holder.tvSalary.setText(String.valueOf(payrollStaff.getSalary()));
        holder.tvCount.setText(String.valueOf(payrollStaff.getCount()));
    }

    @Override
    public int getItemCount() {
        if (payrollStaffList != null) {
            return payrollStaffList.size();
        }
        return 0;
    }

    public class PayrollStaffViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSTT, tvName, tvCount, tvSalary;

        public PayrollStaffViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSTT = itemView.findViewById(R.id.tv_stt);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCount = itemView.findViewById(R.id.tv_count);
            tvSalary = itemView.findViewById(R.id.tv_salary);
        }
    }

}
