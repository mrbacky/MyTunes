    
package mytunes.dal;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;


public interface IDBManager {
    
    public List<Song> getAllSongs();
    
    public List<Playlist> getAllPlaylist();

            
 }
