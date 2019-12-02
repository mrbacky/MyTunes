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
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.gui.model.PlaylistModle;
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
    private ObservableList<Playlist> observableListPlaylist;
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
    private TableView<Song> tbv_Songs;

    @FXML
    private Button btn_addSong;

    
    private MediaPlayer mediaPlayer;
    private SongModel songModel;
    private PlaylistModle playModel;
    @FXML
    private Button btn_next1;
    @FXML
    private Button btn_next11;
    @FXML
    private TableView<Playlist> tbv_Playlists;
    @FXML
    private Button btn_deleteSong1;
    @FXML
    private TableColumn<Playlist, Integer> col_PSongs;
    @FXML
    private TableColumn<Playlist, String> col_PName;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songModel = new SongModel();
        playModel = new PlaylistModle();
        
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_songTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        col_PName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PSongs.setCellValueFactory(new PropertyValueFactory<>("songs"));
        col_PTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        
        
        tbv_Playlists.setItems(playModel.getPlaylist());
        tbv_Songs.setItems(songModel.getSongList());
        
    }

    public void play() {
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

    @FXML
    private void handle_EditSong(ActionEvent event) throws IOException {
        Parent rootSong = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/SongScene.fxml"));
        Stage songStage = new Stage();
        Scene songScene = new Scene(rootSong);
        //songStage.initStyle(StageStyle.UNDECORATED);
        songStage.setScene(songScene);
        songStage.show();
    }

    @FXML
    private void handle_addPlaylist(ActionEvent event) throws IOException {
        Parent rootPlaylist = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/PlaylistScene.fxml"));
        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(rootPlaylist);
        //songStage.initStyle(StageStyle.UNDECORATED);
        playlistStage.setScene(playlistScene);
        playlistStage.show();
    }

    @FXML
    private void handle_editPlaylist(ActionEvent event) throws IOException {
        Parent rootPlaylist = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/PlaylistScene.fxml"));
        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(rootPlaylist);
        //songStage.initStyle(StageStyle.UNDECORATED);
        playlistStage.setScene(playlistScene);
        playlistStage.show();
    }

}
