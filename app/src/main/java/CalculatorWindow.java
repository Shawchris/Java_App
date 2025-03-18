
package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A simple calculator window
 */
public class CalculatorWindow extends JFrame {
    private JTextField display;
    private String operator = "";
    private double firstNumber = 0;
    private boolean startNewInput = true;
    
    /**
     * Constructor
     */
    public CalculatorWindow() {
        // Set up the window
        setTitle("Calculator");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Initialize the UI components
        initComponents();
    }
    
    /**
     * Initialize the UI components
     */
    private void initComponents() {
        // Set layout
        setLayout(new BorderLayout(5, 5));
        
        // Create display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setText("0");
        add(display, BorderLayout.NORTH);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Define button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "CE", "±", "√"
        };
        
        // Create buttons
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }
        
        add(buttonPanel, BorderLayout.CENTER);
        
        // Status bar
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        JLabel statusLabel = new JLabel("Ready");
        statusBar.add(statusLabel, BorderLayout.WEST);
        add(statusBar, BorderLayout.SOUTH);
    }
    
    /**
     * Button click event listener
     */
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            // Check if it's a number or decimal point
            if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
                if (startNewInput) {
                    display.setText("");
                    startNewInput = false;
                }
                display.setText(display.getText() + command);
            }
            // Handle operators
            else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                if (!startNewInput) {
                    calculate();
                    startNewInput = true;
                }
                firstNumber = Double.parseDouble(display.getText());
                operator = command;
            }
            // Handle equals sign
            else if (command.equals("=")) {
                calculate();
                operator = "";
                startNewInput = true;
            }
            // Handle clear
            else if (command.equals("C")) {
                display.setText("0");
                startNewInput = true;
                operator = "";
                firstNumber = 0;
            }
            // Handle clear entry
            else if (command.equals("CE")) {
                display.setText("0");
                startNewInput = true;
            }
            // Handle sign change
            else if (command.equals("±")) {
                String currentText = display.getText();
                if (currentText.startsWith("-")) {
                    display.setText(currentText.substring(1));
                } else if (!currentText.equals("0")) {
                    display.setText("-" + currentText);
                }
            }
            // Handle square root
            else if (command.equals("√")) {
                double number = Double.parseDouble(display.getText());
                if (number >= 0) {
                    display.setText(String.valueOf(Math.sqrt(number)));
                    startNewInput = true;
                } else {
                    display.setText("Error");
                    startNewInput = true;
                }
            }
        }
        
        /**
         * Calculate the result
         */
        private void calculate() {
            if (!operator.isEmpty()) {
                double secondNumber = Double.parseDouble(display.getText());
                double result = 0;
                
                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                
                display.setText(String.valueOf(result));
                firstNumber = result;
            }
        }
    }
}