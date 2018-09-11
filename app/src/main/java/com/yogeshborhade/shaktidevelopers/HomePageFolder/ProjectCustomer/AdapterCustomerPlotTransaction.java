package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
/*import android.widget.Toast;*/

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseTransaction;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithComma;
import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithoutExponential;

public class AdapterCustomerPlotTransaction extends RecyclerView.Adapter<AdapterCustomerPlotTransaction.MyViewHolder> {

    private List<DataBaseTransaction> dataBaseTransactionList;
    Context context;
    String transactionAmount, newTotalAmount, newRemainingAmount;
    String BookID;
    double updateNewGivenAmount, updateNewPendingAmount;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textTransactionID, textTransactionAmount, textTransactionGiveTake, textTransactionDate, textTransactionRemark, textPaymentType, textPaymentNumber;
        RelativeLayout imageViewDelet;

        public MyViewHolder(View view) {
            super(view);
            textTransactionID = (TextView) view.findViewById(R.id.mtextTransactionID);
            textTransactionAmount = (TextView) view.findViewById(R.id.mtexTotalTransactionAmount);
            textTransactionGiveTake = (TextView) view.findViewById(R.id.mtextTransactionGiveTake);
            textTransactionDate = (TextView) view.findViewById(R.id.mtextTransactionDate);
            textTransactionRemark = (TextView) view.findViewById(R.id.mtextTransactionRemark);
            textPaymentType = (TextView) view.findViewById(R.id.mtextTransactionPaymentType);
            textPaymentNumber = (TextView) view.findViewById(R.id.mtextTransactionPaymentNumber);

            imageViewDelet = (RelativeLayout) view.findViewById(R.id.mdelete_Transaction);

        }
    }

    public AdapterCustomerPlotTransaction(List<DataBaseTransaction> dataBaseTransactionList) {
        this.dataBaseTransactionList = dataBaseTransactionList;
    }

    @Override
    public AdapterCustomerPlotTransaction.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_transaction, parent, false);

        return new AdapterCustomerPlotTransaction.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterCustomerPlotTransaction.MyViewHolder holder, final int position) {
        final DataBaseTransaction dataBaseTransaction = dataBaseTransactionList.get(position);
        //      holder.textTransactionID.setText(String.valueOf(dataBaseTransaction.getTransaction_id()));
        holder.textTransactionID.setText(String.valueOf(position + 1));
        holder.textTransactionAmount.setText(numberWithComma.format(Double.valueOf(dataBaseTransaction.getTransaction_amount())));
        holder.textTransactionGiveTake.setText(dataBaseTransaction.getTransaction_money_give_take());
        holder.textTransactionDate.setText(dataBaseTransaction.getTransaction_date());
        holder.textTransactionRemark.setText(dataBaseTransaction.getTransaction_cash_remark());
        holder.textPaymentType.setText(dataBaseTransaction.getTransaction_type());
        holder.textPaymentNumber.setText(dataBaseTransaction.getTransaction_check_number());

        holder.imageViewDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Customer Transaction will be delete By
                BookID = CustomerPlotTransactionDetails.bookID;

                Log.e("given Amount ", CustomerPlotTransactionDetails.bookGivenAmount);
                Log.e("Pending Amount", CustomerPlotTransactionDetails.bookPendingAmount);
                Log.e("Transaction Amount", dataBaseTransaction.getTransaction_amount());


                // Do Calculations
                updateNewGivenAmount = Double.valueOf(CustomerPlotTransactionDetails.bookGivenAmount) - Double.valueOf(dataBaseTransaction.getTransaction_amount());
                updateNewPendingAmount = Double.valueOf(CustomerPlotTransactionDetails.bookPendingAmount) + Double.valueOf(dataBaseTransaction.getTransaction_amount());

                // change the Updated Values
                CustomerPlotTransactionDetails.bookGivenAmount = String.valueOf(numberWithoutExponential.format(updateNewGivenAmount));
                CustomerPlotTransactionDetails.bookPendingAmount = String.valueOf(numberWithoutExponential.format(updateNewPendingAmount));

                //delete Transaction with Updated Values
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
         //   Toast.makeText(view.getContext(), "deleted", Toast.LENGTH_SHORT).show();
        }

        long updatePlot = databaseHelper.BookPlotUpdate(Integer.parseInt(BookID), String.valueOf(numberWithoutExponential.format(updateNewGivenAmount)), String.valueOf(numberWithoutExponential.format(updateNewPendingAmount)));

        if (updatePlot == 0) {
         //   Toast.makeText(view.getContext(), "Plot not Updated", Toast.LENGTH_SHORT).show();
        } else {
         //   Toast.makeText(view.getContext(), "Updated Plot", Toast.LENGTH_SHORT).show();

        }


    }


}







