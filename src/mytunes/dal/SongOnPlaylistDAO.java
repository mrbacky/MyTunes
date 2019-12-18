package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;

/**
 * This DAO class can perform CRUD operations on the database SongOnPlaylist
 * table.
 *
 * @author Radoslav Backovsky
 * @author Anne Luong
 * @author Michael Haaning Pedersen
 */
public class SongOnPlaylistDAO {

    private ConnectDAO connectDAO;

    /**
     * Constructor, which creates the connection with the database.
     */
    public SongOnPlaylistDAO() {
        connectDAO = new ConnectDAO();
    }

    /**
     * Adds a song to the playlist in the database.
     *
     * @param playlist The playlist the song is added to.
     * @param song The song to be added to the playlist.
     * @return Updated playlist with the newly added song.
     */
    public Playlist addSongToPlaylist(Playlist playlist, Song song) throws SQLException {
        try ( //Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "INSERT INTO songOnPlaylist(playlistid, songid, [order]) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setInt(1, playlist.getId());
            pstmt.setInt(2, song.getId());
            pstmt.setInt(3, playlist.getId());
            //Execute SQL query.
            pstmt.executeUpdate();
            //Add the song to the playlist.
            playlist.addSong(song);
            return playlist;
        }
    }

    /**
     * Gets all songs on a playlist.
     * Gets the values from the table songOnPlaylist with a LEFT JOIN SQL statement
     * and adds the values to an ArrayList.
     * @return songsOnPlaylists A list with songs.
     */
    public List<SongOnPlaylist> fetchAllSongsOnPlaylist() {
        List<SongOnPlaylist> songsOnPlaylists = new ArrayList<>();
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "select songonplaylist.songid, song.id, song.title, song.artist, song.genre, song.time, song.songpath\n"
                    + "from songonplaylist left join song on songonplaylist.songid = song.id\n"
                    + "where songonplaylist.playlistid = 1"; //Why = 1?
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int songid = rs.getInt("songid");
                String title = rs.getString("title");
                String songpath = rs.getString("songpath");
                int playlistid = rs.getInt("playlistid");
                int order = rs.getInt("order");
                songsOnPlaylists.add(new SongOnPlaylist(order, playlistid, songid, title, songpath));
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongOnPlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongOnPlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songsOnPlaylists;
    }

    /**
     * Deletes a song from a selected playlist in the database.
     *
     * @param playlist The playlist the song is deleted from.
     * @param song The song to be deleted.
     * @throws SQLException
     */
    public void deleteSongFromPlaylist(Playlist playlist, Song song) throws SQLException {
        try ( //Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "DELETE FROM songOnPlaylist WHERE playlistid = ? and songid = ? and [order] = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setInt(1, playlist.getId());
            pstmt.setInt(2, song.getId());
            pstmt.setInt(3, playlist.getId());
            //Execute SQL query.
            pstmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deletes a selected song from all playlists.
     *
     * @param song The song to be deleted.
     * @throws SQLException
     */
    public void deleteSongFromAllPlaylists(Song song) throws SQLException {
        try ( //Get a connection to the database.
            Connection con = connectDAO.getConnection()) {
            //Create a prepared statement.
            String sql = "DELETE FROM songOnPlaylist WHERE songid = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setInt(1, song.getId());
            //Execute SQL query.
            pstmt.executeUpdate();
        }
    }
}
