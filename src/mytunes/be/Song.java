package mytunes.be;

/**
 * The Song class is an entity class. It represents a table in the database,
 * where each entity instance corresponds to a row in the table. The columns of
 * each row is the attribute of the entity.
 *
 * @author Michael, Rado, Abdi, Anne
 */
public class Song {

    private final int id;
    private String title;
    private String artist;
    private String time;
    private String path;
    private String genre;

    /**
     * Constructs a new song.
     *
     * @param id ID of the song.
     * @param title Title of the song.
     * @param artist Artist of the song.
     * @param time Time (duration) of the song.
     * @param path Path of the song.
     * @param genre Genre of the song.
     */
    public Song(int id, String title, String artist, String time, String path, String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.path = path;
        this.genre = genre;
    }

    /**
     * Gets the ID of the song.
     *
     * @return The ID of the song.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the song.
     *
     * @return The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the artist of the song.
     *
     * @return The artist of the song.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the song.
     *
     * @param artist The artist to set.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    
    
    public String getTime() {
        return time;

    /**
     * Gets the time (duration) of the song.
     *
     * @return The time of the song.
     */
    public String getTime() {
        return time;
    }
    
    //Should the user be able to set the time? Does not make sense to me?
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the path of the song.
     *
     * @return The path of the song.
     */
    public String getPath() {
        return path;
    }

    //Add a setPath?
    
    /**
     * Gets the genre of the song.
     *
     * @return The genre of the song.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the song.
     *
     * @param genre The genre to set.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return title;
    }

}
