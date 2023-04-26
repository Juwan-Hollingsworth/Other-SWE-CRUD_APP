/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.*; //talk to the db use this pckge
/**
 *
 * @author biggestboss
 */
public class OracleConnection {
    
    //define a variable 
    private static Connection connection = null;
    
    //two method we need 
    
    public static Connection getConnection(){
        try{
            
            //s1 load jdbc driver class
            //in this instance we
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            //s2 create connection object
           //@params url (endpoint) -> login creds
           //does any of this need to change to interact w/ our db?
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@168.28.42.148:1521:csit", 
                    "jhollingsworth5", 
                    "jhollingsworth5");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return connection;
    }
    
    public static void closeConnection(){
             try{
                 //close connection
                 connection.close();
            
    
        }
        catch(Exception e){
            e.printStackTrace();
           
        } 
        
        
    }
    
    //build main fx to test
    public static void main(String [] args){
    try{
        //step 1. get oracle connection handle
        Connection conn = OracleConnection.getConnection();
        //step 2. build sql command & statement
        String sql="select sysdate from dual";
        //step 3. : execute
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        //step 4. process returned results from DB
        while(rs.next()){
            //pull data in result
            System.out.println("Current Oracle Sever time is:" + rs.getString(1));
            
        }
        
;        
    }catch(Exception e){
        e.printStackTrace();
    } finally {
        
     OracleConnection.closeConnection();
    }
    }
    
}
