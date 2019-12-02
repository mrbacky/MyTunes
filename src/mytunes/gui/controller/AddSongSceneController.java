
package mytunes.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class AddSongSceneController implements Initializable {

    @FXML
    private Button btn_chooseFile;
    @FXML
    private TextField txtField_AddSong_title;
    @FXML
    private ChoiceBox<?> choiseBox_AddSong_genre;
    @FXML
    private TextField txtField_AddSong_time;
    @FXML
    private TextField txtField_AddSong_filePath;
    @FXML
    private TextField txtField_AddSong_artist;
    @FXML
    private Button btn_AddSong_cancelSong;
    @FXML
    private Button btn_AddSong_saveSong;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_AddSong(ActionEvent event) {
    }
    
}
