package au.edu.rmit.ct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.dietel.BankDatabase;

public class BankDatabaseNew extends BankDatabase {
	private  List<CustomerAccount> customers = new ArrayList<>();

	public BankDatabaseNew() {
 
	}
	public BankDatabaseNew(ArrayList<CustomerAccount> seedCustomers) throws Exception {
		if(seedCustomers.isEmpty())
			throw new Exception ("Trying to seed database with empty source");
		customers = seedCustomers;
	}	
	void clear() throws Exception {
		if(this.isEmpty())
			throw new Exception("Trying to clear an empty database");
		customers.clear();
	}
	
	void add(CustomerAccount aCustomer) throws Exception {
		CustomerAccount ca = getAccount(aCustomer.getAccountNumber());
		if( ca == null) {
			customers.add(aCustomer);			
		}
		else {
			throw new Exception ("Customer number already exists");
		}

	}
	
	boolean isEmpty() {
		return customers.isEmpty();
	}
	
	int size() {
		return customers.size();
	}

	void sortByTotalBalance() {
		// Sort by BSB and then Family Name
		Comparator<CustomerAccount> sortByNameComparator = Comparator.comparing(CustomerAccount::getTotalBalance);
		customers.sort(sortByNameComparator);		
	}
	
	void sortByBSB() {
		// Sort by BSB and then Family Name
		Comparator<CustomerAccount> sortByNameComparator = Comparator.comparing(CustomerAccount::getBSB)
		      .thenComparing(CustomerAccount::getFamilyName);
		customers.sort(sortByNameComparator);		
	}
	void sortByName() {
		// next set the comparator
		Comparator<CustomerAccount> sortByNameComparator = Comparator.comparing(CustomerAccount::getFamilyName)
		      .thenComparing(CustomerAccount::getGivenName);
		customers.sort(sortByNameComparator);		
	}
	

	public boolean authenticateUser(int userAccountNumber, int userPIN) {
		// attempt to retrieve the account with the account number
		CustomerAccount userAccount = getAccount(userAccountNumber);

		// if account exists, return result of Account method validatePIN
		if (userAccount != null)
			return userAccount.validatePIN(userPIN);
		else
			return false; // account number not found, so return false
	} // end method authenticateUser

	private CustomerAccount getAccount(int accountNumber) {
		// loop through accounts searching for matching account number
		for (CustomerAccount currentAccount : customers) {
			// return current account if match found
			if (currentAccount.getAccountNumber() == accountNumber)
				return currentAccount;
		} // end for

		return null; // if no matching account was found, return null
	} 
 
}
