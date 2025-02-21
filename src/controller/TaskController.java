package controller;

import entity.Flag;
import entity.Status;
import service.TaskService;

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
        taskService.update(Integer.parseInt(args[1]), args[2]);
    }

    // delete
    public void deleteTask() {
        taskService.delete(Integer.parseInt(args[1]));
    }

    // mark-in-progress, mark-done
    public void changeStatus(Status status) {
        taskService.changeStatus(Integer.parseInt(args[1]), status);
    }

    // list

    // list done

    // list todo

    // list in-progress

}
