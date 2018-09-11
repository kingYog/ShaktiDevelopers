package com.yogeshborhade.shaktidevelopers.DatabaseClas;

public class DataBaseExpenceClas {


    public static final String TABLE_NAME = "expencedetails";
    public static final String PROJECT_ID = "project_id";
    public static final String EXPENCE_ID = "expenceID";
    public static final String EXPENCE_NAME = "expenceName";
    public static final String EXPENCE_PRICE = "expencePrice";
    public static final String EXPENCE_DATE = "expenceDate";
    public static final String EXPENCE_DETAILS = "expenceDetails";
    public static final String EXPENCE_PAYMENT_MODE = "expencePaymentMode";
    public static final String EXPENCE_PAYMENT_NUMBER = "expencePaymentNumber";


    private String project_Id;
    private int expenceID;
    private String expenceName;
    private String expencePrice;
    private String expenceDate;
    private String expenceDetails;
    private String expencePaymentMode;
    private String expencePaymentNumber;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + EXPENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROJECT_ID + " TEXT,"
                    + EXPENCE_NAME + " TEXT,"
                    + EXPENCE_PRICE + " TEXT,"
                    + EXPENCE_DATE + " TEXT,"
                    + EXPENCE_PAYMENT_MODE + " TEXT,"
                    + EXPENCE_PAYMENT_NUMBER + " TEXT,"
                    + EXPENCE_DETAILS + " TEXT DEFAULT NA"
                    + ")";

    public DataBaseExpenceClas() {

    }


    public DataBaseExpenceClas(int expenceID, String project_Id, String expenceName, String expencePrice, String expenceDate, String expenceDetails,String expencePaymentMode,String expencePaymentNumber) {
        this.expenceID = expenceID;
        this.project_Id = project_Id;
        this.expenceName = expenceName;
        this.expencePrice = expencePrice;
        this.expenceDate = expenceDate;
        this.expenceDetails = expenceDetails;
        this.expencePaymentMode=expencePaymentMode;
        this.expencePaymentNumber=expencePaymentNumber;


    }


    public String getProject_Id() {
        return project_Id;
    }

    public void setProject_Id(String project_Id) {
        this.project_Id = project_Id;
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

    public String getExpencePrice() {
        return expencePrice;
    }

    public void setExpencePrice(String expencePrice) {
        this.expencePrice = expencePrice;
    }

    public String getExpenceDate() {
        return expenceDate;
    }

    public void setExpenceDate(String expenceDate) {
        this.expenceDate = expenceDate;
    }


    public String getExpenceDetails() {
        return expenceDetails;
    }

    public void setExpenceDetails(String expenceDetails) {
        this.expenceDetails = expenceDetails;
    }


    public String getExpencePaymentMode() {
        return expencePaymentMode;
    }

    public void setExpencePaymentMode(String expencePaymentMode) {
        this.expencePaymentMode = expencePaymentMode;
    }

    public String getExpencePaymentNumber() {
        return expencePaymentNumber;
    }

    public void setExpencePaymentNumber(String expencePaymentNumber) {
        this.expencePaymentNumber = expencePaymentNumber;
    }

}
