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

public class Reservations {
    
    private final String faculty;
    private final String room;
    private final Date date;
    private final int seats;
    private final Timestamp currentTimestamp;

    public Reservations(String faculty, String room, Date date, int seats, Timestamp timestamp){
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        currentTimestamp = timestamp;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getRoom() {
        return room;
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
