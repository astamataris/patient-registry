package JFrames;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DataTruncation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;

class HomePanel extends JPanel {

	/**
	 * instantiate a table model to be used with the home panel table
	 */
	static DefaultTableModel model = new DefaultTableModel();
	/**
	 * pass my table model into a table sorter to provide filterning for the
	 * table's search function
	 */
	TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
	/**
	 * instantiate the arraylist that will hold the csv file data and the array
	 * of strings that will hold data for each patient
	 */

	static ArrayList<Object[]> database = new ArrayList<Object[]>();
	static String[] patientRow = new String[19];

	JTextField textField;
	JTable table;

	static int patientID;
	static File counter;

	/**
	 * 
	 * The HomePanel() is the constructor which inherits froma a JPanel.When
	 * called, it constructs a JPanel that includes a number of components such
	 * as textfields and buttons as well as a table, that allow the user to save
	 * a new patient's information
	 */

	HomePanel() {
		setBackground(new Color(204, 255, 255));
		setBorder(null);
		this.setBounds(6, 6, 970, 554);
		setLayout(null);
		// create an add patient button that leads to the add patient panel
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				File counter = new File("counter.txt");
				try {
					Scanner scan = new Scanner(counter);
					// read the counter file and return the number read in the
					// id variable
					Integer id = Integer.parseInt(scan.nextLine());
					// set the generated patientID field to the number read in
					// the counter file. This number is incremented everytime a
					// patient is saved
					MainJFrame.APP.lblGeneratedPatientID.setText(id.toString());
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
				// when the add patient button is pressed show the add patient
				// panel
				MainJFrame.APP.setVisible(true);

			}
		});
		btnAddPatient.setBounds(155, 489, 117, 29);
		add(btnAddPatient);

		JButton btnAddDatabase = new JButton("Add Database");
		btnAddDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooseData = new JFileChooser();
				chooseData.showOpenDialog(null);

				File dataToUpload = chooseData.getSelectedFile();
				try {
					BufferedReader dataReader = new BufferedReader(new FileReader(dataToUpload));

					while (dataReader.ready()) {
						String line = dataReader.readLine();
						patientRow = line.split(",");
						database.add(patientRow);
						model.addRow(patientRow);

					}
					Methods.databaseToCSV();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAddDatabase.setBounds(334, 489, 117, 29);
		add(btnAddDatabase);

		JButton panelTable = new JButton("Exit");
		panelTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?");
				if (i == JOptionPane.YES_OPTION) {
					MainJFrame.frame.dispose();
				}
			}
		});
		panelTable.setBounds(718, 489, 117, 29);
		add(panelTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Click on a patient to view or update information");
		scrollPane.setBounds(6, 53, 958, 389);
		add(scrollPane);
		// create an array of strings to be used as the table headers
		String[] ta = { "ID", "Name", "Surname", "DOB", "Gender", "Patient Mob.", "Patient Phone", "Email",
				"Add. Line 1", " Add. Line 2", "Postcode", "Emergency contact", "Rel. to Patient", "Contact Number",
				"Billing", "Illnesses", "Comments", "Hour", "Minutes", "Next Appointment" };

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.setRowSorter(sorter);
		model.setColumnIdentifiers(ta);
		table.setModel(model);
		// disable header sorting
		for (int i = 0; i < 20; i++) {
			sorter.setSortable(i, false);
		}

		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// get the row clicked to return patient in the view patient
				// field
				// assign the row to an int variable
				int row = table.getSelectedRow();
				// set the date format that will be read from the database
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date date;
				Date date2;
				System.out.println(Arrays.toString(database.get(row)));

				try {
					// patient photos are saved using the patient ID so use the
					// patient ID
					String patientPhoto = database.get(row)[0].toString() + ".png";
					Image profilePic = ImageIO.read(new File(patientPhoto));

					// scale the image to fit the profile picture panel
					Image imak = profilePic.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
					// assign the image read to an icon
					ImageIcon icon = new ImageIcon(imak);
					MainJFrame.VPP.lblPatientPhoto.setIcon(icon);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// read data from the table which was created from the database
				//  and return it into the view patient panel fields
				
				MainJFrame.VPP.lblGeneratedPatientID.setText((String)table.getValueAt(row, 0));
				MainJFrame.VPP.textFieldName.setText((String) (table.getValueAt(row, 1)));
				MainJFrame.VPP.textFieldSurname.setText((String) (table.getValueAt(row, 2)));

				int n;
				String gender = (String)  (table.getValueAt(row, 4));

				if (gender.equals("Male")) {
					n = 0;
					MainJFrame.VPP.comboBoxGender.setSelectedIndex(n);
					System.out.println(n);
				} else if (gender.equals("Female")) {
					n = 1;
					MainJFrame.VPP.comboBoxGender.setSelectedIndex(n);
					System.out.println(n);
				} else {
					n = 2;
					MainJFrame.VPP.comboBoxGender.setSelectedIndex(n);
					System.out.println(n);
				}

				MainJFrame.VPP.textFieldPatientMobile.setText((String)  (table.getValueAt(row, 5)));
				MainJFrame.VPP.textFieldPatientPhone.setText((String)  (table.getValueAt(row, 6)));
				MainJFrame.VPP.textFieldEmail.setText((String)  (table.getValueAt(row, 7)));
				MainJFrame.VPP.textFieldAddressLine1.setText((String)  (table.getValueAt(row, 8)));
				MainJFrame.VPP.textFieldAddressLine2.setText((String)  (table.getValueAt(row, 9)));
				MainJFrame.VPP.textFieldPostcode.setText((String)  (table.getValueAt(row, 10)));
				MainJFrame.VPP.textFieldEmergencyContact.setText((String)  (table.getValueAt(row, 11)));
				MainJFrame.VPP.textFieldrelationshipToPatient.setText((String)  (table.getValueAt(row, 12)));
				MainJFrame.VPP.textFieldContactNumber.setText((String)  (table.getValueAt(row, 13)));
				MainJFrame.VPP.textAreaBilling.setText((String)  (table.getValueAt(row, 14)));
				MainJFrame.VPP.textAreaComments.setText((String)  (table.getValueAt(row, 16)));
				MainJFrame.VPP.textAreaIllnesses.setText((String)  (table.getValueAt(row, 15)));
				MainJFrame.VPP.textFieldHour.setText((String) (table.getValueAt(row, 17)));
				MainJFrame.VPP.textFieldMinutes.setText((String)  (table.getValueAt(row, 18)));
				// read the strings for dates and format them as date objects
				try {
					date = dateFormat.parse((String)  (table.getValueAt(row, 3)));

					date2 = dateFormat.parse((String)  (table.getValueAt(row, 19)));
					MainJFrame.VPP.dateChooserDOB.setDate(date);

					MainJFrame.VPP.dateChooserApp.setDate(date2);

				} catch (ParseException ee) {
					ee.printStackTrace();
				}
				// show the View patient panel and hide the home panel
				MainJFrame.VPP.setVisible(true);
				MainJFrame.HP.setVisible(false);
				MainJFrame.LP.setVisible(false);
				MainJFrame.APP.setVisible(false);

			}

		});
		scrollPane.setViewportView(table);
		// ensure the table is up to date with the CSV
		Methods.CSVtoTable();

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// declare a row filter for your table model
				// //http://zawoad.blogspot.co.uk/2009/02/filter-jtable-row-with-input-in-text.html
				RowFilter<DefaultTableModel, Object> rf = null;

				try {
					// Initialise with a regular expression that will allow me
					// to search through the table and update based on the text
					// inserted
					rf = RowFilter.regexFilter("^" + textField.getText());

				} catch (java.util.regex.PatternSyntaxException x) {
					return;
				}
				sorter.setRowFilter(rf);

			}
		});
		textField.setBounds(66, 5, 898, 28);
		add(textField);
		textField.setColumns(10);

		JButton btnExportDatabase = new JButton("Export Database");
		btnExportDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					File patientsExport = new File("patients.csv");
					BufferedReader readCSV = new BufferedReader(new FileReader(patientsExport));

					File exported = new File(System.getProperty("user.home"), "exported.csv");
					BufferedWriter out = new BufferedWriter(new FileWriter(exported));
					while (readCSV.ready()) {
						String line = readCSV.readLine();
						System.out.println(line);
						out.append(line + "\n");

					}
					out.close();
					readCSV.close();
					JOptionPane.showMessageDialog(btnExportDatabase,
							"The database was exported in your home directory by the name \n exported.csv", "Success", JOptionPane.OK_OPTION);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnExportDatabase.setBounds(521, 489, 135, 29);
		add(btnExportDatabase);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSearch.setBounds(6, 10, 61, 16);
		add(lblSearch);

	}
}
