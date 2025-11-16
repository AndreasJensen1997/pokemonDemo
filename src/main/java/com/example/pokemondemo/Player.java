package com.example.pokemondemo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
    public double x, y;
    private Image sprite;


    public boolean moving = false;
    public String direction = ""; // "up", "down", "left", "right"
    private double targetX, targetY; // målkoordinater for tile-bevægelsen
    private double speed = 2; // pixels per frame

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        sprite = new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/neutral down.png"));
        targetX = x;
        targetY = y;
    }
    public boolean isMoving() {
        return moving;
    }

    public void move(String dir) {
        if (!moving) {
            direction = dir;
            moving = true;

            switch (dir) {
                case "up" -> {
                    targetY -= 32;
                    sprite = new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/up neutral.png"));
                }
                case "down" -> {
                    targetY += 32;
                    sprite = new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/neutral down.png"));
                }
                case "left" -> {
                    targetX -= 32;
                    sprite = new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/left neutral.png"));
                }
                case "right" -> {
                    targetX += 32;
                    sprite = new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/right neutral.png"));
                }
            }
        }
    }

    public void continueMove() {
        if (moving) {
            // move toward target
            if (x < targetX) x += speed;
            if (x > targetX) x -= speed;
            if (y < targetY) y += speed;
            if (y > targetY) y -= speed;

            // stop når vi er nået tile
            if (Math.abs(x - targetX) < 1 && Math.abs(y - targetY) < 1) {
                x = targetX;
                y = targetY;
                moving = false;
            }
        }
    }

    public void renderPlayer(GraphicsContext gc) {
        gc.drawImage(sprite, x, y, 32, 32);
    }

}
