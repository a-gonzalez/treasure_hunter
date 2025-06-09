package item;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Boots extends Item
{
    public Boots()
    {
        type = Type.Boots;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/boots.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        collision = true;
    }
}