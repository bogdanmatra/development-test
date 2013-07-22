package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import library.Librarian;
import library.LibrarySingleton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private LibrarySingleton library;



	/**
	 * Create the frame.
	 */
	public SignIn(LibrarySingleton lib) {
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
		
		fieldUsername = new JTextField();
		fieldUsername.setBounds(199, 43, 166, 20);
		contentPane.add(fieldUsername);
		fieldUsername.setColumns(10);
		
		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(199, 111, 166, 20);
		contentPane.add(fieldPassword);
		this.setResizable(false);
		this.setVisible(true);
		
		library=lib;
		
		
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String user=fieldUsername.getText();
				String pass=new String(fieldPassword.getPassword());
				Librarian currentLibrarian;
				if((currentLibrarian=library.credentialsCheck(user, pass))!=null){
					new ClientsGUI(library, currentLibrarian );
					SignIn.this.dispose();
				}else{
					JOptionPane.showMessageDialog(SignIn.this,"Invalid credentials, try again!","Sorry!",JOptionPane.ERROR_MESSAGE );				}
				
			}
		});
	}
}
