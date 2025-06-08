package item;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Chest extends Item
{
    public Chest()
    {
        name = "Chest";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/chest.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}