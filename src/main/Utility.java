package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Utility
{
    public BufferedImage scale(BufferedImage original, int width, int height)
    {
        BufferedImage scaled = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaled.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaled;
    }
}