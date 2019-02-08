package com.org.rabobank;

import java.io.Serializable;

public class CustomerStatement implements Serializable{

	private static final long serialVersionUID = 4572133601017703254L;
	
	private String reference;
	private String accountNumber;
	private String description;
	private String startBalance;
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(String startBalance) {
		this.startBalance = startBalance;
	}
	
	/**
	 * @param reference
	 * @param accountNumber
	 * @param description
	 * @param startBalance
	 * @param mutation
	 * @param endBalance
	 */
	public CustomerStatement(String reference, String accountNumber, String description, String startBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.description = description;
		this.startBalance = startBalance;
		
	}
	
	
	
}
 