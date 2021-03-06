package mytunes.dal;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;

/**
 * This interface is a facade for the data access layer. It contains methods
 * used for database operations.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Radoslav Backovsky
 * @author Anne Luong
 * @author Michael Haaning Pedersen
 */
public interface DBFacade {

    //__________________________________________________________________________                       
    //
    //      Song  
    //__________________________________________________________________________
    /**
     * Creates and adds a new song to the database.
     *
     * @param title
     * @param artist
     * @param time
     * @param path
     * @param genre
     * @return
     */
    Song createSong(String title, String artist, int time, String path, String genre);

    /**
     * Updates the song in the database to reflect the values in the given Song
     * object.
     *
     * @param song The song to be updated.
     * @param editedTitle
     * @param editedArtist
     * @param editedGenre
     * @param editedTime
     * @param path
     * @return The updated song.
     */
    Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre, int editedTime, String path);

    /**
     * Deletes the given song from the database.
     *
     * @param song The song to delete.
     */
    void deleteSong(Song song);

    /**
     * Gets a list of all the songs in the database.
     *
     * @return List with all songs.
     */
    List<Song> getAllSongs();

    //__________________________________________________________________________                       
    //
    //      Playlist    
    //__________________________________________________________________________  
    /**
     * Creates a playlist in the database.
     *
     * @param playlist The playlist to be created.
     */
    void createPlaylist(Playlist playlist);

    /**
     * Gets a list of all playlists in the database.
     *
     * @return List with all playlists.
     */
    List<Playlist> getAllPlaylists();

    /**
     * Updates the name of the playlist in the database to reflect the values in
     * the given Song object.
     *
     * @param playlist The playlist to be updated.
     * @param editedName
     * @return The updated playlist.
     */
    Playlist updatePlaylist(Playlist playlist, String editedName);

    /**
     * Deletes the playlist from the database.
     *
     * @param playlist The playlist to be deleted.
     */
    void deletePlaylist(Playlist playlist);

    /**
     * Adds the song to a playlist in the database.
     *
     * @param playlist The playlist of the song.
     * @param song The song to be added to the playlist.
     * @return Playlist with the added song.
     */
    Playlist addSongToPlaylist(Playlist playlist, Song song);

    //__________________________________________________________________________                       
    //
    //      Song On Playlist    
    //__________________________________________________________________________                     
    /**
     * Gets all songs on a playlist.
     *
     * @return A list with all the songs of a playlist.
     */
    List<SongOnPlaylist> getAllSongsOnPlaylist();
    
    //__________________________________________________________________________                       
    //
    //      Song On Playlist    
    //__________________________________________________________________________                     
        
    /**
     * Deletes the song from the playlist in the database.
     *
     * @param playlist The playlist of the song.
     * @param song The song to be deleted from the playlist.
     */
    void deleteSongFromPlaylist(Playlist playlist, Song song);
    //__________________________________________________________________________                       
    //
    //      Song On Playlist    
    //__________________________________________________________________________                     

    /**
     * Gets a list with the names of all the genres in the database.
     *
     * @return List with all genres.
     */
    List<String> getAllGenres();

    /**
     * Creates and adds a new genre to the database.
     *
     * @param name The name of the genre.
     */
    void createGenre(String name);

    /**
     * Deletes a genre in the database.
     *
     * @param name The name of the genre.
     */
    void deleteGenre(String name);
}
