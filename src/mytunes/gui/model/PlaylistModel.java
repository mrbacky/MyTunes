/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;

/**
 *
 * @author Bruger
 */
public final class PlaylistModel {
    private ObservableList<Playlist> allPlaylists;
    private LogicFacade logicManager;

    public PlaylistModel() {
        logicManager = new LogicManager();
        allPlaylists = FXCollections.observableArrayList(logicManager.getAllPlaylists());
    }

    /**
     * Gets the list of all playlists.
     * @return The list of all playlists.
     */
    public ObservableList<Playlist> getPlaylists() {
         allPlaylists = FXCollections.observableArrayList(logicManager.getAllPlaylists());
         return allPlaylists;
    }
    
    private void updateListofPlaylists(Playlist playlist){
        allPlaylists.set(allPlaylists.indexOf(playlist), playlist);
        //setPlaylistSongs(playlist);
    }
   
    /**
     * Creates a new playlist.
     * The method calls the BLL to create a playlist in the database.
     * The created playlist is added to the temporary list of all playlists as well.
     @param name The name of the new playlist.
     */
    public void createPlaylist(String name, int songCounter, int time) {
        Playlist playlist = new Playlist(0, name, 0, 0);
        logicManager.createPlaylist(playlist);
        //allPlaylists.add(playlist);
    }
    
    /**
     * Updates a playlist.
     * NOT COMPLETED!!
     * @param playlist The playlist to be updated.
     * @param editedName The edited name of the playlist.
     */
    public void updatePlaylist(Playlist playlist, String editedName) {
        Playlist updatedPlaylist = logicManager.updatePlaylist(playlist, editedName);
        updateListofPlaylists(updatedPlaylist);
    }

    /**
     * Deletes a playlist from the list of all playlists. The method calls the
     * BLL to delete a playlist from the database. The deleted playlist is
     * deleted from the temporary list of all playlists as well.
     *
     * @param playlist The playlist to be deleted.
     */
    public void deletePlaylist(Playlist playlist) {
        logicManager.deletePlaylist(playlist);
        allPlaylists.remove(playlist);
    }
}
