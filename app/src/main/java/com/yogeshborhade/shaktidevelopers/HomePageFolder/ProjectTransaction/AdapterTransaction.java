package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectTransaction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseTransaction;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.List;

/**
 * Created by admin on 26-05-2018.
 */

public class AdapterTransaction extends RecyclerView.Adapter<AdapterTransaction.MyViewHolder> {

    private List<DataBaseTransaction> dataBaseTransactionList;
    Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textTransactionID, textTransactionAmount, textTransactionGiveTake, textTransactionDate, textTransactionRemark;
        RelativeLayout imageViewDelet;

        public MyViewHolder(View view) {
            super(view);
            textTransactionID = (TextView) view.findViewById(R.id.mtextTransactionID);
            textTransactionAmount = (TextView) view.findViewById(R.id.mtexTotalTransactionAmount);
            textTransactionGiveTake = (TextView) view.findViewById(R.id.mtextTransactionGiveTake);
            textTransactionDate = (TextView) view.findViewById(R.id.mtextTransactionDate);
            textTransactionRemark = (TextView) view.findViewById(R.id.mtextTransactionRemark);
            imageViewDelet = (RelativeLayout) view.findViewById(R.id.mdelete_Transaction);

        }
    }


    public AdapterTransaction(List<DataBaseTransaction> dataBaseTransactionList) {
        this.dataBaseTransactionList = dataBaseTransactionList;
    }

    @Override
    public AdapterTransaction.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_transaction, parent, false);

        return new AdapterTransaction.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterTransaction.MyViewHolder holder, final int position) {
        final DataBaseTransaction dataBaseTransaction = dataBaseTransactionList.get(position);

     //   holder.textTransactionID.setText(String.valueOf(dataBaseTransaction.getTransaction_id()));
        holder.textTransactionID.setText(String.valueOf(position + 1));
        holder.textTransactionAmount.setText(dataBaseTransaction.getTransaction_amount());
        holder.textTransactionGiveTake.setText(dataBaseTransaction.getTransaction_money_give_take());
        holder.textTransactionDate.setText(dataBaseTransaction.getTransaction_date());
        holder.textTransactionRemark.setText(dataBaseTransaction.getTransaction_cash_remark());
        holder.imageViewDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletTransaction(view, dataBaseTransaction.getTransaction_id());
                dataBaseTransactionList.remove(position);
                notifyDataSetChanged();

            }
        });
    }


    @Override
    public int getItemCount() {
        return dataBaseTransactionList.size();
    }

    public void deletTransaction(View view, int transactionID) {

        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

        long id = databaseHelper.TransactionDelete(transactionID);

        if (id == 0) {
            Toast.makeText(view.getContext(), "There is Some Errors", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(view.getContext(), "deleted", Toast.LENGTH_SHORT).show();

        }

    }

}






