package au.edu.rmit.ct;

import com.dietel.Account;

public class CustomerAccount extends Account {
	private String FamilyName;
	private String GivenName;
	private final int BSB;

	public CustomerAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance,
			String familyName, String givenName, int bsb) throws Exception {
	
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		if (thePIN < 1000) {
			throw new Exception("Pin number too short, must be > 999 ");
		} else if (thePIN > 9999) {
			throw new Exception("Pin number too big, must be <=9999");			
		}
		this.setFamilyName(familyName);
		this.setGivenName(givenName);
		this.BSB = bsb;
	}

	public int getBSB() {
		return BSB;
	}

	public String getFamilyName() {
		return FamilyName;
	}

	public void setFamilyName(String familyName) {
		this.FamilyName = familyName;
	}

	public String getGivenName() {
		return GivenName;
	}

	public void setGivenName(String givenName) {
		this.GivenName = givenName;
	}

}
