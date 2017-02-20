package cmsc_350_project_4.GUI;

import Exceptions.ClassNotFound;
import Exceptions.CycleFound;
import cmsc_350_project_4.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Kalada Opuiyo
 */
public class P4GUI {

    private JFrame mainFrame;
    private JPanel classDependencyGraphPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextArea order;

    private JButton button1, button2;

    public JTextField input, classToRecompile;
    private JLabel label_1, label_2;

    @SuppressWarnings("OverridableMethodCallInConstructor")

    P4GUI() {
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

        return new ButtonAction(input);
    }

    private void addComponents() {

        mainFrame.add(classDependencyGraphPanel);

        buttonActionOnClick();
    }

    private void setComponentsEditableVisible() {

        mainFrame.setVisible(true);

    }

    private void setBottomPanel() {

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBounds(2, 120, 565, 195);

        TitledBorder title_1;
        title_1 = BorderFactory.createTitledBorder("Recompilation Order");
        bottomPanel.setBorder(title_1);

        bottomPanel.setBackground(Color.white);

        order = new JTextArea();
        bottomPanel.add(order);
        classDependencyGraphPanel.add(bottomPanel);
    }

    private void setTopPanel() {

        topPanel.setLayout(new GroupLayout(topPanel));
        topPanel.setBounds(5, 5, 560, 110);

        TitledBorder title_1;
        title_1 = BorderFactory.createTitledBorder("");
        topPanel.setBorder(title_1);

        button1.setBounds(375, 15, 160, 30);
        label_1.setBounds(45, 15, 200, 25);
        input.setBounds(155, 15, 200, 25);

        button2.setBounds(385, 60, 140, 30);
        label_2.setBounds(35, 60, 200, 25);
        classToRecompile.setBounds(155, 60, 200, 25);

        topPanel.add(label_1);
        topPanel.add(label_2);

        topPanel.add(input);
        topPanel.add(classToRecompile);

        topPanel.add(button1);
        topPanel.add(button2);

        classDependencyGraphPanel.add(topPanel);

    }

    private void setComponents() {

        classDependencyGraphPanel.setLayout(null);

        setTopPanel();
        setBottomPanel();

    }

    private void createNewComponents() {

        mainFrame = new JFrame("Class Dependency Graph");
        classDependencyGraphPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        button1 = new JButton("Build Directed Graph");
        button2 = new JButton("Topological Order");
        label_1 = new JLabel("Input file name:");
        label_2 = new JLabel("Class to recompile:");
        input = new JTextField();
        classToRecompile = new JTextField();

    }

    private void setDisplayFrame() {

        mainFrame.setSize(575, 350);
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

    private class ButtonAction extends DirectedGraph {

        ButtonAction(JTextField textField) {

            DirectedGraph<String> newGraph = new DirectedGraph<>();

            button1.addActionListener((ActionEvent event) -> {

                try {

                    Scanner sc = new Scanner(new File("graphFile/" + input.getText()));

                    while (sc.hasNext()) {

                        newGraph.nameIndexCreate(sc.nextLine());
                    }

                    newGraph.createAdjList();
                    JOptionPane.showMessageDialog(mainFrame, "Graph Built Sucessfully");

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "File Did Not Open");
                }

            });

            button2.addActionListener((ActionEvent event) -> {

                try {

                    //Un-comment to check to confirm the present of a cycle throws an exception.
                    //newGraph.addEdge("ClassA", "ClassA");
                    
                    
                    if (newGraph.hasCycle()) {

                        throw new CycleFound();

                    } else {
                        order.setText(newGraph.sort(classToRecompile.getText()).toString().replaceAll("[\\[\\]]",""));
                    }

                } catch (ClassNotFound cnf) {
                    JOptionPane.showMessageDialog(mainFrame, "Class Not Found");
                } catch (CycleFound cf) {
                    JOptionPane.showMessageDialog(mainFrame, "Cycle Found");
                }
            });

        }
    }
}
