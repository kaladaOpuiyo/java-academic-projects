package atmMachineProject;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

public interface ButtonToTransactions extends ActionListener {

    public static final String WITHDRAW = "Withdraw";
    public static final String DEPOSIT = "Deposit";
    public static final String TRANSFER_TO = "Transfer to";
    public static final String BALANCE = "Balance";


    public void excuteTransactionPanel(JTextField textField, Account account1,
	    Account account2, String transaction);

}
