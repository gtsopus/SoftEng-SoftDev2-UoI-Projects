package command;

import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import model.Document;

public class NewDocument implements ActionListener {
	
	//GUI Components
	JTextArea textArea;
	JFrame frame;
	//Document object carried over from view.
	Document curDocument;

	String author = "";
	String title = "";
	
	private ArrayList<ActionListener> commandList = new ArrayList<ActionListener>();
	
	private ArrayList<Boolean> replayBool = new ArrayList<Boolean>();
	
	//private CommandFactory commandBoolValue = new CommandFactory(null,null,null,null,null,null);

	//if test = 0 then we have not overloaded the constructor so we are not doing a JUnit Test
	int test = 0;
	//bool that check if command is a copy of another
	boolean isReplayed = false;
	
	//constructor
	public NewDocument(JTextArea textArea, JFrame frame, Document curDocument,ArrayList<ActionListener> commandList,ArrayList<Boolean> replayBool) {
		this.textArea = textArea;
		this.frame = frame;
		this.curDocument = curDocument;
		this.commandList = commandList;
		this.replayBool = replayBool;
	}
	//overload for testing
	public NewDocument(JTextArea textArea, JFrame frame, Document curDocument, int test) {
		this.textArea = textArea;
		this.frame = frame;
		this.curDocument = curDocument;
		this.test = test;
	}
	//replay constructor
	public NewDocument(JTextArea textArea, JFrame frame, Document curDocument,String author, String title,ArrayList<ActionListener> commandList) {
		this.author = author;
		this.title = title;
		isReplayed = true;
		this.commandList = commandList;
		this.textArea = textArea;
		this.curDocument = curDocument;
		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createNewDocument();
	}

	public void createNewDocument() {
		//Reasons of use of JOptionPane simple popup:
		//Easy to use/get returned strings, Not alot of code needed, fast to implement and user friendly
		if(test == 0) {
			textArea.setText("");
			if(isReplayed == false) {
				author = (String)JOptionPane.showInputDialog(frame, "Please enter an author: ","Create new document.",JOptionPane.PLAIN_MESSAGE);
				title = (String)JOptionPane.showInputDialog(frame, "Please enter a title: ", "Create new document.",JOptionPane.PLAIN_MESSAGE);
				if(author == null) {
					author = "None";
				}
				else if(author.trim().length() == 0) {
					author = "None";
				}
				if(title == null) {
					title = "None";
				}
				else if(title.trim().length() == 0) {
					title = "None";
				}
			}
		}

		//Change document title,author and creation date.
		curDocument.setAuthor(author);
		curDocument.setTitle(title);
		curDocument.setCreationDate(LocalDate.now());
		curDocument.getContents().clear();

		//check if we are recording commands
		if(!isReplayed && test == 0) {
			if(replayBool.get(0) == true) {
				NewDocument copy = new NewDocument(textArea, frame,curDocument,author,title,commandList);
				commandList.add(copy);
			}
		}
		if(test == 0) {
			frame.setTitle("Text2Speech Editor" + " Author: " + curDocument.getAuthor() + " Title: " + curDocument.getTitle());

		}
	}
}
