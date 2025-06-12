package main;

import javax.swing.JFrame;

public class Main
{
    public static void main(String[] arguments)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Treasure Hunter");

        GamePanel panel = new GamePanel();

        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setup();
        panel.start();

        //int[] array = { 1, 3, 5, 7, 9 };

        /*int[] array;
        array = new int[] { 2, 4, 6, 8 };*/
    }
}

// darth is 128 by 192, sprite is 32 by 48
// FPS == 60