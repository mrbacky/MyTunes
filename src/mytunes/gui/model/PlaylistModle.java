/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.bll.Manager;

/**
 *
 * @author Bruger
 */
public class PlaylistModle {
    
    private ObservableList<Playlist> allPlaylist = FXCollections.observableArrayList();
    Manager manager;
    
    public PlaylistModle(){
        manager = new Manager();
        fetchAllPlaylist();
    }

    public void fetchAllPlaylist() {
        allPlaylist = FXCollections.observableArrayList(manager.getAllPlaylist());
    }
    
    public ObservableList<Playlist>getPlaylist(){
        return allPlaylist;
    }
}
