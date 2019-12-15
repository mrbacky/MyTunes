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
    private final GenreDAO genreDAO;

    public DBManager() {
        playlistDAO = new PlaylistDAO();
        songDAO = new SongDAO();
        SongOnPlaylistDAO = new SongOnPlaylistDAO();
        genreDAO = new GenreDAO();
    }

    //__________________________________________________________________________                       
    //
    //      Songs  
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
            //SongOnPlaylistDAO.deleteSongFromAllPlaylists(song);
            songDAO.deleteSong(song);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //__________________________________________________________________________                       
    //
    //      Playlists  
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
    //      Songs on Playlist
    //__________________________________________________________________________
    @Override
    public Playlist addSongToPlaylist(Playlist selectedPlaylist, Song selectedSong) {
        try {
            return SongOnPlaylistDAO.addSongToPlaylist(selectedPlaylist, selectedSong);
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
    public void deleteSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) {
        try {
            SongOnPlaylistDAO.deleteSongFromPlaylist(selectedPlaylist, selectedSong);
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
        genreDAO.createGenre(name);}

    @Override
    public List<String> getAllGenres() {
        return genreDAO.getAllGenres();
    }
    
    @Override
    public void deleteGenre(String name) {
        genreDAO.deleteGenre(name);}
}
