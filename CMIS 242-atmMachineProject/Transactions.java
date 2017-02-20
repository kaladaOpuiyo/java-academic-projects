package atmMachineProject;

import java.util.Random;

abstract class Transactions implements Account {

    private double balance;
    private int accountNumber;
    private final double SERVICE_FEES = 1.50;
    private final double OVERDRAFT = 35.00;

    private void setBalance(double balance) {
	this.balance = balance;
    }

    private void setAccountNumber(int accountNumber) {
	this.accountNumber = accountNumber;
    }

    public Transactions(int accountNumber) {

	Random rand = new Random();
	accountNumber = rand.nextInt(9999999);

	this.setAccountNumber(accountNumber);
	// this.setBalance(100);

    }

    public double deposit(Double amount) throws NumberFormatException {

	if (amount == null) {

	    throw new NumberFormatException();

	} else {
	    setBalance(getBalance() + amount);

	}

	return getBalance();
    }

    public double withdrawal(double amount) throws InsufficientFunds {

	if (amount <= getBalance()) {

	    setBalance(getBalance() - amount);

	} else {

	    throw new InsufficientFunds();

	}

	return getBalance();
    }

    public double transferTo(double amount, Account account)
	    throws InsufficientFunds {

	if (amount <= account.getBalance()) {

	    account.withdrawal(amount);
	    setBalance(getBalance() + amount);

	} else {

	    throw new InsufficientFunds();
	}

	return getBalance();
    }

    public double OVERDRAFT_FEE() {

	setBalance(getBalance() - OVERDRAFT);
	return getBalance();

    }

    public double SERVICE_FEES() {

	setBalance(getBalance() - SERVICE_FEES);
	return getBalance();

    }

    public double getBalance() {

	return balance;
    }

    public int getAccountNumber() {
	return accountNumber;
    }

}
