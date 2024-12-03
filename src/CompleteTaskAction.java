import java.util.ArrayList;
import java.util.List;

public class CompleteTaskAction implements Action {
    private Task task;

    public CompleteTaskAction(Task task) {
        this.task = task;
    }
    @Override
    public void execute() {
    task.markAsCompleted();
    }
    @Override
    public void undo() {
        task.markAsNotCompleted();
    }
}
