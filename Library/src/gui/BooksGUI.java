package gui;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.swing.ListSelectionModel;

import exceptions.NoBookTypeException;
import books.Book;
import books.BookFactory;
import books.State;
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
    
    private JRadioButton rdbtnPoetry = new JRadioButton("Poetry");
    private JRadioButton rdbtnSF = new JRadioButton("Science-Fiction");
    private JRadioButton rdbtnScience = new JRadioButton("Science");
    private JRadioButton rdbtnDrama = new JRadioButton("Drama");
    
    

    /**
     * Create the frame.
     */
    public BooksGUI(LibrarySingleton lib, final Client currentClient, Librarian librarian) {
	
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setBounds(100, 100, 663, 455);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 11, 341, 373);
	contentPane.add(scrollPane);

	table = new JTable();
	scrollPane.setViewportView(table);

	JButton btnAddBook = new JButton("Add Book");
	// controller for adding a book
	btnAddBook.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		
				
		if (getSelectedRB()!=null && BooksGUI.this.textField.getText()!="" && BooksGUI.this.textField_1.getText()!="" ){
		    boolean notLibrary=true;
		    try {
			
			if(library.getLibraryClient()==currentClient){
			    notLibrary=false;  // if a book is added to the library it will not have a return date
			}
			//adding book to client list of books
			bookList.add(BookFactory.bookCreator(getSelectedRB(), textField.getText(), textField_1.getText() , new State(notLibrary)));
		    } catch (NoBookTypeException e) {
			e.printStackTrace();
		    }
		    setModelBookList(); // refreshing table
		  
		}else{
		    // will be shown if invalid data will be input
		    JOptionPane.showMessageDialog(BooksGUI.this, "Please enter complete data for book!", "Warning",
			    		JOptionPane.WARNING_MESSAGE);
		}
	    }
	});
	btnAddBook.setBounds(521, 244, 109, 23);
	contentPane.add(btnAddBook);

	JButton btnDeleteBook = new JButton("Delete Book");
	//controller for removing a book
	btnDeleteBook.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {

		int selectedRowIndex = table.getSelectedRow(); // gets the index in the table
		if (selectedRowIndex == -1) {
		    // will be shown if the user does not select a book
		    JOptionPane.showMessageDialog(BooksGUI.this, "Please select a row!", "Warning",
			    			  JOptionPane.WARNING_MESSAGE);
		    return;
		}
		bookList.remove(selectedRowIndex); // book is removed from list
		setModelBookList(); // refreshing table

	    }
	});
	btnDeleteBook.setBounds(521, 281, 109, 23);
	contentPane.add(btnDeleteBook);

	JLabel label = new JLabel("");
	label.setBounds(388, 31, 242, 14);
	contentPane.add(label);

	
	rdbtnPoetry.setBounds(372, 281, 109, 23);
	contentPane.add(rdbtnPoetry);

	rdbtnSF.setBounds(372, 318, 109, 23);
	contentPane.add(rdbtnSF);

	rdbtnScience.setBounds(372, 353, 109, 23);
	contentPane.add(rdbtnScience);

	rdbtnDrama.setBounds(372, 244, 109, 23);
	contentPane.add(rdbtnDrama);

	ButtonGroup bGroup = new ButtonGroup();
	bGroup.add(rdbtnDrama);
	bGroup.add(rdbtnPoetry);
	bGroup.add(rdbtnScience);
	bGroup.add(rdbtnSF);

	textField = new JTextField();
	textField.setBounds(471, 101, 163, 20);
	contentPane.add(textField);
	textField.setColumns(10);

	textField_1 = new JTextField();
	textField_1.setBounds(471, 132, 163, 20);
	contentPane.add(textField_1);
	textField_1.setColumns(10);

	JLabel lblTitle = new JLabel("Title:");
	lblTitle.setBounds(392, 104, 46, 14);
	contentPane.add(lblTitle);

	JLabel lblAuthor = new JLabel("Author:");
	lblAuthor.setBounds(392, 135, 46, 14);
	contentPane.add(lblAuthor);

	JButton btnBack = new JButton("Back");
	//controller for going back to the clients window
	btnBack.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {

		BooksGUI.this.dispose();
		new ClientsGUI(library, currentLibrarian);
		//goes back to the clients window

	    }
	});
	btnBack.setBounds(541, 361, 89, 23);
	contentPane.add(btnBack);

	this.setResizable(false);
	this.setVisible(true);
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	// sets fields in the class that will be necessary to manipulate data
	library = lib;
	currentLibrarian = librarian;
	bookList = lib.getLibraryMap().get(currentClient);
	//set label for current client
	label.setText("Editing book list for : " + currentClient.getFirstName() + " " + currentClient.getLastName());
	setModelBookList(); // refreshing book list

    }

    
   // method sets the model for the table from the list of books which the client has
    private void setModelBookList() {

	if (bookList == null) {
	    // shown if client has no books
	    JOptionPane.showMessageDialog(BooksGUI.this, "No books for this client, please add some!", "No Books",
		    JOptionPane.WARNING_MESSAGE);
	    return;
	}

	DefaultTableModel tableModel = new DefaultTableModel() {
	    @Override
	    public boolean isCellEditable(int arg0, int arg1) {
		return false; // the user will not be able to edit cells
	    }
	};
	//sets table header
	tableModel.addColumn("Book Name");
	tableModel.addColumn("Book Author");
	tableModel.addColumn("Genre");
	tableModel.addColumn("Start Date");
	tableModel.addColumn("Return Date");

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); 

	for (Book book : bookList) {

	    String startDate;
	    String returnDate;

	    if (book.getState().getStartDate() != null) {
		startDate = dateFormat.format(book.getState().getStartDate().getTime());
		//get the time the book was loaned
		returnDate = dateFormat.format(book.getState().getReturnDate().getTime()); 
		//gets the time the book will be returned
	    } else {
		startDate = "In Library"; // if the book is in library there will be no dates in the table
		returnDate = "";
	    }
	    
	    //prepares the row that will be added
	    String[] row = { book.getTitle(), book.getAuthor(), book.getGenre(), startDate, returnDate };
	    
	    tableModel.addRow(row); // adds row to table
	}

	table.setModel(tableModel); // refreshing table

    }
    
    
    
    
  //method returns string specific to the radio button selection
    private String getSelectedRB(){
	
	if(rdbtnDrama.isSelected()) { return "Drama"; }
	else if(rdbtnPoetry.isSelected()) { return "Poetry"; }
	else if(rdbtnSF.isSelected()) { return "SF"; }
	else if(rdbtnScience.isSelected()) { return "Science"; }
	else { 
	    return null;
	}
	
    }
    
    

}
