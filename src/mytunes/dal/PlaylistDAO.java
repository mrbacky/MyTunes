
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Playlist;
import mytunes.be.Song;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class PlaylistDAO {
    ConnectDAO connectDAO ;
    
    public PlaylistDAO( ){
        connectDAO = new ConnectDAO();
    }
    
        public Playlist createPlaylist(Playlist playlisttoCreate){
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "INSERT INTO playlist(name,time,nrOfSongs) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, playlisttoCreate.getName());
            pstmt.setInt(2, playlisttoCreate.getTime());
            pstmt.setInt(3, playlisttoCreate.getSongCounter());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return playlisttoCreate;
    }
    
        public List<Playlist> fetchAllSongsInPlaylists() throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        playlists = fetchAllPlaylists();
        try (Connection con = connectDAO.getConnection()) {
            String sql = "select songonplaylist.songid, song.id, song.title, song.artist, song.genre, song.time, song.songpath, songOnPlaylist.[order]\n" +
            "from songonplaylist left join song on songonplaylist.songid = song.id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int id      = rs.getInt("id");
                String name = rs.getString("name");
                int time    = rs.getInt("time");
               // int songs   = rs.getInt("nrOfSongs");
               //playlists.get(playlistID-1).addSongs(new Song(id, name, name, time, name, name));
            }
            } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return playlists;
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
        
        public List<Playlist> fetchAllPlaylists() {
        List<Playlist> allPlaylists = new ArrayList<>();
        
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "SELECT * FROM playlist ORDER BY id DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int songs   = rs.getInt("nrOfSongs");
                int time    = rs.getInt("time");
                allPlaylists.add(new Playlist(id, name, time));
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allPlaylists;
    }
    
}
