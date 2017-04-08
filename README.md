# patient-registry

This project was done as part of the COMPGC01 - Introductory Programming, Module during my MSc Computer Science. The purpose of the coursework was to assess our ability to demonstrate our programming knowledge, purely in Java. It did not require a fully completed application to get a successful mark. However, the more completed it is, the higher the marks would be. The project was completed using Java swing in the eclipse IDE.
___
## Requirements
You are required to design and implement a simple patient management system with the following minimum requirements:
1. The management system should be implemented as a java GUI application using frames, tables, buttons, listeners, etc.
2. The practitioner should be able to login to the patient management system using a username/password pair.
3. Register a new patient with minimal information such as name, first name, DOB, address, emergency phone number, medical condition, appointments, billing, comments, etc. Each record might contain a profile picture of the patient and a set of pictures describing his/her condition such as brain scans (please use any sample image, It doesn’t have to be realistic).
4. The field containing the medical condition data should be clickable and pointing to a wikipedia page or any URL with the description of that condition.
5. Edit an existing patient record, by editing existing information such as dates/times of appointments, uploading new images or deleting others.
6. Delete an exiting patient record. Your program should ask for confirmation before deleting a record.
7. Search the list of patients based on any of the stored fields, i.e., name, DOB, address, etc. and produce a list of corresponding records.
   1/4
 8. **(Optional)** Export a list of selected patient records as a comma separated file or any other format.
9. **(Optional)** Import a comma separated (or the format used in 8) file containing patients’ records into the system. The new patients data will be appended to the existing ones already stored in the management system.
Note: It is NOT mandatory to use a database to store patients’ data. You can store it in a text file.
___
## Requirements implemented
1. Implemented Java GUI application. The GUI consists of JFrames, Jtable, Jbuttons, a variety of action listeners,
2. The practitioner needs to login with the username and password "admin" and "pass" respectively.
3. Can only register a patient using the minimum mandatory information (Name, Surname, mobile phone, DOB, Emergency Contact, Emergency contact number
and next Appointment).
4. The practitioner is able to upload one profile picture and four images/scans.
5. The field containing medical condition can execute four simultaneous google searches for illnesses written, in the browser of the practitioner.
6. Can delete/update photos of patient or completely delete his records including the pictures. The user needs to provide confirmation in both cases
7. The search text box in the home page searches based on all stored fields and updates the table accordingly.
8. Click on a patient in the table to view/edit patients details.
9. (Optional) The user can export the database as a CSV file in his directory, and can also import a CSV file and append the current database. The CSV file needs to be
correctly formatted. 
___
### Additional features implemented:

1. JTabed Panes to switch between profile pictures and scans/images.
2. Field Validation: Ensure the user enters numbers in the phone number fields and the DOB and next appointments are valid i.e. DOB should be older than the current day and next appointment should be after the current date.
3. The look up illness button picks up up to four illnesses entered and opens them in separate tabs.
4. Highlight mandatory text fields.
5. Used an external library for my date pickers, JCalendar from (http://toedter.com/jcalendar/)
6. Coloured my panels to give a distinct look