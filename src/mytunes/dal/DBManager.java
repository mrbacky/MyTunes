
package mytunes.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.LogicManager;

public class DBManager implements DBFacade{

    private PlaylistDAO playlistDAO;
    private SongDAO songDAO;
    public DBManager() {
        playlistDAO = new PlaylistDAO();
        songDAO = new SongDAO();
        
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
            return songDAO.fetchSongsDB();
        } catch (SQLException ex) {
            Logger.getLogger(LogicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

   

}
