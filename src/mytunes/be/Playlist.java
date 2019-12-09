/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mytunes.be;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Playlist {
    private int id;
    private String name;
    private String time;
    private int songs;
    
    
    public Playlist(int id, String name, String time, int songs) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.songs = songs;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
    
    public String getTime() 
    {
        return time;
    }
    
    public void setSongs(int songs)
    {
        this.songs = songs;
    }
    
    public int getSongs()
    {
        return songs;
    }
}
