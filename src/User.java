import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {
  int userID;
  String userName;
  String phoneNum;
  String eMail;
  List <String>rentedList = new ArrayList(); 
  
  public User() {}
  
  public List getRentedList() {
    return rentedList;
  }
  public void addRentedDVD(String dvd) {
    rentedList.add(dvd);
  }
  public void returnRentedDVD(String returnDVD) {
    boolean isExist = false;
    Iterator<String> iterator = rentedList.iterator();
    while(iterator.hasNext()) {
      String dvdName = iterator.next();
      if(dvdName == returnDVD) {
        iterator.remove();
        isExist = true;
        System.out.println("정상적으로 대여리스트에서 제거됨");
        break;
      }
    }
    if(!isExist) {System.out.println("해당 dvd는 존재하지 않습니다.");}  
  }
  public boolean confirmRentLimit() {
    int currentNumber = rentedList.size();
    System.out.println("현재 대여 가능 횟수 : " + (3-currentNumber));
    if(currentNumber < 3) {
      return true;
    } else {
      return false;
    }
  }
  
  
}
