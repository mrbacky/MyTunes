package mytunes.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;
import mytunes.bll.LogicManager;

public class DBManager implements DBFacade {

    private PlaylistDAO playlistDAO;
    private SongDAO songDAO;
    private SongOnPlaylistDAO SongOnPlaylistDAO;

    public DBManager() {
        playlistDAO = new PlaylistDAO();
        songDAO = new SongDAO();
        SongOnPlaylistDAO = new SongOnPlaylistDAO();
    }

    //__________________________________________________________________________                       
    //
    //      Song  
    //__________________________________________________________________________
    @Override
    public void createSong(Song song) {
        songDAO.createSong(song);
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
    public Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSong(Song song) {
        try {
            songDAO.deleteSong(song);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //__________________________________________________________________________                       
    //
    //      Playlist  
    //__________________________________________________________________________
    @Override
    public void createPlaylist(Playlist playlist) {
        playlistDAO.createPlaylist(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        try {
            return playlistDAO.fetchAllSongsInPlaylists();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist, String editedName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        try {
            playlistDAO.deletePlaylist(playlist);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//__________________________________________________________________________                       
    //
    //      Song on Playlist
    //__________________________________________________________________________
    @Override
    public List<SongOnPlaylist> getAllSongsOnPlaylist() {
        return SongOnPlaylistDAO.fetchAllSongsOnPlaylist();
    }

    @Override
    public Playlist addSongToPlaylist(Playlist playlist, Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSongFromPlaylist(Playlist playlist, Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
