package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity
{
    public int worldX, worldY;
    public int speed;
    public int width;
    public int height;
    public int scale;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public boolean moving = false;
    public int solidDefaultX, solidDefaultY;
    public boolean collision = false;

    public BufferedImage up0, up1, down0, down1, right0, right1, left0, left1;
    public Direction direction;
    public Rectangle solid;
    
    /*public void update()
    {

    }

    public void repaint()
    {

    }*/
}