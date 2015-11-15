package com.bank.MyBank;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class CustomerTest {

	@Test // Test customer statement generation
	public void testApp() {

		Account checkingAccount = new CheckingAccount();
		Account savingsAccount = new SavingsAccount();

		Customer Jerry = new Customer("Jerry").openAccount(checkingAccount).openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);

		assertEquals("Statement for Jerry\n" + "\n" + "Checking Account" + "  deposit $100.00\n" + "Total $100.00\n"
				+ "\n" + "Savings Account" + "  deposit $4,000.00\n" + "  withdrawal $200.00\n" + "Total $3,800.00\n"
				+ "\n" + "Total In All Accounts $3,900.00", Jerry.getStatement());
		
	}

	@Test
	public void testOneAccount() {
		Customer Ben = new Customer("Ben").openAccount(new SavingsAccount());
		assertEquals(1, Ben.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		Customer Ben = new Customer("Ben").openAccount(new SavingsAccount());
		Ben.openAccount(new CheckingAccount());
		assertEquals(2, Ben.getNumberOfAccounts());
	}

	@Ignore
	public void testThreeAcounts() {
		Customer Ben = new Customer("Ben").openAccount(new SavingsAccount());
		Ben.openAccount(new CheckingAccount());
		assertEquals(3, Ben.getNumberOfAccounts());
	}
}
