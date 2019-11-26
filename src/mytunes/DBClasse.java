/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import static javax.swing.text.html.HTML.Tag.SELECT;

/**
 *
 * @author Bruger
 */
public class DBClasse {

    public void connectDB(){
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesPJDB");
        ds.setUser("CSe19B_12");
        ds.setPassword("CSe19B_12");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
        
    }
    private void readDB() throws SQLException
    {
        connectDB();
        SQLServerDataSource ds = new SQLServerDataSource();
       try(Connection con = ds.getConnection()){
        String sql = "select * from Song";
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                int id =        rs.getInt("id");
                String title =   rs.getString("title");
                String artist =  rs.getString("artist");
                int time =       rs.getInt("time");
                String genre =  rs.getString("genre");
                String songpath = rs.getString("songpath");
                System.out.println(id + ", "+ title + ", " + artist + ", " + time + ", " + genre + ", " + songpath);
            }
        }    
         catch (SQLServerException ex) {    
            Logger.getLogger(DBClasse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBClasse.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}