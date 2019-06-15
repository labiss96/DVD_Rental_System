import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Controller {
  private Scanner scanner;
  UI ui = new UI();
  DBHandler dbHandler = new DBHandler();
  //boolean mode;
  
  //default 생성자
  public Controller() {
    this.scanner = new Scanner(System.in);
  }
  
  public void choiceMenu(int selectMode) {
    switch(selectMode) {
      case 1:
        showUserMenu();
        break;
      case 2:
        showAdminMenu();
        break;
    }
  }
  
  public void showUserMenu() {
    int selectNum = 0;
    while(true) {
      userDisplayMenu();
      selectNum = selectNumber();
    
      switch (selectNum) {
        case 1:
          showDVDList();
          break;
        case 2:
          break;
        case 3:
          break;
          
        case 9:
          ui.showMenu();
          break;
        case 0:
          exitProgram();
          return;
          
        default:
          System.out.println("Choose again!");
      }
    } 
  }
  
  public void showAdminMenu() {
   int selectNum = 0;
   
   while(true) {
     adminDisplayMenu();
     selectNum = selectNumber();
   
     switch (selectNum) {
       case 1:
         //add dvd
         addDVD();
         break;
       case 2:
         //modify dvd
         break;
       case 3:
         //delete dvd
         break;
         
       case 9:
         ui.showMenu();
         break;
       case 0:
         exitProgram();
         return;
         
       default:
         System.out.println("Choose again!");
     }
   } 
  }
  
  
  private void showDVDList() {
    List list = dbHandler.getDVDList();
    int index = 1;
    Iterator<String> iterator = list.iterator();
    
    while(iterator.hasNext()) {
      String dvd = (String) iterator.next();
      System.out.println(index+". "+dvd);
      index ++;
    }
    
    scanner.nextLine();
  }
  
  private int selectNumber() {
    System.out.print("Select number: ");
    int number = scanner.nextInt();

    return number;
  }
  
  public void userDisplayMenu() {
    System.out.println("");
    System.out.println("....................................");
    System.out.println("select menu [User Mod]");
    System.out.println("....................................");
    System.out.println("1. DVD list");
    System.out.println("2. Search DVD");
    System.out.println("3. My rentaled list");
    System.out.println("9. Previous menu");
    System.out.println("0. Exit Program");
    System.out.println("....................................");
  }
  
  public void adminDisplayMenu() {
    System.out.println("");
    System.out.println("....................................");
    System.out.println("select menu [Admin Mod]");
    System.out.println("....................................");
    System.out.println("1. Add DVD");
    System.out.println("2. Modify DVD info");
    System.out.println("3. Delete DVD");
    System.out.println("9. Previous menu");
    System.out.println("0. Exit Program");
    System.out.println("....................................");
  }

  
  private void searchDVD() {
    System.out.print("You've select the search DVD menu [Enter to go back].");
    scanner.nextLine();
  }
  
  private void rentalList() {
    System.out.print("You've select the rental List menu [Enter to go back].");
    scanner.nextLine();
  }
  
  private void exitProgram() {
    System.out.println("Program exit. Bye...");
    scanner.close(); 
    System.exit(0);
  }
  private void addDVD() {
    String name, genre, actor, director;
    
    System.out.print("You've select the add DVD menu");
    System.out.print("Input DVD name, genre, actor, director : ");
    name = scanner.next();
    
    genre = scanner.next();
    
    actor = scanner.next();
    
    director = scanner.next();
    
    dbHandler.addDVD(name, genre, actor, director);
    System.out.print(name + "DVD가 정상적으로 등록되었습니다!");
    
    
  }
  
  
}
