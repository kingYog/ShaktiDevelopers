package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.BookPlotDataBase;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.List;

public class AdapterAlloacteCustomerToPlot extends RecyclerView.Adapter<AdapterAlloacteCustomerToPlot.MyViewHolder> {

    private List<BookPlotDataBase> bookPlotDataBaseList;
    View itemView;
    DatabaseHelper databaseHelper;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textSRNO, textBookID, textCustomerID, textPlotID, textCustomerName;
        public LinearLayout mainLayout;

        public MyViewHolder(View view) {
            super(view);
            textSRNO = (TextView) view.findViewById(R.id.mtextSRNO);
            textBookID = (TextView) view.findViewById(R.id.mtextBookID);
            textCustomerID = (TextView) view.findViewById(R.id.mtextCustomerID);
            textPlotID = (TextView) view.findViewById(R.id.mtextPlotId);
            textCustomerName = (TextView) view.findViewById(R.id.mtextCustomerName);

            mainLayout = (LinearLayout) view.findViewById(R.id.rowmainLayour);


        }
    }


    public AdapterAlloacteCustomerToPlot(List<BookPlotDataBase> bookPlotDataBaseList) {
        this.bookPlotDataBaseList = bookPlotDataBaseList;
    }

    @Override
    public AdapterAlloacteCustomerToPlot.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_allocate_customer_to_plot, parent, false);

        return new AdapterAlloacteCustomerToPlot.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterAlloacteCustomerToPlot.MyViewHolder holder, int position) {
        final BookPlotDataBase pojoPlot = bookPlotDataBaseList.get(position);

        databaseHelper = new DatabaseHelper(itemView.getContext());

        holder.textSRNO.setText(String.valueOf(position + 1));
        holder.textBookID.setText(String.valueOf(pojoPlot.getBookID()));
        holder.textCustomerID.setText(String.valueOf(pojoPlot.getBookCustomerID()));
        holder.textPlotID.setText(String.valueOf(pojoPlot.getBookPlotID()));

        holder.textCustomerName.setText(databaseHelper.CustomerGetName(Integer.parseInt(pojoPlot.getBookCustomerID())));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), BookPlotAllocatedCustomerDetailsWithPrint.class);
                i.putExtra("bookprojectID", String.valueOf(pojoPlot.getBookProjectID()));
                i.putExtra("bookID", String.valueOf(pojoPlot.getBookID()));
                i.putExtra("bookPlotID", String.valueOf(pojoPlot.getBookPlotID()));
                i.putExtra("customerID", String.valueOf(pojoPlot.getBookCustomerID()));
                i.putExtra("bookPlotEast", String.valueOf(BookPlotAllocateCustomerToPlot.plotEastSize));
                i.putExtra("bookPlotWest", String.valueOf(BookPlotAllocateCustomerToPlot.plotWestSize));
                i.putExtra("bookPlotNorth", String.valueOf(BookPlotAllocateCustomerToPlot.plotNorthsize));
                i.putExtra("bookPlotSouth", String.valueOf(BookPlotAllocateCustomerToPlot.plotSouthSize));
                i.putExtra("bookTotalArea", String.valueOf(pojoPlot.getBookTotalArea()));
                i.putExtra("bookRatePerSqureFoot", String.valueOf(pojoPlot.getBookRatePerSqureFoot()));
                i.putExtra("bookTotalAmount", String.valueOf(pojoPlot.getBookTotalAmount()));
                i.putExtra("booktstatus", String.valueOf(pojoPlot.getBookStatus()));
                ((Activity) v.getContext()).startActivity(i);

            }
        });
//        holder.textCustomerName.setText(pojoPlot.getBookCustomerID());

    }


    @Override
    public int getItemCount() {
        return bookPlotDataBaseList.size();
    }
}




