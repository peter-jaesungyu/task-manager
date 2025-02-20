package fileIO;

import entity.Task;
import service.TaskParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    private final File file;

//    public JsonManager(String jsonFilePath) {
//        this.file = new File(jsonFilePath, "/MyTasks.json");
//    }

    public JsonManager() {
        this.file = new File("MyTasks.json");

        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created : " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred when creating a JSON file!", e);
        }
    }

    // input
    public List<Task> readJson() {
        List<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks; // returns empty List when file does not exist
        }

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
        {
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                Task task = TaskParser.fromString(string);
                tasks.add(task);
            }
            return tasks;
        } catch (IOException e) {
            throw new RuntimeException("Error occurred when reading a JSON file!", e);
        }
    }

    // output
    public void writeJson(List<Task> tasks) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created : " + file.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("Error occurred when creating a JSON file!", e);
            }
        }

        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file)))
        {
            bufferedWriter.write("["); // Start JSON array
            for (int i = 0; i < tasks.size(); i++) {
                String string = tasks.get(i).toString();
                bufferedWriter.write(string);
                if (i < tasks.size() - 1) {
                    bufferedWriter.write(",\n"); // Add a comma between JSON objects
                }
            }
            bufferedWriter.write("]"); // Close JSON array
        } catch (IOException e) {
            throw new RuntimeException("Error occurred when writing a JSON file!", e);
        }
    }
}
