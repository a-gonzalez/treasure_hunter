package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.Utility;

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

    public void setup(int index, String name, boolean solid)
    {
        Utility utility = new Utility();

        try
        {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/resources/img/tile/" + name + ".png"));
            tiles[index].image = utility.scale(tiles[index].image, panel.tileSize, panel.tileSize);
            tiles[index].solid = solid;
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public void getTileImages()
    {
        setup(0, "grass00", false);
        setup(1, "wall", true);
        setup(2, "water00", true);
        setup(3, "dirt", false);
        setup(4, "tree", true);
        setup(5, "road00", false);
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
                g2.drawImage(tiles[number].image, screenX, screenY, null);
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