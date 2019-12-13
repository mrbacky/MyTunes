/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;

/**
 *
 * @author Bruger
 */
public final class PlaylistModel {
    private ObservableList<Playlist> playlistList;
    private LogicFacade logicManager;

    public PlaylistModel() {
        logicManager = new LogicManager();
        getPlaylistList();
        
    }

    /**
     * Gets the list of all playlists.
     * @return The list of all playlists.
     */
    public ObservableList<Playlist> getPlaylistList() {
        List<Playlist> allPlaylists = logicManager.getAllPlaylists();
        for (Playlist playlist1 : allPlaylists) {
            playlist1.setStringTime(sec_To_Format(playlist1.getTotalPlaylistPlaytime()));
        }
        playlistList = FXCollections.observableArrayList(allPlaylists);
        return playlistList;
    }
    
    public Playlist addSongToPlaylist(Playlist selectedPlaylist, Song selectedSong){
        return logicManager.addSongToPlaylist(selectedPlaylist, selectedSong); 
    }
    
    /**
     * Creates a new playlist.The method calls the BLL to create a playlist in the database.The created playlist is added to the temporary list of all playlists as well.
     * @param id
     @param name The name of the new playlist.
     * @param numberOfSongs
     */
    public void createPlaylist(int id,String name,int numberOfSongs) {
        Playlist playlist = new Playlist(id,name,numberOfSongs);
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
        logicManager.updatePlaylist(playlist, editedName);
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
        playlistList.remove(playlist);
    }
    public int format_To_Sec(String timeString) {
        return logicManager.format_To_Sec(timeString);
    }

    public String sec_To_Format(int sec) {
        return logicManager.sec_To_Format(sec);
    }
    
    public void deleteSongFromPlaylist(Playlist playlist, Song song){
        logicManager.deleteSongFromPlaylist(playlist, song);
        playlist.removeSong(song);
    }
}
