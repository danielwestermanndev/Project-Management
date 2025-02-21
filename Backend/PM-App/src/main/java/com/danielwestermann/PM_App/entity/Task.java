package com.danielwestermann.PM_App.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String title;

    @Column(name="PROJECTID")
    private Long projectId;
    private String status;
    @Column(name="USERID")
    private Long userId;
    @Column(name="DUEDATE")
    private Date dueDate;

    // Default constructor
    public Task() {
    }

    // All-args constructor
    public Task(Long id, String description, String title, Long projectId,
                String status, Long userId, Date dueDate) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.projectId = projectId;
        this.status = status;
        this.userId = userId;
        this.dueDate = dueDate;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getStatus() {
        return status;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", projectId=" + projectId +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", dueDate=" + dueDate +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(description, task.description) &&
                Objects.equals(title, task.title) &&
                Objects.equals(projectId, task.projectId) &&
                Objects.equals(status, task.status) &&
                Objects.equals(userId, task.userId) &&
                Objects.equals(dueDate, task.dueDate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, description, title, projectId, status, userId, dueDate);
    }
}