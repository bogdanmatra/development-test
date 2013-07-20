package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField textFUser;
	private JTextField textFPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(178, 201, 89, 23);
		contentPane.add(btnSignIn);
		
		JLabel bUsername = new JLabel("Username:");
		bUsername.setBounds(82, 46, 83, 14);
		contentPane.add(bUsername);
		
		JLabel bPassword = new JLabel("Password:");
		bPassword.setBounds(82, 114, 83, 14);
		contentPane.add(bPassword);
		
		textFUser = new JTextField();
		textFUser.setBounds(199, 43, 166, 20);
		contentPane.add(textFUser);
		textFUser.setColumns(10);
		
		textFPass = new JTextField();
		textFPass.setBounds(199, 108, 166, 20);
		contentPane.add(textFPass);
		textFPass.setColumns(10);
		this.setResizable(false);
	}
}
