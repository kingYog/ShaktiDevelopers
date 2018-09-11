package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.BookPlotDataBase;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabasePlotClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddPlot extends AppCompatActivity {
    public static final int requestcode = 1;
    static String TAG = "ExelLog";

    Button backButton, submitButton, importFromExcel;
    EditText editTextPlotNumber, editTextPlotTotalSize,
            editTextPlotEastSize, editTextPlotWestSize, editTextPlotNorthSize, editTextPlotSouthSize,
            editTextPlotPricePerSqureFoot, editTextTotalPlotSellingPrice;


    String projectId, plotAllocatedCustomerId, plotid,
            plotNumber,
            plotEastSize, plotWestSize, plotNorthsize, plotSouthSize,
            plotRegisterDate, plotTotalAreaSqureFoot,
            plotPricePerSqureFoot, plotTotalSellingPrice,
            plotStatus, plotRemark;

    DatabaseHelper db;


    private List<DatabasePlotClass> plotList = new ArrayList<>();
    private List<BookPlotDataBase> bookPlotDataBases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plot);
        db = new DatabaseHelper(this);
        getXMLContent();
        getAllPlot();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(AddPlot.this, "Makle Toast", Toast.LENGTH_SHORT).show();

                finish();
            }
        });


        importFromExcel = (Button) findViewById(R.id.mbuttonImport);

        importFromExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, requestcode);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPlot();
            }
        });
    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        submitButton = (Button) findViewById(R.id.mSubmit);


        editTextPlotNumber = (EditText) findViewById(R.id.medtPlotNumber);
        editTextPlotTotalSize = (EditText) findViewById(R.id.medtPlotTotalSize);
        editTextPlotEastSize = (EditText) findViewById(R.id.medtPlotEastSize);
        editTextPlotWestSize = (EditText) findViewById(R.id.medtPlotWestSize);
        editTextPlotNorthSize = (EditText) findViewById(R.id.medtPlotNorthSize);
        editTextPlotSouthSize = (EditText) findViewById(R.id.medtPlotSouthSize);
        editTextPlotPricePerSqureFoot = (EditText) findViewById(R.id.medtPlotPricePerSqureFoot);
        editTextTotalPlotSellingPrice = (EditText) findViewById(R.id.medtPlotPriceSelligPrice);


    }

    public void insertPlot() {
        projectId = ProjectDetails.ProjectID;
        plotid = "We Cannot Insert Here";
        plotAllocatedCustomerId = "NA";
        plotNumber = editTextPlotNumber.getText().toString();
        plotEastSize = editTextPlotEastSize.getText().toString();
        plotWestSize = editTextPlotWestSize.getText().toString();
        plotNorthsize = editTextPlotNorthSize.getText().toString();
        plotSouthSize = editTextPlotSouthSize.getText().toString();
        plotRegisterDate = "NA";
        plotTotalAreaSqureFoot = editTextPlotTotalSize.getText().toString();
        plotPricePerSqureFoot = editTextPlotPricePerSqureFoot.getText().toString();
        plotTotalSellingPrice = editTextTotalPlotSellingPrice.getText().toString();
        plotStatus = Constants.PLOTNOTBOOK;
        plotRemark = "NA";


        long id = db.PlotInsert(projectId, plotAllocatedCustomerId, plotNumber, plotEastSize,
                plotWestSize, plotNorthsize, plotSouthSize, plotRegisterDate,
                plotTotalAreaSqureFoot, plotPricePerSqureFoot, plotTotalSellingPrice, plotStatus, plotRemark);

        if (id == 0) {
           // Toast.makeText(AddPlot.this, "Plot Not Added", Toast.LENGTH_LONG).show();
        } else {
           // Toast.makeText(AddPlot.this, "Plot Added" + id, Toast.LENGTH_LONG).show();
            finish();
        }
        finish();

    }

    public void getPlotList() {
        plotList.addAll(db.PlotGetAll(ProjectDetails.ProjectID));

        for (int i = 0; i < plotList.size(); i++) {
            DatabasePlotClass databaseProjectClass = plotList.get(i);

            Log.e("Project ID", String.valueOf(databaseProjectClass.getProject_Id()));
            Log.e("PLOT ID", String.valueOf(databaseProjectClass.getPlot_Id()));
            Log.e("PLOT Customer", String.valueOf(databaseProjectClass.getPlot_allocated_customer_id()));
            Log.e("PLOT Number", String.valueOf(databaseProjectClass.getPlot_number()));
            Log.e("PLOT EAST", String.valueOf(databaseProjectClass.getPlot_east_side()));
            Log.e("PLOT WEST", String.valueOf(databaseProjectClass.getPlot_west_side()));
            Log.e("PLOT SOUTH", String.valueOf(databaseProjectClass.getPlot_south_side()));
            Log.e("PLOT NORTH", String.valueOf(databaseProjectClass.getPlot_north_side()));
            Log.e("PLOT plotRegisterDate", String.valueOf(databaseProjectClass.getPlot_register_date()));
            Log.e("plotTotalAreaSqureFoot", String.valueOf(databaseProjectClass.getPloto_total_area_squre_foot()));
            Log.e("plotPricePerSqureFoot", String.valueOf(databaseProjectClass.getPlot_price_per_squre_foot()));
            Log.e("plotTotalSellingPrice", String.valueOf(databaseProjectClass.getPlot_total_price()));
            Log.e("plotStatus", String.valueOf(databaseProjectClass.getPlot_status()));


            Log.e("***********************", "****************************************");


        }
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }


    private void readFileXLS(Context context, String filename) {

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e(TAG, "Storage not available or read only");
            return;
        }

        try {
            final File file = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath(), filename);
            FileInputStream myInput = new FileInputStream(file);

            Workbook wb = WorkbookFactory.create(myInput);
            DataFormatter objDefaultFormat = new DataFormatter();
            FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) wb);

            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> objIterator = sheet.rowIterator();

            while (objIterator.hasNext())

            {

                Row row = objIterator.next();

                if (row.getRowNum() == 0) {
                    Log.e("Dont Fetch data ", "************* First Row");
                } else if (row != null) {


                    if (!isRowEmpty(row)) {


                        Log.e("Not Empty", "**********Do the Things");
                        projectId = ProjectDetails.ProjectID;
                        plotid = "We Cannot Insert Here";
                        plotAllocatedCustomerId = "NA";

                        // cell 1
                        Cell cellValue1 = row.getCell(0);
                        objFormulaEvaluator.evaluate(cellValue1); // This will evaluate the cell, And any type of cell will return string value
                        plotNumber = objDefaultFormat.formatCellValue(cellValue1, objFormulaEvaluator);

                        // cell 2
                        Cell cellValue2 = row.getCell(1);
                        objFormulaEvaluator.evaluate(cellValue2); // This will evaluate the cell, And any type of cell will return string value
                        plotEastSize = objDefaultFormat.formatCellValue(cellValue2, objFormulaEvaluator);

                        // cell 2

                        Cell cellValue3 = row.getCell(2);
                        objFormulaEvaluator.evaluate(cellValue3); // This will evaluate the cell, And any type of cell will return string value
                        plotWestSize = objDefaultFormat.formatCellValue(cellValue3, objFormulaEvaluator);

                        // cell 4

                        Cell cellValue4 = row.getCell(3);
                        objFormulaEvaluator.evaluate(cellValue4); // This will evaluate the cell, And any type of cell will return string value
                        plotNorthsize = objDefaultFormat.formatCellValue(cellValue4, objFormulaEvaluator);

                        // cell 5

                        Cell cellValue5 = row.getCell(4);
                        objFormulaEvaluator.evaluate(cellValue5); // This will evaluate the cell, And any type of cell will return string value
                        plotSouthSize = objDefaultFormat.formatCellValue(cellValue5, objFormulaEvaluator);

                        plotRegisterDate = "NA";

                        // cell 6

                        Cell cellValue6 = row.getCell(5);
                        objFormulaEvaluator.evaluate(cellValue6); // This will evaluate the cell, And any type of cell will return string value
                        plotTotalAreaSqureFoot = objDefaultFormat.formatCellValue(cellValue6, objFormulaEvaluator);

                        // cell 7

                        Cell cellValue7 = row.getCell(6);
                        objFormulaEvaluator.evaluate(cellValue7); // This will evaluate the cell, And any type of cell will return string value
                        plotPricePerSqureFoot = objDefaultFormat.formatCellValue(cellValue7, objFormulaEvaluator);

                        // cell 8

                        Cell cellValue8 = row.getCell(7);
                        objFormulaEvaluator.evaluate(cellValue8); // This will evaluate the cell, And any type of cell will return string value
                        plotTotalSellingPrice = objDefaultFormat.formatCellValue(cellValue8, objFormulaEvaluator);

                        plotStatus = Constants.PLOTNOTBOOK;

                        plotRemark = "NA";


                        Log.e("plotNumber", plotNumber);
                        Log.e("plotEastSize", plotEastSize);
                        Log.e("plotWestSize", plotWestSize);
                        Log.e("plotNorthsize", plotNorthsize);
                        Log.e("plotSouthSize", plotSouthSize);
                        Log.e("plotTotalAreaSqureFoot", plotTotalAreaSqureFoot);
                        Log.e("plotPricePerSqureFoot", plotPricePerSqureFoot);
                        Log.e("plotTotalSellingPrice", plotTotalSellingPrice);

                        long id = db.PlotInsert(projectId, plotAllocatedCustomerId, plotNumber, plotEastSize,
                                plotWestSize, plotNorthsize, plotSouthSize, plotRegisterDate,
                                plotTotalAreaSqureFoot, plotPricePerSqureFoot, plotTotalSellingPrice, plotStatus,
                                plotRemark);

                        if (id == 0) {
                           // Toast.makeText(AddPlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                        } else {
                         //   Toast.makeText(AddPlot.this, "Inserted Row " + id, Toast.LENGTH_SHORT).show();

                        }
                        finish();
                    } else {
                        Log.e("***********Row Empty", "***********Empty Row");

                    }


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

    private void readFileXLXS(Context context, Uri filename) {

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e(TAG, "Storage not available or read only");
            return;
        }

        try {


            InputStream stream = getContentResolver().openInputStream(filename);

            Workbook wb = WorkbookFactory.create(stream);
            DataFormatter objDefaultFormat = new DataFormatter();
            FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(((XSSFWorkbook) wb));

            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> objIterator = sheet.rowIterator();

            while (objIterator.hasNext()) {
                Row row = objIterator.next();

                if (row.getRowNum() == 0) {
                    Log.e("Dont Fetch data ", "************* First Row");
                } else if (row != null) {


                    if (!isRowEmpty(row)) {
                        Log.e("Insert Data", "Insert Data");
                        projectId = ProjectDetails.ProjectID;
                        plotid = "We Cannot Insert Here";
                        plotAllocatedCustomerId = "NA";

                        // cell 1
                        Cell cellValue1 = row.getCell(0);
                        objFormulaEvaluator.evaluate(cellValue1); // This will evaluate the cell, And any type of cell will return string value
                        plotNumber = objDefaultFormat.formatCellValue(cellValue1, objFormulaEvaluator);

                        // cell 2
                        Cell cellValue2 = row.getCell(1);
                        objFormulaEvaluator.evaluate(cellValue2); // This will evaluate the cell, And any type of cell will return string value
                        plotEastSize = objDefaultFormat.formatCellValue(cellValue2, objFormulaEvaluator);

                        // cell 2

                        Cell cellValue3 = row.getCell(2);
                        objFormulaEvaluator.evaluate(cellValue3); // This will evaluate the cell, And any type of cell will return string value
                        plotWestSize = objDefaultFormat.formatCellValue(cellValue3, objFormulaEvaluator);

                        // cell 4

                        Cell cellValue4 = row.getCell(3);
                        objFormulaEvaluator.evaluate(cellValue4); // This will evaluate the cell, And any type of cell will return string value
                        plotNorthsize = objDefaultFormat.formatCellValue(cellValue4, objFormulaEvaluator);

                        // cell 5

                        Cell cellValue5 = row.getCell(4);
                        objFormulaEvaluator.evaluate(cellValue5); // This will evaluate the cell, And any type of cell will return string value
                        plotSouthSize = objDefaultFormat.formatCellValue(cellValue5, objFormulaEvaluator);

                        plotRegisterDate = "NA";

                        // cell 6

                        Cell cellValue6 = row.getCell(5);
                        objFormulaEvaluator.evaluate(cellValue6); // This will evaluate the cell, And any type of cell will return string value
                        plotTotalAreaSqureFoot = objDefaultFormat.formatCellValue(cellValue6, objFormulaEvaluator);

                        // cell 7

                        Cell cellValue7 = row.getCell(6);
                        objFormulaEvaluator.evaluate(cellValue7); // This will evaluate the cell, And any type of cell will return string value
                        plotPricePerSqureFoot = objDefaultFormat.formatCellValue(cellValue7, objFormulaEvaluator);

                        // cell 8

                        Cell cellValue8 = row.getCell(7);
                        objFormulaEvaluator.evaluate(cellValue8); // This will evaluate the cell, And any type of cell will return string value
                        plotTotalSellingPrice = objDefaultFormat.formatCellValue(cellValue8, objFormulaEvaluator);

                        plotStatus = Constants.PLOTNOTBOOK;
                        plotRemark = "NA";


                        Log.e("plotNumber", plotNumber);
                        Log.e("plotEastSize", plotEastSize);
                        Log.e("plotWestSize", plotWestSize);
                        Log.e("plotNorthsize", plotNorthsize);
                        Log.e("plotSouthSize", plotSouthSize);
                        Log.e("plotTotalAreaSqureFoot", plotTotalAreaSqureFoot);
                        Log.e("plotPricePerSqureFoot", plotPricePerSqureFoot);
                        Log.e("plotTotalSellingPrice", plotTotalSellingPrice);

                        long id = db.PlotInsert(projectId, plotAllocatedCustomerId, plotNumber, plotEastSize,
                                plotWestSize, plotNorthsize, plotSouthSize, plotRegisterDate,
                                plotTotalAreaSqureFoot, plotPricePerSqureFoot, plotTotalSellingPrice, plotStatus,
                                plotRemark);

                        if (id == 0) {
                        //    Toast.makeText(AddPlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                        } else {
                         //   Toast.makeText(AddPlot.this, "Inserted Row " + id, Toast.LENGTH_SHORT).show();

                        }
                        finish();
                    } else {
                        Log.e("Row Empty", "Row Empty");

                    }


                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case 1:
                if (resultCode == RESULT_OK) {

                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    String path = myFile.getAbsolutePath();
                    String displayName = null;

                    if (uriString.startsWith("content://")) {
                        Cursor cursor = null;
                        try {
                            cursor = getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null && cursor.moveToFirst()) {
                                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                                Log.e(" If", displayName);

                            }
                        } finally {
                            cursor.close();
                        }

                    } else if (uriString.startsWith("file://")) {
                        displayName = myFile.getName();
                        Log.e("Else If", displayName);
                    }


                    if (uriString.endsWith("xlsx")) {
                        readFileXLXS(this, uri);

                      //  Toast.makeText(this, "XLLX  URI Vala Aahe", Toast.LENGTH_SHORT).show();
                    } else if (uriString.endsWith("xls")) {
                        readFileXLS(this, displayName);
                      //  Toast.makeText(this, "XLS Vala Aahe ", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(AddPlot.this, "File Not Found", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void getAllPlot(){
        Log.e("***********************", "****************************************");
        bookPlotDataBases.addAll(db.BookPlotGetAllBookedPlot());

        for (int i = 0; i < bookPlotDataBases.size(); i++) {
            BookPlotDataBase bookPlotDataBase = bookPlotDataBases.get(i);


            Log.e("Project ID", String.valueOf(bookPlotDataBase.getBookID()));



            Log.e("***********************", "****************************************");


        }
    }
}
