package com.example.pokemondemo;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Game {

    private GraphicsContext gc;
    private AnimationTimer loop;

    private Player player;
    private TileMap map;

    // Hold tastestatus
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public Game(GraphicsContext gc) {
        this.gc = gc;
        this.player = new Player(100, 100); // player start position

        //this.map = new TileMap();
    }

    public void start() {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        loop.start();
    }

    private void update() {
        // Hvis spilleren ikke allerede bevæger sig, start et nyt step
        if (!player.moving) {
            if (upPressed) player.move("up");
            else if (downPressed) player.move("down");
            else if (leftPressed) player.move("left");
            else if (rightPressed) player.move("right");
        }

        // Bevæg spilleren mod target tile
        player.continueMove();

        // Her kan du tjekke collision/interaktioner
        // fx arena, PC, NPC osv.
    }

    private void render() {
        // Baggrund
        //gc.setFill(Color.LIGHTGREEN);
        //gc.fillRect(0, 0, 800, 600);

        // Tegn map (huse, arena, osv.)
        map.render(gc);

        // Tegn spilleren
        player.renderPlayer(gc);
    }

    // Taster trykket ned
    public void keyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case W -> upPressed = true;
            case S -> downPressed = true;
            case A -> leftPressed = true;
            case D -> rightPressed = true;
        }
    }

    // Taster sluppet
    public void keyReleased(KeyEvent e) {
        switch (e.getCode()) {
            case W -> upPressed = false;
            case S -> downPressed = false;
            case A -> leftPressed = false;
            case D -> rightPressed = false;
        }
    }
}
