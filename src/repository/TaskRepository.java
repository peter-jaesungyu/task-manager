package repository;

import entity.Status;
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
        return Optional.ofNullable(tasks.get(id))
                .filter(task -> !task.isDeleted());
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
    public int update(int id, String description) {
        Task task = findById(id).orElseThrow(() -> new IllegalStateException("Not found id number " + id));
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        tasks.replace(id, task);
        jsonManager.writeJson(tasks.values().stream().toList());
        return id;
    }

    // delete
    public int delete(int id) {
        Task task = findById(id).orElseThrow(() -> new IllegalStateException("Not found id number " + id));
        task.setDeleted(true);
        task.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        tasks.replace(id, task);
        jsonManager.writeJson(tasks.values().stream().toList());
        return id;
    }

    // mark-in-progress, mark-done
    public int updateStatus(int id, Status status) {
        Task task = findById(id).orElseThrow(() -> new IllegalStateException("Not found id number " + id));
        task.setStatus(status);
        tasks.replace(id, task);
        jsonManager.writeJson(tasks.values().stream().toList());
        return id;
    }

    // list
    public List<Task> findAll() {
        return tasks.values().stream().filter(o -> !o.isDeleted()).toList();
    }

    // list done, todo, list in-progress
    public List<Task> findByStatus(Status status) {
        return tasks.values().stream().filter(o -> !o.isDeleted() && o.getStatus() == status).collect(Collectors.toList());
    }
}
