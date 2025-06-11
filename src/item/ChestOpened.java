package item;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class ChestOpened extends Item
{
    public ChestOpened(GamePanel panel)
    {
        type = Type.ChestOpened;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/chest_opened.png"));
            utility.scale(image, panel.tileSize, panel.tileSize);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}