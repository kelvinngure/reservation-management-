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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

public class Dates {
    
    private static Connection connection;
    private static PreparedStatement addDate;
    private static PreparedStatement getAllDates;
    private static ResultSet resultSet;
    
    public static ArrayList<Date> getAllDates(){
        connection = DBConnection.getConnection();
        ArrayList<Date> date = new ArrayList<>();
        try{
            getAllDates = connection.prepareStatement("select date from dates");
            resultSet = getAllDates.executeQuery();
            while(resultSet.next()){
                date.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException){
        }
        return date;
    }
    
    public static void addDate(String date)
    {
        connection = DBConnection.getConnection();

        try {
            Date dr=Date.valueOf(date);
            addDate = connection.prepareStatement("insert into DATES (DATE) values (?)");
            addDate.setDate(1, dr);
            addDate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Dates.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}