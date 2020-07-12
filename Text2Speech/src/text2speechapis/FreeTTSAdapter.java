package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

//ALL FREETTS COPYRIGHTS BELONG TO THEIR RESPECTIVE OWNERS
//freetts.sourceforge.io

public class FreeTTSAdapter implements TextToSpeechAPI{
	
	private Voice voice;
	private VoiceManager vm;
	
	int volume;
	int pitch;
	int rate;
	
	public FreeTTSAdapter() {
	    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

		//set a voice and initialize it
		this.vm = VoiceManager.getInstance();
		this.voice = vm.getVoice("kevin16");
		if(voice != null) {
			this.voice.allocate();
		}
		else {
	        throw new IllegalStateException("Cannot find voice: kevin16");
		}
	}

	@Override
	public void play(String text) {
		voice.speak(text);
	}

	//convert all ints from 0-100 range to float 0.0-1.0
	@Override
	public void setVolume(int volume) {
		this.volume = volume;
		float vol = (float)volume/(float)100;
		voice.setVolume(vol);
	}

	@Override
	public void setRate(int rate) {
		this.rate = rate;
		//set rate 1 to 1000
		voice.setRate(rate);
	}

	@Override
	public void setPitch(int pitch) {
		this.pitch = pitch;
		voice.setPitch(pitch);
	}
		
	@Override
	public int getPitch() {
		return pitch;
	}
	
	@Override
	public int getRate() {
		return rate;
	}
	
	@Override
	public int getVolume() {
		return volume;
	}
}
