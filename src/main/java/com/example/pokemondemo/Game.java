package com.example.pokemondemo;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game {
    private static final int TILE_SIZE = 32;
    private static final int MAP_WIDTH = 26;
    private static final int MAP_HEIGHT = 23;


    // Viewport size (what player sees)
    private static final int VIEWPORT_WIDTH = 640;
    private static final int VIEWPORT_HEIGHT = 480;

    private Stage stage;
    private TileMap tileMap;
    private AudioManager audioManager;
    private Player player;
    private InputHandler inputHandler;
    private AnimationTimer gameLoop;
    private Camera camera;

    public Game(Stage stage) {
        this.stage = stage;
        this.tileMap = new TileMap();
        this.audioManager = new AudioManager();
        this.player = new Player(10 * TILE_SIZE, 10 * TILE_SIZE, tileMap,audioManager);
        this.inputHandler = new InputHandler(player);
        this.audioManager = new AudioManager();

        // Create camera that follows player
        int worldWidth = MAP_WIDTH * TILE_SIZE;
        int worldHeight = MAP_HEIGHT * TILE_SIZE;
        this.camera = new Camera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, worldWidth, worldHeight);
    }

    public void start() {
        audioManager.startBackgroundMusic();

        // Canvas sizes match viewport (not full world!)
        Canvas backgroundCanvas = new Canvas(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        GraphicsContext bgc = backgroundCanvas.getGraphicsContext2D();
        bgc.setImageSmoothing(false);

        Canvas playerCanvas = new Canvas(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        GraphicsContext pgc = playerCanvas.getGraphicsContext2D();
        pgc.setImageSmoothing(false);

        // UI setup
        StackPane root = new StackPane(backgroundCanvas, playerCanvas);
        Scene scene = new Scene(root);

        // Setup keyboard input
        inputHandler.setupInput(scene, audioManager);

        // Game loop - now redraws both layers with camera offset
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update camera to follow player
                camera.followPlayer(player);

                // Clear both canvases
                bgc.clearRect(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
                pgc.clearRect(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

                // Draw map with camera offset
                bgc.save();
                bgc.translate(-camera.getX(), -camera.getY());
                tileMap.renderFullMap(bgc);
                bgc.restore();

                // Update player movement
                player.continueMove();

                // Draw player with camera offset
                pgc.save();
                pgc.translate(-camera.getX(), -camera.getY());
                player.renderPlayer(pgc);
                pgc.restore();

                // Handle new movement input when player stops
                if (!player.isMoving()) {
                    inputHandler.handleMovement();
                }
            }
        };
        gameLoop.start();

        stage.setScene(scene);
        stage.setTitle("Pokemon Starter Town");
        stage.show();
    }

    public void stop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
        audioManager.stop();
    }
}