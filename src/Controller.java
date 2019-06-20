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
  
  /* User,Admin 메뉴 선택 */
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
  
  /* User 메뉴 */
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
  
  /* Admin 메뉴 */
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
  
  /* DB에 현재 저장된 DVD List 출력 */
  private void showDVDList() {
    List list = dbHandler.getDVDList();
    int index = 1;
    Iterator<String> iterator = list.iterator();
    System.out.println();
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
  
  /*해당 DVD에 대한 정보를 보여줌*/
  private void showDVDInfo(boolean isUser_) {
    boolean isUser = isUser_;
    List list = dbHandler.getDVDList();
    List movieInfo = new ArrayList();
    String dvdName;
    
    int movieIndex = selectMovieNumber();
    movieIndex--;
    if(movieIndex == -1) {
      if(isUser) {
        showUserMenu();
      } else {
        showAdminMenu();
      }
      
    } else {
      movieInfo = dbHandler.getDVDInfo((String)list.get(movieIndex));
      dvdName = (String) movieInfo.get(0);
      System.out.println("....................................");
      System.out.println("["+movieInfo.get(0)+"]");
      System.out.println("장르 : "+movieInfo.get(1));
      System.out.println("배우 : "+movieInfo.get(2));
      System.out.println("감독 : "+movieInfo.get(3));
      System.out.println("....................................");
      System.out.println();
      
      /*user모드에서 접근하였나면 -> 이전메뉴*/
      if(isUser == true) {
        System.out.println("Previous menu? [Press Enter]");
        pause();
        showDVDList();
        showDVDInfo(isUser);
      } else { //admin모드에서 접근하였다면 -> DVD 수정, 삭제, 이전메뉴
        
        int selcNum;
        boolean correctNum = false;
        System.out.println("1. modify DVD");
        System.out.println("2. delete DVD");
        System.out.println("0. Previous menu");
        
        while(!correctNum) {
          selcNum = selectNumber();
          
          switch (selcNum) {
            case 1:
              //modify DVD
              updateDVD(dvdName);
              correctNum = true;
              showDVDList();
              showDVDInfo(false);
              break;
            case 2:
              //delete DVD
              deleteDVD(dvdName);
              correctNum = true;
              showDVDList();
              showDVDInfo(false);
              break;
            case 0:
              //previous menu
              showDVDList();
              showDVDInfo(isUser);
              correctNum = true;
              break;
            default:
              System.out.println("Choose again!");
              correctNum = false;
              
          }
        }
      }      
    }
  }
  
  /* 입력값 로직 */
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
  
  /* display */
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
    String name, actor, director;
    int genre;
    
    System.out.println("You've select the add DVD menu");
    System.out.print("Input DVD name : ");
    name = scanner.next();
    scanner.nextLine(); //enter를 읽어줌
    
    System.out.println("[0]Horror, [1]SF, [2]Drama, [3]Romance, [4]Comedy, [5]Action, [6]Cartoon");
    System.out.print("Input DVD genre number :");
    genre = scanner.nextInt();
    scanner.nextLine(); //enter를 읽어줌
    
    System.out.print("Input DVD actor : ");
    actor = scanner.next();
    
    System.out.print("Input DVD director : ");
    director = scanner.next();
    
    dbHandler.addDVD(name, genre, actor, director);
    System.out.println("'"+ name + "' DVD가 정상적으로 등록되었습니다!"); 
    System.out.println();
    
    System.out.println("Previous menu? [Press Enter]");
    pause();
    showAdminMenu();
  }
  
  private void updateDVD(String name) {
    
    String actor, director;
    int genre;
    
    System.out.println("You've select the update DVD menu");
    System.out.println();
    System.out.println("[0]Horror, [1]SF, [2]Drama, [3]Romance, [4]Comedy, [5]Action, [6]Cartoon");
    System.out.print("Input DVD genre number :");
    genre = scanner.nextInt();
    scanner.nextLine(); //enter를 읽어줌
    
    System.out.print("Input DVD actor : ");
    actor = scanner.next();
    
    System.out.print("Input DVD director : ");
    director = scanner.next();
    
    dbHandler.updateDVD(name, genre, actor, director);
  }
  
  private void deleteDVD(String name) {
    int confirm;
    System.out.println("정말 '"+ name +"' DVD 정보를 삭제하시겠습니까?");
    System.out.println("Yes[1] / No, previous menu[2]");
    
    confirm = scanner.nextInt();
    
    if(confirm == 1) {
      dbHandler.deleteDVD(name);
      showDVDList();
      showDVDInfo(false);
    } else {
      return;
    }
  }
  
  public static void pause() {
    try {
      System.in.read();
    } catch (IOException e) { }
  }
  
}
