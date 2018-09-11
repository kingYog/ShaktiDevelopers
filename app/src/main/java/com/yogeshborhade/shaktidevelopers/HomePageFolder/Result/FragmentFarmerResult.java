package com.yogeshborhade.shaktidevelopers.HomePageFolder.Result;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;

public class FragmentFarmerResult extends Fragment {
    View view;

    TextView textViewFarmerTotal, textViewFarmerGiven, textViewFarmerPending;
    PieChart pieChart;

    int pieChartFarmerTotal = 0, pieChartFarmerPaid = 0, pieChartFarmerRemaining = 0;
    String ProjectID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result_farmer, container, false);

        getXMLContent();

        ProjectID = ProjectDetails.ProjectID;
        DatabaseHelper db = new DatabaseHelper(view.getContext());


        /***************************Famer *****************************/
        String farmerTotal = Constants.numberWithoutExponential.format(db.getTotalOfFarmerAmount(ProjectID));
        if (farmerTotal.equals("0")) {
            Log.e("farmerTotal", "0");
            textViewFarmerTotal.setText(String.valueOf(farmerTotal));
        } else {
            Log.e("farmerTotal", String.valueOf(farmerTotal));
            textViewFarmerTotal.setText(String.valueOf(farmerTotal));
        }


        String farmerPaidAmount = Constants.numberWithoutExponential.format(db.getTotalOfFarmerPaidAmount(ProjectID));
        if (farmerPaidAmount.equals("0")) {
            Log.e("farmerPaidAmount", "0");
            textViewFarmerGiven.setText(String.valueOf(farmerPaidAmount));
        } else {
            Log.e("farmerPaidAmount", String.valueOf(farmerPaidAmount));
            textViewFarmerGiven.setText(String.valueOf(farmerPaidAmount));

        }


        String farmerRemainingAmount = Constants.numberWithoutExponential.format(db.getTotalOfFarmerRemainingAmount(ProjectID));
        if (farmerRemainingAmount.equals("0")) {
            Log.e("farmerRemainingAmount", "0");
            textViewFarmerPending.setText(String.valueOf(farmerRemainingAmount));
        } else {
            Log.e("farmerRemainingAmount", String.valueOf(farmerRemainingAmount));
            textViewFarmerPending.setText(String.valueOf(farmerRemainingAmount));
        }
        pieChartFarmerTotal =(int) Math.round(Double.valueOf(farmerTotal));
        pieChartFarmerPaid = (int) Math.round(Double.valueOf(farmerPaidAmount));
        pieChartFarmerRemaining = (int) Math.round(Double.valueOf(farmerRemainingAmount));


        setPieChart();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    public void getXMLContent() {


        textViewFarmerTotal = (TextView) view.findViewById(R.id.mtextFarmerTotal);
        textViewFarmerGiven = (TextView) view.findViewById(R.id.mtextFarmerGiven);
        textViewFarmerPending = (TextView) view.findViewById(R.id.mtextFarmerRemaining);
        pieChart = (PieChart) view.findViewById(R.id.piechart);


    }

    public void setPieChart() {

     // this.pieChart = pieChart;


        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);



        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(pieChartFarmerTotal, "Farmer Total"));
        yValues.add(new PieEntry(pieChartFarmerPaid, "Farmer Paid"));
        yValues.add(new PieEntry(pieChartFarmerRemaining, "Farmer Pending"));



        PieDataSet dataSet = new PieDataSet(yValues, "Total Revenue For Project");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart.setData(pieData);


    // PieChart Ends Here

    }


}
