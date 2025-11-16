package tile;

import com.example.pokemondemo.TileMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class LargeTileManager {
    private TileMap tileMap;
    private Image pokecenter, house, snorlax, tree, battleArea, npc, flower, bush,sign,PCsign;

    public LargeTileManager(TileMap tileMap) {
        this.tileMap = tileMap;
        loadLargeTiles();
    }

    private void loadLargeTiles() {
        pokecenter = new Image(getClass().getResourceAsStream("/sprites/pokeCenter.png"));
        house = new Image(getClass().getResourceAsStream("/sprites/house.png"));
        snorlax = new Image(getClass().getResourceAsStream("/Tiles/snorlax.png"));
        tree = new Image(getClass().getResourceAsStream("/Tiles/tree1.png"));
        battleArea = new Image(getClass().getResourceAsStream("/Tiles/battleArea.png"));
        npc = new Image(getClass().getResourceAsStream("/sprites/npc.png"));
        flower = new Image(getClass().getResourceAsStream("/tiles/flower.png"));
        bush = new Image(getClass().getResourceAsStream("/tiles/bush.png"));
        sign = new Image(getClass().getResourceAsStream("/tiles/sign.png"));
        PCsign = new Image(getClass().getResourceAsStream("/tiles/PCsign.png"));

    }

    // Renders all large tiles/sprites
    public void renderAllLargeTiles(GraphicsContext gc) {
        // Buildings
        drawLargeTile(gc, pokecenter, 16, 4, 5, 4);
        drawLargeTile(gc, house, 16, 11, 5, 4);

        // Battle arena
        drawLargeTile(gc, battleArea, 1, 1, 12, 8);

        // Snorlax blocking path
        drawLargeTile(gc, snorlax, 18, 0, 2, 2);
        // signs
        drawLargeTile(gc, sign, 17, 15, 1, 1);
        drawLargeTile(gc, sign, 5, 7, 1, 1);
        drawLargeTile(gc, PCsign, 17, 8, 1, 1);


        // Battle NPC
        drawLargeTile(gc, npc, 4, 4, 1, 1);
        // flower
        int j =0;
        int flowerIncrement=1;
        int rowIncrement =0;
        for(j=0;j<9;j++){
            drawLargeTile(gc, flower, 13 + flowerIncrement, 10 + rowIncrement, 1, 1);
            flowerIncrement ++;
        }


        // bushes
        drawLargeTile(gc, bush, 8, 7, 1, 1);
        drawLargeTile(gc, bush, 9, 7, 1, 1);
        drawLargeTile(gc, bush, 10, 7, 1, 1);
        drawLargeTile(gc, bush, 11, 7, 1, 1);
        drawLargeTile(gc, bush, 12, 7, 1, 1);

        drawLargeTile(gc, bush, 8, 8, 1, 1);
        drawLargeTile(gc, bush, 9, 8, 1, 1);
        drawLargeTile(gc, bush, 10, 8, 1, 1);
        drawLargeTile(gc, bush, 11, 8, 1, 1);
        drawLargeTile(gc, bush, 12, 8, 1, 1);

        //==== trees=====

        // top
        int topX = -2;
        int topIncrement = 2;
        for (int i = 0; i < 12; i++) {
            drawLargeTile(gc, tree, topX + topIncrement, -2, 2, 3);
            topIncrement += 2;
            if (i==8){
                topIncrement +=2;
            }
        }

        // left side
        int leftY = -4;
        int leftIncrement = 2;
        for (int i = 0; i < 11; i++) {
            drawLargeTile(gc, tree, 0, leftY + leftIncrement, 2, 3);
            leftIncrement += 2;

        }
        // bottom
        int startX = -2;
        int bottomIncrement = 2;
        for (int i = 0; i < 12; i++) {
            drawLargeTile(gc, tree, startX + bottomIncrement, 20, 2, 3);
            bottomIncrement += 2;
        }
        // right side
        int startY = -4;
        int rightIncrement = 2;
        for (int i = 0; i < 12; i++) {
            drawLargeTile(gc, tree, 24, startY + rightIncrement, 2, 3);
            rightIncrement += 2;
        }


    }

    private void drawLargeTile(GraphicsContext gc, Image tile, int startXTile, int startYTile, int widthInTiles, int heightInTiles) {
        int pixelWidth = widthInTiles * tileMap.getTileSize();
        int pixelHeight = heightInTiles * tileMap.getTileSize();
        gc.drawImage(tile, startXTile * tileMap.getTileSize(), startYTile * tileMap.getTileSize(), pixelWidth, pixelHeight);
    }
}