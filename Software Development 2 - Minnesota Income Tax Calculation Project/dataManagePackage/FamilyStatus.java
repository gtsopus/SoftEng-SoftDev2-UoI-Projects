package dataManagePackage;

public class FamilyStatus {
	
	private static final FamilyStatus single = new FamilyStatus(new int[] {24680,81080,90000,152540},new float[] {5.35f,7.05f,7.85f,7.85f,9.85f},new float[] {1320.38f,5296.58f,5996.80f,10906.19f});
	private static final FamilyStatus headOfHousehold = new FamilyStatus(new int[] {30390,90000,122110,203390},new float[] {5.35f,7.05f,7.05f,7.85f,9.85f},new float[] {1625.87f,5828.38f,8092.13f,14472.61f});	
	private static final FamilyStatus marriedJointly = new FamilyStatus(new int[] {36080,90000,143350,254240},new float[] {5.35f,7.05f,7.05f,7.85f,9.85f},new float[] {1930.28f,5731.64f,9492.82f,18197.69f});
	private static final FamilyStatus marriedSeparately = new FamilyStatus(new int[] {18040,71680,90000,127120},new float[] {5.35f,7.05f,7.85f,7.85f,9.85f},new float[] {965.14f,4746.76f,6184.88f,9098.80f});	
	
	private int[] incomeLimits;
	private float[] standardTax;
	private float[] incomeTaxRates;
	
	public FamilyStatus(int[] incomeLimits,float[] incomeTaxRates,float[] standardTax) {
		this.incomeLimits = incomeLimits;
		this.incomeTaxRates = incomeTaxRates;
		this.standardTax = standardTax;
	}
	
	public FamilyStatus() {}
	
	public FamilyStatus getFamilyStatus(String statusName) {
		String tStatusName = statusName.toLowerCase();
		FamilyStatus tempStatus = null;
		if(tStatusName.equals("single")) {
			tempStatus = single;
		}
		else if(tStatusName.equals("married filing jointly")) {
			tempStatus = marriedJointly;
		}
		else if(tStatusName.equals("head of household")) {
			tempStatus = headOfHousehold;
		}
		else{
			tempStatus = marriedSeparately;
		}
		return tempStatus;
	}

	public float[] getIncomeTaxRates() {
		return incomeTaxRates;
	}

	public int[] getIncomeLimits() {
		return incomeLimits;
	}

	public float[] getStandardTax() {
		return standardTax;
	}

	public String getName() {
		String name = "";
		if(this.equals(single)) {
			name = "Single";
		}
		else if(this.equals(headOfHousehold)) {
			name = "Head of Household";
		}
		else if(this.equals(marriedJointly)) {
			name = "Married Filing Jointly";
		}
		else if(this.equals(marriedSeparately)) {
			name = "Married Filing Separately";
		}
		return name;
	}
}
