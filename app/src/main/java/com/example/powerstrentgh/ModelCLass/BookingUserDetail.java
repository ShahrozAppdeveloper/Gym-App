package com.example.powerstrentgh.ModelCLass;

public class BookingUserDetail {
    String trainnername,trainnerimage,TrainnerID,BookinguserID;

    public BookingUserDetail() {
    }

    public BookingUserDetail(String trainnername, String trainnerimage, String trainnerID, String bookinguserID) {
        this.trainnername = trainnername;
        this.trainnerimage = trainnerimage;
        TrainnerID = trainnerID;
        BookinguserID = bookinguserID;
    }

    public String getTrainnername() {
        return trainnername;
    }

    public void setTrainnername(String trainnername) {
        this.trainnername = trainnername;
    }

    public String getTrainnerimage() {
        return trainnerimage;
    }

    public void setTrainnerimage(String trainnerimage) {
        this.trainnerimage = trainnerimage;
    }

    public String getTrainnerID() {
        return TrainnerID;
    }

    public void setTrainnerID(String trainnerID) {
        TrainnerID = trainnerID;
    }

    public String getBookinguserID() {
        return BookinguserID;
    }

    public void setBookinguserID(String bookinguserID) {
        BookinguserID = bookinguserID;
    }
}
