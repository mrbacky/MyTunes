package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.SongOnPlaylist;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;

/**
 * The SongOnPlaylistModel creates an ObservableList and a connection to the BLL.
 *
 * @author Michael Haaning Pedersen
 */
public final class SongOnPlaylistModel {
    
    private ObservableList<SongOnPlaylist> allSongOnPlaylist = FXCollections.observableArrayList();
    LogicFacade logicManager;
    
    /**
     * Creates an instance of the LogicManager to get a connection and call the
     * getSongOnPlaylist() method.
     */
    public SongOnPlaylistModel(){
        logicManager = new LogicManager();
        fetchAllSongOnPlaylist();
    }

    /**
     * Takes allsongOnPlaylist and adds it to the ObservableList by calling the
     * getSongOnPlaylist from the LogicManager.
     */
    public void fetchAllSongOnPlaylist() {
        allSongOnPlaylist = FXCollections.observableArrayList(logicManager.getSongOnPlaylist());
    }
    
    /**
     * Returns all songOnPlayist as a list.
     *
     * @return allSongOnPlaylist
     */
    public ObservableList<SongOnPlaylist>getSongOnPlaylist(){
        return allSongOnPlaylist;
    }
}
