package service;

import entity.Status;
import entity.Task;

import java.time.LocalDateTime;

public class TaskParser {
    public static Task fromString(String json) {
        json = json.replaceAll("[{}\\[\\]\"\n]", ""); // Remove brackets and quotes
        String[] keyValues = json.split(",");

        int id = 0;
        String description = null;
        Status status = Status.TODO;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        boolean isDeleted = false;

        for (String keyValue : keyValues) {
            String[] pair = keyValue.split(":");
            String key = pair[0].trim();
            String value = pair[1].trim();

            switch (key) {
                case "id":
                    id = Integer.parseInt(value);
                    break;
                case "description":
                    description = value;
                    break;
                case "status":
                    status = Status.fromString(value);
                    break;
                case "createdAt":
                    createdAt = LocalDateTime.parse(keyValue.replaceAll("createdAt : ", ""));
                    break;
                case "updatedAt":
                    updatedAt = LocalDateTime.parse(keyValue.replaceAll("updatedAt : ", ""));
                    break;
                case "isDeleted":
                    isDeleted = Boolean.parseBoolean(value);
                    break;
            }
        }

        Task task = new Task(id);
        task.setDescription(description);
        task.setStatus(status);
        task.setCreatedAt(createdAt);
        task.setUpdatedAt(updatedAt);
        task.setDeleted(isDeleted);

        return task;
    }
}
