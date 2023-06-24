package fr.cs.group06.myVelib.userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MyVelibGUI {
    private JFrame frame;
    private JTextField textField;
    private JTextArea textArea;
    private MyVelibCLUI myVelibCLUI;

    public MyVelibGUI() {
        myVelibCLUI = new MyVelibCLUI();

        frame = new JFrame("MyVelib GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("MyVelib GUI");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        textField = new JTextField();
        textField.setText("Write your command here!");
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals("Write your command here!")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Write your command here!");
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = textField.getText();
                executeCommand(command);
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    e.consume();
                    String command = textField.getText();
                    executeCommand(command);
                }
            }
        });
        frame.add(textField, BorderLayout.SOUTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton runButton = new JButton("Run Command");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = textField.getText();
                executeCommand(command);
            }
        });
        frame.add(runButton, BorderLayout.EAST);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(exitButton, BorderLayout.WEST);

        frame.setVisible(true);
    }

    private void executeCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        String commandName = parts[0];
        String[] commandArgs = new String[parts.length ];
        System.arraycopy(parts, 0, commandArgs, 0, commandArgs.length);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream oldOut = System.out;
        System.setOut(printStream);

        switch (commandName) {
            case "setup":
                myVelibCLUI.setup(commandArgs);
                break;
            case "addUser":
                myVelibCLUI.addUser(commandArgs);
                break;
            case "offline":
                myVelibCLUI.offline(commandArgs);
                break;
            case "online":
                myVelibCLUI.online(commandArgs);
                break;
            case "rentBike":
                try {
                    myVelibCLUI.rentBike(commandArgs);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "returnBike":
                try {
                    myVelibCLUI.returnBike(commandArgs);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "sortStation":
            	try {
                    myVelibCLUI.sortStation(commandArgs);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "displayStation":
                myVelibCLUI.displayStation(commandArgs);
                break;
            case "displayUser":
                try {
                    myVelibCLUI.displayUser(commandArgs);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "display":
            	try {
                    myVelibCLUI.display(commandArgs);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }

        System.out.flush();
        System.setOut(oldOut);

        String output = outputStream.toString().trim();
        textArea.append(command + "\n" + output + "\n\n");
        textField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyVelibGUI();
                new MainCLUI();
            }
        });
    }
}
