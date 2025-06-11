package item;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Key extends Item
{
    public Key(GamePanel panel)
    {
        type = Type.Key;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/key.png"));
            utility.scale(image, panel.tileSize, panel.tileSize);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        collision = true;
    }
}