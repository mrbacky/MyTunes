/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 * The Playlist class is an entity class. It represents a table in the database,
 * where each entity instance corresponds to a row in the table. The columns of
 * each row is the attribute of the entity.
 * @author annem
 */
public class Playlist {
    private int id;
    private String name;
    private String time;
    private int songCount;
    private List<Song> playlist;
    
    /**
     * Constructs a new empty playlist.
     *
     * @param id The ID of the playlist.
     * @param name The name of the playlist.
     */
    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        this.time = time;
        songCount = 0;
        playlist = new ArrayList();
    }
        
    /**
     * Sets the name of the playlist.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the playlist.
     *
     * @return The name of the playlist.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the playlist.
     *
     * @return The ID of the playlist.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the list of songs on the playlist.
     * @return The list of songs on the playlist.
     */
    public List<Song> getPlaylist() {
        return playlist;
    }

    /**
     * Sets the list of songs on the playlist.
     *
     * @param songs The list of songs to set.
     */
    public void setPlaylist(List<Song> songs) {
        playlist.clear();
        playlist.addAll(songs);
    }

    /**
     * Gets the combined time of all the songs on the playlist.
     *
     * @return The duration of the playlist.
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the number of songs on the playlist.
     *
     * @return The song count.
     */
    public int getSongCount() {
        return songCount;
    }

    /**
     * Adds a song to the playlist.
     *
     * @param song The song to add to the playlist.
     */
    public void addSong(Song song) {
        playlist.add(song);
        //time += song.getTime();
        songCount++;
    }

    /**
     * Removes a song from the playlist.
     *
     * @param song The song to be removed from the playlist.
     */
    public void removeSong(Song song) {
        playlist.remove(song);
        //time -= song.getTime();
        songCount--;
    }
}
