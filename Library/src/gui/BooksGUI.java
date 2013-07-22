package gui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import books.Book;
import library.Client;
import library.Librarian;
import library.LibrarySingleton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class BooksGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private LibrarySingleton library;
	private List<Book> bookList;
	private Librarian currentLibrarian;


	/**
	 * Create the frame.
	 */
	public BooksGUI(LibrarySingleton lib, Client currentClient, Librarian librarian) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 192, 225);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.setBounds(335, 137, 109, 23);
		contentPane.add(btnAddBook);
		
		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selectedRowIndex = table.getSelectedRow();
				
				if(selectedRowIndex==-1){
					JOptionPane.showMessageDialog(BooksGUI.this, "Please select a row!","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				bookList.remove(selectedRowIndex);
				setModelBookList();
				
			}
		});
		btnDeleteBook.setBounds(335, 174, 109, 23);
		contentPane.add(btnDeleteBook);
		
		JLabel label = new JLabel("");
		label.setBounds(212, 0, 242, 14);
		contentPane.add(label);
		
		JRadioButton rdbtnPoetry = new JRadioButton("Poetry");
		rdbtnPoetry.setBounds(213, 137, 109, 23);
		contentPane.add(rdbtnPoetry);
		
		JRadioButton rdbtnSF = new JRadioButton("Science-Fiction");
		rdbtnSF.setBounds(213, 174, 109, 23);
		contentPane.add(rdbtnSF);
		
		JRadioButton rdbtnScience = new JRadioButton("Science");
		rdbtnScience.setBounds(213, 209, 109, 23);
		contentPane.add(rdbtnScience);
		
		JRadioButton rdbtnDrama = new JRadioButton("Drama");
		rdbtnDrama.setBounds(213, 100, 109, 23);
		contentPane.add(rdbtnDrama);
		
		ButtonGroup bGroup=new ButtonGroup();
		bGroup.add(rdbtnDrama);
		bGroup.add(rdbtnPoetry);
		bGroup.add(rdbtnScience);
		bGroup.add(rdbtnSF);
		
		
		textField = new JTextField();
		textField.setBounds(335, 28, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(335, 59, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(256, 31, 46, 14);
		contentPane.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(256, 62, 46, 14);
		contentPane.add(lblAuthor);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BooksGUI.this.dispose();
				new ClientsGUI(library, currentLibrarian);
				
			}
		});
		btnBack.setBounds(355, 213, 89, 23);
		contentPane.add(btnBack);
		
		
		this.setResizable(false);
		this.setVisible(true);
		
		library=lib;
		currentLibrarian=librarian;
		bookList=lib.getLibraryMap().get(currentClient);
		label.setText("Editing book list for : " + currentClient.getFirstName() +" " + currentClient.getLastName());
		
		
		
		setModelBookList();
		
		
		
	}
	
	
	
	private void setModelBookList() {
		
		if(bookList==null){
			JOptionPane.showMessageDialog(BooksGUI.this, "No books for this client, please add some!","No Books",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		DefaultTableModel tableModel=new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		tableModel.addColumn("Book Name");
		tableModel.addColumn("Book Author");
		tableModel.addColumn("Genre");
		
		
		for (Book book: bookList ){	
			String[] row={ book.getTitle(), book.getAuthor(), book.getGenre() };
			tableModel.addRow(row);
		}
		
		table.setModel(tableModel);
		

	}
	
	
	
}
