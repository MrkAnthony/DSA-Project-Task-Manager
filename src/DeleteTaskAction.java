import java.util.List;

// DeleteTaskAction class allows deleting a task from a list and supports undoing the deletion
public class DeleteTaskAction implements Action {
    private Task task;            // The task to be deleted
    private List<Task> tasksList; // The list of tasks
    private int deletedIndex;     // The index of the deleted task

    // Constructor to initialize the action with a task and task list
    public DeleteTaskAction(Task task, List<Task> tasksList) {
        if (task == null || tasksList == null) {
            throw new IllegalArgumentException("Task and task list cannot be null");
        }
        this.task = task;
        this.tasksList = tasksList;
    }

    // Executes the action to delete the task from the list
    @Override
    public void execute() {
        if (!tasksList.contains(task)) {
            throw new IllegalArgumentException("Task does not exist in the list");
        }
        deletedIndex = tasksList.indexOf(task); // Record the task's index for undo
        tasksList.remove(task);                // Remove the task from the list
    }

    // Undoes the action by re-adding the deleted task to its original position
    @Override
    public void undo() {
        if (deletedIndex < 0 || deletedIndex > tasksList.size()) {
            throw new IllegalArgumentException("Invalid index for undo operation");
        }
        tasksList.add(deletedIndex, task); // Re-insert the task at its original index
    }
}
