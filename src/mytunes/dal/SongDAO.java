/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import static java.awt.PageAttributes.MediaType.C;
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
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Song;

/**
 *
 * @author Bruger
 */
public class SongDAO {

    public void connectDB() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesPJDB");
        ds.setUser("CSe19B_12");
        ds.setPassword("CSe19B_12");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");

    }

    
    public List<Song> fetchSong() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Stayin Out All Night", "Wiz Khalifa", 29, "C:\\Users\\rados\\Disk Google\\songs\\Stayin Out All Night.mp3", "Hip-Hop"));
        songs.add(new Song("So High", "Wiz Khalifa", 83, "C:\\Users\\rados\\Disk Google\\songs\\So High.mp3", "Hip-Hop"));
        songs.add(new Song("True Colors", "Wiz Khalifa", 73, "C:\\Users\\rados\\Disk Google\\songs\\True Colors.mp3", "Hip-Hop"));
        return songs;
    }
    
    
    /**
     *
     * this method read the table of the song list
     */
    private void fetchSongsDB() throws SQLException {
        connectDB();
        SQLServerDataSource ds = new SQLServerDataSource();
        try (Connection con = ds.getConnection()) {
            String sql = "select * from Song";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                int time = rs.getInt("time");
                String genre = rs.getString("genre");
                String songpath = rs.getString("songpath");
                /**
                 * below is to show us what is on the table
                 */
                System.out.println(id + ", " + title + ", " + artist + ", " + time + ", " + genre + ", " + songpath);

            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * this add a song to the song table (where the time is an int!!)
     */
    private void songAdd() throws SQLException {
        connectDB();
        SQLServerDataSource ds = new SQLServerDataSource();
        try (Connection con = ds.getConnection()) {
            String sql = "insert into song(title, artist, time, genre, songpath) values (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            /**
             * we need to make the filds done by the gui
             */
            pstmt.setString(1, "hello");
            pstmt.setString(2, "djhello");
            pstmt.setInt(3, 4);
            pstmt.setString(4, "pop");
            pstmt.setString(5, "C:\\mydownload\\mysongs\\newsongs");
            pstmt.execute();
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }/*
    private void removeSong()
    {
        connectDB();
        SQLServerDataSource ds = new SQLServerDataSource();
        try(Connection con = ds.getConnection())
        {
            
        }
    }*/
}
