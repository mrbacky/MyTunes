
package mytunes.gui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.LogicManager;
import mytunes.bll.LogicFacade;



public final class SongModel {
    private ObservableList<Song> libraryList;
    private LogicFacade logicManager;

    public SongModel() {
        logicManager = new LogicManager();
        getLibraryList();
    }

    public ObservableList<Song> getLibraryList(){
        return libraryList = FXCollections.observableArrayList(logicManager.getAllSongs());
    }
    
    public ObservableList<Song> updateLibraryList(){
        System.out.println("test");
        return getLibraryList();
    }
    
    public void addSong(String title, String artist, String time, Object genre, String path) {
        Song song = new Song(0, title, artist, 0,path, "genre"  );
        logicManager.addSong(song);
    }
    
    
}
