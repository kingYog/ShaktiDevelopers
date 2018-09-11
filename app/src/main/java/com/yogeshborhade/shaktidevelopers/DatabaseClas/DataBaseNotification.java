package com.yogeshborhade.shaktidevelopers.DatabaseClas;

/**
 * Created by admin on 02-06-2018.
 */

public class DataBaseNotification {


    public static final String TABLE_NAME = "notification_details";
    public static final String PROJECT_ID = "project_id";
    public static final String NOTI_ID = "notificationID";
    public static final String NOTI_DATE = "notificationDate";
    public static final String NOTI_TIME = "notificationTime";
    public static final String NOTI_TITLE = "notificationTitle";
    public static final String NOTI_DESC = "notificationDescription";
    public static final String NOTI_FOR = "notificationFor";
    public static final String NOTI_FOR_ID = "notificationForId";
    public static final String NOTI_READ_UNREAD = "notificationReadUnread";


    private int notificationID;
    private String project_id;
    private String notificationDate;


    private String notificationTime;
    private String notificationTitle;
    private String notificationDescription;
    private String notificationFor;
    private String notificationForId;
    private String notificationReadUnread;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + NOTI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROJECT_ID + " TEXT,"
                    + NOTI_DATE + " TEXT,"
                    + NOTI_TIME + " TEXT,"
                    + NOTI_TITLE + " TEXT,"
                    + NOTI_DESC + " TEXT,"
                    + NOTI_FOR + " TEXT,"
                    + NOTI_FOR_ID + " TEXT,"
                    + NOTI_READ_UNREAD + " TEXT DEFAULT NA"
                    + ")";


    public DataBaseNotification() {

    }

    public DataBaseNotification(String project_id, int notificationID, String notificationDate,String notificationTime,
                                String notificationTitle, String notificationDescription, String notificationFor,
                                String notificationForId, String notificationReadUnread) {
        this.project_id = project_id;
        this.notificationID = notificationID;
        this.notificationDate = notificationDate;
        this.notificationTime = notificationTime;
        this.notificationTitle = notificationTitle;
        this.notificationDescription = notificationDescription;
        this.notificationFor = notificationFor;
        this.notificationForId = notificationForId;
        this.notificationReadUnread = notificationReadUnread;


    }




    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public String getNotificationFor() {
        return notificationFor;
    }

    public void setNotificationFor(String notificationFor) {
        this.notificationFor = notificationFor;
    }

    public String getNotificationForId() {
        return notificationForId;
    }

    public void setNotificationForId(String notificationForId) {
        this.notificationForId = notificationForId;
    }

    public String getNotificationReadUnread() {
        return notificationReadUnread;
    }

    public void setNotificationReadUnread(String notificationReadUnread) {
        this.notificationReadUnread = notificationReadUnread;
    }

}


