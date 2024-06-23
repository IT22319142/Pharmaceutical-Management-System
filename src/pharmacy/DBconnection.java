package pharmacy;
// @author thath

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBconnection 
{
    public static Connection con;
        
    public static Connection createDBconnection()
    {
        try
        {
            try 
            {
                // Loading the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Creating the Database Connection
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            } 
            catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex);          
        }
        return con;
    }  
}

