    
package mytunes.dal;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;


public interface DBFacade {
    public List<Playlist> getAllPlaylists();
    public List<Song> getAllSongs();
    public void addSong(Song song);
    //public int format_To_Sec();
}
