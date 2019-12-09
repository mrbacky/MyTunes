/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mytunes.bll;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import mytunes.be.Song;
import mytunes.be.Playlist;

/**
 * This interface is a facade for the business logic layer.
 * @author annem
 */


public interface LogicFacade {
    
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
     * Updates the new in the database.
     * @param song The song to be updated.
     * @param editedTitle The edited title of the song.
     * @param editedArtist The edited artist of the song.
     * @param editedGenre The edited genre of the song.
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
    
    /**
     * Searches for all songs which matches the given query.
     *
     * @param searchBase
     * @param query The search query
     * @return A list of songs that matches the search query.
     */
    List<Song> search(List<Song> searchBase, String query);

}
