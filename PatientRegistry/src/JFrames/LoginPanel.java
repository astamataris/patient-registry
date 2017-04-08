package JFrames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.HashMap;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Dimension;
import javax.swing.SpringLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 class LoginPanel extends JPanel {
	/**
	 * Creates a hash map that will hold the username and password
	 */
	 HashMap<String, String> hmap = new HashMap<String, String>(); 

	 JTextField txtUsername;
	 JPasswordField passwordField;

	/**
	 * Create the panel. The constructor creates a Panel with two textfields labeled username and
	 * password respectively and one login button. The constructor also adds action listeners on each of the components mentioned above.
	 * When focus is lost from the login button an error message pops up and if both fields are filled and correct the visibility method is called.
	 */
	 LoginPanel() {
		this.setBounds(6, 6, 970, 560);
		this.setPreferredSize(new Dimension(960, 544));
		this.setVisible(true);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblUsername = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 357, SpringLayout.WEST, this);
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, -542, SpringLayout.EAST, this);
		add(lblPassword);

		txtUsername = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtUsername, 441, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, txtUsername, -355, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 6, SpringLayout.NORTH, txtUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -6, SpringLayout.WEST, txtUsername);
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsername.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Username is empty");
				}
			}
		});
		add(txtUsername);
		txtUsername.setColumns(10);

		JButton btnLogin = new JButton("Login");
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, lblUsername);
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, -352, SpringLayout.EAST, this);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(MainJFrame.HP);
				
			}
		});
		add(btnLogin);	

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if( e.getKeyCode() == KeyEvent.VK_ENTER){
					visibility(MainJFrame.HP); //http://stackoverflow.com/questions/4673350/detecting-when-user-presses-enter-in-java
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 28, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 6, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.SOUTH, txtUsername, -16, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 265, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 441, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -355, SpringLayout.EAST, this);
		add(passwordField);
		//input the username and password
		hmap.put("admin", "pass");

	}
/**
 * The main purpose of the visibility method is to validate the username against the password entered and if correct change the visibility of 
 * the login pannel. 
 * @param a serves the purpose of allowing the login panel to communicate with the home panel allowing it to change its visibility.
 */
	 void visibility(HomePanel a) {
		String username = txtUsername.getText();

		String password = String.valueOf(passwordField.getPassword());
		System.out.println(password);
		if (hmap.containsKey(username)) {

			if (hmap.get(username).equals(password)) {
				
				setVisible(false);
				a.setVisible(true);
				//HomePanel.lblWelcomeMessage.setText("Welcome doctor");

			} else {
				JOptionPane.showMessageDialog(null, "Invalid username or password", "Login error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid username or password", "Login error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
