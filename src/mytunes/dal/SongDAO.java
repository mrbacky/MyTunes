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

    ConnectDAO connectDAO;

    public SongDAO() {
        connectDAO = new ConnectDAO();
    }

    /**
     *
     * this method read the table of the song list
     *
     * @return
     * @throws java.sql.SQLException
     */
    public List<Song> fetchAllSongs() throws SQLException {
        List<Song> allSongs = new ArrayList<>();

        try (Connection con = connectDAO.getConnection()) {
            String sql = "SELECT * FROM song";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                int time = rs.getInt("time");
                String songpath = rs.getString("songpath");
                String genre = rs.getString("genre");
                allSongs.add(new Song(id, title, artist, time, songpath, genre));
                System.out.println("im heeeeeeeeeeeere");
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allSongs;
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
    public Song createSong(Song songToAdd) {
        try (//Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "INSERT INTO song(title, artist, time, genre, songpath) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1,  songToAdd.getTitle());
            pstmt.setString(2,  songToAdd.getArtist());
            pstmt.setInt(3,     songToAdd.getTime());
            pstmt.setString(4,  songToAdd.getGenre());
            pstmt.setString(5,  songToAdd.getPath());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
       
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songToAdd;
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
