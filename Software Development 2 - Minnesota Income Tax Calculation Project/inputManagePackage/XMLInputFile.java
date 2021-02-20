package inputManagePackage;

import java.util.Scanner;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Receipt.ReceiptFactory;

public class XMLInputFile extends InputFile {

	@Override
	Taxpayer getTaxPayer(Scanner inputStream) {
		
		String taxpayerName = getParameterValueFromFileLine(inputStream.nextLine(), "<Name> ", " </Name>");
		String taxpayerAFM = getParameterValueFromFileLine(inputStream.nextLine(), "<AFM> ", " </AFM>");
		String taxpayerStatus = getParameterValueFromFileLine(inputStream.nextLine(), "<Status> ", " </Status>");
		String taxpayerIncome = getParameterValueFromFileLine(inputStream.nextLine(), "<Income> ", " </Income>");
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
			if (fileLine.indexOf("<Receipts>")!=-1) continue;
			if (fileLine.indexOf("</Receipts>")!=-1) break;
			
			String receiptID = getParameterValueFromFileLine(fileLine, "<ReceiptID> ", " </ReceiptID>");
			String receiptDate = getParameterValueFromFileLine(inputStream.nextLine(), "<Date> ", " </Date>");
			String receiptKind = getParameterValueFromFileLine(inputStream.nextLine(), "<Kind> ", " </Kind>");
			String receiptAmount = getParameterValueFromFileLine(inputStream.nextLine(), "<Amount> ", " </Amount>");
			String receiptCompany = getParameterValueFromFileLine(inputStream.nextLine(), "<Company> ", " </Company>");
			String receiptCountry = getParameterValueFromFileLine(inputStream.nextLine(), "<Country> ", " </Country>");
			String receiptCity = getParameterValueFromFileLine(inputStream.nextLine(), "<City> ", " </City>");
			String receiptStreet = getParameterValueFromFileLine(inputStream.nextLine(), "<Street> ", " </Street>");
			String receiptNumber = getParameterValueFromFileLine(inputStream.nextLine(), "<Number> ", " </Number>");
			Receipt newReceipt = ReceiptFactory.createNewReceipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);

			taxpayer.addReceiptToList(newReceipt);
		}
	}
}
