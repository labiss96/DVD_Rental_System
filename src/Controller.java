import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Controller {
  private Scanner scanner;
  UI ui = new UI();
  DBHandler dbHandler = new DBHandler();
  Rental rental = new Rental();
  //boolean mode;
  
  //default ������
  public Controller() {
    this.scanner = new Scanner(System.in);
  }
  
  /* User,Admin �޴� ���� */
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
  
  /* User �޴� */
  private void showUserMenu() {
    int selectNum = 0;
    
      userDisplayMenu();
      selectNum = selectNumber();
    
      switch (selectNum) {
        case 1:
          showDVDList();
          showDVDInfo(true);
          break;
        case 2:
          //search dvd
          searchMenu();
          break;
        case 3:
          //rentaled list
          showRentedList();
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
  
  /* Admin �޴� */
  private void showAdminMenu() {
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
  
  /* DB�� ���� ����� DVD List ��� */
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
  
  /*�ش� DVD�� ���� ������ ������*/
  private void showDVDInfo(boolean isUser_) {
    boolean isUser = isUser_;
    List list = dbHandler.getDVDList();
    List movieInfo = new ArrayList();
    String dvdName;
    
    int movieIndex = selectMovieNumber();
    movieIndex--;
    if(movieIndex == -1) {
      if( isUser) {
        showUserMenu();
      } else {
        showAdminMenu();
      }
      
    } else {
      movieInfo = dbHandler.getDVDInfo((String)list.get(movieIndex));
      //System.out.println((String)list.get(movieIndex));
      dvdName = (String) movieInfo.get(0);
      toStringDVD(movieInfo);
//      System.out.println("....................................");
//      System.out.println("["+movieInfo.get(0)+"]");
//      System.out.println("�帣 : "+movieInfo.get(1));
//      System.out.println("��� : "+movieInfo.get(2));
//      System.out.println("���� : "+movieInfo.get(3));
//      System.out.println("�뿩���� ���� : "+movieInfo.get(4));
//      System.out.println("....................................");
      
      /*user��忡�� �����Ͽ����� -> �����޴�*/
      if(isUser == true) {
        System.out.println("1. Rent this DVD");
        System.out.println("0. Previous menu");
        System.out.println();
        
        int selcNum = selectNumber();
     
        if(selcNum == 1) {
          //rent dvd
          rentDVD(dvdName);
          showDVDList();
          showDVDInfo(isUser);
        } else if(selcNum == 0) {
          //previous menu
          showDVDList();
          showDVDInfo(isUser);
        } else {
          System.out.println("Choose again!");
        }
        
      } else { //admin��忡�� �����Ͽ��ٸ� -> DVD ����, ����, �����޴�
        
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
              modifyDVD(dvdName);
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
  
  /* �Է°� ���� */
  private int selectNumber() {
    System.out.print("Select number : ");
    
    while (!scanner.hasNextInt()) {
      scanner.next();
      System.out.println("�������� �Է����ּ���!");
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
      System.out.println("�������� �Է����ּ���!");
      System.out.println();
      System.out.print("Select number : ");
    }
    int number = scanner.nextInt();
    
    return number;
  }
  
  /* display */
  private void userDisplayMenu() {
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
  
  private void adminDisplayMenu() {
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

  
  
  private void searchMenu() {
    System.out.println("You've select the search DVD menu");
    System.out.println("....................................");
    System.out.println("1. Search dvd name");
    System.out.println("2. DVD category");
    System.out.println("0. Previous menu");
    System.out.println("....................................");
    System.out.println();

    int selcNum = selectNumber();
    
    switch(selcNum) {
      case 1:
        //name
        searchTitle();
        break;
      case 2:
        //genre
        break;
      case 0:
        //previous menu
        break;
        
      default:
        System.out.println("Choose again!");
    }
    
    scanner.nextLine();
  }
  
  private void searchTitle() {
    String dvdName = new String("");
    List movieInfo = new ArrayList();
    System.out.println();
    System.out.print("ã���÷��� DVD �̸��� �Է����ּ��� :");
    
    dvdName = scanner.next();
    String tm = "�ֳ���";
    System.out.println(tm == dvdName);
    System.out.println(tm.equals(dvdName));
    //dvdName = "�ֳ���";
    //System.out.println("'"+dvdName+"'");
    //movieInfo = dbHandler.getDVDInfo(dvdName);
    //movieInfo = dbHandler.getDVDInfo(dvdName);
    //toStringDVD(movieInfo);
//    System.out.println("....................................");
//    System.out.println("["+movieInfo.get(0)+"]");
//    System.out.println("�帣 : "+movieInfo.get(1));
//    System.out.println("��� : "+movieInfo.get(2));
//    System.out.println("���� : "+movieInfo.get(3));
//    System.out.println("....................................");
    System.out.println(movieInfo);
    
    
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
    scanner.nextLine(); //enter�� �о���
    
    System.out.println("[0]Horror, [1]SF, [2]Drama, [3]Romance, [4]Comedy, [5]Action, [6]Cartoon");
    System.out.print("Input DVD genre number :");
    genre = scanner.nextInt();
    scanner.nextLine(); //enter�� �о���
    
    System.out.print("Input DVD actor : ");
    actor = scanner.next();
    
    System.out.print("Input DVD director : ");
    director = scanner.next();
    
    dbHandler.addDVD(name, genre, actor, director);
    System.out.println("'"+ name + "' DVD�� ���������� ��ϵǾ����ϴ�!"); 
    System.out.println();
    
    System.out.println("Previous menu? [Press Enter]");
    pause();
    showAdminMenu();
  }
  
  private void modifyDVD(String title) {
    
    String actor, director;
    int genre;
    
    System.out.println("You've select the modify DVD menu");
    System.out.println();
    System.out.println("[0]Horror, [1]SF, [2]Drama, [3]Romance, [4]Comedy, [5]Action, [6]Cartoon");
    System.out.print("Input DVD genre number :");
    genre = scanner.nextInt();
    scanner.nextLine(); //enter�� �о���
    
    System.out.print("Input DVD actor : ");
    actor = scanner.next();
    
    System.out.print("Input DVD director : ");
    director = scanner.next();
    
    dbHandler.modifyDVD(title, genre, actor, director);
  }
  
  private void deleteDVD(String title) {
    int confirm;
    System.out.println("���� '"+ title +"' DVD ������ �����Ͻðڽ��ϱ�?");
    System.out.println("Yes[1] / No, previous menu[2]");
    
    confirm = scanner.nextInt();
    
    if(confirm == 1) {
      dbHandler.deleteDVD(title);
      showDVDList();
      showDVDInfo(false);
    } else {
      return;
    }
  }
  
  private void rentDVD(String dvdTitle) {
    
    if(dbHandler.confirmAvail(dvdTitle)) {
      if(rental.confirmLimit()) {
        rental.rentDVD(dvdTitle);
        dbHandler.setRented(dvdTitle);
        System.out.println("DVD�� ���������� �뿩�Ǿ����ϴ�.");
      } else {
        System.out.println("�ִ� �뿩���� Ƚ���� �ʰ��Ͽ����ϴ�.");
      }
    } else {
      System.out.println("���� �ش� DVD�� �뿩 ���̰ų�, �뿩�Ұ� ��ǰ�Դϴ�.");
    }
  }
  
  private void showRentedList() {
    List list = rental.getRentalList();
    Iterator<String> iterator = list.iterator();
    List movieInfo = new ArrayList();
    int idx = 1;
    String dvdName;
    System.out.println();
    System.out.println("....................................");
    System.out.println("<<Rented DVD List>>");
    while(iterator.hasNext()) {
      System.out.println(idx + ". " + iterator.next());
      idx++;
    }
    System.out.println("....................................");
    System.out.println("0. Previous menu");
    System.out.println();
    
    int selcNum = selectNumber();
    if(selcNum == 0) {
      showUserMenu();
    } else {
      movieInfo = dbHandler.getDVDInfo((String)list.get(selcNum-1));
      dvdName = (String) movieInfo.get(0);
      toStringDVD(movieInfo);
      
      System.out.println("1. Return DVD");
      System.out.println("0. Previous menu");
      
      int selcRentMenuNum = selectNumber();
      System.out.print("select number : ");
      if(selcRentMenuNum == 1) {
        returnDVD(dvdName);
        showRentedList();
      } else if(selcRentMenuNum == 0) {
        showRentedList();
      }
       
    }
  }
  private void returnDVD(String dvdTitle) {
    rental.returnDVD(dvdTitle);
    dbHandler.setReturned(dvdTitle);
  }
  
  private void toStringDVD(List movieInfo) {
    System.out.println("....................................");
    System.out.println("["+movieInfo.get(0)+"]");
    System.out.println("�帣 : "+movieInfo.get(1));
    System.out.println("��� : "+movieInfo.get(2));
    System.out.println("���� : "+movieInfo.get(3));
    System.out.println("�뿩���� ���� : "+movieInfo.get(4));
    System.out.println("....................................");
    System.out.println();
  }
  
  public static void pause() {
    try {
      System.in.read();
    } catch (IOException e) { }
  }
  
}
