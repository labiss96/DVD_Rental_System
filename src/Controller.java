import java.io.IOException;
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
    
      userDisplayMenu();
      selectNum = selectNumber();
    
      switch (selectNum) {
        case 1:
          showDVDList();
          showDVDInfo(true);
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
          showAdminMenu();
      }
  }
  
  public void showAdminMenu() {
   int selectNum = 0;
   
     adminDisplayMenu();
     selectNum = selectNumber();
   
     switch (selectNum) {
       case 1:
         //add_dvd
         addDVD();
         break;
       case 2:
         //update_dvd
         showDVDList();
         showDVDInfo(false);
         break;
         
       case 9:
         ui.showMenu();
         break;
       case 0:
         exitProgram();
         return;
         
       default:
         System.out.println("Choose again!");
         showAdminMenu();
     }
  }
  
  
  private void showDVDList() {
    List list = dbHandler.getDVDList();
    int index = 1;
    Iterator<String> iterator = list.iterator();
    
    System.out.println("....................................");
    System.out.println("<<DVD List>>");
    
    while(iterator.hasNext()) {
      String dvd = (String) iterator.next();
      System.out.println(index+". "+dvd);
      index ++;
    }
    
    System.out.println("....................................");
    
    System.out.println("0. Previous menu");
    System.out.println();
  }
  
  private void showDVDInfo(boolean isUser_) {
    boolean isUser = isUser_;
    List list = dbHandler.getDVDList();
    List movieInfo = new ArrayList();
    
    int movieIndex = selectMovieNumber();
    movieIndex--;
    if(movieIndex == -1) {
      showUserMenu();
    } else {
      movieInfo = dbHandler.getDVDInfo((String)list.get(movieIndex));
      System.out.println("....................................");
      System.out.println("["+movieInfo.get(0)+"]");
      System.out.println("장르 : "+movieInfo.get(1));
      System.out.println("배우 : "+movieInfo.get(2));
      System.out.println("감독 : "+movieInfo.get(3));
      System.out.println("....................................");
      System.out.println();
      
      if(isUser == true) {
        System.out.println("Previous menu? [Press Enter]");
        pause();
        showDVDList();
        showDVDInfo(isUser);
      } else { //Admin_Mod
        String choice = "";
        System.out.println(" modify DVD [press M]");
        System.out.println(" delete DVD [press D]");
        choice = scanner.next();
        
        if(choice == "M" || choice == "m") {
          //Modify DVD
        }
        else if(choice == "D" || choice == "d") {
          //Delete DVD
        }
        
        
      }
      
      
    }
  }
  
  private int selectNumber() {
    System.out.print("Select number : ");
    
    while (!scanner.hasNextInt()) {
      scanner.next();
      System.out.println("숫자형만 입력해주세요!");
      System.out.println();
      System.out.print("Select number : ");
    }
    int number = scanner.nextInt();

    return number;
  }
  private int selectMovieNumber() {
    System.out.println("Select Movie(index) : ");
    
    while (!scanner.hasNextInt()) {
      scanner.next();
      System.out.println("숫자형만 입력해주세요!");
      System.out.println();
      System.out.print("Select number : ");
    }
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
    System.out.println("2. Update DVD");
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
  
  private void updateDVD() {
    
    String name, genre, actor, director;
    
    System.out.print("You've select the update DVD menu");
    System.out.print("Input DVD name, genre, actor, director : ");
    name = scanner.next();
    
    genre = scanner.next();
    
    actor = scanner.next();
    
    director = scanner.next();
    
    dbHandler.updateDVD(name, genre, actor, director);
  }
  
  private void deleteDVD() {
    String name;
    System.out.print("You've select the delete DVD menu");
    System.out.print("Input DVD name : ");
  
    name = scanner.nextLine();
    dbHandler.deleteDVD(name);
  }
  
  public static void pause() {
    try {
      System.in.read();
    } catch (IOException e) { }
  }
  
}
