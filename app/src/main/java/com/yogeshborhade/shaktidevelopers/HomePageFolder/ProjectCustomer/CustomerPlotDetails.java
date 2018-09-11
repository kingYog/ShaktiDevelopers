package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabasePlotClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerPlotDetails extends AppCompatActivity {

    DatabaseHelper db;


    EditText editTextPlotNumber, editTextEast, editTextWest, editTextNorth, editTextSouth, editTextTotalArea, editTextRatePerSqureFoot, editTextTotalAmount, editTextGivenAmount, editTextRemainingAmount, editTextNotification, editTextStatus;
    TextView textViewAmountGivenDate, textViewamountNextCommitmentDate, textViewNotificationDate;
    ImageView imageViewDatepickerGivenAmountDate, imageViewDatepickerNextComitmentDate, imageViewDatePickerNotification;

    String ProjectID, bookID, plotNumber, plotEastSize, plotWestSize, plotNorthsize, plotSouthSize, bookplotID, bookCustomerID, bookDate, bookTotalArea, bookRatePerSqureFoot, bookTotalAmount,
            bookGivenAmount, bookGivenAmountDate,
            bookRemainingAmount, bookRemainingAmountDate, bookNotification, bookNotificationDate, bookStatus;

    // Plot Details
    private List<DatabasePlotClass> plotList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_plot_details);

        db = new DatabaseHelper(CustomerPlotDetails.this);
        ProjectID = ProjectDetails.ProjectID;

        getXML();
        getDataFromIntent();

    }

    public void getXML() {

        editTextPlotNumber = (EditText) findViewById(R.id.medtPlotNumber);
        editTextEast = (EditText) findViewById(R.id.medtEestSize);
        editTextWest = (EditText) findViewById(R.id.medtWestSize);
        editTextNorth = (EditText) findViewById(R.id.medtNorthSize);
        editTextSouth = (EditText) findViewById(R.id.medtSouthSize);
        editTextTotalArea = (EditText) findViewById(R.id.medtTotalArea);
        editTextRatePerSqureFoot = (EditText) findViewById(R.id.medtRatePerSqurefoot);
        editTextTotalAmount = (EditText) findViewById(R.id.medtTotalAmount);
        editTextGivenAmount = (EditText) findViewById(R.id.medtAmountGiven);
        editTextRemainingAmount = (EditText) findViewById(R.id.medtAmountPending);
        editTextNotification = (EditText) findViewById(R.id.medtNotificationRemark);
        editTextStatus = (EditText) findViewById(R.id.medtStatus);

        textViewAmountGivenDate = (TextView) findViewById(R.id.mtextGivenAmountDate);
        textViewamountNextCommitmentDate = (TextView) findViewById(R.id.mtextnextCommitMentDate);
        textViewNotificationDate = (TextView) findViewById(R.id.mtextNotificationDate);


        imageViewDatepickerGivenAmountDate = (ImageView) findViewById(R.id.mimgAmountRescievdDatePicker);
        imageViewDatepickerNextComitmentDate = (ImageView) findViewById(R.id.mimgNextCommitmentDatePicker);
        imageViewDatePickerNotification = (ImageView) findViewById(R.id.mimgNotificationRemarkDatePicker);


    }

    public void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        bookID = bundle.getString("bookid", "0");
        bookplotID = bundle.getString("bookplotid", "0");
        bookCustomerID = bundle.getString("bookcustomerid", "0");
        bookDate = bundle.getString("bookdate", "0");
        bookTotalArea = bundle.getString("booktotaarea", "0");
        bookRatePerSqureFoot = bundle.getString("bookratepersqurefoot", "0");
        bookTotalAmount = bundle.getString("booktotalamount", "0");
        bookGivenAmount = bundle.getString("bookgivenamount", "0");
        bookGivenAmountDate = bundle.getString("bookgivenamountdate", "0");
        bookRemainingAmount = bundle.getString("bookremainingamount", "0");
        bookRemainingAmountDate = bundle.getString("bookremainingamountdate", "0");
        bookNotification = bundle.getString("booknotification", "0");
        bookNotificationDate = bundle.getString("booknotificationdate", "0");
        bookStatus = bundle.getString("bookstatus", "0");


        editTextTotalArea.setText(bookTotalArea);
        editTextRatePerSqureFoot.setText(bookRatePerSqureFoot);
        editTextTotalAmount.setText(bookTotalAmount);
        editTextGivenAmount.setText(bookGivenAmount);
        editTextRemainingAmount.setText(bookRemainingAmount);
        editTextNotification.setText(bookNotification);
        editTextStatus.setText(bookStatus);


        textViewAmountGivenDate.setText(bookGivenAmountDate);
        textViewamountNextCommitmentDate.setText(bookRemainingAmountDate);
        textViewNotificationDate.setText(bookNotificationDate);

        getSinglePlot(Integer.parseInt(bookplotID), ProjectID);

    }

    public void getSinglePlot(int Plot_Id, String ProjectID) {

        plotList.clear();
        plotList.addAll(db.PlotGetSingle(Plot_Id, ProjectID));

        for (int i = 0; i <= plotList.size() - 1; i++) {
            final DatabasePlotClass databasePlotClass = plotList.get(i);

            plotNumber = databasePlotClass.getPlot_number();
            plotEastSize = databasePlotClass.getPlot_east_side();
            plotWestSize = databasePlotClass.getPlot_west_side();
            plotNorthsize = databasePlotClass.getPlot_north_side();
            plotSouthSize = databasePlotClass.getPlot_south_side();

            editTextPlotNumber.setText(plotNumber);
            editTextEast.setText(plotEastSize);
            editTextWest.setText(plotWestSize);
            editTextNorth.setText(plotNorthsize);
            editTextSouth.setText(plotSouthSize);


        }
    }

}
