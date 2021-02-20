package tests;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JTextArea;
import org.junit.Test;
import model.*;
import encodingstrategies.*;
import text2speechapis.*;
import command.LineToSpeech;

public class SpeechEncodingTest {

	@Test
	public void test() {
		//Test document initialization
		Document testDoc = new Document();
		FakeTextToSpeechAPI adapter = new FakeTextToSpeechAPI();
		testDoc.setTTSAPI(adapter);
		
		//Set document contents
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
		
		//Set text area
		JTextArea textArea = new JTextArea();
		textArea.append("This is \n");
		textArea.append("test");
		
		//Create LineToSpeech command
		LineToSpeech command = new LineToSpeech(1,textArea, testDoc, 1,null,null);
		
		//Create an encoding strategy (Rot13, AtBash etc)
		Rot13Encoding encodingStrategy = new Rot13Encoding();
		testDoc.tuneEncodingStrategy(encodingStrategy);
		
		//Testing of playDocument,playLine,playReverseDocument,playReverseLine
		command.actionPerformed(null);
		if(!adapter.getTestOutput().equals("Thisis\ntest\n")) {
			fail("Failure at play contents");
		}
		
		command.setChosenFunc(2);
		command.actionPerformed(null);
		if(!adapter.getTestOutput().equals("test  is This  ")) {
			fail("Failure at play reverse contents");
		}
		
		command.setChosenFunc(3);
		command.actionPerformed(null);
		if(!adapter.getTestOutput().equals("Thisis")) {
			fail("Failure at play reverse contents");
		}
		
		command.setChosenFunc(4);
		command.actionPerformed(null);
		if(!adapter.getTestOutput().equals("is This")) {
			fail("Failure at play reverse contents");
		}
		
		//Testing of playEncodedContents and playEncodedLine
		command.setChosenFunc(5);
		command.actionPerformed(null);
		if(!adapter.getTestOutput().equals("Guvfvf\ngrfg\n")) {
			fail("Failure at play reverse contents");
		}
		
		command.setChosenFunc(6);
		command.actionPerformed(null);
		if(!adapter.getTestOutput().equals("Guvf vf ")) {
			fail("Failure at play reverse contents");
		}
		
		System.out.println("Test Case: Speech and Encoding test passed");
	}
}
