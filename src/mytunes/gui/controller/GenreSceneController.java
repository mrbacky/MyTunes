package mytunes.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.gui.model.GenreModel;

/**
 *
 * @author annem
 */
public class GenreSceneController implements Initializable {

    @FXML
    private TextField txtField_create;
    @FXML
    private ChoiceBox<String> choiceBox_currentGenres;
    @FXML
    private Button btn_createGenre;
    @FXML
    private Button btn_deleteGenre;
    @FXML
    private Button btn_cancel;    
    private PrimaryController pCon;
    private GenreModel genreModel;
    private SongSceneController conSong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genreModel = new GenreModel();

        //Get all genres.
        List<String> allGenres = genreModel.getAllGenres();
        //Add all the genres to the choicebox.
        for (String allGenre : allGenres) {
            choiceBox_currentGenres.getItems().addAll(allGenre);
        }
    }    

    public void setContr(SongSceneController conSong) {
        this.conSong = conSong;
    }
    
    @FXML
    private void handle_createGenre(ActionEvent event) {
        String name = txtField_create.getText().trim();
        genreModel.createGenre(name);
        choiceBox_currentGenres.getItems().add(name);
        conSong.updateGenre(name, true);
    }

    @FXML
    private void handle_deleteGenre(ActionEvent event) {
        String name = choiceBox_currentGenres.getSelectionModel().getSelectedItem();
        genreModel.deleteGenre(name);
        choiceBox_currentGenres.getItems().remove(name);
        conSong.updateGenre(name, false);
    }

    @FXML
    private void handle_cancelScene(ActionEvent event) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
}
