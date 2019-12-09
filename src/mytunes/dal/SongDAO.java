
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Song;

/**
 * This DAO class can perform CRUD operations on the database song table. 
 * @author annem
 */
public class SongDAO {
        ConnectDAO connectDAO ;
    public SongDAO( ){
        connectDAO = new ConnectDAO();
    }
    
    /**
     *
     * this method read the table of the song list
     * @return
     */
    public List<Song> getAllSongs() {
        try (Connection con = connectDAO.getConnection()) {
            String sql = "SELECT * FROM song";
            Statement stmt = con.createStatement();
            List<Song> allSongs = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String time = rs.getString("time");
                String songpath = rs.getString("songpath");
                String genre = rs.getString("genre");           
                
                Song s = new Song(id, title, artist, time, songpath, genre);
                allSongs.add(s);
                }
                return allSongs;
                
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Creates and adds a new song to the database.
     *
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param time The time (duration) of the song.
     * @param path The path of the song.
     * @param genre The genre of the song.
     * @return The newly created song.
     */
    public Song createSong(String title, String artist, String time, String path, String genre) {
        try (//Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "INSERT INTO song(title, artist, time, genre, songpath) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, title);
            pstmt.setString(2, artist);
            pstmt.setString(3, time);
            pstmt.setString(4, genre);
            pstmt.setString(5, path);
            //Execute SQL query.
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return new Song(id, title, artist, time, path, genre);
            
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Updates a song in the database after editing.
     * @param song The song to be updated after editing.
     * @param editedTitle The edited title of the song.
     * @param editedArtist The edited artist of the song.
     * @param editedGenre The edited genre of the song.
 * @return The updated song.
     */
    public Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre) {
        try (//Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "UPDATE song SET title = ?, artist = ?, genre = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, editedTitle);
            pstmt.setString(2, editedArtist);
            pstmt.setString(3, editedGenre);
            pstmt.setInt(4, song.getId());
            //Execute SQL query.
            pstmt.executeUpdate();
            song.setArtist(editedTitle);
            song.setArtist(editedArtist);
            song.setGenre(editedGenre);
            return song;
            
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void deleteSong(Song songToDelete) throws SQLException {
        //When the song is deleted, it should also be removed from all playlists.
        //PlaylistDAO.deleteSongFromAllPlaylists(songToDelete);
        try (Connection con = connectDAO.getConnection()) {
            String sql = "DELETE FROM Song WHERE id = ?"; //deleted based on ID
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, songToDelete.getId()); //need to add
            pstmt.execute();
        }
        
        catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
}
