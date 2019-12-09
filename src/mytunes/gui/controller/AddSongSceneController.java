package mytunes.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javax.management.Query.value;
import mytunes.gui.model.SongModel;
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import mytunes.be.Genre;
import mytunes.gui.controller.PrimaryController;

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
    private ChoiceBox<Genre> choiseBox_AddSong_genre;
    @FXML
    private TextField txtField_AddSong_time;
    private SongModel songModel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songModel = new SongModel();
        //choiseBox_AddSong_genre.getItems().add("Rock");
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
    private void handle_AddSongToDB(ActionEvent event) throws InterruptedException, IOException {

        txtField_AddSong_title.getText();
        txtField_AddSong_artist.getText();
        txtField_AddSong_time.getText();
        choiseBox_AddSong_genre.getSelectionModel().getSelectedItem();
        txtField_AddSong_filePath.getText();

        songModel.addSong(txtField_AddSong_title.getText(),
                txtField_AddSong_artist.getText(),
                txtField_AddSong_time.getText(),
                choiseBox_AddSong_genre.getSelectionModel().getSelectedItem(),
                txtField_AddSong_filePath.getText()
        );
        updateLibrary();

        Stage stage;
        stage = (Stage) btn_AddSong_saveSong.getScene().getWindow();
        stage.close();
    }

    public void updateLibrary() {
        pCon.updateLibrary();
    }

    @FXML
    private void handle_CloseScene(ActionEvent event) {
        Stage stage = (Stage) btn_AddSong_cancelSong.getScene().getWindow();
        stage.close();

    }
    
    private PrimaryController pCon;

    void setContr(PrimaryController pCon) {
        this.pCon = pCon;
    }
}
