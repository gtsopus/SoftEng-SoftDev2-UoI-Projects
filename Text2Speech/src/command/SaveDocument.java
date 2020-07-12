package command;

import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Document; 

public class SaveDocument implements ActionListener {

	private Document curDocument;
	private int TEST_FLAG;
	
	private ArrayList<ActionListener> commandList = new ArrayList<ActionListener>();
	
	private ArrayList<Boolean> replayBool = new ArrayList<Boolean>();
	
	JFileChooser saveAsFileChooser = new JFileChooser();
	
	//bool that check if command is a copy of another
	boolean isReplayed = false;
		
	//constructor
	public SaveDocument(Document curDocument,ArrayList<ActionListener> commandList,ArrayList<Boolean> replayBool) {
		this.curDocument = curDocument;
		this.TEST_FLAG = 0;
		this.commandList = commandList;
		this.replayBool = replayBool;
	}
	
	//test constructor
	public SaveDocument(Document curDocument, int TEST_FLAG) {
		this.curDocument = curDocument;
		this.TEST_FLAG = TEST_FLAG;
	}
	
	//replay constructor
	public SaveDocument(Document curDocument, JFileChooser saveAsFileChooser,ArrayList<ActionListener> commandList) {
		this.curDocument = curDocument;
		isReplayed = true;
		this.commandList = commandList;
		this.saveAsFileChooser = saveAsFileChooser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//check if document has been created first so that saveDocument doesn't return error
		if(curDocument.getCreationDate() == null) {
			return;
		}
		//set savedDate in document
		curDocument.setSavedDate(LocalDate.now());
		
		//savedText is going to be the output to .txt file
		JTextArea savedText = new JTextArea();
		
		//transfer curDocument to savedFile
		savedText.append("Author: " + curDocument.getAuthor() + "\n");
		savedText.append("Title: " + curDocument.getTitle() + "\n");
		savedText.append("Creation Date: " + curDocument.getCreationDate().toString() + "\n");
		savedText.append("Saved Date: " + curDocument.getSavedDate().toString() + "\n");
		
		//transfer curDocument's contents to savedText
		for(int i=0; i<curDocument.getContents().size(); i++) {
			ArrayList<String> line = curDocument.getContents().get(i).getWords();
			for(int j=0; j<line.size(); j++) {
				savedText.append(line.get(j) + " ");
			}
			savedText.append("\n");
		}
		
		if(TEST_FLAG == 0 && isReplayed == false) {
			//open gui dialog
			FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
		    saveAsFileChooser.setApproveButtonText("Save");
		    saveAsFileChooser.setFileFilter(extensionFilter);
		    if(TEST_FLAG == 0) {
		    	int actionDialog = saveAsFileChooser.showOpenDialog(null);
			    if (actionDialog != JFileChooser.APPROVE_OPTION) {
			         return;
			    }
		    }
		}
		
	    File file;
		if(TEST_FLAG == 0) {
			file = saveAsFileChooser.getSelectedFile();
		}else{
			file = new File("test.txt");
		}
	    
	    if (!file.getName().endsWith(".txt")) {
	       file = new File(file.getAbsolutePath() + ".txt");
	    }
	    
	    //write the buffer to the .txt file
	    BufferedWriter outFile = null;
	    try {
	       outFile = new BufferedWriter(new FileWriter(file));
	       savedText.write(outFile);

	    } catch (IOException ex) {
	       ex.printStackTrace();
	    } finally {
	       if (outFile != null) {
	          try {
	             outFile.close();
	          } catch (IOException ex2) {}
	       }
	    }
	   
		//check if we are recording commands
		if(!isReplayed && TEST_FLAG == 0) {
			if(replayBool.get(0) == true) {
				SaveDocument copy = new SaveDocument(curDocument, saveAsFileChooser, commandList);
		 		commandList.add(copy);
		 	}
		}
	}
}
