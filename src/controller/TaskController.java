package controller;

import entity.Flag;
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

    // mark-in-progress

    // mark-done

    // list

    // list done

    // list todo

    // list in-progress

}
