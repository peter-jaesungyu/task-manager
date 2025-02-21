package service;

import entity.Status;
import entity.Task;
import entity.TaskResponseDto;
import repository.TaskRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final String[] args;
    private final TaskRepository taskRepository;

    public TaskService(String[] args) {
        this.args = args;
        this.taskRepository = new TaskRepository();
    }

    // add
    public int add() {
        return taskRepository.add(args[1]);
    }

    // update
    public void update(int id, String description) {
        taskRepository.update(id, description);
    }

    // delete
    public void delete(int id) {
        taskRepository.delete(id);
    }

    // mark-in-progress, mark-done
    public void changeStatus(int id, Status status) {
        taskRepository.updateStatus(id, status);
    }

    // list
    public List<TaskResponseDto> listAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponseDto taskResponseDto = new TaskResponseDto(
                    task.getId(), task.getDescription(), task.getStatus().toString(),
                    task.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    task.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            taskResponseDtos.add(taskResponseDto);
        }
        return taskResponseDtos;
    }

    // list done, todo, list in-progress
    public List<TaskResponseDto> listByStatus(Status status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponseDto taskResponseDto = new TaskResponseDto(
                    task.getId(), task.getDescription(), task.getStatus().toString(),
                    task.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    task.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            taskResponseDtos.add(taskResponseDto);
        }
        return taskResponseDtos;
    }
}
