//import java.lang.ModuleLayer.Controller;
import java.util.Scanner;

public class UI {
  private Scanner scanner;
  
  public static void main(String[] args) {
 
    UI ui = new UI();    
    ui.showMenu();
  }
  
  public UI() {
    this.scanner = new Scanner(System.in);
  }
  
  public void displayMode() {
    System.out.println("");
    System.out.println("....................................");
    System.out.println("select your mode");
    System.out.println("....................................");
    System.out.println("1. User");
    System.out.println("2. Admin");
    System.out.println("0. Exit Program");
    System.out.println("....................................");
  }
   
  private int selectNumber() {
    System.out.print("Select number: ");
    int number = scanner.nextInt();

    return number;
  }
  
  
  public void showMenu() {
    int selectNumber = 0;
    Controller ctrl = new Controller();
    
    while(true) {
      displayMode();
      selectNumber = selectNumber();
      
      if(selectNumber == 1 || selectNumber == 2) {
        ctrl.choiceMenu(selectNumber);
        break;
      }
      else if(selectNumber == 0) {
        exitProgram();
        return;
      }
      else {
        System.out.println("Choose again!");
      }
    }
  }
  
  private void exitProgram() {
    System.out.println("Program exit. Bye...");
    scanner.close(); 
    System.exit(0);
  }
  
}
