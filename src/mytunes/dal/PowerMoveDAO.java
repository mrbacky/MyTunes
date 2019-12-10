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
import mytunes.be.PlaylistPowerMove;
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
public class PowerMoveDAO {
    ConnectDAO connectDAO ;
    
    public PowerMoveDAO( ){
        connectDAO = new ConnectDAO();
    }
    
        public List<PlaylistPowerMove> fetchPlaylistsDB() throws SQLException {
        List<PlaylistPowerMove> playlists = new ArrayList<>();
        try (Connection con = connectDAO.getConnection()) {
            String sql = "select * from playlist";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int id      = rs.getInt("id");
                String name = rs.getString("name");
                int time    = rs.getInt("time");
               // int songs   = rs.getInt("nrOfSongs");
               PlaylistPowerMove powermov =new PlaylistPowerMove(id, name, time);
               powermov.addSongs(new Song(2, "jesus 21", "jerome 2", 339 ,"C:\\Users\\PC\\Pictures\\beat2.mp3" , "hip hop"));
               powermov.addSongs(new Song(2, "jesus 2", "jerome", 330 ,"C:\\Users\\PC\\Pictures\\beat1.mp3" , "hip hop"));
               powermov.addSongs(new Song(1,"jesus christ is my nigga", "tyrone", 341, "C:\\Users\\PC\\Pictures\\deli.mp3", "hip hop"));
                playlists.add(powermov);
            }
            } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return playlists;
        }
    
}
