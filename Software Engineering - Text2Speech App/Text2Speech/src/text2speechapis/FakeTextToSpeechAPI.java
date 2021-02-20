package text2speechapis;

public class FakeTextToSpeechAPI implements TextToSpeechAPI{
	 
	private int volume;
	private int rate;
	private int pitch;
	
	private String testOutput;
	
	public FakeTextToSpeechAPI(){

	}
	
	@Override
	public void play(String text) {
		System.out.println(text);
		testOutput = new String(text);
	}
	
	//getters
	public String getTestOutput() {
		return testOutput;
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
	
	//setters
	@Override
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	@Override
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	@Override
	public void setPitch(int pitch) {
		this.pitch = pitch;
	}
}