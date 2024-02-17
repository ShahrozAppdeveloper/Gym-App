package com.example.powerstrentgh.ModelCLass;

public class Trackprogresstrainermodel {

String TaskDay,TaskTitle,TaskType,Cilentusername;


    public Trackprogresstrainermodel(String taskDay, String taskTitle, String taskType, String cilentusername) {
        TaskDay = taskDay;
        TaskTitle = taskTitle;
        TaskType = taskType;
        Cilentusername = cilentusername;
    }

    public Trackprogresstrainermodel() {
    }

    public String getTaskDay() {
        return TaskDay;
    }

    public void setTaskDay(String taskDay) {
        TaskDay = taskDay;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public String getTaskType() {
        return TaskType;
    }

    public void setTaskType(String taskType) {
        TaskType = taskType;
    }

    public String getCilentusername() {
        return Cilentusername;
    }

    public void setCilentusername(String cilentusername) {
        Cilentusername = cilentusername;
    }
}
