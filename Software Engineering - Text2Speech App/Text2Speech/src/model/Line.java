package model;

import java.util.ArrayList;
import encodingstrategies.*;
import text2speechapis.*;

public class Line {
	private ArrayList<String> words;
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	
	public Line(ArrayList<String> words, EncodingStrategy encodingStrategy, TextToSpeechAPI audioManager) {
		this.words = words;
		this.encodingStrategy = encodingStrategy;
		this.audioManager = audioManager;
	}
	
	public Line(ArrayList<String> words) {
		this.words = words;
		TextToSpeechAPIFactory fact = new TextToSpeechAPIFactory();
		audioManager = fact.createTTSAPI("real");
	}
	public Line(ArrayList<String> words, TextToSpeechAPI audioManager) {
		this.words = words;
		this.audioManager = audioManager;
	}
	
	//getter
	public ArrayList<String> getWords() {
		return words;
	}
	
	// Text2Speech & Encoding functions
	public void playLine() {
		//create string from arraylist
		String voice = "";
		for (int j = 0; j < words.size(); j++) {
			if(words.get(j).length() == 0){
				voice = voice + " ";
			}
			else {
				voice = voice + words.get(j);
			}
		}
		
		//play text
		audioManager.play(voice);
	}
	
	public void playReverseLine() {
		//create string from arraylist
		String voice = words.get(0);
		for (int j = 1; j < words.size(); j++) {
				voice = voice + " " + words.get(j);
		}

		//play text
		audioManager.play(voice);
	}
	
	public void playEncodedLine() {
		//create string from arraylist
		String voice = "";
		for(int i=0; i<words.size(); i++) {
			voice += words.get(i);
			voice += " ";
		}
		//play text
		audioManager.play(encodingStrategy.encode(voice));
	}
	
	public void tuneEncodingStrategy(EncodingStrategy encodingStrategy) {
		this.encodingStrategy = encodingStrategy;
	}
}
