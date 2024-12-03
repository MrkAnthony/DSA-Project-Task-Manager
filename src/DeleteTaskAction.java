import java.util.ArrayList;
import java.util.List;

public class DeleteTaskAction implements Action {
    private Task task;
    private List<Task> tasks = new ArrayList<Task>();

    public DeleteTaskAction(Task task, List<Task> tasks) {
        this.task = task;
        this.tasks = tasks;
    }
    @Override
    public void execute() {
        tasks.remove(task);
    }

    @Override
    public void undo() {
        tasks.add(task);

    }
}
