package item;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Key extends Item
{
    public Key()
    {
        name = "Key";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/key.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}