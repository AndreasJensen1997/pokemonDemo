package com.example.pokemondemo;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Game game;

    @Override
    public void start(Stage stage) {
        game = new Game(stage);
        game.start();
    }

   @Override
    public void stop() {
        if (game != null) {
            game.stop();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}