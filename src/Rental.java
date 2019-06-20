import java.util.List;

public class Rental {
  User user = new User();
  
  public Rental() {}
  
  public void rentDVD(String dvd) {
    user.addRentedDVD(dvd);
  }
  public void returnDVD(String returnDVD) {
    user.returnRentedDVD(returnDVD);
  }
  public boolean confirmLimit() {
    return user.confirmRentLimit();
  }
  
  public List getRentalList() {
    return user.getRentedList();
  }
  
}
