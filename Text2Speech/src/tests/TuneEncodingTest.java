package tests;

import static org.junit.Assert.fail;
import org.junit.Test;
import encodingstrategies.StrategiesFactory;
import encodingstrategies.TemplateEncoding;
import model.Document;

public class TuneEncodingTest {

	@Test
	public void test() {
		StrategiesFactory encodingFac = new StrategiesFactory();
		TemplateEncoding encoding = (TemplateEncoding) encodingFac.createStrategy("atbash");
		Document testDoc = new Document();
		
		testDoc.tuneEncodingStrategy(encoding);
		String[] test = testDoc.getEncodingStrategy().split("@");
		
		if(test[0].equals("encodingstrategies.AtBashEncoding")) {
			System.out.println("Test Case 1: AtBash test passed.");
		}
		else {
			fail("Test Case 1: AtBash test failed.");
		}
		
		encoding = (TemplateEncoding) encodingFac.createStrategy("rot13");
		
		testDoc.tuneEncodingStrategy(encoding);
		test = testDoc.getEncodingStrategy().split("@");
		
		if(test[0].equals("encodingstrategies.Rot13Encoding")) {
			System.out.println("Test Case 2: Rot13 test passed.");
		}
		else {
			fail("Test Case 2: Rot13 test failed.");
		}
	}
}