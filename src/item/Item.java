package item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import main.GamePanel;
import main.Utility;

public class Item
{
    public BufferedImage image;
    //public String name;
    public Type type;
    public boolean collision = false;
    public int worldX, worldY;
    public int solidDefaultX = 0;
    public int solidDefaultY = 0;
    public Rectangle solid = new Rectangle(0, 0, 48, 48);
    public Utility utility = new Utility();

    public void draw(Graphics2D g2, GamePanel panel)
    {
        int screenX = worldX - panel.player.worldX + panel.player.screenX;
        int screenY = worldY - panel.player.worldY + panel.player.screenY;

        if (worldX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
            worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
            worldY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
            worldY - panel.tileSize < panel.player.worldY + panel.player.screenY)
        {
            g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
        }
    }
}