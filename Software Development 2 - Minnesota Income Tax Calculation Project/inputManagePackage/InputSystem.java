package inputManagePackage;
import java.util.List;

public class InputSystem {
		
	private static InputSystem instance = null;
	
	private TXTInputFile txtFile = new TXTInputFile();
	private XMLInputFile xmlFile = new XMLInputFile();

	
	public static InputSystem getInstance(){
		if (InputSystem.instance == null){
			InputSystem.instance = new InputSystem();
		}
		return InputSystem.instance;
	}
	
	public void addTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles){
		for (String afmInfoFile : taxpayersAfmInfoFiles)
		{
			if (afmInfoFile.endsWith(".txt")){
				txtFile.parseFile(afmInfoFilesFolderPath, afmInfoFile);
			}
			else if (afmInfoFile.endsWith(".xml")){
				xmlFile.parseFile(afmInfoFilesFolderPath, afmInfoFile);
			}
		}
	}
}