 import javax.swing.JFrame;
/**
 * Viewer for the calculator program
 * @author mehdi Himmiche
 *
 */
public class Project2Driver {
	/**
	 * Creates the frame for the project. 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new Project2Frame();
		frame.setTitle("Mehdi Himmiche - Project 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}

}