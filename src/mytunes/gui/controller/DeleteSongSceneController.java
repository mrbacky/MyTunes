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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mytunes.be.Song;
import mytunes.gui.model.SongModel;

public class DeleteSongSceneController implements Initializable {

    @FXML
    private Label lbl_SongTitleToDelete;
    @FXML
    private Button btn_ConfirmDeleteSong;
    @FXML
    private Button btn_DeleteSong_cancelSong;
    
    private SongModel songModel;
    private Song songToDelete;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songModel = new SongModel();
    }    

    private PrimaryController pCon;

    void setContr(PrimaryController pCon) {
        this.pCon = pCon;
    }
    
    @FXML
    private void handle_deleteSong(ActionEvent event) {
        songModel.deleteSong(songToDelete);
        updateLibrary();
        Stage stage;
        stage = (Stage) btn_ConfirmDeleteSong.getScene().getWindow();
        stage.close();
    }
    
    public void updateLibrary() {
        pCon.updateLibrary();
    }

    @FXML
    private void handle_CloseScene(ActionEvent event) {
        Stage stage = (Stage) btn_DeleteSong_cancelSong.getScene().getWindow();
        stage.close();
    }
    
}
