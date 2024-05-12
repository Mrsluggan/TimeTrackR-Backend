package com.TimeTrackR.TimeTrackR.Model.Users.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.TimeTrackR.TimeTrackR.Model.Tasks.Tasks;

@Document(collection = "Users")
public class Users {

    @Id
    private String userId;
    private String name;
    private String password;
    private List<Tasks> listOfTasks;
    private Boolean isAdmin;





    
    public Users(String name, String password, List<Tasks> listOfTasks, Boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.listOfTasks = listOfTasks;
        this.isAdmin = isAdmin;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Tasks> getListOfTasks() {
        return listOfTasks;
    }
    public void setListOfTasks(List<Tasks> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }






    
}
