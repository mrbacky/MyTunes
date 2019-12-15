package mytunes.bll;

import java.util.List;
import mytunes.be.Song;
import mytunes.be.Playlist;
import mytunes.be.SongOnPlaylist;


public interface LogicFacade {
    
    //__________________________________________________________________________                       
    //
    //      Library  
    //__________________________________________________________________________
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
    //Song createSong(String title, String artist, String time, String path, String genre);
    Song createSong(String title, String artist, int time, String path, String genre);
    /**
     * Gets a list of all the songs in the database.
     * @return List with all songs.
     */
    List<Song> getAllSongs();
    /**
     * Updates the new in the database.
     * @param song The song to be updated.
     * @param editedTitle The edited title of the song.
     * @param editedArtist The edited artist of the song.
     * @param editedGenre The edited genre of the song.
     * @return The updated song.
     */
    Song updateSong(Song song, String editedTitle, String editedArtist, String editedGenre, int editedTime, String editedPath);
    /**
     * Deletes the given song from the database.
     *
     * @param song The song to delete.
     */
    void deleteSong(Song song);
    //__________________________________________________________________________                       
    //
    //      Playlists
    //__________________________________________________________________________
    /**
     * Creates a playlist in the database.
     * @param name The name of the playlist.
     * @return The newly created playlist.
     */
    void createPlaylist(Playlist playlist);
    /**
     * Gets a list of all playlists in the database.
     * @return List with all playlists.
     */
    List<Playlist> getAllPlaylists();
    /**
     * Updates the name of a playlist in the database.
     * @param playlist The playlist to be updated.
     * @param editedName The edited name of the playlist.
     * @return The updated playlist.
     */
    Playlist updatePlaylist(Playlist playlist, String editedName);
    /**
     * Deletes the playlist from the database.
     * @param playlist The playlist to be deleted.
     */
    void deletePlaylist(Playlist playlist);
    //__________________________________________________________________________                       
    //
    //      Songs on Playlist
    //__________________________________________________________________________
    /**
     * Adds the song to a playlist in the database.
     * @param playlist The playlist of the song.
     * @param song The song to be added to the playlist.
     * @return Playlist with the added song.
     */
    Playlist addSongToPlaylist(Playlist selectedPlaylist, Song selectedSong);
    List<SongOnPlaylist> getSongOnPlaylist();
    /**
     * Deletes the song from the playlist in the database.
     * @param playlist The playlist of the song.
     * @param song The song to be deleted from the playlist.
     */
    void deleteSongFromPlaylist(Playlist playlist, Song song);
    //__________________________________________________________________________                       
    //
    //      Genres
    //__________________________________________________________________________
    /**
     * Gets a list of the names of all the genres from the database,
     * @return A list of all the genres.
     */
    List<String> getAllGenres();
    /**
     *
     * @param name
     */
    void createGenre(String name);
    /**
     *
     * @param name
     */
    void deleteGenre(String name);
    //__________________________________________________________________________                       
    //
    //      Utilities
    //__________________________________________________________________________
    String sec_To_Format(int sec);    
    int format_To_Sec(String formatString);
    /**
     * Searches for all songs which matches the given query.
     *
     * @param searchBase
     * @param query The search query
     * @return A list of songs that matches the search query.
     */
    List<Song> search(List<Song> searchBase, String query);
    
    
}
