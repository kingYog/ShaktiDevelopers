package com.yogeshborhade.shaktidevelopers;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabasePlotClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.Signature.CaptureSignature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.RECIEPT_IMAGE_PATH;

public class CreatePDf extends AppCompatActivity {

    private View mRootView;

    Path path;

    ProgressDialog pd;

    String customerID, customerName, projectID, plotId, customerMobile, customerAdd, projectName, ratepersqft, totalArea, east, west, north, south, totalAmount, bookingAmount, RemainingAmount, RegistrationDate;
    TextView textViewName, textAddress, textViewMobile, textViewProjectName, textViewTotalArea,
            textViewEast, textViewWest, textViewnorth, textViewSouth, textViewTotalAmount, textViewBookingAmount, textViewRemainingAmount, textViewRegistrationDate;


    DatabaseHelper databaseHelper;
    public List<DatabasePlotClass> databasePlotClassList = new ArrayList<>();
    public List<DataBaseCustomerClass> dataBaseCustomerClassList = new ArrayList<>();

    ImageView imageViewCustomerSignature,
            imageViewDeveloperSignature;

   // String ReceiptDirecotry = "/ShaktiDeveloper/Receipt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_pdf);
        pd = new ProgressDialog(CreatePDf.this);
        databaseHelper = new DatabaseHelper(this);


        getXMLContent();
        getDetailsFromIntent();



        textViewProjectName.setText(ProjectDetails.ProjectName);
        imageViewCustomerSignature = (ImageView) findViewById(R.id.mCustomerSignature);
        imageViewDeveloperSignature = (ImageView) findViewById(R.id.mdeveloperSignature);

        // Create Directory
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + RECIEPT_IMAGE_PATH);
        if (myDir.exists()) {
            //  Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        } else {
            myDir.mkdirs();
        }

        imageViewCustomerSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreatePDf.this, CaptureSignature.class);
                i.putExtra("requestcode", 1);
                startActivityForResult(i, 1);
            }
        });

        imageViewDeveloperSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreatePDf.this, CaptureSignature.class);
                i.putExtra("requestcode", 2);
                startActivityForResult(i, 2);
            }
        });


    }

    public void SaveClick(View view) {

        pd.setMessage("saving your image");

        pd.show();

        RelativeLayout savingLayout = (RelativeLayout) findViewById(R.id.idForSaving);

        File file = saveBitMap(CreatePDf.this, savingLayout);

        if (file != null) {

            pd.cancel();

            Log.i("TAG", "Drawing saved to the gallery!");
            Intent i = new Intent(Intent.ACTION_MAIN);
            PackageManager managerclock = getPackageManager();
            i = managerclock.getLaunchIntentForPackage("com.dynamixsoftware.printershare");
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(i);


        } else {

            pd.cancel();
            Log.i("TAG", "Oops! Image could not be saved.");

        }
    }

    private File saveBitMap(Context context, View drawView) {
        //File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Logicchip");
        //File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Logicchip");



        File pictureFileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + RECIEPT_IMAGE_PATH);

        if (!pictureFileDir.exists()) {
            pictureFileDir.mkdirs();
       }

        if (!pictureFileDir.exists()) {

            boolean isDirectoryCreated = pictureFileDir.mkdirs();

            if (!isDirectoryCreated)

                Log.i("TAG", "Can't create directory to save the image");

            return null;
        }

        String filename = pictureFileDir.getPath() + File.separator + System.currentTimeMillis() + ".jpg";

        File pictureFile = new File(filename);

        Bitmap bitmap = getBitmapFromView(drawView);

        try {

            pictureFile.createNewFile();

            FileOutputStream oStream = new FileOutputStream(pictureFile);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);

            oStream.flush();

            oStream.close();

        } catch (IOException e) {

            e.printStackTrace();

            Log.i("TAG", "There was an issue saving the image.");

        }

        scanGallery(context, pictureFile.getAbsolutePath());

        return pictureFile;
    }


    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(View view) {

        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);

        //Get the view's background
        Drawable bgDrawable = view.getBackground();

        if (bgDrawable != null) {

            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);

        } else {

            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);

        }

        // draw the view on the canvas

        view.draw(canvas);

        //return the bitmap

        return returnedBitmap;
    }


    // used for scanning gallery
    private void scanGallery(Context cntx, String path) {

        try {

            MediaScannerConnection.scanFile(cntx, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {

                public void onScanCompleted(String path, Uri uri) {


                }

            });

        } catch (Exception e) {

            e.printStackTrace();

            Log.i("TAG", "There was an issue scanning gallery.");

        }
    }

    public void CreatePDF() {

        Document document = new Document();

        String dirpath = android.os.Environment.getExternalStorageDirectory().toString();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(dirpath + "/example.pdf")); //  Change pdf's name.

        } catch (DocumentException e) {

            e.printStackTrace();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

        document.open();

        Image img = null;  // Change image's name and extension.

        try {

            img = Image.getInstance(dirpath + "/" + "Example.jpg");

        } catch (BadElementException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 0) / img.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
        img.scalePercent(scaler);
        img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);

        try {

            document.add(img);

        } catch (DocumentException e) {

            e.printStackTrace();

        }

        document.close();
    }

    public void getDetailsFromIntent() {
        Bundle bundle = getIntent().getExtras();
        customerID = bundle.getString("customerID", "0");
        plotId = bundle.getString("plotID", "0");
        projectID = bundle.getString("projectID", "0");
        totalArea = bundle.getString("totalArea", "0");
        totalAmount = bundle.getString("totalPrice", "0");
        bookingAmount = bundle.getString("givenAmount", "0");
        RemainingAmount = bundle.getString("remainingAmount", "0");
        RegistrationDate = bundle.getString("registrationDate", "0");


        databasePlotClassList.addAll(databaseHelper.PlotGetSingle(Integer.parseInt(plotId), projectID));

        for (int i = 0; i <= databasePlotClassList.size() - 1; i++) {
            final DatabasePlotClass dataBaseCustomerClass = databasePlotClassList.get(i);

            ratepersqft = String.valueOf(dataBaseCustomerClass.getPlot_price_per_squre_foot());
            east = dataBaseCustomerClass.getPlot_east_side();
            west = dataBaseCustomerClass.getPlot_west_side();
            north = dataBaseCustomerClass.getPlot_north_side();
            south = dataBaseCustomerClass.getPlot_south_side();
            dataBaseCustomerClassList.addAll(databaseHelper.CustomerGetSingle(Integer.parseInt(customerID), projectID));

            Log.e("East",east);
            Log.e("East",west);
            Log.e("East",north);
            Log.e("East",east);


            for (int j = 0; j <= databasePlotClassList.size() - 1; j++) {

                final DataBaseCustomerClass customerClass = dataBaseCustomerClassList.get(i);
                customerName = String.valueOf(customerClass.getCustomerName());
                customerMobile = String.valueOf(customerClass.getCustomerMobileNumber());
                customerAdd = String.valueOf(customerClass.getCustomerAddress());


                textViewName.setText(customerName);
                textAddress.setText(customerAdd);
                textViewMobile.setText(customerMobile);
                textViewTotalArea.setText(totalArea);
                textViewEast.setText(east);
                textViewWest.setText(west);
                textViewnorth.setText(north);
                textViewSouth.setText(south);
                textViewTotalAmount.setText(totalAmount);
                textViewBookingAmount.setText(bookingAmount);
                textViewRemainingAmount.setText(RemainingAmount);
                textViewRegistrationDate.setText(RegistrationDate);

            }

        }
    }

    public void getXMLContent() {

        textViewName = (TextView) findViewById(R.id.mtextName);
        textAddress = (TextView) findViewById(R.id.mtextAdd);
        textViewMobile = (TextView) findViewById(R.id.mtextMobileNumber);
        textViewProjectName = (TextView) findViewById(R.id.mtextProjectName);
        textViewTotalArea = (TextView) findViewById(R.id.mtextTotalArea);
        textViewEast = (TextView) findViewById(R.id.mtextEast);
        textViewWest = (TextView) findViewById(R.id.mtextWest);
        textViewnorth = (TextView) findViewById(R.id.mtextNorth);
        textViewSouth = (TextView) findViewById(R.id.mtextSouth);
        textViewTotalAmount = (TextView) findViewById(R.id.mtextTotalAmount);
        textViewBookingAmount = (TextView) findViewById(R.id.mtextBookingAmount);
        textViewRemainingAmount = (TextView) findViewById(R.id.mtextRemainingAmount);
        textViewRegistrationDate = (TextView) findViewById(R.id.mtextRegistertationDate);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == 1) {
            Bitmap b = BitmapFactory.decodeByteArray(
                    data.getByteArrayExtra("byteArray"), 0,
                    data.getByteArrayExtra("byteArray").length);
            imageViewCustomerSignature.setImageBitmap(b);
        } else if (resultCode == 2) {
            Bitmap b = BitmapFactory.decodeByteArray(
                    data.getByteArrayExtra("byteArray"), 0,
                    data.getByteArrayExtra("byteArray").length);
            imageViewDeveloperSignature.setImageBitmap(b);

        } else {
            Toast.makeText(this, "Signature Not found", Toast.LENGTH_SHORT).show();

        }
    }
}
