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

public class FragmentCustomerResult extends Fragment {
    View view;

    TextView textViewCutomerTotal, textViewCutomerRescived, textViewCutomerRemaining;
    PieChart pieChart;

    int pieChartCustomerTotal = 0, pieChartCustomerRescievd = 0, pieChartCustomerRemaining = 0;
    String ProjectID;
    // Amount Rescived -(Amount Given +Expence)

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result_customer, container, false);

        getXMLContent();

        ProjectID = ProjectDetails.ProjectID;


        DatabaseHelper db = new DatabaseHelper(view.getContext());

        /***************************Customer *****************************/
      /*  int customerTotal = db.getTotalOfCustomerAmount(ProjectID);
        if (customerTotal == 0) {
            Log.e("customerTotal", "0");
            textViewCutomerTotal.setText(String.valueOf(customerTotal));
        } else {
            Log.e("customerTotal", String.valueOf(customerTotal));
            textViewCutomerTotal.setText(String.valueOf(customerTotal));
        }


        int customerPaidAmount = db.getTotalOfCustomerPaidAmount(ProjectID);
        if (customerPaidAmount == 0) {
            Log.e("customerPaidAmount", "0");
            textViewCutomerRescived.setText(String.valueOf(customerPaidAmount));
        } else {
            Log.e("customerPaidAmount", String.valueOf(customerPaidAmount));
            textViewCutomerRescived.setText(String.valueOf(customerPaidAmount));
        }


        int customerRemainingAmount = db.getTotalOfCustomerRemainingAmount(ProjectID);
        if (customerRemainingAmount == 0) {
            Log.e("customerRemainingAmount", "0");
            textViewCutomerRemaining.setText(String.valueOf(customerRemainingAmount));
        } else {
            Log.e("customerRemainingAmount", String.valueOf(customerRemainingAmount));
            textViewCutomerRemaining.setText(String.valueOf(customerRemainingAmount));

        }


        pieChartCustomerTotal = customerTotal;
        pieChartCustomerRescievd = customerPaidAmount;
        pieChartCustomerRemaining = customerRemainingAmount;
*/

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

        pieChartCustomerTotal = (int) Math.round(Double.valueOf(customerTotal));
        pieChartCustomerRescievd = (int) Math.round(Double.valueOf(customerPaidAmount));
        pieChartCustomerRemaining = (int) Math.round(Double.valueOf(customerRemainingAmount));

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
        yValues.add(new PieEntry(pieChartCustomerTotal, "Total"));
        yValues.add(new PieEntry(pieChartCustomerRescievd, "Rescieved"));
        yValues.add(new PieEntry(pieChartCustomerRemaining, "Remaining"));



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
