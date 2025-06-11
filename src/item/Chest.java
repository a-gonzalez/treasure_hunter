package item;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Chest extends Item
{
    public Chest(GamePanel panel)
    {
        type = Type.Chest;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/chest.png"));
            utility.scale(image, panel.tileSize, panel.tileSize);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        collision = true;
    }
}