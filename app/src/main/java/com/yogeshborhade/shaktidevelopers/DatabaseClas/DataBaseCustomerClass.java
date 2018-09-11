package com.yogeshborhade.shaktidevelopers.DatabaseClas;

public class DataBaseCustomerClass {


    public static final String TABLE_NAME = "customer_details";
    public static final String PROJECT_ID = "project_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String CUSTOMER_MOBILE_NUMBER = "customer_mobile_number";
    public static final String CUSTOMER_ADDRESS = "customer_address";
    public static final String CUSTOMER_DATE_OF_BIRTH = "customer_date_of_birth";
    public static final String CUSTOMER_AGE = "customer_age";
    public static final String CUSTOMER_PAN_CARD_NUMBER = "customer_pan_card_number";
    public static final String CUSTOMER_PAN_CARD_IMAGE = "customer_pan_card_image";
    public static final String CUSTOMER_ADHAR_CARD_NUMBER = "customer_adhar_card_number";
    /*    public static final String CUSTOMER_BUYING_PLOT_ID = "customer_buying_plot_id";
        public static final String CUSTOMER_PLOT_EAST_SIZE = "customer_plot_east_size";
        public static final String CUSTOMER_PLOT_WEST_SIZE = "customer_plot_west_size";
        public static final String CUSTOMER_PLOT_NORTH_SIZE = "customer_plot_north_size";
        public static final String CUSTOMER_PLOT_SOUTH_SIZE = "customer_plot_south_size";
        public static final String CUSTOMER_TOTAL_BUYING_AREA = "customer_total_buying_area";
        public static final String CUSTOMER_RATE_PER_SQURE_FOOT = "customer_rate_per_squre_foot";
        public static final String CUSTOMER_TOTAL_AMOUNT_TO_BE_PAID = "customer_total_amount_to_be_paid";
        public static final String CUSTOMER_AMOUNT_RESCIEVED = "customer_amount_rescieved";
        public static final String CUSTOMER_AMOUNT_RESCIEVED_DATE = "customer_amount_rescieved_date";
        public static final String CUSTOMER_PENDING_AMOUNT = "customer_pending_amount";
        public static final String CUSTOMER_PENDING_AMOUNT_COMMITMENT_DATE = "customer_pending_amount_commitment_date";
        public static final String CUSTOMER_OTHERE_CHARGES = "customer_other_charges";
        public static final String CUSTOMER_NOTIFICATION_REMARK = "customer_notificationr_remark";
        public static final String CUSTOMER_NOTIFICATION_REMARK_DATE = "customer_notification_remarkDate";*/
    public static final String CUSTOMER_STATUS = "customer_status";


    private String ProjectID;
    private int customerID;
    private String customerName;
    private String customerMobileNumber;
    private String customerAddress;
    private String customerDateOfBirth;
    private String customerAge;


    private String customerPanCardNumber;
    private String customerPanCardImage;
    private String customerAdharCardNumber;
    private String customerStatus;



    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROJECT_ID + " TEXT,"
                    + CUSTOMER_NAME + " TEXT,"
                    + CUSTOMER_MOBILE_NUMBER + " TEXT,"
                    + CUSTOMER_ADDRESS + " TEXT,"
                    + CUSTOMER_DATE_OF_BIRTH + " TEXT,"
                    + CUSTOMER_AGE + " TEXT,"
                    + CUSTOMER_PAN_CARD_NUMBER + " TEXT,"
                    + CUSTOMER_PAN_CARD_IMAGE + " TEXT,"
                    + CUSTOMER_ADHAR_CARD_NUMBER + " TEXT,"
                    + CUSTOMER_STATUS + " TEXT DEFAULT NA"
                    + ")";

    public DataBaseCustomerClass() {
    }

    public DataBaseCustomerClass(String ProjectID, int customerID, String customerName, String customerMobileNumber, String customerAddress,
                                 String customerDateOfBirth,String customerAge, String customerPanCardNumber, String customerPanCardImage, String customerAdharCardNumber, String customerStatus) {
        this.ProjectID = ProjectID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerMobileNumber = customerMobileNumber;
        this.customerAddress = customerAddress;
        this.customerDateOfBirth = customerDateOfBirth;
        this.customerAge = customerAge;
        this.customerPanCardNumber = customerPanCardNumber;
        this.customerPanCardImage = customerPanCardImage;
        this.customerAdharCardNumber = customerAdharCardNumber;
        this.customerStatus = customerStatus;


    }


    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerDateOfBirth() {
        return customerDateOfBirth;
    }

    public void setCustomerDateOfBirth(String customerDateOfBirth) {
        this.customerDateOfBirth = customerDateOfBirth;
    }

    public String getCustomerPanCardNumber() {
        return customerPanCardNumber;
    }

    public void setCustomerPanCardNumber(String customerPanCardNumber) {
        this.customerPanCardNumber = customerPanCardNumber;
    }

    public String getCustomerAdharCardNumber() {
        return customerAdharCardNumber;
    }

    public void setCustomerAdharCardNumber(String customerAdharCardNumber) {
        this.customerAdharCardNumber = customerAdharCardNumber;
    }


    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getCustomerPanCardImage() {
        return customerPanCardImage;
    }

    public void setCustomerPanCardImage(String customerPanCardImage) {
        this.customerPanCardImage = customerPanCardImage;
    }


    public String getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(String customerAge) {
        this.customerAge = customerAge;
    }

}
