package repository;

import entity.Task;
import fileIO.JsonManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepository {
    private final JsonManager jsonManager;
    private Map<Integer, Task> tasks;
    private int size;

    public TaskRepository() {
        this.jsonManager = new JsonManager();
        this.tasks = jsonManager.readJson().stream().collect(Collectors.toMap(Task::getId, obj -> obj));
        if (tasks.isEmpty()) {
            this.size = 0;
        } else {
            this.size = tasks.size();
        }
    }

    // findById
    public Optional<Task> findById(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    // add
    public int add(String description) {
        Task task = new Task(size + 1);
        task.setDescription(description);
        int id = task.getId();
        tasks.put(id, task);
        jsonManager.writeJson(tasks.values().stream().toList());
        return id;
    }

    // update
    public void update(int id, String description) {
        Optional<Task> byId = findById(id);

        if (byId.isEmpty()) {
            throw new IllegalStateException("Not found id number " + id);
        } else {
            Task task = byId.get();
            task.setDescription(description);
            task.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            tasks.replace(id, task);
            jsonManager.writeJson(tasks.values().stream().toList());
        }
    }

    // delete

    // mark-in-progress

    // mark-done

    // list

    // list done

    // list todo

    // list in-progress
}
