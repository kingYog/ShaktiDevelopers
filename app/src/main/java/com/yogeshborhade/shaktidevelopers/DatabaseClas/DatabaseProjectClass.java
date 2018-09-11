package com.yogeshborhade.shaktidevelopers.DatabaseClas;

public class DatabaseProjectClass {

    public static final String TABLE_NAME = "project_details";
    public static final String PROJECT_ID = "project_id";
    public static final String PROJECT_NAME = "project_name";
    public static final String PROJECT_ADRESS = "project_adress";
    public static final String PROJECT_ADDED_DATE = "project_added_date";
    public static final String PROJECT_TOTAL_AREA = "project_total_area";
    public static final String PROJECT_EAST_SIDE = "project_east_side";
    public static final String PROJECT_WEST_SIDE = "project_west_side";
    public static final String PROJECT_NORTH_SIDE = "project_north_side";
    public static final String PROJECT_SOUTH_SIDE = "project_south_side";
    public static final String PROJECT_TOTAL_AVAILABALE_PLOTS = "project_total_available_plots";
    public static final String PROJECT_TOTAL_SALE_PLOTS = "project_total_sale_plots";
    public static final String PROJECT_TOTAL_EXPENCE = "project_total_expence";
    public static final String PROJECT_TOTAL_EARN = "project_total_earn";
    public static final String PROJECT_TOTAL_PROFIT = "project_total_profit";


    private int project_id;
    private String project_name;
    private String project_adress;
    private String project_added_date;
    private String project_totalArea;
    private String project_east_side;
    private String project_west_side;
    private String project_north_side;
    private String project_south_side;


    private String project_total_available_plots;
    private String project_total_sale_plots;
    private String project_total_expence;
    private String project_total_earn;
    private String project_total_profit;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROJECT_NAME + " TEXT,"
                    + PROJECT_ADRESS + " TEXT,"
                    + PROJECT_ADDED_DATE + " TEXT,"
                    + PROJECT_TOTAL_AREA + " TEXT,"
                    + PROJECT_EAST_SIDE + " TEXT,"
                    + PROJECT_WEST_SIDE + " TEXT,"
                    + PROJECT_NORTH_SIDE + " TEXT,"
                    + PROJECT_SOUTH_SIDE + " TEXT,"
                    + PROJECT_TOTAL_AVAILABALE_PLOTS + " TEXT DEFAULT NA,"
                    + PROJECT_TOTAL_SALE_PLOTS + " TEXT DEFAULT NA,"
                    + PROJECT_TOTAL_EXPENCE + " TEXT DEFAULT NA,"
                    + PROJECT_TOTAL_EARN + " TEXT DEFAULT NA,"
                    + PROJECT_TOTAL_PROFIT + " TEXT DEFAULT NA"
                    + ")";

    public DatabaseProjectClass() {
    }

    public DatabaseProjectClass(int project_id, String project_name, String project_adress, String project_added_date,
                                String project_totalArea, String project_east_side, String project_west_side,
                                String project_north_side, String project_south_side, String project_total_available_plots,
                                String project_total_sale_plots, String project_total_expence, String project_total_earn, String project_total_profit) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_adress = project_adress;
        this.project_added_date = project_added_date;
        this.project_totalArea = project_totalArea;
        this.project_east_side = project_east_side;
        this.project_west_side = project_west_side;
        this.project_north_side = project_north_side;
        this.project_south_side = project_south_side;
        this.project_total_available_plots = project_total_available_plots;
        this.project_total_sale_plots = project_total_sale_plots;
        this.project_total_expence = project_total_expence;
        this.project_total_earn = project_total_earn;
        this.project_total_profit = project_total_profit;


    }


    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_adress() {
        return project_adress;
    }

    public void setProject_adress(String project_adress) {
        this.project_adress = project_adress;
    }

    public String getProject_totalArea() {
        return project_totalArea;
    }

    public void setProject_totalArea(String project_totalArea) {
        this.project_totalArea = project_totalArea;
    }

    public String getProject_east_side() {
        return project_east_side;
    }

    public void setProject_east_side(String project_east_side) {
        this.project_east_side = project_east_side;
    }

    public String getProject_west_side() {
        return project_west_side;
    }

    public void setProject_west_side(String project_west_side) {
        this.project_west_side = project_west_side;
    }

    public String getProject_north_side() {
        return project_north_side;
    }

    public void setProject_north_side(String project_north_side) {
        this.project_north_side = project_north_side;
    }

    public String getProject_south_side() {
        return project_south_side;
    }

    public void setProject_south_side(String project_south_side) {
        this.project_south_side = project_south_side;
    }

    public String getProject_added_date() {
        return project_added_date;
    }

    public void setProject_added_date(String project_added_date) {
        this.project_added_date = project_added_date;
    }

    public String getProject_total_available_plots() {
        return project_total_available_plots;
    }

    public void setProject_total_available_plots(String project_total_available_plots) {
        this.project_total_available_plots = project_total_available_plots;
    }

    public String getProject_total_sale_plots() {
        return project_total_sale_plots;
    }

    public void setProject_total_sale_plots(String project_total_sale_plots) {
        this.project_total_sale_plots = project_total_sale_plots;
    }

    public String getProject_total_expence() {
        return project_total_expence;
    }

    public void setProject_total_expence(String project_total_expence) {
        this.project_total_expence = project_total_expence;
    }

    public String getProject_total_earn() {
        return project_total_earn;
    }

    public void setProject_total_earn(String project_total_earn) {
        this.project_total_earn = project_total_earn;
    }

    public String getProject_total_profit() {
        return project_total_profit;
    }

    public void setProject_total_profit(String project_total_profit) {
        this.project_total_profit = project_total_profit;
    }


}
