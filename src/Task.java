import java.util.Date;
import java.util.Objects;

// Task class representing a to-do item with priority, status, and other attributes
public class Task implements Comparable<Task> {
    // Constants for priority levels
    private static final String PRIORITY_HIGH = "High";
    private static final String PRIORITY_MEDIUM = "Medium";
    private static final String PRIORITY_LOW = "Low";

    // Task attributes
    private int id;                    // Unique task identifier
    private String description;        // Task description
    private String priority;           // Task priority level
    private boolean isCompleted;       // Completion status
    private Date creationDate;         // Date of task creation
    private Date completionDate;       // Date of task completion
    private String category;           // Task category (optional)

    // Constructor to initialize Task with id, description, and priority
    public Task(int id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = validatePriority(priority);
        this.isCompleted = false;
        this.creationDate = new Date();
        this.completionDate = null;
    }

    // Validates and sets the priority level
    private String validatePriority(String priority) {
        if (priority == null || priority.trim().isEmpty()) {
            throw new IllegalArgumentException("Priority cannot be null or empty");
        }
        String upperPriority = priority.trim().toUpperCase();
        if (!upperPriority.equals(PRIORITY_HIGH.toUpperCase()) &&
                !upperPriority.equals(PRIORITY_MEDIUM.toUpperCase()) &&
                !upperPriority.equals(PRIORITY_LOW.toUpperCase())) {
            throw new IllegalArgumentException("Invalid priority level");
        }
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Marks the task as completed and sets the completion date
    public void markAsCompleted() {
        this.completionDate = new Date();
    }

    // Resets task completion status and clears the completion date
    public void markAsNotCompleted() {
        this.completionDate = null;
    }

    // Compares tasks based on priority levels
    @Override
    public int compareTo(Task o) {
        return getPriorityValue(this.priority) - getPriorityValue(o.priority);
    }

    // Helper method to convert priority to numeric value
    private int getPriorityValue(String priority) {
        if (priority.equalsIgnoreCase(PRIORITY_HIGH)) {
            return 1;
        } else if (priority.equalsIgnoreCase(PRIORITY_MEDIUM)) {
            return 2;
        } else if (priority.equalsIgnoreCase(PRIORITY_LOW)) {
            return 3;
        }
        return 3; // Default for invalid priority
    }

    // Overrides toString() to format Task details for display
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Task ID: ").append(id);
        sb.append("\nDescription: ").append(description);
        sb.append("\nPriority:  ").append(priority);

        if (isCompleted && completionDate != null) {
            sb.append("\nCompleted On: ").append(completionDate);
        } else {
            sb.append("\nStatus: Pending");
        }

        if (category != null && !category.trim().isEmpty()) {
            sb.append("\nCategory: ").append(category);
        }
        return sb.toString();
    }

    // Overrides equals() to compare tasks by id, description, and priority
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) obj;
        return this.id == otherTask.id &&
                Objects.equals(this.description, otherTask.description) &&
                Objects.equals(this.priority, otherTask.priority);
    }

    // Overrides hashCode() to compute a hash based on id, description, and priority
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + (description == null ? 0 : description.hashCode());
        result = 31 * result + (priority == null ? 0 : priority.hashCode());
        return result;
    }
}
