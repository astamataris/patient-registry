package JFrames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * The Methods interface serves a refactoring purpose where a number of classes were created in order to be used by 
 * other classes repeatedly in this same package.
 * @author Andreas
 *
 */
 interface Methods {
	 
	 /**
	  * This method reads the CSV file (patients.CSV), clears the database of patients and repopulates the database as well as the 
	  * table based on the CSV file.
	  */
	 static void CSVtoTable() {
		try {
			// instantiate a reader for the CSV file
			BufferedReader patientsReader = new BufferedReader(new FileReader("patients.csv"));
			// empty the table to avoid duplicates
			MainJFrame.HP.database.removeAll(MainJFrame.HP.database);
			// read the CSV file and and add it to the database first and then
			// to the table
			while (patientsReader.ready()) {
				String line = patientsReader.readLine();
				MainJFrame.HP.patientRow = line.split(",");
				MainJFrame.HP.database.add(MainJFrame.HP.patientRow);
				MainJFrame.HP.model.addRow(MainJFrame.HP.patientRow);

			}
			patientsReader.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	 /**
	  * This method iterates through the database and rewrites the CSV file(patients.csv) based on the data in the database. 
	  */
	 static void databaseToCSV() {
		//pass the patients CSV into a file variable
		File patients = new File("patients.csv");
		//iterate though the database and append the CSV file using a buffered writer and putting commas between each element
		FileWriter FW;
		try {
			FW = new FileWriter(patients);
			BufferedWriter writer = new BufferedWriter(FW);
			for (int j = 0; j < MainJFrame.HP.database.size(); j++) {

				writer.append((CharSequence) MainJFrame.HP.database.get(j)[0]); // 0
				writer.append("," + MainJFrame.HP.database.get(j)[1]);// 1
				writer.append("," + MainJFrame.HP.database.get(j)[2]);// 2
				writer.append("," + MainJFrame.HP.database.get(j)[3]);// 3
				writer.append("," + MainJFrame.HP.database.get(j)[4]); // 4
				writer.append("," + MainJFrame.HP.database.get(j)[5]);// 5
				writer.append("," + MainJFrame.HP.database.get(j)[6]);// 6
				writer.append("," + MainJFrame.HP.database.get(j)[7]);// 7
				writer.append("," + MainJFrame.HP.database.get(j)[8]);// 8
				writer.append("," + MainJFrame.HP.database.get(j)[9]);// 9
				writer.append("," + MainJFrame.HP.database.get(j)[10]);// 10
				writer.append("," + MainJFrame.HP.database.get(j)[11]);// 11
				writer.append("," + MainJFrame.HP.database.get(j)[12]);// 12
				writer.append("," + MainJFrame.HP.database.get(j)[13]);// 13

				writer.append("," + MainJFrame.HP.database.get(j)[14]);// 14
				writer.append("," + MainJFrame.HP.database.get(j)[15]);// 15
				writer.append("," + MainJFrame.HP.database.get(j)[16]);// 16

				writer.append("," + MainJFrame.HP.database.get(j)[17]);// 17
				writer.append("," + MainJFrame.HP.database.get(j)[18]);// 18
				writer.append("," + MainJFrame.HP.database.get(j)[19]);// 19
				writer.append("\n");

			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	 /**
	  * This method iterates through the database and removes the row or the rows that match the selected 
	  * patient id.
	  */
	 static void removeSelectedRow() {
		//assign the the patientId to a string
		String id = MainJFrame.APP.lblGeneratedPatientID.getText();
		//itterate though the database and remove the row that matches the patient ID selected 
		for (int i = 0; i < MainJFrame.HP.database.size(); i++) {
			if (id.equals(MainJFrame.HP.database.get(i)[0].toString())) {
				MainJFrame.HP.database.remove(i);
			}
		}
	}
/**
 * This method saves the selected image as the new profile picture of the patient and names it based on the patient ID. This method can only be 
 * used in the View Patient Panel
 */
	 static void saveImageVPP() {
		//read the image selected and pass it to variable img
		//this method only works with the View patient panel
		try {
			RenderedImage img = ImageIO.read(MainJFrame.VPP.chooseImage.getSelectedFile());
			//save the new image renaming it according to patientID 
			ImageIO.write(img, "png", new File(MainJFrame.VPP.lblGeneratedPatientID.getText() + ".png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	 /**
	  * This method saves the selected image as the new profile picture of the patient and names it based on the patient ID. This method can only be 
	  * used in the Add Patient Panel
	  */
	 static void saveImageAPP() {
		//read the image selected and pass it to variable img
				//this method only works with the Add patient panel
		try {
			RenderedImage img = ImageIO.read(MainJFrame.APP.chooseImage.getSelectedFile());
			//save the new image renaming it according to patientID 
			ImageIO.write(img, "png", new File(MainJFrame.APP.lblGeneratedPatientID.getText() + ".png"));

		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

	}

/**
 * This method uses the patient ID to save the patient images and scans adding a post fix e.g: 1_1 or 1_2 etc.
 * @param a JButton: allows the method to be used by different Jbuttons
 * @param b JLabel: allows the method to be used by different JLabels
 */
	//pass a button and a label as an argument
	 static void changeImage(JButton a, JLabel b) {
		JFileChooser imagechoosen = new JFileChooser();
		imagechoosen.showOpenDialog(null);
		//ensure the user wants to upload this image
		int dialogueResult = JOptionPane.showConfirmDialog(a, "Are you sure you want to upload this image?");
		if (dialogueResult == JOptionPane.YES_OPTION) {
			//pass the chosen image to a file
			File image = imagechoosen.getSelectedFile();

			try {
				//pass the file selected as an image
				Image ImageToUpload = ImageIO.read(image);
				//pass the image into an ImageIcon variable to be assigned to a Jlabel
				ImageIcon ii = new ImageIcon(ImageToUpload);
				ImageViewer.scrollPane.setViewportView(new JLabel(ii));
				try {
					//after the image is displayed, save it using the patients id as well as the appropriate postfix
					RenderedImage img = ImageIO.read(imagechoosen.getSelectedFile());

					ImageIO.write(img, "png", new File(b.getText() + "_" + ImageViewer.i.toString() + ".png"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
/**
 * This method ensures the image viewer window displays the current image in the scroll pane
 * @param a Parameter a allows the method to rename the image according to the slot selected e.g. 1-4 
 */
	 static void updateViewer(Integer a) {
		try {
			int row = MainJFrame.HP.table.getSelectedRow();
			// patient photos are saved using the patient ID so use the patient
			String patientPhoto = MainJFrame.HP.database.get(row)[0].toString() + "_" + a.toString() + ".png";
			Image prof = ImageIO.read(new File(patientPhoto));

			ImageIcon ii = new ImageIcon(prof);
			ImageViewer.scrollPane.setViewportView(new JLabel(ii));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
/**
 * This method deletes the patient's profile photo utilising his/her patientID to form the photo's name
 */
	 static void deleteProfilePhoto() {
		int row = MainJFrame.HP.table.getSelectedRow();
		String patientPhoto = MainJFrame.HP.database.get(row)[0].toString() + ".png";
		File profilePic = new File(patientPhoto);
		profilePic.delete();
		//after the picture is deleted set the scroll pane to empty
		MainJFrame.VPP.lblPatientPhoto.setIcon(null);

	}
/** This method deletes the image currently viewed in the Image viewer JFrame
 * @param a Parameter a allows the method to remove the image according to the slot selected e.g. 1-4 
 */
	 static void deleteCurrentImage(Integer a) {
		int row = MainJFrame.HP.table.getSelectedRow();
		String currentPhoto = MainJFrame.HP.database.get(row)[0].toString() + "_" + a.toString() + ".png";
		File currentPic = new File(currentPhoto);
		currentPic.delete();
	}
/**
 * This method ensures the fields of the Add Patient panel are empty to avoid confusion when adding new patients
 */
	 static void emptyFieldsAPP() {
		MainJFrame.APP.textAreaBilling.setText(null);
		MainJFrame.APP.textFieldAddressLine1.setText(null);

		MainJFrame.APP.textFieldName.setText(null); // 1
		MainJFrame.APP.textFieldSurname.setText(null);// 2

		MainJFrame.APP.textFieldPatientMobile.setText(null);// 5
		MainJFrame.APP.textFieldPatientPhone.setText(null);// 6
		MainJFrame.APP.textFieldEmail.setText(null);// 7
		MainJFrame.APP.textFieldAddressLine1.setText(null);// 8
		MainJFrame.APP.textFieldAddressLine2.setText(null);// 9
		MainJFrame.APP.textFieldPostcode.setText(null);// 10
		MainJFrame.APP.textFieldEmergencyContact.setText(null);// 11
		MainJFrame.APP.textFieldrelationshipToPatient.setText(null);// 12
		MainJFrame.APP.textFieldContactNumber.setText(null);// 13

		MainJFrame.APP.textAreaBilling.setText(null);// 14
		MainJFrame.APP.textAreaIllnesses.setText(null);// 15
		MainJFrame.APP.textAreaComments.setText(null);// 16

		MainJFrame.APP.textFieldHour.setText(null);// 17
		MainJFrame.APP.textFieldMinutes.setText(null);// 18

	}
	 /**
	 * This method ensures the fields of the View Patient panel are empty to avoid confusion when editing new patients.
	 */
	 
	 static void emptyFieldsVPP() {
		MainJFrame.VPP.textAreaBilling.setText(null);
		MainJFrame.VPP.textFieldAddressLine1.setText(null);

		MainJFrame.VPP.textFieldName.setText(null); // 1
		MainJFrame.VPP.textFieldSurname.setText(null);// 2

		MainJFrame.VPP.textFieldPatientMobile.setText(null);// 5
		MainJFrame.VPP.textFieldPatientPhone.setText(null);// 6
		MainJFrame.VPP.textFieldEmail.setText(null);// 7
		MainJFrame.VPP.textFieldAddressLine1.setText(null);// 8
		MainJFrame.VPP.textFieldAddressLine2.setText(null);// 9
		MainJFrame.VPP.textFieldPostcode.setText(null);// 10
		MainJFrame.VPP.textFieldEmergencyContact.setText(null);// 11
		MainJFrame.VPP.textFieldrelationshipToPatient.setText(null);// 12
		MainJFrame.VPP.textFieldContactNumber.setText(null);// 13

		MainJFrame.VPP.textAreaBilling.setText(null);// 14
		MainJFrame.VPP.textAreaIllnesses.setText(null);// 15
		MainJFrame.VPP.textAreaComments.setText(null);// 16

		MainJFrame.VPP.textFieldHour.setText(null);// 17
		MainJFrame.VPP.textFieldMinutes.setText(null);// 18

	}
/**
 * This method resizes and displays the picture chosen in the patient profile picture panel using the patient ID
 */
	 static void updateProfilePicVPP() {

		try {
			int row = MainJFrame.HP.table.getSelectedRow();
			// patient photos are saved using the patient ID so use the patient
			
			String patientPhoto = MainJFrame.HP.database.get(row)[0].toString() + ".png";
			//read the photo using the patient id name
			Image profilePi = ImageIO.read(new File(patientPhoto));
			//resize the image in a new image file and assign it to an icon to allow it to be used as an icon
			Image profilePic = profilePi.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(profilePic);

			MainJFrame.VPP.lblPatientPhoto.setIcon(icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	 //static void checkTextField(JTextField a) {
//		if (a.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(null,
//					"Please complete this field \n before you move on \n to the next field");
//		}
//	}
/**
 * This method ensures the user can only input numbers and is mainly used for fields that include phone numbers
 * @param e allows to validate each key pressed
 */
	 static void removeLetters(KeyEvent e) {
		//get the characters pressed and if they are not between 0-9 the character is consumed, not allowing the user to use letters
		//in that text field
		char caracter = e.getKeyChar(); // http://stackoverflow.com/questions/18084104/accept-only-numbers-and-a-dot-in-java-textfield
		if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
			e.consume();
		}

	}
/**
 * restore the highlighted fields into their regular colour. Only usable in the Add Patient panel
 */
	 static void whiteBackgroundAPP() {
		//set all coloured fields back to white
		MainJFrame.APP.textFieldName.setBackground(Color.white);
		MainJFrame.APP.textFieldSurname.setBackground(Color.white);
		MainJFrame.APP.textFieldPatientMobile.setBackground(Color.white);
		MainJFrame.APP.textFieldEmergencyContact.setBackground(Color.white);
		MainJFrame.APP.textFieldContactNumber.setBackground(Color.white);
		MainJFrame.APP.textFieldrelationshipToPatient.setBackground(Color.white);
		MainJFrame.APP.dateChooserDOB.setBackground(MainJFrame.APP.panelIllnessPhotos.getBackground());
		MainJFrame.APP.dateChooserApp.setBackground(MainJFrame.APP.panelIllnessPhotos.getBackground());

	}
	 /**
	  * restore the highlighted fields into their regular colour. Only usable in the View Patient panel
	  */
	 static void whiteBackgroundVPP() {
		//set all coloured fields back to white
		MainJFrame.VPP.textFieldName.setBackground(Color.white);
		MainJFrame.VPP.textFieldSurname.setBackground(Color.white);
		MainJFrame.VPP.textFieldPatientMobile.setBackground(Color.white);
		MainJFrame.VPP.textFieldEmergencyContact.setBackground(Color.white);
		MainJFrame.VPP.textFieldContactNumber.setBackground(Color.white);
		MainJFrame.VPP.textFieldrelationshipToPatient.setBackground(Color.white);
		MainJFrame.VPP.dateChooserDOB.setBackground(MainJFrame.VPP.panelIllnessPhotos.getBackground());
		MainJFrame.VPP.dateChooserApp.setBackground(MainJFrame.VPP.panelIllnessPhotos.getBackground());

	}
/**
 * This method increments the counted everytime a patient is succesfully saved. This is done by reading the current
 * value in the counter file , incrementing it and then overwriting the counter file with a new counter file 
 */
	 static void incrementCounter() {

		try {
			File counter = new File("counter.txt");
			FileReader read;
			read = new FileReader(counter);
			BufferedReader buffRead = new BufferedReader(read);
			// Assign the value read to an Integer
			Integer i = Integer.parseInt(buffRead.readLine());
			// increment the read value
			i++;
			// overwrite the counter file with the new counter value
			File counters = new File("counter.txt");
			FileWriter write = new FileWriter(counters);
			write.write(i.toString());
			write.close();
			read.close();
			buffRead.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
