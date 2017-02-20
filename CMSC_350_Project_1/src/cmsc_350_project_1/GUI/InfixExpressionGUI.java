package cmsc_350_project_1.GUI;

import java.awt.*;
import java.awt.event.*;
import cmsc_350_project_1.*;
import javax.swing.*;
/**
 *
 * @author Kalada Opuiyo
 */
public class InfixExpressionGUI {

    private JFrame mainFrame;
    private JPanel InfixExpressionPanel;
    private JButton button;
    private JTextField infixExpression, result;
    private JLabel label_1, label_2;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    InfixExpressionGUI() {

        InfixExpressionPanel = createNewComponents();
        setSizeOfComponents();
        buttonActionOnClick(InfixExpressionPanel);
        createTextField(InfixExpressionPanel);
        setDisplayPanel(InfixExpressionPanel);

        onShutDownRequest();

    }

    public void setDisplayPanel(JPanel InfixExpressionPanel) {

        mainFrame.setLayout(new GridLayout());
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);

    }

    public void createTextField(JPanel InfixExpressionPanel) {

        InfixExpressionPanel.add(label_1);
        InfixExpressionPanel.add(infixExpression);
        InfixExpressionPanel.add(button);
        InfixExpressionPanel.add(label_2);
        InfixExpressionPanel.add(result);

        mainFrame.add(InfixExpressionPanel);

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

    private JPanel createNewComponents() {
        mainFrame = new JFrame("Infix Expression Evaluator");
        InfixExpressionPanel = new JPanel();

        label_1 = new JLabel("Enter Infix Expression");
        label_2 = new JLabel("Result");
        button = new JButton("Evaluate");
        infixExpression = new JTextField(20);
        result = new JTextField(20);

        return InfixExpressionPanel;
    }

    public void setSizeOfComponents() {

        mainFrame.setSize(500, 200);
        mainFrame.setLayout(new GridLayout(1, 1));
        result.setEditable(false);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void buttonActionOnClick(JPanel InfixExpressionPanel) {

        ButtonAction buttonAction = new ButtonAction(infixExpression);
        InfixExpressionPanel.add(button);

    }

    public class ButtonAction extends InfixExpression {

        ButtonAction(JTextField textField) {

            button.addActionListener((ActionEvent event) -> {
                InfixExpression newExpression = new InfixExpression();
                result.setText(newExpression.evaluateExpression(textField.getText()));
            });

        }

    }
}
