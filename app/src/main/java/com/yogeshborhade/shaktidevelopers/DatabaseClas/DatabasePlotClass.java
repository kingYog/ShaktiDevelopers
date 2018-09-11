package com.yogeshborhade.shaktidevelopers.DatabaseClas;

public class DatabasePlotClass {

    public static final String TABLE_NAME = "plot_details";
    public static final String PLOT_ID = "plot_Id";
    public static final String PROJECT_ID = "project_id";
    public static final String PLOT_ALLOCATED_CUSTOMER_ID = "plot_allocated_customer_id";
    public static final String PLOT_NUMBER = "plot_number";
    public static final String PLOT_EAST_SIDE = "plot_east_side";
    public static final String PLOT_WEST_SIDE = "plot_west_side";
    public static final String PLOT_NORTH_SIDE = "plot_north_side";
    public static final String PLOT_SOUTH_SIDE = "plot_south_side";
    public static final String PLOT_TOTAL_AREA_SQURE_FOOT = "ploto_total_area_squre_foot";
    public static final String PLOT_PRICE_PER_SQURE_FOOT = "plot_price_per_squre_foot";
    public static final String PLOT_TOTAL_PRICE = "plot_total_price";
    public static final String PLOT_REGISTER_DATE = "plot_register_date";
    public static final String PLOT_STATUS = "plotStatus";
    public static final String PLOT_REMARK = "plot_Remark";


    private int plot_Id;
    private String project_Id;
    private String plot_allocated_customer_id;
    private String plot_number;
    private String plot_east_side;
    private String plot_west_side;
    private String plot_north_side;
    private String plot_south_side;
    private String ploto_total_area_squre_foot;
    private String plot_price_per_squre_foot;
    private String plot_total_price;
    private String plot_register_date;
    private String plot_status;
    private String plot_Remark;


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + PLOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROJECT_ID + " TEXT,"
                    + PLOT_ALLOCATED_CUSTOMER_ID + " TEXT,"
                    + PLOT_NUMBER + " TEXT,"
                    + PLOT_EAST_SIDE + " TEXT,"
                    + PLOT_WEST_SIDE + " TEXT,"
                    + PLOT_NORTH_SIDE + " TEXT,"
                    + PLOT_SOUTH_SIDE + " TEXT,"
                    + PLOT_TOTAL_AREA_SQURE_FOOT + " TEXT DEFAULT NA,"
                    + PLOT_PRICE_PER_SQURE_FOOT + " TEXT DEFAULT NA,"
                    + PLOT_TOTAL_PRICE + " TEXT DEFAULT NA,"
                    + PLOT_STATUS + " TEXT DEFAULT NA,"
                    + PLOT_REGISTER_DATE + " TEXT DEFAULT NA,"
                    + PLOT_REMARK + " TEXT DEFAULT NA"
                    + ")";


    public DatabasePlotClass() {
    }

    public DatabasePlotClass(int plot_Id, String project_Id, String plot_allocated_customer_id, String plot_number,
                             String plot_east_side, String plot_west_side,
                             String plot_north_side, String plot_south_side, String plot_register_date,
                             String ploto_total_area_squre_foot, String plot_price_per_squre_foot,
                             String plot_total_price, String plot_status, String plot_Remark) {

        this.plot_Id = plot_Id;
        this.project_Id = project_Id;
        this.plot_allocated_customer_id = plot_allocated_customer_id;
        this.plot_number = plot_number;
        this.plot_east_side = plot_east_side;
        this.plot_west_side = plot_west_side;
        this.plot_north_side = plot_north_side;
        this.plot_south_side = plot_south_side;
        this.plot_register_date = plot_register_date;
        this.ploto_total_area_squre_foot = ploto_total_area_squre_foot;
        this.plot_price_per_squre_foot = plot_price_per_squre_foot;
        this.plot_total_price = plot_total_price;
        this.plot_status = plot_status;
        this.plot_Remark = plot_Remark;


    }

    public int getPlot_Id() {
        return plot_Id;
    }

    public void setPlot_Id(int plot_Id) {
        this.plot_Id = plot_Id;
    }

    public String getProject_Id() {
        return project_Id;
    }

    public void setProject_Id(String project_Id) {
        this.project_Id = project_Id;
    }

    public String getPlot_allocated_customer_id() {
        return plot_allocated_customer_id;
    }

    public void setPlot_allocated_customer_id(String plot_allocated_customer_id) {
        this.plot_allocated_customer_id = plot_allocated_customer_id;
    }

    public String getPlot_number() {
        return plot_number;
    }

    public void setPlot_number(String plot_number) {
        this.plot_number = plot_number;
    }


    public String getPlot_east_side() {
        return plot_east_side;
    }

    public void setPlot_east_side(String plot_east_side) {
        this.plot_east_side = plot_east_side;
    }

    public String getPlot_west_side() {
        return plot_west_side;
    }

    public void setPlot_west_side(String plot_west_side) {
        this.plot_west_side = plot_west_side;
    }

    public String getPlot_north_side() {
        return plot_north_side;
    }

    public void setPlot_north_side(String plot_north_side) {
        this.plot_north_side = plot_north_side;
    }

    public String getPlot_south_side() {
        return plot_south_side;
    }

    public void setPlot_south_side(String plot_south_side) {
        this.plot_south_side = plot_south_side;
    }

    public String getPlot_register_date() {
        return plot_register_date;
    }

    public void setPlot_register_date(String plot_register_date) {
        this.plot_register_date = plot_register_date;
    }

    public String getPloto_total_area_squre_foot() {
        return ploto_total_area_squre_foot;
    }

    public void setPloto_total_area_squre_foot(String ploto_total_area_squre_foot) {
        this.ploto_total_area_squre_foot = ploto_total_area_squre_foot;
    }

    public String getPlot_price_per_squre_foot() {
        return plot_price_per_squre_foot;
    }

    public void setPlot_price_per_squre_foot(String plot_price_per_squre_foot) {
        this.plot_price_per_squre_foot = plot_price_per_squre_foot;
    }

    public String getPlot_total_price() {
        return plot_total_price;
    }

    public void setPlot_total_price(String plot_total_price) {
        this.plot_total_price = plot_total_price;
    }

    public String getPlot_status() {
        return plot_status;
    }

    public void setPlot_status(String plot_status) {
        this.plot_status = plot_status;
    }


    public String getPlot_Remark() {
        return plot_Remark;
    }

    public void setPlot_Remark(String plot_Remark) {
        this.plot_Remark = plot_Remark;
    }

}
