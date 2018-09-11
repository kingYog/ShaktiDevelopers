package com.yogeshborhade.shaktidevelopers.DatabaseClas;

/**
 * Created by admin on 13-05-2018.
 */

public class DataBaseTransaction {
    public static final String TABLE_NAME = "transaction_table";
    public static final String TRANSACTION_ID = "transaction_id";
    public static final String TRANSACTION_PROJECT_ID = "transaction_project_id";
    public static final String TRANSACTION_PLOT_ID = "transaction_plot_id";
    public static final String TRANSACTION_CUSTOMER_ID = "transaction_customer_id";
    public static final String TRANSACTION_FARMER_ID = "transaction_farmer_id";
    public static final String TRANSACTION_AMOUNT = "transaction_amount";
    public static final String TRANSACTION_DONE_BY = "transaction_done_by";
    public static final String TRANSACTION_MONEY_GIVE_TAKE = "transaction_money_give_take";
    public static final String TRANSACTION_DATE = "transaction_date";
    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String TRANSACTION_CHECK_NUMBER = "transaction_check_number";
    public static final String TRANSACTION_CAHS_REMARK = "transaction_cash_remark";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TRANSACTION_PROJECT_ID + " TEXT,"
                    + TRANSACTION_PLOT_ID + " TEXT,"
                    + TRANSACTION_CUSTOMER_ID + " TEXT,"
                    + TRANSACTION_FARMER_ID + " TEXT,"
                    + TRANSACTION_AMOUNT + " TEXT,"
                    + TRANSACTION_DONE_BY + " TEXT,"
                    + TRANSACTION_MONEY_GIVE_TAKE + " TEXT,"
                    + TRANSACTION_DATE + " TEXT,"
                    + TRANSACTION_TYPE + " TEXT,"
                    + TRANSACTION_CHECK_NUMBER + " TEXT DEFAULT NA,"
                    + TRANSACTION_CAHS_REMARK + " TEXT DEFAULT NA"
                    + ")";

    int transaction_id;
    String transaction_project_id;
    String transaction_plot_id;
    String transaction_customer_id;
    String transaction_farmer_id;
    String transaction_amount;
    String transaction_done_by;
    String transaction_money_give_take;
    String transaction_date;
    String transaction_type;
    String transaction_check_number;
    String transaction_cash_remark;

    public DataBaseTransaction() {

    }

    public DataBaseTransaction(int transaction_id, String transaction_project_id,
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

    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_project_id() {
        return transaction_project_id;
    }

    public void setTransaction_project_id(String transaction_project_id) {
        this.transaction_project_id = transaction_project_id;
    }

    public String getTransaction_plot_id() {
        return transaction_plot_id;
    }

    public void setTransaction_plot_id(String transaction_plot_id) {
        this.transaction_plot_id = transaction_plot_id;
    }

    public String getTransaction_customer_id() {
        return transaction_customer_id;
    }

    public void setTransaction_customer_id(String transaction_customer_id) {
        this.transaction_customer_id = transaction_customer_id;
    }

    public String getTransaction_farmer_id() {
        return transaction_farmer_id;
    }

    public void setTransaction_farmer_id(String transaction_farmer_id) {
        this.transaction_farmer_id = transaction_farmer_id;
    }

    public String getTransaction_done_by() {
        return transaction_done_by;
    }

    public void setTransaction_done_by(String transaction_done_by) {
        this.transaction_done_by = transaction_done_by;
    }

    public String getTransaction_money_give_take() {
        return transaction_money_give_take;
    }

    public void setTransaction_money_give_take(String transaction_money_give_take) {
        this.transaction_money_give_take = transaction_money_give_take;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_check_number() {
        return transaction_check_number;
    }

    public void setTransaction_check_number(String transaction_check_number) {
        this.transaction_check_number = transaction_check_number;
    }

    public String getTransaction_cash_remark() {
        return transaction_cash_remark;
    }

    public void setTransaction_cash_remark(String transaction_cash_remark) {
        this.transaction_cash_remark = transaction_cash_remark;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }



}
