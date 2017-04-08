package JFrames;

import java.awt.CardLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

 class MainJFrame extends JFrame {
	// instantiate each panel and make it static in order to be accessed by the
	// other classes as well
	/**
	 * Create an instance of the Login panel to pass it as an argument to the constructor of the Jframe
	 */
	 static LoginPanel LP = new LoginPanel();
	/**
	 * Create an instance of the Home panel to pass it as an argument to the constructor of the Jframe
	 */
	 static HomePanel HP = new HomePanel();
	/**
	 * Create an instance of the Add Patient panel to pass it as an argument to the constructor of the Jframe
	 */
	 static AddPatientPanel APP = new AddPatientPanel();
	/**
	 * Create an instance of the View Patient panel to pass it as an argument to the constructor of the Jframe
	 */
	 static ViewPatientPanel VPP = new ViewPatientPanel();
	 static MainJFrame frame;


/**
 * The main method calls the constructor of the Jframe passing the four panels as parameters in the method.
 * @param args
 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// pass the panels into the constructor to allow them to
					// talk to each other
					 frame = new MainJFrame(LP,HP, APP,VPP);
					
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	// define the class constructor passing each panel in in order to allow the
	// panels to talk to the jframe
	/**
	 * This is the method constructor for the main JFrame. The constructor takes four parameters of the four different panels
	 * and adds them to the JFrame
	 * @param a Login panel parameter
	 * @param b Home Panel parameter
	 * @param c	Add Patient panel parameter
	 * @param d View Patient panel parameter
	 */
	 MainJFrame(LoginPanel a,HomePanel b, AddPatientPanel c, ViewPatientPanel d) {
		setTitle("Patient Registry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		super.setSize(982, 587);
		getContentPane().setBackground(UIManager.getColor("Menu.selectionBackground"));
		getContentPane().setLayout(null);
		// create a panel to add the other panels in and set into card layout to
		// show and hide panels accordingly
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 970, 554);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		// add the parameter panels to the JFrame
		panel.add(a);
		panel.add(c);
		panel.add(d);
		panel.add(b);
	}
}
