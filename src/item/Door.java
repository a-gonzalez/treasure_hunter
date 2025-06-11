package item;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Door extends Item
{
    public Door(GamePanel panel)
    {
        type = Type.Door;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/door.png"));
            utility.scale(image, panel.tileSize, panel.tileSize);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        collision = true;
    }
}