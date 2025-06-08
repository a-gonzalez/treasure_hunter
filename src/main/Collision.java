package main;

import entity.Entity;
import entity.Direction;

public class Collision
{
    GamePanel panel;

    public Collision(GamePanel panel)
    {
        this.panel = (GamePanel) panel;
    }

    public void check(Entity entity)
    {
        //System.out.println(entity.direction);

        int leftX = entity.worldX + entity.hitbox.x;
        int rightX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int topY = entity.worldY + entity.hitbox.y;
        int bottomY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        //System.out.format("Left-X: %d\tRight-X: %d\tTop-Y: %d\tBottom-Y: %d", leftX, rightX, topY, bottomY);

        int leftColumn = leftX / panel.tileSize;
        int rightColumn = rightX / panel.tileSize;
        int topRow = topY / panel.tileSize;
        int bottomRow = bottomY / panel.tileSize;

        //System.out.format("Left-Column: %d\tRight-Column: %d\tTop-Row: %d\tBottom-Row: %d", leftColumn, rightColumn, topRow, bottomRow);

        int tile1, tile2;

        switch (entity.direction)
        {
            case Up:
            {
                topRow = (topY - entity.speed) / panel.tileSize;
                tile1 = panel.background.map[leftColumn][topRow];
                tile2 = panel.background.map[rightColumn][topRow];

                //System.out.format("Tile-1: %d\tTile-2:%d", tile1, tile2);

                if (panel.background.tiles[tile1].solid || panel.background.tiles[tile2].solid)
                {
                    entity.collision = true;
                }
                break;
            }
            case Down :
            {
                bottomRow = (bottomY + entity.speed) / panel.tileSize;
                tile1 = panel.background.map[leftColumn][bottomRow];
                tile2 = panel.background.map[rightColumn][bottomRow];

                //System.out.format("Tile-1: %d\tTile-2:%d", tile1, tile2);

                if (panel.background.tiles[tile1].solid || panel.background.tiles[tile2].solid)
                {
                    entity.collision = true;
                }
                break;
            }
            case Right :
            {
                rightColumn = (rightX + entity.speed) / panel.tileSize;
                tile1 = panel.background.map[leftColumn][topRow];
                tile2 = panel.background.map[rightColumn][bottomRow];

                //System.out.format("Tile-1: %d\tTile-2:%d", tile1, tile2);

                if (panel.background.tiles[tile1].solid || panel.background.tiles[tile2].solid)
                {
                    entity.collision = true;
                }
                break;
            }
            case Left :
            {
                leftColumn = (leftX - entity.speed) / panel.tileSize;
                tile1 = panel.background.map[leftColumn][topRow];
                tile2 = panel.background.map[leftColumn][bottomRow];

                //System.out.format("Tile-1: %d\tTile-2:%d", tile1, tile2);

                if (panel.background.tiles[tile1].solid || panel.background.tiles[tile2].solid)
                {
                    entity.collision = true;
                }
                break;
            }
        }
    }
}