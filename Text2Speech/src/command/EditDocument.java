package command;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

import model.Document;
import model.Line; 

public class EditDocument implements ActionListener {
	
	private JTextArea textArea;
	private Document curDocument;
	
	private ArrayList<ActionListener> commandList = new ArrayList<ActionListener>();
	
	private ArrayList<Boolean> replayBool = new ArrayList<Boolean>();
	
	ArrayList<Line> contents = new ArrayList<Line>();
	
	//if test = 0 then we are not doing a JUnit Test
	int test = 0;
		
	//bool that check if command is a copy of another
	boolean isReplayed = false;
	
	public EditDocument(JTextArea textArea, Document curDocument,ArrayList<ActionListener> commandList,ArrayList<Boolean> replayBool) {
		this.textArea = textArea;
		this.curDocument = curDocument;
		this.commandList = commandList;
		this.replayBool = replayBool;
	}
	
	//replay constructor
	public EditDocument(JTextArea textArea, Document curDocument,ArrayList<ActionListener> commandList) {
		this.textArea = textArea;
		this.curDocument = curDocument;
		isReplayed = true;
		this.commandList = commandList;
	}
	
	//test constructor
	public EditDocument(JTextArea textArea, Document curDocument,int test) {
		this.textArea = textArea;
		this.curDocument = curDocument;
		this.test = test;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		ArrayList<String> lines = new ArrayList<String>();

		//Empty already existing contents
		contents = new ArrayList<Line>();		

		//for testing add a line to contents instead from text area.
		if (test == 1) {
			lines.add("test");
			curDocument.setContents(contents);
		}
		else{
			String l[] = textArea.getText().split("\\r?\\n"); 
			Collections.addAll(lines, l);
		
			//split each line by whitespace characters and add the split list to line class object which is then added to contents
			for(int i=0; i<lines.size(); i++) {
				ArrayList<String> words = new ArrayList<>(Arrays.asList(lines.get(i).split("\\s+")));
				Line temp = new Line(words);
				contents.add(temp);
			}
			//set the curDoc's contents
			curDocument.setContents(contents);
		}
		
		//check if we are recording commands
		if(!isReplayed && test==0) {
			if(replayBool.get(0) == true) {
				EditDocument copy = new EditDocument(textArea, curDocument, commandList);
				commandList.add(copy);
			}
		}
	}
}
