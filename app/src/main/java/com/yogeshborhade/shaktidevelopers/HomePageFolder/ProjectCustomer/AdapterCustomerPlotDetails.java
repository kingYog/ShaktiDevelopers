package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.DatabaseClas.BookPlotDataBase;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithComma;

public class AdapterCustomerPlotDetails extends RecyclerView.Adapter<AdapterCustomerPlotDetails.MyViewHolder> {

    private List<BookPlotDataBase> bookPlotDataBaseList;

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textPlotNumber, textPlotBookingDate, textCustomeTotalAmount, textCustomerRemainingAmount, textCustomerNextCommitment, textGivenAmount;

        public Button btnTransaction;

        public LinearLayout mainLayout;

        public MyViewHolder(View view) {
            super(view);
            textPlotNumber = (TextView) view.findViewById(R.id.mtextPlotNumber);
            textPlotBookingDate = (TextView) view.findViewById(R.id.mtextBookingDate);
            textCustomeTotalAmount = (TextView) view.findViewById(R.id.mtextTotalAmount);
            textCustomerRemainingAmount = (TextView) view.findViewById(R.id.mtextRemainingAmount);
            textGivenAmount = (TextView) view.findViewById(R.id.mtextGivenAmount);
            textCustomerNextCommitment = (TextView) view.findViewById(R.id.mtextnextCommitMentDate);
            btnTransaction = (Button) view.findViewById(R.id.mbuttonTransaction);
            mainLayout = (LinearLayout) view.findViewById(R.id.rowmainLayour);


        }
    }


    public AdapterCustomerPlotDetails(List<BookPlotDataBase> bookPlotDataBaseList) {
        this.bookPlotDataBaseList = bookPlotDataBaseList;
    }

    @Override
    public AdapterCustomerPlotDetails.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_customer_plot_details, parent, false);

        return new AdapterCustomerPlotDetails.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterCustomerPlotDetails.MyViewHolder holder, int position) {
        final BookPlotDataBase bookPlotDataBase = bookPlotDataBaseList.get(position);

//      holder.textPlotNumber.setText(String.valueOf(bookPlotDataBase.getBookPlotID()));


        holder.textPlotNumber.setText(String.valueOf(position+1));
        holder.textPlotBookingDate.setText(bookPlotDataBase.getBookDate());
        holder.textCustomeTotalAmount.setText(numberWithComma.format(Double.valueOf(bookPlotDataBase.getBookTotalAmount())));
        holder.textGivenAmount.setText(numberWithComma.format(Double.valueOf(bookPlotDataBase.getBookGivenAmount())));
        holder.textCustomerRemainingAmount.setText(numberWithComma.format(Double.valueOf(bookPlotDataBase.getBookRamainingAmount())));
        holder.textCustomerNextCommitment.setText(bookPlotDataBase.getBookRemainingAmountCommitmentDate());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CustomerPlotDetails.class);
                i.putExtra("bookid", String.valueOf(bookPlotDataBase.getBookID()));
                i.putExtra("bookplotid", String.valueOf(bookPlotDataBase.getBookPlotID()));
                i.putExtra("bookcustomerid", String.valueOf(bookPlotDataBase.getBookCustomerID()));
                i.putExtra("bookdate", String.valueOf(bookPlotDataBase.getBookDate()));
                i.putExtra("booktotaarea", String.valueOf(bookPlotDataBase.getBookTotalArea()));
                i.putExtra("bookratepersqurefoot", String.valueOf(bookPlotDataBase.getBookRatePerSqureFoot()));
                i.putExtra("booktotalamount", String.valueOf(bookPlotDataBase.getBookTotalAmount()));
                i.putExtra("bookgivenamount", String.valueOf(bookPlotDataBase.getBookGivenAmount()));
                i.putExtra("bookgivenamountdate", String.valueOf(bookPlotDataBase.getBookGivenAmountDate()));
                i.putExtra("bookremainingamount", String.valueOf(bookPlotDataBase.getBookRamainingAmount()));
                i.putExtra("bookremainingamountdate", String.valueOf(bookPlotDataBase.getBookRemainingAmountCommitmentDate()));
                i.putExtra("booknotification", String.valueOf(bookPlotDataBase.getBook_Notification_Remark()));
                i.putExtra("booknotificationdate", String.valueOf(bookPlotDataBase.getBook_Notification_Remark_Date()));
                i.putExtra("bookstatus", String.valueOf(bookPlotDataBase.getBookStatus()));
                ((Activity) view.getContext()).startActivity(i);
            }
        });

        holder.btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CustomerPlotTransactionDetails.class);
                i.putExtra("bookid", String.valueOf(bookPlotDataBase.getBookID()));
                i.putExtra("bookplotid", String.valueOf(bookPlotDataBase.getBookPlotID()));
                i.putExtra("bookcustomerid", String.valueOf(bookPlotDataBase.getBookCustomerID()));
                i.putExtra("bookdate", String.valueOf(bookPlotDataBase.getBookDate()));
                i.putExtra("booktotaarea", String.valueOf(bookPlotDataBase.getBookTotalArea()));
                i.putExtra("bookratepersqurefoot", String.valueOf(bookPlotDataBase.getBookRatePerSqureFoot()));
                i.putExtra("booktotalamount", String.valueOf(bookPlotDataBase.getBookTotalAmount()));
                i.putExtra("bookgivenamount", String.valueOf(bookPlotDataBase.getBookGivenAmount()));
                i.putExtra("bookgivenamountdate", String.valueOf(bookPlotDataBase.getBookGivenAmountDate()));
                i.putExtra("bookremainingamount", String.valueOf(bookPlotDataBase.getBookRamainingAmount()));
                i.putExtra("bookremainingamountdate", String.valueOf(bookPlotDataBase.getBookRemainingAmountCommitmentDate()));
                i.putExtra("booknotification", String.valueOf(bookPlotDataBase.getBook_Notification_Remark()));
                i.putExtra("booknotificationdate", String.valueOf(bookPlotDataBase.getBook_Notification_Remark_Date()));
                i.putExtra("bookstatus", String.valueOf(bookPlotDataBase.getBookStatus()));
                ((Activity) view.getContext()).startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return bookPlotDataBaseList.size();
    }


}





