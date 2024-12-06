import java.util.List;

// AddAction class represents an action to add a Task to a task list
public class AddAction implements Action {
    private List<Task> taskList; // The task list to add the task to
    private Task task;          // The task to be added
    private int addedIndex;     // Index where the task was added

    // Constructor to initialize the action with a task list and task
    public AddAction(List<Task> taskList, Task task) {
        if (taskList == null || task == null) {
            throw new IllegalArgumentException("Task list and task cannot be null");
        }
        this.taskList = taskList;
        this.task = task;
    }

    // Executes the action to add the task to the list
    @Override
    public void execute() {
        if (taskList.contains(task)) {
            throw new IllegalArgumentException("Task already exists in the list");
        }
        addedIndex = taskList.size(); // Record the index for potential undo
        taskList.add(task);          // Add the task to the list
    }

    // Undoes the action by removing the task from the list
    @Override
    public void undo() {
        if (addedIndex >= 0 && addedIndex < taskList.size()) {
            taskList.remove(addedIndex); // Remove the task using its index
        } else {
            throw new IllegalArgumentException("Invalid index for undo operation");
        }
    }
}
