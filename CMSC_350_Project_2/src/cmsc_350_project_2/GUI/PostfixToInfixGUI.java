package cmsc_350_project_2.GUI;

import java.awt.event.*;
import cmsc_350_project_2.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Kalada Opuiyo
 */
public class PostfixToInfixGUI {

    private JFrame mainFrame;
    private JPanel postfixToInfixPanel;
    private JButton button;
    private JTextField infixExpression, result;
    private JLabel label_1, label_2;

    @SuppressWarnings("OverridableMethodCallInConstructor")

    PostfixToInfixGUI() {
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

        return new ButtonAction(infixExpression);
    }

    private void addComponents() {

        mainFrame.add(postfixToInfixPanel);
        postfixToInfixPanel.add(button);
        postfixToInfixPanel.add(label_1);
        postfixToInfixPanel.add(label_2);
        postfixToInfixPanel.add(infixExpression);
        postfixToInfixPanel.add(result);

        buttonActionOnClick();

    }

    private void setComponentsEditableVisible() {
        result.setEditable(false);
        mainFrame.setVisible(true);

    }

    private void setComponents() {

        postfixToInfixPanel.setLayout(null);
        button.setBounds(180, 55, 120, 30);
        label_1.setBounds(30, 10, 200, 25);
        label_2.setBounds(50, 110, 200, 25);
        result.setBounds(150, 110, 280, 25);
        infixExpression.setBounds(175, 10, 280, 25);

    }

    private void createNewComponents() {

        mainFrame = new JFrame("Three Address Generator");
        postfixToInfixPanel = new JPanel();
        button = new JButton("Construct Tree");
        label_1 = new JLabel("Enter Postfix Expression");
        label_2 = new JLabel("Infix Expression");
        infixExpression = new JTextField();
        result = new JTextField();

    }

    private void setDisplayFrame() {

        mainFrame.setSize(500, 200);
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

    public class ButtonAction extends PostfixToInfix {

        ButtonAction(JTextField textField) {

            button.addActionListener((ActionEvent event) -> {

                PostfixToInfix newExpression = new PostfixToInfix();

                try {

                    result.setText(newExpression.convertToInFix(textField.getText()));
                    AddressFile.register.add(result.getText() + "\n");
                    AddressFile.addToFile();

                } catch (RuntimeException re) {
                    JOptionPane.showMessageDialog(mainFrame, "Invalid Token " + "(" + re.getMessage() + ")");

                } catch (IOException ex) {
                    Logger.getLogger(PostfixToInfixGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        }
    }
}
