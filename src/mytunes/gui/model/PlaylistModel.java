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
 * @author annem
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
            playlist1.setStringTime(sec_To_Format(playlist1.getPlaylistDuration()));
        }
        playlistList = FXCollections.observableArrayList(allPlaylists);
        return playlistList;
    }

    /**
     * Updates a playlist. The method calls the BLL to update a playlist in the
     * database.
     *
     * @param selectedPlaylist The playlist to be updated.
     * @param editedName The edited name of the playlist.
     */
    public void updatePlaylist(Playlist selectedPlaylist, String editedName) {
        logicManager.updatePlaylist(selectedPlaylist, editedName);
    }

    /**
     * Deletes a playlist from the list of all playlists. The method calls the
     * BLL to delete a playlist from the database.
     *
     * @param selectedPlaylist The playlist to be deleted.
     */
    public void deletePlaylist(Playlist selectedPlaylist) {
        logicManager.deletePlaylist(selectedPlaylist);
    }

    /**
     * Adds a song to a playlist. The method calls the BLL to add a song to a
     * playlist in the database.
     *
     * @param selectedPlaylist The playlist the song is added to.
     * @param selectedSong The song to be added.
     * @return
     */
    public Playlist addSongToPlaylist(Playlist selectedPlaylist, Song selectedSong) {
        return logicManager.addSongToPlaylist(selectedPlaylist, selectedSong);
    }

    /**
     * Deletes a song from a playlist. The method calls the BLL to delete a song
     * from a playlist in the database.
     *
     * @param selectedPlaylist The playlist the song is deleted from.
     * @param selectedSong The song to be deleted.
     */
    public void deleteSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) {
        logicManager.deleteSongFromPlaylist(selectedPlaylist, selectedSong);
    }

    public int format_To_Sec(String timeString) {
        return logicManager.format_To_Sec(timeString);
    }

    public String sec_To_Format(int sec) {
        return logicManager.sec_To_Format(sec);
    }

}
