package com.yogeshborhade.shaktidevelopers.Database;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * Created by admin on 13-05-2018.
 */

public class Constants {

    public static final String DATABASE_NAME_SHAKTI_DEVELOPER = "shaktideveloperdb";
    public static final String TRASACTION_DONE_BY_CUSTOMER = "transactionDoneByCustomer";
    public static final String TRASACTION_DONE_BY_FARMER = "transactionDoneByFarmer";
    public static final String TRASACTION_DONE_BY_OWENER = "transactionDoneByOwner";
    public static final String TRASACTION_MONEY_GIVE = "transactionMoneyGive";
    public static final String TRASACTION_MONEY_TAKE = "transactionMoneyTake";
    public static final String TRANSACTION_TYPE_CASH = "Cash";
    public static final String TRANSACTION_TYPE_CHECK = "Check";
    public static final String TRANSACTION_TYPE_IMPS = "IMPS";
    public static final String TRANSACTION_TYPE_NFT = "NFT";
    public static final String TRANSACTION_TYPE_RTGS = "RTGS";
    public static final String CUSTOMER = "customer";
    public static final String FARMER = "farmer";
    public static final String READ = "Y";
    public static final String UNREAD = "N";
    public static final String PLOTBOOK = "booked";
    public static final String PLOTNOTBOOK = "remaining";
    public static final String DATEFORMATE = "d-M-yyyy";
    public static final String CUSTOMER_PAN_CARD_IMAGE_PATH = "/ShaktiDeveloper/PanCard/Customer";
    public static final String FARMER_PAN_CARD_IMAGE_PATH = "/ShaktiDeveloper/PanCard/Farmer";
    public static final String RECIEPT_IMAGE_PATH = "/ShaktiDeveloper/BookingReciept";
    public static final String CUSTOMER_DETAILS_PDF = "/ShaktiDeveloper/CustomerDetails";
    public static final String SAVED_DATABASEPATH = "/ShaktiDeveloper/DatabaseBackup";
    public static final NumberFormat numberWithoutExponential = new DecimalFormat("#0.00");
    public static final NumberFormat numberWithComma = new DecimalFormat("##,##,###.00");

    public static String getDate() {


        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMATE);
        String dateString = sdf.format(date);
        return dateString;


    }

}


