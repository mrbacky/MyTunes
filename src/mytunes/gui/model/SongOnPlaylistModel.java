package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.SongOnPlaylist;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;

/**
 *
 * @this class create a obserbablelist and a connection to the logicManager
 */
public final class SongOnPlaylistModel {
    
    private ObservableList<SongOnPlaylist> allSongOnPlaylist = FXCollections.observableArrayList();
    LogicFacade logicManager;
    
    /**
     *this songOnPlaylistModle create and instance of the logicManager to get a conection. and call a methert.
     */
    public SongOnPlaylistModel(){
        logicManager = new LogicManager();
        fetchAllSongOnPlaylist();
    }

    /**
     *this fetchAllSongOnPlaylist takes allsongOnPlaylist and add it to the observableList by calling the getSongOnPlaylist
     * from the logicManager.
     */
    public void fetchAllSongOnPlaylist() {
        allSongOnPlaylist = FXCollections.observableArrayList(logicManager.getSongOnPlaylist());
    }
    
    /**
     *this retuns all songOnPlayist as a list.
     * @return allSongOnPlaylist
     */
    public ObservableList<SongOnPlaylist>getSongOnPlaylist(){
        return allSongOnPlaylist;
    }
}
