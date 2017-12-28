package mcq;

import java.awt.EventQueue;
import java.awt.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.BorderLayout;

public class Output {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void outprinter() {
	//public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Output window = new Output();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Output() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 16));
		frame.setBounds(100, 100, 1284, 817);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String text = new String(Files.readAllBytes(Paths.get("mcq.txt")), StandardCharsets.UTF_8);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(scrollPane);
		
		JTextArea txtrQstn = new JTextArea();
		txtrQstn.setFont(new Font("Monospaced", Font.PLAIN, 16));
		scrollPane.setViewportView(txtrQstn);
		txtrQstn.setText(text);
	}

}
