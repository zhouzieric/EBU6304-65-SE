import javax.swing.*;


import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Tutorial {

    private final AudioPlayerComponent mediaPlayerComponent;

    public static void main(String[] args) {
        Tutorial tutorial = new Tutorial();
        tutorial.start("C:\\Users\\Lenovo\\Videos\\Captures\\Stardew Valley 2021-01-17 20-15-54.mp4");
        try {
            Thread.currentThread().join();
        }
        catch(InterruptedException e) {
        }
    }

    private Tutorial() {
        mediaPlayerComponent = new AudioPlayerComponent();
        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                exit(0);
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                exit(1);
            }
        });
    }

    private void start(String mrl) {
        mediaPlayerComponent.mediaPlayer().media().play(mrl);
    }

    private void exit(int result) {
        // It is not allowed to call back into LibVLC from an event handling thread, so submit() is used
        mediaPlayerComponent.mediaPlayer().submit(new Runnable() {
            @Override
            public void run() {
                mediaPlayerComponent.mediaPlayer().release();
                System.exit(result);
            }
        });
    }
}