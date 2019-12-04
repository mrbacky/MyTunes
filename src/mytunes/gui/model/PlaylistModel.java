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
    
    private ObservableList<Playlist> allPlaylist = FXCollections.observableArrayList();
    LogicFacade logicManager;
    
    public PlaylistModel(){
        logicManager = new LogicManager();
        fetchAllPlaylists();
    }

    public void fetchAllPlaylists() {
        allPlaylist = FXCollections.observableArrayList(logicManager.getAllPlaylists());
    }
    
    public ObservableList<Playlist>getPlaylists(){
        return allPlaylist;
    }
}