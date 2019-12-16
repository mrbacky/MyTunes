package mytunes.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mytunes.gui.model.SongModel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import mytunes.be.Song;
import mytunes.gui.model.GenreModel;

public class SongSceneController implements Initializable {

    @FXML
    private TextField txtField_title;
    @FXML
    private TextField txtField_artist;
    @FXML
    private ChoiceBox<String> choiceBox_genre;
    @FXML
    private TextField txtField_time;
    @FXML
    private TextField txtField_filePath;
    @FXML
    private Button btn_chooseFile;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_confirm;

    private boolean edit;
    private Song songToEdit;
    private SongModel songModel;
    private GenreModel genreModel;
    private PrimaryController pCon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edit = false;
        songModel = new SongModel();
        genreModel = new GenreModel();
        //Get all genres.
        List<String> allGenres = genreModel.getAllGenres();
        //Add all the genres to the choicebox.
        for (String allGenre : allGenres) {
            choiceBox_genre.getItems().add(allGenre);
        }
    }

    public void setContr(PrimaryController pCon) {
        this.pCon = pCon;
    }

    public void refreshLibrary() {
        pCon.refreshLibrary();
    }

    @FXML
    private void handle_openFileChooser(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("mp3 Files", "*.mp3"),
                new FileChooser.ExtensionFilter("wav Files", "*.wav")
        );

        File songFile = fileChooser.showOpenDialog(null);
        if (songFile != null) {
            String songPATH = songFile.getAbsolutePath();
            txtField_filePath.setText(songPATH);
            Media media = new Media(songFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnReady(new Runnable() {

                @Override
                public void run() {
                    int time, hours, mins, secs;
                    Duration timeDuration = media.getDuration();
                    time = (int) (timeDuration.toSeconds());// it will cut .898956
                    //String stringTime = String.format("%02d:%02d:%02d", hours, mins, secs);

                    txtField_time.setText(songModel.sec_To_Format(time));
                }
            });
        }
    }

    @FXML
    private void handle_saveSong(ActionEvent event) throws InterruptedException, IOException {
        if (!edit) {
            songModel.createSong(txtField_title.getText().trim(),
                    txtField_artist.getText().trim(),
                    songModel.format_To_Sec(txtField_time.getText()),
                    choiceBox_genre.getSelectionModel().getSelectedItem(),
                    txtField_filePath.getText());
        } else {
            songModel.updateSong(songToEdit,
                    txtField_title.getText().trim(),
                    txtField_artist.getText().trim(),
                    choiceBox_genre.getSelectionModel().getSelectedItem(),
                    songModel.format_To_Sec(txtField_time.getText()),
                    txtField_filePath.getText());
        }

        refreshLibrary();

        Stage stage;
        stage = (Stage) btn_confirm.getScene().getWindow();
        stage.close();
    }

    /**
     * Enables the edit mode, so the song can be edited. The existing info of
     * the selected song is displayed.
     *
     * @param selectedSong The song to be edited.
     */
    public void editMode(Song selectedSong) {
        edit = true;
        songToEdit = selectedSong;

        //sets the existing info of the selected song.
        txtField_title.setText(songToEdit.getTitle());
        txtField_artist.setText(songToEdit.getArtist());
        txtField_time.setText(songToEdit.getStringTime());
        txtField_filePath.setText(songToEdit.getPath());
        choiceBox_genre.setValue(songToEdit.getGenre());
    }

    @FXML
    private void handle_cancelScene(ActionEvent event) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
}