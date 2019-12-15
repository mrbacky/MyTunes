/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class PlaylistSceneController implements Initializable {

    @FXML
    private TextField txtField_namePlaylist;
    @FXML
    private Button btn_cancelPlaylist;
    @FXML
    private Button btn_savePlaylist;
        
    private boolean edit;
    private Playlist playlistToEdit;
    private PlaylistModel playlistModel;
    private PrimaryController pCon;

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
     
    @FXML
    //RENAME!! Now the functionality can be either create or update.
    private void handle_CreatePlaylist(ActionEvent event) {
        if (!edit) {
            String name = txtField_namePlaylist.getText().trim();
            playlistModel.createPlaylist(0, name, 0);
        } else {
            playlistModel.updatePlaylist(playlistToEdit, txtField_namePlaylist.getText());
        }

        updatePlaylists();

        Stage stage;
        stage = (Stage) btn_savePlaylist.getScene().getWindow();
        stage.close();
    }
    
    private void updatePlaylists() {
        pCon.refreshPlaylists();
    }

    public void editMode(Playlist playlist) {
        edit = true;
        playlistToEdit = playlist;
        
        //sets the existing info of the selected playlist.
        txtField_namePlaylist.setText(playlistToEdit.getName());
    }
    @FXML
    private void handle_ClosePlaylistScene(ActionEvent event) {
        Stage stage = (Stage) btn_cancelPlaylist.getScene().getWindow();
        stage.close();
    }    
}
