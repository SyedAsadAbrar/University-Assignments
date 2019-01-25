/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms_final;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Asad
 */
public class LibrarianUI extends javax.swing.JFrame{
    String username;
    Library library;
    Librarian librarian;
    
    private javax.swing.JMenu ViewProfileMenuButton;
    private javax.swing.JMenu LogoutMenuButton;
    private javax.swing.JMenuBar TopMenuBar;
    private javax.swing.JTabbedPane TabbedPane;
    
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JLabel SearchWelcomeLabel;
    private javax.swing.JTextField SearchField;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JButton SearchButton;
    private javax.swing.JButton RequestButton;
    
    private javax.swing.JPanel SearchButtonPanel;
    private javax.swing.ButtonGroup SearchButtonGroup;
    private javax.swing.JRadioButton ByAuthorRadioButton;
    private javax.swing.JRadioButton BySubjectRadioButton;
    private javax.swing.JRadioButton ByTitleRadioButton;
    private javax.swing.JTable SearchResultsTable;
    private javax.swing.JScrollPane SearchResultsTableScrollPane;
    
    private javax.swing.JPanel CheckPanel;
    private javax.swing.JLabel LoanedOutBooksLabel;
    private javax.swing.JLabel CheckOutLabel;
    private javax.swing.JButton CheckOutButton;
    private javax.swing.JButton CheckInButton;
    private javax.swing.JButton RenewButton;
    private javax.swing.JTable CheckOutTable;
    private javax.swing.JScrollPane CheckOutTableScrollPane;
    private javax.swing.JTable CheckInTable;
    private javax.swing.JScrollPane CheckInTableScrollPane;
    
    private javax.swing.JPanel BorrowedPanel;
    private javax.swing.JLabel BorrowedUserLabel;
    private javax.swing.JScrollPane BorrowedTableScrollPane;
    private javax.swing.JTable BorrowedTable;
    
    private javax.swing.JPanel FinePanel;
    private javax.swing.JLabel FineDueLabel;
    private javax.swing.JTable FineDueTable;
    private javax.swing.JButton RecordFineButton;
    private javax.swing.JScrollPane FineDueTableScrollPane;
    
    private javax.swing.JLabel BorrowerManagementLabel;
    private javax.swing.JPanel BorrowerManagementPanel;
    private javax.swing.JButton AddBorrowerButton;
    private javax.swing.JButton EditBorrowerButton;
    
    private javax.swing.JFrame AddBorrowerFrame;
    private javax.swing.JLabel AddBorrowerFrameTitleLabel;
    private javax.swing.JPanel AddBorrowerFrameUpperPanel;
    private javax.swing.JPanel AddBorrowerFrameLowerPanel;    
    private javax.swing.JLabel AddBorrowerFrameAddressLabel;
    private javax.swing.JTextField AddBorrowerFrameAddressField;
    private javax.swing.JLabel AddBorrowerFrameFinePaidLabel;
    private javax.swing.JTextField AddBorrowerFrameFinePaidField;
    private javax.swing.JLabel AddBorrowerFrameNameLabel;
    private javax.swing.JTextField AddBorrowerFrameNameField;
    private javax.swing.JLabel AddBorrowerFramePasswordLabel;
    private javax.swing.JPasswordField AddBorrowerFramePasswordField;
    private javax.swing.JLabel AddBorrowerFramePhoneLabel;
    private javax.swing.JTextField AddBorrowerFramePhoneField;
    private javax.swing.JLabel AddBorrowerFrameUsernameLabel;
    private javax.swing.JTextField AddBorrowerFrameUsernameField;
    private javax.swing.JButton AddBorrowerFrameButton;
    
    private javax.swing.JFrame EditBorrowerFrame;
    private javax.swing.JLabel EditBorrowerFrameTitleLabel;
    private javax.swing.JPanel EditBorrowerFrameUpperPanel;
    private javax.swing.JPanel EditBorrowerFrameLowerPanel;
    private javax.swing.JLabel EditBorrowerFrameUsernameLabel;
    private javax.swing.JTextField EditBorrowerFrameUsernameField;
    private javax.swing.JButton EditBorrowerFrameButton;
    private javax.swing.JLabel EditBorrowerFramePhoneLabel;
    private javax.swing.JTextField EditBorrowerFramePhoneField;
    private javax.swing.JLabel EditBorrowerFrameAddressLabel;
    private javax.swing.JTextField EditBorrowerFrameAddressField;
    
    private javax.swing.JFrame ProfileFrame;
    private javax.swing.JLabel ProfileFrameTitleLabel;
    private javax.swing.JPanel ProfileFrameUpperPanel;
    private javax.swing.JPanel ProfileFrameLowerPanel;    
    private javax.swing.JLabel ProfileFrameAddressLabel;
    private javax.swing.JLabel ProfileFrameAddressValue;
    private javax.swing.JLabel ProfileFrameNameLabel;
    private javax.swing.JLabel ProfileFrameNameValue;
    private javax.swing.JLabel ProfileFramePasswordLabel;
    private javax.swing.JPasswordField ProfileFramePasswordValue;
    private javax.swing.JLabel ProfileFramePhoneLabel;
    private javax.swing.JLabel ProfileFramePhoneValue;
    private javax.swing.JLabel ProfileFrameUsernameLabel;
    private javax.swing.JLabel ProfileFrameUsernameValue;
    private javax.swing.JLabel ProfileFrameFinePaidLabel;
    private javax.swing.JLabel ProfileFrameFinePaidValue;
    
    private JPanel BookManagementPanel;
    private JLabel BookManagementLabel;
    private JButton AddBookButton;
    private JButton DeleteBookButton;
    private JButton EditBookButton;
    private JTable BooksTable;
    private JScrollPane BooksTableScrollPane;
    private JFrame AddBookFrame;
    private JLabel AddBookFrameTitleLabel;
    private JPanel AddBookFrameUpperPanel;
    private JPanel AddBookFrameLowerPanel;
    private JLabel AddBookFrameISBNLabel;
    private JTextField AddBookFrameISBNField;
    private JLabel AddBookFrameNameLabel;
    private JTextField AddBookFrameNameField;
    private JLabel AddBookFrameAuthorLabel;
    private JLabel AddBookFrameSubjectLabel;
    private JTextField AddBookFrameAuthorField;
    private JTextField AddBookFrameSubjectField;
    private JButton AddBookFrameAddButton;
    private JButton AddBookFrameAddID;
    private JFrame EditBookFrame;
    private JLabel EditBookFrameTitleLabel;
    private JPanel EditBookFrameUpperPanel;
    private JPanel EditBookFrameLowerPanel;
    private JLabel EditBookFrameISBNLabel;
    private JTextField EditBookFrameISBNField;
    private JLabel EditBookFrameNameLabel;
    private JTextField EditBookFrameNameField;
    private JLabel EditBookFrameAuthorLabel;
    private JLabel EditBookFrameSubjectLabel;
    private JTextField EditBookFrameAuthorField;
    private JTextField EditBookFrameSubjectField;
    private JButton EditBookFrameSaveButton;
    private JButton EditBookFrameAddID;
    private int ids;
    private String oldIsbn;
    private int oldIds;
    
    public LibrarianUI()
    {
        //Library a = new Library();
        initComponents();
    }
    
    void initComponents()
    {
        // <editor-fold defaultstate="collapsed" desc="Initialization">
        TabbedPane = new javax.swing.JTabbedPane();
        TopMenuBar = new javax.swing.JMenuBar();
        ViewProfileMenuButton = new javax.swing.JMenu();
        LogoutMenuButton = new javax.swing.JMenu();
        
        SearchPanel = new javax.swing.JPanel();
        SearchButtonPanel = new javax.swing.JPanel();
        SearchWelcomeLabel = new javax.swing.JLabel();
        SearchLabel = new javax.swing.JLabel();
        SearchField = new javax.swing.JTextField();
        SearchButtonGroup = new javax.swing.ButtonGroup();
        ByAuthorRadioButton = new javax.swing.JRadioButton();
        BySubjectRadioButton = new javax.swing.JRadioButton();
        ByTitleRadioButton = new javax.swing.JRadioButton();
        SearchButton = new javax.swing.JButton();
        SearchResultsTable = new javax.swing.JTable();
        SearchResultsTableScrollPane = new javax.swing.JScrollPane();
        RequestButton = new javax.swing.JButton();
        
        BorrowedPanel = new javax.swing.JPanel();
        BorrowedUserLabel = new javax.swing.JLabel();
        BorrowedTable = new javax.swing.JTable();
        BorrowedTableScrollPane = new javax.swing.JScrollPane();
        
        CheckPanel = new javax.swing.JPanel();
        CheckOutLabel = new javax.swing.JLabel();
        LoanedOutBooksLabel = new javax.swing.JLabel();
        CheckOutButton = new javax.swing.JButton();
        CheckInButton = new javax.swing.JButton();
        CheckInTable = new javax.swing.JTable();
        CheckInTableScrollPane = new javax.swing.JScrollPane();
        CheckOutTable = new javax.swing.JTable();
        CheckOutTableScrollPane = new javax.swing.JScrollPane();
        RenewButton = new javax.swing.JButton();
        
        FineDueLabel = new javax.swing.JLabel();
        FineDueTable = new javax.swing.JTable();
        FineDueTableScrollPane = new javax.swing.JScrollPane();
        RecordFineButton = new javax.swing.JButton();
        FinePanel = new javax.swing.JPanel();
            
        BorrowerManagementPanel= new javax.swing.JPanel();
        BorrowerManagementLabel = new javax.swing.JLabel();
        AddBorrowerButton = new javax.swing.JButton();
        EditBorrowerButton = new javax.swing.JButton();
        
        AddBorrowerFrame = new javax.swing.JFrame();
        AddBorrowerFrameTitleLabel = new javax.swing.JLabel();
        AddBorrowerFrameUpperPanel = new javax.swing.JPanel();
        AddBorrowerFrameLowerPanel = new javax.swing.JPanel();
        AddBorrowerFrameAddressLabel = new javax.swing.JLabel();
        AddBorrowerFrameAddressField = new javax.swing.JTextField();
        AddBorrowerFrameNameLabel = new javax.swing.JLabel();
        AddBorrowerFrameNameField = new javax.swing.JTextField();
        AddBorrowerFramePasswordLabel = new javax.swing.JLabel();
        AddBorrowerFramePasswordField = new javax.swing.JPasswordField();
        AddBorrowerFramePhoneLabel = new javax.swing.JLabel();
        AddBorrowerFramePhoneField = new javax.swing.JTextField();
        AddBorrowerFrameUsernameLabel = new javax.swing.JLabel();
        AddBorrowerFrameUsernameField = new javax.swing.JTextField();
        AddBorrowerFrameButton = new javax.swing.JButton();
        
        EditBorrowerFrame = new javax.swing.JFrame();
        EditBorrowerFrameTitleLabel = new javax.swing.JLabel();
        EditBorrowerFrameUpperPanel = new javax.swing.JPanel();
        EditBorrowerFrameLowerPanel = new javax.swing.JPanel();
        EditBorrowerFrameUsernameLabel = new javax.swing.JLabel();
        EditBorrowerFrameUsernameField = new javax.swing.JTextField();
        EditBorrowerFrameButton = new javax.swing.JButton();
        EditBorrowerFramePhoneLabel = new javax.swing.JLabel();
        EditBorrowerFramePhoneField = new javax.swing.JTextField();
        EditBorrowerFrameAddressLabel = new javax.swing.JLabel();
        EditBorrowerFrameAddressField = new javax.swing.JTextField();
        
        BookManagementPanel = new javax.swing.JPanel();
        BookManagementLabel = new javax.swing.JLabel();
        AddBookButton = new javax.swing.JButton();
        DeleteBookButton = new javax.swing.JButton();
        BooksTable = new javax.swing.JTable();
        BooksTableScrollPane = new javax.swing.JScrollPane();
        AddBookFrame = new javax.swing.JFrame();
        AddBookFrameTitleLabel = new javax.swing.JLabel();
        AddBookFrameUpperPanel = new javax.swing.JPanel();
        AddBookFrameLowerPanel = new javax.swing.JPanel();
        AddBookFrameISBNLabel = new javax.swing.JLabel();
        AddBookFrameISBNField = new javax.swing.JTextField();
        AddBookFrameNameLabel = new javax.swing.JLabel();
        AddBookFrameNameField = new javax.swing.JTextField();       
        AddBookFrameAuthorLabel = new javax.swing.JLabel();
        AddBookFrameAuthorField = new javax.swing.JTextField();
        AddBookFrameSubjectLabel = new javax.swing.JLabel();
        AddBookFrameSubjectField = new javax.swing.JTextField();
        AddBookFrameAddButton = new javax.swing.JButton();
        AddBookFrameAddID = new javax.swing.JButton();
        EditBookButton = new javax.swing.JButton();
        ids = 0;
        oldIsbn = null;
        oldIds = 0;
        
        EditBookFrame = new javax.swing.JFrame();
        EditBookFrameTitleLabel = new javax.swing.JLabel();
        EditBookFrameUpperPanel = new javax.swing.JPanel();
        EditBookFrameLowerPanel = new javax.swing.JPanel();
        EditBookFrameISBNLabel = new javax.swing.JLabel();
        EditBookFrameISBNField = new javax.swing.JTextField();
        EditBookFrameNameLabel = new javax.swing.JLabel();
        EditBookFrameNameField = new javax.swing.JTextField();       
        EditBookFrameAuthorLabel = new javax.swing.JLabel();
        EditBookFrameAuthorField = new javax.swing.JTextField();
        EditBookFrameSubjectLabel = new javax.swing.JLabel();
        EditBookFrameSubjectField = new javax.swing.JTextField();
        EditBookFrameSaveButton = new javax.swing.JButton();
        EditBookFrameAddID = new javax.swing.JButton();
        
        ProfileFrame = new javax.swing.JFrame();
        ProfileFrameTitleLabel = new javax.swing.JLabel();
        ProfileFrameUpperPanel = new javax.swing.JPanel();
        ProfileFrameLowerPanel = new javax.swing.JPanel();
        ProfileFrameUsernameLabel = new javax.swing.JLabel();
        ProfileFrameUsernameValue = new javax.swing.JLabel();
        ProfileFramePasswordLabel = new javax.swing.JLabel();
        ProfileFramePasswordValue = new javax.swing.JPasswordField();
        ProfileFrameNameLabel = new javax.swing.JLabel();
        ProfileFrameNameValue = new javax.swing.JLabel();
        ProfileFrameAddressLabel = new javax.swing.JLabel();
        ProfileFrameAddressValue = new javax.swing.JLabel();
        ProfileFramePhoneLabel = new javax.swing.JLabel();
        ProfileFramePhoneValue = new javax.swing.JLabel();
        ProfileFrameFinePaidLabel = new javax.swing.JLabel();
        ProfileFrameFinePaidValue = new javax.swing.JLabel();

// </editor-fold>
        
        //MainFrame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Management System");
        getContentPane().setBackground(new java.awt.Color(228, 240, 252));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("MainFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        
        // <editor-fold defaultstate="collapsed" desc="ProfileFrame ">
        ProfileFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ProfileFrame.setTitle("Profile - " + username);
        ProfileFrame.getContentPane().setBackground(new java.awt.Color(228, 240, 252));
        ProfileFrame.setMinimumSize(new java.awt.Dimension(500, 500));
        ProfileFrame.setName("ProfileFrame"); // NOI18N
        ProfileFrame.setResizable(false);
        ProfileFrame.setSize(new java.awt.Dimension(500, 500));
        
        ProfileFrameTitleLabel.setFont(new java.awt.Font("Lato Black", 1, 30)); // NOI18N
        ProfileFrameTitleLabel.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameTitleLabel.setText("Profile Details");
        ProfileFrameTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        ProfileFrameUpperPanel.setBackground(new java.awt.Color(228, 240, 252));
        
        javax.swing.GroupLayout ProfileFrameUpperPanelLayout = new javax.swing.GroupLayout(ProfileFrameUpperPanel);
        ProfileFrameUpperPanel.setLayout(ProfileFrameUpperPanelLayout);
        ProfileFrameUpperPanelLayout.setHorizontalGroup(
                ProfileFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ProfileFrameTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        ProfileFrameUpperPanelLayout.setVerticalGroup(
                ProfileFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProfileFrameUpperPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(ProfileFrameTitleLabel)
                                .addGap(43, 43, 43))
        );
        
        ProfileFrame.getContentPane().add(ProfileFrameUpperPanel, java.awt.BorderLayout.PAGE_START);
        
        ProfileFrameLowerPanel.setBackground(new java.awt.Color(184, 216, 216));
        ProfileFrameLowerPanel.setLayout(new java.awt.GridLayout(6, 2));
        
        ProfileFrameUsernameLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFrameUsernameLabel.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameUsernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameUsernameLabel.setText("Username");
        ProfileFrameLowerPanel.add(ProfileFrameUsernameLabel);
        
        ProfileFrameUsernameValue.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFrameUsernameValue.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameUsernameValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameLowerPanel.add(ProfileFrameUsernameValue);
        
        ProfileFramePasswordLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFramePasswordLabel.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFramePasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFramePasswordLabel.setText("Password");
        ProfileFrameLowerPanel.add(ProfileFramePasswordLabel);
        
        ProfileFramePasswordValue.setEditable(false);
        ProfileFramePasswordValue.setBackground(new java.awt.Color(184, 216, 216));
        ProfileFramePasswordValue.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFramePasswordValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ProfileFramePasswordValue.setBorder(null);
        ProfileFramePasswordValue.setEchoChar('*');
        ProfileFramePasswordValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProfileFramePasswordValueMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProfileFramePasswordValueMouseReleased(evt);
            }
        });
        ProfileFrameLowerPanel.add(ProfileFramePasswordValue);
        
        ProfileFrameNameLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFrameNameLabel.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameNameLabel.setText("Name");
        ProfileFrameLowerPanel.add(ProfileFrameNameLabel);
        
        ProfileFrameNameValue.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFrameNameValue.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameNameValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameLowerPanel.add(ProfileFrameNameValue);
        
        ProfileFrameAddressLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFrameAddressLabel.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameAddressLabel.setText("Address");
        ProfileFrameLowerPanel.add(ProfileFrameAddressLabel);
        
        ProfileFrameAddressValue.setFont(new java.awt.Font("Lato", 1, 12)); // NOI18N
        ProfileFrameAddressValue.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameAddressValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameLowerPanel.add(ProfileFrameAddressValue);
        
        ProfileFramePhoneLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFramePhoneLabel.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFramePhoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFramePhoneLabel.setText("Phone");
        ProfileFrameLowerPanel.add(ProfileFramePhoneLabel);
        
        ProfileFramePhoneValue.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFramePhoneValue.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFramePhoneValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameLowerPanel.add(ProfileFramePhoneValue);
        
        ProfileFrameFinePaidLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFrameFinePaidLabel.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameFinePaidLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameFinePaidLabel.setText("Fine Paid");
        ProfileFrameLowerPanel.add(ProfileFrameFinePaidLabel);
        
        ProfileFrameFinePaidValue.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        ProfileFrameFinePaidValue.setForeground(new java.awt.Color(46, 61, 64));
        ProfileFrameFinePaidValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileFrameLowerPanel.add(ProfileFrameFinePaidValue);
        
        ProfileFrame.getContentPane().add(ProfileFrameLowerPanel, java.awt.BorderLayout.CENTER);
        
        ProfileFrame.setVisible(false);

// </editor-fold>
        
        //TabbedPane
        TabbedPane.setBackground(new java.awt.Color(228, 240, 252));
        TabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        TabbedPane.setFont(new java.awt.Font("Lato Black", 0, 16)); // NOI18N
        TabbedPane.setForeground(new java.awt.Color(46, 61, 64));
        
        // <editor-fold defaultstate="collapsed" desc="Search Books">
        SearchPanel.setBackground(new java.awt.Color(184, 216, 216));
        
        SearchPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchPanelMouseClicked(evt);
            }
        });
        
        SearchWelcomeLabel.setFont(new java.awt.Font("Lato Black", 1, 24)); // NOI18N
        SearchWelcomeLabel.setForeground(new java.awt.Color(46, 61, 64));
        SearchWelcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SearchWelcomeLabel.setText("Welcome to LMS, " + username + "!");
        SearchWelcomeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        SearchLabel.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        SearchLabel.setForeground(new java.awt.Color(46, 61, 64));
        SearchLabel.setText("Search");
        
        SearchField.setBackground(new java.awt.Color(228, 240, 252));
        SearchField.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        SearchField.setForeground(new java.awt.Color(46, 61, 64));
        SearchField.setSize(185, 35);
        SearchField.setMaximumSize(new java.awt.Dimension(185, 35));
        SearchField.setMinimumSize(new java.awt.Dimension(185, 35));
        
        SearchButtonPanel.setBackground(new java.awt.Color(184, 216, 216));
        
        ByTitleRadioButton.setBackground(new java.awt.Color(184, 216, 216));
        ByTitleRadioButton.setForeground(new java.awt.Color(46, 61, 64));
        ByTitleRadioButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        ByTitleRadioButton.setText("By Title");
        
        ByAuthorRadioButton.setBackground(new java.awt.Color(184, 216, 216));
        ByAuthorRadioButton.setForeground(new java.awt.Color(46, 61, 64));
        ByAuthorRadioButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        ByAuthorRadioButton.setText("By Author");
        
        BySubjectRadioButton.setBackground(new java.awt.Color(184, 216, 216));
        BySubjectRadioButton.setForeground(new java.awt.Color(46, 61, 64));
        BySubjectRadioButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        BySubjectRadioButton.setText("By Subject");
        
        SearchButtonGroup.add(ByTitleRadioButton);
        SearchButtonGroup.add(ByAuthorRadioButton);
        SearchButtonGroup.add(BySubjectRadioButton);
        ByTitleRadioButton.setSelected(true);
        
        SearchButtonPanel.setSize(339, 29);
        SearchButtonPanel.setMaximumSize(new java.awt.Dimension(339, 29));
        SearchButtonPanel.setMinimumSize(new java.awt.Dimension(339, 29));
        SearchButtonPanel.setLayout(new GridLayout(1, 3));
        SearchButtonPanel.add(ByTitleRadioButton);
        SearchButtonPanel.add(ByAuthorRadioButton);
        SearchButtonPanel.add(BySubjectRadioButton);
        
        SearchButton.setBackground(new java.awt.Color(209, 231, 255));
        SearchButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        SearchButton.setForeground(new java.awt.Color(46, 61, 64));
        SearchButton.setText("Search");
        SearchButton.setBorderPainted(false);
        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchButtonMouseClicked(evt);
            }
        });
        
        RequestButton.setBackground(new java.awt.Color(209, 231, 255));
        RequestButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        RequestButton.setForeground(new java.awt.Color(46, 61, 64));
        RequestButton.setText("Request Book");
        RequestButton.setBorderPainted(false);
        RequestButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RequestButtonMouseClicked(evt);
            }
        });
        
        SearchResultsTableScrollPane.setBackground(new java.awt.Color(228, 240, 252));
        SearchResultsTableScrollPane.setMaximumSize(new java.awt.Dimension(600, 200));
        SearchResultsTableScrollPane.setSize(600, 200);
        SearchResultsTableScrollPane.setMinimumSize(new java.awt.Dimension(600, 200));
        SearchResultsTableScrollPane.setPreferredSize(new java.awt.Dimension(600, 200));
        
        SearchResultsTable.setMaximumSize(new java.awt.Dimension(600, 200));
        SearchResultsTable.setSize(600, 200);
        SearchResultsTable.setMinimumSize(new java.awt.Dimension(600, 200));
        SearchResultsTable.setPreferredSize(new java.awt.Dimension(600, 200));
        
        SearchResultsTable.setBackground(new java.awt.Color(228, 240, 252));
        //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        SearchResultsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ISBN", "Title", "Author", "Subject"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        SearchResultsTable.getTableHeader().setReorderingAllowed(false);
        SearchResultsTableScrollPane.setViewportView(SearchResultsTable);
        if (SearchResultsTable.getColumnModel().getColumnCount() > 0) {
            SearchResultsTable.getColumnModel().getColumn(0).setResizable(false);
            SearchResultsTable.getColumnModel().getColumn(1).setResizable(false);
            SearchResultsTable.getColumnModel().getColumn(2).setResizable(false);
            SearchResultsTable.getColumnModel().getColumn(3).setResizable(false);
        }
        
        SearchResultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchResultsTableMouseClicked(evt);
            }
        });

        //For testing
        //SearchWelcomeLabel.setVisible(false);
        //SearchLabel.setVisible(false);
        //SearchField.setVisible(false);
        //SearchButton.setVisible(false);
        //SearchButtonPanel.setVisible(false);
        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
                SearchPanelLayout.createParallelGroup()
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(SearchWelcomeLabel)
                        )
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(SearchLabel)
                                .addGap(20, 20, 20)
                                .addComponent(SearchField)
                                .addGap(20, 20, 20)
                                .addComponent(SearchButton)
                                .addGap(20, 20, 20)
                        )
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(SearchButtonPanel)
                        )
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(SearchResultsTableScrollPane)
                                .addGap(100, 100, 100)
                        )
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(320, 320, 320)
                                .addComponent(RequestButton)
                        )
        );
        SearchPanelLayout.setVerticalGroup(
                SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearchPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(SearchWelcomeLabel)
                                .addGap(50, 50, 50)
                                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(SearchLabel)
                                        .addComponent(SearchField)
                                        .addComponent(SearchButton)
                                )
                                .addGap(30, 30, 30)
                                .addComponent(SearchButtonPanel)
                                .addGap(20, 20, 20)
                                .addComponent(SearchResultsTableScrollPane)
                                .addGap(30, 30, 30)
                                .addComponent(RequestButton)
                        )
        );
        
        RequestButton.setEnabled(false);

// </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Borrowed Books">
        BorrowedPanel.setBackground(new java.awt.Color(184, 216, 216));
        
        BorrowedUserLabel.setFont(new java.awt.Font("Lato Black", 1, 24)); // NOI18N
        BorrowedUserLabel.setForeground(new java.awt.Color(46, 61, 64));
        BorrowedUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BorrowedUserLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        BorrowedTableScrollPane.setBackground(new java.awt.Color(228, 240, 252));
        BorrowedTableScrollPane.setMaximumSize(new java.awt.Dimension(600, 350));
        BorrowedTableScrollPane.setSize(600, 350);
        BorrowedTableScrollPane.setMinimumSize(new java.awt.Dimension(600, 350));
        BorrowedTableScrollPane.setPreferredSize(new java.awt.Dimension(600, 350));
        
        BorrowedTable.setMaximumSize(new java.awt.Dimension(600, 350));
        BorrowedTable.setSize(600, 350);
        BorrowedTable.setMinimumSize(new java.awt.Dimension(600, 350));
        BorrowedTable.setPreferredSize(new java.awt.Dimension(600, 350));
        
        BorrowedTable.setBackground(new java.awt.Color(228, 240, 252));
        //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        BorrowedTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Book ID", "ISBN", "Issue Date", "Return Date"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        BorrowedTable.getTableHeader().setReorderingAllowed(false);
        BorrowedTableScrollPane.setViewportView(BorrowedTable);
        if (BorrowedTable.getColumnModel().getColumnCount() > 0) {
            BorrowedTable.getColumnModel().getColumn(0).setResizable(false);
            BorrowedTable.getColumnModel().getColumn(1).setResizable(false);
            BorrowedTable.getColumnModel().getColumn(2).setResizable(false);
            BorrowedTable.getColumnModel().getColumn(3).setResizable(false);
        }
        
        javax.swing.GroupLayout BorrowedPanelLayout = new javax.swing.GroupLayout(BorrowedPanel);
        BorrowedPanel.setLayout(BorrowedPanelLayout);
        BorrowedPanelLayout.setHorizontalGroup(
                BorrowedPanelLayout.createParallelGroup()
                        .addGroup(BorrowedPanelLayout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(BorrowedUserLabel)
                        )
                        .addGroup(BorrowedPanelLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(BorrowedTableScrollPane)
                        )
        );
        BorrowedPanelLayout.setVerticalGroup(
                BorrowedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BorrowedPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(BorrowedUserLabel)
                                .addGap(70, 70, 70)
                                .addComponent(BorrowedTableScrollPane)
                                .addGap(30, 30, 30)
                        )
        );

// </editor-fold>
        
        TabbedPane.add("Search Books", SearchPanel);
        TabbedPane.add("Borrowed Books", BorrowedPanel);
        
        // <editor-fold defaultstate="collapsed" desc="Menu - View Profile, Logout">
        getContentPane().add(TabbedPane, java.awt.BorderLayout.CENTER);
        
        TopMenuBar.setBackground(new java.awt.Color(184, 216, 216));
        
        ViewProfileMenuButton.setText("View Profile");
        ViewProfileMenuButton.setFont(new java.awt.Font("Lato Black", 1, 18)); // NOI18N
        ViewProfileMenuButton.setForeground(new java.awt.Color(46, 61, 64));
        ViewProfileMenuButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ViewProfileMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ViewProfileMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewProfileMenuButtonMouseClicked(evt);
            }
        });
        TopMenuBar.add(ViewProfileMenuButton);
        
        TopMenuBar.add(Box.createHorizontalGlue());
        
        LogoutMenuButton.setText("Logout");
        LogoutMenuButton.setFont(new java.awt.Font("Lato Black", 1, 18)); // NOI18N
        LogoutMenuButton.setForeground(new java.awt.Color(46, 61, 64));
        LogoutMenuButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LogoutMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        LogoutMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMenuButtonMouseClicked(evt);
            }
        });
        TopMenuBar.add(LogoutMenuButton);
        
        setJMenuBar(TopMenuBar);

// </editor-fold>
        
        //Check-in/Check-out Panel
        CheckPanel.setBackground(new java.awt.Color(184, 216, 216));
        TabbedPane.add("Loan Management",CheckPanel);
        
        CheckPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckPanelMouseClicked(evt);
            }
        });
        
        TabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabbedPaneStateChanged(evt);
            }
        });
        
        // <editor-fold defaultstate="collapsed" desc="Loan Management">
        // <editor-fold defaultstate="collapsed" desc="CheckOut Label">
        CheckOutLabel.setFont(new java.awt.Font("Lato Black", 1, 24)); // NOI18N
        CheckOutLabel.setForeground(new java.awt.Color(46, 61, 64));
        CheckOutLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CheckOutLabel.setText("Check-Out Books");
        CheckOutLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="CheckOut Button">
        CheckOutButton.setBackground(new java.awt.Color(209, 231, 255));
        CheckOutButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        CheckOutButton.setForeground(new java.awt.Color(46, 61, 64));
        CheckOutButton.setText("Check-Out");
        CheckOutButton.setBorderPainted(false);
        CheckOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckOutButtonMouseClicked(evt);
            }
        });

        CheckOutButton.setEnabled(false);
        
// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="CheckOut TableScrollPane">
        CheckOutTableScrollPane.setBackground(new java.awt.Color(228, 240, 252));
        CheckOutTableScrollPane.setMaximumSize(new java.awt.Dimension(600, 100));
        CheckOutTableScrollPane.setSize(600, 100);
        CheckOutTableScrollPane.setMinimumSize(new java.awt.Dimension(600, 100));
        CheckOutTableScrollPane.setPreferredSize(new java.awt.Dimension(600, 100));

// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="CheckOut Table">
        CheckOutTable.setMaximumSize(new java.awt.Dimension(600, 100));
        CheckOutTable.setSize(600, 100);
        CheckOutTable.setMinimumSize(new java.awt.Dimension(600, 100));
        CheckOutTable.setPreferredSize(new java.awt.Dimension(600, 100));
        
        CheckOutTable.setBackground(new java.awt.Color(228, 240, 252));
        //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        CheckOutTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ISBN", "Book Name", "Book Author", "Username of Requestee"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        CheckOutTable.getTableHeader().setReorderingAllowed(false);
        CheckOutTableScrollPane.setViewportView(CheckOutTable);
        if (CheckOutTable.getColumnModel().getColumnCount() > 0) {
            CheckOutTable.getColumnModel().getColumn(0).setResizable(false);
            CheckOutTable.getColumnModel().getColumn(1).setResizable(false);
            CheckOutTable.getColumnModel().getColumn(2).setResizable(false);
            CheckOutTable.getColumnModel().getColumn(3).setResizable(false);
        }
        
        CheckOutTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckOutTableMouseClicked(evt);
            }
        });

// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="LoanedOutBooks Label">
        LoanedOutBooksLabel.setFont(new java.awt.Font("Lato Black", 1, 24)); // NOI18N
        LoanedOutBooksLabel.setForeground(new java.awt.Color(46, 61, 64));
        LoanedOutBooksLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoanedOutBooksLabel.setText("Currently Loaned Out Books");
        LoanedOutBooksLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="CheckIn Button">
        CheckInButton.setBackground(new java.awt.Color(209, 231, 255));
        CheckInButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        CheckInButton.setForeground(new java.awt.Color(46, 61, 64));
        CheckInButton.setText("Check-In");
        CheckInButton.setBorderPainted(false);
        CheckInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckInButtonMouseClicked(evt);
            }
        });

        CheckInButton.setEnabled(false);
        
// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="CheckIn TableScrollPane">
        CheckInTableScrollPane.setBackground(new java.awt.Color(228, 240, 252));
        CheckInTableScrollPane.setMaximumSize(new java.awt.Dimension(600, 100));
        CheckInTableScrollPane.setSize(600, 100);
        CheckInTableScrollPane.setMinimumSize(new java.awt.Dimension(600, 100));
        CheckInTableScrollPane.setPreferredSize(new java.awt.Dimension(600, 100));

// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="CheckIn Table">
        CheckInTable.setMaximumSize(new java.awt.Dimension(600, 100));
        CheckInTable.setSize(600, 100);
        CheckInTable.setMinimumSize(new java.awt.Dimension(600, 100));
        CheckInTable.setPreferredSize(new java.awt.Dimension(600, 100));
        
        CheckInTable.setBackground(new java.awt.Color(228, 240, 252));
        //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        CheckInTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Book ID", "ISBN", "Username of Borrower", "Issue Date", "Return Date"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        CheckInTable.getTableHeader().setReorderingAllowed(false);
        CheckInTableScrollPane.setViewportView(CheckInTable);
        if (CheckInTable.getColumnModel().getColumnCount() > 0) {
            CheckInTable.getColumnModel().getColumn(0).setResizable(false);
            CheckInTable.getColumnModel().getColumn(1).setResizable(false);
            CheckInTable.getColumnModel().getColumn(2).setResizable(false);
            CheckInTable.getColumnModel().getColumn(3).setResizable(false);
        }
        
        CheckInTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckInTableMouseClicked(evt);
            }
        });

// </editor-fold>

        RenewButton.setBackground(new java.awt.Color(209, 231, 255));
        RenewButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        RenewButton.setForeground(new java.awt.Color(46, 61, 64));
        RenewButton.setText("Renew");
        RenewButton.setBorderPainted(false);
        RenewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RenewButtonMouseClicked(evt);
            }
        });

        RenewButton.setEnabled(false);

        // <editor-fold defaultstate="collapsed" desc="CheckPanel Layout">
        javax.swing.GroupLayout CheckPanelLayout = new javax.swing.GroupLayout(CheckPanel);
        CheckPanel.setLayout(CheckPanelLayout);
        CheckPanelLayout.setHorizontalGroup(
                CheckPanelLayout.createParallelGroup()
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addComponent(CheckOutLabel)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(CheckOutTableScrollPane)
                                .addGap(100, 100, 100)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(335, 335, 335)
                                .addComponent(CheckOutButton)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(LoanedOutBooksLabel)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(CheckInTableScrollPane)
                                .addGap(100, 100, 100)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(RenewButton)
                                .addGap(60, 60, 60)
                                .addComponent(CheckInButton)
                        )
        );
        CheckPanelLayout.setVerticalGroup(
                CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CheckPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(CheckOutLabel)
                                .addGap(25, 25, 25)
                                .addComponent(CheckOutTableScrollPane)
                                .addGap(30, 30, 30)
                                .addComponent(CheckOutButton)
                                .addGap(30, 30, 30)
                                .addComponent(LoanedOutBooksLabel)
                                .addGap(25, 25, 25)
                                .addComponent(CheckInTableScrollPane)
                                .addGap(30, 30, 30)
                                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(RenewButton)
                                .addComponent(CheckInButton)
                                )
                        )
        );

// </editor-fold>
        // </editor-fold>
        
        //Record Fine
        FinePanel.setBackground(new java.awt.Color(184, 216, 216));
        TabbedPane.add("Record Fine", FinePanel);
        
        // <editor-fold defaultstate="collapsed" desc="Record Fine">
        
        FineDueLabel.setFont(new java.awt.Font("Lato Black", 1, 24)); // NOI18N
        FineDueLabel.setForeground(new java.awt.Color(46, 61, 64));
        FineDueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FineDueLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FineDueLabel.setText("Fine Due");
        
        FineDueTableScrollPane.setBackground(new java.awt.Color(228, 240, 252));
        FineDueTableScrollPane.setMaximumSize(new java.awt.Dimension(600, 300));
        FineDueTableScrollPane.setSize(600, 300);
        FineDueTableScrollPane.setMinimumSize(new java.awt.Dimension(600, 300));
        FineDueTableScrollPane.setPreferredSize(new java.awt.Dimension(600, 300));
        
        FineDueTable.setMaximumSize(new java.awt.Dimension(600, 300));
        FineDueTable.setSize(600, 300);
        FineDueTable.setMinimumSize(new java.awt.Dimension(600, 300));
        FineDueTable.setPreferredSize(new java.awt.Dimension(600, 300));
        
        FineDueTable.setBackground(new java.awt.Color(228, 240, 252));
        //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        FineDueTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Username", "Fine Due"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        FineDueTable.getTableHeader().setReorderingAllowed(false);
        FineDueTableScrollPane.setViewportView(FineDueTable);
        if (FineDueTable.getColumnModel().getColumnCount() > 0) {
            FineDueTable.getColumnModel().getColumn(0).setResizable(false);
            FineDueTable.getColumnModel().getColumn(1).setResizable(false);
        }
        
        RecordFineButton.setBackground(new java.awt.Color(209, 231, 255));
        RecordFineButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        RecordFineButton.setForeground(new java.awt.Color(46, 61, 64));
        RecordFineButton.setText("Record Fine");
        RecordFineButton.setBorderPainted(false);
        RecordFineButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RecordFineButtonMouseClicked(evt);
            }
        });
        
        javax.swing.GroupLayout FinePanelLayout = new javax.swing.GroupLayout(FinePanel);
        FinePanel.setLayout(FinePanelLayout);
        FinePanelLayout.setHorizontalGroup(
                FinePanelLayout.createParallelGroup()
                        .addGroup(FinePanelLayout.createSequentialGroup()
                                .addGap(350, 350, 350)
                                .addComponent(FineDueLabel)
                        )
                        .addGroup(FinePanelLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(FineDueTableScrollPane)
                        )
                        .addGroup(FinePanelLayout.createSequentialGroup()
                                .addGap(330, 330, 330)
                                .addComponent(RecordFineButton)
                        )
        );
        FinePanelLayout.setVerticalGroup(
                FinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FinePanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(FineDueLabel)
                                .addGap(70, 70, 70)
                                .addComponent(FineDueTableScrollPane)
                                .addGap(30, 30, 30)
                                .addComponent(RecordFineButton)
                        )
        );

        FineDueTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FineDueTableMouseClicked(evt);
            }
        });
        
// </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Borrower Management">
        BorrowerManagementPanel.setBackground(new java.awt.Color(184, 216, 216));
        TabbedPane.add("Borrower Management", BorrowerManagementPanel);
        
        BorrowerManagementLabel.setFont(new java.awt.Font("Lato Black", 1, 24)); // NOI18N
        BorrowerManagementLabel.setForeground(new java.awt.Color(46, 61, 64));
        BorrowerManagementLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BorrowerManagementLabel.setText("Borrower Management");
        BorrowerManagementLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        AddBorrowerButton.setBackground(new java.awt.Color(209, 231, 255));
        AddBorrowerButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        AddBorrowerButton.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerButton.setText("Add Borrower");
        AddBorrowerButton.setBorderPainted(false);
        AddBorrowerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBorrowerButtonMouseClicked(evt);
            }
        });
        
        EditBorrowerButton.setBackground(new java.awt.Color(209, 231, 255));
        EditBorrowerButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        EditBorrowerButton.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerButton.setText("Edit Borrower");
        EditBorrowerButton.setBorderPainted(false);
        EditBorrowerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditBorrowerButtonMouseClicked(evt);
            }
        });
        
        javax.swing.GroupLayout BorrowerManagementPanelLayout = new javax.swing.GroupLayout(BorrowerManagementPanel);
        BorrowerManagementPanel.setLayout(BorrowerManagementPanelLayout);
        BorrowerManagementPanelLayout.setHorizontalGroup(
                BorrowerManagementPanelLayout.createParallelGroup()
                        .addGroup(BorrowerManagementPanelLayout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addComponent(BorrowerManagementLabel)
                        )
                        .addGroup(BorrowerManagementPanelLayout.createSequentialGroup()
                                .addGap(310, 310, 310)
                                .addComponent(AddBorrowerButton)
                        )
                        .addGroup(BorrowerManagementPanelLayout.createSequentialGroup()
                                .addGap(310, 310, 310)
                                .addComponent(EditBorrowerButton)
                        )
        );
        BorrowerManagementPanelLayout.setVerticalGroup(
                BorrowerManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BorrowerManagementPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(BorrowerManagementLabel)
                                .addGap(100, 100, 100)
                                .addComponent(AddBorrowerButton)
                                .addGap(150, 150, 150)
                                .addComponent(EditBorrowerButton)
                        )
        );

// </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="AddBorrower Frame">
        AddBorrowerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        AddBorrowerFrame.setTitle("Add Borrower");
        AddBorrowerFrame.getContentPane().setBackground(new java.awt.Color(228, 240, 252));
        AddBorrowerFrame.setMinimumSize(new java.awt.Dimension(500, 500));
        AddBorrowerFrame.setName("AddBorrowerFrame"); // NOI18N
        AddBorrowerFrame.setResizable(false);
        AddBorrowerFrame.setSize(new java.awt.Dimension(500, 500));
        
        AddBorrowerFrameTitleLabel.setFont(new java.awt.Font("Lato Black", 1, 30)); // NOI18N
        AddBorrowerFrameTitleLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFrameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFrameTitleLabel.setText("Add Borrower");
        AddBorrowerFrameTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        AddBorrowerFrameUpperPanel.setBackground(new java.awt.Color(228, 240, 252));
        
        javax.swing.GroupLayout AddBorrowerFrameUpperPanelLayout = new javax.swing.GroupLayout(AddBorrowerFrameUpperPanel);
        AddBorrowerFrameUpperPanel.setLayout(AddBorrowerFrameUpperPanelLayout);
        AddBorrowerFrameUpperPanelLayout.setHorizontalGroup(
                AddBorrowerFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AddBorrowerFrameTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        AddBorrowerFrameUpperPanelLayout.setVerticalGroup(
                AddBorrowerFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddBorrowerFrameUpperPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(AddBorrowerFrameTitleLabel)
                                .addGap(43, 43, 43))
        );
        
        AddBorrowerFrame.getContentPane().add(AddBorrowerFrameUpperPanel, java.awt.BorderLayout.PAGE_START);
        
        AddBorrowerFrameLowerPanel.setBackground(new java.awt.Color(184, 216, 216));
        AddBorrowerFrameLowerPanel.setLayout(new java.awt.GridLayout(6, 2));
        
        AddBorrowerFrameUsernameLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFrameUsernameLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFrameUsernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFrameUsernameLabel.setText("Username");
        AddBorrowerFrameLowerPanel.add(AddBorrowerFrameUsernameLabel);
        
        AddBorrowerFrameUsernameField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFrameUsernameField.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFrameUsernameField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFrameLowerPanel.add(AddBorrowerFrameUsernameField);
        
        AddBorrowerFramePasswordLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFramePasswordLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFramePasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFramePasswordLabel.setText("Password");
        AddBorrowerFrameLowerPanel.add(AddBorrowerFramePasswordLabel);
        
        AddBorrowerFramePasswordField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFramePasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AddBorrowerFramePasswordField.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFramePasswordField.setEchoChar('*');
        AddBorrowerFrameLowerPanel.add(AddBorrowerFramePasswordField);
        
        AddBorrowerFrameNameLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFrameNameLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFrameNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFrameNameLabel.setText("Name");
        AddBorrowerFrameLowerPanel.add(AddBorrowerFrameNameLabel);
        
        AddBorrowerFrameNameField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFrameNameField.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFrameNameField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFrameLowerPanel.add(AddBorrowerFrameNameField);
        
        AddBorrowerFrameAddressLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFrameAddressLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFrameAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFrameAddressLabel.setText("Address");
        AddBorrowerFrameLowerPanel.add(AddBorrowerFrameAddressLabel);
        
        AddBorrowerFrameAddressField.setFont(new java.awt.Font("Lato", 1, 12)); // NOI18N
        AddBorrowerFrameAddressField.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFrameAddressField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFrameLowerPanel.add(AddBorrowerFrameAddressField);
        
        AddBorrowerFramePhoneLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFramePhoneLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFramePhoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFramePhoneLabel.setText("Phone");
        AddBorrowerFrameLowerPanel.add(AddBorrowerFramePhoneLabel);
        
        AddBorrowerFramePhoneField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBorrowerFramePhoneField.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFramePhoneField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBorrowerFrameLowerPanel.add(AddBorrowerFramePhoneField);
        
        AddBorrowerFrameButton.setBackground(new java.awt.Color(209, 231, 255));
        AddBorrowerFrameButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        AddBorrowerFrameButton.setForeground(new java.awt.Color(46, 61, 64));
        AddBorrowerFrameButton.setText("Add Borrower");
        AddBorrowerFrameButton.setBorderPainted(false);
        AddBorrowerFrameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBorrowerFrameButtonMouseClicked(evt);
            }
        });
        
        AddBorrowerFrameLowerPanel.add(AddBorrowerFrameButton);
        
        AddBorrowerFrame.getContentPane().add(AddBorrowerFrameLowerPanel, java.awt.BorderLayout.CENTER);
        
        AddBorrowerFrame.setVisible(false);

// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="EditBorrower Frame">
        EditBorrowerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        EditBorrowerFrame.setTitle("Edit Borrower");
        EditBorrowerFrame.getContentPane().setBackground(new java.awt.Color(228, 240, 252));
        EditBorrowerFrame.setMinimumSize(new java.awt.Dimension(500, 500));
        EditBorrowerFrame.setName("EditBorrowerFrame"); // NOI18N
        EditBorrowerFrame.setResizable(false);
        EditBorrowerFrame.setSize(new java.awt.Dimension(500, 500));
        
        EditBorrowerFrameTitleLabel.setFont(new java.awt.Font("Lato Black", 1, 30)); // NOI18N
        EditBorrowerFrameTitleLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerFrameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBorrowerFrameTitleLabel.setText("Edit Borrower");
        EditBorrowerFrameTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        EditBorrowerFrameUpperPanel.setBackground(new java.awt.Color(228, 240, 252));
        
        javax.swing.GroupLayout EditBorrowerFrameUpperPanelLayout = new javax.swing.GroupLayout(EditBorrowerFrameUpperPanel);
        EditBorrowerFrameUpperPanel.setLayout(EditBorrowerFrameUpperPanelLayout);
        EditBorrowerFrameUpperPanelLayout.setHorizontalGroup(
                EditBorrowerFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(EditBorrowerFrameTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        EditBorrowerFrameUpperPanelLayout.setVerticalGroup(
                EditBorrowerFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditBorrowerFrameUpperPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(EditBorrowerFrameTitleLabel)
                                .addGap(43, 43, 43))
        );
        
        EditBorrowerFrame.getContentPane().add(EditBorrowerFrameUpperPanel, java.awt.BorderLayout.PAGE_START);
        
        EditBorrowerFrameLowerPanel.setBackground(new java.awt.Color(184, 216, 216));
        EditBorrowerFrameLowerPanel.setLayout(new java.awt.GridLayout(4, 2));
        
        EditBorrowerFrameUsernameLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBorrowerFrameUsernameLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerFrameUsernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBorrowerFrameUsernameLabel.setText("Username");
        EditBorrowerFrameLowerPanel.add(EditBorrowerFrameUsernameLabel);
        
        EditBorrowerFrameUsernameField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBorrowerFrameUsernameField.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerFrameUsernameField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBorrowerFrameLowerPanel.add(EditBorrowerFrameUsernameField);
        
        EditBorrowerFrameAddressLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBorrowerFrameAddressLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerFrameAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBorrowerFrameAddressLabel.setText("Address");
        EditBorrowerFrameLowerPanel.add(EditBorrowerFrameAddressLabel);
        
        EditBorrowerFrameAddressField.setFont(new java.awt.Font("Lato", 1, 12)); // NOI18N
        EditBorrowerFrameAddressField.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerFrameAddressField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBorrowerFrameLowerPanel.add(EditBorrowerFrameAddressField);
        
        EditBorrowerFramePhoneLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBorrowerFramePhoneLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerFramePhoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBorrowerFramePhoneLabel.setText("Phone");
        EditBorrowerFrameLowerPanel.add(EditBorrowerFramePhoneLabel);
        
        EditBorrowerFramePhoneField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBorrowerFramePhoneField.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerFramePhoneField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBorrowerFrameLowerPanel.add(EditBorrowerFramePhoneField);
        
        EditBorrowerFrameButton.setBackground(new java.awt.Color(209, 231, 255));
        EditBorrowerFrameButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        EditBorrowerFrameButton.setForeground(new java.awt.Color(46, 61, 64));
        EditBorrowerFrameButton.setText("Edit Borrower");
        EditBorrowerFrameButton.setBorderPainted(false);
        EditBorrowerFrameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditBorrowerFrameButtonMouseClicked(evt);
            }
        });
        
        EditBorrowerFrameLowerPanel.add(EditBorrowerFrameButton);
        
        EditBorrowerFrame.getContentPane().add(EditBorrowerFrameLowerPanel, java.awt.BorderLayout.CENTER);
        
        EditBorrowerFrame.setVisible(false);

// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Book Management">
        BookManagementPanel.setBackground(new java.awt.Color(184, 216, 216));
        TabbedPane.add("Book Management", BookManagementPanel);
        
        BookManagementPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookManagementPanelMouseClicked(evt);
            }
        });
        
        BookManagementLabel.setFont(new java.awt.Font("Lato Black", 1, 24)); // NOI18N
        BookManagementLabel.setForeground(new java.awt.Color(46, 61, 64));
        BookManagementLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BookManagementLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BookManagementLabel.setText("Book Management");
        
        BooksTableScrollPane.setBackground(new java.awt.Color(228, 240, 252));
        BooksTableScrollPane.setMaximumSize(new java.awt.Dimension(600, 300));
        BooksTableScrollPane.setSize(600, 300);
        BooksTableScrollPane.setMinimumSize(new java.awt.Dimension(600, 300));
        BooksTableScrollPane.setPreferredSize(new java.awt.Dimension(600, 300));
        
        BooksTable.setMaximumSize(new java.awt.Dimension(600, 300));
        BooksTable.setSize(600, 300);
        BooksTable.setMinimumSize(new java.awt.Dimension(600, 300));
        BooksTable.setPreferredSize(new java.awt.Dimension(600, 300));
        
        BooksTable.setBackground(new java.awt.Color(228, 240, 252));
        //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        BooksTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ISBN", "Name", "Author", "Subject"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        BooksTable.getTableHeader().setReorderingAllowed(false);
        BooksTableScrollPane.setViewportView(BooksTable);
        if (BooksTable.getColumnModel().getColumnCount() > 0) {
            BooksTable.getColumnModel().getColumn(0).setResizable(false);
            BooksTable.getColumnModel().getColumn(1).setResizable(false);
            BooksTable.getColumnModel().getColumn(2).setResizable(false);
            BooksTable.getColumnModel().getColumn(3).setResizable(false);
        }
        
        DeleteBookButton.setBackground(new java.awt.Color(209, 231, 255));
        DeleteBookButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        DeleteBookButton.setForeground(new java.awt.Color(46, 61, 64));
        DeleteBookButton.setText("Delete Book");
        DeleteBookButton.setBorderPainted(false);
        DeleteBookButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteBookButtonMouseClicked(evt);
            }
        });
        
        EditBookButton.setBackground(new java.awt.Color(209, 231, 255));
        EditBookButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        EditBookButton.setForeground(new java.awt.Color(46, 61, 64));
        EditBookButton.setText("Edit Book");
        EditBookButton.setBorderPainted(false);
        EditBookButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditBookButtonMouseClicked(evt);
            }
        });
        
        AddBookButton.setBackground(new java.awt.Color(209, 231, 255));
        AddBookButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        AddBookButton.setForeground(new java.awt.Color(46, 61, 64));
        AddBookButton.setText("Add Book");
        AddBookButton.setBorderPainted(false);
        AddBookButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBookButtonMouseClicked(evt);
            }
        });
        
        javax.swing.GroupLayout BookManagementPanelLayout = new javax.swing.GroupLayout(BookManagementPanel);
        BookManagementPanel.setLayout(BookManagementPanelLayout);
        BookManagementPanelLayout.setHorizontalGroup(
                BookManagementPanelLayout.createParallelGroup()
                        .addGroup(BookManagementPanelLayout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(BookManagementLabel)
                        )
                        .addGroup(BookManagementPanelLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(BooksTableScrollPane)
                        )
                        .addGroup(BookManagementPanelLayout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(DeleteBookButton)
                                .addGap(30, 30, 30)
                                .addComponent(AddBookButton)
                                .addGap(30, 30, 30)
                                .addComponent(EditBookButton)
                        )
        );
        BookManagementPanelLayout.setVerticalGroup(
                BookManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookManagementPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(BookManagementLabel)
                                .addGap(50, 50, 50)
                                .addComponent(BooksTableScrollPane)
                                .addGap(30, 30, 30)
                                .addGroup(BookManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(DeleteBookButton)
                                        .addComponent(AddBookButton)
                                        .addComponent(EditBookButton)
                                )
                        )
        );
        
        BooksTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BooksTableMouseClicked(evt);
            }
        });
        
        AddBookFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        AddBookFrame.setTitle("Add Book");
        AddBookFrame.getContentPane().setBackground(new java.awt.Color(228, 240, 252));
        AddBookFrame.setMinimumSize(new java.awt.Dimension(500, 500));
        AddBookFrame.setName("AddBookFrame"); // NOI18N
        AddBookFrame.setResizable(false);
        AddBookFrame.setSize(new java.awt.Dimension(500, 500));
        
        AddBookFrameTitleLabel.setFont(new java.awt.Font("Lato Black", 1, 30)); // NOI18N
        AddBookFrameTitleLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBookFrameTitleLabel.setText("Add Book");
        AddBookFrameTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        AddBookFrameUpperPanel.setBackground(new java.awt.Color(228, 240, 252));
        
        javax.swing.GroupLayout AddBookFrameUpperPanelLayout = new javax.swing.GroupLayout(AddBookFrameUpperPanel);
        AddBookFrameUpperPanel.setLayout(AddBookFrameUpperPanelLayout);
        AddBookFrameUpperPanelLayout.setHorizontalGroup(
                AddBookFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AddBookFrameTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        AddBookFrameUpperPanelLayout.setVerticalGroup(
                AddBookFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddBookFrameUpperPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(AddBookFrameTitleLabel)
                                .addGap(43, 43, 43))
        );
        
        AddBookFrame.getContentPane().add(AddBookFrameUpperPanel, java.awt.BorderLayout.PAGE_START);
        
        AddBookFrameLowerPanel.setBackground(new java.awt.Color(184, 216, 216));
        AddBookFrameLowerPanel.setLayout(new java.awt.GridLayout(5, 2));
        
        AddBookFrameISBNLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBookFrameISBNLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameISBNLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBookFrameISBNLabel.setText("ISBN");
        AddBookFrameLowerPanel.add(AddBookFrameISBNLabel);
        
        AddBookFrameISBNField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBookFrameISBNField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AddBookFrameISBNField.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameLowerPanel.add(AddBookFrameISBNField);
        
        AddBookFrameNameLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBookFrameNameLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBookFrameNameLabel.setText("Title");
        AddBookFrameLowerPanel.add(AddBookFrameNameLabel);
        
        AddBookFrameNameField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBookFrameNameField.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameNameField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBookFrameLowerPanel.add(AddBookFrameNameField);
        
        AddBookFrameAuthorLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBookFrameAuthorLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameAuthorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBookFrameAuthorLabel.setText("Author");
        AddBookFrameLowerPanel.add(AddBookFrameAuthorLabel);
        
        AddBookFrameAuthorField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBookFrameAuthorField.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameAuthorField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBookFrameLowerPanel.add(AddBookFrameAuthorField);
        
        AddBookFrameSubjectLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBookFrameSubjectLabel.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameSubjectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBookFrameSubjectLabel.setText("Subject");
        AddBookFrameLowerPanel.add(AddBookFrameSubjectLabel);
        
        AddBookFrameSubjectField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AddBookFrameSubjectField.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameSubjectField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddBookFrameLowerPanel.add(AddBookFrameSubjectField);
        
        AddBookFrameAddID.setBackground(new java.awt.Color(209, 231, 255));
        AddBookFrameAddID.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        AddBookFrameAddID.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameAddID.setText("Add ID");
        AddBookFrameAddID.setBorderPainted(false);
        AddBookFrameAddID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBookFrameAddIDButtonMouseClicked(evt);
            }
        });
        
        AddBookFrameAddButton.setBackground(new java.awt.Color(209, 231, 255));
        AddBookFrameAddButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        AddBookFrameAddButton.setForeground(new java.awt.Color(46, 61, 64));
        AddBookFrameAddButton.setText("Add Book");
        AddBookFrameAddButton.setBorderPainted(false);
        AddBookFrameAddButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBookFrameAddButtonMouseClicked(evt);
            }
        });
        
        AddBookFrameLowerPanel.add(AddBookFrameAddID);
        AddBookFrameLowerPanel.add(AddBookFrameAddButton);
        
        AddBookFrame.getContentPane().add(AddBookFrameLowerPanel, java.awt.BorderLayout.CENTER);
        
        AddBookFrame.setVisible(false);
        
        EditBookFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        EditBookFrame.setTitle("Edit Book");
        EditBookFrame.getContentPane().setBackground(new java.awt.Color(228, 240, 252));
        EditBookFrame.setMinimumSize(new java.awt.Dimension(500, 500));
        EditBookFrame.setName("EditBookFrame"); // NOI18N
        EditBookFrame.setResizable(false);
        EditBookFrame.setSize(new java.awt.Dimension(500, 500));
        
        EditBookFrameTitleLabel.setFont(new java.awt.Font("Lato Black", 1, 30)); // NOI18N
        EditBookFrameTitleLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBookFrameTitleLabel.setText("Edit Book");
        EditBookFrameTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        EditBookFrameUpperPanel.setBackground(new java.awt.Color(228, 240, 252));
        
        javax.swing.GroupLayout EditBookFrameUpperPanelLayout = new javax.swing.GroupLayout(EditBookFrameUpperPanel);
        EditBookFrameUpperPanel.setLayout(EditBookFrameUpperPanelLayout);
        EditBookFrameUpperPanelLayout.setHorizontalGroup(
                EditBookFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(EditBookFrameTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        EditBookFrameUpperPanelLayout.setVerticalGroup(
                EditBookFrameUpperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditBookFrameUpperPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(EditBookFrameTitleLabel)
                                .addGap(43, 43, 43))
        );
        
        EditBookFrame.getContentPane().add(EditBookFrameUpperPanel, java.awt.BorderLayout.PAGE_START);
        
        EditBookFrameLowerPanel.setBackground(new java.awt.Color(184, 216, 216));
        EditBookFrameLowerPanel.setLayout(new java.awt.GridLayout(5, 2));
        
        EditBookFrameISBNLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBookFrameISBNLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameISBNLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBookFrameISBNLabel.setText("ISBN");
        EditBookFrameLowerPanel.add(EditBookFrameISBNLabel);
        
        EditBookFrameISBNField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBookFrameISBNField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        EditBookFrameISBNField.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameLowerPanel.add(EditBookFrameISBNField);
        
        EditBookFrameNameLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBookFrameNameLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBookFrameNameLabel.setText("Title");
        EditBookFrameLowerPanel.add(EditBookFrameNameLabel);
        
        EditBookFrameNameField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBookFrameNameField.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameNameField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBookFrameLowerPanel.add(EditBookFrameNameField);
        
        EditBookFrameAuthorLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBookFrameAuthorLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameAuthorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBookFrameAuthorLabel.setText("Author");
        EditBookFrameLowerPanel.add(EditBookFrameAuthorLabel);
        
        EditBookFrameAuthorField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBookFrameAuthorField.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameAuthorField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBookFrameLowerPanel.add(EditBookFrameAuthorField);
        
        EditBookFrameSubjectLabel.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBookFrameSubjectLabel.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameSubjectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBookFrameSubjectLabel.setText("Subject");
        EditBookFrameLowerPanel.add(EditBookFrameSubjectLabel);
        
        EditBookFrameSubjectField.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EditBookFrameSubjectField.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameSubjectField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditBookFrameLowerPanel.add(EditBookFrameSubjectField);
        
        EditBookFrameAddID.setBackground(new java.awt.Color(209, 231, 255));
        EditBookFrameAddID.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        EditBookFrameAddID.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameAddID.setText("Add ID");
        EditBookFrameAddID.setBorderPainted(false);
        EditBookFrameAddID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditBookFrameAddIDButtonMouseClicked(evt);
            }
        });
        
        EditBookFrameSaveButton.setBackground(new java.awt.Color(209, 231, 255));
        EditBookFrameSaveButton.setFont(new java.awt.Font("Lato Black", 1, 16)); // NOI18N
        EditBookFrameSaveButton.setForeground(new java.awt.Color(46, 61, 64));
        EditBookFrameSaveButton.setText("Save Book");
        EditBookFrameSaveButton.setBorderPainted(false);
        EditBookFrameSaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditBookFrameSaveButtonMouseClicked(evt);
            }
        });
        
        EditBookFrameLowerPanel.add(EditBookFrameAddID);
        EditBookFrameLowerPanel.add(EditBookFrameSaveButton);
        
        EditBookFrame.getContentPane().add(EditBookFrameLowerPanel, java.awt.BorderLayout.CENTER);
        
        EditBookFrame.setVisible(false);

// </editor-fold>
        
        pack();
    }
    private void EditBookFrameSaveButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        String isbn = EditBookFrameISBNField.getText();
        String name = EditBookFrameNameField.getText();
        String author = EditBookFrameAuthorField.getText();
        String subject = EditBookFrameSubjectField.getText();
         
        if("".equals(isbn) || "".equals(name) || "".equals(author) || "".equals(subject))
        {
            JOptionPane.showMessageDialog(this, "Some fields are empty.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
            EditBookFrameISBNField.setText("");
            EditBookFrameNameField.setText("");
            EditBookFrameAuthorField.setText("");
            EditBookFrameSubjectField.setText("");
            return;
        }
        Book book = null;
                
        for (int i = 0; i < library.books.size(); i++) 
        {
            if(library.books.get(i).isbn.equals(oldIsbn))
            {
                book = library.books.get(i);
                break;
            }
        }
        book.author = author;
        book.isbn = isbn;
        book.name = name;
        book.subject = subject;
        
        while(oldIds > ids)
        {
            book.addIDs(oldIds - ids);
        }
        
        JOptionPane.showMessageDialog(this, "Book edited!",
"", JOptionPane.PLAIN_MESSAGE);
        EditBookFrameISBNField.setText("");
        EditBookFrameNameField.setText("");
        EditBookFrameAuthorField.setText("");
        EditBookFrameSubjectField.setText("");
        EditBookFrame.setVisible(false);
    }
    
    private void EditBookFrameAddIDButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        ids++;
    }
    
    private void EditBookButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        EditBookFrame.setVisible(true);
        int row = BooksTable.getSelectedRow();
        
        boolean result = false;
        
        String isbn = (String)BooksTable.getValueAt(row, 0);
        String name = (String)BooksTable.getValueAt(row, 1);
        String author = (String)BooksTable.getValueAt(row, 2);
        String subject = (String)BooksTable.getValueAt(row, 3);
        
        oldIsbn = isbn;
        
        Book book = null;
                
        for (int i = 0; i < library.books.size(); i++) 
        {
            if(library.books.get(i).isbn.equals(isbn))
            {
                book = library.books.get(i);
                break;
            }
        }
        
        ids = book.ids.size();
        oldIds = ids;
        
        EditBookFrameISBNField.setText(isbn);
        EditBookFrameNameField.setText(name);
        EditBookFrameAuthorField.setText(author);
        EditBookFrameSubjectField.setText(subject);
    }
    
    private void AddBookFrameAddButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
         String isbn = AddBookFrameISBNField.getText();
         String name = AddBookFrameNameField.getText();
         String author = AddBookFrameAuthorField.getText();
         String subject = AddBookFrameSubjectField.getText();
         
         
         if("".equals(isbn) || "".equals(name) || "".equals(author) || "".equals(subject))
         {
            JOptionPane.showMessageDialog(this, "Some fields are empty.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
         }
         if(ids == 0)
         {
            JOptionPane.showMessageDialog(this, "Add atleast one id.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
         }
         
         Book a = new Book(isbn, name, author, subject);
         a.addIDs(ids);
         JOptionPane.showMessageDialog(this, "Book added!",
"", JOptionPane.PLAIN_MESSAGE);
        AddBookFrameISBNField.setText("");
        AddBookFrameNameField.setText("");
        AddBookFrameAuthorField.setText("");
        AddBookFrameSubjectField.setText("");
        ids = 0;
        AddBookFrame.setVisible(false);
    }
    
    private void AddBookFrameAddIDButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        String isbn = AddBookFrameISBNField.getText();
        String name = AddBookFrameNameField.getText();
        String author = AddBookFrameAuthorField.getText();
        String subject = AddBookFrameSubjectField.getText();
        
        if(!"".equals(isbn) && !"".equals(name) && !"".equals(author) && !"".equals(subject))
        {
            ids++;  
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Some fields are empty.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void BookManagementPanelMouseClicked(java.awt.event.MouseEvent evt)
    {
        DeleteBookButton.setEnabled(false);
        EditBookButton.setEnabled(false);
    }
    
    private void BooksTableMouseClicked(java.awt.event.MouseEvent evt)
    {
        DeleteBookButton.setEnabled(true);
        EditBookButton.setEnabled(true);
    }
    
    private void DeleteBookButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        int row = BooksTable.getSelectedRow();
        
        boolean result = false;
        
        String isbn = (String)BooksTable.getValueAt(row, 0);
        
        Book book = null;
                
        for (int i = 0; i < library.books.size(); i++) 
        {
            if(library.books.get(i).isbn.equals(isbn))
            {
                book = library.books.get(i);
                break;
            }
        }
        
        result = librarian.removeBook(book);
        
        if(result)
        {
            JOptionPane.showMessageDialog(this, "Book has been removed.",
"", JOptionPane.PLAIN_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Book is on loan, cannot be removed.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void AddBookButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        AddBookFrame.setVisible(true);
    }
    
    private void AddBorrowerFrameButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        String username = AddBorrowerFrameUsernameField.getText();
        String password = AddBorrowerFramePasswordField.getText();
        String name = AddBorrowerFrameNameField.getText();
        String address = AddBorrowerFrameAddressField.getText();
        long phone = 0;
        if("".equals(username) || "".equals(password) || "".equals(name) || "".equals(address))
        {
            JOptionPane.showMessageDialog(this, "Please fill all fields.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            phone = Long.parseLong(AddBorrowerFramePhoneField.getText());
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Please enter valid phone number.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Borrower a = new Borrower(username, password, name, address, phone, 0);
        library.users.add(a);
        JOptionPane.showMessageDialog(this, "Borrower added!",
"", JOptionPane.PLAIN_MESSAGE);
        AddBorrowerFrameUsernameField.setText("");
        AddBorrowerFramePasswordField.setText("");
        AddBorrowerFrameNameField.setText("");
        AddBorrowerFrameAddressField.setText("");
        AddBorrowerFramePhoneField.setText("");
        AddBorrowerFrame.setVisible(false);
    }
    
    private void EditBorrowerButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        EditBorrowerFrame.setVisible(true);
    }
    
    private void AddBorrowerButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        AddBorrowerFrame.setVisible(true);
    }
    
    private void EditBorrowerFrameButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        String username = EditBorrowerFrameUsernameField.getText();
        String address = EditBorrowerFrameAddressField.getText();
        long phone = 0;
        if("".equals(username) && "".equals(address) && EditBorrowerFramePhoneField.getText().equals(""))
        {
            EditBorrowerFrame.setVisible(false);
            return;
        }
        if("".equals(username))
        {
            JOptionPane.showMessageDialog(this, "Please fill username field.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
            EditBorrowerFrame.setVisible(false);
            return;
        }
        try
        {
            phone = Long.parseLong(EditBorrowerFramePhoneField.getText());
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Please enter valid phone number.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        User a = null;
        
        for(int i = 0;i < library.users.size(); i++)
        {
            if(username.equals(library.users.get(i).username))
            {
                a = library.users.get(i);
                break;
            }
        }
        
        if(a == null)
        {
            JOptionPane.showMessageDialog(this, "User doesn't exist or cannot be edited.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
            EditBorrowerFrame.setVisible(false);
            return;
        }
        else
        {
            a.phone = phone;
            if(!"".equals(address))
            {
                a.address = address;
            }
        }
        
        JOptionPane.showMessageDialog(this, "User edited!",
"", JOptionPane.PLAIN_MESSAGE);
        EditBorrowerFrameUsernameField.setText("");
        EditBorrowerFrameAddressField.setText("");
        EditBorrowerFramePhoneField.setText("");
        EditBorrowerFrame.setVisible(false);
    }
    
    private void FineDueTableMouseClicked(java.awt.event.MouseEvent evt)
    {
        RecordFineButton.setEnabled(true);
    }
    
    private void RecordFineButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        int row = FineDueTable.getSelectedRow();
        
        int fine = (Integer)FineDueTable.getValueAt(row, 1);
        
        User user = null;
                
        for (int i = 0; i < library.users.size(); i++) 
        {
            if(library.users.get(i).username.equals((String)FineDueTable.getValueAt(row, 0)))
            {
                user = library.users.get(i);
                break;
            }
        }
        
        librarian.recordFine(-fine, user);
        
        JOptionPane.showMessageDialog(this, "Fine has been recorded.",
"", JOptionPane.PLAIN_MESSAGE);
        
        ArrayList<String> Username = new ArrayList<>();
            ArrayList<Integer> Fine = new ArrayList<>();
        
            //library.users.get(0).finePaid = -1000;
            
            for (int i = 0; i < library.users.size(); i++) {
                if(library.users.get(i).finePaid < 0)
                {
                    Username.add(library.users.get(i).username);
                    Fine.add(library.users.get(i).finePaid);
                }
            }
            
            FineDueTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "Username", "Fine Due"}, 0);
            FineDueTable.setModel(tableModel);
            FineDueTable.getTableHeader().setReorderingAllowed(false);
            FineDueTableScrollPane.setViewportView(FineDueTable);
            if (FineDueTable.getColumnModel().getColumnCount() > 0) {
                FineDueTable.getColumnModel().getColumn(0).setResizable(false);
                FineDueTable.getColumnModel().getColumn(1).setResizable(true);
            }

            for(int i = 0; i < Username.size(); i++)
            {
                Object [] data = {Username.get(i), Fine.get(i)};
                tableModel.addRow(data);
            }

            FinePanel.add(FineDueTableScrollPane);
            FineDueTableScrollPane.setVisible(true);
            
            javax.swing.GroupLayout FinePanelLayout = new javax.swing.GroupLayout(FinePanel);
            FinePanel.setLayout(FinePanelLayout);
            FinePanelLayout.setHorizontalGroup(
                    FinePanelLayout.createParallelGroup()
                            .addGroup(FinePanelLayout.createSequentialGroup()
                                    .addGap(350, 350, 350)
                                    .addComponent(FineDueLabel)
                            )
                            .addGroup(FinePanelLayout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(FineDueTableScrollPane)
                            )
                            .addGroup(FinePanelLayout.createSequentialGroup()
                                    .addGap(330, 330, 330)
                                    .addComponent(RecordFineButton)
                            )
            );
            FinePanelLayout.setVerticalGroup(
                    FinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FinePanelLayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(FineDueLabel)
                                    .addGap(70, 70, 70)
                                    .addComponent(FineDueTableScrollPane)
                                    .addGap(30, 30, 30)
                                    .addComponent(RecordFineButton)
                            )
            );
    }
    
    private void CheckInButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        int row = CheckInTable.getSelectedRow();
        Loan loanObj = new Loan((Integer)CheckInTable.getValueAt(row, 0), (String)CheckInTable.getValueAt(row, 1), (String)CheckInTable.getValueAt(row, 2), (Date)CheckInTable.getValueAt(row, 3), (Date)CheckInTable.getValueAt(row, 4));
        
        librarian.checkIn(loanObj);
        
        JOptionPane.showMessageDialog(this, "Book has been checked in.",
"", JOptionPane.PLAIN_MESSAGE);
        
        // <editor-fold defaultstate="collapsed" desc="CheckPanel Layout">
        ArrayList<Book> Books = new ArrayList<>();
            ArrayList<String> Users = new ArrayList<>();
            Book temp;
            
            for(int i = 0; i < library.books.size(); i++)
            {
                temp = library.books.get(i);
                if(temp.requestList.size() > 0)
                {
                    for (int j = 0; j < temp.requestList.size(); j++) 
                    {
                        Users.add(temp.requestList.get(j));
                        Books.add(temp);
                    }
                }
            }
        
            CheckOutTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "ISBN", "Book Name", "Book Author", "Username of Requestee"}, 0);
            CheckOutTable.setModel(tableModel);
            CheckOutTable.getTableHeader().setReorderingAllowed(false);
            CheckOutTableScrollPane.setViewportView(CheckOutTable);
            if (CheckOutTable.getColumnModel().getColumnCount() > 0) {
                CheckOutTable.getColumnModel().getColumn(0).setResizable(false);
                CheckOutTable.getColumnModel().getColumn(1).setResizable(true);
                CheckOutTable.getColumnModel().getColumn(2).setResizable(true);
                CheckOutTable.getColumnModel().getColumn(3).setResizable(true);
            }

            for(int i = 0; i < Users.size(); i++)
            {
                Object [] data = {Books.get(i).isbn, Books.get(i).name, Books.get(i).author, Users.get(i)};
                tableModel.addRow(data);
            }

            CheckPanel.add(CheckOutTableScrollPane);
            CheckOutTableScrollPane.setVisible(true);    
        
        ArrayList<Loan> Loans = new ArrayList<>();
            
            for(int i = 0; i < library.users.size(); i++)
            {
                for(int j = 0; j < library.users.get(i).loanedBooks.size(); j++)
                {
                    Loans.add(library.users.get(i).loanedBooks.get(j));
                }
            }
            
            CheckInTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "Book ID", "ISBN", "Username of Borrower", "Issue Date", "Return Date"}, 0);
            CheckInTable.setModel(tableModel);
            CheckInTable.getTableHeader().setReorderingAllowed(false);
            CheckInTableScrollPane.setViewportView(CheckInTable);
            if (CheckInTable.getColumnModel().getColumnCount() > 0) {
                CheckInTable.getColumnModel().getColumn(0).setResizable(false);
                CheckInTable.getColumnModel().getColumn(1).setResizable(true);
                CheckInTable.getColumnModel().getColumn(2).setResizable(true);
                CheckInTable.getColumnModel().getColumn(3).setResizable(true);
                CheckInTable.getColumnModel().getColumn(4).setResizable(true);
            }
            
            for(int i = 0; i < Loans.size(); i++)
            {
                Object [] data = {Loans.get(i).bookId, Loans.get(i).isbn, Loans.get(i).username, Loans.get(i).issD, Loans.get(i).retD};
                tableModel.addRow(data);
            }
            
            CheckPanel.add(CheckInTableScrollPane);
            CheckInTableScrollPane.setVisible(true);
            
            javax.swing.GroupLayout CheckPanelLayout = new javax.swing.GroupLayout(CheckPanel);
            CheckPanel.setLayout(CheckPanelLayout);
            CheckPanelLayout.setHorizontalGroup(
                    CheckPanelLayout.createParallelGroup()
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(300, 300, 300)
                                    .addComponent(CheckOutLabel)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(CheckOutTableScrollPane)
                                    .addGap(100, 100, 100)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(335, 335, 335)
                                    .addComponent(CheckOutButton)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(220, 220, 220)
                                    .addComponent(LoanedOutBooksLabel)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(CheckInTableScrollPane)
                                    .addGap(100, 100, 100)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(280, 280, 280)
                                    .addComponent(RenewButton)
                                    .addGap(60, 60, 60)
                                    .addComponent(CheckInButton)
                            )
            );
            CheckPanelLayout.setVerticalGroup(
                    CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CheckPanelLayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(CheckOutLabel)
                                    .addGap(25, 25, 25)
                                    .addComponent(CheckOutTableScrollPane)
                                    .addGap(30, 30, 30)
                                    .addComponent(CheckOutButton)
                                    .addGap(30, 30, 30)
                                    .addComponent(LoanedOutBooksLabel)
                                    .addGap(25, 25, 25)
                                    .addComponent(CheckInTableScrollPane)
                                    .addGap(30, 30, 30)
                                    .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(RenewButton)
                                    .addComponent(CheckInButton)
                                    )
                            )
            );

// </editor-fold>
    }
    
    private void RenewButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        int row = CheckInTable.getSelectedRow();
        
        Loan loanObj = null;
        
        for(int i = 0; i < library.loans.size(); i++)
        {
            if(library.loans.get(i).onLoan && (Integer)CheckInTable.getValueAt(row, 0) == library.loans.get(i).bookId)
            {
                if(((String)CheckInTable.getValueAt(row, 1)).equals(library.loans.get(i).isbn))
                {
                    if(((String)CheckInTable.getValueAt(row, 2)).equals(library.loans.get(i).username))
                    {
                        if(((Date)CheckInTable.getValueAt(row, 3)).equals(library.loans.get(i).issD))
                        {
                            if(((Date)CheckInTable.getValueAt(row, 4)).equals(library.loans.get(i).retD))
                            {
                                loanObj = library.loans.get(i);
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        boolean result = false;
        
        result = librarian.renew(loanObj);
        
        if(!result)
        {
            JOptionPane.showMessageDialog(this, "Book is on hold by other users, cannot be renewed.",  
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Loan has been successfully renewed for an additional 7 days.",
"", JOptionPane.PLAIN_MESSAGE);
            // <editor-fold defaultstate="collapsed" desc="CheckPanel Layout">
            ArrayList<Loan> Loans = new ArrayList<>();
            
            for(int i = 0; i < library.users.size(); i++)
            {
                for(int j = 0; j < library.users.get(i).loanedBooks.size(); j++)
                {
                    Loans.add(library.users.get(i).loanedBooks.get(j));
                }
            }
            
            CheckInTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "Book ID", "ISBN", "Username of Borrower", "Issue Date", "Return Date"}, 0);
            CheckInTable.setModel(tableModel);
            CheckInTable.getTableHeader().setReorderingAllowed(false);
            CheckInTableScrollPane.setViewportView(CheckInTable);
            if (CheckInTable.getColumnModel().getColumnCount() > 0) {
                CheckInTable.getColumnModel().getColumn(0).setResizable(false);
                CheckInTable.getColumnModel().getColumn(1).setResizable(true);
                CheckInTable.getColumnModel().getColumn(2).setResizable(true);
                CheckInTable.getColumnModel().getColumn(3).setResizable(true);
                CheckInTable.getColumnModel().getColumn(4).setResizable(true);
            }
            
            for(int i = 0; i < Loans.size(); i++)
            {
                Object [] data = {Loans.get(i).bookId, Loans.get(i).isbn, Loans.get(i).username, Loans.get(i).issD, Loans.get(i).retD};
                tableModel.addRow(data);
            }
            
            CheckPanel.add(CheckInTableScrollPane);
            CheckInTableScrollPane.setVisible(true);
            
            javax.swing.GroupLayout CheckPanelLayout = new javax.swing.GroupLayout(CheckPanel);
            CheckPanel.setLayout(CheckPanelLayout);
            CheckPanelLayout.setHorizontalGroup(
                    CheckPanelLayout.createParallelGroup()
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(300, 300, 300)
                                    .addComponent(CheckOutLabel)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(CheckOutTableScrollPane)
                                    .addGap(100, 100, 100)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(335, 335, 335)
                                    .addComponent(CheckOutButton)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(220, 220, 220)
                                    .addComponent(LoanedOutBooksLabel)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(CheckInTableScrollPane)
                                    .addGap(100, 100, 100)
                            )
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                    .addGap(280, 280, 280)
                                    .addComponent(RenewButton)
                                    .addGap(60, 60, 60)
                                    .addComponent(CheckInButton)
                            )
            );
            CheckPanelLayout.setVerticalGroup(
                    CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CheckPanelLayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(CheckOutLabel)
                                    .addGap(25, 25, 25)
                                    .addComponent(CheckOutTableScrollPane)
                                    .addGap(30, 30, 30)
                                    .addComponent(CheckOutButton)
                                    .addGap(30, 30, 30)
                                    .addComponent(LoanedOutBooksLabel)
                                    .addGap(25, 25, 25)
                                    .addComponent(CheckInTableScrollPane)
                                    .addGap(30, 30, 30)
                                    .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(RenewButton)
                                    .addComponent(CheckInButton)
                                    )
                            )
            );

// </editor-fold>
        }
    }
    
    private void CheckOutButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        int row = CheckOutTable.getSelectedRow();
        String username = (String)CheckOutTable.getValueAt(row, 3);
        String isbn = (String)CheckOutTable.getValueAt(row, 0);
        Book book = null;
        User user = null;
        
        for(int i = 0; i < library.books.size(); i++)
        {
            if(isbn.equals(library.books.get(i).isbn))
            {
                book = library.books.get(i);
                break;
            }
        }
        
        for (int i = 0; i < library.users.size(); i++) 
        {
            if(username.equals(library.users.get(i).username))
            {
                user = library.users.get(i);
                break;
            }
        }
        
        librarian.checkOut(user, book);
    }
    
    private void CheckInTableMouseClicked(java.awt.event.MouseEvent evt)
    {
        CheckInButton.setEnabled(true);
        RenewButton.setEnabled(true);
        CheckOutButton.setEnabled(false);
    }
    
    private void CheckOutTableMouseClicked(java.awt.event.MouseEvent evt)
    {
        CheckOutButton.setEnabled(true);
        CheckInButton.setEnabled(false);
        RenewButton.setEnabled(false);
    }
    
    private void CheckPanelMouseClicked(java.awt.event.MouseEvent evt) {                                                
        CheckOutButton.setEnabled(false);
        CheckInButton.setEnabled(false);
        RenewButton.setEnabled(false);
    } 
    
    void giveValues(String username, Library library)
    {
        this.username = username;
        this.library = library;
        
        for(int i = 0; i < library.users.size(); i++)
        {
            if(username.equals(library.users.get(i).username))
            {
                librarian = (Librarian)library.users.get(i);
                break;
            }
        }
        
        SearchWelcomeLabel.setText("Welcome to LMS, " + username + "!");
    }
    
    private void TabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {                                          
        int index = TabbedPane.getSelectedIndex();
        
        if (index == 1)
        {
            BorrowedUserLabel.setText("Books borrowed by " + username);
            
            ArrayList<Loan> BorrowedResults = librarian.loanedBooks;
        
            BorrowedTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "Book ID","ISBN", "Issue Date", "Return Date"}, 0);
            BorrowedTable.setModel(tableModel);
            BorrowedTable.getTableHeader().setReorderingAllowed(false);
            BorrowedTableScrollPane.setViewportView(BorrowedTable);
            if (BorrowedTable.getColumnModel().getColumnCount() > 0) {
                BorrowedTable.getColumnModel().getColumn(0).setResizable(false);
                BorrowedTable.getColumnModel().getColumn(1).setResizable(true);
                BorrowedTable.getColumnModel().getColumn(2).setResizable(true);
                BorrowedTable.getColumnModel().getColumn(3).setResizable(true);
            }

            for(int i = 0; i < BorrowedResults.size(); i++)
            {
                Object [] data = {BorrowedResults.get(i).bookId, BorrowedResults.get(i).isbn, BorrowedResults.get(i).issD, BorrowedResults.get(i).retD};
                tableModel.addRow(data);
            }

            BorrowedPanel.add(BorrowedTableScrollPane);
            BorrowedTableScrollPane.setVisible(true);
        }
        else if(index == 2)
        {
            CheckOutButton.setEnabled(false);
            CheckInButton.setEnabled(false);
            RequestButton.setEnabled(false);

            ArrayList<Book> Books = new ArrayList<>();
            ArrayList<String> Users = new ArrayList<>();
            ArrayList<Loan> Loans = new ArrayList<>();
            Book temp;
            
            for(int i = 0; i < library.books.size(); i++)
            {
                temp = library.books.get(i);
                if(temp.requestList.size() > 0)
                {
                    for (int j = 0; j < temp.requestList.size(); j++) 
                    {
                        Users.add(temp.requestList.get(j));
                        Books.add(temp);
                    }
                }
            }
        
            CheckOutTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "ISBN", "Book Name", "Book Author", "Username of Requestee"}, 0);
            CheckOutTable.setModel(tableModel);
            CheckOutTable.getTableHeader().setReorderingAllowed(false);
            CheckOutTableScrollPane.setViewportView(CheckOutTable);
            if (CheckOutTable.getColumnModel().getColumnCount() > 0) {
                CheckOutTable.getColumnModel().getColumn(0).setResizable(false);
                CheckOutTable.getColumnModel().getColumn(1).setResizable(true);
                CheckOutTable.getColumnModel().getColumn(2).setResizable(true);
                CheckOutTable.getColumnModel().getColumn(3).setResizable(true);
            }

            for(int i = 0; i < Users.size(); i++)
            {
                Object [] data = {Books.get(i).isbn, Books.get(i).name, Books.get(i).author, Users.get(i)};
                tableModel.addRow(data);
            }

            CheckPanel.add(CheckOutTableScrollPane);
            CheckOutTableScrollPane.setVisible(true);
            
            for(int i = 0; i < library.users.size(); i++)
            {
                for(int j = 0; j < library.users.get(i).loanedBooks.size(); j++)
                {
                    Loans.add(library.users.get(i).loanedBooks.get(j));
                }
            }
            
            CheckInTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "Book ID", "ISBN", "Username of Borrower", "Issue Date", "Return Date"}, 0);
            CheckInTable.setModel(tableModel);
            CheckInTable.getTableHeader().setReorderingAllowed(false);
            CheckInTableScrollPane.setViewportView(CheckInTable);
            if (CheckInTable.getColumnModel().getColumnCount() > 0) {
                CheckInTable.getColumnModel().getColumn(0).setResizable(false);
                CheckInTable.getColumnModel().getColumn(1).setResizable(true);
                CheckInTable.getColumnModel().getColumn(2).setResizable(true);
                CheckInTable.getColumnModel().getColumn(3).setResizable(true);
                CheckInTable.getColumnModel().getColumn(4).setResizable(true);
            }
            
            for(int i = 0; i < Loans.size(); i++)
            {
                Object [] data = {Loans.get(i).bookId, Loans.get(i).isbn, Loans.get(i).username, Loans.get(i).issD, Loans.get(i).retD};
                tableModel.addRow(data);
            }
            
            CheckPanel.add(CheckInTableScrollPane);
            CheckInTableScrollPane.setVisible(true);
            
            // <editor-fold defaultstate="collapsed" desc="CheckPanel Layout">
        javax.swing.GroupLayout CheckPanelLayout = new javax.swing.GroupLayout(CheckPanel);
        CheckPanel.setLayout(CheckPanelLayout);
        CheckPanelLayout.setHorizontalGroup(
                CheckPanelLayout.createParallelGroup()
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addComponent(CheckOutLabel)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(CheckOutTableScrollPane)
                                .addGap(100, 100, 100)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(335, 335, 335)
                                .addComponent(CheckOutButton)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(LoanedOutBooksLabel)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(CheckInTableScrollPane)
                                .addGap(100, 100, 100)
                        )
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(RenewButton)
                                .addGap(60, 60, 60)
                                .addComponent(CheckInButton)
                        )
        );
        CheckPanelLayout.setVerticalGroup(
                CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CheckPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(CheckOutLabel)
                                .addGap(25, 25, 25)
                                .addComponent(CheckOutTableScrollPane)
                                .addGap(30, 30, 30)
                                .addComponent(CheckOutButton)
                                .addGap(30, 30, 30)
                                .addComponent(LoanedOutBooksLabel)
                                .addGap(25, 25, 25)
                                .addComponent(CheckInTableScrollPane)
                                .addGap(30, 30, 30)
                                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(RenewButton)
                                .addComponent(CheckInButton)
                                )
                        )
        );

// </editor-fold>
        }
        else if (index == 3)
        {
            RecordFineButton.setEnabled(false);
            
            ArrayList<String> Username = new ArrayList<>();
            ArrayList<Integer> Fine = new ArrayList<>();
        
            //library.users.get(0).finePaid = -1000;
            
            for (int i = 0; i < library.users.size(); i++) {
                if(library.users.get(i).finePaid < 0)
                {
                    Username.add(library.users.get(i).username);
                    Fine.add(library.users.get(i).finePaid);
                }
            }
            
            FineDueTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "Username", "Fine Due"}, 0);
            FineDueTable.setModel(tableModel);
            FineDueTable.getTableHeader().setReorderingAllowed(false);
            FineDueTableScrollPane.setViewportView(FineDueTable);
            if (FineDueTable.getColumnModel().getColumnCount() > 0) {
                FineDueTable.getColumnModel().getColumn(0).setResizable(false);
                FineDueTable.getColumnModel().getColumn(1).setResizable(true);
            }

            for(int i = 0; i < Username.size(); i++)
            {
                Object [] data = {Username.get(i), Fine.get(i)};
                tableModel.addRow(data);
            }

            FinePanel.add(FineDueTableScrollPane);
            FineDueTableScrollPane.setVisible(true);
            
            javax.swing.GroupLayout FinePanelLayout = new javax.swing.GroupLayout(FinePanel);
            FinePanel.setLayout(FinePanelLayout);
            FinePanelLayout.setHorizontalGroup(
                    FinePanelLayout.createParallelGroup()
                            .addGroup(FinePanelLayout.createSequentialGroup()
                                    .addGap(350, 350, 350)
                                    .addComponent(FineDueLabel)
                            )
                            .addGroup(FinePanelLayout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(FineDueTableScrollPane)
                            )
                            .addGroup(FinePanelLayout.createSequentialGroup()
                                    .addGap(330, 330, 330)
                                    .addComponent(RecordFineButton)
                            )
            );
            FinePanelLayout.setVerticalGroup(
                    FinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FinePanelLayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(FineDueLabel)
                                    .addGap(70, 70, 70)
                                    .addComponent(FineDueTableScrollPane)
                                    .addGap(30, 30, 30)
                                    .addComponent(RecordFineButton)
                            )
            );
        }
        else if (index == 5)
        {
            DeleteBookButton.setEnabled(false);
            EditBookButton.setEnabled(false);
            
            ArrayList<Book> Books = Library.books;
            
            BooksTable.setBackground(new java.awt.Color(228, 240, 252));
            //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
            DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                    "ISBN", "Name", "Author", "Subject"}, 0);
            BooksTable.setModel(tableModel);
            BooksTable.getTableHeader().setReorderingAllowed(false);
            BooksTableScrollPane.setViewportView(BooksTable);
            if (BooksTable.getColumnModel().getColumnCount() > 0) {
                BooksTable.getColumnModel().getColumn(0).setResizable(true);
                BooksTable.getColumnModel().getColumn(1).setResizable(true);
                BooksTable.getColumnModel().getColumn(2).setResizable(true);
                BooksTable.getColumnModel().getColumn(3).setResizable(true);
            }

            for(int i = 0; i < Books.size(); i++)
            {
                Object [] data = {Books.get(i).isbn, Books.get(i).name, Books.get(i).author, Books.get(i).subject};
                tableModel.addRow(data);
            }

            BookManagementPanel.add(BooksTableScrollPane);
            BooksTableScrollPane.setVisible(true);
            
            javax.swing.GroupLayout BookManagementPanelLayout = new javax.swing.GroupLayout(BookManagementPanel);
            BookManagementPanel.setLayout(BookManagementPanelLayout);
            BookManagementPanelLayout.setHorizontalGroup(
                    BookManagementPanelLayout.createParallelGroup()
                            .addGroup(BookManagementPanelLayout.createSequentialGroup()
                                    .addGap(280, 280, 280)
                                    .addComponent(BookManagementLabel)
                            )
                            .addGroup(BookManagementPanelLayout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(BooksTableScrollPane)
                            )
                            .addGroup(BookManagementPanelLayout.createSequentialGroup()
                                    .addGap(180, 180, 180)
                                    .addComponent(DeleteBookButton)
                                    .addGap(30, 30, 30)
                                    .addComponent(AddBookButton)
                                    .addGap(30, 30, 30)
                                    .addComponent(EditBookButton)
                            )
            );
            BookManagementPanelLayout.setVerticalGroup(
                    BookManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookManagementPanelLayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(BookManagementLabel)
                                    .addGap(50, 50, 50)
                                    .addComponent(BooksTableScrollPane)
                                    .addGap(30, 30, 30)
                                    .addGroup(BookManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(DeleteBookButton)
                                            .addComponent(AddBookButton)
                                            .addComponent(EditBookButton)
                                    )
                            )
            );
        }
    }  
    
    private void SearchButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        RequestButton.setEnabled(false);
        
        ArrayList<Book> SearchResults = null;
        
        if(ByTitleRadioButton.isSelected())
        {
            SearchResults = librarian.searchBookbyName(SearchField.getText());
        }
        else if(ByAuthorRadioButton.isSelected())
        {
            SearchResults = librarian.searchBookbyAuthor(SearchField.getText());
        }
        else if(BySubjectRadioButton.isSelected())
        {
            SearchResults = librarian.searchBookbySubject(SearchField.getText());
        }
        
        SearchResultsTable.setBackground(new java.awt.Color(228, 240, 252));
        //SearchResultsTable.setFont(new java.awt.Font("Lato Black", 1, 12)); // NOI18N
        DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(new String [] {
                "ISBN", "Title", "Author", "Subject"}, 0);
        SearchResultsTable.setModel(tableModel);
        SearchResultsTable.getTableHeader().setReorderingAllowed(false);
        SearchResultsTableScrollPane.setViewportView(SearchResultsTable);
        if (SearchResultsTable.getColumnModel().getColumnCount() > 0) {
            SearchResultsTable.getColumnModel().getColumn(0).setResizable(false);
            SearchResultsTable.getColumnModel().getColumn(1).setResizable(true);
            SearchResultsTable.getColumnModel().getColumn(2).setResizable(true);
            SearchResultsTable.getColumnModel().getColumn(3).setResizable(true);
        }
        
        for(int i = 0; i < SearchResults.size(); i++)
        {
            Object [] data = {SearchResults.get(i).isbn, SearchResults.get(i).name, SearchResults.get(i).author, SearchResults.get(i).subject};
            tableModel.addRow(data);
        }
        
        SearchPanel.add(SearchResultsTableScrollPane);
        SearchResultsTableScrollPane.setVisible(true);
    }
        
    private void ViewProfileMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {                                    
        String password = null;
        String name = null;
        String address = null;
        long phone = 0;
        int finePaid = 0;
        
        RequestButton.setEnabled(false);
        CheckOutButton.setEnabled(false);
        CheckInButton.setEnabled(false);
        
        for(int i = 0; i < library.users.size(); i++)
        {
            if(username.equals(library.users.get(i).username))
            {
                password = library.users.get(i).password;
                name = library.users.get(i).name;
                address = library.users.get(i).address;
                phone = library.users.get(i).phone;
                finePaid = library.users.get(i).finePaid;
                break;
            }
        }
        
        ProfileFrameUsernameValue.setText(username);
        ProfileFramePasswordValue.setText(password);
        ProfileFrameNameValue.setText(name);
        ProfileFrameAddressValue.setText(address);
        ProfileFramePhoneValue.setText("+" + Long.toString(phone));
        ProfileFrameFinePaidValue.setText(Integer.toString(finePaid));
        ProfileFrame.setVisible(true);
        
    }
    
    private void ProfileFramePasswordValueMousePressed(java.awt.event.MouseEvent evt) {                                              ProfileFramePasswordValue.setEchoChar((char)0);
    }   
    
    private void ProfileFramePasswordValueMouseReleased(java.awt.event.MouseEvent evt) {                                             ProfileFramePasswordValue.setEchoChar('*');
    } 
    
    private void SearchResultsTableMouseClicked(java.awt.event.MouseEvent evt) {                                                
        RequestButton.setEnabled(true);
    } 
    
    private void SearchPanelMouseClicked(java.awt.event.MouseEvent evt) {                                                
        RequestButton.setEnabled(false);
    } 
    
    private void RequestButtonMouseClicked(java.awt.event.MouseEvent evt) {                                                
        int row = SearchResultsTable.getSelectedRow();
        String bookSelected = (String)SearchResultsTable.getValueAt(row, 1);
        
        Book requestedBook = null;
        
        boolean result = false;
        
        for(int i = 0; i < library.books.size(); i++)
        {
            if(bookSelected.equals(library.books.get(i).name))
            {
                requestedBook = library.books.get(i);
                break;
            }
        }
        result = librarian.requestBook(requestedBook);
        
        if(result)
        {
            JOptionPane.showMessageDialog(this, "Your request to borrow " + requestedBook.name + " has been submitted successfully!",
"", JOptionPane.PLAIN_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Book " + requestedBook.name + " is not available right now. You have been put on hold.",
"", JOptionPane.PLAIN_MESSAGE);            
        }
    } 
    
    private void LogoutMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {                                              
        try
        {
            String s = "jdbc:sqlserver://localhost\\DESKTOP-O2UFFF2:1433;databaseName=LMS;integrated‌​Security=true";
            Connection con = DriverManager.getConnection(s,"sa","123");

            Statement stmt = con.createStatement();
            
            stmt.execute("delete from Requests");
            stmt.execute("delete from Loans");
            stmt.execute("delete from Copies");
            stmt.execute("delete from Books");
            stmt.execute("delete from Users");
            
            PreparedStatement pStmt = Library.con.prepareStatement("Insert into Books values(?, ?, ?, ?)");
            
            for(int i = 0; i < library.books.size(); i++)
            {
                pStmt.setString(1, library.books.get(i).isbn);
                pStmt.setString(2, library.books.get(i).name);
                pStmt.setString(3, library.books.get(i).author);
                pStmt.setString(4, library.books.get(i).subject);
                pStmt.executeUpdate();
            }
            
            pStmt = Library.con.prepareStatement("Insert into Copies values(?, ?)");
            
            for(int i = 0; i < library.books.size(); i++)
            {
                for(int j = 0; j < library.books.get(i).ids.size(); j++)
                {
                    pStmt.setInt(1, library.books.get(i).ids.get(j));
                    pStmt.setString(2, library.books.get(i).isbn);
                    pStmt.executeUpdate();                    
                }
            }
            
            pStmt = Library.con.prepareStatement("Insert into Users values(?, ?, ?, ?, ?, ?, ?)");
            
            for(int i = 0; i < library.users.size(); i++)
            {
                pStmt.setString(1, library.users.get(i).username);
                pStmt.setString(2, library.users.get(i).password);
                pStmt.setString(3, library.users.get(i).name);
                pStmt.setString(4, library.users.get(i).address);
                pStmt.setLong(5, library.users.get(i).phone);
                if(library.users.get(i) instanceof Borrower)
                {
                    pStmt.setString(6, "B");
                }
                else if(library.users.get(i) instanceof Clerk)
                {
                    pStmt.setString(6, "C");
                }
                else if(library.users.get(i) instanceof Librarian)
                {
                    pStmt.setString(6, "L");
                }
                pStmt.setInt(7, library.users.get(i).finePaid);
                pStmt.executeUpdate();
            } 
            
            pStmt = Library.con.prepareStatement("Insert into Requests values(?, ?, ?)");
            
            for(int i = 0; i < library.books.size(); i++)
            {
                for (int j = 0; j < library.books.get(i).onHoldList.size(); j++) {
                    pStmt.setString(1, library.books.get(i).onHoldList.get(j));
                    pStmt.setString(2, library.books.get(i).isbn);
                    pStmt.setString(3, "On Hold");
                    pStmt.executeUpdate();
                }
                for (int j = 0; j < library.books.get(i).requestList.size(); j++) {
                    pStmt.setString(1, library.books.get(i).requestList.get(j));
                    pStmt.setString(2, library.books.get(i).isbn);
                    pStmt.setString(3, "Requested");
                    pStmt.executeUpdate();
                }
            }            
                       
            pStmt = Library.con.prepareStatement("Insert into Loans values(?, ?, ?, ?, ?, ?)");
            
            for(int i = 0; i < library.books.size(); i++)
            {
                for (int j = 0; j < library.books.get(i).loanedDetails.size(); j++) {
                    pStmt.setInt(1, library.books.get(i).loanedDetails.get(j).bookId);
                    pStmt.setString(2, library.books.get(i).loanedDetails.get(j).isbn);
                    pStmt.setString(3, library.books.get(i).loanedDetails.get(j).username);
                    pStmt.setDate(4, new java.sql.Date(library.books.get(i).loanedDetails.get(j).issD.getTime()));
                    pStmt.setDate(5, new java.sql.Date(library.books.get(i).loanedDetails.get(j).retD.getTime()));
                    pStmt.setBoolean(6, library.books.get(i).loanedDetails.get(j).onLoan);
                    pStmt.executeUpdate();
                }
            }
            
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        dispose();
        LibraryUI newInstance = new LibraryUI();
        newInstance.setVisible(true);
    }
        
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibrarianUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibrarianUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibrarianUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibrarianUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibrarianUI().setVisible(true);
            }
        });
    }
}
