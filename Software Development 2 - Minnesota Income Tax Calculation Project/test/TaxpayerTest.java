package test;

import static org.junit.Assert.*;
import org.junit.Test;

import dataManagePackage.FamilyStatus;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.*;


//Our aim is to get good test coverage of Taxpayer class, while ignoring obsolete testing such as getters/setters.
public class TaxpayerTest {
	FamilyStatus fs = new FamilyStatus();
	FamilyStatus familyStatuses[] = {fs.getFamilyStatus("married filing jointly"),fs.getFamilyStatus("married filing separately"),fs.getFamilyStatus("single"),fs.getFamilyStatus("head of household")}; 
	String incomes[] = {"15000","30000","70000","155000"};
	
	//16 tax values, each correctly calculated for every family status and income (4*4)	
	double correctTaxes[][] = {{802.5, 1605.0,  4321.64013671875, 10407.345703125},
							   {802.5, 1808.320068359375, 4628.3203125, 11844.9794921875},
							   {802.5,1695.4400634765625,4515.43994140625,11148.5},
							   {802.5, 1605.0, 4418.375, 10673.9951171875}};
	Taxpayer testpayer;
	
	int testCases = familyStatuses.length;
	
	@Test
	//Test each important public method of Taxpayer class.
	public void test() {
		float tax = 0;
		//Tax expected values based on income
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				testpayer = new Taxpayer("Greg","1234",familyStatuses[i],incomes[j]);
				tax = (float)testpayer.calculateTax(testpayer.getIncome());
				failTaxTest(tax,correctTaxes[i][j]);	
			}
		}
		
		//Test receipt additions
		testpayer = new Taxpayer("Greg","1234",familyStatuses[0],incomes[0]);
				
		Receipt testHealth = new Receipt("1","Health", "1/1/2001", "250","AB Clinic", "Greece", "Volos", "Test", "32");
		Receipt testEnter = new Receipt("2", "Entertainment", "1/1/2001", "50","Bar G", "Greece", "Volos", "Test", "33");
		Receipt testOther = new Receipt("3", "Other", "1/4/2011", "70","Rouxalakias", "Greece", "Volos", "Test", "34");
		Receipt testBasic = new Receipt("4", "Basic", "8/6/2018", "130","AB Marketopoulos", "Greece", "Volos", "Test", "35");
		Receipt testTravel = new Receipt("5", "Travel","8/6/2018", "100","Agxialos Airport", "Greece", "Volos", "Test", "35");

		
		//Test addition/removal of receipts
		testpayer.addReceiptToList(testHealth);
		if(testpayer.getReceiptsArrayList().size() != 1) {
			fail("Test case failed: receipt not added.");
		}
		
		if(testpayer.getReceiptsTotalAmount("Health") != 250.0) {
			System.out.println(testpayer.getReceiptsTotalAmount("Health"));
			fail("Test case failed: health receipt amount is incorrect.");
		}
		
		//??? RemoveReceiptFromList is based on arraylist index, not on receipt ID.
		testpayer.removeReceiptFromList(0);
		if(testpayer.getReceiptsArrayList().size() != 0) {
			fail("Test case failed: receipt not removed.");
		}
		//Test all receipt types
		testpayer.addReceiptToList(testEnter);
		testpayer.addReceiptToList(testOther);
		testpayer.addReceiptToList(testBasic);
		testpayer.addReceiptToList(testTravel);

		if(testpayer.getReceiptsTotalAmount("Entertainment") != 50.0) {
			fail("Test case failed: entertainment receipt ammunt is incorrect.");
		}
		if(testpayer.getReceiptsTotalAmount("Other") != 70.0) {
			fail("Test case failed: other receipt amount is incorrect.");
		}
		if(testpayer.getReceiptsTotalAmount("Basic") != 130.0) {
			fail("Test case failed: basic receipt amount is incorrect.");
		}
		if(testpayer.getReceiptsTotalAmount("Travel") != 100.0) {
			fail("Test case failed: travel receipt amount is incorrect.");
		}
		//Test total receipt amount
		if(testpayer.getTotalReceiptsAmount() != 350.0) {
			fail("Test case failed: basic receipt amount is incorrect.");
		}
		
	}

 private void failTaxTest(double tax, double correctTax) {
	 if(tax != correctTax) {
		 fail("Tax testing failed. " + String.valueOf(tax));
	 }
 }
}
