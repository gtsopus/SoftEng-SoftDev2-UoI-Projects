package test;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dataManagePackage.Database;
import inputManagePackage.InputSystem;

public class DatabaseTest {

	@Test
	public void test() {
		InputSystem inTemp = new InputSystem();
		Database updateData = Database.getInstance();
		File txt = null;
		
		//Create 2 test .txt files
		try {
			txt = new File("1234_INFO.txt");
			FileWriter myWriter = new FileWriter("1234_INFO.txt");
			myWriter.write("Name: John Test\r\n"
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
					+ "");
			myWriter.close();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}		

		//Get parent directory (file name size fixed to xxxx_INFO.(txt/xml))
		String fileDirectory = txt.getAbsolutePath();
		fileDirectory = fileDirectory.substring(0, fileDirectory.length() - 14);
		List<String> list=new ArrayList<String>();  

		//Add to input system
		list.add(txt.getName());
		inTemp.addTaxpayersDataFromFilesIntoDatabase(fileDirectory, list);
		list.clear();
			    
		//delete a receipt and update the file
		updateData.setTaxpayersInfoFilesPath(fileDirectory);
		updateData.getTaxpayerFromArrayList(0).removeReceiptFromList(1);
		updateData.updateTaxpayerInputFile(0);
		
		//test the file
		Path txtLogPath = Paths.get(fileDirectory.concat("\\1234_INFO.txt"));
	    
	    List<String> txtLogFile = null;
		try {
			txtLogFile = Files.readAllLines(txtLogPath);
		} catch (IOException e) {
			System.out.println("Error opennig .txt file");
			e.printStackTrace();
		}
		
		if(txtLogFile.size() > 17 && (!txtLogFile.get(7).equals("Receipt ID: 1"))){
	    	fail("Test Failed: File not updated.");
	    }
		
	    System.out.println("Database test successful!");
	    
	    //Clean up 
	    txt.delete();
	}
}
