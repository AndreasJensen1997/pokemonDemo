package com.example.pokemondemo;
import javafx.scene.Scene;

    public class InputHandler {
        private Player player;
        private boolean upPressed, downPressed, leftPressed, rightPressed;

        public InputHandler(Player player) {
            this.player = player;
        }

        public void setupInput(Scene scene, AudioManager audioManager) {
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case W -> upPressed = true;
                    case S -> downPressed = true;
                    case A -> leftPressed = true;
                    case D -> rightPressed = true;
                    case ENTER -> audioManager.playButtonSound();
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
        }
        public void handleMovement() {
            if (upPressed) player.move("up");
            else if (downPressed) player.move("down");
            else if (leftPressed) player.move("left");
            else if (rightPressed) player.move("right");
        }
    }

