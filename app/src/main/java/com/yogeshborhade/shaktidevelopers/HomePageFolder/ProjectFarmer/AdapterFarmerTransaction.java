package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectFarmer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithoutExponential;

public class AdapterFarmerTransaction extends RecyclerView.Adapter<AdapterFarmerTransaction.MyViewHolder> {

    private List<DataBaseTransaction> dataBaseTransactionList;
    Context context;

    String BookID, ProjectID, FarmerID;
    double updateNewGivenAmount, updateNewPendingAmount;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textTransactionID, textTransactionAmount, textTransactionGiveTake, textTransactionDate, textTransactionRemark, textPaymentMode;
        RelativeLayout imageViewDelet;

        public MyViewHolder(View view) {
            super(view);
            textTransactionID = (TextView) view.findViewById(R.id.mtextTransactionID);
            textTransactionAmount = (TextView) view.findViewById(R.id.mtexTotalTransactionAmount);
            textTransactionGiveTake = (TextView) view.findViewById(R.id.mtextTransactionGiveTake);
            textTransactionDate = (TextView) view.findViewById(R.id.mtextTransactionDate);
            textTransactionRemark = (TextView) view.findViewById(R.id.mtextTransactionRemark);
            textPaymentMode = (TextView) view.findViewById(R.id.mtextTransactionPaymentType);


            imageViewDelet = (RelativeLayout) view.findViewById(R.id.mdelete_Transaction);

        }
    }


    public AdapterFarmerTransaction(List<DataBaseTransaction> dataBaseTransactionList) {
        this.dataBaseTransactionList = dataBaseTransactionList;
    }

    @Override
    public AdapterFarmerTransaction.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_transaction, parent, false);

        return new AdapterFarmerTransaction.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterFarmerTransaction.MyViewHolder holder, final int position) {
        final DataBaseTransaction dataBaseTransaction = dataBaseTransactionList.get(position);
//        holder.textTransactionID.setText(String.valueOf(dataBaseTransaction.getTransaction_id()));
        holder.textTransactionID.setText(String.valueOf(position + 1));

        holder.textTransactionAmount.setText(dataBaseTransaction.getTransaction_amount());
        holder.textTransactionGiveTake.setText(dataBaseTransaction.getTransaction_money_give_take());
        holder.textTransactionDate.setText(dataBaseTransaction.getTransaction_date());
        holder.textTransactionRemark.setText(dataBaseTransaction.getTransaction_cash_remark());
        holder.textPaymentMode.setText(dataBaseTransaction.getTransaction_type());

        holder.imageViewDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProjectID = dataBaseTransaction.getTransaction_project_id();
                FarmerID = dataBaseTransaction.getTransaction_farmer_id();

                updateNewGivenAmount = Double.valueOf(FarmerTransactionDetails.GivenAmount) - Double.valueOf(dataBaseTransaction.getTransaction_amount());
                updateNewPendingAmount = Double.valueOf(FarmerTransactionDetails.PendingAmount) + Double.valueOf(dataBaseTransaction.getTransaction_amount());

                FarmerTransactionDetails.GivenAmount = String.valueOf(numberWithoutExponential.format(updateNewGivenAmount));
                FarmerTransactionDetails.PendingAmount = String.valueOf(numberWithoutExponential.format(updateNewPendingAmount));

                Log.e("Updated Given Amount", String.valueOf(updateNewGivenAmount));
                Log.e("Updated Pending Amount", String.valueOf(updateNewPendingAmount));

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
         //   Toast.makeText(view.getContext(), "There is Some Errors", Toast.LENGTH_SHORT).show();

        } else {

           // Toast.makeText(view.getContext(), "deleted", Toast.LENGTH_SHORT).show();

        }

        long UpdateFarmer = databaseHelper.FarmerUpdateAmount(ProjectID, FarmerID, String.valueOf(numberWithoutExponential.format(updateNewGivenAmount)), String.valueOf(numberWithoutExponential.format(updateNewPendingAmount)));
        if (UpdateFarmer == 0) {
            //Toast.makeText(view.getContext(), "Not Inserted", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(view.getContext(), "Inserted Farmer ", Toast.LENGTH_SHORT).show();
        }

        // ((Activity)view.getContext()).finish();
    }

}







