package mytunes.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.gui.model.PlaylistModel;

/**
 * The PlaylistSceneController is the controller for the PlaylistScene. It sends
 * requests to the PlaylistModel when creating and updating a playlist. The
 * default mode is to create a playlist. The mode can be changed to edit a
 * playlist.
 *
 * @author Anne Luong
 */
public class PlaylistSceneController implements Initializable {

    @FXML
    private TextField txtField_name;
    @FXML
    private Button btn_confirm;
    @FXML
    private Button btn_cancel;

    private boolean edit;
    private Playlist playlistToEdit;
    private PlaylistModel playlistModel;
    private PrimaryController pCon;

    /**
     * Initializes the controller class. Upon initialization, the mode is set to
     * create a playlist.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edit = false;
        playlistModel = new PlaylistModel();
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
     * Checks the selected mode (new or edit) and saves the playlist.
     */
    @FXML
    private void handle_savePlaylist(ActionEvent event) {
        if (!edit) {
            String name = txtField_name.getText().trim();
            playlistModel.createPlaylist(0, name);
        } else {
            playlistModel.updatePlaylist(playlistToEdit, txtField_name.getText());
        }

        updatePlaylists();

        Stage stage;
        stage = (Stage) btn_confirm.getScene().getWindow();
        stage.close();
    }

    /**
     * Updates the library to reflect the changes of a new playlist or an edited
     * playlist.
     */
    private void updatePlaylists() {
        pCon.refreshPlaylists();
    }

    /**
     * Enables the edit mode, so the playlist can be edited. The existing info
     * of the selected playlist is displayed.
     *
     * @param selectedPlaylist The playlist to be edited.
     */
    public void editMode(Playlist selectedPlaylist) {
        edit = true;
        playlistToEdit = selectedPlaylist;

        //sets the existing info of the selected playlist.
        txtField_name.setText(playlistToEdit.getName());
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
