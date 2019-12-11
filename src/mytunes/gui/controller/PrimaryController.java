//  working primary controller
package mytunes.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
//import java.time.Duration;
import javafx.util.Duration;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mytunes.be.Playlist;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongOnPlaylist;
import mytunes.gui.model.PlaylistModel;
import mytunes.gui.model.SongModel;
import mytunes.gui.model.SongOnPlaylistModel;

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
    private TableColumn<Playlist, String> col_PTime;
    @FXML
    private TableColumn<Song, String> col_title;
    @FXML
    private TableColumn<Song, String> col_artist;
    @FXML
    private TableColumn<Song, String> col_genre;
    @FXML
    private TableColumn<Song, String> col_songTime;

    @FXML
     TableView<Playlist> tbv_Playlists;
    @FXML
    private TableColumn<Playlist, Integer> col_PSongs;
    @FXML
    private TableColumn<Playlist, String> col_PName;
    @FXML
    TableView<Song> tbv_Library;
    @FXML
    private ListView<Song> lv_SongsOnPlaylist;
    @FXML
    private Label lbl_Library;
    @FXML
    private TextField txtSongSearch;
    @FXML
    private Button btn_deletePlaylist;
    @FXML
    private Button btn_createSong;

    private boolean isPaused = false;
    private int currentSongPlaying = 0;
    private Song song;
    private ObservableList<Playlist> observableListPlaylist;
    private ObservableList<Song> observableListSong;
    //private ObservableList<Playlist> observableListPlaylist;
    private ObservableList<Song> ObservableListSongOnPlaylist;
    private MediaPlayer mediaPlayer;
    private SongModel songModel;
    private PlaylistModel playlistModel;
    private SongOnPlaylistModel SongOnPlaylistModel;
    private Playlist playlist;
    
    
    
    
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingTableViews();
        setSearchFilter();
    }

    private void settingTableViews() {
        songModel = new SongModel();
        playlistModel = new PlaylistModel();

        //  Library table view
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_songTime.setCellValueFactory(new PropertyValueFactory<>("stringTime"));
        //  Playlist table view
        col_PName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PSongs.setCellValueFactory(new PropertyValueFactory<>("numberOfSongs"));
        col_PTime.setCellValueFactory(new PropertyValueFactory<>("stringTime"));
        //  displaying content
        tbv_Library.setItems(songModel.getLibraryList());
        tbv_Playlists.setItems(playlistModel.getPlaylistList());

        
    }

    private void setSearchFilter() {
        //Set the filter Predicate when the filter changes. Any changes to the
        //search textfield activates the filter.
        txtSongSearch.textProperty().addListener((obs, oldVal, newVal) -> {
            songModel.filteredSongs(newVal);
        });
    }

    private Duration currentTime;
    private boolean isScheduelSong = true;

    public void play() throws IOException {

        if (isPaused == true && isScheduelSong == false) {
            currentTime = mediaPlayer.getCurrentTime();
            mediaPlayer.setStartTime(currentTime);
            mediaPlayer.play();
            isPaused = false;
        }else{
            if (mediaPlayer != null && isPaused == false && isScheduelSong == false) {
                mediaPlayer.pause();
                isPaused = true;
            }else {
                
            if (lv_SongsOnPlaylist.getSelectionModel().getSelectedItems() != null && lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex() != -1) {
                currentSongPlaying = lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex();
                lv_SongsOnPlaylist.getSelectionModel().clearSelection();
                isScheduelSong=false;
            }
                mediaPlayer = new MediaPlayer(new Media(new File(lv_SongsOnPlaylist.getItems().get(currentSongPlaying).getPath()).toURI().toString()));

                mediaPlayer.setVolume(slider.getValue());
                mediaPlayer.play();
                isPaused = false;

                lbl_Library.setText(lv_SongsOnPlaylist.getItems().get(currentSongPlaying).getTitle() + " is now playing");

                mediaPlayer.setOnEndOfMedia(() -> {

                    if (lv_SongsOnPlaylist.getItems().size() == currentSongPlaying + 1 || currentSongPlaying == -1) {
                        currentSongPlaying = 0;

                    } else {
                        currentSongPlaying++;

                    }
                    mediaPlayer = null;
                    try {
                        play();
                    } catch (IOException ex) {
                        Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });

            }
        }

    }

    @FXML
    private void handle_play(ActionEvent event) throws IOException {
        play();
    }

    @FXML
    private void handle_addPlaylist(ActionEvent event) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/CreatePlaylistScene.fxml"));
        root1 = (Parent) fxmlLoader.load();
        //Parent rootSong = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/CreatePlaylistScene.fxml"));
        fxmlLoader.<CreatePlaylistSceneController>getController().setContr(this);
        
        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(root1);

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
    private void handle_createSong(ActionEvent event) throws IOException {
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

    public void updatePlaylists() {
        tbv_Playlists.getItems().clear();
        tbv_Playlists.setItems(playlistModel.getPlaylistList());
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
    private void handle_getPlaylist(MouseEvent event) {

        if (tbv_Playlists.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Song> songsInPlaylist = FXCollections.observableArrayList();
            songsInPlaylist.addAll(tbv_Playlists.getSelectionModel().getSelectedItem().getSongs());
            lv_SongsOnPlaylist.setItems(songsInPlaylist);
            
        }

    }

    @FXML
    private void handle_deleteSong(ActionEvent event) throws IOException {
        //move code to controller
        /*Song selectedSong = tbv_Library.getSelectionModel().getSelectedItem();
        songModel.deleteSong(selectedSong);
        tbv_Library.getSelectionModel().clearSelection();
         */
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/DeleteSongScene.fxml"));
        root1 = (Parent) fxmlLoader.load();
        //Parent rootSong = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/DeleteSongScene.fxml"));
        fxmlLoader.<DeleteSongSceneController>getController().setContr(this);

        Stage songStage = new Stage();
        Scene songScene = new Scene(root1);

        //songStage.initStyle(StageStyle.UNDECORATED);
        songStage.setScene(songScene);
        songStage.show();
    }

    private void btn_shuffleAction(ActionEvent event) {

        lv_SongsOnPlaylist.getItems().clear();
        if (tbv_Playlists.getSelectionModel().getSelectedItem() != null) {
            //songsInPlaylist.getItems().clear();
            List<Song> IDK = tbv_Playlists.getSelectionModel().getSelectedItem().getSongs();

            Collections.shuffle(IDK);
            ObservableList<Song> allOverPower = FXCollections.observableArrayList();

            allOverPower.addAll(FXCollections.observableArrayList(IDK));
            lv_SongsOnPlaylist.setItems(allOverPower);

        }

    }

    @FXML
    private void setSlider(MouseEvent event) {

        if (mediaPlayer != null) {
            System.out.println(slider.getValue());
            mediaPlayer.setVolume(slider.getValue() * 100);
            mediaPlayer.setVolume(slider.getValue() / 100);

        }

    }

    private void btn_loopAction(MouseEvent event) {

        if (mediaPlayer != null) {
        }
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.getOnEndOfMedia();

    }

    @FXML
    private void handle_deletePlaylist(ActionEvent event) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/DeletePlaylistScene.fxml"));
        root1 = (Parent) fxmlLoader.load();
        //Parent rootSong = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/DeletePlaylistScene.fxml"));
        fxmlLoader.<DeletePlaylistSceneController>getController().setContr(this);
        
        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(root1);

        //songStage.initStyle(StageStyle.UNDECORATED);
        playlistStage.setScene(playlistScene);
        playlistStage.show();
    }

    private void MoveSongDown(ActionEvent event) {

        int index = lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex();
        if (index != lv_SongsOnPlaylist.getItems().size() - 1) {

            // swap items            
            lv_SongsOnPlaylist.getItems().add(index + 1, lv_SongsOnPlaylist.getItems().remove(index));
            // select item at new position
            lv_SongsOnPlaylist.getSelectionModel().clearAndSelect(index + 1);

        }

    }

    private void MoveSongUp(ActionEvent event) {

        int index = lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex();
        if (index != 0) {
            // swap items
            lv_SongsOnPlaylist.getItems().add(index - 1, lv_SongsOnPlaylist.getItems().remove(index));
            // select item at new position
            lv_SongsOnPlaylist.getSelectionModel().clearAndSelect(index - 1);
        }
    }

    @FXML
    private void handle_Previous(ActionEvent event) throws IOException {

        if (lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex() != -1) {
            mediaPlayer.stop();
            if (currentSongPlaying - 1 == -1) {
                currentSongPlaying = 0;
            } else {
                currentSongPlaying--;
            }
            play();
        }
    }

    @FXML
    private void handle_Next(ActionEvent event) throws IOException {

        if (lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex() != -1) {
            mediaPlayer.stop();
            if (currentSongPlaying + 1 == lv_SongsOnPlaylist.getItems().size()) {
                currentSongPlaying = 0;
            } else {
                currentSongPlaying++;
            }
            play();
        }
    }

    @FXML
    private void MoveDownSong(ActionEvent event) {
    }

    @FXML
    private void MoveUpSong(ActionEvent event) {
    }

    @FXML
    private void scheduleSong(MouseEvent event) {

        if (lv_SongsOnPlaylist.getSelectionModel().getSelectedItem() != null) {
            isScheduelSong = true;
        }
    }

}
