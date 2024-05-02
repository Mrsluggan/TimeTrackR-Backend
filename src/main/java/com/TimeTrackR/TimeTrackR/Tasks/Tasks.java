package com.TimeTrackR.TimeTrackR.Tasks;

import java.time.LocalDateTime;
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
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long totalTime;

    public Tasks(String id, String taskName, Boolean isActive, LocalDateTime startDate, LocalDateTime endDate, long totalTime) {
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
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public long getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }


}
