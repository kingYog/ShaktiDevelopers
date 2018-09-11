package com.yogeshborhade.shaktidevelopers.HomePageFolder.CustomerDetailsPdf;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabaseProjectClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.CUSTOMER_DETAILS_PDF;

public class GetAllCustomerByProject extends AppCompatActivity {

    Button buttonBack;
    RecyclerView recyclerView;
    Spinner customerSpinner;
    Button buttonCreatePDF;


    private ArrayList<DatabaseProjectClass> databaseProjectClassArrayList = new ArrayList<>();
    DatabaseHelper databaseHelper;
    AdapterGetAllProject adapterGetAllCustomer;


    // Customers
    AdapterGetAllCustomer customerAdapter;
    private List<DataBaseCustomerClass> dataBaseCustomerClassList = new ArrayList<>();

    String ProjectID, ProjectName = "Not Available";
    PermissionChecker checker;

    //  String PDFdirectory = "/ShaktiDeveloperPDF/CustomerDetails";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_customer_by_project);


        // Strict mode to open file in updated version

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        databaseHelper = new DatabaseHelper(this);


        // Create Directory
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + CUSTOMER_DETAILS_PDF);
        if (myDir.exists()) {
        } else {
            myDir.mkdirs();
        }

        customerSpinner = (Spinner) findViewById(R.id.mspinner);
        buttonBack = (Button) findViewById(R.id.mbackButton);
        buttonCreatePDF = (Button) findViewById(R.id.mcreatePDF);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        loadSpinnerData();


        customerAdapter = new AdapterGetAllCustomer(dataBaseCustomerClassList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(GetAllCustomerByProject.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customerAdapter);

        customerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                DatabaseProjectClass databaseProjectClass = databaseProjectClassArrayList.get(position);
                ProjectID = String.valueOf(databaseProjectClass.getProject_id());
                ProjectName = String.valueOf(databaseProjectClass.getProject_name());
                getCustomerList(ProjectID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        buttonCreatePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    createPDF(ProjectName);
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadSpinnerData() {
        databaseProjectClassArrayList.clear();
        // database handler
        databaseHelper = new DatabaseHelper(getApplicationContext());
        // Spinner Drop down elements
        databaseProjectClassArrayList.addAll(databaseHelper.ProjectGetAll());

        adapterGetAllCustomer = new AdapterGetAllProject(GetAllCustomerByProject.this, databaseProjectClassArrayList);

        // Set adapter to customerSpinner
        customerSpinner.setAdapter(adapterGetAllCustomer);
        adapterGetAllCustomer.notifyDataSetChanged();

        for (int i = 0; i < databaseProjectClassArrayList.size(); i++) {
            DatabaseProjectClass databaseProjectClass = databaseProjectClassArrayList.get(i);
            String CustomerID = String.valueOf(databaseProjectClass.getProject_id());
            String CustomerName = databaseProjectClass.getProject_name();
            Log.e("Project ID", CustomerID);
            Log.e("Project NaMe ", CustomerName);
        }
    }


    public void getCustomerList(String projectID) {
        dataBaseCustomerClassList.clear();
        dataBaseCustomerClassList.addAll(databaseHelper.CustomerGetAll(projectID));
        customerAdapter.notifyDataSetChanged();
    }

    public void createPDF(String fileName) throws DocumentException, FileNotFoundException {
        String FILE = Environment.getExternalStorageDirectory().toString()
                + CUSTOMER_DETAILS_PDF + "/" + fileName + ".pdf";
        // Add Permission into Manifest.xml


        // Create New Blank Document
        Document document = new Document(PageSize.A4);

        // Create Directory in External Storage
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + CUSTOMER_DETAILS_PDF);

        if (myDir.exists()) {

        } else {
            myDir.mkdirs();
        }


// Create Pdf Writer for Writting into New Created Document
        PdfWriter.getInstance(document, new FileOutputStream(FILE));

// Open Document for Writting into document
        document.open();

// User Define Method

        addTitlePage(document);

// Close Document after writting all content
        document.close();
        viewPdf(fileName, CUSTOMER_DETAILS_PDF);
    }


    public void addTitlePage(Document document) throws DocumentException {
        // Font Style for Document
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD
                | Font.UNDERLINE, BaseColor.GREEN);

        Font titleFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD
                | Font.UNDERLINE, BaseColor.RED);

        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLUE);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        // Start New Paragraph
        Paragraph prHead = new Paragraph();
        // Set Font in this Paragraph
        prHead.setFont(titleFont);
        // Add item into Paragraph
        prHead.add("Shakti Developer \n" + "\n" + "\n");

        // Start New Paragraph
        Paragraph prHead1 = new Paragraph();
        // Set Font in this Paragraph
        prHead1.setFont(titleFont1);
        // Add item into Paragraph
        prHead1.add("Customer Details Of Project  :-  " + ProjectName + "\n" + "\n");

        Paragraph CustomerDetails = new Paragraph();
        // Set Font in this Paragraph
        CustomerDetails.setFont(smallBold);
        // Add item into Paragraph
        String CustomerNumber = "1";
        String CustomerName = "Yogesh Borhade";
        String CustomerMobileNumber = "9130907071";
        String CustomerAge = "28";

        for (int i = 0; i < dataBaseCustomerClassList.size(); i++) {

            DataBaseCustomerClass dataBaseCustomerClass = dataBaseCustomerClassList.get(i);

            CustomerNumber = String.valueOf(i + 1);
            CustomerName = dataBaseCustomerClass.getCustomerName();
            CustomerMobileNumber = dataBaseCustomerClass.getCustomerMobileNumber();
            CustomerAge = dataBaseCustomerClass.getCustomerAge();


            CustomerDetails.add("No:- " + CustomerNumber + " \n" + "Customer Name   :-  " + CustomerName + " \n" + "Customer Mobile Number   :-  " + CustomerMobileNumber + " \n" + "Customer Age    :-  " + CustomerAge + " \n" + " \n" + " \n");

//            CustomerDetails.add("No:- " + i + " \n" + "Customer Name   :-  " + CustomerName + " \n" + "Customer Mobile Number   :-  " + CustomerMobileNumber + " \n" + "Customer Age    :-  " + CustomerAge + " \n" + " \n" + " \n");

        }


        document.add(prHead);
        document.add(prHead1);
        document.add(CustomerDetails);


        // Create new Page in PDF
//        document.newPage();
    }

    // Method for opening a pdf file
    private void viewPdf(String file, String directory) {

        File pdfFile = new File(Environment.getExternalStorageDirectory() + directory + "/" + file + ".pdf");
        Uri path = Uri.fromFile(pdfFile);

//        Uri path = FileProvider.getUriForFile(GetAllCustomerByProject.this, BuildConfig.APPLICATION_ID + ".provider", pdfFile);


        // Setting the intent for pdf reader
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Can't read pdf file", Toast.LENGTH_SHORT).show();
        }
    }


}
