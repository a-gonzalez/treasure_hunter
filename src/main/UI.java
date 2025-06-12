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
    Graphics2D g2;
    Font arial_40;
    Font arial_80;
    int messageCounter = 0;
    double playTime = 0;
    DecimalFormat playTimeFormat = new DecimalFormat("#0.00");

    public boolean gameOver = false;
    public boolean messageOn = false;
    public String message = "";

    public UI (GamePanel panel)
    {
        this.panel = panel;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_80 = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        g2.setColor(Color.white);

        if (panel.state == State.Play)
        {
            // regular game ui draw
        }

        if (panel.state == State.Pause)
        {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen()
    {
        String text = "Game Paused";
        
        int y = panel.screenHeight / 2;
        int x = getXtoCenterText(text);

        g2.drawString(text, x, y);
    }

    public int getXtoCenterText(String text)
    {
        int width = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = panel.screenWidth / 2 - width / 2;

        return x;
    }
}