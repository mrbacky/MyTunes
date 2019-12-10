package mytunes.bll;


import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;
import mytunes.dal.DBManager;
import mytunes.dal.SongDAO;
import mytunes.dal.PlaylistDAO;
import mytunes.dal.DBFacade;
import mytunes.bll.util.TimeConverter;

public class LogicManager implements LogicFacade {
    private final DBFacade dbManager;
    private final TimeConverter timeConverter;
    public LogicManager() {
        dbManager = new DBManager();
        timeConverter = new TimeConverter();
        
    }

    public void Search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSongToPlaylist() {

    }

    @Override
    public void removeSongFromPlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void editSong() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSongFromLib() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newPlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editPlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Song> getAllSongs() {
        return dbManager.getAllSongs();
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return dbManager.getAllPlaylists();
        
    }

    @Override
    public void addSong(Song song) {
        dbManager.addSong(song);
        
    }

    @Override
    public List<SongOnPlaylist> getSongOnPlaylist() {
        return dbManager.getAllSongsOnPlaylist();
    }

    public String sec_To_Format(int sec) {
        return timeConverter.sec_To_Format(sec);
    }

    @Override
    public int format_To_Sec(String formatString) {
        return timeConverter.format_To_Sec(formatString);
    }

   
    

}
package mytunes.bll;


import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.util.SearchFilter;
import mytunes.dal.DBManager;
import mytunes.dal.DBFacade;

/**
 * This class connects the GUI and DAL.
 * @author annem
 */
public class LogicManager implements LogicFacade {
    private final DBFacade dbManager;
    private SearchFilter searcher;

    public LogicManager() {
        dbManager = new DBManager();
        searcher = new SearchFilter();
    }

    @Override
    public Song createSong(String title, String artist, String time, String path, String genre) {
        return dbManager.createSong(title, artist, time, path, genre);
    }
   
    @Override
    public Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre) {
        return dbManager.updateSong(song, editedTitle, editedArtist, editedGenre);
    }

    @Override
    public void deleteSong(Song song) {
        dbManager.deleteSong(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return dbManager.getAllSongs();
    }
    
    @Override
    public Playlist createPlaylist(String name) {
        return dbManager.createPlaylist(name);
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist, String editedName) {
        return dbManager.updatePlaylist(playlist, editedName);
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        dbManager.deletePlaylist(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return dbManager.getAllPlaylists();
    }

    @Override
    public Playlist addSongToPlaylist(Playlist playlist, Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSongFromPlaylist(Playlist playlist, Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Song> search(List<Song> searchBase, String query) {
        return searcher.search(searchBase, query);
    }

}
