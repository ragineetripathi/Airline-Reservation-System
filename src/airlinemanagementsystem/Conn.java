
package airlinemanagementsystem;

import java.sql.*;

public class Conn {
   
    Connection c;
    Statement s;
    
    public Conn(){
        try {
            //register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create the connection string
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinemanagementsystem", "root", "root");
            s = c.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
    }    
}
