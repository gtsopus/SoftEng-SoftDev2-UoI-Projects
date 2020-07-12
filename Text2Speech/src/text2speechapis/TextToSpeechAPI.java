package text2speechapis;

public interface TextToSpeechAPI {
	public void play(String text);
	
	public void setVolume(int volume);
	
	public void setRate(int rate);
	
	public void setPitch(int pitch);

	public int getPitch();
	
	public int getRate();
	
	public int getVolume();

}
