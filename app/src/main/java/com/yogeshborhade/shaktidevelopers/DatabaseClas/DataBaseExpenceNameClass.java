package com.yogeshborhade.shaktidevelopers.DatabaseClas;

public class DataBaseExpenceNameClass {
    public static final String TABLE_NAME = "expenceNames";
    public static final String EXPENCE_ID = "expenceID";
    public static final String EXPENCE_NAME = "expenceName";

    private int expenceID;
    private String expenceName;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + EXPENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + EXPENCE_NAME + " TEXT DEFAULT NA"
                    + ")";

    public DataBaseExpenceNameClass() {

    }


    public DataBaseExpenceNameClass(int expenceID, String expenceName) {
        this.expenceID = expenceID;
        this.expenceName = expenceName;


    }

    public int getExpenceID() {
        return expenceID;
    }

    public void setExpenceID(int expenceID) {
        this.expenceID = expenceID;
    }

    public String getExpenceName() {
        return expenceName;
    }

    public void setExpenceName(String expenceName) {
        this.expenceName = expenceName;
    }

}
