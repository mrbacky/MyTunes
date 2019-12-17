package mytunes.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.util.Duration;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.gui.model.PlaylistModel;
import mytunes.gui.model.SongModel;
import mytunes.gui.model.SongOnPlaylistModel;

public class PrimaryController implements Initializable {

    @FXML
    private Button btn_createSong;
    @FXML
    private Button btn_editSong;
    @FXML
    private Button btn_deleteSong;
    @FXML
    private Button btn_newPlaylist;
    @FXML
    private Button btn_editPlaylist;
    @FXML
    private Button btn_deletePlaylist;    
    @FXML
    private Button btn_addSongToPlaylist;
    @FXML
    private Button btn_deleteSongFromPlaylist;
    @FXML
    private Button btn_moveSongUp;
    @FXML
    private Button btn_moveSongDown;    
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_next;
    @FXML
    private Button btn_previous;
    @FXML
    private Button btn_loop;
    @FXML
    private Slider slider;
 
    
    @FXML
    public TableView<Song> tbv_Library;
    @FXML
    private TableColumn<Song, String> col_title;
    @FXML
    private TableColumn<Song, String> col_artist;
    @FXML
    private TableColumn<Song, String> col_genre;
    @FXML
    private TableColumn<Song, String> col_songTime;
   
    @FXML
    public TableView<Playlist> tbv_Playlists;
    @FXML
    private TableColumn<Playlist, String> col_PName;
    @FXML
    private TableColumn<Playlist, Integer> col_PSongs;
    @FXML
    private TableColumn<Playlist, String> col_PTime;
    
    @FXML
    public ListView<Song> lv_SongsOnPlaylist;
   
    @FXML
    private Label lbl_Library;
    @FXML
    private Label lbl_SelectedPlaylist;
    @FXML
    private TextField txtSongSearch;
    
    private boolean isPaused = false;
    private int currentSongPlaying = 0;
    private Duration currentTime;
    private boolean isScheduelSong = true;
    private MediaPlayer mediaPlayer;

    private Song song;
    private Playlist playlist;
    private SongModel songModel;
    private PlaylistModel playlistModel;
    private SongOnPlaylistModel SongOnPlaylistModel;
    
    //WHAT IS THIS BUTTON?!
    @FXML
    private Button btn_shuffle;
    

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

    //__________________________________________________________________________                       
    //
    //      Library
    //__________________________________________________________________________
    @FXML
    private void handle_createSong(ActionEvent event) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/SongScene.fxml"));
        root1 = (Parent) fxmlLoader.load();
        fxmlLoader.<SongSceneController>getController().setContr(this);

        Stage songStage = new Stage();
        Scene songScene = new Scene(root1);

        //songStage.initStyle(StageStyle.UNDECORATED);
        songStage.setScene(songScene);
        songStage.show();
    }

    @FXML
    private void handle_getSong(MouseEvent event) { // pick  selected song
        song = tbv_Library.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handle_EditSong(ActionEvent event) throws IOException {
        Song selectedSong = tbv_Library.getSelectionModel().getSelectedItem();

        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/SongScene.fxml"));
        root = (Parent) fxmlLoader.load();
        //Parent rootSong = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/AddSongScene.fxml"));
        SongSceneController controller = (SongSceneController) fxmlLoader.getController();
        controller.setContr(this);
        controller.editMode(selectedSong); //set mode to edit song.
        Stage songStage = new Stage();
        Scene songScene = new Scene(root);

        //songStage.initStyle(StageStyle.UNDECORATED);
        songStage.setScene(songScene);
        songStage.show();
    }

    @FXML
    private void handle_deleteSong(ActionEvent event) throws IOException { // deletion of songs
        Song selectedSong = tbv_Library.getSelectionModel().getSelectedItem();
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/DeleteSongScene.fxml"));
        root = (Parent) fxmlLoader.load();
        DeleteSongSceneController controller = (DeleteSongSceneController) fxmlLoader.getController();
        controller.setContr(this);
        controller.setDeleteSongLabel(selectedSong);
        
        Stage songStage = new Stage();
        Scene songScene = new Scene(root);

        //songStage.initStyle(StageStyle.UNDECORATED);
        songStage.setScene(songScene);
        songStage.show();
    }

    public void refreshLibrary() {
        tbv_Library.getItems().clear();
        tbv_Library.setItems(songModel.getLibraryList());
    }

    //__________________________________________________________________________                       
    //
    //      Playlists
    //__________________________________________________________________________
    @FXML
    private void handle_createPlaylist(ActionEvent event) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/PlaylistScene.fxml"));
        root1 = (Parent) fxmlLoader.load();
        fxmlLoader.<PlaylistSceneController>getController().setContr(this);

        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(root1);

        //songStage.initStyle(StageStyle.UNDECORATED);
        playlistStage.setScene(playlistScene);
        playlistStage.show();
    }

    @FXML
    private void handle_getPlaylist(MouseEvent event) {
        Playlist selectedPlaylist = tbv_Playlists.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            getSongsInPlaylist();
            lbl_SelectedPlaylist.setText(selectedPlaylist.getName());
        }
    }

    @FXML
    private void handle_editPlaylist(ActionEvent event) throws IOException {
        Playlist selectedPlaylist = tbv_Playlists.getSelectionModel().getSelectedItem();
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/PlaylistScene.fxml"));
        root = (Parent) fxmlLoader.load();
        PlaylistSceneController controller = (PlaylistSceneController) fxmlLoader.getController();
        controller.setContr(this);
        controller.editMode(selectedPlaylist);

        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(root);

        //songStage.initStyle(StageStyle.UNDECORATED);
        playlistStage.setScene(playlistScene);
        playlistStage.show();
    }

    @FXML
    private void handle_deletePlaylist(ActionEvent event) throws IOException {
        Playlist selectedPlaylist = tbv_Playlists.getSelectionModel().getSelectedItem();
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/DeletePlaylistScene.fxml"));
        root = (Parent) fxmlLoader.load();
        DeletePlaylistSceneController controller = (DeletePlaylistSceneController) fxmlLoader.getController();
        controller.setContr(this);
        controller.setDeletePlaylistLabel(selectedPlaylist);

        Stage playlistStage = new Stage();
        Scene playlistScene = new Scene(root);

        //songStage.initStyle(StageStyle.UNDECORATED);
        playlistStage.setScene(playlistScene);
        playlistStage.show();
    }

    //__________________________________________________________________________                       
    //
    //      Songs on Playlist
    //__________________________________________________________________________
    @FXML
    private void handle_AddSongToPlaylist(ActionEvent event) {
        Playlist selectedPlaylist = tbv_Playlists.getSelectionModel().getSelectedItem();
        Song selectedSong = tbv_Library.getSelectionModel().getSelectedItem();
        playlistModel.addSongToPlaylist(selectedPlaylist, selectedSong);
        updateSongOnPlaylist();
        refreshPlaylists();
    }

    private void getSongsInPlaylist() {
        ObservableList<Song> songsInPlaylist = FXCollections.observableArrayList();
        songsInPlaylist.clear();
        songsInPlaylist.addAll(tbv_Playlists.getSelectionModel().getSelectedItem().getSongs());

        lv_SongsOnPlaylist.setItems(songsInPlaylist);
    }

    public void refreshPlaylists() {
        tbv_Playlists.getItems().clear();
        tbv_Playlists.setItems(playlistModel.getPlaylistList());
    }

    @FXML
    private void handle_deleteSongFromPlaylst(ActionEvent event) {
        Song selectedSong = lv_SongsOnPlaylist.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tbv_Playlists.getSelectionModel().getSelectedItem();
        playlistModel.deleteSongFromPlaylist(selectedPlaylist, selectedSong);
        updateSongOnPlaylist();
        refreshPlaylists();
    }

    public void updateSongOnPlaylist() {
        Playlist selectedPlaylist = tbv_Playlists.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            getSongsInPlaylist();
        }
    }

    @FXML
    private void handle_moveSongDown(ActionEvent event) {
        int index = lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex();
        if (index != lv_SongsOnPlaylist.getItems().size() - 1) {

            // swap items
            lv_SongsOnPlaylist.getItems().add(index + 1, lv_SongsOnPlaylist.getItems().remove(index));
            // select item at new position
            lv_SongsOnPlaylist.getSelectionModel().clearAndSelect(index + 1);
        }
    }

    @FXML
    private void handle_moveSongUp(ActionEvent event) {
        int index = lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex();
        if (index != 0) {
            System.out.println(index);
            // swap items
            lv_SongsOnPlaylist.getItems().add(index - 1, lv_SongsOnPlaylist.getItems().remove(index));
            // select item at new position
            lv_SongsOnPlaylist.getSelectionModel().clearAndSelect(index - 1);
        }
    }

    //__________________________________________________________________________                       
    //
    //      Controls
    //__________________________________________________________________________
    public void play() throws IOException { // plays, pauses, resumes song when chosen
        
         if (isPaused == true && isScheduelSong == false) {
            currentTime = mediaPlayer.getCurrentTime();
            mediaPlayer.setStartTime(currentTime);
            mediaPlayer.play();
            isPaused = false;
        } else {
            if (mediaPlayer != null && isPaused == false && isScheduelSong == false) {
                mediaPlayer.pause();
                isPaused = true;
            } else {
                
            }
        mediaPlayer = new MediaPlayer(new Media(new File(lv_SongsOnPlaylist.getItems().get(currentSongPlaying).getPath()).toURI().toString()));
        lv_SongsOnPlaylist.getSelectionModel().clearAndSelect(currentSongPlaying);
        lbl_Library.setText(lv_SongsOnPlaylist.getItems().get(currentSongPlaying).getTitle() + " is now playing");
        mediaPlayer.play();
        mediaPlayer.setVolume(slider.getValue());
        isPaused = false;
        isScheduelSong=false;
        
        
        mediaPlayer.setOnEndOfMedia(() -> {
            
            if (lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex() != -1) {
                if (lv_SongsOnPlaylist.getItems().size() == currentSongPlaying + 1 || currentSongPlaying == -1) {
                    currentSongPlaying = 0;
                } else {
                    currentSongPlaying++;
                }
                try {
                    play();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(PrimaryController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            } else {
                handle_Stop();
            }
        });
    }
 }
    @FXML
    private void handle_play(ActionEvent event) throws IOException {
        
        if (mediaPlayer == null && lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex() != -1) {
            currentSongPlaying = lv_SongsOnPlaylist.getSelectionModel().getSelectedIndex();
            isScheduelSong=false;
            play(); 
        } else { 
            handle_Stop();
            mediaPlayer = null;
        }
    }
    
    private void handle_Stop() {
        if (mediaPlayer != null) {
            mediaPlayer = null;
            mediaPlayer.stop();
        }
    }

    @FXML
    private void setSlider(MouseEvent event) { // volume

        if (mediaPlayer != null) {
            System.out.println(slider.getValue());
            mediaPlayer.setVolume(slider.getValue() * 100);
            mediaPlayer.setVolume(slider.getValue() / 100);
        }
    }

    private void btn_loopAction(MouseEvent event) { // loop

        mediaPlayer.setOnEndOfMedia(() -> {
            if (mediaPlayer != null) {
            }
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        });
    }

    @FXML
    private void handle_Previous(ActionEvent event) throws IOException { // back button

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
    private void handle_Next(ActionEvent event) throws IOException { //skip button

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
    private void scheduleSong(MouseEvent event) {

        if (lv_SongsOnPlaylist.getSelectionModel().getSelectedItem() != null) {
            isScheduelSong = true;
        }
    }

    @FXML
    private void btn_loopAction(ActionEvent event) {
    }
    
    @FXML
    private void btn_shuffleAction(ActionEvent event) { // shuffle songs in playlist
        lv_SongsOnPlaylist.getItems().clear();
        if (tbv_Playlists.getSelectionModel().getSelectedItem() != null) {
            //songsInPlaylist.getItems().clear();
            List<Song> songsInPlaylist = tbv_Playlists.getSelectionModel().getSelectedItem().getSongs();

            Collections.shuffle(songsInPlaylist);
            ObservableList<Song> shuffledSongs = FXCollections.observableArrayList();

            shuffledSongs.addAll(FXCollections.observableArrayList(songsInPlaylist));

            lv_SongsOnPlaylist.setItems(shuffledSongs);
        }
    }
}
