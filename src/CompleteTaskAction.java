// CompleteTaskAction class allows marking a task as completed and supports undoing the action
public class CompleteTaskAction implements Action {
    private Task task;                   // The task to be marked as completed
    private boolean previousCompletionState; // Stores the task's completion state before the action

    // Constructor to initialize the action with the task
    public CompleteTaskAction(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        this.task = task;
        this.previousCompletionState = task.isCompleted(); // Save the initial completion state
    }

    // Executes the action to mark the task as completed
    @Override
    public void execute() {
        task.markAsCompleted(); // Set the task as completed
    }

    // Undoes the action by restoring the task's previous completion state
    @Override
    public void undo() {
        if (previousCompletionState) {
            task.markAsCompleted(); // Restore to completed if it was completed before
        } else {
            task.markAsNotCompleted(); // Restore to not completed if it was incomplete before
        }
    }
}

