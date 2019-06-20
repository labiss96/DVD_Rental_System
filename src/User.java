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
        System.out.println("���������� �뿩����Ʈ���� ���ŵ�");
        break;
      }
    }
    if(!isExist) {System.out.println("�ش� dvd�� �������� �ʽ��ϴ�.");}  
  }
  public boolean confirmRentLimit() {
    int currentNumber = rentedList.size();
    System.out.println("���� �뿩 ���� Ƚ�� : " + (3-currentNumber));
    if(currentNumber < 3) {
      return true;
    } else {
      return false;
    }
  }
  
  
}
