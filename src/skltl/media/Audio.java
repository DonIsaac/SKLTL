package skltl.media;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio implements Disposable{

	private Clip c;
	private boolean isLooping=false;

	private Audio() {

	}

	protected Audio(File file) {

		try {
			c = AudioSystem.getClip();
			c.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

	}

	public void play() {
		c.start();
	}

	public void pause() {
		c.stop();
	}
	public void stop(){
		c.stop();
		c.setMicrosecondPosition(0L);
	}

	public void restart() {
		c.setMicrosecondPosition(0L);
	}

	public long getDuration(){
		return c.getMicrosecondLength();
	}
	public void setLooping(boolean loop){
		if(loop)
			c.loop(Clip.LOOP_CONTINUOUSLY);
		
		else
			c.loop(0);
		isLooping=loop;
		
	}
	public boolean isLooping(){
		return isLooping;
	}
	public void dispose(){
		c.flush();
		c.close();
	}
}
