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
 *
 * @author annem
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

    public void setContr(PrimaryController pCon) {
        this.pCon = pCon;
    }
    
    private void refreshPlaylists() {
        pCon.refreshPlaylists();
    }
    
    @FXML
    private void handle_deletePlaylist(ActionEvent event) {
        //Deletes the selected playlist from the database, but the library is not showing properly(?).
        playlistModel.deletePlaylist(selectedPlaylist);
        
        refreshPlaylists();
        Stage stage;
        stage = (Stage) btn_confirm.getScene().getWindow();
        stage.close();
    }
    
    public void setDeletePlaylistLabel(Playlist playlist){
        selectedPlaylist = playlist;
        lbl_name.setText(selectedPlaylist.getName());
    }

    @FXML
    private void handle_cancelScene(ActionEvent event) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }    
}