
package mytunes.gui.model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;


public class SongModel {
    ArrayList<Song> songList = new ArrayList<>();
    private ObservableList<Song> allSongs = FXCollections.observableArrayList();
    

    public SongModel() {
        songList.add(new Song("Stayin Out All Night", "Wiz Khalifa", 29, "C:\\Users\\rados\\Disk Google\\songs\\Stayin Out All Night.mp3", "Hip-Hop"));
        songList.add(new Song("So High", "Wiz Khalifa", 83, "C:\\Users\\rados\\Disk Google\\songs\\So High.mp3", "Hip-Hop"));
        songList.add(new Song("True Colors", "Wiz Khalifa", 73, "C:\\Users\\rados\\Disk Google\\songs\\True Colors.mp3", "Hip-Hop"));
    }
    
    //Jeppe is khul
   
    public ObservableList<Song> getAllSongs(){
        allSongs.addAll(songList);
        return allSongs;
    }
    
}
