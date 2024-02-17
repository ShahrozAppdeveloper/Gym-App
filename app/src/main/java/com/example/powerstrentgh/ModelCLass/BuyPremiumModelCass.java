package com.example.powerstrentgh.ModelCLass;

public class BuyPremiumModelCass {
    String userID, username, AccountDetail, email;

    public BuyPremiumModelCass(String userID, String username, String accountDetail, String email) {
        this.userID = userID;
        this.username = username;
        AccountDetail = accountDetail;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountDetail() {
        return AccountDetail;
    }

    public void setAccountDetail(String accountDetail) {
        AccountDetail = accountDetail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}