/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll.util;

import java.util.ArrayList;
import java.util.List;
import mytunes.be.Song;

/**
 * The SearchFilter class is a tool used to filter out song items, which 
 * matches the search query.
 *
 * @author Anne
 */
public class SearchFilter {

    /**
     *
     * @param searchBase
     * @param query The search query.
     * @return A list of songs that matches the search query.
     */
    public List<Song> search(List<Song> searchBase, String query){
    //case insensitive and partial search
    List<Song> filtered = new ArrayList();
    
    if (query.isEmpty()){
    return searchBase;
    }    
        for (Song song : searchBase) {
            if (song.getTitle().toLowerCase().contains(query.trim().toLowerCase())) {
                filtered.add(song);
            } else if (song.getArtist().toLowerCase().contains(query.trim().toLowerCase())) {
                filtered.add(song);
            }
        }
        return filtered;  
    }
}
