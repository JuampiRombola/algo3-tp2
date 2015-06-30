package algoCraft;

import java.io.FileInputStream;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Musica implements java.applet.AudioClip {
	private AudioData audiodata;
	private AudioDataStream audiostream;
	private ContinuousAudioDataStream continuousaudiostream;
	private boolean estaActiva;
  
	public Musica(String filename) throws java.io.IOException {
		FileInputStream fis = new FileInputStream(filename);
		@SuppressWarnings("resource")
		AudioStream audioStream = new AudioStream(fis);
		audiodata = audioStream.getData();
		audiostream = null;
		continuousaudiostream = null;
	}

	public void play() {
		audiostream = new AudioDataStream(audiodata);
		AudioPlayer.player.start(audiostream);
	}

	public void loop() {
		continuousaudiostream = new ContinuousAudioDataStream(audiodata);
		AudioPlayer.player.start(continuousaudiostream);
		estaActiva = true;
	}

	public void stop() {
		estaActiva = false;
		if (audiostream != null)
			AudioPlayer.player.stop(audiostream);
		if (continuousaudiostream != null)
			AudioPlayer.player.stop(continuousaudiostream);
		}

	public boolean estaActiva() {
		return estaActiva;
	}
}