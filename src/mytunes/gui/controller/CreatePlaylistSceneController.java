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

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class CreatePlaylistSceneController implements Initializable {

    @FXML
    private TextField txtField_namePlaylist;
    @FXML
    private Button btn_cancelPlaylist;
    @FXML
    private Button btn_savePlaylist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_ClosePlaylistScene(ActionEvent event) {
    
    }

    @FXML
    private void handle_CreatePlaylist(ActionEvent event) {
    }
    
}
