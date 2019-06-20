import java.util.List;

public class Rental {
  User user = new User();
  
  public Rental() {}
  
  public void rentDVD(String dvd) {
    user.addRentaledDVD(dvd);
  }
  public void returnDVD(String returnDVD) {
    user.returnRentaledDVD(returnDVD);
  }
  public boolean confirmLimit() {
    return user.confirmRentLimit();
  }
  
  public List getRentalList() {
    return user.getRentaledList();
  }
  
}
