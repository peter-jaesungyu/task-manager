package service;

import entity.Status;
import entity.Task;

import java.time.LocalDateTime;

public class TaskParser {
    public static Task fromString(String json) {
        json = json.replaceAll("[{}\\[\\]\n]", ""); // Remove brackets and quotes
        String[] keyValues = json.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split by "," only if it's outside of quotes

        int id = 0;
        String description = null;
        Status status = Status.TODO;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        boolean isDeleted = false;

        for (String keyValue : keyValues) {
            if (!keyValue.isEmpty()) {
                String[] pair = keyValue.split(":");
                String key = pair[0].replaceAll("[\"]", "").trim();
                String value = pair[1].replaceAll("[\"]", "").trim();

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
                        createdAt = LocalDateTime.parse(keyValue.replace("\"createdAt\" : ", "").replaceAll("[\"]", ""));
                        break;
                    case "updatedAt":
                        updatedAt = LocalDateTime.parse(keyValue.replace("\"updatedAt\" : ", "").replaceAll("[\"]", ""));
                        break;
                    case "isDeleted":
                        isDeleted = Boolean.parseBoolean(value);
                        break;
                }
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
