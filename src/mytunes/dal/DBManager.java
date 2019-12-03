
package mytunes.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;

public class DBManager {
    
    SongDAO songDAO;
    PlaylistDAO playlistDAO;
    
    public DBManager() {
    songDAO = new SongDAO();
    playlistDAO = new PlaylistDAO();
    }
    public List<Song> getAllSongs() {
        try {
            return songDAO.fetchSongsDB();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 
    }
    
    public List<Playlist> getAllPlaylist(){
        try{
            return playlistDAO.fetchPlaylistDB();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}

