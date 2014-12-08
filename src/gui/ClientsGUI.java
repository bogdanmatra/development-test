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

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

@SuppressWarnings("serial")
public class ClientsGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private LibrarySingleton library;
	private Librarian currentLibrarian;
	private JTextField fieldFName;
	private JTextField fieldLName;
	private JTextField fieldPhone;


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
		// controller for adding a client
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// verifying if user inserts data
				if ((!fieldFName.getText().equals("")) && (!fieldLName.getText().equals("")) && (!fieldPhone.getText().equals(""))) {
					//creating a new client and adding him to the map keys
					Client c =new Client(fieldFName.getText(), fieldLName.getText(), fieldPhone.getText());
					library.getLibraryMap().put(c, null);
					// refreshing table data
					ClientsGUI.this.setModelFromClients();
				}
				else{
				        // shown if the user tries to input invalid data
					JOptionPane.showMessageDialog(ClientsGUI.this, "Please enter valid data!","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		bAdd.setBounds(308, 243, 114, 23);
		contentPane.add(bAdd);
		
		JButton btnNewButton_1 = new JButton("Delete Client");
		// controller for deleting a client
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = table.getSelectedRow();
				if(selectedRowIndex==-1){
				    	// if the user does not select a row the method will exit and shows a message
					JOptionPane.showMessageDialog(ClientsGUI.this, "Please select a row!","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				// gets data from table
				String fName = (String) table.getModel().getValueAt(selectedRowIndex, 0);
				String lName = (String) table.getModel().getValueAt(selectedRowIndex, 1);
				String phone = (String) table.getModel().getValueAt(selectedRowIndex, 2);
	
					for (Client client: library.getLibraryMap().keySet()){	
						// searching for the client in the map keys 
						if (client.getFirstName().equals(fName) &&
							client.getLastName().equals(lName) &&
							client.getPhoneNumber().equals(phone) ) {
							library.getLibraryMap().remove(client);
							break;	 // exits if the client is found and deleted
						}
						
					}
					ClientsGUI.this.setModelFromClients();	// refreshes table
			}
				
				
			
		});
		btnNewButton_1.setBounds(308, 277, 114, 23);
		contentPane.add(btnNewButton_1);
		
		JButton bSee = new JButton("See Books");
		// controller for seeing a client's books
		bSee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selectedRowIndex = table.getSelectedRow();
				if(selectedRowIndex==-1){
					JOptionPane.showMessageDialog(ClientsGUI.this, "Please select a row!","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String fName = (String) table.getModel().getValueAt(selectedRowIndex, 0);
				String lName = (String) table.getModel().getValueAt(selectedRowIndex, 1);
				String phone = (String) table.getModel().getValueAt(selectedRowIndex, 2);
				
					for (Client client: library.getLibraryMap().keySet()){	
						// finding the client in the map keys
						if (client.getFirstName().equals(fName) &&
							client.getLastName().equals(lName) &&
							client.getPhoneNumber().equals(phone) ) {
							new BooksGUI(library, client,currentLibrarian);
							break;	// client found and the window with his books opens
						}
						
					}
				ClientsGUI.this.dispose(); // deleting the clients window
				
				
			}
		});
		bSee.setBounds(308, 311, 114, 23);
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
		
		fieldFName = new JTextField();
		fieldFName.setBounds(308, 86, 114, 20);
		contentPane.add(fieldFName);
		fieldFName.setColumns(10);
		
		fieldLName = new JTextField();
		fieldLName.setBounds(308, 143, 114, 20);
		contentPane.add(fieldLName);
		fieldLName.setColumns(10);
		
		fieldPhone = new JTextField();
		fieldPhone.setBounds(308, 199, 114, 20);
		contentPane.add(fieldPhone);
		fieldPhone.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(308, 61, 114, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName_1 = new JLabel("Last Name:");
		lblLastName_1.setBounds(308, 118, 114, 14);
		contentPane.add(lblLastName_1);
		
		JLabel lblLastName = new JLabel("Phone Number:");
		lblLastName.setBounds(308, 174, 114, 14);
		contentPane.add(lblLastName);
		
		library=lib;
		currentLibrarian=signedInLib;
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JButton btnLoad = new JButton("Load");
        // controller for loading a file
        btnLoad.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        	    	JFileChooser chooser = new JFileChooser();
        	    	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        	    	chooser.showOpenDialog(ClientsGUI.this);
        	    	File file =chooser.getSelectedFile();
        	    	// checking if a file was chosen and if it has a '.ser' extention
        		if ( file==null || (checkExtention(file, "ser")==false) ){
        			JOptionPane.showMessageDialog(ClientsGUI.this, "Please select a '.ser' file!","Warning",JOptionPane.WARNING_MESSAGE);
        			return;
        		}
        		
        		library.deserializeMapToFile(file);
        		setModelFromClients(); // refreshing table data
        	}
        });
        btnLoad.setBounds(140, 337, 89, 23);
        contentPane.add(btnLoad);
        
        JButton btnSave = new JButton("Save");
        //controller for saving a file
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	    	JFileChooser chooser = new JFileChooser();
        	    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	    	chooser.showSaveDialog(ClientsGUI.this);
        	    	File file =chooser.getSelectedFile();
        	    	// checking if a directory has been selected
        	    	if(file==null){
        	    	JOptionPane.showMessageDialog(ClientsGUI.this, "Please select directory!","Warning",JOptionPane.WARNING_MESSAGE);
        	    	return;
        	    	}
        	    
        		library.serializeMapToFile(file);
        	}
        });
        btnSave.setBounds(20, 337, 89, 23);
        contentPane.add(btnSave);
        this.setModelFromClients();
  
		
				
	}
	
	// method sets table model from the set of clients
	private void setModelFromClients() {

		DefaultTableModel tableModel=new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		//adding columns
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Phone Number");
		
		for (Client client: library.getLibraryMap().keySet()){
		    	// adding data to the table from the set
			String[] row={ client.getFirstName(), client.getLastName(), client.getPhoneNumber() };
			tableModel.addRow(row);
		}
		
		table.setModel(tableModel);
		

	}
	
	
	
	// method for checking an extention for a file
	 private static boolean checkExtention(File file, String extention) {
		    String[] splitted =file.getName().split("\\.");
		    if (extention.equals(splitted[splitted.length-1])) return true;
		    else return false;
		}

}
