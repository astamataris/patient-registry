package JFrames;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.SpringLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DatabaseMetaData;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import org.jdatepicker.util.JDatePickerUtil;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import javax.swing.JFormattedTextField.AbstractFormatter;
import com.toedter.calendar.JMonthChooser;
import java.awt.BorderLayout;
import com.toedter.calendar.JYearChooser;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.JList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.Font;

class AddPatientPanel extends JPanel {
	JLabel lblGeneratedPatientID = new JLabel();
	JTextField textFieldName;
	JTextField textFieldSurname;
	JTextField textFieldPatientMobile;
	JTextField textFieldPatientPhone;
	JTextField textFieldAddressLine1;
	JTextField textFieldAddressLine2;
	JTextField textFieldPostcode;
	JTextField textFieldEmergencyContact;
	JTextField textFieldrelationshipToPatient;
	JTextField textFieldContactNumber;
	JPanel panelPatientPhoto = new JPanel();
	JPanel panelIllnessPhotos = new JPanel();
	JComboBox comboBoxGender = new JComboBox();
	static Integer patientID;
	static File patients = null;
	JTextField textFieldEmail;
	JTextField textFieldHour;
	JTextField textFieldMinutes;
	JDateChooser dateChooserDOB = new JDateChooser();
	JDateChooser dateChooserApp = new JDateChooser();
	JTextArea textAreaBilling = new JTextArea();
	JTextArea textAreaIllnesses = new JTextArea();
	JTextArea textAreaComments = new JTextArea();
	JFileChooser chooseImage = new JFileChooser();

	/**
	 * The AddPatientPanel() is the constructor which inherits froma a
	 * JPanel.When called, it constructs a JPanel that includes a number of
	 * components such as textfields and buttons that allow the user to save a
	 * new patient's information
	 */
	AddPatientPanel() {

		setBackground(new Color(204, 255, 204));
		// check if a patients file already exists
		boolean fileTest = false;
		patients = new File("patients.csv");

		try {
			// create new file
			patients = new File("logs.txt");

			// tries to create new file in the system
			fileTest = patients.createNewFile();

			// prints if the file is created
			System.out.println("File created: " + fileTest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setBounds(6, 6, 970, 554);
		setLayout(null);

		JPanel PatientInfoPanel = new JPanel();
		PatientInfoPanel.setBackground(new Color(204, 255, 204));
		PatientInfoPanel.setBounds(6, 6, 609, 542);
		add(PatientInfoPanel);
		PatientInfoPanel.setLayout(new MigLayout("", "[][][grow]",
				"[][][][][grow][grow][grow][grow][][][][grow][grow][grow][grow][grow]"));

		JLabel lblPatientid = new JLabel("PatientID");
		PatientInfoPanel.add(lblPatientid, "cell 0 0");

		PatientInfoPanel.add(lblGeneratedPatientID, "cell 2 0");

		JLabel lblGender = new JLabel("Gender");
		PatientInfoPanel.add(lblGender, "flowx,cell 2 1,alignx right");

		// create an array of Strings to populate the combobox
		String[] genders = { "Male", "Female", "Other" };
		comboBoxGender.setModel(new DefaultComboBoxModel(genders));
		PatientInfoPanel.add(comboBoxGender, "cell 2 1,alignx trailing");

		JLabel lblName = new JLabel("Name");
		PatientInfoPanel.add(lblName, "cell 0 2");

		textFieldName = new JTextField();
		PatientInfoPanel.add(textFieldName, "flowx,cell 2 2,growx");
		textFieldName.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");
		PatientInfoPanel.add(lblSurname, "cell 0 3");

		textFieldSurname = new JTextField();
		PatientInfoPanel.add(textFieldSurname, "flowx,cell 2 3,growx");
		textFieldSurname.setColumns(10);

		JLabel lblDob = new JLabel("DOB");
		PatientInfoPanel.add(lblDob, "cell 0 4");

		JLabel lblMobile = new JLabel("Mobile");
		PatientInfoPanel.add(lblMobile, "cell 2 2");

		JLabel lblPhone = new JLabel("Phone ");
		PatientInfoPanel.add(lblPhone, "cell 2 3");

		textFieldPatientMobile = new JTextField();
		// ensure only numbers are used for the patients mobile
		textFieldPatientMobile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				Methods.removeLetters(e);
			}
		});
		PatientInfoPanel.add(textFieldPatientMobile, "cell 2 2,growx");
		textFieldPatientMobile.setColumns(10);

		textFieldPatientPhone = new JTextField();
		// ensure only numbers are used for the patients mobile
		textFieldPatientPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Methods.removeLetters(e);
			}
		});
		PatientInfoPanel.add(textFieldPatientPhone, "cell 2 3,growx");
		textFieldPatientPhone.setColumns(10);
		dateChooserDOB.setBackground(new Color(204, 255, 204));

		PatientInfoPanel.add(dateChooserDOB, "cell 2 4,grow");

		JLabel lblNewLabel = new JLabel("Email  ");
		PatientInfoPanel.add(lblNewLabel, "cell 0 5");

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 255, 204));
		PatientInfoPanel.add(panel_2, "cell 2 5,grow");
		panel_2.setLayout(null);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(0, 0, 201, 28);
		panel_2.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblAddressLine = new JLabel("Address Line 1");
		PatientInfoPanel.add(lblAddressLine, "cell 0 8");

		textFieldAddressLine1 = new JTextField();
		PatientInfoPanel.add(textFieldAddressLine1, "flowx,cell 2 8,growx");
		textFieldAddressLine1.setColumns(10);

		JLabel lblAddressLine_1 = new JLabel("Address Line 2");
		PatientInfoPanel.add(lblAddressLine_1, "cell 0 9");

		textFieldAddressLine2 = new JTextField();
		PatientInfoPanel.add(textFieldAddressLine2, "flowx,cell 2 9,growx");
		textFieldAddressLine2.setColumns(10);

		JLabel lblPostcode = new JLabel("Postcode");
		PatientInfoPanel.add(lblPostcode, "cell 0 10");

		textFieldPostcode = new JTextField();
		PatientInfoPanel.add(textFieldPostcode, "flowx,cell 2 10,alignx left");
		textFieldPostcode.setColumns(10);

		JLabel lblBilling = new JLabel("Billing");
		lblBilling.setHorizontalAlignment(SwingConstants.TRAILING);
		PatientInfoPanel.add(lblBilling, "cell 0 11");

		JScrollPane scrollPane = new JScrollPane();
		PatientInfoPanel.add(scrollPane, "cell 2 11,grow");

		scrollPane.setViewportView(textAreaBilling);

		JLabel lblIllnesses = new JLabel("Illnesses");
		PatientInfoPanel.add(lblIllnesses, "cell 0 12");

		JScrollPane scrollPaneIllnesses = new JScrollPane();
		PatientInfoPanel.add(scrollPaneIllnesses, "cell 2 12,grow");

		scrollPaneIllnesses.setViewportView(textAreaIllnesses);

		JLabel lblAppointments = new JLabel("Next Appointment");
		PatientInfoPanel.add(lblAppointments, "cell 0 13");

		JPanel panel = new JPanel();
		PatientInfoPanel.add(panel, "flowx,cell 2 13");
		panel.setLayout(null);

		JLabel lblComments = new JLabel("Comments");
		PatientInfoPanel.add(lblComments, "cell 0 14");

		JScrollPane scrollPaneComments = new JScrollPane();
		PatientInfoPanel.add(scrollPaneComments, "cell 2 14,grow");

		scrollPaneComments.setViewportView(textAreaComments);

		JPanel panelAppointments = new JPanel();
		panelAppointments.setBackground(new Color(204, 255, 204));
		PatientInfoPanel.add(panelAppointments, "cell 2 13,grow");
		panelAppointments.setLayout(null);

		textFieldHour = new JTextField();
		textFieldHour.addKeyListener(new KeyAdapter() {
			@Override
			// ensure only numbers are used for the patients mobile
			public void keyTyped(KeyEvent e) {
				Methods.removeLetters(e);
			}
		});
		textFieldHour.setBounds(0, 0, 28, 28);
		panelAppointments.add(textFieldHour);
		textFieldHour.setColumns(10);

		JLabel label_2 = new JLabel(":");
		label_2.setBounds(26, 6, 10, 16);
		panelAppointments.add(label_2);

		textFieldMinutes = new JTextField();
		textFieldMinutes.setBounds(28, 0, 28, 28);
		panelAppointments.add(textFieldMinutes);
		textFieldMinutes.setColumns(10);

		JLabel label_5 = new JLabel("-");
		label_5.setBounds(54, 6, 10, 16);
		panelAppointments.add(label_5);
		dateChooserApp.getCalendarButton().setBackground(new Color(153, 255, 204));
		dateChooserApp.setBackground(new Color(204, 255, 204));

		dateChooserApp.setBounds(68, 0, 135, 28);
		panelAppointments.add(dateChooserApp);

		JPanel panelEmergencyContact = new JPanel();
		panelEmergencyContact.setBackground(new Color(204, 255, 204));
		panelEmergencyContact.setBounds(615, 76, 349, 105);
		add(panelEmergencyContact);
		panelEmergencyContact.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblEmergencyContact = new JLabel("Emergency Contact");
		panelEmergencyContact.add(lblEmergencyContact, "2, 2, left, default");

		textFieldEmergencyContact = new JTextField();
		panelEmergencyContact.add(textFieldEmergencyContact, "4, 2, fill, default");
		textFieldEmergencyContact.setColumns(10);

		JLabel lblRelationshipToPatient = new JLabel("Relationship to Patient");
		panelEmergencyContact.add(lblRelationshipToPatient, "2, 4, right, default");

		textFieldrelationshipToPatient = new JTextField();
		panelEmergencyContact.add(textFieldrelationshipToPatient, "4, 4, fill, default");
		textFieldrelationshipToPatient.setColumns(10);

		JLabel lblContactNumber = new JLabel("Contact Number");
		panelEmergencyContact.add(lblContactNumber, "2, 6, left, default");

		textFieldContactNumber = new JTextField();
		// ensure only numbers are used for the contact number of the Emergency
		// contact
		textFieldContactNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Methods.removeLetters(e);

			}
		});
		panelEmergencyContact.add(textFieldContactNumber, "4, 6, fill, default");
		textFieldContactNumber.setColumns(10);

		JPanel panelImages = new JPanel();
		panelImages.setBackground(new Color(204, 255, 204));
		panelImages.setBounds(615, 193, 349, 284);
		add(panelImages);
		panelImages.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 337, 232);
		panelImages.add(tabbedPane);
		panelPatientPhoto.setBackground(new Color(255, 255, 255));
		tabbedPane.add("Patient Image", panelPatientPhoto);
		panelPatientPhoto.setLayout(null);

		JLabel lblPantienPhoto = new JLabel("");
		lblPantienPhoto.setBounds(6, 6, 304, 174);
		panelPatientPhoto.add(lblPantienPhoto);
		panelIllnessPhotos.setBackground(new Color(204, 255, 204));

		tabbedPane.add("Scans/Photos", panelIllnessPhotos);
		panelIllnessPhotos.setLayout(null);

		JButton btnImage1 = new JButton("Image 1");
		btnImage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When the button is pressed initialise a new frame from the
				// ImageViewer class and pass
				// the argument of the patient id
				ImageViewer IV = new ImageViewer(MainJFrame.APP.lblGeneratedPatientID);
				IV.setVisible(true);
				// When the button is pressed change the static variable i in
				// the Image viewer class to 1
				ImageViewer.i = 1;
				Methods.updateViewer(ImageViewer.i);
			}
		});
		btnImage1.setBounds(99, 26, 117, 29);
		panelIllnessPhotos.add(btnImage1);

		JButton btnImage2 = new JButton("Image 2");
		btnImage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When the button is pressed initialise a new frame from the
				// ImageViewer class and pass
				// the argument of the patient id
				ImageViewer IV = new ImageViewer(MainJFrame.APP.lblGeneratedPatientID);
				IV.setVisible(true);
				// When the button is pressed change the static variable i in
				// the Image viewer class to 2
				ImageViewer.i = 2;
				Methods.updateViewer(ImageViewer.i);
			}
		});
		btnImage2.setBounds(99, 67, 117, 29);
		panelIllnessPhotos.add(btnImage2);

		JButton btnImage3 = new JButton("Image 3");
		btnImage3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When the button is pressed initialise a new frame from the
				// ImageViewer class and pass
				// the argument of the patient id
				ImageViewer IV = new ImageViewer(MainJFrame.APP.lblGeneratedPatientID);
				IV.setVisible(true);
				// When the button is pressed change the static variable i in
				// the Image viewer class to 3
				ImageViewer.i = 3;
				Methods.updateViewer(ImageViewer.i);
			}
		});
		btnImage3.setBounds(99, 108, 117, 29);
		panelIllnessPhotos.add(btnImage3);

		JButton btnImage4 = new JButton("Image 4");
		btnImage4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When the button is pressed initialise a new frame from the
				// ImageViewer class and pass
				// the argument of the patient id
				ImageViewer IV = new ImageViewer(MainJFrame.APP.lblGeneratedPatientID);
				IV.setVisible(true);
				// When the button is pressed change the static variable i in
				// the Image viewer class to 4
				ImageViewer.i = 4;
				Methods.updateViewer(ImageViewer.i);
			}
		});
		btnImage4.setBounds(99, 149, 117, 29);
		panelIllnessPhotos.add(btnImage4);
		JButton btnUploadImage = new JButton("Upload/Update Image");
		btnUploadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when the button is pressed ensure the user wants to save the
				// image and after confirmation go ahead and save it
				chooseImage.showOpenDialog(null);
				int dialogueResult = JOptionPane.showConfirmDialog(btnUploadImage,
						"Are you sure you want to save this patients with the details entered?");
				if (dialogueResult == JOptionPane.YES_OPTION) {
					Methods.saveImageAPP();
				}
			}
		});
		btnUploadImage.setBounds(6, 249, 223, 29);
		panelImages.add(btnUploadImage);

		JButton btnSavePatient = new JButton("Save Patient");
		btnSavePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the dates from both JCalendar fields
				Object dob = dateChooserDOB.getDate();
				Object app = dateChooserApp.getDate();
				// check if the required fields are empty, if so open dialogue
				// window and colour fields yellow
				if (textFieldName.getText().isEmpty() || textFieldSurname.getText().isEmpty()
						|| textFieldPatientMobile.getText().isEmpty() || textFieldEmergencyContact.getText().isEmpty()
						|| textFieldContactNumber.getText().isEmpty()
						|| textFieldrelationshipToPatient.getText().isEmpty() || dob == null || app == null) {
					JOptionPane.showMessageDialog(null,
							"Please complete all the mandatory fields \n before you save a patient!");

					textFieldName.setBackground(Color.yellow);
					textFieldSurname.setBackground(Color.yellow);
					textFieldPatientMobile.setBackground(Color.YELLOW);
					textFieldEmergencyContact.setBackground(Color.YELLOW);
					textFieldContactNumber.setBackground(Color.YELLOW);
					textFieldrelationshipToPatient.setBackground(Color.yellow);
					dateChooserDOB.setBackground(Color.yellow);
					dateChooserApp.setBackground(Color.yellow);
					// next check if the DOB and the Next Appointment dates are
					// valid.
				} else if (dateChooserDOB.getDate().after(Calendar.getInstance().getTime())
						|| dateChooserApp.getDate().before(Calendar.getInstance().getTime())) {
					JOptionPane.showMessageDialog(null,
							"Make sure the dates you entered for DOB \n and next Appointment are valid!");
				} else {
					// if all the required fields are complete we can now save
					// the patient

					// first check if the doctor wants to actually save the
					// patients details. If so,
					// get the data from the fields and write them into the CSV
					// file.
					int dialogueResult = JOptionPane.showConfirmDialog(btnSavePatient,
							"Are you sure you want to save this patients with the details entered?");
					if (dialogueResult == JOptionPane.YES_OPTION) {

						try {

							// declare patients.CSV as a file and assign a
							// writer and buffered writer for it
							// ensure you replace illegal characters such as
							// commas
							File patients = new File("patients.csv");

							FileWriter FW = new FileWriter(patients, true);
							BufferedWriter writer = new BufferedWriter(FW);

							writer.append(lblGeneratedPatientID.getText()); // 0
							writer.append("," + textFieldName.getText().replace(",", " ")); // 1
							writer.append("," + textFieldSurname.getText().replace(",", " "));// 2
							// Format date into a string that can be later read
							// and returned as a date
							Format formatter = new SimpleDateFormat("dd-MM-yyyy");
							String DOB = formatter.format(dateChooserDOB.getDate());
							writer.append("," + DOB);// 3
							writer.append("," + genders[comboBoxGender.getSelectedIndex()]); // 4
							writer.append("," + textFieldPatientMobile.getText().replace(",", " "));// 5
							writer.append("," + textFieldPatientPhone.getText().replace(",", " "));// 6
							writer.append("," + textFieldEmail.getText().replace(",", " "));// 7
							writer.append("," + textFieldAddressLine1.getText().replace(",", " "));// 8
							writer.append("," + textFieldAddressLine2.getText().replace(",", " "));// 9
							writer.append("," + textFieldPostcode.getText().replace(",", " "));// 10
							writer.append("," + textFieldEmergencyContact.getText().replace(",", " "));// 11
							writer.append("," + textFieldrelationshipToPatient.getText().replace(",", " "));// 12
							writer.append("," + textFieldContactNumber.getText().replace(",", " "));// 13

							writer.append("," + textAreaBilling.getText().replace("\n", " ").replace(",", " "));// 14
							writer.append("," + textAreaIllnesses.getText().replace("\n", " ").replace(",", " "));// 15
							writer.append("," + textAreaComments.getText().replace("\n", " ").replace(",", " "));// 16
							Format formatter2 = new SimpleDateFormat("dd-MM-yyyy");
							String LatestAppointment = formatter2.format(dateChooserApp.getDate());
							writer.append("," + textFieldHour.getText().replace(",", " "));// 17
							writer.append("," + textFieldMinutes.getText().replace(",", " "));// 18
							writer.append("," + LatestAppointment);// 19
							writer.append("\n");
							writer.close();

							Methods.incrementCounter();
							Methods.CSVtoTable();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		btnSavePatient.setBounds(847, 519, 117, 29);
		add(btnSavePatient);

		JButton btnBackToHome = new JButton("Back to Home");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// when the return to home button is pressed ensure the
					// table on the home page is up to date based on the updated
					// CSV
					BufferedReader patientsReader = new BufferedReader(new FileReader("patients.csv"));
					// firstly reset the table
					MainJFrame.HP.model.setRowCount(0);
					// also reset the arraylist to ensure no duplicates
					MainJFrame.HP.database.removeAll(MainJFrame.HP.database);
					// iterate through the CSV and re-write the arraylist and
					// repopulate the table
					while (patientsReader.ready()) {
						String line = patientsReader.readLine();
						MainJFrame.HP.patientRow = line.split(",");
						MainJFrame.HP.database.add(MainJFrame.HP.patientRow);
						MainJFrame.HP.model.addRow(MainJFrame.HP.patientRow);

					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// ensure the GUI is reset after you return to the home page
				Methods.emptyFieldsAPP();
				Methods.whiteBackgroundAPP();
				// hide the Add patient panel and show the home page
				MainJFrame.LP.setVisible(false);
				MainJFrame.APP.setVisible(false);
				MainJFrame.VPP.setVisible(false);
				MainJFrame.HP.setVisible(true);
			}
		});
		btnBackToHome.setBounds(615, 519, 117, 29);
		add(btnBackToHome);

		JLabel lblAddPatient = new JLabel("Add Patient");
		lblAddPatient.setForeground(new Color(0, 51, 255));
		lblAddPatient.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAddPatient.setFont(new Font("Lucida Grande", Font.PLAIN, 33));
		lblAddPatient.setBounds(699, 18, 254, 39);
		add(lblAddPatient);

	}
}
