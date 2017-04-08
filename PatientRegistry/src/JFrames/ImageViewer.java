package JFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SpringLayout;

public class ImageViewer extends JFrame {

	JPanel contentPane;
	JFileChooser imagechoosen = new JFileChooser();
	static JScrollPane scrollPane = new JScrollPane();
	static Integer i;

	/**
	 * Launch the application and call the constructor for the new JFrame that
	 * will allow doctors to view their images, pixel by pixel though the
	 * scrollable panel they display on
	 * 
	 * @param a
	 *            Requires an input of a JLabel which provides the class with
	 *            the patient number the images are going to be handled for
	 */
	public static void main(JLabel a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageViewer frame = new ImageViewer(a);
					frame.getContentPane().setLayout(new BorderLayout());
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * the constructor of the ImageViewer builds a Jframe with a large
	 * scrollable panel allowing users to view their images pixel by pixel and
	 * allows users to save as well as delete images based on the patient id of
	 * the patient currently viewed.
	 * 
	 * @param a
	 *            Takes a JLabel as input which provides the class with the
	 *            patient number the images are going to be handled for
	 */
	ImageViewer(JLabel a) {
		setResizable(false);
		setBackground(new Color(0, 102, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 513);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(6, 6, 599, 429);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnDeleteImage = new JButton("Delete Image");
		btnDeleteImage.setBounds(51, 452, 117, 29);
		btnDeleteImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogueResult = JOptionPane.showConfirmDialog(btnDeleteImage,
						"Are you sure you want to delete this image?");
				if (dialogueResult == JOptionPane.YES_OPTION) {
					Methods.deleteCurrentImage(i);
					scrollPane.setViewportView(null);
				}
			}
		});
		contentPane.add(btnDeleteImage);

		JButton btnUpdateImage = new JButton("Update Image");
		btnUpdateImage.setBounds(185, 452, 117, 29);
		btnUpdateImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Methods.changeImage(btnUpdateImage, a);

			}
		});
		contentPane.add(btnUpdateImage);

		JButton btnUploadImage = new JButton("Upload Image");
		btnUploadImage.setBounds(314, 452, 117, 29);
		btnUploadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Methods.changeImage(btnUploadImage, a);

			}
		});
		contentPane.add(btnUploadImage);

		JButton btnConfirmAndClose = new JButton("Close");
		btnConfirmAndClose.setBounds(443, 452, 117, 29);
		btnConfirmAndClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

			}
		});
		contentPane.add(btnConfirmAndClose);
	}
}
