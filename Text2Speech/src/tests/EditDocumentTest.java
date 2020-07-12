package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import command.EditDocument;
import model.Document;
import model.Line;

public class EditDocumentTest {

	Document dummyDoc = new Document();
	File dummyFile = new File("testFile.txt");
	
	@Test
	public void test() {
		
		ArrayList<Line> contents = new ArrayList<Line>();
		ArrayList<String> lines = new ArrayList<String>();
		
		lines.add("This is a");
		lines.add("test text");
		lines.add("in a test file");
		 
		//split each line by whitespace characters and add the split list to line class object which is then added to contents
		for(int i=0; i<lines.size(); i++) {
			ArrayList<String> words = new ArrayList<>(Arrays.asList(lines.get(i).split("\\s+")));
			Line temp = new Line(words);
			contents.add(temp);
		}
		
		//set the dummyDoc's contents
		dummyDoc.setContents(contents);
		 				
		//call EditDocument to change dummyDoc
		EditDocument editDoc = new EditDocument(null,dummyDoc,1);
		editDoc.actionPerformed(null);
		
		if(contents != dummyDoc.getContents()) {
			System.out.println("Test Case: EditDocument test passed successfully.");
		}
		else {
			fail("EditDocument Test Failed");
		}
	}
}
