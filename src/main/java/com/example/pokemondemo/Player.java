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
    private TileMap colissionMap; // Need reference to check collisions!




    private Image[] upSprites, downSprites, leftSprites, rightSprites;
    private Image currentSprite;
    private int animationFrame = 0;
    private int frameCounter = 0;
    private final int FRAMES_PER_SPRITE = 8; // Change speed here (lower

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
        double newTargetX = targetX;  // Start with current target
        double newTargetY = targetY;

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
            /*if (!colissionMap.isPositionBlocked( newTargetX, newTargetY)){

                 targetX = newTargetX;
                 targetY = newTargetY;
                moving = true;
            }else {
                System.out.println("Collision detected at tile!");
            }
*/

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
    private void loadAnimationSprites() {
        // Load walking animation frames (adjust paths to your files)
        downSprites = new Image[] {
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/neutral down.png")),
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/down 1.png")),
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/down 2.png"))
        };

        upSprites = new Image[] {
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/up neutral.png")),
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/up 1.png")),
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/up 2.png"))
        };

        leftSprites = new Image[] {
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/left neutral.png")),
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/left 1.png")),
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/left 2.png"))
        };

        rightSprites = new Image[] {
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/right neutral.png")),
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/right 1.png")),
                new Image(getClass().getResourceAsStream("/sprites/PlayerSprites/right 2.png"))
        };
    }

}
