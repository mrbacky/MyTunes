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
import static javax.management.Query.value;

public class AddSongSceneController implements Initializable {

    @FXML
    private Button btn_chooseFile;
    
    
    @FXML
    private TextField txtField_AddSong_filePath;
    
    @FXML
    private Button btn_AddSong_cancelSong;
    @FXML
    private Button btn_AddSong_saveSong;
    @FXML
    private TextField txtField_AddSong_title;
    @FXML
    private TextField txtField_AddSong_artist;
    @FXML
    private ChoiceBox<?> choiseBox_AddSong_genre;
    @FXML
    private TextField txtField_AddSong_time;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handle_OpenFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("mp3 Files", "*.mp3"),
                new FileChooser.ExtensionFilter("wav Files", "*.wav")
        );
        
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            txtField_AddSong_filePath.setText(selectedFile.getAbsolutePath());
        }
    }

    
    @FXML
    private void handle_AddSongToDB(ActionEvent event) {
        
        txtField_AddSong_artist.getText();
        txtField_AddSong_time.getText();
        
    
    }
}


