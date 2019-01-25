/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms_final;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

/**
 *
 * @author Asad
 */
public class BorrowerUI extends javax.swing.JFrame{
    String username;
    Library library;
    Borrower borrower;
    
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
    
    private javax.swing.JPanel BorrowedPanel;
    private javax.swing.JLabel BorrowedUserLabel;
    private javax.swing.JScrollPane BorrowedTableScrollPane;
    private javax.swing.JTable BorrowedTable;
    
    private javax.swing.JFrame ProfileFrame;
    private javax.swing.JLabel ProfileFrameTitleLabel;
    private javax.swing.JPanel ProfileFrameUpperPanel;
    private javax.swing.JPanel ProfileFrameLowerPanel;    
    private javax.swing.JLabel ProfileFrameAddressLabel;
    private javax.swing.JLabel ProfileFrameAddressValue;
    private javax.swing.JLabel ProfileFrameFinePaidLabel;
    private javax.swing.JLabel ProfileFrameFinePaidValue;
    private javax.swing.JLabel ProfileFrameNameLabel;
    private javax.swing.JLabel ProfileFrameNameValue;
    private javax.swing.JLabel ProfileFramePasswordLabel;
    private javax.swing.JPasswordField ProfileFramePasswordValue;
    private javax.swing.JLabel ProfileFramePhoneLabel;
    private javax.swing.JLabel ProfileFramePhoneValue;
    private javax.swing.JLabel ProfileFrameUsernameLabel;
    private javax.swing.JLabel ProfileFrameUsernameValue;
    
    public BorrowerUI()
    {
        //Library a = new Library();
        initComponents();
    }
    
    void initComponents()
    {
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
        BorrowedTableScrollPane = new javax.swing.JScrollPane();;
        
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
        
        //MainFrame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Management System");
        getContentPane().setBackground(new java.awt.Color(228, 240, 252));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("MainFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        
        //ProfileFrame
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
        
        ProfileFrameUpperPanel.setBackground(new java.awt.Color(228,240,252));
        
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
        
        //TabbedPane
        TabbedPane.setBackground(new java.awt.Color(228, 240, 252));
        TabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        TabbedPane.setFont(new java.awt.Font("Lato Black", 0, 16)); // NOI18N
        TabbedPane.setForeground(new java.awt.Color(46, 61, 64));
        
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
        SearchField.setMinimumSize(new java.awt.Dimension(185,35));
        
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
        
        SearchButtonPanel.setSize(339,29);
        SearchButtonPanel.setMaximumSize(new java.awt.Dimension(339,29));
        SearchButtonPanel.setMinimumSize(new java.awt.Dimension(339,29));
        SearchButtonPanel.setLayout(new GridLayout(1,3));
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
            new Object [][] {
            },
            new String [] {
                "ISBN", "Title", "Author", "Subject"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
            new Object [][] {
            },
            new String [] {
                "Book ID","ISBN", "Issue Date", "Return Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
        
        TabbedPane.add("Search Books", SearchPanel);
        TabbedPane.add("Borrowed Books", BorrowedPanel);
        
        TabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabbedPaneStateChanged(evt);
            }
        });
        
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
        
        //TabbedPane.add("", rootPane)
        
        pack();
    }
    
    void giveValues(String username, Library library)
    {
        this.username = username;
        this.library = library;
        
        for(int i = 0; i < library.users.size(); i++)
        {
            if(username.equals(library.users.get(i).username))
            {
                borrower = (Borrower) library.users.get(i);
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
            
            ArrayList<Loan> BorrowedResults = borrower.loanedBooks;
        
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
            
            BorrowedPanel.add(BorrowedTableScrollPane);
            BorrowedTableScrollPane.setVisible(true);
        }
    }  
    
    private void SearchButtonMouseClicked(java.awt.event.MouseEvent evt)
    {
        RequestButton.setEnabled(false);
        
        ArrayList<Book> SearchResults = null;
        
        if(ByTitleRadioButton.isSelected())
        {
            SearchResults = borrower.searchBookbyName(SearchField.getText());
        }
        else if(ByAuthorRadioButton.isSelected())
        {
            SearchResults = borrower.searchBookbyAuthor(SearchField.getText());
        }
        else if(BySubjectRadioButton.isSelected())
        {
            SearchResults = borrower.searchBookbySubject(SearchField.getText());
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
    }
        
    private void ViewProfileMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {                                    
        String password = null;
        String name = null;
        String address = null;
        long phone = 0;
        int finePaid = 0;
        
        RequestButton.setEnabled(false);
        
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
        result = borrower.requestBook(requestedBook);
        
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
            
            PreparedStatement pStmt = Library.con.prepareStatement("Insert into Requests values(?, ?, ?)");
            
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
            java.util.logging.Logger.getLogger(BorrowerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrowerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrowerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrowerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BorrowerUI().setVisible(true);
            }
        });
    }
}
