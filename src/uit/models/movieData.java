package uit.models;

import java.sql.Date;
import java.time.LocalDate;

public class movieData {
    private int id;
    private String title;
    private String genre;
    private int duration;
    private String image;
    private int year;
    public movieData(int id, String title, String genre, int duration, String image, int year) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.image = image;
        this.year = year;
    }
    public int getId() {return id;}
    public String getTitle(){
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public int getDuration() {
        return duration;
    }
    public String getImage() {
        return image;
    }
    public int getYear() {return year;}
}
