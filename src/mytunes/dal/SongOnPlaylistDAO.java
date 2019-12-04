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
   
    /*ConnectDAO connectDAO ;
    public SongOnPlaylistDAO( ){
        connectDAO = new ConnectDAO();
    }
    
       public List<SongOnPlaylist> songOnPlaylist(){
        List<SongOnPlaylist> SongsOnPlaylists = new ArrayList<>();
        try (Connection con = connectDAO.getConnection()) {
            String sql = "select songonplaylist.songid, song.id, song.title, song.artist, song.genre, song.lengthInMS, song.songpath\n" +
            "from songonplaylist left join song on songonplaylist.songid = song.id\n" +
            "where songonplaylist.playlistid = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int songid = rs.getInt("songid");
                String title = rs.getString("title");
                String songpath = rs.getString("songpath");
                int playlistid = rs.getInt("playlistid");
                int order = rs.getInt("order");
                SongsOnPlaylists.add(new SongOnPlaylist(order, playlistid, songid, title, songpath));
                
            }
            
        } catch (SQLServerException ex) {
            Logger.getLogger(SongOnPlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongOnPlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return SongsOnPlaylists;
    }*/
}
