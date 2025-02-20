package entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Task {
    // FIELDS
    private final int id;
    private String description;
    private Status status = Status.TODO;

//        dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private LocalDateTime updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private boolean isDeleted = false;

    // CONSTRUCTOR
    public Task(int id) {
        this.id = id;
    }

    // GETTER, SETTER
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    // toSTRING
    @Override
    public String toString() {
        return "{" +
                "\"id\" : \""+ id + "\"" +
                ",\"description\" : \"" + description + '\"' +
                ",\"status\" : \"" + status + '\"' +
                ",\"createdAt\" : \"" + createdAt + '\"' +
                ",\"updatedAt\" : \"" + updatedAt + '\"' +
                ",\"isDeleted\" : \"" + isDeleted + '\"' +
                "}";
    }
}
