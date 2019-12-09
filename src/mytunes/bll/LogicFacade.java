
package mytunes.bll;

import java.util.List;
import mytunes.be.Song;
import mytunes.be.Playlist;


public interface LogicFacade {
    
    //  time converter methods
    public String sec_To_Format(int sec);    // format:   hh:mm:ss
    public int format_To_Sec(String formatString);
    
    //  Songs on Playlist methods
    public void addSongToPlaylist();
    public void removeSongFromPlaylist();
    
    
    //  Library
    public void addSong(Song song);//done
    public List<Song> getAllSongs();//done
    public void editSong();
    public void removeSongFromLib();
    
    //  Playlist methods
    public void newPlaylist();
    public void editPlaylist();
    public void removePlaylist();
    public List<Playlist> getAllPlaylists();//done
    
    

    
    
    
    
    
    
    
}
