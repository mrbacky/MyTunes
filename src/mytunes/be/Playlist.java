/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class Playlist {
    private int id;
    private String name;
    private int time;
    private List<Song> songs = new ArrayList<>();
    private int numberOfSongs;
    private String stringTime;
   

    public int getTotalPlaylistPlaytime() {
        int totalPlaylistPlaytime = 0;
        for (Song song : songs) {
            song.getTime();
            totalPlaylistPlaytime+=song.getTime();
        }
        return totalPlaylistPlaytime;
    }

    
    
    public String getStringTime() {
        return stringTime;
    }

    public void setStringTime(String stringTime) {
        this.stringTime = stringTime;
    }

    

   

    public Playlist(int id, String name, int time) {
        this.id = id;
        this.name = name;
        this.time = time;
        numberOfSongs = songs.size();
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Adds a song to a playlist.
     *
     * @param son The song to add.
     */
    public void addSong(Song son) {
        songs.add(son);
        numberOfSongs = songs.size();
    }

    /**
     * Removes a song from a playlist.
     *
     * @param song
     */
    public void removeSong(Song song) {
        songs.remove(song);
        numberOfSongs--;
    }
    
    
}
