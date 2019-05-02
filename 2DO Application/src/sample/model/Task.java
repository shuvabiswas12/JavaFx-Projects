package sample.model;

import java.sql.Timestamp;

public class Task {

    private Timestamp dateCreated;
    private String task;
    private String description;
    private int userId;
    private int taskId;

    public Task() {

    }

    public Task(Timestamp dateCreated, String description, String task) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.task = task;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public int getUserId() {
        return userId;
    }

    public int getTaskIdI() {
        return taskId;
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }
}
