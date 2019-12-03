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
    
    ConnectDAO connectDAO;
    
    public PlaylistDAO(){
        connectDAO = new ConnectDAO();

    }
    
    public List<Playlist> fetchPlaylistDB() throws SQLException {
        List<Playlist> playlist = new ArrayList<>();
        try (Connection con = connectDAO.connectDB()) {
            String sql = "select * from playlist";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int id      = rs.getInt("id");
                String name = rs.getString("name");
                int time    = rs.getInt("lengthInMs");
                int songs   = rs.getInt("nrOfSongs");
                playlist.add(new Playlist(id, name, time, songs));
            }
            } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return playlist;
        }
    
}
