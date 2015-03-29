import javax.sound.sampled.AudioFormat;

import net.beadsproject.beads.core.*;
import net.beadsproject.beads.data.*;
import net.beadsproject.beads.ugens.*;

public class Start {
	// create our AudioContext
	static AudioContext ac;
	static Gain masterGain;
	static RecordToSample rts;
	// this object will hold our audio data
	static Sample targetSample;
	static boolean recording = false;

	public static void main(String args[]) {
		// initialize the AudioContext
		ac = new AudioContext();
		// get an AudioInput UGen from the AudioContext
		// this will setup an input from whatever input is your
		// default audio input (usually the microphone in)
		UGen microphoneIn = ac.getAudioInput();
		// setup the recording unit generator
		try {
			// setup a recording format
			AudioFormat af = new AudioFormat(44100.0f, 16, 1, true, true);
			// create a holder for audio data
			targetSample = new Sample(44100);
			// initialize the RecordToSample object
			rts = new RecordToSample(ac, targetSample, RecordToSample.Mode.INFINITE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// connect the microphone input to the RecordToSample
		rts.addInput(microphoneIn);
		// tell the AudioContext to work with the RecordToSample
		ac.out.addDependent(rts);
		// pause the RecordToSample object
		rts.pause(true);
		// set up our usual master gain object
		masterGain = new Gain(ac, 1);
		masterGain.addInput(microphoneIn);
		
		ac.out.addInput(masterGain);

		try {
			ac.start();
			Thread.sleep(4000);
			ac.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
