package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import command.NewDocument;
import model.Document;
import model.Line;

public class NewDocumentTest {
	Document dummyDoc = new Document();
	
	//arraylists in order to fill the dummyDocument with words.
	ArrayList<String> dummyWord = new ArrayList<String>();
	ArrayList<Line> dummyContent = new ArrayList<Line>();
	
	NewDocument newDocument = new NewDocument(null, null, dummyDoc,1);
	
	@Test
	public void test() {
		//Test case 1: already empty document
		//Execute command
		newDocument.createNewDocument();
		if(dummyDoc.getContents().size() == 0) {
			System.out.println("NewDocumentTest passed successfully.");
		}
		else {
			fail("NewDocument Test 1 Failed: New Document not empty.");
		}
		//creates a line
		dummyWord.add("test");
		Line newLine = new Line(dummyWord, null, null);
		//adds line to document contents
		dummyContent.add(newLine);
		dummyDoc.setContents(dummyContent);
		////Test case 2: filled document with words/line
		newDocument.createNewDocument();
		if(dummyDoc.getContents().size() == 0) {
			System.out.println("Test Case: NewDocument test passed.");
		}
		else {
			fail("NewDocument Test 2 Failed: New Document previously filled not empty.");
		}
	}
}
