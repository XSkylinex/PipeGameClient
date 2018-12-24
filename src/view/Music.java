package view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music{

    Long currentFrame;
    Clip clip;

    String status;

    AudioInputStream audioInputStream;
    private final String pokemonSound = "./resources/Poekmon_opening.WAV";

    public Music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(pokemonSound).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play(){
            clip.start();
            status = "play";

    }

    public void stop(){
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(pokemonSound).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}