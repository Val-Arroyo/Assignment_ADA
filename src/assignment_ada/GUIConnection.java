/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author valar
 */
public class GUIConnection extends JPanel implements ActionListener{
    
    private JPanel panel;// displaying the menu to the panel
    private JLabel frontLabel;
    private JButton guest_button, login_button, quit_button; // JButton view
    
    //Login 
    private JLabel user, passwords; // Label
    private JTextField userInput, passwordInput; //TextField for input
    private final int Width = 300; // Width Size
    private int Height = 500;// Height
    private final JButton enter_button, back_button; //Final login button because there is no changes needed    
    private GridBagConstraints constraints;// 
    private JList Chatlist; // Chat list variable for chats
    private JTextArea Area_text;
    
    //Chat Method class
    private JButton ImageButton;
    private JButton sendButton;
    private JTextField messageInput;
    private JScrollPane chatScroll; // Used to scroll image
    private JPanel list_panel;
    private JPanel panel_chat;
    private JPanel panel_button;
    private JLayeredPane listPane = new JLayeredPane();
    
    public GUIConnection(){
        
        super(new BorderLayout());
        //Displaying the the Main Panel
        panel = new JPanel(new GridBagLayout()); // Top left corner of the grid
        panel.setBorder(new EmptyBorder(300,300,300,300));// Setting up the position for the empty border
        
        constraints = new GridBagConstraints();
        // Buttons display
        guest_button = new JButton("Guest");
        login_button = new JButton("Login");
        quit_button = new JButton("Quit");
        
        // Labels
        user = new JLabel("UserName: ");
        passwords = new JLabel("Password: ");
        frontLabel = new JLabel("Enter a text");
        
        //Text Field input
        userInput = new JTextField(15);// Size of the username field
        passwordInput = new JTextField(15);// Size of the password field
        enter_button = new JButton("ENTER >"); // Login button
        back_button = new JButton("BACK <");// Back Button
        
        //Main menu
        login_button.addActionListener(this);
        guest_button.addActionListener(this);
        quit_button.addActionListener(this);
        //Enter button and back button
        enter_button.addActionListener(this);
        back_button.addActionListener(this);
        
        //Chat list Panel
        ImageButton = new JButton("Select Image");
        sendButton = new JButton("Send");
        messageInput = new JTextField(20);
        add(panel, BorderLayout.CENTER);
        
        ImageButton.addActionListener(this);
        sendButton.addActionListener(this);
        
        list_panel = new JPanel(true);
        panel_chat = new JPanel(true);
        panel_button = new JPanel(true);
        
        selectScreen();// Method added for screen selection
    }
    
    public void selectScreen() {
        panel.removeAll();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(guest_button, constraints);
        
        constraints.gridx = 1;
        panel.add(login_button, constraints);
        
        constraints.gridx = 2;
        panel.add(quit_button, constraints); // To identify the constraints
        
        panel.revalidate(); // Updating purpose
        panel.repaint(); // Updating purpose
    }
    
    public void loginScreen() {
        panel.removeAll();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(user, constraints);
        
        constraints.gridy = 1;
        panel.add(passwords, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        panel.add(userInput, constraints);
        
        constraints.gridy = 1;
        panel.add(passwordInput, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(enter_button, constraints);
        
        constraints.gridx = 1;
        panel.add(back_button, constraints);
        
        panel.revalidate();
        panel.repaint();
    }
    
    public void chatScreen() {
        panel.removeAll();
        panel_chat.removeAll();
        list_panel.removeAll();
        
        DefaultListModel<String> List = new DefaultListModel<>(); // Creating a chatlist for listing all the chats
        List.addElement("Planning");
        List.addElement("Testing");
        List.addElement("Implementing");
        List.addElement("Reviewing");
        List.addElement("Selling");
        
        Chatlist = new JList(List);// New JList of for Chat List
        constraints.gridx = 2;
        constraints.gridy = 1;
        
        Chatlist.setBounds(5,5,Width - 200, Height - 200);
        Chatlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);// Selection list mode
        
        listPane.add(Chatlist);
        listPane.setPreferredSize(new Dimension(Width - 200, Height - 200));
        list_panel.add(listPane);
        
        //Setting up the Text Area
        Area_text = new JTextArea();
        Area_text.setEditable(false);
        Area_text.setLineWrap(true);
        Area_text.setBounds(5,5, Width - 100, Height - 200);
        
        //Display all the button for chatscreen
        panel_button.add(messageInput);
        panel_button.add(quit_button);
        panel_button.add(sendButton);
        panel_button.add(ImageButton);
        
        //Chat scroll pane display
        chatScroll = new JScrollPane(Area_text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel_chat.add(chatScroll);
        chatScroll.setPreferredSize(new Dimension(Width - 200, Height - 200));
        
        this.add(list_panel, BorderLayout.WEST);
        this.add(panel_chat, BorderLayout.CENTER);
        this.add(panel_button, BorderLayout.SOUTH);
        
        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       
       if (source == login_button) {
           loginScreen();
       }
       
       if (source == quit_button){
           System.exit(0);
       }
       
       if (source == back_button){
           selectScreen();
       }
       if(source == enter_button){
           chatScreen();
       }
       if(source == guest_button){
           chatScreen();
       }
    }
    
    public static void main(String[] args) {
        
        GUIConnection gui = new GUIConnection();
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gui);
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width/2)-(frame.getWidth()/2), (d.height/2)-(frame.getHeight()/2)));
        frame.setVisible(true);
    }
}
