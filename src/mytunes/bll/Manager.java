package mytunes.bll;


import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.DBManager;
import mytunes.dal.IDBManager;

public class Manager implements IManager {
    
    IDBManager DBM;
    
    public Manager() {
    DBM = new DBManager();
    }

    
    
    public void Search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSongToPlaylist() {
        
    }

    @Override
    public void removeSongFromPlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newSong() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editSong() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSongFromLib() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newPlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editPlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Song> getAllSongs() {
        
       return DBM.getAllSongs();
    }
    
    public List<Playlist> getAllPlaylist(){
        
      return DBM.getAllPlaylist();   
    }
    
    
}
