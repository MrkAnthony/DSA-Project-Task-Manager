public class Task {
    private int id;
    private String description;
    private String priority;
    private boolean isCompleted;


    public Task(int id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void markAsCompleted() {
        isCompleted = true;
    }
    public void markAsNotCompleted() {isCompleted = false;}
    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", priority=" + priority + ", isCompleted=" +
                isCompleted + "]";
    }
}

