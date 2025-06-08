package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener
{
    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent event)
    {
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        //System.out.format("Code: %d\n", event.getKeyCode());

        int code = event.getKeyCode();

        if (code == KeyEvent.VK_UP)
        {
            up = true;
        }

        if (code == KeyEvent.VK_DOWN)
        {
            down = true;
        }

        if (code == KeyEvent.VK_LEFT)
        {
            left = true;
        }

        if (code == KeyEvent.VK_RIGHT)
        {
            right = true;
            //direction = Direction.Right;
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
        int code = event.getKeyCode();

        if (code == KeyEvent.VK_UP)
        {
            up = false;
        }

        if (code == KeyEvent.VK_DOWN)
        {
            down = false;
        }

        if (code == KeyEvent.VK_LEFT)
        {
            left = false;
        }

        if (code == KeyEvent.VK_RIGHT)
        {
            right = false;
        }
    }
}
//  8 - Backspace
//  9 - Tab
// 10 - Enter
// 12 - Clear
// 16 - Shift
// 17 - Ctrl
// 16 - Shift
// 18 - Alt
// 37 - Left
// 38 - Up
// 39 - Right
// 40 - Down
// 65 - A
// 66 - B
// 67 - C
// 68 - D
// 69 - E
// 70 - F
// 71 - G
// 72 - H
// 73 - I
// 74 - J