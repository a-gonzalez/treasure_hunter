package main;

import entity.Entity;
import entity.Direction;
//import item.Item;

public class Collision
{
    GamePanel panel;

    public Collision(GamePanel panel)
    {
        this.panel = (GamePanel) panel;
    }

    public void checkEntity(Entity entity)
    {
        //System.out.println(entity.direction);

        int leftX = entity.worldX + entity.solid.x;
        int rightX = entity.worldX + entity.solid.x + entity.solid.width;
        int topY = entity.worldY + entity.solid.y;
        int bottomY = entity.worldY + entity.solid.y + entity.solid.height;

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

    public int checkItem(Entity entity, boolean player)
    {
        int result = 999;

        for (int index = 0; index < panel.items.length; index++)
        {
            if (panel.items[index] != null)
            {
                // get entity's solid area position
                entity.solid.x = entity.worldX + entity.solid.x;
                entity.solid.y = entity.worldY + entity.solid.y;

                // get item's solid area position
                panel.items[index].solid.x = panel.items[index].worldX + panel.items[index].solid.x;
                panel.items[index].solid.y = panel.items[index].worldY + panel.items[index].solid.y;

                switch (entity.direction)
                {
                    case Up :
                    {
                        entity.solid.y -= entity.speed;

                        if (entity.solid.intersects(panel.items[index].solid))
                        {
                            if (panel.items[index].collision)
                            {
                                entity.collision = true;
                            }

                            if (player)
                            {
                                result = index;
                            }
                        }
                        break;
                    }
                    case Down :
                    {
                        entity.solid.y += entity.speed;

                        if (entity.solid.intersects(panel.items[index].solid))
                        {
                            if (panel.items[index].collision)
                            {
                                entity.collision = true;
                            }

                            if (player)
                            {
                                result = index;
                            }
                        }
                        break;
                    }
                    case Left :
                    {
                        entity.solid.x -= entity.speed;

                        if (entity.solid.intersects(panel.items[index].solid))
                        {
                            if (panel.items[index].collision)
                            {
                                entity.collision = true;
                            }

                            if (player)
                            {
                                result = index;
                            }
                        }
                        break;
                    }
                    case Right :
                    {
                        entity.solid.x += entity.speed;

                        if (entity.solid.intersects(panel.items[index].solid))
                        {
                            if (panel.items[index].collision)
                            {
                                entity.collision = true;
                            }

                            if (player)
                            {
                                result = index;
                            }
                        }
                        break;
                    }
                }
                entity.solid.x = entity.solidDefaultX;
                entity.solid.y = entity.solidDefaultY;

                panel.items[index].solid.x = panel.items[index].solidDefaultX;
                panel.items[index].solid.y = panel.items[index].solidDefaultY;
            }
        }
        return result;
    }
}