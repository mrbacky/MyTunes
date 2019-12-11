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
