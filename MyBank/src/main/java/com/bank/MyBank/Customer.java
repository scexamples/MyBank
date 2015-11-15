package com.bank.MyBank;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.interestEarned();
		return total;
	}

	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		double total = 0.0;
		for (Account a : accounts) {
			statement += "\n" + statementForAccount(a) + "\n";
			total += a.sumTransactions();
		}
		statement += "\nTotal In All Accounts " + toDollars(total);
		return statement;
	}

	private String statementForAccount(Account a) {
		String s = "";

		s += a.getAccountType();

		// total up all the transactions
		double total = 0.0;
		for (Transaction t : a.transactions) {
			s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
			total += t.amount;
		}
		s += "Total " + toDollars(total);
		return s;
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}

	/*
	 * Added new feature This method uses a private helper method containsAcc()
	 * to confirm that the account exists
	 * 
	 */
	public void transfer(Account from, Account to, Double amount) {
		if (getNumberOfAccounts() < 2) {
			throw new IllegalArgumentException("You must have at least two accounts to transfer.");
		}
		if (!containsAcc(from)) {
			throw new IllegalArgumentException("Your 'from' account is not valid.");
		}
		if (!containsAcc(to)) {
			throw new IllegalArgumentException("Your 'to' account is not valid.");
		}

		if (from.sumTransactions() < amount) {
			throw new IllegalArgumentException("Not enough funds in from account.");
		}

		from.withdraw(amount);
		to.deposit(amount);
	}

	/*
	 * private helper method for transfer(). Ideal implementation would ensure
	 * account identity by having Account class override its equals() and hashCode()
	 * methods TODO: Include a uniquely identifying field in Account class for proper
	 * override of equals() and hashCode()
	 */
	private boolean containsAcc(Account acc) {
		for (Account account : accounts) {
			if (account.getAccountType().equals(acc.getAccountType()))
				return true;
		}
		return false;
	}
}
