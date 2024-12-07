import java.util.Date;
import java.util.Objects;

// Task class representing a to-do item with attributes like ID, priority, status, and more
public class Task implements Comparable<Task> {

    // Constants for priority levels
    private static final String PRIORITY_HIGH = "High";
    private static final String PRIORITY_MEDIUM = "Medium";
    private static final String PRIORITY_LOW = "Low";

    // Attributes of the task
    private int id;                    // Unique task identifier
    private String description;        // Description of the task
    private String priority;           // Priority of the task (High, Medium, Low)
    private boolean isCompleted;       // Completion status of the task
    private Date creationDate;         // Date when the task was created
    private Date completionDate;       // Date when the task was completed (if any)
    private String category;           // Optional category of the task

    // Constructor to initialize a Task object
    public Task(int id, String description, String priority) {
        this.id = id;                           // Assign task ID
        this.description = description;         // Set task description
        this.priority = validatePriority(priority); // Validate and set the priority
        this.isCompleted = false;               // Mark task as not completed initially
        this.creationDate = new Date();         // Set creation date to current date
        this.completionDate = null;             // No completion date initially
    }

    // Method to validate the priority input and ensure it is valid
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
        return priority; // Return validated priority
    }

    // Getter method to check if the task is completed
    public boolean isCompleted() {
        return isCompleted;
    }

    // Getter method for the task description
    public String getDescription() {
        return description;
    }

    // Getter method for the task priority
    public String getPriority() {
        return priority;
    }

    // Method to mark the task as completed
    public void markAsCompleted() {
        this.completionDate = new Date(); // Set completion date to current date
        this.isCompleted = true;         // Mark task as completed
    }

    // Method to reset task completion status
    public void markAsNotCompleted() {
        this.completionDate = null;      // Clear the completion date
        this.isCompleted = false;        // Mark task as not completed
    }

    // Getter method for the task ID
    public int getId() {
        return id;
    }

    // Method to compare tasks based on their priority for sorting
    @Override
    public int compareTo(Task o) {
        // Higher priority tasks are ordered before lower priority tasks
        return getPriorityValue(o.priority) - getPriorityValue(this.priority);
    }

    // Helper method to convert priority levels to numeric values for comparison
    private int getPriorityValue(String priority) {
        if (priority.equalsIgnoreCase(PRIORITY_HIGH)) {
            return 1; // High priority has the highest precedence
        } else if (priority.equalsIgnoreCase(PRIORITY_MEDIUM)) {
            return 2; // Medium priority has moderate precedence
        } else if (priority.equalsIgnoreCase(PRIORITY_LOW)) {
            return 3; // Low priority has the lowest precedence
        }
        return 3; // Default to lowest precedence for invalid priority
    }

    // Override of the toString() method to provide a readable task representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Task ID: ").append(id);
        sb.append("\nDescription: ").append(description);
        sb.append("\nPriority:  ").append(priority);

        if (isCompleted) { // Include completion details if the task is completed
            sb.append("\nStatus: Completed");
            if (completionDate != null) {
                sb.append("\nCompleted On: ").append(completionDate);
            }
        } else { // Indicate if the task is still pending
            sb.append("\nStatus: Pending...");
        }

        // Include category information if it is provided
        if (category != null && !category.trim().isEmpty()) {
            sb.append("\nCategory: ").append(category);
        }
        return sb.toString(); // Return the formatted string
    }

    // Override of the equals() method to define task equality based on ID, description, and priority
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false; // Return false if the other object is null
        }
        if (this == obj) {
            return true; // Return true if comparing the same instance
        }
        if (!(obj instanceof Task)) {
            return false; // Return false if the other object is not a Task
        }
        Task otherTask = (Task) obj; // Cast object to Task
        return this.id == otherTask.id &&
                Objects.equals(this.description, otherTask.description) &&
                Objects.equals(this.priority, otherTask.priority);
    }

    // Override of the hashCode() method to generate a hash based on task attributes
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;                  // Include ID in hash
        result = 31 * result + (description == null ? 0 : description.hashCode()); // Include description in hash
        result = 31 * result + (priority == null ? 0 : priority.hashCode());       // Include priority in hash
        return result; // Return the computed hash value
    }
}
