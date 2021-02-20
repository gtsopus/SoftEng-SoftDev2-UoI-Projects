package outputManagePackage;

public class TaxpayerHeaders {
	
	private final String updatedTxtOpening[] = {"Name: ","AFM: ","Status: ","Income: ","Receipts:","Receipt ID: ","Date: ","Kind: ","Amount: ","Company: ","Country: ","City: ","Street: ","Number: "};
	private final String updatedTxtClosing[] = {"","","","","","","","","","","","","",""};
	private final String updatedXmlOpening[] = {"<Name> ","<AFM> ","<Status> ","<Income> ","<Receipts>","<Receipt ID> ","<Date> ","<Kind> ","<Amount> ","<Company> ","<Country> ","<City> ","<Street> ","<Number> "};
	private final String updatedXmlClosing[] = {"</Name> ","</AFM> ","</Status> ","</Income> ","</Receipt ID> ","</Date> ","</Kind> ","</Amount> ","</Company> ","</Country> ","</City> ","</Street> ","</Number> ","</Receipts>"};
	
	private final String txtOpening[] = {"Name: ","AFM: ","Income: ","Basic Tax: ","Tax Increase: ","Tax Decrease: ","Total Tax: ","Total Receipts Amount: ","Entertainment: ","Basic: ","Travel: ","Health: ","Other: "};
	private final String txtClosing[] = {"","","","","","","","","","","","",""};
	private final String xmlOpening[] = {"<Name> ","<AFM> ","<Income> ","<Basic Tax> ","<Tax Increase> ","<Tax Decrease> ","<Total Tax> ","<Total Receipts Amount> ","<Entertainment> ","<Basic> ","<Travel> ","<Health: ","<Other> "};
	private final String xmlClosing[] = {"</Name> ","</AFM> ","</Income> ","</Basic Tax> ","</Tax Increase> ","</Tax Decrease> ","</Total Tax> ","</Total Receipts Amount> ","</Entertainment> ","</Basic> ","</Travel> ","</Health: ","</Other> "};
	
	private static TaxpayerHeaders instance = null;
	
	public static TaxpayerHeaders getInstance(){
		if(TaxpayerHeaders.instance == null){
			TaxpayerHeaders.instance = new TaxpayerHeaders();
		}
		return TaxpayerHeaders.instance;
	}
	
	public String[] getUpdatedTxtOpening(){
		return updatedTxtOpening;
	}
	
	public String[] getUpdatedTxtClosing(){
		return updatedTxtClosing;
	}
	
	public String[] getUpdatedXmlOpening(){
		return updatedXmlOpening;
	}
	
	public String[] getUpdatedXmlClosing(){
		return updatedXmlClosing;
	}
	
	public String[] getTxtOpening(){
		return txtOpening;
	}
	
	public String[] getTxtClosing(){
		return txtClosing;
	}
	
	public String[] getXmlOpening(){
		return xmlOpening;
	}
	
	public String[] getXmlClosing(){
		return xmlClosing;
	}
}
