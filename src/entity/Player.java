package entity;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import javax.imageio.ImageIO;

//import java.net.URL;
//import java.net.URISyntaxException;

import main.Control;
import main.GamePanel;
import item.*;

public class Player extends Entity
{
    GamePanel panel;
    Control control;
    BufferedImage current;

    public final int screenX;
    public final int screenY;
    public int keys = 0;

    //int interval = 40;
    //int timer = 0;

    public Player(GamePanel panel, Control control)
    {
        this.panel = (GamePanel) panel;
        this.control = control;

        screenX = panel.screenWidth / 2 - (panel.tileSize / 2);
        screenY = panel.screenHeight / 2 - (panel.tileSize / 2);

        solid = new Rectangle(8, 16, 32, 32);
        solidDefaultX = solid.x;
        solidDefaultY = solid.y;

        initialize();
    }

    private void initialize()
    {
        setDefaultValues();
        getPlayerImages();
    }

    public void setDefaultValues()
    {
        height = 16;
        width = 16;
        worldX = panel.tileSize * 23;
        worldY = panel.tileSize * 21;
        scale = 3;
        speed = 4;
        direction = Direction.Down;
    }

    public void getPlayerImages()
    {
        try
        {
            up0 = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/up0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/up1.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/right0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/right1.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/left0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/left1.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/down0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/down1.png"));

            current = right0;

            //System.out.println(current != null);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void update(double delta_time)
    {
        if (control.up || control.down || control.right || control.left)
        {
            if (control.up)
            {
                direction = Direction.Up;
            }
            else if (control.down)
            {
                direction = Direction.Down;
            }
            else if (control.left)
            {
                direction = Direction.Left;
            }
            else if (control.right)
            {
                direction = Direction.Right;
            }
            collision = false;

            // check tile collision
            panel.collision.checkEntity(this);

            // check item collision
            int itemIndex = panel.collision.checkItem(this, true);

            pickUpItem(itemIndex);

            if (!collision)
            {
                switch (direction)
                {
                    case Up :
                    {
                        worldY -= speed; break;
                    }
                    case Down :
                    {
                        worldY += speed; break;
                    }
                    case Right :
                    {
                        worldX += speed; break;
                    }
                    case Left :
                    {
                        worldX -= speed; break;
                    }
                }
            }
            ++spriteCounter;

            if (spriteCounter > 14)
            {
                if (spriteNumber == 1)
                {
                    spriteNumber = 2;
                }
                else if (spriteNumber == 2)
                {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpItem(int index)
    {
        //System.out.format("pick-up index: %d", index);

        if (index != 999)
        {
            switch(panel.items[index].type)
            {
                case Key :
                {
                    panel.playEffect(1);

                    ++keys;
                    panel.items[index] = null;

                    panel.ui.showMessage("You found a key!");

                    break;
                }
                case Door :
                {
                    if (keys > 0)
                    {
                        panel.playEffect(3);

                        --keys;
                        panel.items[index] = null;

                        panel.ui.showMessage("You opened a door!");
                    }
                    else
                    {
                        panel.ui.showMessage("You need a key to open this door!");
                    }
                    break;
                }
                case Chest :
                {
                    panel.ui.gameOver = true;
                    panel.stopMusic();
                    panel.playEffect(4);

                    break;
                }
                case Boots :
                {
                    panel.playEffect(2);

                    speed += 2;
                    panel.items[index] = null;

                    panel.ui.showMessage("POWER-UP - SPEED!");

                    break;
                }
            }
        }
    }

    public void draw(Graphics2D g2)
    {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, panel.tileSize, panel.tileSize);
        BufferedImage image = null;

        switch (direction)
        {
            case Up:
            {
                if (spriteNumber == 1)
                {
                    image = up0;
                }

                if (spriteNumber == 2)
                {
                    image = up1;
                }
                break;
            }
            case Down:
            {
                if (spriteNumber == 1)
                {
                    image = down0;
                }

                if (spriteNumber == 2)
                {
                    image = down1;
                }
                break;
            }
            case Right:
            {
                if (spriteNumber == 1)
                {
                    image = right0;
                }

                if (spriteNumber == 2)
                {
                    image = right1;
                }
                break;
            }
            case Left:
            {
                if (spriteNumber == 1)
                {
                    image = left0;
                }

                if (spriteNumber == 2)
                {
                    image = left1;
                }
                break;
            }
        }
        g2.drawImage(image, screenX, screenY, width * scale, height * scale, null);
        
        g2.setColor(Color.red);
        //g2.drawRect(this.screenX + 8, this.screenY + 16, 32, 32);
        g2.drawRect(screenX + solid.x, screenY + solid.y, solid.width, solid.height);
    }
}
/*
switch (direction)
            {
                case Up:
                {
                    if (current == up0)
                    {
                        image = up1;
                    }
                    else
                    {
                        image = up0;
                    }
                    break;
                }
                case Down:
                {
                    if (current == down0)
                    {
                        image = down1;
                    }
                    else
                    {
                        image = down0;
                    }
                    break;
                }
                case Right:
                {
                    if (current == right0)
                    {
                        image = right1;
                    }
                    else
                    {
                        image = right0;
                    }
                    break;
                }
                case Left:
                {
                    if (current == left0)
                    {
                        image = left1;
                    }
                    else
                    {
                        image = left0;
                    }
                    break;
                }
            }
*/