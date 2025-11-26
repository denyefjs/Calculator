package my.calculator;

import java.awt.*;    
import java.awt.event.*;	
import java.util.Arrays;	
import javax.swing.*;		
import javax.swing.border.LineBorder;	

public class MyCalculator_main {
	
		
		int boardWidth = 360;
		int boardHeight = 540;
		
		Color colLightBlue = new Color(51, 153, 255);
		Color colLightGrey = new Color(204, 204, 204);
		Color colGrey = new Color(153, 153, 153);
		Color colWhite = new Color(255, 255, 255);
		Color colDarkGrey = new Color(51, 51,  51);
		Color colLighterBlue = new Color (79, 205, 255);
		Color colGrey2 = new Color (133, 132, 131);
		
		String[] buttonValues = {
				"AC", "√", "%", "÷", 
		        "7", "8", "9", "×", 
		        "4", "5", "6", "-",
		        "1", "2", "3", "+",
		        "0", ".", "+/-", "="
		    };
		    String[] rightSymbols = {"÷", "×", "-", "+"};
		    String[] topSymbols = {"AC", "√", "%"};
		    
		String A = "0";
		String operator = null;
		String B = null;
		
		JFrame frame = new JFrame("Calculator");	// створюємо нове вікно з назвою
		JLabel displayLabel = new JLabel();
		JPanel displayPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		
		public MyCalculator_main() {
		
		/**
		 * building of the window  
		 */
		
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0, 0));
		
		/**
		 * bulding of the display lable 
		 */
		displayLabel.setBackground(colWhite);
		displayLabel.setForeground(colDarkGrey);
		displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
		displayLabel.setHorizontalAlignment(JLabel.RIGHT);
		displayLabel.setText("0");
		displayLabel.setOpaque(true);
		
		
		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(displayLabel);
		frame.add(displayPanel, BorderLayout.NORTH);
		
		buttonsPanel.setLayout(new GridLayout(5, 4));
		buttonsPanel.setBackground(colWhite);
		frame.add(buttonsPanel);
		
		for (int i = 0; i < buttonValues.length; i++) {
			JButton button = new JButton();
			String buttonValue = buttonValues[i];
			button.setFont(new Font("Arial", Font.PLAIN, 35));
			button.setText(buttonValue);
			button.setFocusable(false);
			
			button.setBorder(new LineBorder(colGrey2));	
				//button styling
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
				
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton button = (JButton) e.getSource();
						String buttonValue = button.getText();
			
						if (buttonValue.equals("=")){
							if (A != null) {
								B = displayLabel.getText();
								double numA = Double.parseDouble(A);
								double numB = Double.parseDouble(B); 
								
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
								cleanAll();
							}
							
						}
						else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
							if (operator == null)	{
								A = displayLabel.getText();
								displayLabel.setText("0");
								B = "0";
							}
							operator = buttonValue;
						}
						else if (Arrays.asList(topSymbols).contains(buttonValue)) {
							if (buttonValue.equals("AC")) {
								cleanAll();
								displayLabel.setText("0"); 
							}
							else if (buttonValue == "√") {
								double squaredRoot = Double.parseDouble(displayLabel.getText());
								 displayLabel.setText(removeZeroDecimal(Math.sqrt(squaredRoot)));
								
							}
							else if (buttonValue == "%") {
								double numDisplay = Double.parseDouble(displayLabel.getText());
								numDisplay /= 100 ;
								displayLabel.setText(removeZeroDecimal(numDisplay));
							}
						}
						else {	
							//	.	dot
							if (buttonValue == ".") {
								if (!displayLabel.getText().contains(buttonValue)) {
									displayLabel.setText(displayLabel.getText() + buttonValue);
								}
							}
							else if (buttonValue == "+/-") {
								double numDisplay = Double.parseDouble(displayLabel.getText());
								numDisplay = numDisplay * -1;
								displayLabel.setText(removeZeroDecimal(numDisplay));
							}
							//	digits
							else if ("0123456789".contains(buttonValue)) {
								if (displayLabel.getText() == "0") {
									displayLabel.setText(buttonValue);
								}
								else if (displayLabel.getText() == buttonValue) {
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
	void cleanAll() {
		A = "0";
		operator = null;
		B = null;
		
	}
	


	
	
	String removeZeroDecimal(double numDisplay) {
		if (numDisplay % 1 == 0) {
			return Integer.toString((int) numDisplay);
		}
		return Double.toString((double) numDisplay);
	}
	
	
	
	
	public static void main(String args[]) {
		
		MyCalculator_main calculator = new MyCalculator_main();
 			
	}

	
	
	
}





 
