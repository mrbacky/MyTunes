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
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;
import mytunes.gui.model.PlaylistModel;
import mytunes.gui.model.SongModel;
import mytunes.gui.model.SongOnPlaylistModle;

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

    private ObservableList<Song> observableListSong;
    private ObservableList<Playlist> observableListPlaylist;
    private ObservableList<SongOnPlaylist>ObservableListSongOnPlaylist;
    private MediaPlayer mediaPlayer;
    private SongModel songModel;
    private PlaylistModel playlistModel;
    private Song song;
    private SongOnPlaylistModle SongOnPlaylistModle;
    @FXML
    private TableView<Song> tbv_Library;
    @FXML
    private Label lbl_Library;
    @FXML
    private ListView<SongOnPlaylist> LV_SongsOnPlaylist;
    
    private AddSongSceneController addSongSceneController;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        addSongSceneController = new AddSongSceneController();
        settingTableViews();
    }

    private void settingTableViews() {
        songModel = new SongModel();
        playlistModel = new PlaylistModel();
        SongOnPlaylistModle = new SongOnPlaylistModle();

        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_songTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        col_PName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PSongs.setCellValueFactory(new PropertyValueFactory<>("songs"));
        col_PTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        tbv_Library.setItems(songModel.getLibraryList());
        tbv_Playlists.setItems(playlistModel.getPlaylists());
        LV_SongsOnPlaylist.setItems(SongOnPlaylistModle.getSongOnPlaylist());
    }

    public void play() {
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

}
