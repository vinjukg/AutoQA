package mcq;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollBar;

public class gui {

	private JFrame frame;
	private JFileChooser textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1193, 736);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton rdbtnNewRadioButton = new JButton("Generate");
		textField = new JFileChooser();
		textField.setBounds(136, 160, 622, 295);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				test t=new test();
				File location=textField.getSelectedFile();
				try {
					t.testingMethod(location.getName());
				} catch (OWLOntologyCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OWLOntologyStorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("helooooooooo");
			}
		});
		rdbtnNewRadioButton.setBounds(921, 309, 127, 25);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JLabel lblMcqGenerator = new JLabel("MCQ Generator");
		lblMcqGenerator.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblMcqGenerator.setBounds(442, 54, 404, 51);
		frame.getContentPane().add(lblMcqGenerator);
		

		frame.getContentPane().add(textField);
		
	}
}
