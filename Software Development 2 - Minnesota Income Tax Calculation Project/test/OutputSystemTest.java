package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Test;

import outputManagePackage.CreateBarChart;
import outputManagePackage.CreateChart;
import outputManagePackage.CreatePieChart;
import outputManagePackage.SaveOutput;
import inputManagePackage.InputSystem;
import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public class OutputSystemTest {
	//Create two dummy files, one .txt and one .xml and check if OutputSystem operations work on them correctly
	@Test
	public void test() {
		SaveOutput outTest = new SaveOutput();
		CreateChart outChart = new CreatePieChart();
		CreateChart outBarChart = new CreateBarChart();
		InputSystem inTemp = new InputSystem();
		File txt = null;
		File xml = null;
		DefaultPieDataset receiptPieChartDataset;
		DefaultCategoryDataset taxAnalysisBarChartDataset;
		
		//Create test .txt
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

	    //Get OutputFiles directory
	    String saveDirectory = fileDirectory.concat("\\OutputFiles");
	    
	    //.txt log file tes
	    outTest.saveTaxpayerInfoToLogFile(saveDirectory, 0, "txt");
	    
	    Path txtLogPath = Paths.get(saveDirectory.concat("\\1234_LOG.txt"));
	    
	    List<String> txtLogFile = null;
		try {
			txtLogFile = Files.readAllLines(txtLogPath);
		} catch (IOException e) {
			System.out.println("Error opennig LOG file");
			e.printStackTrace();
		}
		
		Database database = Database.getInstance();
	    Taxpayer txtPayer = database.getTaxpayerFromArrayList(0);
	    if((!txtPayer.getName().contentEquals(txtLogFile.get(0).substring(6, txtLogFile.get(0).length()))) 
	    		|| (!txtPayer.getAFM().contentEquals(txtLogFile.get(1).substring(5, txtLogFile.get(1).length())))){
	    	fail("Test Failed: incorrect txt taxpayer info.");
	    }
	    System.out.println(".txt file test successful!");
	    	    
	    
	    //.xml log file test
		try {
			xml = new File("2345_INFO.xml");
	        FileWriter myWriter = new FileWriter("2345_INFO.xml");
	        myWriter.write("<Name> John Test </Name>\r\n"
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
		}catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
		//Add xml to input system	    
	    list.add(xml.getName());
		inTemp.addTaxpayersDataFromFilesIntoDatabase(fileDirectory, list);
	    list.clear();
	    
	    //.xml log file test
	    outTest.saveTaxpayerInfoToLogFile(saveDirectory, 1, "xml");
	    
	    Path xmlLogPath = Paths.get(saveDirectory.concat("\\2345_LOG.xml"));
	    
	    List<String> xmlLogFile = null;
		try {
			xmlLogFile = Files.readAllLines(xmlLogPath);
		} catch (IOException e) {
			System.out.println("Error opennig LOG file");
			e.printStackTrace();
		}
		
	    Taxpayer xmlPayer = database.getTaxpayerFromArrayList(1);
	    if((!xmlPayer.getName().contentEquals(xmlLogFile.get(0).substring(7, xmlLogFile.get(0).length()-8))) 
	    		|| (!xmlPayer.getAFM().contentEquals(xmlLogFile.get(1).substring(6, xmlLogFile.get(1).length()-7)))){
	    	fail("Test Failed: incorrect txt taxpayer info.");
	    }
	    System.out.println(".xml file test successful!");

	    
	    /* saveUpdatedTaxpayerTxtInputFile() and saveUpdatedTaxpayerXmlInputFile() methods can't 
	     * be tested without altering the OutputSystem class due to GUI's pop-up dialog requirements.
	     * Also they use the same code as saveTaxpayerInfoToTxtLogFile() and saveTaxpayerInfoToXmlLogFile() 
	     * method respectively so their testing is embedded in the existing two test cases.
	    */
	    
	    
	    //Pie Chart test
	    outChart.createTaxpayerJFreeChart(0);
	    receiptPieChartDataset = outChart.getReceiptPieChartDataset();
	    
	    if(Double.compare(receiptPieChartDataset.getValue("Basic").doubleValue(), txtPayer.getReceiptsTotalAmount("Basic")) == 1
	    		|| Double.compare(receiptPieChartDataset.getValue("Entertainment").doubleValue(), txtPayer.getReceiptsTotalAmount("Entertainment")) == 1
	    		|| Double.compare(receiptPieChartDataset.getValue("Travel").doubleValue(), txtPayer.getReceiptsTotalAmount("Travel")) == 1
	    		|| Double.compare(receiptPieChartDataset.getValue("Health").doubleValue(), txtPayer.getReceiptsTotalAmount("Health")) == 1
	    		|| Double.compare(receiptPieChartDataset.getValue("Other").doubleValue(), txtPayer.getReceiptsTotalAmount("Other")) == 1){
	    	fail("Test Failed: incorrect txt taxpayer info.");
	    }
		
		System.out.println("Pie Chart test successful!");
		
	    //Bar Chart test
		outBarChart.createTaxpayerJFreeChart(0);
		
		taxAnalysisBarChartDataset = outBarChart.getTaxAnalysisBarChartDataset();
		
		if(Double.compare(taxAnalysisBarChartDataset.getValue(0, 0).doubleValue(), txtPayer.getBasicTax()) == 1
	    		|| Double.compare(taxAnalysisBarChartDataset.getValue(0, 1).doubleValue(), txtPayer.getTaxInxrease()) == 1
	    		|| Double.compare(taxAnalysisBarChartDataset.getValue(0, 2).doubleValue(), txtPayer.getTotalTax()) == 1){
	    	fail("Test Failed: incorrect txt taxpayer info.");
	    }
		
		System.out.println("Bar Chart test successful!");
		
		//Clean up
		txt.delete();
	    xml.delete();
	}

}
