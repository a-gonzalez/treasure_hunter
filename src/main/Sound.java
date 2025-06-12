package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
    Clip clip;
    URL url[] = new URL[10];

    public Sound()
    {
        url[0] = getClass().getResource("/resources/aud/blueboyadventure.wav");
        url[1] = getClass().getResource("/resources/aud/coin.wav");
        url[2] = getClass().getResource("/resources/aud/powerup.wav");
        url[3] = getClass().getResource("/resources/aud/unlock.wav");
        url[4] = getClass().getResource("/resources/aud/fanfare.wav");
    }

    public void play()
    {
        clip.start();
    }

    public void setFile(int index)
    {
        try
        {
            AudioInputStream stream = AudioSystem.getAudioInputStream(url[index]);
            clip = AudioSystem.getClip();
            clip.open(stream);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop()
    {
        clip.stop();
    }
}