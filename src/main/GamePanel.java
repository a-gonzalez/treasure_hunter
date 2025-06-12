package main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Player;
import tile.Background;
import item.*;

public class GamePanel extends JPanel implements Runnable
{
    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumns = 16;
    public final int maxScreenRows = 12;
    public final int screenWidth = tileSize * maxScreenColumns; // 768
    public final int screenHeight = tileSize * maxScreenRows; // 576
    final int FPS = 60;

    Thread thread;
    Control control = new Control(this);
    Sound music = new Sound();
    Sound effect = new Sound();
    Background background = new Background(this);
    
    public Collision collision = new Collision(this);
    public Assets assets = new Assets(this);
    public UI ui = new UI(this);
    
    // entity and item
    public Player player = new Player(this, control);
    public Item items[] = new Item[10];

    // World settings
    public final int maxWorldColumns = 50;
    public final int maxWorldRows = 50;
    
    public State state;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(control);
        this.setFocusable(true);
    }

    public void setup()
    {
        //assets.setItems();

        startMusic(0);

        state = State.Play;
    }

    public void start()
    {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        double interval = 1000000000 / FPS; // 0.01666 seconds
        double delta_time = 0;
        long previous_time = System.nanoTime();
        long current_time = 0;
        
        /*long timer = 0;
        int count = 0;*/

        while (thread != null) // game loop
        {
            current_time = System.nanoTime();
            delta_time += (current_time - previous_time) / interval;
            //timer += (current_time - previous_time);
            previous_time = current_time;

            //System.out.format("Current: %d\n", current);

            if (delta_time >= 1)
            {
                // Update: update information. ie character position information
                update(delta_time);

                // Draw: draw the screen with update information
                repaint();

                delta_time--;
                //count++;
            }
        }
    }

    public void update(double delta_time)
    {
        if (state == State.Play)
        {
            player.update(delta_time);
        }

        if (state == State.Pause)
        {
            // later, later
        }
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        //System.out.format("X: %d - Y: %d\n", x, y);

        Graphics2D g2 = (Graphics2D) graphics;
        /*g2.setColor(Color.white);
        g2.fillRect(x, y, tileSize, tileSize);*/
        
        //long drawStart = System.nanoTime();

        // environment tiles
        background.draw(g2);

        // game assets - keys, doors, treasure chest..
        /*for (int index = 0; index < items.length; index++)
        {
            if (items[index] != null)
            {
                items[index].draw(g2, this);
            }
        }*/

        player.draw(g2);
        ui.draw(g2);

        //long drawEnd = System.nanoTime();
        //long elapsedTime = drawEnd - drawStart;

        /*g2.setColor(Color.white);
        g2.drawString("Draw Time: " + elapsedTime, 10, 400);*/

        g2.dispose();
    }

    public void startMusic(int index)
    {
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playEffect(int index)
    {
        effect.setFile(index);
        effect.play();
    }
}