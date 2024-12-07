import java.util.*;

// TaskManager class for managing tasks, including add, delete, complete, undo, and redo operations
public class TaskManager {

    // Map to store tasks with their unique IDs as keys
    private Map<Integer, Task> taskMap = new HashMap<Integer, Task>();

    // Priority queue to manage tasks sorted by priority
    private PriorityQueue<Task> priorityQueue = new PriorityQueue<Task>();

    // Stacks to track actions for undo and redo functionality
    private Stack<Action> undoAction = new Stack<>();
    private Stack<Action> redoAction = new Stack<>();

    // Variable to generate unique IDs for tasks
    private int nextID;

    // Constructor to initialize the TaskManager
    public TaskManager() {
        this.nextID = 1; // Start IDs from 1
    }

    // Method to add a new task
    public Task addTask(String description, String priority) {
        // Create a new task with a unique ID, description, and priority
        Task task = new Task(nextID++, description, priority);

        // List of current tasks for action tracking
        List<Task> tasklist = new ArrayList<>(taskMap.values());

        // Create an action for adding the task and execute it
        Action action = new AddAction(tasklist, task);
        executeAction(action);

        // Add the task to the taskMap and priorityQueue
        taskMap.put(task.getId(), task);
        priorityQueue.offer(task);

        return task; // Return the newly added task
    }

    // Method to delete an existing task by its ID
    public Task deleteTask(int id) {
        // Get the task to be deleted from the taskMap
        Task task = taskMap.get(id);

        if (task != null) {
            // Create an action for deleting the task and execute it
            Action action = new DeleteTaskAction(task, new ArrayList<>(taskMap.values()));
            executeAction(action);

            // Remove the task from the taskMap and priorityQueue
            taskMap.remove(task.getId());
            priorityQueue.remove(task);
        } else {
            // Throw an error if the task is not found
            throw new IllegalArgumentException("Task not found");
        }
        return task; // Return the deleted task
    }

    // Method to mark a task as completed by its ID
    public Task completeTask(int id) {
        // Get the task to be marked as completed
        Task task = taskMap.get(id);

        if (task != null) {
            // Create an action for completing the task and execute it
            Action action = new CompleteTaskAction(task);
            executeAction(action);
        } else {
            // Throw an error if the task is not found
            throw new IllegalArgumentException("Task not found");
        }
        return task; // Return the completed task
    }

    // Method to execute an action and update undo/redo stacks
    public void executeAction(Action action) {
        action.execute(); // Execute the action
        undoAction.push(action); // Add action to undo stack
        redoAction.clear(); // Clear the redo stack since a new action has been executed
    }

    // Method to undo the last action
    public void undoAction() {
        if (!undoAction.isEmpty()) {
            // Pop the last action from the undo stack and undo it
            Action action = undoAction.pop();
            action.undo();
            redoAction.push(action); // Add the undone action to the redo stack
        } else {
            // Throw an error if there are no actions to undo
            throw new IllegalArgumentException("Action not found");
        }
    }

    // Method to redo the last undone action
    public void redoAction() {
        if (!redoAction.isEmpty()) {
            // Pop the last undone action from the redo stack and execute it
            Action action = redoAction.pop();
            action.execute();
            undoAction.push(action); // Add the redone action to the undo stack
        } else {
            // Throw an error if there are no actions to redo
            throw new IllegalArgumentException("Action not found");
        }
    }

    // Method to get tasks sorted by priority
    public List<Task> getTaskByPriority() {
        // Create a list from the priorityQueue for sorted tasks
        List<Task> sortedTask = new ArrayList<>(priorityQueue);
        return sortedTask; // Return the sorted list of tasks
    }

    // Method to get all tasks
    public Collection<Task> getAllTask() {
        return taskMap.values(); // Return all tasks from the taskMap
    }

    // Method to get a specific task by its ID
    public Task getTask(int id) {
        return taskMap.get(id); // Return the task with the specified ID
    }
}
