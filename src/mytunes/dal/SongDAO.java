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
 *
 * @author annem
 */
public class SongDAO {

    private ConnectDAO connectDAO;
    private SongOnPlaylistDAO spDAO;

    /**
     * Constructor, which creates the connection with the database and the DAO
     * for SongOnPlaylist.
     */
    public SongDAO() {
        connectDAO = new ConnectDAO();
        spDAO = new SongOnPlaylistDAO();
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
    public Song createSong(String title, String artist, int time, String path, String genre) {
        try (//Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "INSERT INTO song(title, artist, time, genre, songpath) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Set parameter values.
            pstmt.setString(1, title);
            pstmt.setString(2, artist);
            pstmt.setInt(3, time);
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
     * Gets all the songs from the database.
     *
     * @return A list with all the songs.
     * @throws SQLException
     */
    public List<Song> fetchAllSongs() throws SQLException {
        List<Song> allSongs = new ArrayList<>();

        try ( Connection con = connectDAO.getConnection()) {
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
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allSongs;
    }

    /**
     * Updates a song in the database after editing.
     *
     * @param songToEdit The song to be updated after editing.
     * @param editedTitle The edited title of the song.
     * @param editedArtist The edited artist of the song.
     * @param editedGenre The edited genre of the song.
     * @return The updated song.
     */
    public Song updateSong(Song songToEdit, String editedTitle, String editedArtist, String editedGenre, int editedTime, String editedPath) {
        try (//Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "UPDATE song SET title = ?, artist = ?, genre = ?, time = ?, songpath = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, editedTitle);
            pstmt.setString(2, editedArtist);
            pstmt.setString(3, editedGenre);
            pstmt.setInt(4, editedTime);
            pstmt.setString(5, editedPath);
            pstmt.setInt(6, songToEdit.getId());
            //Execute SQL query.
            pstmt.executeUpdate();
            songToEdit.setArtist(editedTitle);
            songToEdit.setArtist(editedArtist);
            songToEdit.setGenre(editedGenre);
            songToEdit.setPath(editedPath);
            return songToEdit;
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Deletes a song from the database. Also, uses object of SongOnPlaylistDAO
     * to delete the song from all playlist it may part of.
     *
     * @param songToDelete The song to be deleted.
     * @throws SQLException
     */
    public void deleteSong(Song songToDelete) throws SQLException {
        //When the song is deleted, it should also be removed from all playlists.
        spDAO.deleteSongFromAllPlaylists(songToDelete);

        try ( Connection con = connectDAO.getConnection()) {
            String sql = "DELETE FROM Song WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, songToDelete.getId());
            pstmt.execute();
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
