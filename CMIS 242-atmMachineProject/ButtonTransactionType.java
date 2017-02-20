package atmMachineProject;

import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public abstract class ButtonTransactionType implements ButtonToTransactions {

    private Component ATM_GUI_InterfaceBuilder;

    private Double amount;
    private Double transactionAmount;
    private int buttonClicked;

    private static final int ZERO = 0;
    private static final int ENOUGH_TO_CHARGE = 4;

    private void displayTransactionResult(Account account) {

	String dollarFormat = NumberFormat.getCurrencyInstance()
		.format(account.getBalance());

	JOptionPane.showMessageDialog(ATM_GUI_InterfaceBuilder, dollarFormat,
		"Balance in Account # " + account.getAccountNumber(),
		JOptionPane.INFORMATION_MESSAGE);

    }

    private void checkForOverdraftPanel(Account account) {

	account.SERVICE_FEES();
	buttonClicked = ZERO;

	if (account.getBalance() < ZERO) {
	    account.OVERDRAFT_FEE();

	    JOptionPane.showConfirmDialog(null,
		    "An Over Draft fee has been assessed on your Account",
		    "Overdraft FEE: $35.00 ", JOptionPane.CLOSED_OPTION);
	}

    }

    private void feeAssessmentPanel(Account account1, Account account2) {

	if (buttonClicked == ENOUGH_TO_CHARGE) {

	    JOptionPane.showConfirmDialog(null,
		    "A service fee has been assessed on your Account",
		    "Service FEE: $1.50 ", JOptionPane.CLOSED_OPTION);

	    if (account1.getBalance() >= account2.getBalance()) {

		checkForOverdraftPanel(account1);

	    } else {

		checkForOverdraftPanel(account2);

	    }

	}
    }

    private void accountTransaction(JTextField textField, Account account1,
	    Account account2, String TRANSACTIONS)
		    throws NumberFormatException, InsufficientFunds {

	switch (TRANSACTIONS) {

	case BALANCE:

	    account1.getBalance();
	    break;

	case TRANSFER_TO:

	    transactionAmount = Double.valueOf(textField.getText());
	    amount = transactionAmount;
	    account1.transferTo(amount, account2);
	    break;

	case DEPOSIT:

	    transactionAmount = Double.valueOf(textField.getText());
	    amount = transactionAmount;
	    account1.deposit(amount);
	    break;

	case WITHDRAW:

	    transactionAmount = Double.valueOf(textField.getText());
	    amount = (transactionAmount % 20 == ZERO) ? transactionAmount
		    : null;
	    account1.withdrawal(amount);

	    if (amount > ZERO)
		buttonClicked++;
	    feeAssessmentPanel(account1, account2);

	    break;

	default:

	    break;
	}
    }

    public void excuteTransactionPanel(JTextField textField, Account account1,
	    Account account2, String transaction) {

	try {

	    accountTransaction(textField, account1, account2, transaction);

	} catch (InsufficientFunds e3) {

	    JOptionPane.showConfirmDialog(null,
		    "You current have Insufficient Funds for this Transaction",
		    "Insufficient Funds", JOptionPane.CLOSED_OPTION);

	} catch (NumberFormatException e1) {

	    JOptionPane.showConfirmDialog(null, "Please enter a number",
		    "Number Error: Account # " + account1.getAccountNumber(),
		    JOptionPane.CLOSED_OPTION);

	} catch (RuntimeException e4) {

	    JOptionPane.showConfirmDialog(null,
		    "Only amount in increments of $20 are allowed", "$20s Only",
		    JOptionPane.CLOSED_OPTION);

	} finally {

	    displayTransactionResult(account1);

	}

    }

}
