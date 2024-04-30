package com.TimeTrackR.TimeTrackR.Tasks;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tasks")
public class Tasks {

    @Id
    private String id;
    // Task variables
    private String taskName;
    private Boolean isActive;
    private Date startDate;
    private Date endDate;
    private long totalTime;

    public Tasks(String id, String taskName, Boolean isActive, Date startDate, Date endDate, long totalTime) {
        this.id = id;
        this.taskName = taskName;
        this.isActive = isActive;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalTime = totalTime;
    }



    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public long getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }


}
