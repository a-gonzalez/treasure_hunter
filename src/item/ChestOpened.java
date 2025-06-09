package item;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ChestOpened extends Item
{
    public ChestOpened()
    {
        type = Type.ChestOpened;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/chest_opened.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}