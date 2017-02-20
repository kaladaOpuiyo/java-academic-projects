package atmMachineProject;

public interface Account {

    public double deposit(Double amount) throws NumberFormatException;

    public double withdrawal(double amount) throws InsufficientFunds;

    public double getBalance();

    public int getAccountNumber();

    public double transferTo(double amount, Account account)
	    throws InsufficientFunds;

    public double OVERDRAFT_FEE();

    public double SERVICE_FEES();

}