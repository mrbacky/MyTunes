package mytunes.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.LogicManager;

/**
 * This class manages all operations for the database.
 * @author annem
 */
public class DBManager implements DBFacade{

    private SongDAO songDAO;
    private PlaylistDAO playlistDAO;
    //private SongOnPlaylistDAO songonplaylistDAO;

    /**
     * Creates the data access objects.
     */
    public DBManager() {
        songDAO = new SongDAO();
        playlistDAO = new PlaylistDAO();
        //songonplaylistDAO = new SongOnPlaylistDAO();
    }

    @Override
    public Song createSong(String title, String artist, String time, String path, String genre) {
        return songDAO.createSong(title, artist, time, path, genre);
    }

    @Override
    public Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre) {
        return songDAO.updateSong(song, editedTitle, editedArtist, editedGenre);
    }

    @Override
    public void deleteSong(Song song) {
        try {
            songDAO.deleteSong(song);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }
    
    @Override
    public Playlist createPlaylist(String name) {
        return playlistDAO.createPlaylist(name);
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist, String editedName) {
        return playlistDAO.updatePlaylist(playlist, editedName);
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        try {
            playlistDAO.deletePlaylist(playlist);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
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

   


