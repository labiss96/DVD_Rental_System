import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DataBase {

  List dvdList = new ArrayList();
  
  /*DataBaseŬ������ Singleton ������������ ���.*/
  private DataBase() {
    addDVD("avengers", "SF/��Ÿ��", "�ι�Ʈ�ٿ���ִϾ�", "���");
    addDVD("�����", "���", "�۰�ȣ", "����ȣ");
    addDVD("�ֳ���", "ȣ��", "��Ʈ�� ����", "�Ը� �ٿ����");
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

  public void addDVD(String name, String genre, String actor, String director) {
    DVD dvd = new DVD(name, genre, actor, director);
    // if dvd.name == exist?
    dvdList.add(dvd);
    System.out.println(dvd.toString());
  }

  public void updateDVD(String name, String genre, String actor, String director) {

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
  public void deleteDVD(String name) {
    Iterator<DVD> iterator = dvdList.iterator();
    int idx = 0;
    while (iterator.hasNext()) {
      DVD dvd = iterator.next();
      if (dvd.getName() == "name") {
        dvdList.remove(idx);
        System.out.println("'"+name + "' ��ȭ�� ������û�� ���������� ó���Ǿ����ϴ�.");
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
       break;
      }
  }
    return infoList;
    
  }
   

}
