    
package mytunes.dal;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;


public interface DBFacade {
    public List<Playlist> getAllPlaylists();
    public List<Song> getAllSongs();
    public List<SongOnPlaylist> getAllSongsOnPlaylist();
    
    public void addSong(Song song);

}