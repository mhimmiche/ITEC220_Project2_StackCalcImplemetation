import java.util.*;

/**
 * Class to handle the operations allowed; subtraction and addition. 
 * @author Mehdi Himmiche
 *
 */
public class NumberOperation {
	// Declare the variables used
	private NumberStack numberOne;
	private NumberStack numberTwo;
	private String inputOne;
	private String inputTwo;
	private Stack<Integer> resultStack; 
	private Stack<Integer> firstStack;
	private Stack<Integer> secondStack;
	private int firstInt;
	private int secondInt;
	private int carry;
	private int result;
	private boolean isFlipped;
	
	/**
	 * Initializes the boolean used
	 * Creates two NumberStack objects 
	 */
	public NumberOperation() {
		isFlipped = false;
		numberOne = new NumberStack();
		numberTwo = new NumberStack();
	}
	
	/**
	 * Checks if the input string contains all numbers
	 * @param inputString
	 * @return true if every character is a number, false if there is a character that isn't
	 */
	public boolean isGoodInt(String inputString) {
		boolean isGood = true;
		for (int i = 0; i < inputString.length(); i++) {
			char intChar = inputString.charAt(i);
			if (!Character.isDigit(intChar)) {
				isGood = false;
			}
		}
		return isGood;
	}
	
	/**
	 * Determines the operation order based on the length of each input string.
	 * If the first string is longer, push it onto the first stack.
	 * If the second string is longer, push it onto the first stack. 
	 * If they're equal, call the equalLengths method.
	 */
	public void operationOrder() {
		if (inputOne.length() > inputTwo.length()) {
			numberOne.addToStack(inputOne);
			numberTwo.addToStack(inputTwo);
		}
		else if (inputOne.length() < inputTwo.length()){
			numberOne.addToStack(inputTwo);
			numberTwo.addToStack(inputOne);
			isFlipped = true;
		}
		else {
			equalLengths();
		}
	}
	
	/**
	 * Determines the order of operation if the two input strings are the same size. 
	 * The checking makes subtraction easier to handle. 
	 */
	public void equalLengths() {	
		if (inputOne.compareTo(inputTwo) < 0) {
			numberOne.addToStack(inputTwo);
			numberTwo.addToStack(inputOne);
			isFlipped = true;
		}
		else {
			numberOne.addToStack(inputOne);
			numberTwo.addToStack(inputTwo);
		}		
	}
	
	/**
	 * Removes all the leading zeros from the input string
	 * @param inputString
	 * @return inputString without leading zeros. 
	 */
	public String leadZero(String inputString) {
		while (inputString.charAt(0) == '0' && inputString.length() > 1) {
			inputString = inputString.substring(1);
		}
		String output = inputString;
		return output;
	}
	
	/**
	 * Gets the users inputs from GUI and stores them in the local strings. 
	 * @param firstInput
	 * @param secondInput
	 */
	public void getInputs(String firstInput, String secondInput) {
		inputOne = firstInput;
		inputTwo = secondInput;
	}
	
	/**
	 * Set up for the operations. Prevent having to retype this in each operation. 
	 * Reset the boolean value of isFlipped to false. 
	 * Resets the stacks to new empty stacks (This is done because I am paranoid there 
	 * might be a bug where not all the numbers are popped). 
	 * Clears the leading zeros from the inputs (if there are any)
	 * Determines the appropriate order for pushing the strings. 
	 * Return the stacks from the NumberStack class. 
	 * reset carry. 
	 */
	public void operationSetUp() {
		isFlipped = false;
		resultStack = new Stack<Integer>();
		numberOne = new NumberStack();
		numberTwo = new NumberStack();
		inputOne = leadZero(inputOne);
		inputTwo = leadZero(inputTwo);
		operationOrder();
		firstStack = numberOne.getStack();
		secondStack = numberTwo.getStack();
		carry = 0;
	}
	
	/**
	 * Add the two digits together and store them into a string
	 * Formats the output string to make it more legible for user. 
	 * @return the result string
	 */
	public String addNumber() {
		operationSetUp();		
		while (!firstStack.isEmpty()) {
			firstInt = (int) firstStack.pop();
			if (secondStack.isEmpty()) {
				secondInt = 0;
			}
			else {
				secondInt = (int) secondStack.pop();
			}			
			result = firstInt + secondInt + carry;
			int resultDigit = result % 10;
			carry = result / 10;
			resultStack.push(resultDigit);
		}
		if (carry > 0) {
			resultStack.push(carry);
		}
		String resultString = toString();
		resultString = formatOutput(resultString);
		return resultString;
	}
	
	/**
	 * Subtract the two digits together and store them into a string.
	 * Formats the output string to make it more legible for user. 
	 * @return the result string
	 */
	public String subNumber() {
		operationSetUp();
		while (!firstStack.isEmpty()) {
			firstInt = (int) firstStack.pop();
			firstInt -= carry;
			carry = 0;
			if (secondStack.isEmpty()) {
				secondInt = 0;
			}
			else {
				secondInt = (int) secondStack.pop();
			}	
			if (firstInt < secondInt) {
				result = 10 + firstInt - secondInt;
				carry = 1;				
			}
			else {
				result = firstInt - secondInt;
			}
			resultStack.push(result);
		}
		String resultString = toString();
		resultString = leadZero(resultString);
		resultString = formatOutput(resultString);
		if (isFlipped) {
			resultString = "-" + resultString;
		}		
		return resultString;
	}
	
	/**
	 * Override the toString method to return each digit in the result stack. 
	 */
	public String toString() {
		String result = "";
		
		while (!resultStack.isEmpty()) {
			result += resultStack.pop();
		}	
		return result;
	}
	
	/**
	 * Formats the output string by separating the thousands, millions... etc. 
	 * This makes it easier to read for the user (and debug for the programmer). 
	 * @param outputString
	 * @return formatted outputString
	 */
	public String formatOutput(String outputString) {
		String returnString = "";
		int curPos = 0;
		for (int i = outputString.length() - 1; i >= 0; i--) {
			curPos++;
			returnString = outputString.charAt(i) + returnString;
			if (curPos == 3 && i != 0) {
				returnString = " " + returnString;
				curPos = 0;
			}
		}
		return returnString;
	}
}
