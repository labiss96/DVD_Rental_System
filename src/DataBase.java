import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DataBase {

  List dvdList = new ArrayList();
  
  /*DataBase클래스는 Singleton 디자인패턴을 사용.*/
  private DataBase() {
    addDVD("avengers", 1, "로버트다우니주니어", "루소");
    addDVD("기생충", 2, "송강호", "봉준호");
    addDVD("애나벨", 0, "패트릭 월슨", "게리 다우버맨");
    addDVD("토이스토리4", 6, "톰 행크스/우디", "조시 쿨리");
    addDVD("존윅3", 5, "키아누 리브스", "채드 스타헬스키");
  }

  private static class SingletonHolder {
    public static final DataBase INSTANCE = new DataBase();
  }

  public static DataBase getDataBaseObject() {
    return SingletonHolder.INSTANCE;
  }

  public List getDVDList() {
    return dvdList;
  }

  public void addDVD(String name, int genre, String actor, String director) {
    int id = dvdList.size();
    DVD dvd = new DVD(name, genre, actor, director, id);
    // if dvd.name == exist?
    dvdList.add(dvd);
    //System.out.println(dvd.toString());
  }

  public void modifyDVD(String name, int genre, String actor, String director) {

    Iterator<DVD> iterator = dvdList.iterator();

    while (iterator.hasNext()) {
      DVD dvd = iterator.next();
      if (dvd.getName() == name) {
        dvd.setGenre(genre);    
        dvd.setActor(actor);
        dvd.setDirector(director);
        System.out.println(dvd.toString());
        break;
      }
      
    }
  }
  public void deleteDVD(String name) {
    Iterator<DVD> iterator = dvdList.iterator();
    int idx = 0;
    while (iterator.hasNext()) {
      DVD dvd = iterator.next();
      if (dvd.getName() == name) {
        dvdList.remove(idx);
        System.out.println("'"+name+"' 영화의 삭제요청이 정상적으로 처리되었습니다.");
        break;
      }
      idx++;
    }
  }
  @SuppressWarnings("unchecked")
  public List getDVDInfo(String name) {
    
    Iterator<DVD> iterator = dvdList.iterator();
    List infoList = new ArrayList();
    while (iterator.hasNext()) {
      DVD dvd = iterator.next();
      if (dvd.getName() == name) {
       infoList.add(dvd.getName());
       infoList.add(dvd.getGenre());
       infoList.add(dvd.getActor());
       infoList.add(dvd.getDirector());
       infoList.add(dvd.getAvail());
       infoList.add(dvd.getId());
       break;
      } else {
        //System.out.println("'"+name+"' DVD에 대한 정보가 존재하지 않습니다.");
      }
    }
    return infoList;
    
  }
  
  public void setRented(String name) {
    Iterator<DVD> iterator = dvdList.iterator();
    
    while (iterator.hasNext()) {
      DVD dvd = iterator.next();
      if (dvd.getName() == name) {
       dvd.setAvail(false);
       break;
      } 
    }
  }
  
  public void setReturned(String name) {
Iterator<DVD> iterator = dvdList.iterator();
    
    while (iterator.hasNext()) {
      DVD dvd = iterator.next();
      if (dvd.getName() == name) {
       dvd.setAvail(true);
       break;
      } 
    }
  }
  
  public boolean confirmAvail(String name) {
    Iterator<DVD> iterator = dvdList.iterator();
    boolean avail = false;
    while (iterator.hasNext()) {
      DVD dvd = iterator.next();
      if (dvd.getName() == name) {
       avail = dvd.getAvail();
       break;
      } 
    }
    return avail;
  }
  
  

}
