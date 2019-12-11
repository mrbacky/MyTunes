package mytunes.gui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import mytunes.be.Song;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;
import mytunes.gui.controller.AddSongSceneController;

/**
 * Description to be added...
 *
 * @author annem
 */
public final class SongModel {

    private ObservableList<Song> libraryList;
    private LogicFacade logicManager;

    /**
     * Establish a connection to the BLL.
     */
    public SongModel() {
        
        logicManager = new LogicManager();
        getLibraryList();
    }

    /**
     * Returns a list with all the songs (library).
     *
     * @return The list of all songs.
     */
    public ObservableList<Song> getLibraryList() {
        List<Song> allSongs = logicManager.getAllSongs();
        for (Song song1 : allSongs) {
            song1.setStringTime(sec_To_Format(song1.getTime()));
        }
        libraryList = FXCollections.observableArrayList(allSongs);
        return libraryList;
    }

    /**
     * Creates and adds a new song. The method calls the BLL to create a song in
     * the database. The created song is added to the library list (the library
     * consists of all the songs).
     *
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param time The time (duration) of the song.
     * @param path The path of the song.
     * @param genre The genre of the song.
     */
    public void createSong(String title, String artist, int time, String genre, String path) {
        Song song = new Song(0, title, artist, time, path, genre);
        logicManager.createSong(song);
    }

    /**
     * Updates a song. NOT COMPLETED!!
     *
     * @param song
     * @param editedTitle
     * @param editedArtist
     * @param editedGenre
     */
    public void updateSong(Song song, String editedTitle, String editedArtist, String editedGenre) {
        Song updatedSong = logicManager.updateSong(song, editedTitle, editedArtist, editedGenre);
        //updateLibraryList(updatedSong);
    }

    /**
     * Deletes a song from the library list. The method calls the BLL to delete
     * a song from the database. The deleted song is deleted from the library
     * list !!!and all other playlists.
     *
     * @param songToDelete The song to be deleted.
     */
    public void deleteSong(Song songToDelete) {
        logicManager.deleteSong(songToDelete);
        libraryList.remove(songToDelete);
    }

    public void filteredSongs(String query) {
        //Create a temporary list which contains the songs obtained from the search method.
        List<Song> temp = logicManager.search(logicManager.getAllSongs(), query);
        //Clear all songs from the library and the songs from the temporary list to the library list.
        libraryList.clear();
        libraryList.addAll(temp);
    }

    public int format_To_Sec(String timeString) {
        return logicManager.format_To_Sec(timeString);
    }

    public String sec_To_Format(int sec) {
        return logicManager.sec_To_Format(sec);
    }

}
