/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import mytunes.be.SongOnPlaylist;

/**
 *
 * @author Bruger
 */
public class SongOnPlaylistDAO {
   
    ConnectDAO connectDAO ;
    public SongOnPlaylistDAO( ){
        connectDAO = new ConnectDAO();
    }
    
       public List<SongOnPlaylist> fetchAllSongsOnPlaylist(){
        List<SongOnPlaylist> songsOnPlaylists = new ArrayList<>();
        try (Connection con = connectDAO.getConnection()) {
<<<<<<< Updated upstream:src/mytunes/dal/SongOnPlaylistDAO.java
            String sql = "select songonplaylist.songid, song.id, song.title, song.artist, song.genre, song.time, song.songpath\n" +
=======
            String sql = "select songOnplaylist.songid, song.id, song.title, song.artist, song.genre, song.time, song.songpath\n" +
>>>>>>> Stashed changes:src/mytunes/dal/SongOnPlaylistDAO.java
            "from songonplaylist left join song on songonplaylist.songid = song.id\n" +
            "where songonplaylist.playlistid = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int songID = rs.getInt("songid");
                String title = rs.getString("title");
                String songPath = rs.getString("songpath");
                int playlistID = rs.getInt("playlistid");
                int order = rs.getInt("order");
<<<<<<< Updated upstream:src/mytunes/dal/SongOnPlaylistDAO.java
                songsOnPlaylists.add(new SongOnPlaylist(order, playlistid, songid, title, songpath));
=======
                SongsOnPlaylists.add(new SongOnPlaylist(order, playlistID, songID, title, songPath));
>>>>>>> Stashed changes:src/mytunes/dal/SongOnPlaylistDAO.java
                
            }
            
        } catch (SQLServerException ex) {
            Logger.getLogger(SongOnPlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongOnPlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return songsOnPlaylists;
    }
}
