/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Playlist;

/**
 * This DAO class can perform CRUD operations on the database playlist table.
 * @author annem
 */
public class PlaylistDAO {
    
    private ConnectDAO connectDAO;

    /**
     * Creates the database connector class.
     */
    public PlaylistDAO() {
        connectDAO = new ConnectDAO();
    }
    
    public Playlist createPlaylist(Playlist playlistToAdd){
        try (Connection con = connectDAO.getConnection()) {
            String sql = "INSERT INTO playlist(title) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, playlistToAdd.getName());
            pstmt.executeUpdate();
//            ResultSet rs = pstmt.getGeneratedKeys();
//            rs.next();
//            int id = rs.getInt(1);
            return playlistToAdd;
            
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Updates the name of the playlist in the database.
     * @param playlist The playlist to be updated.
     * @param editedName The edited name of the playlist.
     * @return The updated playlist.
     */
    public Playlist updatePlaylist(Playlist playlist, String editedName) {
        try (//Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "UPDATE playlist SET name = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, editedName);
            pstmt.setInt(2, playlist.getId());
            //Execute SQL query.
            pstmt.executeUpdate();
            playlist.setName(editedName);
            return playlist;

        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Gets a list of all the playlists from the database.
     * @return List with all the playlists.
     */
    public List<Playlist> fetchAllPlaylists() {
        List<Playlist> allPlaylists = new ArrayList<>();
        
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "SELECT * FROM playlist";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int songs   = rs.getInt("nrOfSongs");
                String time    = rs.getString("time");
                allPlaylists.add(new Playlist(id, name, songs, time));
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allPlaylists;
    }
    
    public void deletePlaylist(Playlist playlist) throws SQLException {
        //When the song is deleted, it should also be removed from all playlists.
        //PlaylistDAO.deleteSongFromAllPlaylists(songToDelete);
        try (Connection con = connectDAO.getConnection()) {
            String sql = "DELETE FROM playlist WHERE id = ?"; //deleted based on ID
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, playlist.getId());
            pstmt.execute();
        }
        
        catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
