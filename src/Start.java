import javax.sound.sampled.AudioFormat;

import net.beadsproject.beads.core.*;
import net.beadsproject.beads.data.*;
import net.beadsproject.beads.ugens.*;

public class Start {
	// create our AudioContext
	static AudioContext ac;
	static Gain g;
	static RecordToSample rts;
	// this object will hold our audio data
	static Sample targetSample;
	static boolean recording = false;

	public static void main(String args[]) {
		ac = new AudioContext();
		
		// create a WavePlayer
		// WavePlayer objects generate a waveform
		WavePlayer wp = new WavePlayer(ac, 440, Buffer.SINE);
		// connect the WavePlayer to the AudioContext
		ac.out.addInput(wp);
		// start audio processing

		try {
			ac.start();
			Thread.sleep(4000);
			ac.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
