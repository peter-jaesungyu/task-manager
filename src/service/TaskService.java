package service;

import repository.TaskRepository;

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

    // delete

    // mark-in-progress

    // mark-done

    // list

    // list done

    // list todo

    // list in-progress
}
