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
 * Controller class for the delete song confirmation pop-up.
 *
 * @author annem
 */


public class DeleteSongSceneController implements Initializable {

    @FXML
    private Label lbl_SongTitleToDelete;
    @FXML
    private Button btn_ConfirmDeleteSong;
    @FXML
    private Button btn_DeleteSong_cancelSong;

    private PrimaryController pCon;
    private SongModel songModel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songModel = new SongModel();

        
    }

    public void setContr(PrimaryController pCon) {
        this.pCon = pCon;
    }
    
    private void updateLibrary() {
        pCon.refreshLibrary();
        pCon.updateSongOnPlaylist();
        pCon.refreshPlaylists();
    }
    
    @FXML
    private void handle_deleteSong(ActionEvent event) {
        //Deletes from the database, but the library is not showing properly.
        Song selectedSong = this.pCon.tbv_Library.getSelectionModel().getSelectedItem();
        songModel.deleteSong(selectedSong);
        updateLibrary();
        Stage stage;
        stage = (Stage) btn_ConfirmDeleteSong.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handle_CloseScene(ActionEvent event) {
        Stage stage = (Stage) btn_DeleteSong_cancelSong.getScene().getWindow();
        stage.close();
    }
}
