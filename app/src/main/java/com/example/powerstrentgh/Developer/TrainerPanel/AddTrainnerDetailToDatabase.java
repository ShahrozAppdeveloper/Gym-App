package com.example.powerstrentgh.Developer.TrainerPanel;

public class AddTrainnerDetailToDatabase {
    String trainnerID,trainnername,trainnerimageurl,trainnerspec;

    public AddTrainnerDetailToDatabase() {
    }

    public AddTrainnerDetailToDatabase(String trainnerID, String trainnername, String trainnerimageurl, String trainnerspec) {
        this.trainnerID = trainnerID;
        this.trainnername = trainnername;
        this.trainnerimageurl = trainnerimageurl;
        this.trainnerspec = trainnerspec;
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
}
