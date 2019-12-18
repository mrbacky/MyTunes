package mytunes.gui.model;

import java.util.List;
import mytunes.bll.LogicFacade;
import mytunes.bll.LogicManager;

/**
 * The GenreModel gets and passes data about the genres to the BLL.
 *
 * @author Anne Luong
 */
public class GenreModel {

    private final LogicFacade logicLayer;

    /**
     * Initialize the BLL manager.
     */
    public GenreModel() {
        logicLayer = new LogicManager();
    }

    /**
     * Gets all genres from the database.
     *
     * @return A String list of all genres.
     */
    public List<String> getAllGenres() {
        return logicLayer.getAllGenres();
    }

    /**
     * Creates a new genre.
     *
     * @param name The name of the newly created genre.
     */
    public void createGenre(String name) {
        logicLayer.createGenre(name);
    }

    /**
     * Deletes a genre.
     *
     * @param name The name of the genre to be deleted.
     */
    public void deleteGenre(String name) {
        logicLayer.deleteGenre(name);
    }
}
