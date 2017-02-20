package cmsc_350_project_3.GUI;

import cmsc_350_project_3.*;
import java.awt.GridLayout;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Kalada Opuiyo
 */
public class BinarySearchTreeGUI {

    private JFrame mainFrame;
    private JPanel binarySearchTreeMain;
    private JPanel SortOrder;
    private JPanel NumericType;

    JRadioButton button1 = new JRadioButton("Ascending");
    JRadioButton button2 = new JRadioButton("Descending");
    JRadioButton button3 = new JRadioButton("Integer");
    JRadioButton button4 = new JRadioButton("Fraction");

    private JButton button;
    public JTextField unsortedList, sortedList;
    private JLabel label_1, label_2;

    @SuppressWarnings("OverridableMethodCallInConstructor")

    BinarySearchTreeGUI() {
        launchApp();

    }

    private void launchApp() {
        createNewComponents();
        setDisplayFrame();
        setComponents();
        setComponentsEditableVisible();
        addComponents();
        onShutDownRequest();
    }

    private ButtonAction buttonActionOnClick() {

        return new ButtonAction(unsortedList);
    }

    private void addComponents() {

        mainFrame.add(binarySearchTreeMain);
        binarySearchTreeMain.add(button);
        binarySearchTreeMain.add(label_1);
        binarySearchTreeMain.add(label_2);
        binarySearchTreeMain.add(unsortedList);
        binarySearchTreeMain.add(sortedList);

        buttonActionOnClick();
    }

    private void setComponentsEditableVisible() {
        sortedList.setEditable(false);
        mainFrame.setVisible(true);

    }

    private void setComponents() {

        binarySearchTreeMain.setLayout(null);
        button.setBounds(180, 160, 120, 30);
        label_1.setBounds(50, 15, 200, 25);
        label_2.setBounds(55, 90, 200, 25);
        sortedList.setBounds(120, 90, 280, 25);
        unsortedList.setBounds(120, 15, 280, 25);
        setSubPanels();

    }

    private void createNewComponents() {

        mainFrame = new JFrame("Binary Search Tree Sort");
        binarySearchTreeMain = new JPanel();
        button = new JButton("Peform Sort");
        label_1 = new JLabel("Orginal List");
        label_2 = new JLabel("Sorted List");
        unsortedList = new JTextField();
        sortedList = new JTextField();
        SortOrder = new JPanel();
        NumericType = new JPanel();

    }

    private void setDisplayFrame() {

        mainFrame.setSize(500, 350);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    private void onShutDownRequest() {

        mainFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int reply;
                reply = JOptionPane.showConfirmDialog(mainFrame,
                        "Would you like to Shutdown this Program",
                        "Shutdown Request", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

        });
    }

    private void setSubPanels() {

        createPanelSubPanel(5, "Sort Order", SortOrder, button1, button2);
        createPanelSubPanel(260, "Numeric Type", NumericType, button3, button4);

    }

    private void createPanelSubPanel(int location, String title, JPanel panel,
            JRadioButton button1, JRadioButton button2) {

        panel.setLayout(new GridLayout(2, 1));
        panel.setBounds(location, 240, 230, 70);

        TitledBorder title_1;
        title_1 = BorderFactory.createTitledBorder(title);
        panel.setBorder(title_1);

        button1.setSelected(false);
        button2.setSelected(true);
        ButtonGroup group = new ButtonGroup();

        group.add(button1);
        group.add(button2);
        panel.add(button1);
        panel.add(button2);

        binarySearchTreeMain.add(panel);

    }

    private class ButtonAction extends BinarySearchTree {

        ButtonAction(JTextField textField) {

            button.addActionListener((ActionEvent event) -> {

                CheckData reviewData = new CheckData();

                BinarySearchTree<String> unsrtedList = new BinarySearchTree<>();

                if (button3.isSelected()) {

                    reviewData.confirmNoExceptions(textField.getText(), button3.getText(), unsrtedList);

                } else {

                    reviewData.confirmNoExceptions(textField.getText(), button4.getText(), unsrtedList);
                }

                if (button1.isSelected()) {

                    sortedList.setText(unsrtedList.inOrderSort(unsrtedList.root));
                } else {
                    sortedList.setText(unsrtedList.desOrderSort(unsrtedList.root));
                }
            });

        }
    }
}
