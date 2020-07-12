package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import encodingstrategies.*;
import text2speechapis.*;

public class Document {

	private ArrayList<Line> contents;
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	private String author;
	private String title;
	private LocalDate creationDate;
	private LocalDate savedDate;
	
	public Document() {
		this.contents = new ArrayList<Line>();
		TextToSpeechAPIFactory fact = new TextToSpeechAPIFactory();
		audioManager = fact.createTTSAPI("real");
		author = "None";
		title = "None";
	}

	//setters
	public void setTTSAPI(TextToSpeechAPI adapter) {
		this.audioManager = adapter;
	}
	
	public void setCreationDate(LocalDate date) {
		this.creationDate = date;
	}

	public void setSavedDate(LocalDate date) {
		this.savedDate = date;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContents(ArrayList<Line> contents) {
		this.contents = contents;
	}

	
	//getters
	public String getAuthor() {
		return author;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public LocalDate getSavedDate() {
		return savedDate;
	}

	public String getTitle() {
		return title;
	}

	public ArrayList<Line> getContents() {
		return contents;
	}

	//Text2speech functions
	public void playContents() {
		//convert contents into a string
		StringBuilder contentStringBuilder = new StringBuilder();
		for (int k=0; k<contents.size(); k++) {
			for (String s : contents.get(k).getWords()) 
			{
				if(s.length() == 0){
					contentStringBuilder.append(" ");
				}
				else {
					contentStringBuilder.append(s);
				}
			}
			contentStringBuilder.append("\n");
		}
		audioManager.play(contentStringBuilder.toString());
	}

	public void playReverseContents() {
		//convert contents into a string
		String contentString = new String();
		for (int k=0; k<contents.size(); k++) {
			for (String s : contents.get(k).getWords()) 
			{
				contentString += " " + s;
			}
			contentString += "\n";
		}
		
		//reverse the string
		String string = "";
	    String[] words = contentString.toString().split("\\n");
	    for (int j = 0; j < words.length; j++) {
		    String temp[] = words[j].toString().split(" ");
		    for (int k = 0; k < temp.length; k++) {
		    	string = temp[k] + " " + string;
		    }
	    }
	   audioManager.play(string);
	}

	public void playLine(int num) {
		//check if the row num returned from LineToSpeech is within limits and then if the row is empty
		if(num <= contents.size()){
			if(!contents.get(num-1).getWords().isEmpty()) {
				//call Line with the correct line text
				Line lineVoice = new Line(contents.get(num-1).getWords(),audioManager);
				lineVoice.playLine();
			}
		}
	}
	
	public void playReverseLine(int num) {
		//check if the row num returned from LineToSpeech is within limits
		if(num <= contents.size()) {
			//create string for reverse process 		    
		    String reverseLine = "";
		    for(int i=0; i<contents.get(num-1).getWords().size(); i++) { 
		    	reverseLine = contents.get(num-1).getWords().get(i) + " " + reverseLine;
		    }
		    //create arraylist from string for Line constructor
			ArrayList<String> reverseLineArray = new ArrayList<String>(Arrays.asList(reverseLine.split(" ")));
			    
			//call Line with the correct line text
			Line lineVoice = new Line(reverseLineArray, audioManager);
			lineVoice.playReverseLine();
		}
	}

	//Encoding functions
	public void playEncodedContents() {
		//if encodingStrategy doesnt exist dont proceed
		if(encodingStrategy == null) {
			return;
		}
		StringBuilder contentStringBuilder = new StringBuilder();
		for (int k=0; k<contents.size(); k++) {
			for (String s : contents.get(k).getWords()) 
			{
				if(s.length() == 0){
					contentStringBuilder.append(" ");
				}
				else {
					contentStringBuilder.append(s);
				}
			}
			contentStringBuilder.append("\n");
		}
		audioManager.play(encodingStrategy.encode(contentStringBuilder.toString()));
	}

	public void playEncodedLine(int num) {
		//if encodingStrategy doesnt exist dont proceed
		if(encodingStrategy == null) {
			return;
		}
		//check if the row num returned from LineToSpeech is within limits and then if the row is empty
		if(num <= contents.size()){
			if(!contents.get(num-1).getWords().isEmpty()) {
				//call Line with the correct line text
				Line lineVoice = new Line(contents.get(num-1).getWords(),encodingStrategy,audioManager);
				lineVoice.playEncodedLine();
			}
		}
	}

	public void tuneEncodingStrategy(EncodingStrategy encodingStrategy) {
		this.encodingStrategy = encodingStrategy;
	}
	
	public String getEncodingStrategy() {
		return encodingStrategy.toString();
	}
	
	public void tuneAudioSettings(int volume, int rate, int pitch) {
		audioManager.setVolume(volume);
		audioManager.setRate(rate);
		audioManager.setPitch(pitch);
	}
}
