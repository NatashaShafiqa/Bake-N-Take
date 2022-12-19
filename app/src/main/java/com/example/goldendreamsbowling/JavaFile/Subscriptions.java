package com.example.goldendreamsbowling.JavaFile;

public class Subscriptions {
    String dataName,dataEmail,dataPhone;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataEmail() {
        return dataEmail;
    }

    public void setDataEmail(String dataEmail) {
        this.dataEmail = dataEmail;
    }

    public String getDataPhone() {
        return dataPhone;
    }

    public void setDataPhone(String dataPhone) {
        this.dataPhone = dataPhone;
    }

    public Subscriptions(String dataName, String dataEmail, String dataPhone) {
        this.dataName = dataName;
        this.dataEmail = dataEmail;
        this.dataPhone = dataPhone;
    }

}
