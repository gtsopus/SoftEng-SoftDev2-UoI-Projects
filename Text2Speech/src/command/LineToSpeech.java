package command;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import model.Document;

public class LineToSpeech implements ActionListener{
	
	Document curDocument;
	JTextArea textArea;
	
	int chosenFunc = 0;
	/*
	  chosenFunc can be one of the following numbers:
	  1 --> playDocument
	  2 --> playReverseDocument
	  3 --> playLine
	  4 --> playReverseLine
	  5 --> playEncodedDocument
	  6 --> playEncodedLine
	*/
	
	private ArrayList<ActionListener> commandList = new ArrayList<ActionListener>();
	private ArrayList<Boolean> replayBool = new ArrayList<Boolean>();
	
	int caretPos = 0;
	int test = 0;
	
	//bool that check if command is a copy of another
	boolean isReplayed = false;
	
	//constructor
	public LineToSpeech(JTextArea textArea, Document curDocument, int chosenFunc,ArrayList<ActionListener> commandList,ArrayList<Boolean> replayBool) {
		this.textArea = textArea;
		this.curDocument = curDocument;
		this.chosenFunc = chosenFunc;
		this.commandList = commandList;
		this.replayBool = replayBool;
	}
	
	//test constructor
	public LineToSpeech(int test,JTextArea textArea, Document curDocument, int chosenFunc,ArrayList<ActionListener> commandList,ArrayList<Boolean> replayBool) {
		this.test = test;
		this.textArea = textArea;
		this.curDocument = curDocument;
		this.chosenFunc = chosenFunc;
		this.commandList = commandList;
		this.replayBool = replayBool;
	}
	
	//replay constructor
	public LineToSpeech(JTextArea textArea, Document curDocument, int chosenFunc,int caretPos, ArrayList<ActionListener> commandList) {
		this.textArea = textArea;
		this.curDocument = curDocument;
		this.chosenFunc = chosenFunc;
		this.commandList = commandList;
		isReplayed = true;
		this.caretPos = caretPos;
	}
	
	
	public int getRowNumber() {
		int caretPos = textArea.getCaretPosition();
		int rowNum = (caretPos == 0) ? 1 : 0;
		for (int offset = caretPos; offset > 0;) {
		    try {
				offset = Utilities.getRowStart(textArea, offset) - 1;
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		    rowNum++;
		}
		return rowNum;
	}
	
	public void setChosenFunc(int num) {
		this.chosenFunc = num;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(curDocument.getContents().size() == 0) {
			System.out.print("empty document");
			return;
		}
		if(chosenFunc == 1) {
			curDocument.playContents();
			//check if we are recording commands
			if(!isReplayed && test == 0) {
				if(replayBool.get(0) == true) {
					LineToSpeech copy = new LineToSpeech(textArea, curDocument, chosenFunc, caretPos,  commandList);
					commandList.add(copy);
				}
			}
		} else if(chosenFunc == 2) {
			curDocument.playReverseContents();
			//check if we are recording commands
			if(!isReplayed && test == 0) {
				if(replayBool.get(0) == true) {
					LineToSpeech copy = new LineToSpeech(textArea, curDocument, chosenFunc, caretPos,  commandList);
					commandList.add(copy);
				}
			}
		} else if(chosenFunc == 3) {
			if(isReplayed == false) {
				curDocument.playLine(this.getRowNumber());
			}
			else{curDocument.playLine(caretPos);}
			//check if we are recording commands
			if(!isReplayed && test == 0) {
				if(replayBool.get(0) == true) {
					LineToSpeech copy = new LineToSpeech(textArea, curDocument, chosenFunc, caretPos,  commandList);
					commandList.add(copy);
				}
			}
		} else if(chosenFunc == 4){
			if(isReplayed == false) {
				curDocument.playReverseLine(this.getRowNumber());
			}
			
			else{curDocument.playReverseLine(caretPos);}
			//check if we are recording commands
			if(!isReplayed && test == 0) {
				if(replayBool.get(0) == true) {
					LineToSpeech copy = new LineToSpeech(textArea, curDocument, chosenFunc, caretPos,  commandList);
					commandList.add(copy);
				}
			}
		} else if(chosenFunc == 5) {
			curDocument.playEncodedContents();
			//check if we are recording commands
			if(!isReplayed && test == 0) {
				if(replayBool.get(0) == true) {
					LineToSpeech copy = new LineToSpeech(textArea, curDocument, chosenFunc, caretPos,  commandList);
					commandList.add(copy);
				}
			}
		} else if(chosenFunc == 6) {
			if(isReplayed == false) {
				curDocument.playEncodedLine(this.getRowNumber());
			}
			else{curDocument.playEncodedLine(caretPos);}
			//check if we are recording commands
			if(!isReplayed && test == 0) {
				if(replayBool.get(0) == true) {
					LineToSpeech copy = new LineToSpeech(textArea, curDocument, chosenFunc, caretPos,  commandList);
					commandList.add(copy);
				}
			}
		}
	}
}