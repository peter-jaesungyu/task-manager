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
    public int update(int id, String description) {
        return taskRepository.update(id, description);
    }

    // delete
    public int delete(int id) {
        return taskRepository.delete(id);
    }

    // mark-in-progress, mark-done
    public int changeStatus(int id, Status status) {
        return taskRepository.updateStatus(id, status);
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
