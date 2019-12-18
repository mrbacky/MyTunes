package mytunes.gui.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;

/**
 * The PlaylistModel gets and passes data about the playlists to the BLL.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Radoslav Backovsky
 * @author Anne Luong
 * @author Michael Haaning Pedersen
 */
public final class PlaylistModel {

    private ObservableList<Playlist> playlistList;
    private LogicFacade logicManager;

    /**
     * Creates a connection to the BLL and gets the list of playlists.
     */
    public PlaylistModel() {
        logicManager = new LogicManager();
        getPlaylistList();
    }

    /**
     * Creates a new playlist. The method calls the BLL to create a playlist in
     * the database.The created playlist is added to the temporary list of all
     * playlists as well.
     *
     * @param id The ID of the new playlist.
     * @param name The name of the new playlist.
     */
    public void createPlaylist(int id, String name) {
        Playlist playlist = new Playlist(id, name);
        logicManager.createPlaylist(playlist);
        //allPlaylists.add(playlist);
    }

    /**
     * Gets the list of all playlists.
     *
     * @return The list of all playlists.
     */
    public ObservableList<Playlist> getPlaylistList() {
        List<Playlist> allPlaylists = logicManager.getAllPlaylists();
        for (Playlist playlist1 : allPlaylists) {
            //Save the converted time in the hh:mm:ss format before adding to an ObservableList.
            playlist1.setStringTime(sec_To_Format(playlist1.getPlaylistDuration()));
        }
        playlistList = FXCollections.observableArrayList(allPlaylists);
        return playlistList;
    }

    /**
     * Updates a playlist. The method calls the BLL to update a playlist in the
     * database.
     *
     * @param playlist The playlist to be updated.
     * @param editedName The edited name of the playlist.
     */
    public void updatePlaylist(Playlist playlist, String editedName) {
        logicManager.updatePlaylist(playlist, editedName);
    }

    /**
     * Deletes a playlist from the list of all playlists. The method calls the
     * BLL to delete a playlist from the database.
     *
     * @param playlist The playlist to be deleted.
     */
    public void deletePlaylist(Playlist playlist) {
        logicManager.deletePlaylist(playlist);
    }

    /**
     * Adds a song to a playlist. The method calls the BLL to add a song to a
     * playlist in the database.
     *
     * @param playlist The playlist the song is added to.
     * @param song The song to be added.
     * @return
     */
    public Playlist addSongToPlaylist(Playlist playlist, Song song) {
        return logicManager.addSongToPlaylist(playlist, song);
    }

    /**
     * Deletes a song from a playlist. The method calls the BLL to delete a song
     * from a playlist in the database.
     *
     * @param playlist The playlist the song is deleted from.
     * @param song The song to be deleted.
     */
    public void deleteSongFromPlaylist(Playlist playlist, Song song) {
        logicManager.deleteSongFromPlaylist(playlist, song);
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
