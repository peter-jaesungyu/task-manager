package entity;

public class TaskResponseDto {
    private int id;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

    public TaskResponseDto(int id, String description, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "{" +
                '\'' + description + '\'' +
                ",\n Task id : " + id +
                ", status : '" + status + '\'' +
                ", createdAt : '" + createdAt + '\'' +
                ", updatedAt : '" + updatedAt + '\'' +
                "}\n";
    }
}
