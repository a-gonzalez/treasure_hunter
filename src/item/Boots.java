package item;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Boots extends Item
{
    public Boots(GamePanel panel)
    {
        type = Type.Boots;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/boots.png"));
            utility.scale(image, panel.tileSize, panel.tileSize);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        collision = true;
    }
}