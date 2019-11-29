
package mytunes.gui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;


public class SongModel {
    private final ObservableList<Song> allSongs = FXCollections.observableArrayList();
    
 
    public SongModel() {
        allSongs.add(new Song("Stayin Out All Night", "Wiz Khalifa", 29, "C:\\Users\\rados\\Disk Google\\songs\\Stayin Out All Night.mp3", "Hip-Hop"));
        allSongs.add(new Song("So High", "Wiz Khalifa", 83, "C:\\Users\\rados\\Disk Google\\songs\\So High.mp3", "Hip-Hop"));
        allSongs.add(new Song("True Colors", "Wiz Khalifa", 73, "C:\\Users\\rados\\Disk Google\\songs\\True Colors.mp3", "Hip-Hop"));
    }
    
    public ObservableList<Song> getSongList(){
        return allSongs;
    }
    
    
    public void fetchAllSongs(){
        allSongs.clear();
       // allSongs.addAll(bll.getAllSongs());
    }
    
}
