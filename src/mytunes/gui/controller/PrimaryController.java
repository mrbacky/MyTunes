
package mytunes.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mytunes.be.Song;
import mytunes.gui.model.SongModel;


public class PrimaryController implements Initializable {

    @FXML
    private Button btn_newPlaylist;
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
    private Button btn_deleteSongFromPlaylist;
    @FXML
    private Button btn_deleteSong;
    @FXML
    private Button btn_editSong;
    @FXML
    private Button btn_editPlaylist11;
    @FXML
    private Slider slider;
    @FXML
    private ProgressBar progressBar;

    private ObservableList<Song> observableListSong;
    
    private SongModel songModel;
    @FXML
    private TableColumn<Song, ?> col_Pname;
    @FXML
    private TableColumn<Song, ?> col_pSongs;// will add Playlist be
    @FXML
    private TableColumn<Song, ?> col_PTime;
    @FXML
    private TableColumn<Song, String> col_title;
    @FXML
    private TableColumn<Song, String> col_artist;
    @FXML
    private TableColumn<Song, String> col_genre;
    @FXML
    private TableColumn<Song, Integer> col_songTime;
    @FXML
    private TableView<Song> tbv_Songs;
    //@FXML
    //private Button btn_addSong;
    @FXML
    private Button btn_addSong;
    
    public PrimaryController() {
        songModel = new SongModel();
    }
    private MediaPlayer mediaPlayer;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // observableListSong = songModel.getAllSongs();
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_songTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tbv_Songs.setItems(observableListSong);
    }
    
    public void play(){
        mediaPlayer = new MediaPlayer(new Media(new File(tbv_Songs.getItems().get(0).getPath()).toURI().toString()));
        mediaPlayer.play();
        
    }
    
    
    
    
       


    @FXML
    private void handle_play(ActionEvent event) {
        play();
    
    }

  
    @FXML
    private void handle_AddSong(ActionEvent event) throws IOException {
        Parent rootSong = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/SongScene.fxml"));
        
        Stage songStage = new Stage();
        Scene songScene = new Scene(rootSong);
        //songStage.initStyle(StageStyle.UNDECORATED);
        
        
        songStage.setScene(songScene);
        songStage.show();
        
        

    
    }

    
    
    
    
    
}
