package mytunes.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mytunes.be.Playlist;
import mytunes.be.PlaylistPowerMove;
import mytunes.be.Song;
import mytunes.gui.model.PlaylistModel;
import mytunes.gui.model.SongModel;

public class PrimaryController implements Initializable {

    @FXML
    private Button btn_newPlaylist;
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
    private Button btn_editSong;
    @FXML
    private Button btn_editPlaylist11;
    @FXML
    private Slider slider;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TableColumn<Playlist, Integer> col_PTime;
    @FXML
    private TableColumn<Song, String> col_title;
    @FXML
    private TableColumn<Song, String> col_artist;
    @FXML
    private TableColumn<Song, String> col_genre;
    @FXML
    private TableColumn<Song, Integer> col_songTime;
    @FXML
    private Button btn_addSong;
    @FXML
    private Button btn_next11;
    @FXML
    private TableView<PlaylistPowerMove> tbv_Playlists;
    @FXML
    private Button btn_deleteSong1;
    @FXML
    private TableColumn<PlaylistPowerMove, Integer> col_PSongs;
    @FXML
    private TableColumn<PlaylistPowerMove, String> col_PName;

    private ObservableList<Song> observableListSong;
    private ObservableList<PlaylistPowerMove> observableListPlaylist;
    private MediaPlayer mediaPlayer;
    private SongModel songModel;
    private PlaylistModel playlistModel;
    private Song song;
    @FXML
    private TableView<Song> tbv_Library;
    @FXML
    private Label lbl_Library;
    private AddSongSceneController addSongSceneController;
    @FXML
    private Button btn_loop;
    @FXML
    private ImageView btn_shuffle;
    @FXML
    private ListView<Song> songsInPlaylist;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        addSongSceneController = new AddSongSceneController();
        settingTableViews();
    }

    private void settingTableViews() {
        songModel = new SongModel();
        playlistModel = new PlaylistModel();

        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_songTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        col_PName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PSongs.setCellValueFactory(new PropertyValueFactory<>("songs"));
        col_PTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        tbv_Library.setItems(songModel.getLibraryList());
        //tbv_Playlists.setItems(playlistModel.getPlaylists());
        System.out.println(playlistModel.getPowrPlaylists());
        tbv_Playlists.setItems(playlistModel.getPowrPlaylists());

    }

    public void play() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        
        mediaPlayer = new MediaPlayer(new Media(new File(song.getPath()).toURI().toString()));

        mediaPlayer.play();
      

    }

    @FXML
    private void handle_play(ActionEvent event) {
        play();
        

    }

    @FXML
    private void handle_addPlaylist(ActionEvent event) throws IOException {
        Parent rootPlaylist = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/CreatePlaylistScene.fxml"));
        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(rootPlaylist);
        //songStage.initStyle(StageStyle.UNDECORATED);
        playlistStage.setScene(playlistScene);
        playlistStage.show();
    }

    @FXML
    private void handle_editPlaylist(ActionEvent event) throws IOException {
        Parent rootPlaylist = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/EditPlaylistScene.fxml"));
        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(rootPlaylist);
        //songStage.initStyle(StageStyle.UNDECORATED);
        playlistStage.setScene(playlistScene);
        playlistStage.show();
    }

    @FXML
    private void handle_AddSong(ActionEvent event) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/AddSongScene.fxml"));
        root1 = (Parent) fxmlLoader.load();
        //Parent rootSong = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/AddSongScene.fxml"));
        fxmlLoader.<AddSongSceneController>getController().setContr(this);

        Stage songStage = new Stage();
        Scene songScene = new Scene(root1);

        //songStage.initStyle(StageStyle.UNDECORATED);
        songStage.setScene(songScene);
        songStage.show();

    }

    public void updateLibrary() {

        tbv_Library.getItems().clear();
        tbv_Library.setItems(songModel.getLibraryList());
    }

    @FXML
    private void handle_EditSong(ActionEvent event) throws IOException {
        Parent rootSong = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/EditSongScene.fxml"));
        Stage songStage = new Stage();
        Scene songScene = new Scene(rootSong);
        //songStage.initStyle(StageStyle.UNDECORATED);
        songStage.setScene(songScene);
        songStage.show();

    }

    @FXML
    private void handle_getSong(MouseEvent event) {
        song = tbv_Library.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void btn_loopAction(MouseEvent event) {
       Random r = new Random();
       
       tbv_Library.getSelectionModel().clearAndSelect(r.nextInt(tbv_Library.getItems().size()));
       mediaPlayer.play();

    }

   

    @FXML
    private void selectPlaylist(MouseEvent event) {

        if (tbv_Playlists.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Song> allOverPower = FXCollections.observableArrayList();

            allOverPower.addAll(FXCollections.observableArrayList(tbv_Playlists.getSelectionModel().getSelectedItem().getSongs()));
            songsInPlaylist.setItems(allOverPower);
        }

    }

    @FXML
    private void btn_shuffleAction(ActionEvent event) {
        
  songsInPlaylist.getItems().clear();
        if (tbv_Playlists.getSelectionModel().getSelectedItem() != null) {
      //songsInPlaylist.getItems().clear();
            List<Song> IDK =tbv_Playlists.getSelectionModel().getSelectedItem().getSongs();
            
        Collections.shuffle(IDK);
                    ObservableList<Song> allOverPower = FXCollections.observableArrayList();

            allOverPower.addAll(FXCollections.observableArrayList(IDK));
            songsInPlaylist.setItems(allOverPower);
        
        }
    
    }

     

    @FXML
    private void setSlider(MouseEvent event) {
        
        if(mediaPlayer != null){
             mediaPlayer.setVolume(slider.getValue());
             
        }
    
    }

    @FXML
    private void handle_Previous(ActionEvent event) {
        
        if(mediaPlayer != null){
            mediaPlayer.dispose();
    }
        
  }
    
}  
