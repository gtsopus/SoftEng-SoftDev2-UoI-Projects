package outputManagePackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;

public class SaveOutput {
	
	private static SaveOutput instance = null;
	
	public static SaveOutput getInstance(){
		if (SaveOutput.instance == null){
			SaveOutput.instance = new SaveOutput();
		}
		return SaveOutput.instance;
	}

	public void saveUpdatedTaxpayerInputFile(String filePath, int taxpayerIndex, String type){
		
		String[] openingHeaders = {};
		String[] closingHeaders = {};
		
		TaxpayerHeaders taxpayerHeaders = TaxpayerHeaders.getInstance();
		if (type.equals("txt")){
			openingHeaders = taxpayerHeaders.getUpdatedTxtOpening();
			closingHeaders = taxpayerHeaders.getUpdatedTxtClosing();
		}
		else if (type.equals("xml")){
			openingHeaders = taxpayerHeaders.getUpdatedXmlOpening();
			closingHeaders = taxpayerHeaders.getUpdatedXmlClosing();
		}
		
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+filePath);
		}
		
		Database database = Database.getInstance();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		outputStream.println(openingHeaders[0]+taxpayer.getName()+closingHeaders[0]);
		outputStream.println(openingHeaders[1]+taxpayer.getAFM()+closingHeaders[1]);
		outputStream.println(openingHeaders[2]+taxpayer.getFamilyStatus()+closingHeaders[2]);
		outputStream.println(openingHeaders[3]+taxpayer.getIncome()+closingHeaders[3]);
		
		if (taxpayer.getReceiptsArrayList().size() > 0){
			outputStream.println();
			outputStream.println(openingHeaders[4]);
			outputStream.println();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				outputStream.println(openingHeaders[5]+receipt.getId()+closingHeaders[4]);
				outputStream.println(openingHeaders[6]+receipt.getDate()+closingHeaders[5]);
				outputStream.println(openingHeaders[7]+receipt.getKind()+closingHeaders[6]);
				outputStream.println(openingHeaders[8]+receipt.getAmount()+closingHeaders[7]);
				outputStream.println(openingHeaders[9]+receipt.getCompany().getName()+closingHeaders[8]);
				outputStream.println(openingHeaders[10]+receipt.getCompany().getCountry()+closingHeaders[9]);
				outputStream.println(openingHeaders[11]+receipt.getCompany().getCity()+closingHeaders[10]);
				outputStream.println(openingHeaders[12]+receipt.getCompany().getStreet()+closingHeaders[11]);
				outputStream.println(openingHeaders[13]+receipt.getCompany().getNumber()+closingHeaders[12]);
				outputStream.println();
			}
			outputStream.println(closingHeaders[13]);
		}
		
		outputStream.close();
	}
	
	
	public void saveTaxpayerInfoToLogFile(String folderSavePath, int taxpayerIndex, String type){
		Database database = Database.getInstance();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		String[] openingHeaders = {};
		String[] closingHeaders = {};
		
		TaxpayerHeaders taxpayerHeaders = TaxpayerHeaders.getInstance();
		if (type.equals("txt")){
			openingHeaders = taxpayerHeaders.getTxtOpening();
			closingHeaders = taxpayerHeaders.getTxtClosing();
		}
		else if (type.equals("xml")){
			openingHeaders = taxpayerHeaders.getXmlOpening();
			closingHeaders = taxpayerHeaders.getXmlClosing();
		}
		
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG."+type));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG."+type);
		}
		
		outputStream.println(openingHeaders[0]+taxpayer.getName()+closingHeaders[0]);
		outputStream.println(openingHeaders[1]+taxpayer.getAFM()+closingHeaders[1]);
		outputStream.println(openingHeaders[2]+taxpayer.getIncome()+closingHeaders[2]);
		outputStream.println(openingHeaders[3]+taxpayer.getBasicTax()+closingHeaders[3]);
		if (taxpayer.getTaxInxrease()!=0){
			outputStream.println(openingHeaders[4]+taxpayer.getTaxInxrease()+closingHeaders[4]);
		}else{
			outputStream.println(openingHeaders[5]+taxpayer.getTaxDecrease()+closingHeaders[5]);
		}
		outputStream.println(openingHeaders[6]+taxpayer.getTotalTax()+closingHeaders[6]);
		outputStream.println(openingHeaders[7]+taxpayer.getTotalReceiptsAmount()+closingHeaders[7]);
		outputStream.println(openingHeaders[8]+taxpayer.getReceiptsTotalAmount("Entertainment")+closingHeaders[8]);
		outputStream.println(openingHeaders[9]+taxpayer.getReceiptsTotalAmount("Basic")+closingHeaders[9]);
		outputStream.println(openingHeaders[10]+taxpayer.getReceiptsTotalAmount("Travel")+closingHeaders[10]);
		outputStream.println(openingHeaders[11]+taxpayer.getReceiptsTotalAmount("Health")+closingHeaders[11]);
		outputStream.println(openingHeaders[12]+taxpayer.getReceiptsTotalAmount("Other")+closingHeaders[12]);
		
		outputStream.close();
		
		JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
	}
}
