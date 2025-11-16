package com.example.pokemondemo;

public class Camera {
    private double x, y;
    private int cameraWidth, cameraHeight;
    private int worldWidth, worldHeight;

    public Camera(int viewportWidth, int viewportHeight, int worldWidth, int worldHeight) {
        this.cameraWidth = viewportWidth;
        this.cameraHeight = viewportHeight;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.x = 0;
        this.y = 0;
    }

    // Center camera on player
    public void followPlayer(Player player) {
        // Center on player
        x = player.x - cameraWidth / 2.0 + 16; // +16 to center on player sprite
        y = player.y - cameraHeight / 2.0 + 16;

        // Clamp to world bounds
        x = Math.max(0, Math.min(x, worldWidth - cameraWidth));
        y = Math.max(0, Math.min(y, worldHeight - cameraHeight));
    }

    // Get camera offset for rendering
    public double getX() { return x; }
    public double getY() { return y; }
}