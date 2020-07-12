package command;

import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.*;
import model.Document;
import model.Line;

public class OpenDocument implements ActionListener{
	
	//GUI Components
	JTextArea textArea;
	JFileChooser fc;
	JFrame frame;
	
	//Document object carried over from view.
	Document curDocument;
	
	StringBuilder sb = new StringBuilder();
	ArrayList<Line> contents = new ArrayList<Line>();
	
	LocalDate creationDate;
	LocalDate savedDate;
	String author = "";
	String title = "";
	
	private ArrayList<ActionListener> commandList = new ArrayList<ActionListener>();
	private ArrayList<Boolean> replayBool = new ArrayList<Boolean>();
	
	int test = 0;
	String replayString = "";
	String textAreaRe = "";
	
	//bool that check if command is a copy of another
	boolean isReplayed = false;
		
	//constructor
	public OpenDocument(JTextArea textArea, JFrame frame, Document curDocument,ArrayList<ActionListener> commandList,ArrayList<Boolean> replayBool) {
		this.textArea = textArea;
		this.frame = frame;
		this.curDocument = curDocument;
		this.commandList = commandList;
		this.replayBool = replayBool;
	}
	
	//test constructor
	public OpenDocument(JTextArea textArea, JFrame frame, Document curDocument, int test) {
		this.textArea = textArea;
		this.frame = frame;
		this.curDocument = curDocument;
		this.test = test;
	}
	
	//replay constructor
	public OpenDocument(JTextArea textArea,String textAreaRe, JFrame frame, Document curDocument,ArrayList<Line> contents,  String author, String title, LocalDate creationDate, LocalDate savedDate,ArrayList<ActionListener> commandList) {
		this.textAreaRe = textAreaRe;
		this.textArea = textArea;
		this.frame = frame;
		this.curDocument = curDocument;
		this.contents = contents;
		this.author = author;
		this.title = title;
		this.creationDate = creationDate;
		this.savedDate = savedDate;
		this.commandList = commandList;
		isReplayed = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		//create file chooser
        fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
		
		//for testing pass existing file
		if (test == 1) {
			fc.setSelectedFile(new File("testFile.txt"));
		}
		else if(test==0 && isReplayed==false){
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal != JFileChooser.APPROVE_OPTION) {
				return;
			}
		}
		
		if(isReplayed==false) {
	        File file = fc.getSelectedFile();            
	        Scanner input;
	           
			try {
				//reset caret position
				if (test == 0) {
					textArea.setCaretPosition(0);
				}
				input = new Scanner(file);
				
				//load stringBuilder with title,author,saved date and creation date.
				for(int lineNumber=0; lineNumber<4; lineNumber++){
					sb.append(input.nextLine());
	            	sb.append("\n");
				}
				
				/* Header format:
				 * Author: ""
				 * Title: ""
				 * Creation Date: ""
				 * Saved Date: ""
				 */
				String[] files = sb.toString().split("\\r?\\n");
				author = files[0].substring(8);
				title = files[1].substring(7);
				String creationDateStr = files[2].substring(15);
				String savedDateStr = files[3].substring(12);
				
				//convert String to LocalDate
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				creationDate = LocalDate.parse(creationDateStr, formatter);
				savedDate = LocalDate.parse(savedDateStr, formatter);
				
				//reset StringBuilder buffer
				sb.setLength(0);
				
				//stringBuilder for testing
				StringBuilder tsb = new StringBuilder();
				
				//empty contents from before
				contents.clear();
				
				//load StringBuilder and Line class with the rest of the file
				while(input.hasNext()) {
					String nextLine = input.nextLine();
	            	sb.append(nextLine);
	            	sb.append("\n");
	            	
	            	tsb.append(nextLine);
	            	tsb.append("\n");
	            	
	            	//split line in words and append them in an arraylist
	            	ArrayList<String> words = new ArrayList<String>(Arrays.asList(tsb.toString().split("\\s")));
	            	Line curLine = new Line(words, null, null);
	            	
	            	//add word arraylist in Document's content
	            	contents.add(curLine);
	            	
	            	tsb.setLength(0);
	            }
	            input.close();
	   
				if(test == 0) {
					textArea.setText(sb.toString());
					frame.setTitle("Text2Speech Editor" + " Author: " + author + " Title: " + title);
				}
				
	            //reset StringBuilder buffer
	            sb.setLength(0);
	            	            
			} catch (FileNotFoundException e1) {
				//auto-generated catch block
				textArea.setText("File not found." + "\n");
				e1.printStackTrace();
			}
		}
		
		//if command is a copy then set textArea from constructor
		if(test==0 && isReplayed==true) {
			textArea.setText(textAreaRe.toString());
			frame.setTitle("Text2Speech Editor" + " Author: " + author + " Title: " + title);
		}
		
		//load document with title,author,creation date,saved date and text.
		if(author.equals("")) {
			curDocument.setAuthor("None");
		} else {
			curDocument.setAuthor(author);
		}
		if(title.equals("")) {
			curDocument.setTitle("None");
		} else {
			curDocument.setTitle(title);
		}
		curDocument.setCreationDate(creationDate);
		curDocument.setSavedDate(savedDate);
		curDocument.setContents(contents);

		//check if we are recording commands
		if(!isReplayed && test == 0) {
			if(replayBool.get(0) == true) {
				OpenDocument copy = new OpenDocument(textArea, textArea.getText(), frame, curDocument, contents, author, title, creationDate, savedDate, commandList);
				commandList.add(copy);
			}
		}
	}
}
