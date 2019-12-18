package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.SongOnPlaylist;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;

/**
 *
 * @author Michael Haaning Pedersen
 */
public final class SongOnPlaylistModel {
    
    private ObservableList<SongOnPlaylist> allSongOnPlaylist = FXCollections.observableArrayList();
    LogicFacade logicManager;
    
    /**
     *
     */
    public SongOnPlaylistModel(){
        logicManager = new LogicManager();
        fetchAllSongOnPlaylist();
    }

    /**
     *
     */
    public void fetchAllSongOnPlaylist() {
        allSongOnPlaylist = FXCollections.observableArrayList(logicManager.getSongOnPlaylist());
    }
    
    /**
     *
     * @return
     */
    public ObservableList<SongOnPlaylist>getSongOnPlaylist(){
        return allSongOnPlaylist;
    }
}
