package text2speechapis;

public class TextToSpeechAPIFactory {

	private TextToSpeechAPI dummyAudioManager;
	
	public TextToSpeechAPIFactory() {

	}
	
	public TextToSpeechAPI createTTSAPI(String text) {
		if(text == "real") {
			TextToSpeechAPI freeAudioManager = new FreeTTSAdapter();
			return freeAudioManager;
		}
		else if(text == "fake") {
			TextToSpeechAPI fakeAudioManager = new FakeTextToSpeechAPI();
			return fakeAudioManager;
		}
		System.out.println("Adapter not found.");
		return dummyAudioManager;
	}
}