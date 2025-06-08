package main;

import item.*;

public class Assets
{
    GamePanel panel;

    public Assets(GamePanel panel)
    {
        this.panel = panel;
    }

    public void setItems()
    {
        panel.items[0] = new Key();
        panel.items[0].worldX = 23 * panel.tileSize;
        panel.items[0].worldY = 7 * panel.tileSize;

        panel.items[1] = new Key();
        panel.items[1].worldX = 23 * panel.tileSize;
        panel.items[1].worldY = 40 * panel.tileSize;

        panel.items[2] = new Key();
        panel.items[2].worldX = 35 * panel.tileSize;
        panel.items[2].worldY = 9 * panel.tileSize;

        panel.items[3] = new Door();
        panel.items[3].worldX = 10 * panel.tileSize;
        panel.items[3].worldY = 11 * panel.tileSize;

        panel.items[4] = new Door();
        panel.items[4].worldX = 8 * panel.tileSize;
        panel.items[4].worldY = 28 * panel.tileSize;

        panel.items[5] = new Door();
        panel.items[5].worldX = 12 * panel.tileSize;
        panel.items[5].worldY = 22 * panel.tileSize;

        panel.items[6] = new Chest();
        panel.items[6].worldX = 10 * panel.tileSize;
        panel.items[6].worldY = 7 * panel.tileSize;
    }
}