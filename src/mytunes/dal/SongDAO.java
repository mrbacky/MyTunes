
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

public class SongDAO {

        ConnectDAO connectDAO ;
    public SongDAO( ){
        connectDAO = new ConnectDAO();
    }
    
    /**
     *
     * this method read the table of the song list
     */
    public List<Song> fetchSongsDB() throws SQLException {
        List<Song> songs = new ArrayList<>();
        try (Connection con = connectDAO.getConnection()) {
            String sql = "select * from Song";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                int time = rs.getInt("lengthInMS");
                String songpath = rs.getString("songpath");
                String genre = rs.getString("genre");           
                songs.add(new Song(id, title, artist, time, songpath, genre));
               

            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songs;
    }

    /**
     *
     * this add a song to the song table (where the time is an int!!)
     */
    private void songAdd(Song songToAdd) throws SQLException {
       
        
        try (Connection con = connectDAO.getConnection()) {
            String sql = "insert into song(title, artist, time, genre, songpath) values ("
                    + ",?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            /**
             * we need to make the filds done by the gui
             */
            //Song songToAdd = new Song(0, sql, sql, 0, sql, sql)
            pstmt.setString(1, songToAdd.getTitle());
            pstmt.setString(2, songToAdd.getArtist());
            pstmt.setInt(3, songToAdd.getTime());
            pstmt.setString(4, songToAdd.getGenre());
            pstmt.setString(5, songToAdd.getPath());
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
