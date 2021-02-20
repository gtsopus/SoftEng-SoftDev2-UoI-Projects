package inputManagePackage;

import java.util.Scanner;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Receipt.ReceiptFactory;

public class TXTInputFile extends InputFile {

	@Override
	Taxpayer getTaxPayer(Scanner inputStream) {
		String taxpayerName = getParameterValueFromFileLine(inputStream.nextLine(), "Name: ","");
		String taxpayerAFM = getParameterValueFromFileLine(inputStream.nextLine(), "AFM: ","");
		String taxpayerStatus = getParameterValueFromFileLine(inputStream.nextLine(), "Status: ","");
		String taxpayerIncome = getParameterValueFromFileLine(inputStream.nextLine(), "Income: ","");
		Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM, familyStatus.getFamilyStatus(taxpayerStatus), taxpayerIncome);
		return newTaxpayer;
	}

	@Override
	void getReceipts(Scanner inputStream, Taxpayer taxpayer) {
		String fileLine;
		while (inputStream.hasNextLine())
		{
			fileLine = inputStream.nextLine();
			if (fileLine.equals("")) continue;
			if (fileLine.indexOf("Receipts:")!=-1) continue;

			String receiptID = getParameterValueFromFileLine(fileLine, "Receipt ID: ","");
			String receiptDate = getParameterValueFromFileLine(inputStream.nextLine(), "Date: ","");
			String receiptKind = getParameterValueFromFileLine(inputStream.nextLine(), "Kind: ","");
			String receiptAmount = getParameterValueFromFileLine(inputStream.nextLine(), "Amount: ","");
			String receiptCompany = getParameterValueFromFileLine(inputStream.nextLine(), "Company: ","");
			String receiptCountry = getParameterValueFromFileLine(inputStream.nextLine(), "Country: ","");
			String receiptCity = getParameterValueFromFileLine(inputStream.nextLine(), "City: ","");
			String receiptStreet = getParameterValueFromFileLine(inputStream.nextLine(), "Street: ","");
			String receiptNumber = getParameterValueFromFileLine(inputStream.nextLine(), "Number: ","");
			Receipt newReceipt = ReceiptFactory.createNewReceipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
			
			taxpayer.addReceiptToList(newReceipt);
		}
	}
}
