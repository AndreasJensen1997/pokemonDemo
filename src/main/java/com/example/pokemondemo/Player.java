package com.example.pokemondemo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
// =====Sounds=====
    private long lastCollisionSoundTime = 0;
    private final long COLLISION_SOUND_COOLDOWN = 500;

    public double x, y;
    public boolean moving = false;
    public String direction = "down";
    private double targetX, targetY;
    private double speed = 2;
    private TileMap tileMap;
    private AudioManager audioManager;

    // =====Animation=====
    private Image[] upSprites, downSprites, leftSprites, rightSprites;
    private Image currentSprite;
    private int animationFrame = 0;
    private int frameCounter = 0;
    private final int FRAMES_PER_SPRITE = 8; // Lower = faster animation

    public Player(double x, double y, TileMap tileMap, AudioManager audioManager) {
        this.x = x;
        this.y = y;
        this.tileMap = tileMap;
        this.audioManager = audioManager;
        targetX = x;
        targetY = y;

        loadAnimationSprites();
        currentSprite = downSprites[0]; // Start with idle down sprite
    }

    public boolean isMoving() {
        return moving;
    }

    public void move(String dir) {
        if (!moving) {
            direction = dir;
            animationFrame = 0; // Reset animation on new move

            double newTargetX = targetX;
            double newTargetY = targetY;

            switch (dir) {
                case "up" -> newTargetY -= 32;
                case "down" -> newTargetY += 32;
                case "left" -> newTargetX -= 32;
                case "right" -> newTargetX += 32;
            }
            if (!tileMap.isPositionBlocked(newTargetX, newTargetY)) {
                targetX = newTargetX;
                targetY = newTargetY;
                moving = true;
            } else {
                currentSprite = getCurrentDirectionSprites()[0];
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastCollisionSoundTime > COLLISION_SOUND_COOLDOWN) {
                    audioManager.playCollisionSound();
                    lastCollisionSoundTime = currentTime;
                    System.out.println("Blocked at tile!");
                }
            }
        }
    }

    public void continueMove() {
        if (moving) {
            // Move toward target
            if (x < targetX) x += speed;
            if (x > targetX) x -= speed;
            if (y < targetY) y += speed;
            if (y > targetY) y -= speed;

            // ANIMATE while moving
            frameCounter++;
            if (frameCounter >= FRAMES_PER_SPRITE) {
                frameCounter = 0;
                animationFrame++;

                // Get current direction sprites
                Image[] sprites = getCurrentDirectionSprites();
                if (animationFrame >= sprites.length) {
                    animationFrame = 0; // Loop animation
                }
                currentSprite = sprites[animationFrame];
            }

            // Stop when reached tile
            if (Math.abs(x - targetX) < 1 && Math.abs(y - targetY) < 1) {
                x = targetX;
                y = targetY;
                moving = false;
                animationFrame = 0;
                // Set to idle pose (first frame)
                currentSprite = getCurrentDirectionSprites()[0];
            }
        }
    }

    private Image[] getCurrentDirectionSprites() {
        return switch (direction) {
            case "up" -> upSprites;
            case "down" -> downSprites;
            case "left" -> leftSprites;
            case "right" -> rightSprites;
            default -> downSprites;
        };
    }

    public void renderPlayer(GraphicsContext gc) {
        gc.drawImage(currentSprite, x, y, 32, 32);
    }

    private Image loadSprite(String path) {
        return new Image(
                getClass().getResourceAsStream(path),
                32,    // Force width to 32
                32,    // Force height to 32
                false, // Don't preserve aspect ratio
                false  // Don't smooth (crisp pixels)
        );
    }
    private void loadAnimationSprites() {
        downSprites = new Image[] {
                loadSprite("/sprites/PlayerSprites/down 1.png"),
                loadSprite("/sprites/PlayerSprites/down 2.png")
        };

        upSprites = new Image[] {
                loadSprite("/sprites/PlayerSprites/up 1.png"),
                loadSprite("/sprites/PlayerSprites/up 2.png")
        };

        leftSprites = new Image[] {
                loadSprite("/sprites/PlayerSprites/left 1.png"),
                loadSprite("/sprites/PlayerSprites/left 2.png")
        };

        rightSprites = new Image[] {
                loadSprite("/sprites/PlayerSprites/right 1 .png"),
                loadSprite("/sprites/PlayerSprites/right 2.png")
        };
        }
}