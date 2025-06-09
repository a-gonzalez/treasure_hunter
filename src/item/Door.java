package item;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Door extends Item
{
    public Door()
    {
        type = Type.Door;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/img/item/door.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        collision = true;
    }
}