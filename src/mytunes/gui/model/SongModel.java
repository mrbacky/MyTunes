package mytunes.gui.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;

/**
 * The SongModel gets and passes data about the songs to the BLL.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Radoslav Backovsky
 * @author Anne Luong
 * @author Michael Haaning Pedersen
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
            //Save the converted time in the hh:mm:ss format before adding the song to an ObservableList.
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
        Song song = logicManager.createSong(title, artist, time, path, genre);
        libraryList.add(song);
    }

    /**
     * Updates a song. The method calls the BLL to update an edited song in the
     * database.
     *
     * @param song The song to be updated.
     * @param editedTitle The edited title of the song.
     * @param editedArtist The edited artist of the song.
     * @param editedGenre The edited genre of the song.
     */
    public void updateSong(Song song, String editedTitle, String editedArtist, String editedGenre, int editedTime, String editedPath) {
        logicManager.updateSong(song, editedTitle, editedArtist, editedGenre, editedTime, editedPath);
    }

    /**
     * Deletes a song from the library list. The method calls the BLL to delete
     * a song from the database. The deleted song is deleted from the library
     * list and all other playlists.
     *
     * @param song The song to be deleted.
     */
    public void deleteSong(Song song) {
        logicManager.deleteSong(song);
        libraryList.remove(song);
    }

    /**
     * Searches for all songs which matches the given query.
     *
     * @param query The search query
     */
    public void filteredSongs(String query) {
        //Create a temporary list which contains the songs obtained from the search method.
        List<Song> temp = logicManager.search(logicManager.getAllSongs(), query);
        //Clear all songs from the library and add the songs from the temporary list to the library list.
        libraryList.clear();
        libraryList.addAll(temp);
    }

    /**
     * Converts the time from the format hh:mm:ss to seconds.
     *
     * @param timeString The time in the format hh:mm:ss.
     * @return The time in seconds.
     */
    public int format_To_Sec(String timeString) {
        return logicManager.format_To_Sec(timeString);
    }

    /**
     * Converts the time from seconds to the format hh:mm:ss.
     *
     * @param sec The time in seconds.
     * @return The formatted time.
     */
    public String sec_To_Format(int sec) {
        return logicManager.sec_To_Format(sec);
    }
}
