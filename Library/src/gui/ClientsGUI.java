package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;

import library.Client;
import library.Librarian;
import library.LibrarySingleton;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ClientsGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private LibrarySingleton library;


	/**
	 * Create the frame.
	 */
	public ClientsGUI(LibrarySingleton lib, Librarian signedInLib) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bAdd = new JButton("Add Client");
		bAdd.setBounds(308, 90, 114, 23);
		contentPane.add(bAdd);
		
		JButton btnNewButton_1 = new JButton("Delete Client");
		btnNewButton_1.setBounds(308, 159, 114, 23);
		contentPane.add(btnNewButton_1);
		
		JButton bSee = new JButton("See Books");
		bSee.setBounds(308, 230, 114, 23);
		contentPane.add(bSee);
		
		JLabel labelHello = new JLabel("");
		labelHello.setBounds(308, 36, 146, 14);
		contentPane.add(labelHello);
		labelHello.setText("Hello, " + signedInLib.getName() + "!");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 262, 323);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
		this.setResizable(false);
		this.setVisible(true);
		
		library=lib;
		
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Phone Number");
		
		for (Client client: library.getLibraryMap().keySet()){	
			String[] row={ client.getFirstName(), client.getLastName(), client.getPhoneNumber() };
			tableModel.addRow(row);
		}
		
		table.setModel(tableModel);
				
	}
}
