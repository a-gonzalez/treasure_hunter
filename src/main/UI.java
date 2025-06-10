package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.text.DecimalFormat;

import item.Key;

public class UI
{
    GamePanel panel;
    BufferedImage key_icon;
    Font arial_40;
    Font arial_80;
    int messageCounter = 0;
    double playTime = 0;
    DecimalFormat playTimeFormat = new DecimalFormat("#0.00");

    public boolean gameOver = false;
    public boolean messageOn = false;
    public String message = "";

    public UI(GamePanel panel)
    {
        this.panel = panel;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_80 = new Font("Arial", Font.BOLD, 80);
        Key key = new Key();

        key_icon = key.image;
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2)
    {
        if (gameOver)
        {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(20f));

            String text = "You found the treasure!";
            int textWidth = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x, y;

            x = panel.screenWidth / 2 - textWidth / 2;
            y = panel.screenHeight / 2 - (panel.tileSize * 3);

            g2.drawString(text, x, y);

            x = panel.screenWidth / 2 - textWidth / 2;
            y = panel.screenHeight / 2 + (panel.tileSize * 4);

            text = "Your total game-time is: " + playTimeFormat.format(playTime);
            textWidth = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            
            g2.drawString(text, x, y);

            x = panel.screenWidth / 2 - textWidth / 2;
            y = panel.screenHeight / 2 - (panel.tileSize * 3);

            g2.setColor(Color.yellow);
            g2.setFont(arial_80);

            text = "Congratulations!";
            textWidth = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = panel.screenWidth / 2 - textWidth / 2;
            y = panel.screenHeight / 2 + (panel.tileSize * 2);

            g2.drawString(text, x, y);

            panel.thread = null;
        }
        else
        {
            g2.setFont(arial_40);
            g2.setColor(Color.yellow);
            g2.drawImage(key_icon, panel.tileSize / 1, panel.tileSize / 2, panel.tileSize, panel.tileSize, null);
            g2.drawString(Integer.toString(panel.player.keys), panel.tileSize * 2, 65);

            // play time
            playTime += (double) 1 / 60;

            g2.drawString("Time: " + playTimeFormat.format(playTime), panel.tileSize * 11, 65);

            // messages
            if (messageOn)
            {
                //g2.setFont(arial_20);
                g2.setFont(g2.getFont().deriveFont(20f));
                g2.setColor(Color.white);
                g2.drawString(message, panel.tileSize / 2, panel.tileSize * 5);

                ++messageCounter;

                if (messageCounter > 120)
                {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }
}