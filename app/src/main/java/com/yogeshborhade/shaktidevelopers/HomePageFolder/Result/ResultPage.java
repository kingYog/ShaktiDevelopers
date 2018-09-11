package com.yogeshborhade.shaktidevelopers.HomePageFolder.Result;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yogeshborhade.shaktidevelopers.R;

public class ResultPage extends AppCompatActivity {


    Button buttonBack, buttonProjectReport, buttonCustomerReport, buttonFarmerReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        getXML();

        loadFragment(new FragmentProjectResult());


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        buttonProjectReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonProjectReport.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                buttonCustomerReport.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonFarmerReport.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(new FragmentProjectResult());

            }
        });

        buttonCustomerReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonProjectReport.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonCustomerReport.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                buttonFarmerReport.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(new FragmentCustomerResult());

            }
        });


        buttonFarmerReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonProjectReport.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonCustomerReport.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonFarmerReport.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                loadFragment(new FragmentFarmerResult());

            }
        });


    }

    public void getXML() {


        buttonBack = (Button) findViewById(R.id.mbackButton);
        buttonProjectReport = (Button) findViewById(R.id.mbtnProjectReport);
        buttonCustomerReport = (Button) findViewById(R.id.mbtnCustomerReport);
        buttonFarmerReport = (Button) findViewById(R.id.mbtnFarmerReport);


    }

    public void loadFragment(Fragment fragment) {

        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.mFrameResult, fragment);
        fragmentTransaction.commit(); // save the changes

    }

}