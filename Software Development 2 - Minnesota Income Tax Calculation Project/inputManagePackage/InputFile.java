package inputManagePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dataManagePackage.Database;
import dataManagePackage.FamilyStatus;
import dataManagePackage.Taxpayer;

public abstract class InputFile {
	protected final FamilyStatus familyStatus = new FamilyStatus();

	public void parseFile(String afmInfoFileFolderPath, String afmInfoFile) {
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream(afmInfoFileFolderPath+"\\"+afmInfoFile));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening " + afmInfoFile + " file.");
			System.exit(0);
		}
		Taxpayer newTaxpayer = getTaxPayer(inputStream);
		getReceipts(inputStream,newTaxpayer);
		Database database = Database.getInstance();
		database.addTaxpayerToList(newTaxpayer);
	}
	
	protected String getParameterValueFromFileLine(String fileLine, String parameterStartField, String parameterEndField){
		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
	}
	
	abstract Taxpayer getTaxPayer(Scanner inputStream);
	
	abstract void getReceipts(Scanner inputStream, Taxpayer taxpayer);
}
