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

public class PrimaryController implements Initializable {
    
    private Label label;
    @FXML
    private Button btn_addPlaylist;
    @FXML
    private Button btn_deletePlaylist;
    @FXML
    private Button btn_addSongToPlaylist;
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_previous;
    @FXML
    private Button btn_next;
    @FXML
    private Button btn_downSong;
    @FXML
    private Button btn_upSong;
    @FXML
    private Button btn_editPlaylist;
    @FXML
    private Button btn_deletePlaylist1;
    @FXML
    private Button btn_addPlaylist1;
    @FXML
    private Button btn_deletePlaylist2;
    @FXML
    private Button btn_editPlaylist1;
    @FXML
    private Button btn_editPlaylist11;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}