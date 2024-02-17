package com.example.powerstrentgh.ModelCLass;

public class TaskManagerModelClass {
    String CurrentUserId,TaskTitle,TaskType,CilentuserID,cilentusername,cilentuserimage,TaskDay;

    public TaskManagerModelClass(String currentUserId, String taskTitle, String taskType, String cilentuserID, String cilentusername, String cilentuserimage, String taskDay) {
        CurrentUserId = currentUserId;
        TaskTitle = taskTitle;
        TaskType = taskType;
        CilentuserID = cilentuserID;
        this.cilentusername = cilentusername;
        this.cilentuserimage = cilentuserimage;
        TaskDay = taskDay;
    }

    public TaskManagerModelClass() {
    }

    public String getCurrentUserId() {
        return CurrentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        CurrentUserId = currentUserId;
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

    public String getCilentuserID() {
        return CilentuserID;
    }

    public void setCilentuserID(String cilentuserID) {
        CilentuserID = cilentuserID;
    }

    public String getCilentusername() {
        return cilentusername;
    }

    public void setCilentusername(String cilentusername) {
        this.cilentusername = cilentusername;
    }

    public String getCilentuserimage() {
        return cilentuserimage;
    }

    public void setCilentuserimage(String cilentuserimage) {
        this.cilentuserimage = cilentuserimage;
    }

    public String getTaskDay() {
        return TaskDay;
    }

    public void setTaskDay(String taskDay) {
        TaskDay = taskDay;
    }
}
