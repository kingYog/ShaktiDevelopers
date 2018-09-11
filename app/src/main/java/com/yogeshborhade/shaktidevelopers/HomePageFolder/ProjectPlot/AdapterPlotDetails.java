package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabasePlotClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.List;

/**
 * Created by admin on 3/29/2018.
 */

public class AdapterPlotDetails extends RecyclerView.Adapter<AdapterPlotDetails.MyViewHolder> {

    private List<DatabasePlotClass> pojoPlotDetails;
    View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textPlotNumber, textPlotEast, textPlotWest, textPlotNorth, textPlotSouth, textStatus, textTotalArea, textTotalPrice;
        public Button btnBookPlot;
        public LinearLayout mainLayout;

        public MyViewHolder(View view) {
            super(view);
            textPlotNumber = (TextView) view.findViewById(R.id.mtextPlotNumber);
            textPlotEast = (TextView) view.findViewById(R.id.mtextPlotEast);
            textPlotWest = (TextView) view.findViewById(R.id.mtextPlotWast);
            textPlotNorth = (TextView) view.findViewById(R.id.mtextPlotNorth);
            textPlotSouth = (TextView) view.findViewById(R.id.mtextPlotSouth);
            textStatus = (TextView) view.findViewById(R.id.plotstatus);
            textTotalArea = (TextView) view.findViewById(R.id.mtexttotalArea);
            textTotalPrice = (TextView) view.findViewById(R.id.mtexttotalPrice);
            btnBookPlot = (Button) view.findViewById(R.id.mbuttonBook);
            mainLayout = (LinearLayout) view.findViewById(R.id.rowmainLayour);


        }
    }


    public AdapterPlotDetails(List<DatabasePlotClass> pojoPlotDetails) {
        this.pojoPlotDetails = pojoPlotDetails;
    }

    @Override
    public AdapterPlotDetails.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_plot_details, parent, false);

        return new AdapterPlotDetails.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterPlotDetails.MyViewHolder holder, int position) {
        final DatabasePlotClass pojoPlot = pojoPlotDetails.get(position);
        holder.textPlotNumber.setText(pojoPlot.getPlot_number());
        holder.textPlotEast.setText(pojoPlot.getPlot_east_side());
        holder.textPlotNorth.setText(pojoPlot.getPlot_north_side());
        holder.textPlotSouth.setText(pojoPlot.getPlot_south_side());
        holder.textPlotWest.setText(pojoPlot.getPlot_west_side());


        String plotStatus = pojoPlot.getPlot_status();
        holder.textStatus.setText(plotStatus);


        holder.textTotalPrice.setText(pojoPlot.getPlot_total_price());

        holder.textTotalArea.setText(pojoPlot.getPloto_total_area_squre_foot());


        if (plotStatus.equals(Constants.PLOTBOOK)) {
            holder.textStatus.setTextColor(itemView.getResources().getColor(R.color.colorRED));
        } else if (plotStatus.equals(Constants.PLOTNOTBOOK)) {
            holder.textStatus.setTextColor(itemView.getResources().getColor(R.color.colorGreen));
        }

        holder.btnBookPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), BookPlotMainPage.class);
                i.putExtra("bookprojectID", String.valueOf(pojoPlot.getProject_Id()));
                i.putExtra("bookPlotID", String.valueOf(pojoPlot.getPlot_Id()));
                i.putExtra("bookPlotEast", String.valueOf(pojoPlot.getPlot_east_side()));
                i.putExtra("bookPlotWest", String.valueOf(pojoPlot.getPlot_west_side()));
                i.putExtra("bookPlotNorth", String.valueOf(pojoPlot.getPlot_north_side()));
                i.putExtra("bookPlotSouth", String.valueOf(pojoPlot.getPlot_south_side()));
                i.putExtra("bookTotalArea", String.valueOf(pojoPlot.getPloto_total_area_squre_foot()));
                i.putExtra("bookRatePerSqureFoot", String.valueOf(pojoPlot.getPlot_price_per_squre_foot()));
                i.putExtra("bookTotalAmount", String.valueOf(pojoPlot.getPlot_total_price()));
                i.putExtra("booktstatus", String.valueOf(pojoPlot.getPlot_status()));
                ((Activity) v.getContext()).startActivity(i);

            }
        });
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdatePlot.class);
                i.putExtra("projectID", String.valueOf(pojoPlot.getProject_Id()));
                i.putExtra("plotID", String.valueOf(pojoPlot.getPlot_Id()));

                ((Activity) v.getContext()).startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return pojoPlotDetails.size();
    }
}



