package uit.models;

import java.sql.Date;
import java.sql.Time;

public class bookingData {
    private int id;
    private String customerID;
    private Date bookingDate;
    private String movie;
    private Date dateShow;
    private Time startTime;
    private int room;
    private int total;
    private String seats;
    public bookingData(int id, String customerID, Date bookingDate
            , String movie, Date dateShow, Time startTime, int room, int total, String seats ) {
        this.id = id;
        this.customerID = customerID;
        this.bookingDate = bookingDate;
        this.movie = movie;
        this.dateShow = dateShow;
        this.startTime = startTime;
        this.room = room;
        this.total = total;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public String getMovie() {
        return movie;
    }

    public Date getDateShow() {
        return dateShow;
    }

    public Time getStartTime() {
        return startTime;
    }

    public int getRoom() {
        return room;
    }

    public int getTotal() {
        return total;
    }

    public String getSeats() {
        return seats;
    }
}
