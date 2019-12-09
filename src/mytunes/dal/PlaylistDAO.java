/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Playlist;
/**
 *
 * @author Bruger
 */
public class PlaylistDAO {
    
    ConnectDAO connectDAO ;
    public PlaylistDAO( ){
        connectDAO = new ConnectDAO();
    }
    
    public List<Playlist> fetchPlaylistsDB() throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        try (Connection con = connectDAO.getConnection()) {
            String sql = "select * from playlist";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int id      = rs.getInt("id");
                String name = rs.getString("name");
                String time    = rs.getString("time");
                int songs   = rs.getInt("nrOfSongs");
                playlists.add(new Playlist(id, name, time, songs));
            }
            } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return playlists;
        }
    
}
