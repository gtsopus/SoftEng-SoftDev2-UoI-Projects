package dataManagePackage;
import dataManagePackage.Receipt.*;
import inputManagePackage.*;
import outputManagePackage.*;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class Database {
	private String taxpayersInfoFilesPath;
	private ArrayList<Taxpayer> taxpayersArrayList = new ArrayList<Taxpayer>();
	private static Database instance = null; 
	
	public void setTaxpayersInfoFilesPath(String taxpayersInfoFilesPath){
		Database database = Database.getInstance();
		database.taxpayersInfoFilesPath = taxpayersInfoFilesPath;
	}
	
	public String getTaxpayersInfoFilesPath(){
		Database database = Database.getInstance();
		return database.taxpayersInfoFilesPath;
	}
	
	public static Database getInstance(){
		if (Database.instance == null){
			Database.instance = new Database();
		}
		return Database.instance;
	}
	
	public void proccessTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles){
		InputSystem.getInstance().addTaxpayersDataFromFilesIntoDatabase(afmInfoFilesFolderPath, taxpayersAfmInfoFiles);
	}
	
	public void addTaxpayerToList(Taxpayer taxpayer){
		taxpayersArrayList.add(taxpayer);
	}
	
	public int getTaxpayersArrayListSize(){
		return taxpayersArrayList.size();
	}
	
	public Taxpayer getTaxpayerFromArrayList(int index){
		return taxpayersArrayList.get(index);
	}
	
	public void removeTaxpayerFromArrayList(int index){
		taxpayersArrayList.remove(index);
	}
	
	public String getTaxpayerNameAfmValuesPairList(int index){
		Taxpayer taxpayer = taxpayersArrayList.get(index);
		return taxpayer.getName() + " | " + taxpayer.getAFM();
	}
	
	public String[] getTaxpayersNameAfmValuesPairList(){
		String[] taxpayersNameAfmValuesPairList = new String[taxpayersArrayList.size()];
		
		int c = 0;
		for (Taxpayer taxpayer : taxpayersArrayList){
			taxpayersNameAfmValuesPairList[c++] = taxpayer.getName() + " | " + taxpayer.getAFM();
		}
		
		return taxpayersNameAfmValuesPairList;
	}
	
	public void updateTaxpayerInputFile(int index){
		File taxpayersInfoFilesPathFileObject = new File(taxpayersInfoFilesPath);
		FilenameFilter fileNameFilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
               return (name.toLowerCase().endsWith("_info.txt") || name.toLowerCase().endsWith("_info.xml"));
            }
         };
		
		for (File file : taxpayersInfoFilesPathFileObject.listFiles(fileNameFilter)){
			if (!file.getName().contains(taxpayersArrayList.get(index).getAFM())) continue;
			
			SaveOutput outputSystem = SaveOutput.getInstance();
			if (file.getName().toLowerCase().endsWith(".txt")){
				outputSystem.saveUpdatedTaxpayerInputFile(file.getAbsolutePath(), index, "txt");
			}
			if (file.getName().toLowerCase().endsWith(".xml")){
				outputSystem.saveUpdatedTaxpayerInputFile(file.getAbsolutePath(), index, "xml");
			}
			break;
		}
	}	
}