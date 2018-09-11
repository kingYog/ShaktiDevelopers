package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabaseProjectClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.List;

/**
 * Created by admin on 3/27/2018.
 */

public class AdapterHomePage extends RecyclerView.Adapter<AdapterHomePage.MyViewHolder> {

    private List<DatabaseProjectClass> pojoProjectDetails;
    View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textProjectID, textProjectName, textProjectAdress, textTotalPlot, textSalePlot, textAvailablePlot;
        RelativeLayout rightArrow, mainLayout;


        public MyViewHolder(View view) {
            super(view);
            textProjectID = (TextView) view.findViewById(R.id.mtextProjectID);
            textProjectName = (TextView) view.findViewById(R.id.mtextProjectName);
            textProjectAdress = (TextView) view.findViewById(R.id.mtextProjectAdress);
            textTotalPlot = (TextView) view.findViewById(R.id.mtextTotalPlot);
            textSalePlot = (TextView) view.findViewById(R.id.mtextSalePlot);
            textAvailablePlot = (TextView) view.findViewById(R.id.mtextAvailablePlot);
            rightArrow = (RelativeLayout) view.findViewById(R.id.rlrighArrow);
            mainLayout = (RelativeLayout) view.findViewById(R.id.rlmain);

        }
    }


    public AdapterHomePage(List<DatabaseProjectClass> pojoMailsList) {
        this.pojoProjectDetails = pojoMailsList;
    }

    @Override
    public AdapterHomePage.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_home_page, parent, false);

        return new AdapterHomePage.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DatabaseProjectClass pojoDatabaseProjectClass = pojoProjectDetails.get(position);

        Log.e("Project ID", String.valueOf(pojoDatabaseProjectClass.getProject_id()));
        Log.e("Project Name", String.valueOf(pojoDatabaseProjectClass.getProject_name()));
        Log.e("projectStartDate", pojoDatabaseProjectClass.getProject_added_date());
        Log.e("projectAdress", pojoDatabaseProjectClass.getProject_adress());
        Log.e("project Total", pojoDatabaseProjectClass.getProject_totalArea());
        Log.e("projectEastSize", pojoDatabaseProjectClass.getProject_east_side());
        Log.e("projectWestSize", pojoDatabaseProjectClass.getProject_west_side());
        Log.e("projectNorthsize", pojoDatabaseProjectClass.getProject_north_side());
        Log.e("projectSouthSize", pojoDatabaseProjectClass.getProject_south_side());
        Log.e("projectVailabale", pojoDatabaseProjectClass.getProject_total_available_plots());
        Log.e("projectSalePlot", pojoDatabaseProjectClass.getProject_total_sale_plots());
        Log.e("projectExpence", pojoDatabaseProjectClass.getProject_total_expence());
        Log.e("projectEarn", pojoDatabaseProjectClass.getProject_total_earn());
        Log.e("projectProfit", pojoDatabaseProjectClass.getProject_total_profit());


     //   holder.textProjectID.setText(String.valueOf(pojoDatabaseProjectClass.getProject_id()));
        holder.textProjectID.setText(String.valueOf(position + 1));
        holder.textProjectName.setText(pojoDatabaseProjectClass.getProject_name());
        holder.textProjectAdress.setText(pojoDatabaseProjectClass.getProject_adress());
     /*   holder.textTotalPlot.setText(pojoDatabaseProjectClass.getProject_total_available_plots());
        holder.textSalePlot.setText(pojoDatabaseProjectClass.getProject_total_sale_plots());
        holder.textAvailablePlot.setText(pojoDatabaseProjectClass.getProject_total_available_plots());*/

        /************** To get Count Of Plot ***************/
        DatabaseHelper db = new DatabaseHelper(itemView.getContext());
        String statusRemaining = "'remaining'";
        String statusBooked = "'booked'";

        int plotReamining = db.getPlotCountOfTotalRemaining(String.valueOf(pojoDatabaseProjectClass.getProject_id()), statusRemaining);
        int plotBooked = db.getPlotCountOfTotalBooked(String.valueOf(pojoDatabaseProjectClass.getProject_id()), statusBooked);
        int plotAll = db.getPlotCountOfTotalPlot(String.valueOf(pojoDatabaseProjectClass.getProject_id()));

        holder.textTotalPlot.setText(String.valueOf(plotAll));
        holder.textSalePlot.setText(String.valueOf(plotBooked));
        holder.textAvailablePlot.setText(String.valueOf(plotReamining));

        /*****************************************************/

        holder.rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ProjectDetails.class);
                i.putExtra("pid", String.valueOf(pojoDatabaseProjectClass.getProject_id()));
                i.putExtra("pName", String.valueOf(pojoDatabaseProjectClass.getProject_name()));
                view.getContext().startActivity(i);
            }
        });

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), UpdateProject.class);
                i.putExtra("pid", String.valueOf(pojoDatabaseProjectClass.getProject_id()));
                view.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return pojoProjectDetails.size();
    }
}


