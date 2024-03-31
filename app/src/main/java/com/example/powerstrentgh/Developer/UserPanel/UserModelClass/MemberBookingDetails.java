package com.example.powerstrentgh.Developer.UserPanel.UserModelClass;

public class MemberBookingDetails {
    String memberID,TrainnerID,reqID,reqstatus;

    public MemberBookingDetails() {
    }

    public MemberBookingDetails(String memberID, String trainnerID, String reqID, String reqstatus) {
        this.memberID = memberID;
        TrainnerID = trainnerID;
        this.reqID = reqID;
        this.reqstatus = reqstatus;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getTrainnerID() {
        return TrainnerID;
    }

    public void setTrainnerID(String trainnerID) {
        TrainnerID = trainnerID;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getReqstatus() {
        return reqstatus;
    }

    public void setReqstatus(String reqstatus) {
        this.reqstatus = reqstatus;
    }
}
