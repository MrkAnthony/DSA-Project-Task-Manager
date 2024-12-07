import java.util.Scanner;


public class Main {

    // Static scanner instance for user input
    static Scanner scanner = new Scanner(System.in);

    // Static TaskManager instance for managing tasks
    static TaskManager manager = new TaskManager();

    // Main method where the application logic resides
    public static void main(String[] args) {

        // Infinite loop to continuously display the menu and handle user input
        while (true) {
            // Display the task manager menu to the user
            System.out.println("\n=== Task Manager Menu ===");
            System.out.println("1. Add Task - Create a new task with description and priority");
            System.out.println("2. Delete Task - Delete an existing task");
            System.out.println("3. Mark Complete - Mark a task as completed using its ID");
            System.out.println("4. Undo - Undo an existing task");
            System.out.println("5. Redo - Redo the previously undone action");
            System.out.println("6. Display Tasks - Show all current tasks and their IDs (View IDs for delete/complete operations)");
            System.out.println("7. Exit");

            try {
                // Read user input for menu selection
                int answer = scanner.nextInt();

                // Handle user input based on selected menu option
                switch (answer) {
                    case 1:
                        // Add Task: Prompt user for task details and add to the manager
                        scanner.nextLine(); // Consume newline character
                        System.out.println("Enter Description Of The Task: ");
                        String description = scanner.nextLine();
                        System.out.println("Enter Priority (High/Medium/Low): ");
                        String priority = scanner.nextLine();
                        manager.addTask(description, priority); // Add task to the manager
                        System.out.println("Task was successfully added");
                        break;

                    case 2:
                        // Delete Task: Prompt user for task ID and delete it
                        scanner.nextLine(); // Consume newline character
                        System.out.println("\nProvide Task ID");
                        int taskID = scanner.nextInt(); // Get task ID from user
                        scanner.nextLine(); // Consume newline character
                        manager.deleteTask(taskID); // Delete the task with given ID
                        System.out.println("\nTask Successfully Deleted");
                        break;

                    case 3:
                        // Mark Task Complete: Prompt user for task ID and mark it as completed
                        scanner.nextLine(); // Consume newline character
                        System.out.println("\nProvide Task ID Please: ");
                        int providedID = scanner.nextInt(); // Get task ID from user
                        scanner.nextLine(); // Consume newline character
                        manager.completeTask(providedID); // Mark task as completed
                        System.out.println("Task Successfully Completed");
                        break;

                    case 4:
                        // Undo Action: Revert the last performed action
                        System.out.println("\nUndoing Action");
                        manager.undoAction(); // Perform undo
                        System.out.println("Task Successfully Undone");
                        break;

                    case 5:
                        // Redo Action: Reapply the last undone action
                        System.out.println("\nRedoing Action");
                        manager.redoAction(); // Perform redo
                        System.out.println("Task Successfully Redone");
                        break;

                    case 6:
                        // Display Tasks: Show all tasks sorted by priority
                        System.out.println("\nTask List: ");
                        for (Task task : manager.getTaskByPriority()) { // Retrieve and iterate through tasks
                            System.out.println(task.toString()); // Print task details
                        }
                        System.out.println();
                        break;

                    case 7:
                        // Exit: Terminate the program
                        System.out.println("\nHave a nice day!");
                        System.exit(0); // Exit the application
                        break;

                    default:
                        // Handle invalid menu options
                        System.out.println("Invalid Option. Select 1-6");
                        break;
                }
            } catch (Exception e) {
                // Handle input errors or other exceptions
                System.out.println(e.getMessage());
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
