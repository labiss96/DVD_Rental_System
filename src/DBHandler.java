import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class DBHandler {
  DataBase DB = DataBase.getDataBaseObject();
  
  public DBHandler() {}

  public List getDVDList() {

    List dvdDB = DB.getDVDList();
    List dvdList = new ArrayList();
    
    Iterator<DVD> iterator = dvdDB.iterator();

    while (iterator.hasNext()) {
      String dvdName = iterator.next().getName();
      dvdList.add(dvdName);
    }

    return dvdList;
  }

  public void addDVD(String name, String genre, String actor, String director) {
    DB.addDVD(name, genre, actor, director);
  }
  public void updateDVD(String name, String genre, String actor, String director) {
    DB.updateDVD(name, genre, actor, director);
  }
  public void deleteDVD(String name) {
    DB.deleteDVD(name);
  }
  public List getDVDInfo(String name) {
    return DB.getDVDInfo(name);
  }
}
