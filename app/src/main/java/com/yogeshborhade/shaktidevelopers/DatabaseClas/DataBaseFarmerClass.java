package com.yogeshborhade.shaktidevelopers.DatabaseClas;

public class DataBaseFarmerClass {

    public static final String TABLE_NAME = "farmer_details";
    public static final String PROJECT_ID = "project_id";
    public static final String FARMER_ID = "farmer_id";
    public static final String FARMER_NAME = "farmer_name";
    public static final String FARMER_MOBILE_NUMBER = "farmer_mobile_number";
    public static final String FARMER_ADDRESS = "farmer_address";
    public static final String FARMER_DATE_OF_BIRTH = "farmer_date_of_birth";
    public static final String FARMER_AGE = "farmer_age";

    public static final String FARMER_PAN_CARD_NUMBER = "farmer_pan_card_number";

    public static final String FARMER_ADHAR_CARD_NUMBER = "farmer_adhar_card_number";
    public static final String FARMER_SELLING_PLOT_ID = "farmer_selling_plot_id";
    public static final String FARMER_PLOT_EAST_SIZE = "farmer_plot_east_size";
    public static final String FARMER_PLOT_WEST_SIZE = "farmer_plot_west_size";
    public static final String FARMER_PLOT_NORTH_SIZE = "farmer_plot_north_size";
    public static final String FARMER_PLOT_SOUTH_SIZE = "farmer_plot_south_size";
    public static final String FARMER_TOTAL_SELLING_AREA = "farmer_total_selling_area";
    public static final String FARMER_RATE_PER_SQURE_FOOT = "farmer_rate_per_squre_foot";
    public static final String FARMER_TOTAL_AMOUNT_TO_BE_PAID = "farmer_total_amount_to_be_paid";
    public static final String FARMER_AMOUNT_GIVEN = "farmer_amount_given";
    public static final String FARMER_AMOUNT_GIVEN_DATE = "farmer_amount_given_date";
    public static final String FARMER_PENDING_AMOUNT = "farmer_pending_amount";
    public static final String FARMER_PENDING_AMOUNT_COMMITMENT_DATE = "farmer_pending_amount_commitment_date";
    public static final String FARMER_OTHERE_CHARGES = "farmer_other_charges";
    public static final String FARMER_NOTIFICATION_REMARK = "farmer_notificationr_remark";
    public static final String FARMER_NOTIFICATION_REMARK_DATE = "farmer_notification_remarkDate";
    public static final String FARMER_STATUS = "farmer_status";
    public static final String FARMER_PAYMENT_TYPE = "farmer_payment_type";
    public static final String FARMER_PAYMENT_NUMBER = "farmer_payment_number";
    public static final String FARMER_PAN_CARD_IMAGE = "farmer_pan_card_Image";


    private String ProjectID;
    private int farmerID;
    private String farmerName;
    private String farmerMobileNumber;
    private String farmerAddress;
    private String farmerDateOfBirth;
    private String farmerAge;
    private String farmerPanCardNumber;
    private String farmerAdharCardNumber;
    private String farmerSellingPlotID;
    private String farmerPlotEastSize;
    private String farmerPlotWestSize;
    private String farmerPlotNorthSize;
    private String farmerPlotSouthSize;
    private String farmerTotalSellingArea;
    private String farmerRatePerSqureFoot;
    private String farmerTotalAmountToBePaid;
    private String farmerAmountgiven;
    private String farmerAmountgivenDate;
    private String farmerPendingAmount;
    private String farmerPendingAmountCommitmentDate;
    private String farmerOtherCharges;
    private String farmerNotificationrRemark;
    private String farmerNotificationrRemarkDate;
    private String farmerStatus;
    private String farmer_payment_number;
    private String farmer_payment_type;
    private String farmerPanCardImage;



    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + FARMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROJECT_ID + " TEXT,"
                    + FARMER_NAME + " TEXT,"
                    + FARMER_MOBILE_NUMBER + " TEXT,"
                    + FARMER_ADDRESS + " TEXT,"
                    + FARMER_DATE_OF_BIRTH + " TEXT,"
                    + FARMER_AGE + " TEXT,"
                    + FARMER_PAN_CARD_NUMBER + " TEXT,"
                    + FARMER_ADHAR_CARD_NUMBER + " TEXT,"
                    + FARMER_SELLING_PLOT_ID + " TEXT,"
                    + FARMER_PLOT_EAST_SIZE + " TEXT DEFAULT NA,"
                    + FARMER_PLOT_WEST_SIZE + " TEXT DEFAULT NA,"
                    + FARMER_PLOT_NORTH_SIZE + " TEXT DEFAULT NA,"
                    + FARMER_PLOT_SOUTH_SIZE + " TEXT DEFAULT NA,"
                    + FARMER_TOTAL_SELLING_AREA + " TEXT DEFAULT NA,"
                    + FARMER_RATE_PER_SQURE_FOOT + " TEXT DEFAULT NA,"
                    + FARMER_TOTAL_AMOUNT_TO_BE_PAID + " TEXT DEFAULT NA,"
                    + FARMER_AMOUNT_GIVEN + " TEXT DEFAULT NA,"
                    + FARMER_AMOUNT_GIVEN_DATE + " TEXT DEFAULT NA,"
                    + FARMER_PENDING_AMOUNT + " TEXT DEFAULT NA,"
                    + FARMER_PENDING_AMOUNT_COMMITMENT_DATE + " TEXT DEFAULT NA,"
                    + FARMER_OTHERE_CHARGES + " TEXT DEFAULT NA,"
                    + FARMER_NOTIFICATION_REMARK + " TEXT DEFAULT NA,"
                    + FARMER_NOTIFICATION_REMARK_DATE + " TEXT DEFAULT NA,"
                    + FARMER_PAYMENT_TYPE + " TEXT DEFAULT NA,"
                    + FARMER_PAYMENT_NUMBER + " TEXT DEFAULT NA,"
                    + FARMER_PAN_CARD_IMAGE + " TEXT DEFAULT NA,"
                    + FARMER_STATUS + " TEXT DEFAULT NA"
                    + ")";

    public DataBaseFarmerClass() {

    }

    public DataBaseFarmerClass(String ProjectID, int farmerID, String farmerName, String farmerMobileNumber, String farmerAddress,
                               String farmerDateOfBirth,String farmerAge, String farmerPanCardNumber, String farmerAdharCardNumber, String farmerSellingPlotID, String farmerPlotEastSize,
                               String farmerPlotWestSize, String farmerPlotNorthSize, String farmerPlotSouthSize, String farmerTotalSellingArea, String farmerRatePerSqureFoot,
                               String farmerTotalAmountToBePaid, String farmerAmountgiven, String farmerAmountgivenDate, String farmerPendingAmount, String farmerPendingAmountCommitmentDate,
                               String farmerOtherCharges, String farmerNotificationrRemark, String farmerNotificationrRemarkDate, String farmerStatus, String farmer_payment_type, String farmer_payment_number) {
        this.ProjectID = ProjectID;
        this.farmerID = farmerID;
        this.farmerName = farmerName;
        this.farmerMobileNumber = farmerMobileNumber;
        this.farmerAddress = farmerAddress;
        this.farmerDateOfBirth = farmerDateOfBirth;
        this.farmerAge=farmerAge;
        this.farmerPanCardNumber = farmerPanCardNumber;
        this.farmerAdharCardNumber = farmerAdharCardNumber;
        this.farmerSellingPlotID = farmerSellingPlotID;
        this.farmerPlotEastSize = farmerPlotEastSize;
        this.farmerPlotWestSize = farmerPlotWestSize;
        this.farmerPlotNorthSize = farmerPlotNorthSize;
        this.farmerPlotSouthSize = farmerPlotSouthSize;
        this.farmerTotalSellingArea = farmerTotalSellingArea;
        this.farmerRatePerSqureFoot = farmerRatePerSqureFoot;
        this.farmerTotalAmountToBePaid = farmerTotalAmountToBePaid;
        this.farmerAmountgiven = farmerAmountgiven;
        this.farmerAmountgivenDate = farmerAmountgivenDate;
        this.farmerPendingAmount = farmerPendingAmount;
        this.farmerPendingAmountCommitmentDate = farmerPendingAmountCommitmentDate;
        this.farmerOtherCharges = farmerOtherCharges;
        this.farmerNotificationrRemark = farmerNotificationrRemark;
        this.farmerNotificationrRemarkDate = farmerNotificationrRemarkDate;
        this.farmerStatus = farmerStatus;
        this.farmer_payment_type = farmer_payment_type;
        this.farmer_payment_number = farmer_payment_number;


    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public int getFarmerID() {
        return farmerID;
    }

    public void setFarmerID(int farmerID) {
        this.farmerID = farmerID;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmerMobileNumber() {
        return farmerMobileNumber;
    }

    public void setFarmerMobileNumber(String farmerMobileNumber) {
        this.farmerMobileNumber = farmerMobileNumber;
    }

    public String getFarmerAddress() {
        return farmerAddress;
    }

    public void setFarmerAddress(String farmerAddress) {
        this.farmerAddress = farmerAddress;
    }

    public String getFarmerDateOfBirth() {
        return farmerDateOfBirth;
    }

    public void setFarmerDateOfBirth(String farmerDateOfBirth) {
        this.farmerDateOfBirth = farmerDateOfBirth;
    }

    public String getFarmerPanCardNumber() {
        return farmerPanCardNumber;
    }

    public void setFarmerPanCardNumber(String farmerPanCardNumber) {
        this.farmerPanCardNumber = farmerPanCardNumber;
    }

    public String getFarmerAdharCardNumber() {
        return farmerAdharCardNumber;
    }

    public void setFarmerAdharCardNumber(String farmerAdharCardNumber) {
        this.farmerAdharCardNumber = farmerAdharCardNumber;
    }

    public String getFarmerSellingPlotID() {
        return farmerSellingPlotID;
    }

    public void setFarmerSellingPlotID(String farmerSellingPlotID) {
        this.farmerSellingPlotID = farmerSellingPlotID;
    }

    public String getFarmerPlotEastSize() {
        return farmerPlotEastSize;
    }

    public void setFarmerPlotEastSize(String farmerPlotEastSize) {
        this.farmerPlotEastSize = farmerPlotEastSize;
    }

    public String getFarmerPlotWestSize() {
        return farmerPlotWestSize;
    }

    public void setFarmerPlotWestSize(String farmerPlotWestSize) {
        this.farmerPlotWestSize = farmerPlotWestSize;
    }

    public String getFarmerPlotNorthSize() {
        return farmerPlotNorthSize;
    }

    public void setFarmerPlotNorthSize(String farmerPlotNorthSize) {
        this.farmerPlotNorthSize = farmerPlotNorthSize;
    }

    public String getFarmerPlotSouthSize() {
        return farmerPlotSouthSize;
    }

    public void setFarmerPlotSouthSize(String farmerPlotSouthSize) {
        this.farmerPlotSouthSize = farmerPlotSouthSize;
    }

    public String getFarmerTotalSellingArea() {
        return farmerTotalSellingArea;
    }

    public void setFarmerTotalSellingArea(String farmerTotalSellingArea) {
        this.farmerTotalSellingArea = farmerTotalSellingArea;
    }

    public String getFarmerRatePerSqureFoot() {
        return farmerRatePerSqureFoot;
    }

    public void setFarmerRatePerSqureFoot(String farmerRatePerSqureFoot) {
        this.farmerRatePerSqureFoot = farmerRatePerSqureFoot;
    }

    public String getFarmerTotalAmountToBePaid() {
        return farmerTotalAmountToBePaid;
    }

    public void setFarmerTotalAmountToBePaid(String farmerTotalAmountToBePaid) {
        this.farmerTotalAmountToBePaid = farmerTotalAmountToBePaid;
    }

    public String getFarmerAmountgiven() {
        return farmerAmountgiven;
    }

    public void setFarmerAmountgiven(String farmerAmountgiven) {
        this.farmerAmountgiven = farmerAmountgiven;
    }

    public String getFarmerAmountgivenDate() {
        return farmerAmountgivenDate;
    }

    public void setFarmerAmountgivenDate(String farmerAmountgivenDate) {
        this.farmerAmountgivenDate = farmerAmountgivenDate;
    }

    public String getFarmerPendingAmount() {
        return farmerPendingAmount;
    }

    public void setFarmerPendingAmount(String farmerPendingAmount) {
        this.farmerPendingAmount = farmerPendingAmount;
    }

    public String getFarmerPendingAmountCommitmentDate() {
        return farmerPendingAmountCommitmentDate;
    }

    public void setFarmerPendingAmountCommitmentDate(String farmerPendingAmountCommitmentDate) {
        this.farmerPendingAmountCommitmentDate = farmerPendingAmountCommitmentDate;
    }

    public String getFarmerOtherCharges() {
        return farmerOtherCharges;
    }

    public void setFarmerOtherCharges(String farmerOtherCharges) {
        this.farmerOtherCharges = farmerOtherCharges;
    }

    public String getFarmerNotificationrRemark() {
        return farmerNotificationrRemark;
    }

    public void setFarmerNotificationrRemark(String farmerNotificationrRemark) {
        this.farmerNotificationrRemark = farmerNotificationrRemark;
    }

    public String getFarmerNotificationrRemarkDate() {
        return farmerNotificationrRemarkDate;
    }

    public void setFarmerNotificationrRemarkDate(String farmerNotificationrRemarkDate) {
        this.farmerNotificationrRemarkDate = farmerNotificationrRemarkDate;
    }

    public String getFarmerStatus() {
        return farmerStatus;
    }

    public void setFarmerStatus(String farmerStatus) {
        this.farmerStatus = farmerStatus;
    }


    public String getFarmer_payment_number() {
        return farmer_payment_number;
    }

    public void setFarmer_payment_number(String farmer_payment_number) {
        this.farmer_payment_number = farmer_payment_number;
    }

    public String getFarmer_payment_type() {
        return farmer_payment_type;
    }

    public void setFarmer_payment_type(String farmer_payment_type) {
        this.farmer_payment_type = farmer_payment_type;
    }

    public String getFarmerPanCardImage() {
        return farmerPanCardImage;
    }

    public void setFarmerPanCardImage(String farmerPanCardImage) {
        this.farmerPanCardImage = farmerPanCardImage;
    }

    public String getFarmerAge() {
        return farmerAge;
    }

    public void setFarmerAge(String farmerAge) {
        this.farmerAge = farmerAge;
    }
}
