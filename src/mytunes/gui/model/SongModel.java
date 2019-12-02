
package mytunes.gui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.Manager;



public class SongModel {
    private ObservableList<Song> allSongs;
    private Manager manager;

    public SongModel() {
        manager = new Manager();
        fetchAllSongs();
    }

    public void fetchAllSongs(){
        manager.getAllSongs();
        allSongs = FXCollections.observableArrayList(manager.getAllSongs());
    }
   
    
    public ObservableList<Song> getSongList(){
        return allSongs;
    }
    
    
    
    
}
