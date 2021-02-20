package tests;

import static org.junit.Assert.*;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

public class TuneAudioTest {
	
	@Test
	public void test() {
		TextToSpeechAPIFactory fact = new TextToSpeechAPIFactory();
		TextToSpeechAPI audioManager = fact.createTTSAPI("fake");
		
		int randomPitch = ThreadLocalRandom.current().nextInt(1, 500 + 1);
		int randomRate =  ThreadLocalRandom.current().nextInt(1, 1000 + 1);
		int randomVolume =ThreadLocalRandom.current().nextInt(1, 100 + 1);
		
		audioManager.setPitch(randomPitch);
		audioManager.setRate(randomRate);
		audioManager.setVolume(randomVolume);
		
		if(audioManager.getPitch() == randomPitch && audioManager.getRate() == randomRate && audioManager.getVolume() == randomVolume) {
			System.out.println("Test Case: TuneAudio test passed.");
		}
		else {
			fail("Test Case: TuneAudio test failed.");
		}
	}
}
