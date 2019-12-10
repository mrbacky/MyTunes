
package mytunes.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.PlaylistPowerMove;
import mytunes.be.Song;
import mytunes.bll.LogicManager;

public class DBManager implements DBFacade{

    private PlaylistDAO playlistDAO;
    private SongDAO songDAO;
    private PowerMoveDAO powr;
    public DBManager() {
        playlistDAO = new PlaylistDAO();
        songDAO = new SongDAO();
        powr = new PowerMoveDAO();
        
        }
    
    @Override
    public List<Playlist> getAllPlaylists() {
        try {
            return playlistDAO.fetchPlaylistsDB();
        } catch (SQLException ex) {
            Logger.getLogger(LogicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Song> getAllSongs() {
    try {
            return songDAO.fetchAllSongs();
        } catch (SQLException ex) {
            Logger.getLogger(LogicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void addSong(Song song) {
        try {
            songDAO.addSong(song);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<PlaylistPowerMove> getAllPowerPlaylists() {
        try {
            return   powr.fetchPlaylistsDB();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

   


