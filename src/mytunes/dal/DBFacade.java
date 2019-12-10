package mytunes.dal;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;

/**
 * This interface is a facade for the data access layer.
 * @author annem
 */
public interface DBFacade {
    public List<Playlist> getAllPlaylists();
    public List<Song> getAllSongs();
    public List<SongOnPlaylist> getAllSongsOnPlaylist();
    
    public void addSong(Song song);

    /**
     * Creates and adds a new song to the database.
     *
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param time The time (duration) of the song.
     * @param path The path of the song.
     * @param genre The genre of the song.
     * @return The newly created song.
     */
    Song createSong(String title, String artist, String time, String path, String genre);

    /**
     * Updates the song in the database to reflect the values in the given Song object.
     * @param song The song to be updated.
     * @param editedTitle
     * @param editedArtist
     * @param editedGenre
     * @return The updated song.
     */
    Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre);
    
    /**
     * Deletes the given song from the database.
     *
     * @param song The song to delete.
     */
    void deleteSong(Song song);

    /**
     * Gets a list of all the songs in the database.
     * @return List with all songs.
     */
    List<Song> getAllSongs();
    
    /**
     * Creates a playlist in the database.
     * @param name The name of the playlist.
     * @return The newly created playlist.
     */
    Playlist createPlaylist(String name);
    
    /**
     * Updates the name of the playlist in the database to reflect the values in the given Song object.
     
     * @param playlist The playlist to be updated.
     * @param editedName 
     * @return The updated playlist.
     */
    Playlist updatePlaylist(Playlist playlist, String editedName);
    /**
     * Deletes the playlist from the database.
     * @param playlist The playlist to be deleted.
     */
    void deletePlaylist(Playlist playlist);
    
    /**
     * Gets a list of all playlists in the database.
     * @return List with all playlists.
     */
    List<Playlist> getAllPlaylists();
    
    /**
     * Adds the song to a playlist in the database.
     * @param playlist The playlist of the song.
     * @param song The song to be added to the playlist.
     * @return Playlist with the added song.
     */
    Playlist addSongToPlaylist(Playlist playlist, Song song);
    
    /**
     * Deletes the song from the playlist in the database.
     * @param playlist The playlist of the song.
     * @param song The song to be deleted from the playlist.
     */
    void deleteSongFromPlaylist(Playlist playlist, Song song);
    
}
