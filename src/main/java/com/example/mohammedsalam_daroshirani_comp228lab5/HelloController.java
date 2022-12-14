package com.example.mohammedsalam_daroshirani_comp228lab5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.*;
import java.util.Objects;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.mohammedsalam_daroshirani_comp228lab5.DBUtil.updateGameInformation;
import static com.example.mohammedsalam_daroshirani_comp228lab5.DBUtil.updatePlayerInformation;
import static java.lang.Integer.parseInt;

public class HelloController {
    @FXML
    public TextField pgi_player_game_id;
    @FXML
    public TextField pgi_game_id;
    @FXML
    public TextField pgi_player_id;
    @FXML
    public TextField pgi_playing_date;
    @FXML
    public TextField pgi_score;
    @FXML
    public Button insertPlayerAndGameInformation;
    @FXML
    public TextField pi_player_id;
    @FXML
    public TextField pi_first_name;
    @FXML
    public TextField pi_last_name;
    @FXML
    public TextField pi_address;
    @FXML
    public TextField pi_postal_code;
    @FXML
    public TextField pi_province;
    @FXML
    public TextField pi_phone_number;
    @FXML
    public Button updatePlayerInformation;
    @FXML
    public TextField gi_game_id;
    @FXML
    public TextField gi_game_title;
    @FXML
    public Button updateGameInformation;
    @FXML
    public TableView<Object> display_table;
    @FXML
    public TableColumn<Player, Integer> player_id_table;
    @FXML
    public TableColumn<Player, String> game_id_table;
    @FXML
    public TableColumn<Player, String> first_name_table;
    @FXML
    public TableColumn<Player, String> last_name_table;
    @FXML
    public TableColumn<Player, String> address_table;
    @FXML
    public TableColumn<Player, String> postal_code_table;
    @FXML
    public TableColumn<Player, String> province_table;
    @FXML
    public TableColumn<Player, String> phone_number_table;
    @FXML
    public TableColumn<Game, String> game_title_table;
    @FXML
    public TableColumn<Game, Integer> player_game_id_table;
    @FXML
    public TableColumn<PlayerAndGame, String> playing_date_table;
    @FXML
    public TableColumn<PlayerAndGame, Integer> score_table;
    @FXML
    public Button insertPlayerInformation;
    @FXML
    public Button insertGameInformation;
    @FXML
    public Tab viewgametabledata;
    @FXML
    public TableView<Object> display_game_table;
    @FXML
    public Button viewgametable;
    @FXML
    public TableView<Object> display_playerandgame_table;
    @FXML
    public Button viewplayerandgametable;
    @FXML
    public TableView<Object> display_player_table;
    @FXML
    public Button viewplayertabledata;
    @FXML
    public TableColumn<PlayerAndGame, Integer> player_id_playerandgametable;
    @FXML
    public TableColumn<PlayerAndGame, Integer> game_id_playerandgametable;


    public void onInsertPlayerAndGameInformationButtonClick(ActionEvent actionEvent) throws SQLException {
        DBUtil.insertPlayerAndGameData("playerandgame", parseInt(pgi_player_game_id.getText()), parseInt(pgi_game_id.getText()), parseInt(pgi_player_id.getText()), pgi_playing_date.getText(), parseInt(pgi_score.getText()));
    }

    public void onUpdatePlayerInformationButtonClick(ActionEvent actionEvent) throws SQLException {
        updatePlayerInformation("player", parseInt(pi_player_id.getText()), pi_first_name.getText(), pi_last_name.getText(), pi_address.getText(), pi_postal_code.getText(), pi_province.getText(), pi_phone_number.getText());
        populatePlayerData();
    }

    public void onUpdateGameInformationButtonClick (ActionEvent actionEvent) throws SQLException {
        updateGameInformation("game", parseInt(gi_game_id.getText()), gi_game_title.getText());
        populateGameData();
    }

    public void onInsertPlayerInformationButtonClick (ActionEvent actionEvent) throws SQLException {
        DBUtil.insertPlayerData("player", parseInt(pi_player_id.getText()), pi_first_name.getText(), pi_last_name.getText(), pi_address.getText(), pi_postal_code.getText(), pi_province.getText(), pi_phone_number.getText());
    }

    public void onInsertGameInformationButtonClick (ActionEvent actionEvent) throws SQLException {
        DBUtil.insertGameData("game", parseInt(gi_game_id.getText()), gi_game_title.getText());
    }


        //////////////////////////////////////////////////////////// Populate Table Data Methods ///////////////////////////
    public void populatePlayerData () throws SQLException {
        ResultSet rs = DBUtil.queryPlayerData("SELECT * FROM player");

        // creating a list of objects that we want to add to the table
        ObservableList<Player> players = FXCollections.observableArrayList();

        // add objects one by one to the list by going through the resultset we got from running query
        while (rs.next()) {
            Player player = new Player(rs.getInt("player_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("postal_code"), rs.getString("province"), rs.getString("phone_number"));
            players.add(player);
        }

        // assign each attribute of the Player class(entity) to each column of the table
        player_id_table.setCellValueFactory(new PropertyValueFactory<>("player_id")); //name of Player attribute "player_id"
        first_name_table.setCellValueFactory(new PropertyValueFactory<>("firstName")); // name of Player attribute "firstName"
        last_name_table.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        address_table.setCellValueFactory(new PropertyValueFactory<>("address"));
        postal_code_table.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        province_table.setCellValueFactory(new PropertyValueFactory<>("province"));
        phone_number_table.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        //clear table before adding new records
        display_player_table.getItems().clear();

        // add data to the table
        display_player_table.getItems().addAll(players);

    }

    public void populateGameData () throws SQLException {
        ResultSet rs = DBUtil.queryGameData("SELECT * FROM game");

        // creating a list of objects that we want to add to the table
        ObservableList<Game> games = FXCollections.observableArrayList();

        // add objects one by one to the list by going through the resultset we got from running query
        while (rs.next()) {
            Game game = new Game(rs.getInt("game_id"), rs.getString("game_title"));
            games.add(game);
        }

        game_id_table.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        game_title_table.setCellValueFactory(new PropertyValueFactory<>("game_title"));

        //clear table before adding new records
        display_game_table.getItems().clear();

        // add data to the table
        display_game_table.getItems().addAll(games);

    }

    public void populatePlayerAndGameData () throws SQLException {
        ResultSet rs = DBUtil.queryPlayerAndGameData("SELECT * FROM playerandgame");

        // creating a list of objects that we want to add to the table
        ObservableList<PlayerAndGame> playerandgames = FXCollections.observableArrayList();

        // add objects one by one to the list by going through the resultset we got from running query
        while (rs.next()) {
            PlayerAndGame playerandgame = new PlayerAndGame(rs.getInt("player_game_id"), rs.getInt("game_id"), rs.getInt("player_id"), rs.getString("playing_date"), rs.getInt("score"));
            playerandgames.add(playerandgame);
        }

        // assign each attribute of the Student class(entity) to each column of the table
        player_game_id_table.setCellValueFactory(new PropertyValueFactory<>("player_game_id")); //name of Student attribute "name"
        game_id_playerandgametable.setCellValueFactory(new PropertyValueFactory<>("game_id")); // name of student attribute "id"
        player_id_playerandgametable.setCellValueFactory(new PropertyValueFactory<>("player_id"));
        playing_date_table.setCellValueFactory(new PropertyValueFactory<>("playing_date"));
        score_table.setCellValueFactory(new PropertyValueFactory<>("score"));

        //clear table before adding new records
        display_playerandgame_table.getItems().clear();

        // add data to the table
        display_playerandgame_table.getItems().addAll(playerandgames);

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void onViewGameTableButtonClick (ActionEvent actionEvent) throws SQLException {
        populateGameData();
    }

    public void onViewPlayerAndGameTableButtonClick (ActionEvent actionEvent) throws SQLException {
        populatePlayerAndGameData();
    }

    public void onViewPlayerTableButtonClick (ActionEvent actionEvent) throws SQLException {
        populatePlayerData();
    }

    public void onSelectPlayerIdButtonClick(ActionEvent actionEvent) throws SQLException {
        JFrame frame;
        frame = new JFrame();

        int search_id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Please enter the ID of the Player you would like to update"));

        ResultSet rs = DBUtil.queryRow("Select * FROM Player WHERE player_id =" + search_id);

        while(rs.next()) {
            pi_player_id.setText(String.valueOf(rs.getInt("player_id")));
            pi_first_name.setText(rs.getString("first_name"));
            pi_last_name.setText(rs.getString("last_name"));
            pi_address.setText(rs.getString("address"));
            pi_postal_code.setText(rs.getString("postal_code"));
            pi_province.setText(rs.getString("province"));
            pi_phone_number.setText(rs.getString("phone_number"));
        }
    }

    public void onSelectGameIdButtonClick(ActionEvent actionEvent) throws SQLException {
        JFrame frame;
        frame = new JFrame();

        int search_id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Please enter the ID of the Game you would like to update"));

        ResultSet rs = DBUtil.queryRow("Select * FROM game WHERE game_id =" + search_id);

        while(rs.next()) {
            gi_game_id.setText(String.valueOf(rs.getInt("game_id")));
            gi_game_title.setText(rs.getString("game_title"));
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

