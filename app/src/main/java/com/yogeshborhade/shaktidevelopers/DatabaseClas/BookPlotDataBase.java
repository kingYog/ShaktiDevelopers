package com.yogeshborhade.shaktidevelopers.DatabaseClas;

/**
 * Created by admin on 13-05-2018.
 */

public class BookPlotDataBase {

    public static final String TABLE_NAME = "bookingPlot";
    public static final String BOOK_ID = "bookID";
    public static final String PROJECT_ID = "bookProjectId";
    public static final String BOOK_PLOT_ID = "bookPlotId";
    public static final String BOOK_CUSTOMER_ID = "bookCustomerID";
    public static final String BOOK_DATE = "bookDate";
    public static final String BOOK_TOTAL_AREA = "bookTotalArea";
    public static final String BOOK_RATE_PER_SQURE_FOOT = "bookRatePerSqureFoot";
    public static final String BOOK_TOTAL_AMOUNT = "bookTotalAmount";
    public static final String BOOK_GIVEN_AMOUNT = "bookGivenAmount";
    public static final String BOOK_GIVEN_AMOUNT_DATE = "bookGivenAmountDate";
    public static final String BOOK_REMAINING_AMOUNT = "bookRemainingAmount";
    public static final String BOOK_REMAINING_AMOUNT_COMMITMENT_DATE = "bookRemainingAmountCommitmentDate";
    public static final String BOOK_PAYMENT_MODE = "bookPaymentMode";
    public static final String BOOK_PAYMENT_MODE_NUMBER = "bookPaymentModeNumber";
    public static final String BOOK_OTHERE_CHARGES = "bookOtherCharges";
    public static final String BOOK_NOTIFICATION_REMARK = "bookNotificationRemark";
    public static final String BOOK_NOTIFICATION_REMARK_DATE = "bookNotificationDate";
    public static final String BOOK_STATUS = "bookStatus";


    private String bookProjectID;
    private int bookID;
    private String bookPlotID;
    private String bookCustomerID;
    private String bookDate;
    private String bookTotalArea;
    private String bookRatePerSqureFoot;
    private String bookTotalAmount;
    private String bookGivenAmount;
    private String bookGivenAmountDate;
    private String bookRamainingAmount;
    private String bookRemainingAmountCommitmentDate;
    private String bookOtherCharges;
    private String bookPaymentMode;
    private String bookPaymentModeNNumber;
    private String book_Notification_Remark;
    private String book_Notification_Remark_Date;
    private String bookStatus;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROJECT_ID + " TEXT,"
                    + BOOK_PLOT_ID + " TEXT,"
                    + BOOK_CUSTOMER_ID + " TEXT,"
                    + BOOK_DATE + " TEXT,"
                    + BOOK_TOTAL_AREA + " TEXT,"
                    + BOOK_RATE_PER_SQURE_FOOT + " TEXT,"
                    + BOOK_TOTAL_AMOUNT + " TEXT,"
                    + BOOK_GIVEN_AMOUNT + " TEXT,"
                    + BOOK_GIVEN_AMOUNT_DATE + " TEXT,"
                    + BOOK_REMAINING_AMOUNT + " TEXT DEFAULT NA,"
                    + BOOK_REMAINING_AMOUNT_COMMITMENT_DATE + " TEXT DEFAULT NA,"
                    + BOOK_OTHERE_CHARGES + " TEXT DEFAULT NA,"
                    + BOOK_PAYMENT_MODE + " TEXT DEFAULT NA,"
                    + BOOK_PAYMENT_MODE_NUMBER + " TEXT DEFAULT NA,"
                    + BOOK_NOTIFICATION_REMARK + " TEXT DEFAULT NA,"
                    + BOOK_NOTIFICATION_REMARK_DATE + " TEXT DEFAULT NA,"
                    + BOOK_STATUS + " TEXT DEFAULT NA"
                    + ")";


    public BookPlotDataBase() {

    }

    public BookPlotDataBase(String bookProjectID, int bookID, String bookPlotID, String bookCustomerID, String bookDate, String bookTotalArea,
                            String bookRatePerSqureFoot, String bookTotalAmount, String bookGivenAmount, String bookGivenAmountDate, String bookRamainingAmount, String bookRemainingAmountCommitmentDate
            , String bookOtherCharges, String bookPaymentMode, String bookPaymentModeNNumber, String book_Notification_Remark, String book_Notification_Remark_Date, String bookStatus) {

        this.bookProjectID = bookProjectID;
        this.bookID = bookID;
        this.bookPlotID = bookPlotID;
        this.bookCustomerID = bookCustomerID;
        this.bookDate = bookDate;
        this.bookTotalArea = bookTotalArea;
        this.bookRatePerSqureFoot = bookRatePerSqureFoot;
        this.bookTotalAmount = bookTotalAmount;
        this.bookGivenAmount = bookGivenAmount;
        this.bookGivenAmountDate = bookGivenAmountDate;
        this.bookRamainingAmount = bookRamainingAmount;
        this.bookRemainingAmountCommitmentDate = bookRemainingAmountCommitmentDate;
        this.bookOtherCharges = bookOtherCharges;
        this.bookPaymentMode = bookPaymentMode;
        this.bookPaymentModeNNumber = bookPaymentModeNNumber;
        this.book_Notification_Remark = book_Notification_Remark;
        this.book_Notification_Remark_Date = book_Notification_Remark_Date;
        this.bookStatus = bookStatus;


    }

    public String getBookProjectID() {
        return bookProjectID;
    }

    public void setBookProjectID(String bookProjectID) {
        this.bookProjectID = bookProjectID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookPlotID() {
        return bookPlotID;
    }

    public void setBookPlotID(String bookPlotID) {
        this.bookPlotID = bookPlotID;
    }

    public String getBookCustomerID() {
        return bookCustomerID;
    }

    public void setBookCustomerID(String bookCustomerID) {
        this.bookCustomerID = bookCustomerID;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getBookTotalArea() {
        return bookTotalArea;
    }

    public void setBookTotalArea(String bookTotalArea) {
        this.bookTotalArea = bookTotalArea;
    }

    public String getBookRatePerSqureFoot() {
        return bookRatePerSqureFoot;
    }

    public void setBookRatePerSqureFoot(String bookRatePerSqureFoot) {
        this.bookRatePerSqureFoot = bookRatePerSqureFoot;
    }

    public String getBookTotalAmount() {
        return bookTotalAmount;
    }

    public void setBookTotalAmount(String bookTotalAmount) {
        this.bookTotalAmount = bookTotalAmount;
    }

    public String getBookGivenAmount() {
        return bookGivenAmount;
    }

    public void setBookGivenAmount(String bookGivenAmount) {
        this.bookGivenAmount = bookGivenAmount;
    }

    public String getBookGivenAmountDate() {
        return bookGivenAmountDate;
    }

    public void setBookGivenAmountDate(String bookGivenAmountDate) {
        this.bookGivenAmountDate = bookGivenAmountDate;
    }

    public String getBookRamainingAmount() {
        return bookRamainingAmount;
    }

    public void setBookRamainingAmount(String bookRamainingAmount) {
        this.bookRamainingAmount = bookRamainingAmount;
    }

    public String getBookRemainingAmountCommitmentDate() {
        return bookRemainingAmountCommitmentDate;
    }

    public void setBookRemainingAmountCommitmentDate(String bookRemainingAmountCommitmentDate) {
        this.bookRemainingAmountCommitmentDate = bookRemainingAmountCommitmentDate;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }


    public String getBookOtherCharges() {
        return bookOtherCharges;
    }

    public void setBookOtherCharges(String bookOtherCharges) {
        this.bookOtherCharges = bookOtherCharges;
    }

    public String getBook_Notification_Remark() {
        return book_Notification_Remark;
    }

    public void setBook_Notification_Remark(String book_Notification_Remark) {
        this.book_Notification_Remark = book_Notification_Remark;
    }

    public String getBook_Notification_Remark_Date() {
        return book_Notification_Remark_Date;
    }

    public void setBook_Notification_Remark_Date(String book_Notification_Remark_Date) {
        this.book_Notification_Remark_Date = book_Notification_Remark_Date;
    }


    public String getBookPaymentMode() {
        return bookPaymentMode;
    }

    public void setBookPaymentMode(String bookPaymentMode) {
        this.bookPaymentMode = bookPaymentMode;
    }

    public String getBookPaymentModeNNumber() {
        return bookPaymentModeNNumber;
    }

    public void setBookPaymentModeNNumber(String bookPaymentModeNNumber) {
        this.bookPaymentModeNNumber = bookPaymentModeNNumber;
    }
}
