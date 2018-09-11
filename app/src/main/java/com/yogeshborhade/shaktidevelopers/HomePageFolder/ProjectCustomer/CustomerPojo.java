package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

/**
 * Created by admin on 06-04-2018.
 */

public class CustomerPojo {

    public static final String TABLE_NAME = "customer_details";
    public static final String PROJECT_ID = "project_id";
    public static final String CUSTOMER_ID = "customerID";
    public static final String CUSTOMER_NAME = "customerName";
    public static final String CUSTOMER_MOBILE_NUMBER = "customerMobileNumber";
    public static final String CUSTOMER_ADDRESS = "customerAddress";
    public static final String CUSTOMER_DATE_OF_BIRTH = "customerDateOfBirth";
    public static final String CUSTOMER_PAN_CARD_NUMBER = "customerPanCardNumber";
    public static final String CUSTOMER_ADHAR_CARD_NUMBER = "customerAdharCardNumber";
    public static final String CUSTOMER_BUYING_PLOT_ID = "customerBuyingPlotID";
    public static final String CUSTOMER_PLOT_EAST_SIZE = "customerPlotEastSize";
    public static final String CUSTOMER_PLOT_WEST_SIZE = "customerPlotWestSize";
    public static final String CUSTOMER_PLOT_NORTH_SIZE = "customerPlotNorthSize";
    public static final String CUSTOMER_PLOT_SOUTH_SIZE = "customerPlotSouthSize";
    public static final String CUSTOMER_TOTAL_BUYING_AREA = "customerTotalBuyingArea";
    public static final String CUSTOMER_RATE_PER_SQURE_FOOT = "customerRatePerSqureFoot";
    public static final String CUSTOMER_TOTAL_AMOUNT_TO_BE_PAID = "customerTotalAmountToBePaid";
    public static final String CUSTOMER_AMOUNT_RESCIEVED = "customerAmountRescieved";
    public static final String CUSTOMER_AMOUNT_RESCIEVED_DATE = "customerAmountResCievedDate";
    public static final String CUSTOMER_PENDING_AMOUNT = "customerPendingAmount";
    public static final String CUSTOMER_PENDING_AMOUNT_COMMITMENT_DATE = "customerPendingAmountCommitmentDate";
    public static final String CUSTOMER_OTHERE_CHARGES = "customerOtherCharges";
    public static final String CUSTOMER_NOTIFICATION_REMARK = "customerNotificationrRemark";
    public static final String CUSTOMER_NOTIFICATION_REMARK_DATE = "customerNotificationrRemarkDate";
    public static final String CUSTOMER_STATUS = "customerStatus";


    private String ProjectID;
    private String customerID;
    private String customerName;
    private String customerMobileNumber;
    private String customerAddress;
    private String customerDateOfBirth;
    private String customerPanCardNumber;
    private String customerAdharCardNumber;
    private String customerBuyingPlotID;
    private String customerPlotEastSize;
    private String customerPlotWestSize;
    private String customerPlotNorthSize;
    private String customerPlotSouthSize;
    private String customerTotalBuyingArea;
    private String customerRatePerSqureFoot;
    private String customerTotalAmountToBePaid;
    private String customerAmountRescieved;
    private String customerAmountResCievedDate;
    private String customerPendingAmount;
    private String customerPendingAmountCommitmentDate;
    private String customerOtherCharges;
    private String customerNotificationrRemark;
    private String customerNotificationrRemarkDate;

    public CustomerPojo(String ProjectID, String customerID, String customerName, String customerMobileNumber, String customerAddress,
                        String customerDateOfBirth, String customerPanCardNumber, String customerAdharCardNumber, String customerBuyingPlotID, String customerPlotEastSize,
                        String customerPlotWestSize, String customerPlotNorthSize, String customerPlotSouthSize, String customerTotalBuyingArea, String customerRatePerSqureFoot,
                        String customerTotalAmountToBePaid, String customerAmountRescieved, String customerAmountResCievedDate, String customerPendingAmount, String customerPendingAmountCommitmentDate,
                        String customerOtherCharges, String customerNotificationrRemark, String customerNotificationrRemarkDate) {
        this.ProjectID = ProjectID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerMobileNumber = customerMobileNumber;
        this.customerAddress = customerAddress;
        this.customerDateOfBirth = customerDateOfBirth;
        this.customerPanCardNumber = customerPanCardNumber;
        this.customerAdharCardNumber = customerAdharCardNumber;
        this.customerBuyingPlotID = customerBuyingPlotID;
        this.customerPlotEastSize = customerPlotEastSize;
        this.customerPlotWestSize = customerPlotWestSize;
        this.customerPlotNorthSize = customerPlotNorthSize;
        this.customerPlotSouthSize = customerPlotSouthSize;
        this.customerTotalBuyingArea = customerTotalBuyingArea;
        this.customerRatePerSqureFoot = customerRatePerSqureFoot;
        this.customerTotalAmountToBePaid = customerTotalAmountToBePaid;
        this.customerAmountRescieved = customerAmountRescieved;
        this.customerAmountResCievedDate = customerAmountResCievedDate;
        this.customerPendingAmount = customerPendingAmount;
        this.customerPendingAmountCommitmentDate = customerPendingAmountCommitmentDate;
        this.customerOtherCharges = customerOtherCharges;
        this.customerNotificationrRemark = customerNotificationrRemark;
        this.customerNotificationrRemarkDate = customerNotificationrRemarkDate;

    }


}
