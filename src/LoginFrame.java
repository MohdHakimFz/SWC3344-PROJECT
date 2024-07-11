import java.awt.EventQueue; // Import necessary packages for creating the GUI and handling events
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L; // Serial version UID for serialization
    private JPanel contentPane; // Main content pane of the frame
    private JTextField UsernameField; // Text field for entering username
    private JPasswordField passwordField; // Password field for entering password
    private static String username; // Static variable to store the logged-in username

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { // Create a new thread to run the application
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame(); // Create a new instance of LoginFrame
                    frame.setVisible(true); // Make the frame visible
                } catch (Exception e) {
                    e.printStackTrace(); // Print stack trace if an exception occurs
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginFrame() {
    	setResizable(false); // Disable resizing of the frame
        setTitle("Login System"); // Set the title of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setBounds(100, 100, 1055, 660); // Set the size and position of the frame
        contentPane = new JPanel(); // Initialize the main content pane
        contentPane.setBackground(new Color(204, 204, 255)); // Set background color of the content pane
        contentPane.setForeground(new Color(255, 255, 255)); // Set foreground color of the content pane
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set border of the content pane

        setContentPane(contentPane); // Set the content pane of the frame
        contentPane.setLayout(null); // Set layout manager to null for absolute positioning

        UsernameField = new JTextField(); // Initialize the username text field
        UsernameField.setFont(new Font("Nirmala UI", Font.BOLD, 12)); // Set font of the username text field
        UsernameField.setBounds(672, 289, 222, 37); // Set position and size of the username text field
        contentPane.add(UsernameField); // Add the username text field to the content pane
        UsernameField.setColumns(10); // Set the number of columns for the text field

        JLabel lblUsername = new JLabel("Username:"); // Initialize the username label
        lblUsername.setFont(new Font("Leelawadee", Font.BOLD, 14)); // Set font of the username label
        lblUsername.setBounds(579, 300, 83, 24); // Set position and size of the username label
        contentPane.add(lblUsername); // Add the username label to the content pane

        JLabel lblPassword = new JLabel("Password:"); // Initialize the password label
        lblPassword.setFont(new Font("Leelawadee", Font.BOLD, 14)); // Set font of the password label
        lblPassword.setBounds(579, 371, 83, 19); // Set position and size of the password label
        contentPane.add(lblPassword); // Add the password label to the content pane

        passwordField = new JPasswordField(); // Initialize the password field
        passwordField.setFont(UIManager.getFont("PasswordField.font")); // Set font of the password field
        passwordField.setBounds(672, 358, 222, 37); // Set position and size of the password field
        contentPane.add(passwordField); // Add the password field to the content pane
        
        JButton LoginButton = new JButton("Login"); // Initialize the login button
        LoginButton.setBounds(707, 430, 154, 37); // Set position and size of the login button
        contentPane.add(LoginButton); // Add the login button to the content pane
        LoginButton.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                // Dummy validation logic
                username = UsernameField.getText(); // Get the entered username
                String password = new String(passwordField.getPassword()); // Get the entered password

                if (username.equals("Admin") && password.equals("123456")) {
                    // If login is successful, open the DashboardPage
                    DashboardPage dashboardPage = new DashboardPage(username); // Create a new instance of DashboardPage
                    dashboardPage.setVisible(true); // Make the DashboardPage visible
                    // Close the LoginFrame
                    dispose(); // Dispose the LoginFrame
                } else {
                    // Display an error message or handle login failure
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE); // Show error message
                }
            }
        });
        LoginButton.setFont(new Font("Rockwell", Font.BOLD, 12)); // Set font of the login button
        LoginButton.setForeground(new Color(255, 255, 255)); // Set foreground color of the login button
        LoginButton.setBackground(new Color(153, 51, 255)); // Set background color of the login button
        
        JLabel lblNewLabel = new JLabel("Welcome"); // Initialize the welcome label
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment of the welcome label
        lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 30)); // Set font of the welcome label
        lblNewLabel.setBounds(672, 148, 229, 30); // Set position and size of the welcome label
        contentPane.add(lblNewLabel); // Add the welcome label to the content pane
        
        JPanel panel = new JPanel(); // Initialize the panel
        panel.setBackground(new Color(153, 51, 255)); // Set background color of the panel
        panel.setBounds(0, 0, 448, 623); // Set position and size of the panel
        contentPane.add(panel); // Add the panel to the content pane
        panel.setLayout(null); // Set layout manager to null for absolute positioning
        
        JLabel lblNewLabel_1 = new JLabel(""); // Initialize a label for the image
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Hakim\\Downloads\\Purple (1).jpg")); // Set the icon of the label
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment of the label
        lblNewLabel_1.setBounds(0, 0, 448, 623); // Set position and size of the label
        panel.add(lblNewLabel_1); // Add the label to the panel
        
        JLabel lblHotnFast = new JLabel("Hot & Fast"); // Initialize the Hot & Fast label
        lblHotnFast.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment of the Hot & Fast label
        lblHotnFast.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 50)); // Set font of the Hot & Fast label
        lblHotnFast.setBounds(637, 195, 307, 54); // Set position and size of the Hot & Fast label
        contentPane.add(lblHotnFast); // Add the Hot & Fast label to the content pane
    }

    public static String getUsername() {
        return username; // Return the logged-in username
    }
}
