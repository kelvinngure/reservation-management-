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

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ReservationsMethods {
    
    private static Connection connection;
    private static PreparedStatement addReservation;
    //private static PreparedStatement deleteReservation;
    private static PreparedStatement getReservedRooms;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement deleteReservations;
    private static ResultSet resultSet;
    private static PreparedStatement deleteReservationByRoom;
    
    public static ArrayList<String> getRoomsReservedByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<>();
        try{
            getReservedRooms = connection.prepareStatement("select room from reservations where date = ?");
            getReservedRooms.setDate(1, date);
            resultSet = getReservedRooms.executeQuery();
            while(resultSet.next()){
                rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException){
        }
        return rooms;
    }
    
    public static ArrayList<Reservations> getReservationsByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<Reservations> reservations = new ArrayList<>();
        try{
            getReservationsByDate = connection.prepareStatement("select faculty, room, date, seats, timestamp from reservations where date = ?");
            getReservationsByDate.setDate(1, date);
            resultSet = getReservationsByDate.executeQuery();
            while(resultSet.next()){
                Reservations reservationEntry = new Reservations(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservations.add(reservationEntry);
            }
        }
        catch(SQLException sqlException){
        }
        return reservations;
    }
    
    public static ArrayList<Reservations> getReservationsByFaculty(String faculty){
        connection = DBConnection.getConnection();
        ArrayList<Reservations> reservations = new ArrayList<>();
        try{
            getReservationsByFaculty = connection.prepareStatement("select faculty, room, date, seats, timestamp from reservations where faculty = ?");
            getReservationsByFaculty.setString(1, faculty);
            resultSet = getReservationsByFaculty.executeQuery();
            while(resultSet.next()){
                Reservations reservationEntry = new Reservations(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservations.add(reservationEntry);
            }
        }
        catch(SQLException sqlException){
        }
        return reservations;
    }
    
    public static ArrayList<Reservations> getReservationsByRoom(String room){
        connection = DBConnection.getConnection();
        ArrayList<Reservations> reservations = new ArrayList<>();
        try{
            getReservationsByFaculty = connection.prepareStatement("select faculty, room, date, seats, timestamp from reservations where room = ?");
            getReservationsByFaculty.setString(1,room);
            resultSet = getReservationsByFaculty.executeQuery();
            while(resultSet.next()){
                Reservations reservationEntry = new Reservations(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservations.add(reservationEntry);
            }
        }
        catch(SQLException sqlException){
        }
        return reservations;
    }
    
    public static void addReservation(Reservations entry){
        connection = DBConnection.getConnection();
        try{
            addReservation = connection.prepareStatement("insert into reservations (faculty, room, date, seats, timestamp) values(?, ?, ?, ?, ?)");
            addReservation.setString(1,entry.getFaculty());
            addReservation.setString(2,entry.getRoom());
            addReservation.setDate(3,entry.getDate());
            addReservation.setInt(4,entry.getSeats());
            addReservation.setTimestamp(5,entry.getCurrentTimestamp());
            addReservation.executeUpdate();
        }
        catch(SQLException sqlException){
        }
    }
    
        public static void deleteReservation(String name, String date ){
        connection = DBConnection.getConnection();
        try{
            Date dr=Date.valueOf(date);
            deleteReservations = connection.prepareStatement("delete from reservations where faculty =? and date =?");
            deleteReservations.setString(1,name);
            deleteReservations.setDate(2,dr);
            deleteReservations.executeUpdate();
        }
        catch(SQLException sqlException){
        }
    }
     
    public static void deleteReservationByRoom(String room ){
        connection = DBConnection.getConnection();
        try{
            deleteReservationByRoom = connection.prepareStatement("delete from reservations where room =?");
            deleteReservationByRoom.setString(1,room);
            deleteReservationByRoom.executeUpdate();
        }
        catch(SQLException sqlException){
        }
    }

}