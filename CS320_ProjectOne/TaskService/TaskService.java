import java.util.HashMap;
import java.util.Map;

public class TaskService {
    // stores tasks in memory using their id as the key
    private final Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }

        // make sure an id is not already being used
        String taskId = task.getTaskId();
        if (tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("A task with this ID already exists.");
        }

        tasks.put(taskId, task);
    }

    public void deleteTask(String taskId) {
        // only remove the task if its id exists
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID was not found.");
        }

        tasks.remove(taskId);
    }

    public void updateTask(String taskId, String name, String description) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task ID was not found.");
        }

        // validate every replacement value before changing the stored task
        Task validatedTask = new Task(taskId, name, description);

        task.setName(validatedTask.getName());
        task.setDescription(validatedTask.getDescription());
    }

    // mainly used to find a task by its id
    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }
}
