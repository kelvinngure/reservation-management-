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

public class WaitlistMethods {
    
    private static Connection connection;
    private static PreparedStatement addWaitlist;
    private static PreparedStatement getWaitlist;
    private static ResultSet resultSet;
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement deleteWaitlist;
    private static PreparedStatement getWaitlistByFaculty;
    
    public static ArrayList<Waitlist> getWaitlist(){
        connection = DBConnection.getConnection();
        ArrayList<Waitlist> waitlist = new ArrayList<>();
        try{
            getWaitlist = connection.prepareStatement("select faculty, date, seats, timestamp from waitlist order by timestamp");
            resultSet = getWaitlist.executeQuery();
            while(resultSet.next()){
                Waitlist waitlistEntry = new Waitlist(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlist.add(waitlistEntry);
            }
        }
        catch(SQLException sqlException){
        }
        return waitlist;
    }
    
    public static void addToWaitlist(Waitlist entry){
        connection = DBConnection.getConnection();
        try{
            addWaitlist = connection.prepareStatement("insert into waitlist (faculty, date, seats, timestamp) values(?, ?, ?, ?)");
            addWaitlist.setString(1,entry.getFaculty());
            addWaitlist.setDate(2,entry.getDate());
            addWaitlist.setInt(3,entry.getSeats());
            addWaitlist.setTimestamp(4,entry.getCurrentTimestamp());
            addWaitlist.executeUpdate();
        }
        catch(SQLException sqlException){
        }
    }
    
       public static ArrayList<Waitlist> getWaitlistByDate(Date date){
       connection = DBConnection.getConnection();
       ArrayList<Waitlist> waitlist = new ArrayList<Waitlist>();
       try{
           getWaitlistByDate = connection.prepareStatement("select faculty, room, date, timestamp from waitlist where date = ? order by date");
           getWaitlistByDate.setDate(1, date);
           ResultSet rs2 = getWaitlistByDate.executeQuery();
           while(rs2.next()){
               Waitlist waitlist2 = new Waitlist(rs2.getString(1), rs2.getDate(2), rs2.getInt(3), rs2.getTimestamp(4));
               waitlist.add(waitlist2);
           }
       }
       catch(SQLException sqlException){
           sqlException.printStackTrace();
       }
       return waitlist;
   }
       
   public static void deleteWaitlistEntry(String faculty, Date date){
       connection = DBConnection.getConnection();
       try{
           deleteWaitlist = connection.prepareStatement("delete from waitlist where faculty = ? and date = ?");
           deleteWaitlist.setString(1, faculty);
           deleteWaitlist.setDate(2, date);
           deleteWaitlist.executeUpdate();
       }
       catch(SQLException sqlException){
       }
   }
   
   
   
      public static ArrayList<Waitlist> getWaitlistByFaculty(String faculty){
       connection = DBConnection.getConnection();
       ArrayList<Waitlist> waitlist = new ArrayList<>();
       try{
           getWaitlistByFaculty = connection.prepareStatement("select faculty, date, seats, timestamp from waitlist where faculty = ? order by faculty");
           getWaitlistByFaculty.setString(1, faculty);
           resultSet = getWaitlistByFaculty.executeQuery();
           while(resultSet.next()){
               Waitlist waitlistEntry;
               waitlistEntry = new Waitlist(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
               waitlist.add(waitlistEntry);
           }
       }
       catch(SQLException sqlException){
           sqlException.printStackTrace();
       }
       return waitlist;
   }
      
      
    
    

}
