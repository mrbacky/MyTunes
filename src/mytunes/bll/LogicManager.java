package mytunes.bll;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;
import mytunes.bll.util.SearchFilter;
import mytunes.dal.DBManager;
import mytunes.dal.DBFacade;
import mytunes.bll.util.TimeConverter;

/**
 * This class manages the connection between GUI and DAL.
 *
 * @author annem
 */
public class LogicManager implements LogicFacade {

    private final DBFacade dbManager;
    private final TimeConverter timeConverter;
    private final SearchFilter searcher;

    /**
     * Creates a connection to the database.
     * Constructs a TimeConverter and SearchFilter.
     */
    public LogicManager() {
        dbManager = new DBManager();
        timeConverter = new TimeConverter();
        searcher = new SearchFilter();
    }

    //__________________________________________________________________________                       
    //      This is new master branch
    //      Song  
    //__________________________________________________________________________
    @Override
    public Song createSong(String title, String artist, int time, String path, String genre) {
        return dbManager.createSong(title, artist, time, path, genre);
    }

    @Override
    public List<Song> getAllSongs() {
        return dbManager.getAllSongs();
    }

    @Override
    public Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre, int editedTime, String editedPath) {
        return dbManager.updateSong(song, editedTitle, editedArtist, editedGenre, editedTime, editedPath);
    }

    @Override
    public void deleteSong(Song song) {
        dbManager.deleteSong(song);
    }

    //__________________________________________________________________________                       
    //
    //      Playlists  
    //__________________________________________________________________________
    @Override
    public void createPlaylist(Playlist playlist) {
        dbManager.createPlaylist(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return dbManager.getAllPlaylists();
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist, String editedName) {
        return dbManager.updatePlaylist(playlist, editedName);
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        dbManager.deletePlaylist(playlist);
    }

    //__________________________________________________________________________                       
    //
    //      Songs on Playlist
    //__________________________________________________________________________
    @Override
    public Playlist addSongToPlaylist(Playlist selectedPlaylist, Song selectedSong) {
        return dbManager.addSongToPlaylist(selectedPlaylist, selectedSong);
    }

    @Override
    public List<SongOnPlaylist> getSongOnPlaylist() {
        return dbManager.getAllSongsOnPlaylist();
    }

    @Override
    public void deleteSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) {
        dbManager.deleteSongFromPlaylist(selectedPlaylist, selectedSong);
    }

    //__________________________________________________________________________                       
    //
    //      Genres
    //__________________________________________________________________________
    @Override
    public List<String> getAllGenres() {
        return dbManager.getAllGenres();
    }

    @Override
    public void createGenre(String name) {
        dbManager.createGenre(name);
    }

    @Override
    public void deleteGenre(String name) {
        dbManager.deleteGenre(name);
    }

    //__________________________________________________________________________                       
    //
    //      Utilities
    //__________________________________________________________________________
    public String sec_To_Format(int sec) {
        return timeConverter.sec_To_Format(sec);
    }

    @Override
    public int format_To_Sec(String formatString) {
        return timeConverter.format_To_Sec(formatString);
    }

    @Override
    public List<Song> search(List<Song> searchBase, String query) {
        return searcher.search(searchBase, query);
    }
}
