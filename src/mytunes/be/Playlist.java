package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 * The Playlist class is an entity class. It represents a table in the database,
 * where each entity instance corresponds to a row in the table. The columns of
 * each row is the attribute of the entity.
 * @author PC
 */
public class Playlist {

    private final int id;
    private String name;
    private int time;
    private List<Song> songs;
    private int numberOfSongs;
    private String stringTime;

    /**
     * Constructs a new empty playlist.
     *
     * @param id ID of the playlist.
     * @param name Name of the playlist.
     */
    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        this.time = 0;
        songs = new ArrayList();
        numberOfSongs = songs.size();
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
     * Gets the name of the playlist.
     *
     * @return The name of the playlist.
     */
    public String getName() {
        return name;
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
     * Gets the time (duration) of the playlist.
     *
     * @return The time of the playlist.
     */
    public int getTime() {
        return time;
    }

    /**
     * Gets the list of songs in the playlist.
     *
     * @return The list of songs.
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Gets the number of songs on the playlist.
     *
     * @return The number of songs.
     */
    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    /**
     * Gets the duration of the playlist in the format hh:mm:ss.
     *
     * @return The formatted time.
     */
    public String getStringTime() {
        return stringTime;
    }

    /**
     * Sets the duration of the playlist in the format hh:mm:ss.
     * @param stringTime
     */
    public void setStringTime(String stringTime) {
        this.stringTime = stringTime;
    }

    /**
     * Gets the duration of the playlist in seconds.
     *
     * @return The duration of the playlist.
     */
    public int getPlaylistDuration() {
        int playlistDuration = 0;
        for (Song song : songs) {
            song.getTime();
            playlistDuration += song.getTime();
        }
        return playlistDuration;
    }

    /**
     * Adds a song to a playlist.
     *
     * @param song The song to add.
     */
    public void addSong(Song song) {
        songs.add(song);
        numberOfSongs = songs.size();
    }

    /**
     * Removes a song from a playlist.
     *
     * @param song The song to remove.
     */
    public void removeSong(Song song) {
        songs.remove(song);
        numberOfSongs--;
    }
}
