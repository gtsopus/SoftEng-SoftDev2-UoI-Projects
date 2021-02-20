package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;

public class TuneEncoding implements ActionListener {
	
	private Document curDocument;
	private int choice;
	
	private ArrayList<ActionListener> commandList = new ArrayList<ActionListener>();
	private ArrayList<Boolean> replayBool = new ArrayList<Boolean>();
	
	//bool that check if command is a copy of another
	boolean isReplayed = false;
	
	//constructor
	public TuneEncoding(Document curDocument, int choice,ArrayList<ActionListener> commandList,ArrayList<Boolean> replayBool) {
		this.curDocument = curDocument;
		this.choice = choice;
		this.commandList = commandList;
		this.replayBool = replayBool;
	}
	
	//replay constructor
	public TuneEncoding(Document curDocument, int choice,ArrayList<ActionListener> commandList) {
		this.curDocument = curDocument;
		this.choice = choice;
		this.commandList = commandList;
		isReplayed = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//check if document exists
		if(curDocument == null) {
			System.out.println("No document found");
			return;
		}
		//create encoding strategy factory
		EncodingStrategy encodingStrategy;
		StrategiesFactory factory = new StrategiesFactory();
		//check which encoding has been chosen
		if(choice == 1) {
			encodingStrategy = factory.createStrategy("atbash");
			curDocument.tuneEncodingStrategy(encodingStrategy);
			
			//tune encoding for all the lines in doc
			for(int i=0; i<curDocument.getContents().size(); i++) {
				curDocument.getContents().get(i).tuneEncodingStrategy(encodingStrategy);
			}
			
			//check if we are recording commands
			if(!isReplayed) {
				if(replayBool.get(0) == true) {
					TuneEncoding copy = new TuneEncoding(curDocument,choice,commandList);
					commandList.add(copy);
				}
			}
		}
		else if(choice == 2) {
			encodingStrategy = factory.createStrategy("rot13");
			curDocument.tuneEncodingStrategy(encodingStrategy);
			
			//tune encoding for all the lines in doc
			for(int i=0; i<curDocument.getContents().size(); i++) {
				curDocument.getContents().get(i).tuneEncodingStrategy(encodingStrategy);
			}
			
			//check if we are recording commands
			if(!isReplayed) {
				if(replayBool.get(0) == true) {
					TuneEncoding copy = new TuneEncoding(curDocument,choice,commandList);
					commandList.add(copy);
				}
			}
		}
	}
}
