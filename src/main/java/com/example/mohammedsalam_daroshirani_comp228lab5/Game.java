package com.example.mohammedsalam_daroshirani_comp228lab5;

public class Game {
    int game_id;
    String game_title;

    public Game(int game_id, String game_title) {
        this.game_id = game_id;
        this.game_title = game_title;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_title() {
        return game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }
}
