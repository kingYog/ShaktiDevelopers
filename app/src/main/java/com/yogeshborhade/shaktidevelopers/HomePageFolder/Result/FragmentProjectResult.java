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

public class FragmentProjectResult extends Fragment {
    View view;

    TextView textViewCutomerTotal, textViewCutomerRescived, textViewCutomerRemaining,
            textViewFarmerTotal, textViewFarmerGiven, textViewFarmerPending, textViewExpenceTotal, textViewprofit;
    PieChart pieChart;

    int pieChartExpence = 0, pieChartRescieved = 0, pieChartGiven = 0;
    String ProjectID;
    // Amount Rescived -(Amount Given +Expence)

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result_project, container, false);

        getXMLContent();

        ProjectID = ProjectDetails.ProjectID;


        DatabaseHelper db = new DatabaseHelper(view.getContext());

        /***************************Customer *****************************/

        String customerTotal = Constants.numberWithoutExponential.format(db.getTotalOfCustomerAmountByProject(ProjectID));
        if (customerTotal.equals("0")) {
            Log.e("customerTotal", "0");
            textViewCutomerTotal.setText(String.valueOf(customerTotal));
        } else {
            Log.e("customerTotal", String.valueOf(customerTotal));
            textViewCutomerTotal.setText(String.valueOf(customerTotal));
        }


        String customerPaidAmount = Constants.numberWithoutExponential.format(db.getTotalOfCustomerPaidAmountByProject(ProjectID));
        if (customerPaidAmount.equals("0")) {
            Log.e("customerPaidAmount", "0");
            textViewCutomerRescived.setText(String.valueOf(customerPaidAmount));
        } else {
            Log.e("customerPaidAmount", String.valueOf(customerPaidAmount));
            textViewCutomerRescived.setText(String.valueOf(customerPaidAmount));

        }


        String customerRemainingAmount = Constants.numberWithoutExponential.format(db.getTotalOfCustomerRemainingAmountByProject(ProjectID));
        if (customerRemainingAmount.equals("0")) {
            Log.e("customerRemainingAmount", "0");
            textViewCutomerRemaining.setText(String.valueOf(customerRemainingAmount));
        } else {
            Log.e("customerRemainingAmount", String.valueOf(customerRemainingAmount));
            textViewCutomerRemaining.setText(String.valueOf(customerRemainingAmount));

        }

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


        /*************************** Expenc *****************************/
        int totalExpece = db.getTotalOfExpenceByProject(ProjectID);
        if (totalExpece == 0) {
            Log.e("TotalValues", "0");
            textViewExpenceTotal.setText(String.valueOf(totalExpece));
        } else {
            Log.e("TotalValues", String.valueOf(totalExpece));
            textViewExpenceTotal.setText(String.valueOf(totalExpece));
            pieChartExpence = totalExpece;
        }

        /*************************** Calculate Profit *****************************/
        double totalExpenceWithFarmer = Double.valueOf(textViewExpenceTotal.getText().toString()) + Double.valueOf(textViewFarmerGiven.getText().toString());
        double totalProfit = Double.valueOf(textViewCutomerRescived.getText().toString()) - totalExpenceWithFarmer;
        textViewprofit.setText(String.valueOf(Constants.numberWithoutExponential.format(totalProfit)));

        setPieChart();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    public void getXMLContent() {
        textViewCutomerTotal = (TextView) view.findViewById(R.id.mtextCustomerTotal);
        textViewCutomerRescived = (TextView) view.findViewById(R.id.mtextCustomerRescived);
        textViewCutomerRemaining = (TextView) view.findViewById(R.id.mtextCustomerRemaining);
        textViewFarmerTotal = (TextView) view.findViewById(R.id.mtextFarmerTotal);
        textViewFarmerGiven = (TextView) view.findViewById(R.id.mtextFarmerGiven);
        textViewFarmerPending = (TextView) view.findViewById(R.id.mtextFarmerRemaining);
        textViewExpenceTotal = (TextView) view.findViewById(R.id.mtextExpenceTotal);
        textViewprofit = (TextView) view.findViewById(R.id.mtextProfitTotal);
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
        yValues.add(new PieEntry(pieChartRescieved, "Customer Rescived"));
        yValues.add(new PieEntry(pieChartGiven, "Farmer Paid"));
        yValues.add(new PieEntry(pieChartExpence, "Expence"));

        PieDataSet dataSet = new PieDataSet(yValues, "Total Revenue For Project");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart.setData(pieData);

        //PieChart Ends Here


    }


}
