package entity;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Control;
import main.GamePanel;
import main.Utility;

public class Player extends Entity
{
    GamePanel panel;
    Control control;
    BufferedImage current;

    public final int screenX;
    public final int screenY;
    public int keys = 0;

    int idleCounter = 0;
    int pixelCounter = 0;

    public Player(GamePanel panel, Control control)
    {
        this.panel = (GamePanel) panel;
        this.control = control;

        screenX = panel.screenWidth / 2 - (panel.tileSize / 2);
        screenY = panel.screenHeight / 2 - (panel.tileSize / 2);

        //solid = new Rectangle(8, 16, 32, 32);
        solid = new Rectangle(1, 1, 46, 46);
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
        moving = false;
    }

    public BufferedImage setup(String name)
    {
        Utility utility = new Utility();
        BufferedImage scaled = null;

        try
        {
            scaled = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/" + name + ".png"));
            scaled = utility.scale(scaled, panel.tileSize, panel.tileSize);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        return scaled;
    }

    public void getPlayerImages()
    {
        up0 = setup("up0");
        up1 = setup("up1");
        right0 = setup("right0");
        right1 = setup("right1");
        left0 = setup("left0");
        left1 = setup("left1");
        down0 = setup("down0");
        down1 = setup("down1");
    }

    public void update(double delta_time)
    {
        if (!moving)
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
                moving = true;
                collision = false;

                // check tile collision
                panel.collision.checkEntity(this);

                // check item collision
                int itemIndex = panel.collision.checkItem(this, true);

                pickUpItem(itemIndex);
            }
            else
            {// reset player sprite when idle for 20 frames
                ++idleCounter;

                if (idleCounter == 20)
                {
                    spriteNumber = 1;

                    idleCounter = 0;
                }
            }
        }
        
        if (moving)
        {
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
            pixelCounter += speed;

            if (pixelCounter == 48)
            {
                moving = false;
                pixelCounter = 0;
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
                        panel.ui.showMessage("You need a key to open this door.");
                    }
                    break;
                }
                case Chest :
                {
                    panel.ui.gameOver = true;
                    //panel.stopMusic();
                    panel.playEffect(4);

                    break;
                }
                case Boots :
                {
                    panel.playEffect(2);

                    speed += 2;
                    panel.items[index] = null;

                    panel.ui.showMessage("Power-Up - Speed");

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
        g2.drawImage(image, screenX, screenY, null);
        
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