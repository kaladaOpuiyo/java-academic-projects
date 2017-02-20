package Student_DB;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

abstract class StudentDatabaseGUI {

    private JFrame mainFrame;
    private JPanel StudentDatabasePanel;
    private JButton button;
    private DefaultComboBoxModel<String> databaseAction;
    private JComboBox<String> databaseActionCombo;
    private JScrollPane databaseActionScrollPane;

    private JTextField idField, nameField, majorField;
    private JLabel label_1, label_2, label_3;

    StudentDatabaseGUI() {

	StudentDatabasePanel = createNewComponents();

	setSizeOfComponents();

	buttonActionOnClick(StudentDatabasePanel);

	createComboBox();

	createTextField(StudentDatabasePanel);

	setDisplayPanel(StudentDatabasePanel);

	onShutDownRequest();
    }

    private void createComboBox() {

	databaseAction = new DefaultComboBoxModel<>();
	databaseActionCombo = new JComboBox<>(databaseAction);
	databaseActionScrollPane = new JScrollPane(databaseActionCombo);

	databaseAction.addElement(DataProcessing.INSERTING);
	databaseAction.addElement(DataProcessing.DELETING);
	databaseAction.addElement(DataProcessing.FINDING);
	databaseAction.addElement(DataProcessing.UPDATING);

	databaseActionCombo.setPreferredSize(new Dimension(150, 25));
	databaseActionCombo.setSelectedIndex(0);

    }

    private void onShutDownRequest() {

	mainFrame.addWindowListener(new WindowAdapter() {

            @Override
	    public void windowClosing(WindowEvent e) {
		int reply;
                reply = JOptionPane.showConfirmDialog(null,
                        "Would you like to Shutdown this Program",
                        "Shutdown Request", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
		    System.exit(0);
		}
	    }

	});

    }

    public JPanel createNewComponents() {

	mainFrame = new JFrame("Student Database");
	StudentDatabasePanel = new JPanel();

	label_1 = new JLabel(StudentFields.ID);
	label_2 = new JLabel(StudentFields.NAME);
	label_3 = new JLabel(StudentFields.MAJOR);

	idField = new JTextField(10);
	nameField = new JTextField(10);
	majorField = new JTextField(10);

	button = new JButton(DataRequestExecution.PROCESS_REQUEST);

	return StudentDatabasePanel;
    }

    public void setSizeOfComponents() {

	mainFrame.setSize(500, 100);
	mainFrame.setLayout(new GridLayout(1, 1));
	mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void buttonActionOnClick(JPanel StudentDatabasePanel) {

	ButtonAction buttonAction = new ButtonAction(idField, nameField,
		majorField);
	StudentDatabasePanel.add(button);

    }

    public void createTextField(JPanel StudentDatabasePanel) {

	StudentDatabasePanel.add(label_1);
	StudentDatabasePanel.add(idField);

	StudentDatabasePanel.add(label_2);
	StudentDatabasePanel.add(nameField);

	StudentDatabasePanel.add(label_3);
	StudentDatabasePanel.add(majorField);

	StudentDatabasePanel.add(databaseActionScrollPane);
	databaseActionScrollPane.setVisible(true);

	StudentDatabasePanel.add(button);
	mainFrame.add(StudentDatabasePanel);
    }

    public void setDisplayPanel(JPanel StudentDatabasePanel) {

	mainFrame.setLayout(new GridLayout());
	mainFrame.setResizable(false);
	mainFrame.setVisible(true);

    }

    public class ButtonAction extends DataRequestExecution {

	ButtonAction(JTextField textField1, JTextField textField2,
		JTextField textField3) {

	    button.addActionListener((ActionEvent event) -> {
                excuteDataRequestPanel(textField1, textField2, textField3,
                        databaseActionCombo);
            });/*databaseActionCombo*/

	}

    }

}
