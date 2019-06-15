
public class DVD {
    private String name;
    private int dvdId;
    private String genre;
    private boolean available;
    private String actor;
    private String director;
    
    //default »ý¼ºÀÚ
    public DVD () {
      this.name = "null";
      this.genre = "null";
      this.actor = "null";
      this.director = "null";
      this.available = false;
    }
    
    public DVD (String name, String genre, String actor, String director) {
      this.name = name;
      this.genre = genre;
      this.actor = actor;
      this.director = director;
      this.available = true;
    }
    
    public String getName() {
      return this.name;
    }
    public void setName(String name) {
      this.name = name;
    }
    
    public String getGenre() {
      return this.genre;
    }
    public void setGenre(String genre) {
      this.genre = genre;
    }
    
    public String getActor() {
      return this.actor;
    }
    public void setActor(String actor) {
      this.actor = actor;
    }
    
    public String getDirector() {
      return this.director;
    }
    public void setDirector(String director) {
      this.director = director;
    }
    
    public boolean getAvail() {
      return this.available;
    }
    public void setAvail(boolean available) {
      this.available = available;
    }
    
    @Override
    public String toString() {
      String DVDInfo = "";
      DVDInfo = "Name : " + this.name + "\n" +
                "Genre : " + this.genre + "\n" +
                "Actor : " + this.actor + "\n" +
                "Director : " + this.director + "\n";
      return DVDInfo;
    }
    
    
    
}
