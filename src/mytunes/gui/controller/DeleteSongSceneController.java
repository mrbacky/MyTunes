package mytunes.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mytunes.be.Song;
import mytunes.gui.model.SongModel;

/**
 * Controller class for the DeleteSongScene. It sends requests to the SongModel
 * when deleting a song.
 *
 * @author Anne Luong
 */
public class DeleteSongSceneController implements Initializable {

    @FXML
    private Label lbl_title;
    @FXML
    private Button btn_confirm;
    @FXML
    private Button btn_cancel;

    private PrimaryController pCon;
    private SongModel songModel;
    private Song selectedSong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songModel = new SongModel();
    }

    /**
     * Sets the controller for the PrimaryScene.
     *
     * @param pCon PrimaryController
     */
    public void setContr(PrimaryController pCon) {
        this.pCon = pCon;
    }

    /**
     * Updates the library to reflect the changes of a deleted song.
     */
    private void updateLibrary() {
        pCon.refreshLibrary();
        pCon.updateSongOnPlaylist();
        pCon.refreshPlaylists();
    }

    /**
     * Deletes a song.
     */
    @FXML
    private void handle_deleteSong(ActionEvent event) {
        //Deletes the selected song from the database.
        songModel.deleteSong(selectedSong);
        updateLibrary();
        Stage stage;
        stage = (Stage) btn_confirm.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the title of the selected song on a label.
     *
     * @param song The song to be deleted.
     */
    public void setDeleteSongLabel(Song song) {
        selectedSong = song;
        lbl_title.setText(selectedSong.getTitle());
    }

    /**
     * Closes the stage.
     */
    @FXML
    private void handle_closeScene(ActionEvent event) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
}
