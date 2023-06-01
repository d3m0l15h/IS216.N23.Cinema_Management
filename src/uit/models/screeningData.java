package uit.models;

import java.sql.Date;
import java.sql.Time;
// import java.time.LocalDateTime;
import java.time.LocalTime;

public class screeningData {
    private int id;
    private int movieID;
    private String movieTitle;
    private Date dateShow;
    private Time startTime;
    private int roomID;
    private String image;
    private String status;

    public screeningData(int id, int movieID, String title, Date date, Time time, int roomID, String image) {
        this.id = id;
        this.movieID = movieID;
        this.movieTitle = title;
        this.dateShow = date;
        this.startTime = time;
        this.roomID = roomID;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getMovieID() {
        return movieID;
    }

    public Date getDateShow() {
        return dateShow;
    }

    public Time getStartTime() {
        return startTime;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getImage() {
        return image;
    }

    public void setStatus() {
        if (LocalTime.now().isAfter(startTime.toLocalTime())) {
            this.status = "Finished";
        } else {
            this.status = "Available";
        }
    }
    public String getStatus() {
        return status;
    }
}
