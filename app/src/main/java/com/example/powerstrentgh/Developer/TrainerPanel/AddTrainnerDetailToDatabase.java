package com.example.powerstrentgh.Developer.TrainerPanel;

public class AddTrainnerDetailToDatabase {
    String trainnerID,trainnername,bookingstatus,trainnerimageurl,trainnerspec,trainnerstatus;

    public AddTrainnerDetailToDatabase() {
    }

    public AddTrainnerDetailToDatabase(String trainnerID, String trainnername, String bookingstatus, String trainnerimageurl, String trainnerspec, String trainnerstatus) {
        this.trainnerID = trainnerID;
        this.trainnername = trainnername;
        this.bookingstatus = bookingstatus;
        this.trainnerimageurl = trainnerimageurl;
        this.trainnerspec = trainnerspec;
        this.trainnerstatus = trainnerstatus;
    }

    public String getTrainnerID() {
        return trainnerID;
    }

    public void setTrainnerID(String trainnerID) {
        this.trainnerID = trainnerID;
    }

    public String getTrainnername() {
        return trainnername;
    }

    public void setTrainnername(String trainnername) {
        this.trainnername = trainnername;
    }

    public String getBookingstatus() {
        return bookingstatus;
    }

    public void setBookingstatus(String bookingstatus) {
        this.bookingstatus = bookingstatus;
    }

    public String getTrainnerimageurl() {
        return trainnerimageurl;
    }

    public void setTrainnerimageurl(String trainnerimageurl) {
        this.trainnerimageurl = trainnerimageurl;
    }

    public String getTrainnerspec() {
        return trainnerspec;
    }

    public void setTrainnerspec(String trainnerspec) {
        this.trainnerspec = trainnerspec;
    }

    public String getTrainnerstatus() {
        return trainnerstatus;
    }

    public void setTrainnerstatus(String trainnerstatus) {
        this.trainnerstatus = trainnerstatus;
    }
}
