package my.calculator;

import java.awt.*;    
import java.awt.event.*;	
import java.util.Arrays;	
import javax.swing.*;		
import javax.swing.border.LineBorder;	

/**
 * Simple GUI Calculator built with Java Swing.
 * Handles basic operations: +, -, ×, ÷, √, %, +/-.
 */
public class MyCalculator_main {
		
		// -------------------------
		// Calculator window settings
		// -------------------------
		
		int boardWidth = 360;
		int boardHeight = 540;
		
		// -------------------------
		// UI Colors
		// -------------------------
		
		Color colLightBlue = new Color(51, 153, 255);
		Color colLightGrey = new Color(204, 204, 204);
		Color colGrey = new Color(153, 153, 153);
		Color colWhite = new Color(255, 255, 255);
		Color colDarkGrey = new Color(51, 51,  51);
		Color colLighterBlue = new Color (79, 205, 255);
		Color colGrey2 = new Color (133, 132, 131);
		
		// -------------------------
		// Button values for the 5x4 grid
		// -------------------------
		String[] buttonValues = {
				"AC", "√", "%", "÷", 
		        "7", "8", "9", "×", 
		        "4", "5", "6", "-",
		        "1", "2", "3", "+",
		        "0", ".", "+/-", "="
		    };
		
		// Operator groups for coloring / logic
		String[] rightSymbols = {"÷", "×", "-", "+"};
		String[] topSymbols = {"AC", "√", "%"};
		    
		// -------------------------
		// Calculator operation variables
		// A — first number, B — second number
		// operator — selected operator
		// -------------------------
		String A = "0";
		String operator = null;
		String B = null;
		
		// -------------------------
		// Swing components
		// -------------------------
		JFrame frame = new JFrame("Calculator");	
		JLabel displayLabel = new JLabel();
		JPanel displayPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		/**
		 * Constructor builds the entire calculator window.
		 */
		public MyCalculator_main() {
		
		// -------------------------
		// Setup main window
		// -------------------------		
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0, 0));
		
		// -------------------------
		// Setup display label
		// -------------------------
		displayLabel.setBackground(colWhite);
		displayLabel.setForeground(colDarkGrey);
		displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
		displayLabel.setHorizontalAlignment(JLabel.RIGHT);
		displayLabel.setText("0");
		displayLabel.setOpaque(true);
		
		
		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(displayLabel);
		frame.add(displayPanel, BorderLayout.NORTH);
		
		// -------------------------
		// Setup grid of buttons
		// -------------------------
		buttonsPanel.setLayout(new GridLayout(5, 4));
		buttonsPanel.setBackground(colWhite);
		frame.add(buttonsPanel);
		
		// -------------------------
		// Create each button
		// -------------------------
		for (int i = 0; i < buttonValues.length; i++) {
			
			JButton button = new JButton();
			String buttonValue = buttonValues[i];
			button.setFont(new Font("Arial", Font.PLAIN, 35));
			button.setText(buttonValue);
			button.setFocusable(false);
			button.setBorder(new LineBorder(colGrey2));	
				
				// Button styling based on type
				if  (buttonValue.equals("=")) {
					button.setBackground(colDarkGrey);
					button.setForeground(colWhite);
				}
				else if ( Arrays.asList(rightSymbols).contains(buttonValue)) {
					button.setBackground(colGrey);
					button.setForeground(colWhite);
				}
				else if (Arrays.asList(topSymbols).contains(buttonValue)) {
						button.setBackground(colLightBlue);
						button.setForeground(colWhite);
				}
				else {
					button.setBackground(colWhite);
					button.setForeground(colDarkGrey);
				}
				
				buttonsPanel.add(button);
				
				// -------------------------
				// Button action logic
				// -------------------------
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton button = (JButton) e.getSource();
						String buttonValue = button.getText();
						
						// = : calculate result
						if (buttonValue.equals("=")){
							if (A != null) {
								B = displayLabel.getText();
								double numA = Double.parseDouble(A);
								double numB = Double.parseDouble(B); 
								
//								Perform operator
								if (operator.equals("+")) {
									displayLabel.setText(removeZeroDecimal(numA + numB));
								}
								else if (operator.equals("-")) {
									displayLabel.setText(removeZeroDecimal(numA - numB));
								}
								else if (operator.equals("×")) {
									displayLabel.setText(removeZeroDecimal(numA * numB));
								}
								else if (operator.equals("÷")) {
									displayLabel.setText(removeZeroDecimal(numA / numB));
								}
								
//								Reset variables
								cleanAll();
							}
							
						}
						
						// Operator pressed (+ - × ÷)
						else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
							if (operator == null)	{
								A = displayLabel.getText();
								displayLabel.setText("0");
								B = "0";
							}
							operator = buttonValue;
						}
						
						// Special top buttons: AC, √, %
						else if (Arrays.asList(topSymbols).contains(buttonValue)) {
							
							// Reset calculator
							if (buttonValue.equals("AC")) {
								cleanAll();
								displayLabel.setText("0"); 
							}
							
							//Square root
							else if (buttonValue.equals("√")) {
								double squaredRoot = Double.parseDouble(displayLabel.getText());
								 displayLabel.setText(removeZeroDecimal(Math.sqrt(squaredRoot)));
								}
							
							// Percentage
							else if (buttonValue.equals("%")) {
								double numDisplay = Double.parseDouble(displayLabel.getText());
								numDisplay /= 100 ;
								displayLabel.setText(removeZeroDecimal(numDisplay));
							}
						}
						
//						Number or dot or +/- toggle
						else {	
							
							//	Dot	(.)	
							if (buttonValue.equals(".")) {
								if (!displayLabel.getText().contains(buttonValue)) {
									displayLabel.setText(displayLabel.getText() + buttonValue);
								}
							}
							
							// +/- sign toggle
							else if (buttonValue.equals("+/-")) {
								double numDisplay = Double.parseDouble(displayLabel.getText());
								numDisplay = numDisplay * -1;
								displayLabel.setText(removeZeroDecimal(numDisplay));
							}
							
							// Digits 0–9
							else if ("0123456789".contains(buttonValue)) {
								if (displayLabel.getText().equals("0")) {
									displayLabel.setText(buttonValue);
								}
								
								// Replace leading zero
								else if (displayLabel.getText().equals(buttonValue)) {
									displayLabel.setText(displayLabel.getText() + buttonValue);
								}
								else {
									displayLabel.setText(displayLabel.getText() + buttonValue);
								}
							}
						}
					}
				});
		
		}
		frame.setVisible(true);
	}
		
	/**
	* Resets internal calculator state (A, B, operator).
	*/
	void cleanAll() {
		A = "0";
		operator = null;
		B = null;
	}
	
	/**
	* Removes ".0" from whole numbers (e.g. 3.0 → "3").
	*/
	String removeZeroDecimal(double numDisplay) {
		if (numDisplay % 1 == 0) {
			return Integer.toString((int) numDisplay);
		}
		return Double.toString((double) numDisplay);
	}
	
	/**
	 * Program entry point.
	 */
	public static void main(String args[]) {
		
		MyCalculator_main calculator = new MyCalculator_main();
 			
	}
	
}

