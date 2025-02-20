import controller.TaskController;

public class TaskCliApplication {
    public static void main(String[] args) {
        TaskController taskController = new TaskController(args);
        taskController.resolveCommand();
    }
}
