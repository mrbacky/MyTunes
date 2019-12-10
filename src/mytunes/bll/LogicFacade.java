/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mytunes.bll;

import java.util.List;
import mytunes.be.Song;
import mytunes.be.Playlist;
import mytunes.be.PlaylistPowerMove;


public interface LogicFacade {
    
    //  Songs on Playlist methods
    public void addSongToPlaylist();
    public void removeSongFromPlaylist();
    
    
    //  Library
    public void newSong();
    public void editSong();
    public void removeSongFromLib();
    
    //  Playlist methods
    public void newPlaylist();
    public void editPlaylist();
    public void removePlaylist();
    public List<Playlist> getAllPlaylists();
    
    public List<Song> getAllSongs();

    public void addSong(Song song);
    
    
    
    public List<PlaylistPowerMove> getAllPowerPlaylists();
    
    
    
}
