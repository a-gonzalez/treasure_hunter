package item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Item
{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

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