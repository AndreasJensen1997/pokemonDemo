package com.example.pokemondemo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class TileMap {

    public int TILE_SIZE = 32;
    private final int[][] background;
    private final int[][] foreground;

    private Image grass, path, house, water, tree, arena;

    public TileMap(int[][] background, int[][] foreground) {
        this.background = background;
        this.foreground = foreground;
    }

    public void render(GraphicsContext gc) {

    }
    public void loadTiles() {
        grass = new Image(getClass().getResourceAsStream("/tiles/plainvar.png"));
        path  = new Image(getClass().getResourceAsStream("/tiles/dirt.png"));
        house = new Image(getClass().getResourceAsStream("/tiles/plainvar.png"));
        water = new Image(getClass().getResourceAsStream("/tiles/water.png"));
        tree  = new Image(getClass().getResourceAsStream("/tiles/tree1.png"));
        arena = new Image(getClass().getResourceAsStream("/tiles/battleArea.png"));
    }
    public void loadTileImages() {
        grass = new Image(getClass().getResourceAsStream("/tiles/plainvar.png"));
        path = new Image(getClass().getResourceAsStream("/tiles/dirt.png"));
        house = new Image(getClass().getResourceAsStream("/tiles/plainvar.png"));
        water = new Image(getClass().getResourceAsStream("/tiles/water.png"));
        arena = new Image(getClass().getResourceAsStream("/tiles/water.png"));
    }

    public int getTileSize() {
        return TILE_SIZE;
    }
}


