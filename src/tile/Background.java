package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Background
{
    GamePanel panel;
    
    public Tile[] tiles;
    public int map[][];

    public Background(GamePanel panel)
    {
        this.panel = (GamePanel) panel;
        this.tiles = new Tile[10];
        this.map = new int[panel.maxWorldColumns][panel.maxWorldRows];

        getTileImages();
        loadMap("/resources/dat/maps/003.dat");
    }

    public void loadMap(String path)
    {
        try
        {
            InputStream stream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            int worldColumn = 0;
            int worldRow = 0;

            while (worldColumn < panel.maxWorldColumns && worldRow < panel.maxWorldRows)
            {
                String line = reader.readLine();

                while (worldColumn < panel.maxWorldColumns)
                {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[worldColumn]);

                    map[worldColumn][worldRow] = number;

                    ++worldColumn;
                }
                
                if (worldColumn == panel.maxWorldColumns)
                {
                    worldColumn = 0;
                    ++worldRow;
                }
            }
            reader.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void getTileImages()
    {
        try
        {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/img/tile/grass01.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/img/tile/wall.png"));
            tiles[1].solid = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/img/tile/water00.png"));
            tiles[2].solid = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/img/tile/dirt.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/img/tile/tree.png"));
            tiles[4].solid = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/img/tile/road00.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < panel.maxWorldColumns && worldRow < panel.maxWorldRows)
        {
            int number = map[worldColumn][worldRow];
            int worldX = worldColumn * panel.tileSize;
            int worldY = worldRow * panel.tileSize;
            int screenX = worldX - panel.player.worldX + panel.player.screenX;
            int screenY = worldY - panel.player.worldY + panel.player.screenY;

            if (worldX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
                worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
                worldY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
                worldY - panel.tileSize < panel.player.worldY + panel.player.screenY)
            {
                g2.drawImage(tiles[number].image, screenX, screenY, panel.tileSize, panel.tileSize, null);
            }
            ++worldColumn;

            if (worldColumn == panel.maxWorldColumns)
            {
                worldColumn = 0;
                
                ++worldRow;
            }
            //System.out.format("X: %d\tY: %d", worldRow, worldColumn);
        }
    }
}