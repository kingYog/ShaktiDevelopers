package com.yogeshborhade.shaktidevelopers.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yogeshborhade.shaktidevelopers.DatabaseClas.BookPlotDataBase;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceClas;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceNameClass;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseFarmerClass;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseNotification;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseTransaction;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabasePlotClass;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabaseProjectClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.DATABASE_NAME_SHAKTI_DEVELOPER;
import static com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabasePlotClass.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = DATABASE_NAME_SHAKTI_DEVELOPER;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create Project table
        db.execSQL(DatabaseProjectClass.CREATE_TABLE);
        // Create Plot Table
        db.execSQL(DatabasePlotClass.CREATE_TABLE);
        // Create Customer Table
        db.execSQL(DataBaseCustomerClass.CREATE_TABLE);
        //Create Farmer Table
        db.execSQL(DataBaseFarmerClass.CREATE_TABLE);
        //Create Expence Table
        db.execSQL(DataBaseExpenceClas.CREATE_TABLE);

        //Create Expence Table
        db.execSQL(DataBaseTransaction.CREATE_TABLE);

        // Create NotificationPage Table
        db.execSQL(DataBaseNotification.CREATE_TABLE);

        // Create Book Plot Table
        db.execSQL(BookPlotDataBase.CREATE_TABLE);

        db.execSQL(DataBaseExpenceNameClass.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed Project Table
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseProjectClass.TABLE_NAME);

        // Drop older table if existed PLOT Table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


        // Drop older table if existed Customer Table
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseCustomerClass.TABLE_NAME);


        // Drop older table if existed Farmer Table
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseFarmerClass.TABLE_NAME);


        // Drop older table if existed Expence Table
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseExpenceClas.TABLE_NAME);


        // Drop older table if existed Transaction Table
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseTransaction.TABLE_NAME);

        // Drop older table if existed NotificationPage Table
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseNotification.TABLE_NAME);


        // Drop older table if existed book Plot Table
        db.execSQL("DROP TABLE IF EXISTS " + BookPlotDataBase.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + DataBaseExpenceNameClass.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    //**************************************Project Related Queries********************
    public long ProjectInsert(String projectName, String projectAdress, String projectAddedDate, String projectTotalArea, String projectEastSide,
                              String projectWestSide, String projectNorthSide, String projectSouthSide, String project_total_available_plots, String project_total_sale_plots, String project_total_expence, String project_total_earn, String project_total_profit) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DatabaseProjectClass.PROJECT_NAME, projectName);
        values.put(DatabaseProjectClass.PROJECT_ADRESS, projectAdress);
        values.put(DatabaseProjectClass.PROJECT_ADDED_DATE, projectAddedDate);
        values.put(DatabaseProjectClass.PROJECT_TOTAL_AREA, projectTotalArea);
        values.put(DatabaseProjectClass.PROJECT_EAST_SIDE, projectEastSide);
        values.put(DatabaseProjectClass.PROJECT_WEST_SIDE, projectWestSide);
        values.put(DatabaseProjectClass.PROJECT_NORTH_SIDE, projectNorthSide);
        values.put(DatabaseProjectClass.PROJECT_SOUTH_SIDE, projectSouthSide);
        values.put(DatabaseProjectClass.PROJECT_TOTAL_AVAILABALE_PLOTS, project_total_available_plots);
        values.put(DatabaseProjectClass.PROJECT_TOTAL_SALE_PLOTS, project_total_sale_plots);
        values.put(DatabaseProjectClass.PROJECT_TOTAL_EXPENCE, project_total_expence);
        values.put(DatabaseProjectClass.PROJECT_TOTAL_EARN, project_total_earn);
        values.put(DatabaseProjectClass.PROJECT_TOTAL_PROFIT, project_total_profit);


        // insert row
        long id = db.insert(DatabaseProjectClass.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<DatabaseProjectClass> ProjectGetAll() {
        List<DatabaseProjectClass> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseProjectClass.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseProjectClass note = new DatabaseProjectClass();
                note.setProject_id(cursor.getInt(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_ID)));
                note.setProject_name(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_NAME)));
                note.setProject_adress(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_ADRESS)));
                note.setProject_added_date(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_ADDED_DATE)));
                note.setProject_totalArea(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_AREA)));
                note.setProject_east_side(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_EAST_SIDE)));
                note.setProject_west_side(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_WEST_SIDE)));
                note.setProject_north_side(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_NORTH_SIDE)));
                note.setProject_south_side(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_SOUTH_SIDE)));
                note.setProject_total_available_plots(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_AVAILABALE_PLOTS)));
                note.setProject_total_sale_plots(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_SALE_PLOTS)));
                note.setProject_total_expence(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_EXPENCE)));
                note.setProject_total_earn(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_EARN)));
                note.setProject_total_profit(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_PROFIT)));


                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public List<DatabaseProjectClass> ProjectGetSingle(int id) {
        List<DatabaseProjectClass> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseProjectClass.TABLE_NAME + " WHERE " + DatabaseProjectClass.PROJECT_ID + "=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseProjectClass note = new DatabaseProjectClass();
                note.setProject_id(cursor.getInt(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_ID)));
                note.setProject_name(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_NAME)));
                note.setProject_adress(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_ADRESS)));
                note.setProject_added_date(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_ADDED_DATE)));
                note.setProject_totalArea(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_AREA)));
                note.setProject_east_side(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_EAST_SIDE)));
                note.setProject_west_side(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_WEST_SIDE)));
                note.setProject_north_side(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_NORTH_SIDE)));
                note.setProject_south_side(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_SOUTH_SIDE)));
                note.setProject_total_available_plots(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_AVAILABALE_PLOTS)));
                note.setProject_total_sale_plots(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_SALE_PLOTS)));
                note.setProject_total_expence(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_EXPENCE)));
                note.setProject_total_earn(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_EARN)));
                note.setProject_total_profit(cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_TOTAL_PROFIT)));

                Log.e("Project Name", cursor.getString(cursor.getColumnIndex(DatabaseProjectClass.PROJECT_NAME)));


                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }


    public int ProjectUpdate(int projectId, String projectName, String projectAdress, String projectAddedDate, String projectTotalArea, String projectEastSide,
                             String projectWestSide, String projectNorthSide, String projectSouthSide) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseProjectClass.PROJECT_NAME, projectName);
        values.put(DatabaseProjectClass.PROJECT_ADRESS, projectAdress);
        values.put(DatabaseProjectClass.PROJECT_ADDED_DATE, projectAddedDate);
        values.put(DatabaseProjectClass.PROJECT_TOTAL_AREA, projectTotalArea);
        values.put(DatabaseProjectClass.PROJECT_EAST_SIDE, projectEastSide);
        values.put(DatabaseProjectClass.PROJECT_WEST_SIDE, projectWestSide);
        values.put(DatabaseProjectClass.PROJECT_NORTH_SIDE, projectNorthSide);
        values.put(DatabaseProjectClass.PROJECT_SOUTH_SIDE, projectSouthSide);

        int rowsUpdated = db.update(DatabaseProjectClass.TABLE_NAME, values, DatabaseProjectClass.PROJECT_ID + "=" + projectId, null);
        return rowsUpdated;
    }

    public int ProjectDelete(int projectId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DatabaseProjectClass.TABLE_NAME, DatabaseProjectClass.PROJECT_ID + " = " + projectId, null);
        db.close();
        return rowDeleted;
    }

    public int projectAddPlot(String ProjectID) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + DatabaseProjectClass.TABLE_NAME +
                " SET " + DatabaseProjectClass.PROJECT_TOTAL_AVAILABALE_PLOTS + "=IFNULL(" + DatabaseProjectClass.PROJECT_TOTAL_AVAILABALE_PLOTS + ",0)+1" +
                " WHERE " + DatabaseProjectClass.PROJECT_ID + "=" + ProjectID);
        db.close();
        return 0;

    }

    public int projectSalePlot(String ProjectID) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + DatabaseProjectClass.TABLE_NAME +
                " SET " + DatabaseProjectClass.PROJECT_TOTAL_SALE_PLOTS + "=IFNULL(" + DatabaseProjectClass.PROJECT_TOTAL_SALE_PLOTS + ",0)+1" +
                " WHERE " + DatabaseProjectClass.PROJECT_ID + "=" + ProjectID);
        db.close();
        return 0;

    }

    public int getPlotCountOfTotalPlot(String ProjectID) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + DatabasePlotClass.PROJECT_ID + "=" + ProjectID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }

    public int getPlotCountOfTotalRemaining(String ProjectID, String Status) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + DatabasePlotClass.PROJECT_ID + "=" + ProjectID + " AND " + DatabasePlotClass.PLOT_STATUS + "=" + Status;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }

    public int getPlotCountOfTotalBooked(String ProjectID, String Status) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + DatabasePlotClass.PROJECT_ID + "=" + ProjectID + " AND " + DatabasePlotClass.PLOT_STATUS + "=" + Status;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }


    //**************************************PLOT Related Queries********************

    public long PlotInsert(String project_Id, String plot_allocated_customer_id, String plot_number,
                           String plot_east_side, String plot_west_side,
                           String plot_north_side, String plot_south_side, String plot_register_date,
                           String ploto_total_area_squre_foot, String plot_price_per_squre_foot,
                           String plot_total_price, String plot_status, String plot_Remark) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DatabasePlotClass.PROJECT_ID, project_Id);
        values.put(DatabasePlotClass.PLOT_ALLOCATED_CUSTOMER_ID, plot_allocated_customer_id);
        values.put(DatabasePlotClass.PLOT_NUMBER, plot_number);
        values.put(DatabasePlotClass.PLOT_EAST_SIDE, plot_east_side);
        values.put(DatabasePlotClass.PLOT_WEST_SIDE, plot_west_side);
        values.put(DatabasePlotClass.PLOT_NORTH_SIDE, plot_north_side);
        values.put(DatabasePlotClass.PLOT_SOUTH_SIDE, plot_south_side);
        values.put(DatabasePlotClass.PLOT_REGISTER_DATE, plot_register_date);
        values.put(DatabasePlotClass.PLOT_TOTAL_AREA_SQURE_FOOT, ploto_total_area_squre_foot);
        values.put(DatabasePlotClass.PLOT_PRICE_PER_SQURE_FOOT, plot_price_per_squre_foot);
        values.put(DatabasePlotClass.PLOT_TOTAL_PRICE, plot_total_price);
        values.put(DatabasePlotClass.PLOT_STATUS, plot_status);
        values.put(DatabasePlotClass.PLOT_REMARK, plot_Remark);


        // insert row
        long id = db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<DatabasePlotClass> PlotGetAll(String ProjectID) {
        List<DatabasePlotClass> databasePlotClasses = new ArrayList<>();

        // Select All Query
        //  String selectQuery = "SELECT  * FROM " + DatabasePlotClass.TABLE_NAME;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + DatabasePlotClass.PROJECT_ID + "=" + ProjectID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabasePlotClass note = new DatabasePlotClass();

                note.setPlot_Id(cursor.getInt(cursor.getColumnIndex(DatabasePlotClass.PLOT_ID)));
                note.setProject_Id(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PROJECT_ID)));
                note.setPlot_allocated_customer_id(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_ALLOCATED_CUSTOMER_ID)));
                note.setPlot_number(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_NUMBER)));
                note.setPlot_east_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_EAST_SIDE)));
                note.setPlot_west_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_WEST_SIDE)));
                note.setPlot_north_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_NORTH_SIDE)));
                note.setPlot_south_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_SOUTH_SIDE)));
                note.setPlot_register_date(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_REGISTER_DATE)));
                note.setPloto_total_area_squre_foot(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_TOTAL_AREA_SQURE_FOOT)));
                note.setPlot_price_per_squre_foot(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_PRICE_PER_SQURE_FOOT)));
                note.setPlot_total_price(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_TOTAL_PRICE)));
                note.setPlot_status(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_STATUS)));
                note.setPlot_Remark(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_REMARK)));


                databasePlotClasses.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return databasePlotClasses;
    }

    public List<DatabasePlotClass> PlotGetSingle(int plotId, String ProjectID) {
        List<DatabasePlotClass> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + DatabasePlotClass.PLOT_ID + "=" + plotId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabasePlotClass note = new DatabasePlotClass();
                note.setPlot_Id(cursor.getInt(cursor.getColumnIndex(DatabasePlotClass.PLOT_ID)));
                note.setProject_Id(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PROJECT_ID)));
                note.setPlot_allocated_customer_id(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_ALLOCATED_CUSTOMER_ID)));
                note.setPlot_number(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_NUMBER)));
                note.setPlot_east_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_EAST_SIDE)));
                note.setPlot_west_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_WEST_SIDE)));
                note.setPlot_north_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_NORTH_SIDE)));
                note.setPlot_south_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_SOUTH_SIDE)));
                note.setPlot_register_date(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_REGISTER_DATE)));
                note.setPloto_total_area_squre_foot(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_TOTAL_AREA_SQURE_FOOT)));
                note.setPlot_price_per_squre_foot(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_PRICE_PER_SQURE_FOOT)));
                note.setPlot_total_price(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_TOTAL_PRICE)));
                note.setPlot_status(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_STATUS)));
                note.setPlot_Remark(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_REMARK)));


                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int PlotUpdate(String PlotID, String project_Id, String plot_allocated_customer_id, String plot_number,
                          String plot_east_side, String plot_west_side,
                          String plot_north_side, String plot_south_side, String plot_register_date,
                          String ploto_total_area_squre_foot, String plot_price_per_squre_foot,
                          String plot_total_price, String plot_status, String plot_Remark) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabasePlotClass.PROJECT_ID, project_Id);
        values.put(DatabasePlotClass.PLOT_ALLOCATED_CUSTOMER_ID, plot_allocated_customer_id);
        values.put(DatabasePlotClass.PLOT_NUMBER, plot_number);
        values.put(DatabasePlotClass.PLOT_EAST_SIDE, plot_east_side);
        values.put(DatabasePlotClass.PLOT_WEST_SIDE, plot_west_side);
        values.put(DatabasePlotClass.PLOT_NORTH_SIDE, plot_north_side);
        values.put(DatabasePlotClass.PLOT_SOUTH_SIDE, plot_south_side);
        values.put(DatabasePlotClass.PLOT_REGISTER_DATE, plot_register_date);
        values.put(DatabasePlotClass.PLOT_TOTAL_AREA_SQURE_FOOT, ploto_total_area_squre_foot);
        values.put(DatabasePlotClass.PLOT_PRICE_PER_SQURE_FOOT, plot_price_per_squre_foot);
        values.put(DatabasePlotClass.PLOT_TOTAL_PRICE, plot_total_price);
        values.put(DatabasePlotClass.PLOT_STATUS, plot_status);
        values.put(DatabasePlotClass.PLOT_REMARK, plot_Remark);


        int rowsUpdated = db.update(TABLE_NAME, values, DatabasePlotClass.PLOT_ID + "=" + PlotID, null);
        return rowsUpdated;
    }

    public int PlotBookInsert(String PlotID, String plot_allocated_customer_id,
                              String plot_register_date,
                              String plot_status, String plot_Remark) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabasePlotClass.PLOT_ALLOCATED_CUSTOMER_ID, plot_allocated_customer_id);
        values.put(DatabasePlotClass.PLOT_REGISTER_DATE, plot_register_date);
        values.put(DatabasePlotClass.PLOT_STATUS, plot_status);
        values.put(DatabasePlotClass.PLOT_REMARK, plot_Remark);
        int rowsUpdated = db.update(TABLE_NAME, values, DatabasePlotClass.PLOT_ID + "=" + PlotID, null);
        return rowsUpdated;
    }

    public int PlotDelete(int plotID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(TABLE_NAME, DatabasePlotClass.PLOT_ID + " = " + plotID, null);
        db.close();
        return rowDeleted;
    }

    public int getTotalPlot(String ProjectID) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + DatabasePlotClass.PROJECT_ID + "=" + ProjectID;
        /*String countQuery = "SELECT  * FROM " + DatabasePlotClass.TABLE_NAME;*/

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public List<DatabasePlotClass> getRemainingPlot(String ProjectID, String status) {
        List<DatabasePlotClass> databasePlotClasses = new ArrayList<>();

        // Select All Query
        //  String selectQuery = "SELECT  * FROM " + DatabasePlotClass.TABLE_NAME;
        //  String selectQuery = "SELECT  * FROM " + DatabasePlotClass.TABLE_NAME + " WHERE " + DatabasePlotClass.PROJECT_ID + "=" + ProjectID+ " AND " +DatabasePlotClass.PLOT_STATUS + "=" +"remaining";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + DatabasePlotClass.PLOT_STATUS + "=" + status;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabasePlotClass note = new DatabasePlotClass();

                note.setPlot_Id(cursor.getInt(cursor.getColumnIndex(DatabasePlotClass.PLOT_ID)));
                note.setProject_Id(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PROJECT_ID)));
                note.setPlot_allocated_customer_id(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_ALLOCATED_CUSTOMER_ID)));
                note.setPlot_number(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_NUMBER)));
                note.setPlot_east_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_EAST_SIDE)));
                note.setPlot_west_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_WEST_SIDE)));
                note.setPlot_north_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_NORTH_SIDE)));
                note.setPlot_south_side(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_SOUTH_SIDE)));
                note.setPlot_register_date(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_REGISTER_DATE)));
                note.setPloto_total_area_squre_foot(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_TOTAL_AREA_SQURE_FOOT)));
                note.setPlot_price_per_squre_foot(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_PRICE_PER_SQURE_FOOT)));
                note.setPlot_total_price(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_TOTAL_PRICE)));
                note.setPlot_status(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_STATUS)));
                note.setPlot_Remark(cursor.getString(cursor.getColumnIndex(DatabasePlotClass.PLOT_REMARK)));


                databasePlotClasses.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return databasePlotClasses;
    }

    // Update Plot With Defferetn Parameters

    public int PlotUpdate(String PlotID, String plot_allocated_customer_id, String plot_register_date,
                          String plot_status, String plot_Remark) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabasePlotClass.PLOT_ALLOCATED_CUSTOMER_ID, plot_allocated_customer_id);
        values.put(DatabasePlotClass.PLOT_REGISTER_DATE, plot_register_date);
        values.put(DatabasePlotClass.PLOT_STATUS, plot_status);
        values.put(DatabasePlotClass.PLOT_REMARK, plot_Remark);
        int rowsUpdated = db.update(TABLE_NAME, values, DatabasePlotClass.PLOT_ID + "=" + PlotID, null);
        return rowsUpdated;
    }

    //**************************************Customer Related Queries********************

    public long CustomerInsert(String ProjectID, String customerName, String customerMobileNumber, String customerAddress,
                               String customerDateOfBirth, String customerAge, String customerPanCardNumber, String customerPanCardImage, String customerAdharCardNumber, String customerStatus) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseCustomerClass.PROJECT_ID, ProjectID);
        values.put(DataBaseCustomerClass.CUSTOMER_NAME, customerName);
        values.put(DataBaseCustomerClass.CUSTOMER_MOBILE_NUMBER, customerMobileNumber);
        values.put(DataBaseCustomerClass.CUSTOMER_ADDRESS, customerAddress);
        values.put(DataBaseCustomerClass.CUSTOMER_DATE_OF_BIRTH, customerDateOfBirth);
        values.put(DataBaseCustomerClass.CUSTOMER_AGE, customerAge);
        values.put(DataBaseCustomerClass.CUSTOMER_PAN_CARD_NUMBER, customerPanCardNumber);
        values.put(DataBaseCustomerClass.CUSTOMER_PAN_CARD_IMAGE, customerPanCardImage);
        values.put(DataBaseCustomerClass.CUSTOMER_ADHAR_CARD_NUMBER, customerAdharCardNumber);
        values.put(DataBaseCustomerClass.CUSTOMER_STATUS, customerStatus);


        // insert row
        long id = db.insert(DataBaseCustomerClass.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public List<DataBaseCustomerClass> CustomerGetAll(String ProjectID) {
        List<DataBaseCustomerClass> dataBaseCustomerClasses = new ArrayList<>();

        // Select All Query
        //  String selectQuery = "SELECT  * FROM " + DatabasePlotClass.TABLE_NAME;
        String selectQuery = "SELECT  * FROM " + DataBaseCustomerClass.TABLE_NAME + " WHERE " + DataBaseCustomerClass.PROJECT_ID + "=" + ProjectID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseCustomerClass note = new DataBaseCustomerClass();

                note.setCustomerID(cursor.getInt(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_ID)));
                note.setProjectID(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.PROJECT_ID)));
                note.setCustomerName(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_NAME)));
                note.setCustomerMobileNumber(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_MOBILE_NUMBER)));
                note.setCustomerAddress(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_ADDRESS)));
                note.setCustomerDateOfBirth(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_DATE_OF_BIRTH)));
                note.setCustomerAge(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_AGE)));
                note.setCustomerPanCardNumber(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_PAN_CARD_NUMBER)));
                note.setCustomerPanCardImage(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_PAN_CARD_IMAGE)));
                note.setCustomerAdharCardNumber(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_ADHAR_CARD_NUMBER)));
                note.setCustomerStatus(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_STATUS)));

                dataBaseCustomerClasses.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseCustomerClasses;
    }


    public String CustomerGetName(int customerId) {

        String customerName = "Not Found";
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseCustomerClass.TABLE_NAME + " WHERE " + DataBaseCustomerClass.CUSTOMER_ID + "=" + customerId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseCustomerClass note = new DataBaseCustomerClass();
                customerName = cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_NAME));

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return customerName;
    }

    public List<DataBaseCustomerClass> CustomerGetSingle(int customerId, String ProjectID) {
        List<DataBaseCustomerClass> dataBaseCustomerClasses = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseCustomerClass.TABLE_NAME + " WHERE " + DataBaseCustomerClass.CUSTOMER_ID + "=" + customerId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseCustomerClass note = new DataBaseCustomerClass();

                note.setCustomerID(cursor.getInt(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_ID)));
                note.setProjectID(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.PROJECT_ID)));
                Log.e("ProjectID", cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.PROJECT_ID)));
                note.setCustomerName(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_NAME)));
                note.setCustomerMobileNumber(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_MOBILE_NUMBER)));
                note.setCustomerAddress(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_ADDRESS)));
                note.setCustomerDateOfBirth(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_DATE_OF_BIRTH)));
                note.setCustomerAge(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_AGE)));
                note.setCustomerPanCardNumber(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_PAN_CARD_NUMBER)));
                note.setCustomerPanCardImage(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_PAN_CARD_IMAGE)));
                note.setCustomerAdharCardNumber(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_ADHAR_CARD_NUMBER)));
                note.setCustomerStatus(cursor.getString(cursor.getColumnIndex(DataBaseCustomerClass.CUSTOMER_STATUS)));

                dataBaseCustomerClasses.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseCustomerClasses;
    }


    public int CustomerUpdate(String ProjectID, String customerId, String customerName, String customerMobileNumber, String customerAddress,
                              String customerDateOfBirth, String customerAge, String customerPanCardNumber, String customerAdharCardNumber, String customerStatus) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseCustomerClass.PROJECT_ID, ProjectID);
        values.put(DataBaseCustomerClass.CUSTOMER_NAME, customerName);
        values.put(DataBaseCustomerClass.CUSTOMER_MOBILE_NUMBER, customerMobileNumber);
        values.put(DataBaseCustomerClass.CUSTOMER_ADDRESS, customerAddress);
        values.put(DataBaseCustomerClass.CUSTOMER_DATE_OF_BIRTH, customerDateOfBirth);
        values.put(DataBaseCustomerClass.CUSTOMER_AGE, customerAge);
        values.put(DataBaseCustomerClass.CUSTOMER_PAN_CARD_NUMBER, customerPanCardNumber);
        values.put(DataBaseCustomerClass.CUSTOMER_ADHAR_CARD_NUMBER, customerAdharCardNumber);
        values.put(DataBaseCustomerClass.CUSTOMER_STATUS, customerStatus);


        // return newly inserted row id

        int rowsUpdated = db.update(DataBaseCustomerClass.TABLE_NAME, values, DataBaseCustomerClass.CUSTOMER_ID + "=" + customerId, null);
        db.close();

        return rowsUpdated;
    }

    public int CustomerUpdatePanCard(String customerId, String customerPanCardImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseCustomerClass.CUSTOMER_PAN_CARD_IMAGE, customerPanCardImage);


        // return newly inserted row id

        int rowsUpdated = db.update(DataBaseCustomerClass.TABLE_NAME, values, DataBaseCustomerClass.CUSTOMER_ID + "=" + customerId, null);
        db.close();

        return rowsUpdated;
    }


    public int CustomerUpdateBookPlot(String ProjectID, String customerId, String customerBuyingPlotID, String customerPlotEastSize,
                                      String customerPlotWestSize, String customerPlotNorthSize, String customerPlotSouthSize, String customerTotalBuyingArea, String customerRatePerSqureFoot,
                                      String customerTotalAmountToBePaid, String customerAmountRescieved, String customerAmountResCievedDate, String customerPendingAmount, String customerPendingAmountCommitmentDate,
                                      String customerOtherCharges, String customerNotificationrRemark, String customerNotificationrRemarkDate, String customerStatus) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseCustomerClass.PROJECT_ID, ProjectID);
        values.put(DataBaseCustomerClass.CUSTOMER_STATUS, customerStatus);


        // return newly inserted row id

        int rowsUpdated = db.update(DataBaseCustomerClass.TABLE_NAME, values, DataBaseCustomerClass.CUSTOMER_ID + "=" + customerId, null);
        db.close();

        return rowsUpdated;
    }


    public int CustomerDelete(int customerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseCustomerClass.TABLE_NAME, DataBaseCustomerClass.CUSTOMER_ID + " = " + customerId, null);
        db.close();
        return rowDeleted;
    }


    //**************************************FARMER RELATED QUIREIS ****************

    public long FarmerInsert(String ProjectID, String farmerName, String farmerMobileNumber, String farmerAddress,
                             String farmerDateOfBirth, String farmerAge, String farmerPanCardNumber, String farmerAdharCardNumber, String farmerSellingPlotID, String farmerPlotEastSize,
                             String farmerPlotWestSize, String farmerPlotNorthSize, String farmerPlotSouthSize, String farmerTotalSellingArea, String farmerRatePerSqureFoot,
                             String farmerTotalAmountToBePaid, String farmerAmountgiven, String farmerAmountgivenDate, String farmerPendingAmount, String farmerPendingAmountCommitmentDate,
                             String farmerOtherCharges, String farmerNotificationrRemark, String farmerNotificationrRemarkDate, String farmerStatus, String farmerPanCardImage) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseFarmerClass.PROJECT_ID, ProjectID);
        values.put(DataBaseFarmerClass.FARMER_NAME, farmerName);
        values.put(DataBaseFarmerClass.FARMER_MOBILE_NUMBER, farmerMobileNumber);
        values.put(DataBaseFarmerClass.FARMER_ADDRESS, farmerAddress);
        values.put(DataBaseFarmerClass.FARMER_DATE_OF_BIRTH, farmerDateOfBirth);
        values.put(DataBaseFarmerClass.FARMER_AGE, farmerAge);

        values.put(DataBaseFarmerClass.FARMER_PAN_CARD_NUMBER, farmerPanCardNumber);
        values.put(DataBaseFarmerClass.FARMER_ADHAR_CARD_NUMBER, farmerAdharCardNumber);
        values.put(DataBaseFarmerClass.FARMER_SELLING_PLOT_ID, farmerSellingPlotID);
        values.put(DataBaseFarmerClass.FARMER_PLOT_EAST_SIZE, farmerPlotEastSize);
        values.put(DataBaseFarmerClass.FARMER_PLOT_WEST_SIZE, farmerPlotWestSize);
        values.put(DataBaseFarmerClass.FARMER_PLOT_NORTH_SIZE, farmerPlotNorthSize);
        values.put(DataBaseFarmerClass.FARMER_PLOT_SOUTH_SIZE, farmerPlotSouthSize);
        values.put(DataBaseFarmerClass.FARMER_TOTAL_SELLING_AREA, farmerTotalSellingArea);
        values.put(DataBaseFarmerClass.FARMER_RATE_PER_SQURE_FOOT, farmerRatePerSqureFoot);
        values.put(DataBaseFarmerClass.FARMER_TOTAL_AMOUNT_TO_BE_PAID, farmerTotalAmountToBePaid);
        values.put(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN, farmerAmountgiven);
        values.put(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN_DATE, farmerAmountgivenDate);
        values.put(DataBaseFarmerClass.FARMER_PENDING_AMOUNT, farmerPendingAmount);
        values.put(DataBaseFarmerClass.FARMER_PENDING_AMOUNT_COMMITMENT_DATE, farmerPendingAmountCommitmentDate);
        values.put(DataBaseFarmerClass.FARMER_OTHERE_CHARGES, farmerOtherCharges);
        values.put(DataBaseFarmerClass.FARMER_NOTIFICATION_REMARK, farmerNotificationrRemark);
        values.put(DataBaseFarmerClass.FARMER_NOTIFICATION_REMARK_DATE, farmerNotificationrRemarkDate);
        values.put(DataBaseFarmerClass.FARMER_STATUS, farmerStatus);
        values.put(DataBaseFarmerClass.FARMER_PAN_CARD_IMAGE, farmerPanCardImage);


        // insert row
        long id = db.insert(DataBaseFarmerClass.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public List<DataBaseFarmerClass> FarmerGetAll(String ProjectID) {
        List<DataBaseFarmerClass> dataBaseFarmerClasses = new ArrayList<>();

        // Select All Query
        //  String selectQuery = "SELECT  * FROM " + DatabasePlotClass.TABLE_NAME;
        String selectQuery = "SELECT  * FROM " + DataBaseFarmerClass.TABLE_NAME + " WHERE " + DataBaseFarmerClass.PROJECT_ID + "=" + ProjectID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseFarmerClass note = new DataBaseFarmerClass();

                note.setFarmerID(cursor.getInt(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_ID)));
                note.setProjectID(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.PROJECT_ID)));
                note.setFarmerName(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_NAME)));
                note.setFarmerMobileNumber(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_MOBILE_NUMBER)));
                note.setFarmerAddress(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_ADDRESS)));
                note.setFarmerDateOfBirth(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_DATE_OF_BIRTH)));
                note.setFarmerAge(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_AGE)));

                note.setFarmerPanCardNumber(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PAN_CARD_NUMBER)));
                note.setFarmerAdharCardNumber(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_ADHAR_CARD_NUMBER)));
                note.setFarmerSellingPlotID(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_SELLING_PLOT_ID)));
                note.setFarmerPlotEastSize(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PLOT_EAST_SIZE)));
                note.setFarmerPlotWestSize(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PLOT_WEST_SIZE)));
                note.setFarmerPlotNorthSize(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PLOT_NORTH_SIZE)));
                note.setFarmerPlotSouthSize(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PLOT_SOUTH_SIZE)));
                note.setFarmerTotalSellingArea(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_TOTAL_SELLING_AREA)));
                note.setFarmerRatePerSqureFoot(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_RATE_PER_SQURE_FOOT)));
                note.setFarmerTotalAmountToBePaid(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_TOTAL_AMOUNT_TO_BE_PAID)));
                note.setFarmerAmountgiven(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN)));
                note.setFarmerAmountgivenDate(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN_DATE)));
                note.setFarmerPendingAmount(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PENDING_AMOUNT)));
                note.setFarmerPendingAmountCommitmentDate(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PENDING_AMOUNT_COMMITMENT_DATE)));
                note.setFarmerOtherCharges(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_OTHERE_CHARGES)));
                note.setFarmerNotificationrRemark(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_NOTIFICATION_REMARK)));
                note.setFarmerNotificationrRemarkDate(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_NOTIFICATION_REMARK_DATE)));
                note.setFarmerStatus(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_STATUS)));
                note.setFarmer_payment_type(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PAYMENT_TYPE)));
                note.setFarmer_payment_number(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PAYMENT_NUMBER)));
                note.setFarmerPanCardImage(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PAN_CARD_IMAGE)));

                dataBaseFarmerClasses.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseFarmerClasses;
    }


    public List<DataBaseFarmerClass> FarmerGetSingle(int customerId, String ProjectID) {
        List<DataBaseFarmerClass> dataBaseFarmerClasses = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseFarmerClass.TABLE_NAME + " WHERE " + DataBaseFarmerClass.FARMER_ID + "=" + customerId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseFarmerClass note = new DataBaseFarmerClass();


                note.setFarmerID(cursor.getInt(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_ID)));
                note.setProjectID(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.PROJECT_ID)));
                note.setFarmerName(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_NAME)));
                note.setFarmerMobileNumber(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_MOBILE_NUMBER)));
                note.setFarmerAddress(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_ADDRESS)));
                note.setFarmerDateOfBirth(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_DATE_OF_BIRTH)));
                note.setFarmerAge(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_AGE)));
                note.setFarmerPanCardNumber(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PAN_CARD_NUMBER)));
                note.setFarmerAdharCardNumber(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_ADHAR_CARD_NUMBER)));
                note.setFarmerSellingPlotID(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_SELLING_PLOT_ID)));
                note.setFarmerPlotEastSize(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PLOT_EAST_SIZE)));
                note.setFarmerPlotWestSize(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PLOT_WEST_SIZE)));
                note.setFarmerPlotNorthSize(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PLOT_NORTH_SIZE)));
                note.setFarmerPlotSouthSize(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PLOT_SOUTH_SIZE)));
                note.setFarmerTotalSellingArea(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_TOTAL_SELLING_AREA)));
                note.setFarmerRatePerSqureFoot(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_RATE_PER_SQURE_FOOT)));
                note.setFarmerTotalAmountToBePaid(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_TOTAL_AMOUNT_TO_BE_PAID)));
                note.setFarmerAmountgiven(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN)));
                note.setFarmerAmountgivenDate(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN_DATE)));
                note.setFarmerPendingAmount(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PENDING_AMOUNT)));
                note.setFarmerPendingAmountCommitmentDate(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PENDING_AMOUNT_COMMITMENT_DATE)));
                note.setFarmerOtherCharges(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_OTHERE_CHARGES)));
                note.setFarmerNotificationrRemark(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_NOTIFICATION_REMARK)));
                note.setFarmerNotificationrRemarkDate(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_NOTIFICATION_REMARK_DATE)));
                note.setFarmerStatus(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_STATUS)));
                note.setFarmer_payment_type(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PAYMENT_TYPE)));
                note.setFarmer_payment_number(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PAYMENT_NUMBER)));
                note.setFarmerPanCardImage(cursor.getString(cursor.getColumnIndex(DataBaseFarmerClass.FARMER_PAN_CARD_IMAGE)));

                dataBaseFarmerClasses.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseFarmerClasses;
    }


    public int FarmerUpdate(String ProjectID, String farmerid, String farmerName, String farmerMobileNumber, String farmerAddress,
                            String farmerDateOfBirth, String farmerAge, String farmerPanCardNumber, String farmerAdharCardNumber, String farmerSellingPlotID, String farmerPlotEastSize,
                            String farmerPlotWestSize, String farmerPlotNorthSize, String farmerPlotSouthSize, String farmerTotalSellingArea, String farmerRatePerSqureFoot,
                            String farmerTotalAmountToBePaid, String farmerAmountgiven, String farmerAmountgivenDate, String farmerPendingAmount, String farmerPendingAmountCommitmentDate,
                            String farmerOtherCharges, String farmerNotificationrRemark, String farmerNotificationrRemarkDate, String farmerStatus, String farmer_payment_type, String farmer_payment_number) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseFarmerClass.PROJECT_ID, ProjectID);
        values.put(DataBaseFarmerClass.FARMER_NAME, farmerName);
        values.put(DataBaseFarmerClass.FARMER_MOBILE_NUMBER, farmerMobileNumber);
        values.put(DataBaseFarmerClass.FARMER_ADDRESS, farmerAddress);
        values.put(DataBaseFarmerClass.FARMER_DATE_OF_BIRTH, farmerDateOfBirth);
        values.put(DataBaseFarmerClass.FARMER_AGE, farmerAge);

        values.put(DataBaseFarmerClass.FARMER_PAN_CARD_NUMBER, farmerPanCardNumber);
        values.put(DataBaseFarmerClass.FARMER_ADHAR_CARD_NUMBER, farmerAdharCardNumber);
        values.put(DataBaseFarmerClass.FARMER_SELLING_PLOT_ID, farmerSellingPlotID);
        values.put(DataBaseFarmerClass.FARMER_PLOT_EAST_SIZE, farmerPlotEastSize);
        values.put(DataBaseFarmerClass.FARMER_PLOT_WEST_SIZE, farmerPlotWestSize);
        values.put(DataBaseFarmerClass.FARMER_PLOT_NORTH_SIZE, farmerPlotNorthSize);
        values.put(DataBaseFarmerClass.FARMER_PLOT_SOUTH_SIZE, farmerPlotSouthSize);
        values.put(DataBaseFarmerClass.FARMER_TOTAL_SELLING_AREA, farmerTotalSellingArea);
        values.put(DataBaseFarmerClass.FARMER_RATE_PER_SQURE_FOOT, farmerRatePerSqureFoot);
        values.put(DataBaseFarmerClass.FARMER_TOTAL_AMOUNT_TO_BE_PAID, farmerTotalAmountToBePaid);
        values.put(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN, farmerAmountgiven);
        values.put(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN_DATE, farmerAmountgivenDate);
        values.put(DataBaseFarmerClass.FARMER_PENDING_AMOUNT, farmerPendingAmount);
        values.put(DataBaseFarmerClass.FARMER_PENDING_AMOUNT_COMMITMENT_DATE, farmerPendingAmountCommitmentDate);
        values.put(DataBaseFarmerClass.FARMER_OTHERE_CHARGES, farmerOtherCharges);
        values.put(DataBaseFarmerClass.FARMER_NOTIFICATION_REMARK, farmerNotificationrRemark);
        values.put(DataBaseFarmerClass.FARMER_NOTIFICATION_REMARK_DATE, farmerNotificationrRemarkDate);
        values.put(DataBaseFarmerClass.FARMER_STATUS, farmerStatus);
        values.put(DataBaseFarmerClass.FARMER_PAYMENT_TYPE, farmer_payment_type);
        values.put(DataBaseFarmerClass.FARMER_PAYMENT_NUMBER, farmer_payment_number);

        // return newly inserted row id
        int rowsUpdated = db.update(DataBaseFarmerClass.TABLE_NAME, values, DataBaseFarmerClass.FARMER_ID + "=" + farmerid, null);
        db.close();

        return rowsUpdated;
    }


    public int FarmerUpdateAmount(String ProjectID, String farmerid, String farmerAmountgiven, String farmerPendingAmount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseFarmerClass.PROJECT_ID, ProjectID);
        values.put(DataBaseFarmerClass.FARMER_AMOUNT_GIVEN, farmerAmountgiven);
        values.put(DataBaseFarmerClass.FARMER_PENDING_AMOUNT, farmerPendingAmount);

        // return newly inserted row id
        int rowsUpdated = db.update(DataBaseFarmerClass.TABLE_NAME, values, DataBaseFarmerClass.FARMER_ID + "=" + farmerid, null);
        db.close();

        return rowsUpdated;
    }


    public int FarmerDelete(int customerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseFarmerClass.TABLE_NAME, DataBaseFarmerClass.FARMER_ID + " = " + customerId, null);
        db.close();
        return rowDeleted;
    }

    public int FarmerUpdatePanCard(String farmerid, String farmerPanCardImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DataBaseFarmerClass.FARMER_PAN_CARD_IMAGE, farmerPanCardImage);

        // return newly inserted row id
        int rowsUpdated = db.update(DataBaseFarmerClass.TABLE_NAME, values, DataBaseFarmerClass.FARMER_ID + "=" + farmerid, null);
        db.close();

        return rowsUpdated;
    }

    //*****************************************Expence Related Querise***************


    public long ExpenceInsert(String project_Id, String expenceName, String expencePrice, String expenceDate, String expencePaymentMode, String expencePaymentNumber, String expenceDetails) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseExpenceClas.PROJECT_ID, project_Id);
        values.put(DataBaseExpenceClas.EXPENCE_NAME, expenceName);
        values.put(DataBaseExpenceClas.EXPENCE_PRICE, expencePrice);
        values.put(DataBaseExpenceClas.EXPENCE_DATE, expenceDate);
        values.put(DataBaseExpenceClas.EXPENCE_PAYMENT_MODE, expencePaymentMode);
        values.put(DataBaseExpenceClas.EXPENCE_PAYMENT_NUMBER, expencePaymentNumber);
        values.put(DataBaseExpenceClas.EXPENCE_DETAILS, expenceDetails);


        // insert row
        long id = db.insert(DataBaseExpenceClas.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public List<DataBaseExpenceClas> ExpenceGetAll(String ProjectID) {
        List<DataBaseExpenceClas> dataBaseExpenceClas = new ArrayList<>();

        // Select All Query
        //  String selectQuery = "SELECT  * FROM " + DatabasePlotClass.TABLE_NAME;
        String selectQuery = "SELECT  * FROM " + DataBaseExpenceClas.TABLE_NAME + " WHERE " + DataBaseExpenceClas.PROJECT_ID + "=" + ProjectID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseExpenceClas note = new DataBaseExpenceClas();

                note.setExpenceID(cursor.getInt(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_ID)));
                note.setProject_Id(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.PROJECT_ID)));
                note.setExpenceName(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_NAME)));
                note.setExpencePrice(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_PRICE)));
                note.setExpenceDate(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_DATE)));
                note.setExpenceDetails(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_DETAILS)));
                note.setExpencePaymentMode(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_PAYMENT_MODE)));
                note.setExpencePaymentNumber(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_PAYMENT_NUMBER)));

                dataBaseExpenceClas.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseExpenceClas;
    }


    public List<DataBaseExpenceClas> ExpenceGetSingle(int expenceId, String ProjectID) {
        List<DataBaseExpenceClas> dataBaseExpenceClas = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DataBaseExpenceClas.TABLE_NAME + " WHERE " + DataBaseExpenceClas.EXPENCE_ID + "=" + expenceId;

        // Select All Query


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseExpenceClas note = new DataBaseExpenceClas();

                note.setExpenceID(cursor.getInt(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_ID)));
                note.setProject_Id(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.PROJECT_ID)));
                note.setExpenceName(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_NAME)));
                note.setExpencePrice(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_PRICE)));
                note.setExpenceDate(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_DATE)));
                note.setExpenceDetails(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_DETAILS)));
                note.setExpencePaymentMode(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_PAYMENT_MODE)));
                note.setExpencePaymentNumber(cursor.getString(cursor.getColumnIndex(DataBaseExpenceClas.EXPENCE_PAYMENT_NUMBER)));

                dataBaseExpenceClas.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseExpenceClas;
    }


    public int ExpenceUpdate(String project_Id, String expence_id, String expenceName, String expencePrice, String expenceDate, String expencePaymentMode, String expencePaymentNumber, String expenceDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseExpenceClas.PROJECT_ID, project_Id);
        values.put(DataBaseExpenceClas.EXPENCE_NAME, expenceName);
        values.put(DataBaseExpenceClas.EXPENCE_PRICE, expencePrice);
        values.put(DataBaseExpenceClas.EXPENCE_DATE, expenceDate);
        values.put(DataBaseExpenceClas.EXPENCE_DETAILS, expenceDetails);
        values.put(DataBaseExpenceClas.EXPENCE_PAYMENT_MODE, expencePaymentMode);
        values.put(DataBaseExpenceClas.EXPENCE_PAYMENT_NUMBER, expencePaymentNumber);

        // return newly inserted row id
        int rowsUpdated = db.update(DataBaseExpenceClas.TABLE_NAME, values, DataBaseExpenceClas.EXPENCE_ID + "=" + expence_id, null);
        db.close();

        return rowsUpdated;
    }


    public int ExpeneceDelete(int expence_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseExpenceClas.TABLE_NAME, DataBaseExpenceClas.EXPENCE_ID + " = " + expence_id, null);
        db.close();
        return rowDeleted;
    }

 /*   public int getTotalOfExpence() {

        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        //String query = "SELECT SUM(expencePrice) FROM " + DataBaseExpenceClas.TABLE_NAME;
        String query = "SELECT SUM(" + DataBaseExpenceClas.EXPENCE_PRICE + ") as Total FROM " + DataBaseExpenceClas.TABLE_NAME;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getInt(0);

        return i;

    }*/

    public int getTotalOfExpenceByProject(String ProjectID) {

        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(" + DataBaseExpenceClas.EXPENCE_PRICE + ") as Total FROM " + DataBaseExpenceClas.TABLE_NAME + " WHERE " + DataBaseExpenceClas.PROJECT_ID + "=" + ProjectID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getInt(0);

        return i;

    }


    //*****************************************Transactions All***************

    public long TransactionInsert(String transaction_project_id,
                                  String transaction_plot_id,
                                  String transaction_customer_id,
                                  String transaction_farmer_id,
                                  String transaction_amount,
                                  String transaction_done_by,
                                  String transaction_money_give_take,
                                  String transaction_date,
                                  String transaction_type,
                                  String transaction_check_number,
                                  String transaction_cash_remark) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseTransaction.TRANSACTION_PROJECT_ID, transaction_project_id);
        values.put(DataBaseTransaction.TRANSACTION_PLOT_ID, transaction_plot_id);
        values.put(DataBaseTransaction.TRANSACTION_CUSTOMER_ID, transaction_customer_id);
        values.put(DataBaseTransaction.TRANSACTION_FARMER_ID, transaction_farmer_id);
        values.put(DataBaseTransaction.TRANSACTION_AMOUNT, transaction_amount);
        values.put(DataBaseTransaction.TRANSACTION_DONE_BY, transaction_done_by);
        values.put(DataBaseTransaction.TRANSACTION_MONEY_GIVE_TAKE, transaction_money_give_take);
        values.put(DataBaseTransaction.TRANSACTION_DATE, transaction_date);
        values.put(DataBaseTransaction.TRANSACTION_TYPE, transaction_type);
        values.put(DataBaseTransaction.TRANSACTION_CHECK_NUMBER, transaction_check_number);
        values.put(DataBaseTransaction.TRANSACTION_CAHS_REMARK, transaction_cash_remark);


        // insert row
        long id = db.insert(DataBaseTransaction.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<DataBaseTransaction> TransactionGetSingle(String ProjectID, String CuFaID, String doneBy) {

        List<DataBaseTransaction> dataBaseTransactions = new ArrayList<>();
        String selectQuery;
        selectQuery = "SELECT  * FROM " + DataBaseTransaction.TABLE_NAME;

        /*if (doneBy.equals(Constants.TRASACTION_DONE_BY_CUSTOMER)) {
            selectQuery = "SELECT  * FROM " + DataBaseTransaction.TABLE_NAME + " WHERE " + DataBaseTransaction.TRANSACTION_CUSTOMER_ID + "=" + CuFaID;

        } else if (doneBy.equals(Constants.TRASACTION_DONE_BY_FARMER)) {
            selectQuery = "SELECT  * FROM " + DataBaseTransaction.TABLE_NAME + " WHERE " + DataBaseTransaction.TRANSACTION_FARMER_ID + "=" + CuFaID;
        } else if (doneBy.equals(Constants.TRASACTION_DONE_BY_OWENER)) {
            selectQuery = "SELECT  * FROM " + DataBaseTransaction.TABLE_NAME + " WHERE " + DataBaseTransaction.TRANSACTION_FARMER_ID + "=" + CuFaID;
        } else {
            selectQuery = "SELECT  * FROM " + DataBaseTransaction.TABLE_NAME + " WHERE " + DataBaseTransaction.TRANSACTION_PLOT_ID + "=" + CuFaID;
        }*/


        // Select All Query


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseTransaction note = new DataBaseTransaction();

                note.setTransaction_id(cursor.getInt(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_ID)));
                note.setTransaction_project_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_PROJECT_ID)));
                note.setTransaction_plot_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_PLOT_ID)));
                note.setTransaction_customer_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CUSTOMER_ID)));
                note.setTransaction_farmer_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_FARMER_ID)));
                note.setTransaction_amount(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_AMOUNT)));
                note.setTransaction_done_by(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_DONE_BY)));
                note.setTransaction_money_give_take(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_MONEY_GIVE_TAKE)));
                note.setTransaction_date(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_DATE)));
                note.setTransaction_type(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_TYPE)));
                note.setTransaction_check_number(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CHECK_NUMBER)));
                note.setTransaction_cash_remark(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CAHS_REMARK)));


                dataBaseTransactions.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseTransactions;
    }

    public List<DataBaseTransaction> TransactionGetSingleFarmer(String ProjectID, String CuFaID, String doneBy) {

        List<DataBaseTransaction> dataBaseTransactions = new ArrayList<>();
        String selectQuery;
        selectQuery = "SELECT  * FROM " + DataBaseTransaction.TABLE_NAME + " WHERE " + DataBaseTransaction.TRANSACTION_PROJECT_ID + "=" + ProjectID + " AND " + DataBaseTransaction.TRANSACTION_FARMER_ID + "=" + CuFaID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseTransaction note = new DataBaseTransaction();

                note.setTransaction_id(cursor.getInt(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_ID)));
                note.setTransaction_project_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_PROJECT_ID)));
                note.setTransaction_plot_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_PLOT_ID)));
                note.setTransaction_customer_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CUSTOMER_ID)));
                note.setTransaction_farmer_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_FARMER_ID)));
                note.setTransaction_amount(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_AMOUNT)));
                note.setTransaction_done_by(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_DONE_BY)));
                note.setTransaction_money_give_take(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_MONEY_GIVE_TAKE)));
                note.setTransaction_date(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_DATE)));
                note.setTransaction_type(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_TYPE)));
                note.setTransaction_check_number(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CHECK_NUMBER)));
                note.setTransaction_cash_remark(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CAHS_REMARK)));


                dataBaseTransactions.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseTransactions;
    }

    public List<DataBaseTransaction> TransactionGetSingleCustomer(String ProjectID, String CuFaID, String doneBy, String PlotId) {

        List<DataBaseTransaction> dataBaseTransactions = new ArrayList<>();
        String selectQuery;
        selectQuery = "SELECT  * FROM " + DataBaseTransaction.TABLE_NAME + " WHERE " + DataBaseTransaction.TRANSACTION_PROJECT_ID + "=" + ProjectID + " AND " + DataBaseTransaction.TRANSACTION_CUSTOMER_ID + "=" + CuFaID + " AND " + DataBaseTransaction.TRANSACTION_PLOT_ID + "=" + PlotId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseTransaction note = new DataBaseTransaction();

                note.setTransaction_id(cursor.getInt(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_ID)));
                note.setTransaction_project_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_PROJECT_ID)));
                note.setTransaction_plot_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_PLOT_ID)));
                note.setTransaction_customer_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CUSTOMER_ID)));
                note.setTransaction_farmer_id(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_FARMER_ID)));
                note.setTransaction_amount(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_AMOUNT)));
                note.setTransaction_done_by(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_DONE_BY)));
                note.setTransaction_money_give_take(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_MONEY_GIVE_TAKE)));
                note.setTransaction_date(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_DATE)));
                note.setTransaction_type(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_TYPE)));
                note.setTransaction_check_number(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CHECK_NUMBER)));
                note.setTransaction_cash_remark(cursor.getString(cursor.getColumnIndex(DataBaseTransaction.TRANSACTION_CAHS_REMARK)));


                dataBaseTransactions.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseTransactions;
    }

    public int TransactionDelete(int tansactionID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseTransaction.TABLE_NAME, DataBaseTransaction.TRANSACTION_ID + " = " + tansactionID, null);
        db.close();
        return rowDeleted;
    }

    public int getTotalOfFarmerTransactions(String ProjectID, String CuFaID, String doneBy) {

        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        //String query = "SELECT SUM(expencePrice) FROM " + DataBaseExpenceClas.TABLE_NAME;
        String query = "SELECT SUM(" + DataBaseTransaction.TRANSACTION_AMOUNT + ") as Total FROM " + DataBaseTransaction.TABLE_NAME + " WHERE " + DataBaseTransaction.TRANSACTION_PROJECT_ID + "=" + ProjectID + " AND " + DataBaseTransaction.TRANSACTION_FARMER_ID + "=" + CuFaID;
        ;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getInt(0);

        return i;


    }

    public int getTotalOfCustomerTransactions(String ProjectID, String CuFaID, String doneBy) {

        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        //String query = "SELECT SUM(expencePrice) FROM " + DataBaseExpenceClas.TABLE_NAME;
        String query = "SELECT SUM(" + DataBaseTransaction.TRANSACTION_AMOUNT + ") as Total FROM " + DataBaseTransaction.TABLE_NAME + " WHERE " + DataBaseTransaction.TRANSACTION_PROJECT_ID + "=" + ProjectID + " AND " + DataBaseTransaction.TRANSACTION_CUSTOMER_ID + "=" + CuFaID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getInt(0);

        return i;


    }


    public int TransactionDeleteByPlotID(String PlotID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseTransaction.TABLE_NAME, DataBaseTransaction.TRANSACTION_PLOT_ID + " = " + PlotID, null);
        db.close();
        return rowDeleted;
    }

    public int TransactionDeleteByFarmerID(String farmerID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseTransaction.TABLE_NAME, DataBaseTransaction.TRANSACTION_FARMER_ID + " = " + farmerID, null);
        db.close();
        return rowDeleted;
    }

    public int TransactionDeleteByPlotAndCustomer(String PlotID,String customerID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseTransaction.TABLE_NAME, DataBaseTransaction.TRANSACTION_PLOT_ID + " = " + PlotID+ " AND "+DataBaseTransaction.TRANSACTION_CUSTOMER_ID + " = " + customerID, null);
        db.close();
        return rowDeleted;
    }


    //*****************************************NotificationPage All***************


    // NotificationPage
    public long NotificationInsert(String project_id, String notificationDate, String notificationTime,
                                   String notificationTitle, String notificationDescription, String notificationFor,
                                   String notificationForId, String notificationReadUnread) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseNotification.PROJECT_ID, project_id);
        values.put(DataBaseNotification.NOTI_DATE, notificationDate);
        values.put(DataBaseNotification.NOTI_TIME, notificationTime);
        values.put(DataBaseNotification.NOTI_TITLE, notificationTitle);
        values.put(DataBaseNotification.NOTI_DESC, notificationDescription);
        values.put(DataBaseNotification.NOTI_FOR, notificationFor);
        values.put(DataBaseNotification.NOTI_FOR_ID, notificationForId);
        values.put(DataBaseNotification.NOTI_READ_UNREAD, notificationReadUnread);


        // insert row
        long id = db.insert(DataBaseNotification.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public List<DataBaseNotification> NotificationGetAll(String ProjectID) {
        List<DataBaseNotification> dataBaseNotificationArrayList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DataBaseNotification.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseNotification note = new DataBaseNotification();

                note.setProject_id(cursor.getString(cursor.getColumnIndex(DataBaseNotification.PROJECT_ID)));
                note.setNotificationID(cursor.getInt(cursor.getColumnIndex(DataBaseNotification.NOTI_ID)));
                note.setNotificationDate(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_DATE)));
                String date = cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_DATE));
                note.setNotificationTime(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_TIME)));
                note.setNotificationTitle(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_TITLE)));
                note.setNotificationDescription(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_DESC)));
                note.setNotificationFor(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_FOR)));
                note.setNotificationForId(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_FOR_ID)));
                note.setNotificationReadUnread(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_READ_UNREAD)));


                dataBaseNotificationArrayList.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseNotificationArrayList;
    }

    public List<DataBaseNotification> NotificationGetByTodayDate(String ProjectID) {
        List<DataBaseNotification> dataBaseNotificationArrayList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DataBaseNotification.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseNotification note = new DataBaseNotification();

                note.setProject_id(cursor.getString(cursor.getColumnIndex(DataBaseNotification.PROJECT_ID)));
                note.setNotificationID(cursor.getInt(cursor.getColumnIndex(DataBaseNotification.NOTI_ID)));
                note.setNotificationDate(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_DATE)));
                String date = cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_DATE));

                Log.e("Date", date);
                Log.e("CurrentDate", getTodayDate());
                note.setNotificationTime(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_TIME)));
                note.setNotificationTitle(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_TITLE)));
                note.setNotificationDescription(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_DESC)));
                note.setNotificationFor(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_FOR)));
                note.setNotificationForId(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_FOR_ID)));
                note.setNotificationReadUnread(cursor.getString(cursor.getColumnIndex(DataBaseNotification.NOTI_READ_UNREAD)));

                if (date.equals(getTodayDate())) {
                    dataBaseNotificationArrayList.add(note);
                } else {

                }


            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseNotificationArrayList;
    }

    public int NotificatioReadUpdate(String notificationid, String notificationReadUnread) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataBaseNotification.NOTI_READ_UNREAD, notificationReadUnread);


        // return newly inserted row id
        int rowsUpdated = db.update(DataBaseNotification.TABLE_NAME, values, DataBaseNotification.NOTI_ID + "=" + notificationid, null);
        db.close();

        return rowsUpdated;
    }

    public int NotificatioDelete(int notificationid) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseNotification.TABLE_NAME, DataBaseNotification.NOTI_ID + " = " + notificationid, null);
        db.close();
        return rowDeleted;
    }

    // Get Todays Noitification
    public String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
        String todayDate = df.format(c);

        return todayDate;
    }


    // Total By Customer And Project By BookDatabase
    public double getTotalOfCustomerAmountByCustomerAndProject(String ProjectID, String customerID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(" + BookPlotDataBase.BOOK_TOTAL_AMOUNT + ") as Total FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID + " AND " + BookPlotDataBase.BOOK_CUSTOMER_ID + "=" + customerID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);

        return i;


    }

    public double getTotalOfCustomerPaidAmountCustomerAndProject(String ProjectID, String customerID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "SELECT SUM(" + BookPlotDataBase.BOOK_GIVEN_AMOUNT + ") as Total FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID + " AND " + BookPlotDataBase.BOOK_CUSTOMER_ID + "=" + customerID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);


        return i;


    }

    public double getTotalOfCustomerRemainingAmountByCustomerAndProject(String ProjectID, String customerID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(" + BookPlotDataBase.BOOK_REMAINING_AMOUNT + ") as Total FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID + " AND " + BookPlotDataBase.BOOK_CUSTOMER_ID + "=" + customerID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);


        return i;


    }

    // Total By Project
    public double getTotalOfCustomerAmountByProject(String ProjectID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(" + BookPlotDataBase.BOOK_TOTAL_AMOUNT + ") as Total FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);

        return i;


    }

    public double getTotalOfCustomerPaidAmountByProject(String ProjectID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "SELECT SUM(" + BookPlotDataBase.BOOK_GIVEN_AMOUNT + ") as Total FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);

        return i;


    }

    public double getTotalOfCustomerRemainingAmountByProject(String ProjectID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(" + BookPlotDataBase.BOOK_REMAINING_AMOUNT + ") as Total FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);

        return i;


    }


    public double getTotalOfFarmerAmount(String ProjectID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(" + DataBaseFarmerClass.FARMER_TOTAL_AMOUNT_TO_BE_PAID + ") as Total FROM " + DataBaseFarmerClass.TABLE_NAME + " WHERE " + DataBaseFarmerClass.PROJECT_ID + "=" + ProjectID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);

        return i;


    }

    public double getTotalOfFarmerPaidAmount(String ProjectID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(" + DataBaseFarmerClass.FARMER_AMOUNT_GIVEN + ") as Total FROM " + DataBaseFarmerClass.TABLE_NAME + " WHERE " + DataBaseFarmerClass.PROJECT_ID + "=" + ProjectID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);

        return i;


    }

    public double getTotalOfFarmerRemainingAmount(String ProjectID) {

        double i = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(" + DataBaseFarmerClass.FARMER_PENDING_AMOUNT + ") as Total FROM " + DataBaseFarmerClass.TABLE_NAME + " WHERE " + DataBaseFarmerClass.PROJECT_ID + "=" + ProjectID;


        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        i = c.getDouble(0);

        return i;


    }


    // BOOk Plot Database BOOOOK Plot


    public long BookPlot(String bookProjectID, String bookPlotID, String bookCustomerID, String bookDate, String bookTotalArea,
                         String bookRatePerSqureFoot, String bookTotalAmount, String bookGivenAmount, String bookGivenAmountDate, String bookRamainingAmount, String bookRemainingAmountCommitmentDate
            , String bookOtherCharges, String bookPaymentMode, String bookPaymentModenumber, String book_Notification_Remark, String book_Notification_Remark_Date, String bookStatus) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookPlotDataBase.PROJECT_ID, bookProjectID);
        values.put(BookPlotDataBase.BOOK_PLOT_ID, bookPlotID);
        values.put(BookPlotDataBase.BOOK_CUSTOMER_ID, bookCustomerID);
        values.put(BookPlotDataBase.BOOK_DATE, bookDate);
        values.put(BookPlotDataBase.BOOK_TOTAL_AREA, bookTotalArea);
        values.put(BookPlotDataBase.BOOK_RATE_PER_SQURE_FOOT, bookRatePerSqureFoot);
        values.put(BookPlotDataBase.BOOK_TOTAL_AMOUNT, bookTotalAmount);
        values.put(BookPlotDataBase.BOOK_GIVEN_AMOUNT, bookGivenAmount);
        values.put(BookPlotDataBase.BOOK_GIVEN_AMOUNT_DATE, bookGivenAmountDate);
        values.put(BookPlotDataBase.BOOK_REMAINING_AMOUNT, bookRamainingAmount);
        values.put(BookPlotDataBase.BOOK_REMAINING_AMOUNT_COMMITMENT_DATE, bookRemainingAmountCommitmentDate);
        values.put(BookPlotDataBase.BOOK_OTHERE_CHARGES, bookOtherCharges);
        values.put(BookPlotDataBase.BOOK_PAYMENT_MODE, bookPaymentMode);
        values.put(BookPlotDataBase.BOOK_PAYMENT_MODE_NUMBER, bookPaymentModenumber);
        values.put(BookPlotDataBase.BOOK_NOTIFICATION_REMARK, book_Notification_Remark);
        values.put(BookPlotDataBase.BOOK_NOTIFICATION_REMARK_DATE, book_Notification_Remark_Date);
        values.put(BookPlotDataBase.BOOK_STATUS, bookStatus);

        long BookPlot = db.insert(BookPlotDataBase.TABLE_NAME, null, values);
        return BookPlot;
    }

    public long BookPlot(String bookProjectID, String bookPlotID, String bookCustomerID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookPlotDataBase.PROJECT_ID, bookProjectID);
        values.put(BookPlotDataBase.BOOK_PLOT_ID, bookPlotID);
        values.put(BookPlotDataBase.BOOK_CUSTOMER_ID, bookCustomerID);

        long BookPlot = db.insert(BookPlotDataBase.TABLE_NAME, null, values);
        return BookPlot;
    }

    public long BookPlotUpdate(int BookID, String bookGivenAmount, String bookRamainingAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookPlotDataBase.BOOK_GIVEN_AMOUNT, bookGivenAmount);
        values.put(BookPlotDataBase.BOOK_REMAINING_AMOUNT, bookRamainingAmount);
        int rowsUpdated = db.update(BookPlotDataBase.TABLE_NAME, values, BookPlotDataBase.BOOK_ID + "=" + BookID, null);
        return rowsUpdated;
    }


    public List<BookPlotDataBase> BookPlotGetAllBookedPlot() {
        List<BookPlotDataBase> bookPlotDataBases = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + BookPlotDataBase.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BookPlotDataBase note = new BookPlotDataBase();

                note.setBookID(cursor.getInt(cursor.getColumnIndex(BookPlotDataBase.BOOK_ID)));
                note.setBookProjectID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.PROJECT_ID)));
                note.setBookPlotID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PLOT_ID)));
                note.setBookCustomerID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_CUSTOMER_ID)));
                note.setBookDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_DATE)));
                note.setBookTotalArea(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_TOTAL_AREA)));
                note.setBookRatePerSqureFoot(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_RATE_PER_SQURE_FOOT)));
                note.setBookTotalAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_TOTAL_AMOUNT)));
                note.setBookGivenAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_GIVEN_AMOUNT)));
                note.setBookGivenAmountDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_GIVEN_AMOUNT_DATE)));
                note.setBookRamainingAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_REMAINING_AMOUNT)));
                note.setBookRemainingAmountCommitmentDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_REMAINING_AMOUNT_COMMITMENT_DATE)));
                note.setBookOtherCharges(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_OTHERE_CHARGES)));
                note.setBookPaymentMode(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PAYMENT_MODE)));
                note.setBookPaymentModeNNumber(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PAYMENT_MODE_NUMBER)));
                note.setBook_Notification_Remark(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_NOTIFICATION_REMARK)));
                note.setBook_Notification_Remark_Date(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_NOTIFICATION_REMARK_DATE)));
                note.setBookStatus(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_STATUS)));


                bookPlotDataBases.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return bookPlotDataBases;
    }

    public List<BookPlotDataBase> BookPlotGetPlotByCustomer(String CustomerId, String ProjectID) {
        List<BookPlotDataBase> bookPlotDataBases = new ArrayList<>();


        String selectQuery = "SELECT  * FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID + " AND " + BookPlotDataBase.BOOK_CUSTOMER_ID + "=" + CustomerId;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BookPlotDataBase note = new BookPlotDataBase();

                note.setBookID(cursor.getInt(cursor.getColumnIndex(BookPlotDataBase.BOOK_ID)));
                note.setBookProjectID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.PROJECT_ID)));
                note.setBookPlotID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PLOT_ID)));
                note.setBookCustomerID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_CUSTOMER_ID)));
                note.setBookDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_DATE)));
                note.setBookTotalArea(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_TOTAL_AREA)));
                note.setBookRatePerSqureFoot(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_RATE_PER_SQURE_FOOT)));
                note.setBookTotalAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_TOTAL_AMOUNT)));
                note.setBookGivenAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_GIVEN_AMOUNT)));
                note.setBookGivenAmountDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_GIVEN_AMOUNT_DATE)));
                note.setBookRamainingAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_REMAINING_AMOUNT)));
                note.setBookRemainingAmountCommitmentDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_REMAINING_AMOUNT_COMMITMENT_DATE)));
                note.setBookOtherCharges(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_OTHERE_CHARGES)));
                note.setBookPaymentMode(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PAYMENT_MODE)));
                note.setBookPaymentModeNNumber(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PAYMENT_MODE_NUMBER)));
                note.setBook_Notification_Remark(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_NOTIFICATION_REMARK)));
                note.setBook_Notification_Remark_Date(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_NOTIFICATION_REMARK_DATE)));
                note.setBookStatus(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_STATUS)));


                bookPlotDataBases.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return bookPlotDataBases;
    }

    public List<BookPlotDataBase> BookPlotGetBookedSinglePlotByPlotId(String plotId, String ProjectID) {
        List<BookPlotDataBase> bookPlotDataBases = new ArrayList<>();


        String selectQuery = "SELECT  * FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.BOOK_PLOT_ID + "=" + plotId + " AND " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BookPlotDataBase note = new BookPlotDataBase();

                note.setBookID(cursor.getInt(cursor.getColumnIndex(BookPlotDataBase.BOOK_ID)));
                note.setBookProjectID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.PROJECT_ID)));
                note.setBookPlotID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PLOT_ID)));
                note.setBookCustomerID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_CUSTOMER_ID)));
                note.setBookDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_DATE)));
                note.setBookTotalArea(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_TOTAL_AREA)));
                note.setBookRatePerSqureFoot(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_RATE_PER_SQURE_FOOT)));
                note.setBookTotalAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_TOTAL_AMOUNT)));
                note.setBookGivenAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_GIVEN_AMOUNT)));
                note.setBookGivenAmountDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_GIVEN_AMOUNT_DATE)));
                note.setBookRamainingAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_REMAINING_AMOUNT)));
                note.setBookRemainingAmountCommitmentDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_REMAINING_AMOUNT_COMMITMENT_DATE)));
                note.setBookOtherCharges(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_OTHERE_CHARGES)));
                note.setBookPaymentMode(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PAYMENT_MODE)));
                note.setBookPaymentModeNNumber(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PAYMENT_MODE_NUMBER)));
                note.setBook_Notification_Remark(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_NOTIFICATION_REMARK)));
                note.setBook_Notification_Remark_Date(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_NOTIFICATION_REMARK_DATE)));
                note.setBookStatus(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_STATUS)));


                bookPlotDataBases.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return bookPlotDataBases;
    }

    public List<BookPlotDataBase> BookPlotGetAllAllocatedCustomerPlotId(String plotId, String ProjectID) {
        List<BookPlotDataBase> bookPlotDataBases = new ArrayList<>();


        String selectQuery = "SELECT  * FROM " + BookPlotDataBase.TABLE_NAME + " WHERE " + BookPlotDataBase.BOOK_PLOT_ID + "=" + plotId + " AND " + BookPlotDataBase.PROJECT_ID + "=" + ProjectID;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BookPlotDataBase note = new BookPlotDataBase();

                note.setBookID(cursor.getInt(cursor.getColumnIndex(BookPlotDataBase.BOOK_ID)));
                note.setBookProjectID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.PROJECT_ID)));
                note.setBookPlotID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PLOT_ID)));
                note.setBookCustomerID(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_CUSTOMER_ID)));
                note.setBookDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_DATE)));
                note.setBookTotalArea(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_TOTAL_AREA)));
                note.setBookRatePerSqureFoot(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_RATE_PER_SQURE_FOOT)));
                note.setBookTotalAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_TOTAL_AMOUNT)));
                note.setBookGivenAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_GIVEN_AMOUNT)));
                note.setBookGivenAmountDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_GIVEN_AMOUNT_DATE)));
                note.setBookRamainingAmount(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_REMAINING_AMOUNT)));
                note.setBookRemainingAmountCommitmentDate(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_REMAINING_AMOUNT_COMMITMENT_DATE)));
                note.setBookOtherCharges(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_OTHERE_CHARGES)));
                note.setBookPaymentMode(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PAYMENT_MODE)));
                note.setBookPaymentModeNNumber(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_PAYMENT_MODE_NUMBER)));
                note.setBook_Notification_Remark(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_NOTIFICATION_REMARK)));
                note.setBook_Notification_Remark_Date(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_NOTIFICATION_REMARK_DATE)));
                note.setBookStatus(cursor.getString(cursor.getColumnIndex(BookPlotDataBase.BOOK_STATUS)));


                bookPlotDataBases.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return bookPlotDataBases;
    }

    public int BookPlotDelete(int bookPlotID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(BookPlotDataBase.TABLE_NAME, BookPlotDataBase.BOOK_ID + " = " + bookPlotID, null);
        db.close();
        return rowDeleted;
    }


    // Expence Names

    public long ExpenceNameInsert(String expenceName) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DataBaseExpenceNameClass.EXPENCE_NAME, expenceName);

        // insert row
        long id = db.insert(DataBaseExpenceNameClass.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public List<DataBaseExpenceNameClass> ExpenceNameGetAll() {
        List<DataBaseExpenceNameClass> dataBaseExpenceNameClassList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseExpenceNameClass.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataBaseExpenceNameClass note = new DataBaseExpenceNameClass();

                note.setExpenceID(cursor.getInt(cursor.getColumnIndex(DataBaseExpenceNameClass.EXPENCE_ID)));
                note.setExpenceName(cursor.getString(cursor.getColumnIndex(DataBaseExpenceNameClass.EXPENCE_NAME)));

                dataBaseExpenceNameClassList.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return dataBaseExpenceNameClassList;
    }

    public int ExpenceNameDelete(int expenceID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(DataBaseExpenceNameClass.TABLE_NAME, DataBaseExpenceNameClass.EXPENCE_ID + " = " + expenceID, null);
        db.close();
        return rowDeleted;
    }

}
