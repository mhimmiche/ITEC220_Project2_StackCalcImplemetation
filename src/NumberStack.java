import java.util.*;
/**
 * Creates the stack used in the operation
 * @author Mehdi Himmiche
 *
 */
public class NumberStack {
	// Declares the variables needed
	private Stack<Integer> bigNumber;
	
	/**
	 * Initializes the stack used
	 */
	public NumberStack() {
		bigNumber = new Stack<Integer>();
	}
	
	/**
	 * Parses the input string and adds it to the stack one digit at a time. 
	 * The pushing operation starts from the left. 
	 * @param inputString
	 */
	public void addToStack(String inputString) {
		for (int i = 0; i < inputString.length(); i++) {
			int digit = Character.getNumericValue(inputString.charAt(i));
			bigNumber.push(digit);
		}
	}
	
	/**
	 * Returns the stack created.
	 * @return the stack containing the numbers.
	 */
	public Stack<Integer> getStack() {
		return bigNumber;
	}

}
