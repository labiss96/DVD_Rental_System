import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DataBase {

  List dvdList = new ArrayList();
  
  /*DataBaseŬ������ Singleton ������������ ���.*/
  private DataBase() {
    addDVD("avengers", 1, "�ι�Ʈ�ٿ���ִϾ�", "���");
    addDVD("�����", 2, "�۰�ȣ", "����ȣ");
    addDVD("�ֳ���", 0, "��Ʈ�� ����", "�Ը� �ٿ����");
    addDVD("���̽��丮4", 6, "�� ��ũ��/���", "���� ��");
    addDVD("����3", 5, "Ű�ƴ� ���꽺", "ä�� ��Ÿ�ｺŰ");
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
        System.out.println("'"+name+"' ��ȭ�� ������û�� ���������� ó���Ǿ����ϴ�.");
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
        //System.out.println("'"+name+"' DVD�� ���� ������ �������� �ʽ��ϴ�.");
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
