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
    private int songCounter;
    private String time;
    
    /**
     * Constructs a new empty playlist.
     *
     * @param id The ID of the playlist.
     * @param name The name of the playlist.
     * @param songCounter
     * @param time
     */
    public Playlist(int id, String name, int songCounter, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.songCounter = songCounter;
        
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

    public int getSongCounter() {
        return songCounter;
    }

    public void setSongCounter(int songCounter) {
        this.songCounter = songCounter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}