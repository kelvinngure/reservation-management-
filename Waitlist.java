package rs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kelvin Ngure
 */

import java.sql.Date;
import java.sql.Timestamp;

public class Waitlist {
    
    private final String faculty;
    private final Date date;
    private final int seats;
    private Timestamp currentTimestamp;
    
    public Waitlist(String faculty, Date date, int seats, Timestamp timestamp){
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        currentTimestamp = timestamp;
    }

    public String getFaculty() {
        return faculty;
    }

    public Date getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public Timestamp getCurrentTimestamp() {
        return currentTimestamp;
    }
}
