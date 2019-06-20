
public class DVD {
    private String name;
    private static int id = 0;
    private String genre;
    private boolean available;
    private String actor;
    private String director;
    
    //default »ý¼ºÀÚ
    public DVD () {}
    
    public DVD (String name, int genre, String actor, String director) {
      this.id += 1;
      this.name = name;
      this.genre = MovieGenre.values()[genre].toString();
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
    public void setGenre(int genre) {
      this.genre = MovieGenre.values()[genre].toString();;
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
