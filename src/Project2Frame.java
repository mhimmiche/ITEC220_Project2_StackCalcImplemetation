import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Creates the frame used for the project and sets up the layout. 
 * @author Mehdi Himmiche
 *
 */
public class Project2Frame extends JFrame {
	// Declating the variables used
	private JLabel nameLabel;
	private JButton addButton;
	private JButton subButton;
	private JButton clearButton;
	private JButton swapButton;
	private JTextField numField1;
	private JTextField numField2;
	private JTextArea outputArea;
	private String input1;
	private String input2;
	private String errorString;
	boolean allFilled;
	private NumberOperation operation;
	
	/**
	 * Initializes all the buttons and text fields. 
	 * Creates the output area. 
	 */
	public Project2Frame() {
		nameLabel = new JLabel("<html>Calculator Program. <br>By Mehdi Himmiche</html>");
		operation = new NumberOperation();
		numField1 = new JTextField();
		numField2 = new JTextField();
		addButton = new JButton("Add");
		subButton = new JButton("Substract");
		swapButton = new JButton("Swap Numbers");
		clearButton = new JButton("Clear Inputs");
		outputArea = new JTextArea(10, 30);
		errorString = "";
		createPanel();
	}

	/**
	 * Creates the full panel. 
	 * The panel contains the input areas, buttons, and the output area. 
	 */
	private void createPanel() {
		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new BorderLayout());
		add(finalPanel);
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BorderLayout());
		JPanel buttonPanel = buttonPanel();
		JScrollPane outputPane = outputAreaPane();
		tempPanel.add(buttonPanel, BorderLayout.NORTH);
		tempPanel.add(outputPane, BorderLayout.CENTER);
		
		finalPanel.add(tempPanel, BorderLayout.CENTER);
		finalPanel.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.EAST);
		finalPanel.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.WEST);
		finalPanel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
		finalPanel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);
	}
	
	/**
	 * Creates the ScrollPane where the result is displayed
	 * @return the display area
	 */
	private JScrollPane outputAreaPane() {
		JScrollPane outputPane = new JScrollPane(outputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		outputArea.setEditable(false);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		
		return outputPane;
	}
	
	/**
	 * Creates the panel containing the buttons and input areas. 
	 * @return the panel containing the buttons
	 */
	private JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(8, 1, 0, 10));
		ActionListener pushListener = new addListener();
		addButton.addActionListener(pushListener);
		ActionListener popListener = new subListener();
		subButton.addActionListener(popListener);
		ActionListener clearListener = new clearListener();
		clearButton.addActionListener(clearListener);
		ActionListener swapListener = new swapListener();
		swapButton.addActionListener(swapListener);
		buttonPanel.add(nameLabel);
		buttonPanel.add(numField1);
		buttonPanel.add(numField2);
		buttonPanel.add(addButton);
		buttonPanel.add(subButton);
		buttonPanel.add(swapButton);
		buttonPanel.add(clearButton);
		
		return buttonPanel;
	}
	
	/**
	 * Checks to see if the inputs by the user are correct. 
	 * The method runs to check if the user input a full string (didn't just not enter anything)
	 * as well as checks if the user input is a number. 
	 * @return true if everything is correct, false if there's an error.
	 */
	public boolean checkInputs() {
		boolean isGood = true;
		if (!numField1.getText().equals("")) {
			if (!operation.isGoodInt(numField1.getText())) {
				isGood = false;
				errorString += "You entered an inappropriate value for the first input \n";
			}
		} else {
			errorString += "You did not fill in anything for the first input \n";
			isGood = false;
		}
		if (!numField2.getText().equals("")) {
			if (!operation.isGoodInt(numField2.getText())) {
				isGood = false;
				errorString += "You entered an inappropriate value for the second input \n";
			}
		} else {
			errorString += "You did not fill in anything for the second input \n";
			isGood = false;
		}
		return isGood;
	}
	
	/**
	 * Listener class for the addition button
	 * @author Mehdi Himmiche
	 *
	 */
	class addListener implements ActionListener {
		/**
		 * Performs the addition woperation when the button is pressed
		 */
		public void actionPerformed(ActionEvent e) {
			errorString = "";
			boolean isGood = checkInputs();
			if (!isGood) {
				outputArea.setText(errorString);
			} else {
				input1 = numField1.getText();
				input2 = numField2.getText();
				operation.getInputs(input1, input2);
				String result = operation.addNumber();
				outputArea.setText(result);
			}
		}
	}
	
	/**
	 * Listener class for the clear button
	 * @author Mehdi Himmiche
	 *
	 */
	class clearListener implements ActionListener {
		/**
		 * Clears the input and output areas
		 */
		public void actionPerformed(ActionEvent e) {
			numField1.setText("");
			numField2.setText("");
			outputArea.setText("");
		}
	}
	
	/**
	 * Listener class for the swap button
	 * @author Mehdi Himmiche
	 *
	 */
	class swapListener implements ActionListener {
		/**
		 * Swaps the input texts 
		 */
		public void actionPerformed(ActionEvent e) {
			String tempString1 = numField1.getText();
			String tempString2 = numField2.getText();
			numField1.setText(tempString2);
			numField2.setText(tempString1);
		}
	}
	
	/**
	 * Listener class for the subtraction listener
	 * @author Mehdi Himmiche
	 *
	 */
	class subListener implements ActionListener {
		/**
		 * Performs the subtraction when button is pressed
		 */
		public void actionPerformed(ActionEvent e) {
			errorString = "";
			boolean isGood = checkInputs();
			if (!isGood) {
				outputArea.setText(errorString);
			} else {
				String input1 = numField1.getText();
				String input2 = numField2.getText();
				operation.getInputs(input1, input2);
				String result = operation.subNumber();
				outputArea.setText(result);
			}
		}
	}
}
