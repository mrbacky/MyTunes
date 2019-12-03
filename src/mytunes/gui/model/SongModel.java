
package mytunes.gui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;



public final class SongModel {
    private ObservableList<Song> allSongs;
    private LogicFacade logicManager;

    public SongModel() {
        logicManager = new LogicManager();
        fetchAllSongs();
    }

    public void fetchAllSongs(){
        logicManager.getAllSongs();
        allSongs = FXCollections.observableArrayList(logicManager.getAllSongs());
    }
   
    
    public ObservableList<Song> getSongList(){
        return allSongs;
    }
    
    
    
    
}
