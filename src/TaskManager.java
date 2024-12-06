import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class TaskManager {
    private Map<Integer, Task> taskMap = new HashMap<Integer, Task>();
    private PriorityQueue<Task> priorityQueue = new PriorityQueue<Task>();
    private Stack<Task> undoAction = new Stack<>();
    private Stack<Task> redoAction = new Stack<>();
    private int nextID;

    public TaskManager(Map<Integer, Task> taskMap, PriorityQueue<Task> priorityQueue, Stack<Task> undoAction, Stack<Task> redoAction
    ) {
        this.taskMap = taskMap;
        this.priorityQueue = priorityQueue;
        this.undoAction = undoAction;
        this.redoAction = redoAction;
        this.nextID = 1;
    }
}
