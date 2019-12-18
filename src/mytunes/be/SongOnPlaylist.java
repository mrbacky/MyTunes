package mytunes.be;

/**
 * The SongOnPlaylist class is an entity class. It represents a table in the
 * database, where each entity instance corresponds to a row in the table. The
 * columns of each row is the attribute of the entity.
 * 
 * Actually, it should not be an entity class but there was not enough time to
 * correct this mistake. The class could not be removed, because there is code
 * using this class and there not enough time to rewrite the code.
 *
 * @author Michael Haaning Pedersen
 */
public class SongOnPlaylist {

    private int songid;
    private int playlistid;
    private int order;
    private String title;
    private String songPath;

    public SongOnPlaylist(int songID, int playlistID, int order, String title, String songPath) {

        this.order = order;
        this.playlistid = playlistID;
        this.songid = songID;
        this.title = title;
        this.songPath = songPath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPath(String path) {
        this.songPath = songPath;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return songPath;
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
