import java.util.ArrayList;
import java.util.List;

public class AddAction implements Action {
    List<Task> taskList = new ArrayList<Task>();
    Task task;
    public AddAction(List<Task> taskList, Task task) {
    this.taskList = taskList;
    this.task = task;

    }
    @Override
    public void execute() {
        taskList.add(task);
    }

    @Override
    public void undo() {
        taskList.remove(task);

    }
}
