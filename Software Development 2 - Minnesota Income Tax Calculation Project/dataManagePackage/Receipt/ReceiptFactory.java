package dataManagePackage.Receipt;

public class ReceiptFactory {
	
	public static Receipt createNewReceipt(String kind, String id, String date, String amount, String name, String country, String city, String street, String number){
		if (kind.equals("Entertainment")){
			return new Receipt(id,"Entertainment",date, amount, name, country, city, street, number);
		}
		else if (kind.equals("Basic")){
			return new Receipt(id,"Basic", date, amount, name, country, city, street, number);
		}
		else if (kind.equals("Travel")){
			return new Receipt(id,"Travel", date, amount, name, country, city, street, number);
		}
		else if (kind.equals("Health")){
			return new Receipt(id,"Health", date, amount, name, country, city, street, number);
		}
		else if (kind.equals("Other")){
			return new Receipt(id, "Other",date, amount, name, country, city, street, number);
		}
		else{
			return null;
		}
	}
}
