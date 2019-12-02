/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import static java.awt.PageAttributes.MediaType.C;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
/**
 *
 * @author Bruger
 */
public class PlaylistDAO {
    
    public Connection connectDB() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesPJDB");
        ds.setUser("CSe19B_12");
        ds.setPassword("CSe19B_12");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
        return ds.getConnection();

    }
    
    public List<Playlist> fetchPlaylistDB() throws SQLException {
        List<Playlist> playlist = new ArrayList<>();
        try (Connection con = connectDB()) {
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
