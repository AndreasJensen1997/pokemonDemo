package com.example.pokemondemo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.AudioClip;
import tile.LargeTileManager;

import java.net.URL;

public class Main extends Application {

    private MediaPlayer mediaPlayer;
    private AudioClip buttonClip;

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    private Image grassImage, pathImage, houseImage, waterImage, treeImage, arenaImage, pokecenter, house, snorlax, tree,battleArea;

    Player player = new Player(400, 260);

    private final int TILE_SIZE = 32;
    private final int[][] townMapBackground = {

            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
    };
    private final int[][] townMapForeground = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 4, 4, 4, 4,},
            {4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,},
            {4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            {4, 4, 0, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,},
            {4, 4, 0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 0, 0, 1, 0,},
            {4, 4, 0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 0, 0, 1, 0,},
            {4, 4, 0, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 0, 0, 1, 0,},
            {4, 4, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,},
            {4, 4, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0,},
            {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            {4, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 0, 0, 1, 0,},
            {4, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 0, 0, 1, 0,},
            {4, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 0, 0, 1, 0,},
            {1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,},
            {4, 4, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,},
            {4, 4, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0,},
            {4, 4, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            {4, 4, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
    };

    @Override
    public void start(Stage stage) {
        startMusic();
        TileMap map = new TileMap(townMapBackground, townMapForeground);
        map.loadTiles(); // instansmetode
      //  map.loadTileImages();

        int width = 25 * TILE_SIZE;
        int height = 19 * TILE_SIZE;

        // Canvas til baggrunden (static)
        Canvas backgroundCanvas = new Canvas(width, height);
        GraphicsContext bg = backgroundCanvas.getGraphicsContext2D();
        bg.setImageSmoothing(false);

        LargeTileManager largeTiles = new LargeTileManager(bg);
        largeTiles.loadLargeTiles(); // instansmetode
        loadButtonSound();

        // Canvas til spilleren (dynamic)
        Canvas playerCanvas = new Canvas(width, height);
        GraphicsContext pgc = playerCanvas.getGraphicsContext2D();
        pgc.setImageSmoothing(false);

        Player player = new Player(10 * TILE_SIZE, 10 * TILE_SIZE);

        // Tegn tilemaps KUN ÉN GANG
        drawTilemap2(bg);
        drawTilemap(bg);

        // Store tiles tegnes også kun én gang
        drawLargeTile(bg, pokecenter, 16, 4, 5, 4);
        drawLargeTile(bg, house,      16, 11, 5, 4);
        drawLargeTile(bg, tree,       21, -2, 2, 3);
        drawLargeTile(bg, tree,       23, -2, 2, 3);
        drawLargeTile(bg, tree,       16, -2, 2, 3);
        drawLargeTile(bg, tree,       14, -2, 2, 3);
        drawLargeTile(bg, tree,       12, -2, 2, 3);
        drawLargeTile(bg, tree,       10, -2, 2, 3);
        drawLargeTile(bg, tree,       8,  -2, 2, 3);
        drawLargeTile(bg, tree,       6,  -2, 2, 3);
        drawLargeTile(bg, tree,       4,  -2, 2, 3);
        drawLargeTile(bg, tree,       2,  -2, 2, 3);
        drawLargeTile(bg, tree,       0,  -2, 2, 3);
        drawLargeTile(bg, battleArea, 1,   1, 12, 8);
        drawLargeTile(bg, snorlax,    18,  0, 2, 2);

        // UI lag: Baggrund nederst, spiller øverst
        StackPane root = new StackPane(backgroundCanvas, playerCanvas);
        Scene scene = new Scene(root);

        // 🔥 Animation loop: opdaterer KUN spilleren
        new AnimationTimer() {
            @Override
            public void handle(long now) {

                // Ryd kun player canvas
                pgc.clearRect(0, 0, width, height);

                player.continueMove();
                player.renderPlayer(pgc);

                if (!player.isMoving()) {
                    if (upPressed)    player.move("up");
                    if (downPressed)  player.move("down");
                    if (leftPressed)  player.move("left");
                    if (rightPressed) player.move("right");
                }
            }
        }.start();

        // Input
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W -> upPressed = true;
                case S -> downPressed = true;
                case A -> leftPressed = true;
                case D -> rightPressed = true;
                case ENTER -> playButtonSound();
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W -> upPressed = false;
                case S -> downPressed = false;
                case A -> leftPressed = false;
                case D -> rightPressed = false;
            }
        });


        stage.setScene(scene);
        stage.setTitle("Pokemon Starter Town");
        stage.show();
    }


    private void drawLargeTile(GraphicsContext gc, Image tile, int startXTile, int startYTile, int widthInTiles, int heightInTiles) {
        int pixelWidth = widthInTiles * TILE_SIZE;
        int pixelHeight = heightInTiles * TILE_SIZE;

        gc.drawImage(tile, startXTile * TILE_SIZE, startYTile * TILE_SIZE, pixelWidth, pixelHeight);
    }

    private void drawTilemap(GraphicsContext gc) {
        for (int y = 0; y < townMapForeground.length; y++) {
            for (int x = 0; x < townMapForeground[0].length; x++) {
                int tile = townMapForeground[y][x];
                Image tileImage;
                switch (tile) {
                    case 0 -> tileImage = grassImage;
                    case 1 -> tileImage = pathImage;
                    case 2 -> tileImage = houseImage;
                    case 3 -> tileImage = waterImage;
                    case 4 -> tileImage = treeImage;
                    case 5 -> tileImage = arenaImage;
                    default -> tileImage = grassImage;
                }
                // Draw at integer coordinates to avoid subpixel gaps
                gc.drawImage(tileImage, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE + 10, TILE_SIZE + 10);
            }
        }
    }

    private void drawTilemap2(GraphicsContext gc) {
        for (int y = 0; y < townMapBackground.length; y++) {
            for (int x = 0; x < townMapBackground[0].length; x++) {
                int tile = townMapBackground[y][x];
                Image tileImage;
                switch (tile) {
                    case 0 -> tileImage = grassImage;
                    case 1 -> tileImage = pathImage;
                    case 2 -> tileImage = houseImage;
                    case 3 -> tileImage = waterImage;
                    case 4 -> tileImage = treeImage;
                    case 5 -> tileImage = arenaImage;
                    default -> tileImage = grassImage;
                }
                // Draw at integer coordinates to avoid subpixel gaps
                gc.drawImage(tileImage, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE + 10, TILE_SIZE + 10);
            }
        }
    }

    private void loadButtonSound() {
        URL resource = getClass().getResource("/music/button.mp3");
        if (resource != null) {
            buttonClip = new AudioClip(resource.toString());
            buttonClip.setVolume(0.5);
        } else System.out.println("Button sound not found!");
    }

    private void playButtonSound() {
        if (buttonClip != null) buttonClip.play();
    }

    public void startMusic() {
        URL resource = getClass().getResource("/music/littleroot_town.mp3");
        if (resource != null) {
            Media sound = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        } else System.out.println("Background music not found!");
    }


    @Override
    public void stop() {
        if (mediaPlayer != null) mediaPlayer.stop();
    }

    public static void main(String[] args) {
        launch();
    }



}

