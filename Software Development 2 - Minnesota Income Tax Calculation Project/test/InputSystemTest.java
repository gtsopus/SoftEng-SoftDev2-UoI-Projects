package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import inputManagePackage.InputSystem;
import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public class InputSystemTest {
	//Create two dummy files, one .txt and .xml and check if their contents are correct
	@Test
	public void test() {
		InputSystem isTest = new InputSystem();
		File txt = null;
		File xml = null;
	    //Create .txt and test it
		try {
	        txt = new File("1234_INFO.txt");
	        FileWriter myWriter = new FileWriter("1234_INFO.txt");
	        myWriter.write("Name: Greg Test\r\n"
	            		+ "AFM: 1234\r\n"
	            		+ "Status: Married Filing Jointly\r\n"
	            		+ "Income: 22570\r\n"
	            		+ "Receipts:\r\n"
	            		+ "\r\n"
	            		+ "Receipt ID: 1\r\n"
	            		+ "Date: 25/2/2014\r\n"
	            		+ "Kind: Basic\r\n"
	            		+ "Amount: 2000\r\n"
	            		+ "Company: Hand Made Clothes\r\n"
	            		+ "Country: Greece\r\n"
	            		+ "City: Ioannina\r\n"
	            		+ "Street: Kaloudi \r\n"
	            		+ "Number: 10\r\n"
	            		+ "\r\n"
	            		+ "Receipt ID: 2\r\n"
	            		+ "Date: 28/2/2014\r\n"
	            		+ "Kind: Entertainment\r\n"
	            		+ "Amount: 500\r\n"
	            		+ "Company: Floca Cafe\r\n"
	            		+ "Country: Greece\r\n"
	            		+ "City: Ioannina\r\n"
	            		+ "Street: Kavafi\r\n"
	            		+ "Number: 4\r\n"
	            		+ "\r\n"
	            		+ "Receipt ID: 3\r\n"
	            		+ "Date: 10/3/2014\r\n"
	            		+ "Kind: Health\r\n"
	            		+ "Amount: 500\r\n"
	            		+ "Company: Dentist\r\n"
	            		+ "Country: Greece\r\n"
	            		+ "City: Ioannina\r\n"
	            		+ "Street: Lamprou\r\n"
	            		+ "Number: 20\r\n"
	            		+ "\r\n"
	            		+ "\r\n"
	            		+ "Receipt ID: 4\r\n"
	            		+ "Date: 3/5/2014\r\n"
	            		+ "Kind: Other\r\n"
	            		+ "Amount: 900\r\n"
	            		+ "Company: Public\r\n"
	            		+ "Country: Greece\r\n"
	            		+ "City: Ioannina\r\n"
	            		+ "Street: Vlahlidou\r\n"
	            		+ "Number: 20\r\n"
	            		+ "\r\n"
	            		+ "Receipt ID: 5\r\n"
	            		+ "Date: 3/5/2014\r\n"
	            		+ "Kind: Other\r\n"
	            		+ "Amount: 600\r\n"
	            		+ "Company: Toys Store\r\n"
	            		+ "Country: Greece\r\n"
	            		+ "City: Ioannina\r\n"
	            		+ "Street: Omhrou\r\n"
	            		+ "Number: 5\r\n"
	            		+ "");
	      myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	    
	    //Get parent directory (file name size fixed to xxxx_INFO.(txt/xml))
	    String fileDirectory = txt.getAbsolutePath();
	    fileDirectory = fileDirectory.substring(0, fileDirectory.length() - 14);
	    List<String> list=new ArrayList<String>();  
	    
	    //Add to input system
	    list.add(txt.getName());
	    
	    isTest.addTaxpayersDataFromFilesIntoDatabase(fileDirectory, list);
	    
	    list.clear();
	    
	    //txt tests here
	    Database database = Database.getInstance();
	    if(database.getTaxpayersArrayListSize() != 1) {
	    	fail("Test Failed: txt taxpayer array size incorrect.");
	    }
	    Taxpayer txtPayer = database.getTaxpayerFromArrayList(0);

	    if((!txtPayer.getAFM().contentEquals("1234")) && (!txtPayer.getName().contentEquals("Greg Test"))) {
	    	fail("Test Failed: incorrect txt taxpayer info.");
	    }
	    
	    System.out.println(".txt parsing/input test successful!");
	    
	    //Create .xml and test it
	    try {
	        xml = new File("2345_INFO.xml");
	        FileWriter myWriter = new FileWriter("2345_INFO.xml");
	        myWriter.write("<Name> Greg Test </Name>\r\n"
	            		+ "<AFM> 2345 </AFM>\r\n"
	            		+ "<Status> Single </Status>\r\n"
	            		+ "<Income> 40000.0 </Income>\r\n"
	            		+ "\r\n"
	            		+ "<Receipts>\r\n"
	            		+ "\r\n"
	            		+ "<ReceiptID> 1 </ReceiptID>\r\n"
	            		+ "<Date> 25/2/2014 </Date>\r\n"
	            		+ "<Kind> Other </Kind>\r\n"
	            		+ "<Amount> 2000.0 </Amount>\r\n"
	            		+ "<Company> Omega Watches </Company>\r\n"
	            		+ "<Country> Greece </Country>\r\n"
	            		+ "<City> Ioannina </City>\r\n"
	            		+ "<Street> Kaloudi </Street>\r\n"
	            		+ "<Number> 4 </Number>\r\n"
	            		+ "\r\n"
	            		+ "<ReceiptID> 3 </ReceiptID>\r\n"
	            		+ "<Date> 5/6/2014 </Date>\r\n"
	            		+ "<Kind> Basic </Kind>\r\n"
	            		+ "<Amount> 4000.0 </Amount>\r\n"
	            		+ "<Company> IKEA </Company>\r\n"
	            		+ "<Country> Greece </Country>\r\n"
	            		+ "<City> Ioannina </City>\r\n"
	            		+ "<Street> Averof </Street>\r\n"
	            		+ "<Number> 100 </Number>\r\n"
	            		+ "\r\n"
	            		+ "</Receipts>");
	         myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	    //Add xml to input system
	    list.add(xml.getName());
		isTest.addTaxpayersDataFromFilesIntoDatabase(fileDirectory, list);
	    
		//xml tests here
		if(database.getTaxpayersArrayListSize() != 2) {
	    	fail("Test failed: xml taxpayer array size incorrect.");
	    }
		
		Taxpayer xmlPayer = database.getTaxpayerFromArrayList(1);

	    if((!xmlPayer.getAFM().contentEquals("2345")) && (!xmlPayer.getName().contentEquals("Greg Tset"))) {
	    	fail("Test Failed: incorrect xml taxpayer info.");
	    }
		
	    System.out.println(".xml parsing/input test successful!");
	    
	    //Clean up
	    txt.delete();
	    xml.delete(); 
	    list.clear();	    
	    }
}