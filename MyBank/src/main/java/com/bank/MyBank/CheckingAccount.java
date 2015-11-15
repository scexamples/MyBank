package com.bank.MyBank;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super("Checking Account");

	}

	// interest rate of 0.1%
	public double interestEarned() {
		double amount = sumTransactions();
		return amount * 0.001;
	}

	public String getAccountType() {
		return name;
	}

}
