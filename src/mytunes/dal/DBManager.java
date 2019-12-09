
package mytunes.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;
import mytunes.bll.LogicManager;

public class DBManager implements DBFacade{

    private PlaylistDAO playlistDAO;
    private SongDAO songDAO;
    private SongsOnPlaylistDAO SongOnPlaylistDAO;
    public DBManager() {
        playlistDAO = new PlaylistDAO();
        songDAO = new SongDAO();
        SongOnPlaylistDAO = new SongsOnPlaylistDAO();
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
    public List<SongOnPlaylist> getAllSongsOnPlaylist() {
        return SongOnPlaylistDAO.songOnPlaylist();
    }

    @Override
    public void addSong(Song song) {
        try {
            songDAO.addSong(song);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}

   


