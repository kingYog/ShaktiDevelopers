package com.yogeshborhade.shaktidevelopers.DatabaseClas;

/**
 * Created by admin on 13-05-2018.
 */

public class PojoCustomerSpinner {
    private String CustomerID;
    private String CustomerNamer;

    public  PojoCustomerSpinner(){

    }
    public PojoCustomerSpinner(String CustomerID, String CustomerNamer) {
        this.CustomerID = CustomerID;
        this.CustomerNamer = CustomerNamer;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerNamer() {
        return CustomerNamer;
    }

    public void setCustomerNamer(String customerNamer) {
        CustomerNamer = customerNamer;
    }
}
