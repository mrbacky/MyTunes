/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

/**
 *
 * @author Bruger
 */
     
 
public class SongOnPlaylist {
    private int songid; 
    private int playlistid;
    private int order;
    private String title;
    private String songpath;
    
    
    
    public SongOnPlaylist(int songid, int playlistid, int order, String title, String songpath){
    
        this.order = order;
        this.playlistid = playlistid;
        this.songid = songid;
        this.title = title;
        this.songpath = songpath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPath(String path) {
        this.songpath = songpath;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return songpath;
    }

    public int getSongid() {
        return songid;
    }

    public int getPlaylistid() {
        return playlistid;
    }

    public int getOrder() {
        return order;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public void setPlaylistid(int playlistid) {
        this.playlistid = playlistid;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    
}
