package controller;

import entity.Flag;
import entity.Status;
import entity.TaskResponseDto;
import service.TaskService;

import java.util.List;

public class TaskController {
    private final String[] args;
    private final TaskService taskService;

    public TaskController(String[] args) {
        this.args = args;
        this.taskService = new TaskService(args);
    }

    // switching
    public void resolveCommand() {
        switch (Flag.fromString(args[0])) {
            case ADD :
                if (args.length != 2) {
                    throw new IllegalArgumentException("Invalid command line error! Expected 2 argument but the actual number of arguments is " + args.length);
                }
                addTask();
                break;
            case UPDATE :
                if (args.length != 3) {
                    throw new IllegalArgumentException("Invalid command line error! Expected 3 argument but the actual number of arguments is " + args.length);
                }
                updateTask();
                break;
            case DELETE :
                if (args.length != 2) {
                    throw new IllegalArgumentException("Invalid command line error! Expected 2 argument but the actual number of arguments is " + args.length);
                }
                deleteTask();
                break;
            case MARK_DONE :
                if (args.length != 2) {
                    throw new IllegalArgumentException("Invalid command line error! Expected 2 argument but the actual number of arguments is " + args.length);
                }
                changeStatus(Status.DONE);
                break;
            case MARK_IN_PROGRESS :
                if (args.length != 2) {
                    throw new IllegalArgumentException("Invalid command line error! Expected 2 argument but the actual number of arguments is " + args.length);
                }
                changeStatus(Status.IN_PROGRESS);
                break;
            case LIST :
                if (args.length == 1) {
                    listTasks();
                } else if (Status.fromString(args[1]) != null && args.length == 2) {
                    listTasksByStatus(Status.fromString(args[1]));
                } else {
                    throw new IllegalArgumentException("Invalid command line error! Expected at least 1 argument but the actual number of arguments is " + args.length);
                }
                break;
            default :
                System.out.println("Invalid command error! Unable to process your command");
                break;
        }
    }

    // add
    public void addTask() {
        int taskId = taskService.add();
        System.out.printf("Task added successfully (ID: %d)", taskId);
    }

    // update
    public void updateTask() {
        int taskId = taskService.update(Integer.parseInt(args[1]), args[2]);
        System.out.printf("Task updated successfully (ID: %d)", taskId);
    }

    // delete
    public void deleteTask() {
        int taskId = taskService.delete(Integer.parseInt(args[1]));
        System.out.printf("Task deleted successfully (ID: %d)", taskId);
    }

    // mark-in-progress, mark-done
    public void changeStatus(Status status) {
        int taskId = taskService.changeStatus(Integer.parseInt(args[1]), status);
        System.out.printf("Task's status changed successfully (ID: %d)", taskId);
    }

    // list
    public void listTasks() {
        List<TaskResponseDto> taskResponseDtos = taskService.listAll();
        for (TaskResponseDto dto : taskResponseDtos) {
            System.out.println(dto);
        }
    }

    // list done, todo, list in-progress
    public void listTasksByStatus(Status status) {
        List<TaskResponseDto> taskResponseDtos = taskService.listByStatus(status);
        for (TaskResponseDto dto : taskResponseDtos) {
            System.out.println(dto);
        }
    }
}
