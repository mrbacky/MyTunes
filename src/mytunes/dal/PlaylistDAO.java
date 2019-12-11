package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    ConnectDAO connectDAO;

    public PlaylistDAO() {
        connectDAO = new ConnectDAO();
    }

    public List<Playlist> fetchAllSongsInPlaylists() throws SQLException {
        //List<Playlist> playlists = new ArrayList<>();
        HashMap<Integer, Playlist> playlists = fetchAllPlaylists();
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "select songonplaylist.songid, song.id, song.title, song.time, song.songpath, songOnPlaylist.[order],songOnPlaylist.playlistid\n"
                    + "from songonplaylist left join song on songonplaylist.songid = song.id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int playlistid = rs.getInt("playlistid");
                int time = rs.getInt("time");
                String songPath = rs.getString("songPath");
                String title = rs.getString("title");
                int order = rs.getInt("order");

                playlists.get(playlistid).addSongs(new Song(id, title, "ert", time, songPath, "lol"));
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Playlist> unhashedPlaylists = new ArrayList<>();
        for (Map.Entry<Integer, Playlist> entry : playlists.entrySet()) {
            unhashedPlaylists.add(entry.getValue());

        }
        return unhashedPlaylists;
    }

    private HashMap<Integer, Playlist> fetchAllPlaylists() {
        //List<Playlist> allPlaylists = new ArrayList<>();
        HashMap<Integer, Playlist> allPlaylists = new HashMap<Integer, Playlist>();
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "SELECT * FROM playlist ORDER BY id ASC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int songs = rs.getInt("nrOfSongs");
                int time = rs.getInt("time");
                allPlaylists.put(id, new Playlist(id, name, time));
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allPlaylists;
    }

}
