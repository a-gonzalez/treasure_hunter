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
        this.tiles = new Tile[50];
        this.map = new int[panel.maxWorldColumns][panel.maxWorldRows];

        getTileImages();
        loadMap("/resources/dat/maps/004.dat");
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
    { // the first 10 tiles are placeholders
        setup(0, "hut", false);
        setup(1, "hut", false);
        setup(2, "hut", true);
        setup(3, "hut", false);
        setup(4, "hut", false);
        setup(5, "hut", false);
        setup(6, "hut", false);
        setup(7, "hut", false);
        setup(8, "hut", false);
        setup(9, "hut", false);
        
        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "dirt", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
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
                //System.out.println(number);

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