package atmMachineProject;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author Kalada Opuiyo
 *
 */
@SuppressWarnings("serial")
public class ATM_GUI_InterfaceBuilder extends JFrame {

    private static int accountNumber;
    private static Account savingsAccount = new Savings(accountNumber);
    private static Account checkingAccount = new Checking(accountNumber);

    private static JButton button1, button2, button3, button4;
    private static JTextField inputFieldValue;
    private static JRadioButton checking, savings;
    private static JPanel atmPanel;

    ActionEvent transaction;

    public static void main(String[] args) {
	new ATM_GUI_InterfaceBuilder();
    }

    public ATM_GUI_InterfaceBuilder() {

	atmPanel = createNewPanel();
	createButtonsAndFields();
	buttonActonOnClick(atmPanel);
	createJRadioButtonGroup_SetDefaultSelection(atmPanel);
	createTextField(atmPanel);
	displayPanel(atmPanel);

    }

    private JPanel createNewPanel() {

	JPanel newPanel = new JPanel();
	this.setSize(400, 100);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("ATM Machine");

	return newPanel;
    }

    private void createTextField(JPanel thePanel) {

	thePanel.add(inputFieldValue);
    }

    private void createJRadioButtonGroup_SetDefaultSelection(JPanel thePanel) {

	ButtonGroup groupAccountType = new ButtonGroup();
	groupAccountType.add(checking);
	groupAccountType.add(savings);

	thePanel.add(checking);
	thePanel.add(savings);

	checking.setSelected(true);
    }

    private void createButtonsAndFields() {
	checking = new JRadioButton("Checking");
	savings = new JRadioButton("Savings");
	inputFieldValue = new JTextField("", 10);
	button1 = new JButton(ButtonToTransactions.WITHDRAW);
	button2 = new JButton(ButtonToTransactions.DEPOSIT);
	button3 = new JButton(ButtonToTransactions.TRANSFER_TO);
	button4 = new JButton(ButtonToTransactions.BALANCE);
    }

    private void buttonActonOnClick(JPanel thePanel) {

	ActionListener withdrawal = new TransactionButton(thePanel,
		inputFieldValue, savingsAccount, checkingAccount,
		ButtonToTransactions.WITHDRAW, checking, savings, button1);

	button1.addActionListener(withdrawal);

	ActionListener deposit = new TransactionButton(thePanel,
		inputFieldValue, savingsAccount, checkingAccount,
		ButtonToTransactions.DEPOSIT, checking, savings, button2);
	button2.addActionListener(deposit);

	ActionListener transferTo = new TransactionButton(thePanel,
		inputFieldValue, savingsAccount, checkingAccount,
		ButtonToTransactions.TRANSFER_TO, checking, savings, button3);
	button3.addActionListener(transferTo);

	ActionListener balance = new TransactionButton(thePanel,
		inputFieldValue, savingsAccount, checkingAccount,
		ButtonToTransactions.BALANCE, checking, savings, button4);
	button4.addActionListener(balance);
    }

    private void displayPanel(JPanel thePanel) {

	this.add(thePanel);
	this.setVisible(true);
    }

    /*
     * Private Classes extends ButtonType Class. Used to execute back-end
     * commands when a button is pushed
     */

    /*
     * savingsAccount checkingAccount
     */

    private class TransactionButton extends ButtonTransactionType {

	private JRadioButton rButton1;
	private JRadioButton rButton2;
	private JTextField textField;
	private Account account1;
	private Account account2;
	private String transactionType;

	TransactionButton(JPanel thePanel, JTextField textField,
		Account account2, Account account1, String transactionType,
		JRadioButton rButton1, JRadioButton rButton2, JButton button) {

	    this.account1 = account1;
	    this.account2 = account2;
	    this.rButton1 = rButton1;
	    this.rButton2 = rButton2;
	    this.textField = textField;
	    this.transactionType = transactionType;

	    thePanel.add(button);

	}

	@Override
	public void actionPerformed(ActionEvent transaction) {

	    if (rButton2.isSelected()) {

		excuteTransactionPanel(textField, account2, account1,
			transactionType);

	    } else {

		if (rButton1.isSelected()) {

		    excuteTransactionPanel(textField, account1, account2,
			    transactionType);

		}

	    }

	}
    }

}
