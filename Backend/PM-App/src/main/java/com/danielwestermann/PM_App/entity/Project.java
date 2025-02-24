package com.danielwestermann.PM_App.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String title;
    private Long progress;
    @Column(name="TEAMID")
    private Long teamId;

    // Default constructor
    public Project() {
    }

    // All-args constructor
    public Project(Long id, String description, String title, Long progress,
                   Long teamId) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.progress = progress;
        this.teamId = teamId;
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

    public Long getProgress() { return progress; }

    public Long getTeamId() { return teamId; }

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

    public void setProgress(Long progress) { this.progress = progress; }

    public void setTeamId(Long teamId) { this.teamId = teamId; }

    // toString method
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", progress=" + progress +
                ", teamId='" + teamId + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(description, project.description) &&
                Objects.equals(title, project.title) &&
                Objects.equals(progress, project.progress) &&
                Objects.equals(teamId, project.teamId);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, description, title, progress, teamId);
    }
}