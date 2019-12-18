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
 * @author annem
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
     * @param selectedPlaylist The playlist of the song is added to.
     * @param selectedSong The song to be added to the playlist.
     * @return Updated playlist with the newly added song.
     * @throws SQLException
     */
    public Playlist addSongToPlaylist(Playlist selectedPlaylist, Song selectedSong) throws SQLException {
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "INSERT INTO songOnPlaylist(playlistid, songid, [order]) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylist.getId());
            pstmt.setInt(2, selectedSong.getId());
            pstmt.setInt(3, selectedPlaylist.getId());
            pstmt.executeUpdate();
            selectedPlaylist.addSong(selectedSong);
            return selectedPlaylist;
        }
    }
    /**
     * this fetchAllSOngsOnPlaylist gets all the values from the table songonplaylist with an left join sql statment
     * and add the values to an ArrayList.
     * @param songid
     * @param title
     * @param songpath
     * @param playlistid
     * @param order
     * @throws SQLSeverException
     * @return songsOnPlaylists
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
     * @param selectedPlaylist
     * @param selectedSong
     * @throws SQLException
     */
    public void deleteSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) throws SQLException {
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "DELETE FROM songOnPlaylist WHERE playlistid = ? and songid = ? and [order] = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylist.getId());
            pstmt.setInt(2, selectedSong.getId());
            pstmt.setInt(3, selectedPlaylist.getId());
            pstmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deletes all the songs on the selected playlist in the database.
     *
     * @param selectedPlaylist The playlist to empty.
     * @throws SQLException
     */
/*  public void deleteAllSongsOnPlaylist(Playlist selectedPlaylist) throws SQLException {
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "DELETE FROM songOnPlaylist WHERE playlistid = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylist.getId());
            pstmt.executeUpdate();
        }
    }
*/
    /**
     * Deletes a selected song from all playlists.
     *
     * @param selectedSong The song to be deleted.
     * @throws SQLException
     */
    public void deleteSongFromAllPlaylists(Song selectedSong) throws SQLException {
        try ( Connection con = connectDAO.getConnection()) {
            String sql = "DELETE FROM songOnPlaylist WHERE songid = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedSong.getId());
            pstmt.executeUpdate();
        }
    }
}
