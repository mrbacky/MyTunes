
package mytunes.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import mytunes.gui.model.SongModel;

public class EditSongSceneController implements Initializable {
    @FXML
    private Button btn_chooseFile;
    @FXML
    private TextField txtField_EditSong_title;
    @FXML
    private ChoiceBox<?> choiseBox_EditSong_genre;
    @FXML
    private TextField txtField_EditSong_time;
    @FXML
    private TextField txtField_EditSong_filePath;
    @FXML
    private TextField txtField_EditSong_artist;
    @FXML
    private Button btn_EditSong_cancelSong;
    @FXML
    private Button btn_EditSong_saveSong;
    
    //__________________________________________________________________________
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    
   
}
