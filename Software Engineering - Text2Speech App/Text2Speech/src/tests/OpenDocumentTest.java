package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import command.OpenDocument;
import model.Document;

public class OpenDocumentTest {
	
	Document dummyDoc = new Document();
	File dummyFile = new File("testFile.txt");
	
	@Test
	public void test() {
		//create dummy file
		try {
		      FileWriter myWriter = new FileWriter("testFile.txt");
		      myWriter.write(String.format("Author: John%n"));
		      myWriter.write(String.format("Title: Title1%n"));
		      myWriter.write(String.format("Creation Date: 2020-04-16%n"));
		      myWriter.write(String.format("Saved Date: 2020-04-16%n"));
		      myWriter.write(String.format("This is%n"));
		      myWriter.write(String.format("a test"));
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		OpenDocument openDoc = new OpenDocument(null,null,dummyDoc,1);
		openDoc.actionPerformed(null);
		
		int it = 0;
		//read Document.content text
		StringBuilder listString = new StringBuilder();
		for (int k=0; k<dummyDoc.getContents().size(); k++) {
			for (String s : dummyDoc.getContents().get(k).getWords()) 
			{
				listString.append(s);
				if(!(it == dummyDoc.getContents().get(k).getWords().size() - 1)) {
					listString.append(" ");
				}
				it +=1;
			}
			it = 0;
			listString.append("\n");
		}
		
		//opened file text
		String openedData = ("Author: " + dummyDoc.getAuthor().toString() + "\n" + "Title: " + dummyDoc.getTitle().toString() + "\n" + "Creation Date: " + dummyDoc.getCreationDate().toString() + "\n" + "Saved Date: " + dummyDoc.getSavedDate().toString() + "\n" + listString);     
		
		//read test file text
		File file = new File("testFile.txt");
		StringBuilder fileData = new StringBuilder();
		try {
			Scanner input = new Scanner(file);
			while(input.hasNext()) {
				String nextLine = input.nextLine();
				fileData.append(nextLine);
				fileData.append("\n");
			}			
			input.close();
		} catch (FileNotFoundException e1) {
			//auto-generated catch block
			e1.printStackTrace();
		}
		
		//compare opened file text and actual file text
		if (fileData.toString().equals(openedData.toString())){
			System.out.println("Test Case: OpenDocument test passed.");
		}
		else {
			fail("OpenDocument Test Failed");
		}
	}
}
