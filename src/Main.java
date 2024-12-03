import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
  List<Task> tasks = new ArrayList<Task>();
  Stack<Action> undostack = new Stack<>();

  while(true) {
      System.out.println("1. Add Task");
      System.out.println("2. Delete Task");
      System.out.println("3. Mark Task as Completed");
      System.out.println("4. Undo Last Action");
      System.out.println("5. Display All Tasks");
      System.out.println("6. Exit");

      System.out.println("Enter your choice: ");
    }
  public void addTask(int id, String priority) {
    Scanner input = new Scanner(System.in);

    System.out.println("Enter Task ID: ");
    int id = Scanner.nextInt();
    Scanner.nextline();


  }

}