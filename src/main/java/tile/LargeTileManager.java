package tile;

import com.example.pokemondemo.TileMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LargeTileManager {
    private TileMap tileMap;


    public Image pokecenter, house, snorlax, tree;
    GraphicsContext gc;

    public LargeTileManager(GraphicsContext gc) {
        this.gc = gc;
    }

    public void loadLargeTiles() {
        pokecenter = new Image(getClass().getResourceAsStream("/sprites/pokeCenter.png"));
        house      = new Image(getClass().getResourceAsStream("/sprites/house.png"));
        snorlax    = new Image(getClass().getResourceAsStream("/tiles/snorlax.png"));
        tree       = new Image(getClass().getResourceAsStream("/tiles/tree1.png"));
    }

    private void drawLargeTile(GraphicsContext gc, Image tile, int startXTile, int startYTile, int widthInTiles, int heightInTiles) {
        int pixelWidth = widthInTiles * tileMap.getTileSize();
        int pixelHeight = heightInTiles * tileMap.getTileSize();;

        gc.drawImage(tile, startXTile * tileMap.getTileSize(), startYTile * tileMap.getTileSize(), pixelWidth, pixelHeight);
    }


}
