package mytunes.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;
import mytunes.bll.LogicManager;

/**
 * This class manages all operations on the database.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Radoslav Backovsky
 * @author Anne Luong
 * @author Michael Haaning Pedersen
 */
public class DBManager implements DBFacade {

    private PlaylistDAO playlistDAO;
    private SongDAO songDAO;
    private SongOnPlaylistDAO SongOnPlaylistDAO;
    private final GenreDAO genreDAO;

    /**
     * Constructs data access objects.
     */
    public DBManager() {
        playlistDAO = new PlaylistDAO();
        songDAO = new SongDAO();
        SongOnPlaylistDAO = new SongOnPlaylistDAO();
        genreDAO = new GenreDAO();
    }

    //__________________________________________________________________________                       
    //
    //      Song  
    //__________________________________________________________________________
    @Override
    public Song createSong(String title, String artist, int time, String path, String genre) {
        return songDAO.createSong(title, artist, time, path, genre);
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
    public Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre, int editedTime, String editedPath) {
        return songDAO.updateSong(song, editedTitle, editedArtist, editedGenre, editedTime, editedPath);
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

    //__________________________________________________________________________                       
    //
    //      Song on Playlist
    //__________________________________________________________________________
    @Override
    public Playlist addSongToPlaylist(Playlist playlist, Song song) {
        try {
            return SongOnPlaylistDAO.addSongToPlaylist(playlist, song);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<SongOnPlaylist> getAllSongsOnPlaylist() {
        return SongOnPlaylistDAO.fetchAllSongsOnPlaylist();
    }

    @Override
    public void deleteSongFromPlaylist(Playlist playlist, Song song) {
        try {
            SongOnPlaylistDAO.deleteSongFromPlaylist(playlist, song);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //__________________________________________________________________________                       
    //
    //      Genre
    //__________________________________________________________________________
    @Override
    public void createGenre(String name) {
        genreDAO.createGenre(name);
    }

    @Override
    public List<String> getAllGenres() {
        return genreDAO.getAllGenres();
    }

    @Override
    public void deleteGenre(String name) {
        genreDAO.deleteGenre(name);
    }
}
