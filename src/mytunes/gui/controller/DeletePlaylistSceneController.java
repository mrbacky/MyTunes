package mytunes.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.gui.model.PlaylistModel;

/**
 * Controller class for the DeletePlaylistScene. It sends requests to the
 * PlaylistModel when deleting a song.
 *
 * @author Anne Luong
 */
public class DeletePlaylistSceneController implements Initializable {

    @FXML
    private Label lbl_name;
    @FXML
    private Button btn_confirm;
    @FXML
    private Button btn_cancel;

    private PrimaryController pCon;
    private PlaylistModel playlistModel;
    private Playlist selectedPlaylist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
     * Updates the playlist table view to reflect the changes of a deleted
     * playlist.
     */
    private void refreshPlaylists() {
        pCon.refreshPlaylists();
    }

    /**
     * Deletes a playlist.
     */
    @FXML
    private void handle_deletePlaylist(ActionEvent event) {
        //Deletes the selected playlist from the database, but the the list view
        //(which shows the songs on the playlist) does not disappear.
        playlistModel.deletePlaylist(selectedPlaylist);

        refreshPlaylists();
        Stage stage;
        stage = (Stage) btn_confirm.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the title of the selected playlist on a label.
     *
     * @param playlist The playlist to be deleted.
     */
    public void setDeletePlaylistLabel(Playlist playlist) {
        selectedPlaylist = playlist;
        lbl_name.setText(selectedPlaylist.getName());
    }

    /**
     * Closes the stage.
     */
    @FXML
    private void handle_cancelScene(ActionEvent event) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
}
