import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DataBase {

  List dvdList = new ArrayList();
  
  /*DataBase클래스는 Singleton 디자인패턴을 사용.*/
  private DataBase() {}

  private static class SingletonHolder {
    public static final DataBase INSTANCE = new DataBase();
  }

  public static DataBase getDataBaseObject() {
    return SingletonHolder.INSTANCE;
  }

  public List getDVDList() {
    return dvdList;
  }

  public void addDVD(String name, String genre, String actor, String director) {
    DVD dvd = new DVD(name, genre, actor, director);
    // if dvd.name == exist?
    dvdList.add(dvd);
    System.out.println(dvd.toString());
  }

  public void modifyDVD(String name, String genre, String actor, String director) {

    Iterator<DVD> iterator = dvdList.iterator();

    while (iterator.hasNext()) {
      DVD dvd = iterator.next();
      if (dvd.getName() == "name") {
        dvd.setGenre(genre);
        dvd.setActor(actor);
        dvd.setDirector(director);
      }
      System.out.println(dvd.toString());

    }
  }

}
