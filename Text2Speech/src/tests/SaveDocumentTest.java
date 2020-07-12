package tests;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;
import model.*;
import command.SaveDocument;

public class SaveDocumentTest {
	
	@Test
	public void test() {
		//create dummy document and fill its fields
		Document testDoc = new Document();
		ArrayList<String> ar1 = new ArrayList<String>();
		ArrayList<String> ar2 = new ArrayList<String>();
		ar1.add("This");
		ar1.add("is");
		ar2.add("test");
		Line l1 = new Line(ar1);
		Line l2 = new Line(ar2);
		ArrayList<Line> testContents = new ArrayList<Line>();
		testContents.add(l1);
		testContents.add(l2);
		testDoc.setContents(testContents);
		testDoc.setAuthor("John");
		testDoc.setTitle("Title1");
		testDoc.setCreationDate(LocalDate.now());
		testDoc.setSavedDate(LocalDate.now());
		
		//initiate saveDocument functionality
		//Please note that you need to save to D:\test.txt in order for test case to have the right path
		SaveDocument saveDoc = new SaveDocument(testDoc, 1);
		saveDoc.actionPerformed(null);
		
		//take the output and save it as string
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get("test.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//test if saved text file contains the correct info
		if(data.equals("Author: John\r\n" + "Title: Title1\r\n" + "Creation Date: " + LocalDate.now().toString() + "\r\n" + "Saved Date: " + LocalDate.now().toString() + "\r\n" + "This is \r\n" + "test \r\n")) {
			System.out.println("Test Case: SaveDocument test passed.");
		} else {
			fail("SaveDocument test failed");
		}
	}
}
