import java.awt.CardLayout; // Import necessary packages for creating the GUI and handling events
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class DashboardPage extends JFrame {

    private static final long serialVersionUID = 1L; // Serial version UID for serialization
    private JPanel contentPane; // Main content pane of the frame
    private JPanel cardPanel; // Panel for holding card layout
    private CardLayout cardLayout; // Card layout for switching between different panels
    private JLabel CountLabel; // Label to display the count of remaining customers
    private int customerCount = 0; // Counter for the number of customers
    private ArrayList<String> customerList = new ArrayList<>(); // List to hold customer data

    // Queues for each counter
    private Queue<String> counter1Queue = new ArrayDeque<>();
    private Queue<String> counter2Queue = new ArrayDeque<>();
    private Queue<String> counter3Queue = new ArrayDeque<>();

    // Lists to hold paid customers
    private ArrayList<String> counter1Paid = new ArrayList<>();
    private ArrayList<String> counter2Paid = new ArrayList<>();
    private ArrayList<String> counter3Paid = new ArrayList<>();

    // Table models for each counter
    private DefaultTableModel counter1TableModel;
    private DefaultTableModel counter2TableModel;
    private DefaultTableModel counter3TableModel;

    // Table for displaying all receipts
    private DefaultTableModel allReceiptsTableModel;

    // Label to display username
    private JLabel HiLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { // Create a new thread to run the application
            public void run() {
                try {
                    DashboardPage frame = new DashboardPage("User123"); // Create a new instance of DashboardPage
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
    public DashboardPage(String username) {
        setResizable(false); // Disable resizing of the frame
        setTitle("DashboardPage"); // Set the title of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setBounds(100, 100, 1132, 800); // Set the size and position of the frame
        contentPane = new JPanel(); // Initialize the main content pane
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set border of the content pane

        setContentPane(contentPane); // Set the content pane of the frame
        contentPane.setLayout(null); // Set layout manager to null for absolute positioning

        JPanel panel = new JPanel(); // Initialize the side panel
        panel.setBackground(new Color(147, 112, 219)); // Set background color of the side panel
        panel.setBounds(0, 38, 222, 731); // Set position and size of the side panel
        contentPane.add(panel); // Add the side panel to the content pane
        panel.setLayout(null); // Set layout manager to null for absolute positioning

        JButton LogOutButton = new JButton("Log Out"); // Initialize the log out button
        LogOutButton.setBackground(Color.WHITE); // Set background color of the log out button
        LogOutButton.setFont(new Font("Nirmala UI", Font.BOLD, 14)); // Set font of the log out button
        LogOutButton.setBounds(36, 591, 147, 30); // Set position and size of the log out button
        LogOutButton.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                logOut(); // Call logOut method when button is clicked
            }
        });
        panel.add(LogOutButton); // Add the log out button to the side panel

        JButton DisplayButton = new JButton("Display"); // Initialize the display button
        DisplayButton.setBackground(Color.WHITE); // Set background color of the display button
        DisplayButton.setFont(new Font("Nirmala UI", Font.BOLD, 14)); // Set font of the display button
        DisplayButton.setBounds(36, 512, 147, 30); // Set position and size of the display button
        panel.add(DisplayButton); // Add the display button to the side panel

        JButton ShowDataButton = new JButton("Show Data"); // Initialize the show data button
        ShowDataButton.setBackground(Color.WHITE); // Set background color of the show data button
        ShowDataButton.setFont(new Font("Nirmala UI", Font.BOLD, 14)); // Set font of the show data button
        ShowDataButton.setBounds(36, 432, 147, 30); // Set position and size of the show data button
        panel.add(ShowDataButton); // Add the show data button to the side panel

        JButton AddCusButton = new JButton("Add New Customer"); // Initialize the add new customer button
        AddCusButton.setBackground(Color.WHITE); // Set background color of the add new customer button
        AddCusButton.setFont(new Font("Nirmala UI", Font.BOLD, 12)); // Set font of the add new customer button
        AddCusButton.setBounds(36, 472, 147, 30); // Set position and size of the add new customer button
        panel.add(AddCusButton); // Add the add new customer button to the side panel

        AddCusButton.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                addNewCustomers(); // Call addNewCustomers method when button is clicked
            }
        });

        HiLabel = new JLabel("Hi, " + username); // Initialize the label to display username
        HiLabel.setForeground(new Color(248, 248, 255)); // Set foreground color of the username label
        HiLabel.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment of the username label
        HiLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 20)); // Set font of the username label
        HiLabel.setBounds(25, 210, 165, 30); // Set position and size of the username label
        panel.add(HiLabel); // Add the username label to the side panel

        JButton NextQueueButton = new JButton("Next Queue "); // Initialize the next queue button
        NextQueueButton.setBackground(Color.WHITE); // Set background color of the next queue button
        NextQueueButton.setFont(new Font("Nirmala UI", Font.BOLD, 14)); // Set font of the next queue button
        NextQueueButton.setBounds(36, 392, 147, 30); // Set position and size of the next queue button
        NextQueueButton.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                distributeToQueues(); // Call distributeToQueues method when button is clicked
            }
        });
        panel.add(NextQueueButton); // Add the next queue button to the side panel
        
        JLabel lblNewLabel = new JLabel(""); // Initialize the label for the user image
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Hakim\\Downloads\\user (1).png")); // Set the icon of the label
        lblNewLabel.setBounds(40, 40, 128, 176); // Set position and size of the label
        panel.add(lblNewLabel); // Add the label to the side panel

        JPanel panel_1 = new JPanel(); // Initialize the top panel
        panel_1.setBackground(new Color(45, 0, 89)); // Set background color of the top panel
        panel_1.setBounds(0, 0, 1187, 39); // Set position and size of the top panel
        contentPane.add(panel_1); // Add the top panel to the content pane
        panel_1.setLayout(null); // Set layout manager to null for absolute positioning

        CountLabel = new JLabel("Count: 0"); // Initialize the count label
        CountLabel.setForeground(new Color(248, 248, 255)); // Set foreground color of the count label
        CountLabel.setFont(new Font("Tahoma", Font.BOLD, 15)); // Set font of the count label
        CountLabel.setBounds(1014, 10, 95, 17); // Set position and size of the count label
        panel_1.add(CountLabel); // Add the count label to the top panel

        cardPanel = new JPanel(); // Initialize the card panel
        cardLayout = new CardLayout(); // Initialize the card layout
        cardPanel.setLayout(cardLayout); // Set layout manager of the card panel to card layout
        cardPanel.setBounds(218, 38, 785, 731); // Set position and size of the card panel
        contentPane.add(cardPanel); // Add the card panel to the content pane

        // Counter 1 Panel
        JPanel counter1Panel = new JPanel(); // Initialize the counter 1 panel
        counter1Panel.setBackground(Color.LIGHT_GRAY); // Set background color of the counter 1 panel
        counter1Panel.setLayout(null); // Set layout manager to null for absolute positioning
        JLabel counter1Label = new JLabel("COUNTER 1"); // Initialize the label for counter 1
        counter1Label.setFont(new Font("Leelawadee UI", Font.BOLD, 15)); // Set font of the counter 1 label
        counter1Label.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment of the counter 1 label
        counter1Label.setBounds(348, 10, 87, 30); // Set position and size of the counter 1 label
        counter1Panel.add(counter1Label); // Add the counter 1 label to the counter 1 panel

        counter1TableModel = new DefaultTableModel(new Object[]{"Customer ID", "Customer Name", "Table Number", "Order ID", "Item Name", "Item Price", "Quantity", "Order Time"}, 0); // Initialize the table model for counter 1
        JTable counter1Table = new JTable(counter1TableModel); // Initialize the table for counter 1
        JScrollPane counter1ScrollPane = new JScrollPane(counter1Table); // Initialize the scroll pane for counter 1 table
        counter1ScrollPane.setBounds(10, 50, 765, 543); // Set position and size of the scroll pane
        counter1Panel.add(counter1ScrollPane); // Add the scroll pane to the counter 1 panel

        JButton PaymentButton1 = new JButton("Payment"); // Initialize the payment button for counter 1
        PaymentButton1.setBackground(new Color(230, 230, 250)); // Set background color of the payment button
        PaymentButton1.setFont(new Font("Leelawadee UI", Font.BOLD, 13)); // Set font of the payment button
        PaymentButton1.setBounds(169, 641, 180, 30); // Set position and size of the payment button
        PaymentButton1.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                processPayment(counter1Queue, counter1Paid, counter1TableModel); // Call processPayment method when button is clicked
                updateCountLabel(); // Update the count label
            }
        });
        counter1Panel.add(PaymentButton1); // Add the payment button to the counter 1 panel

        JButton ReceiptButton1 = new JButton("Receipt"); // Initialize the receipt button for counter 1
        ReceiptButton1.setBackground(new Color(230, 230, 250)); // Set background color of the receipt button
        ReceiptButton1.setForeground(Color.BLACK); // Set foreground color of the receipt button
        ReceiptButton1.setFont(new Font("Leelawadee UI", Font.BOLD, 13)); // Set font of the receipt button
        ReceiptButton1.setBounds(386, 641, 180, 30); // Set position and size of the receipt button
        ReceiptButton1.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                showReceipt(counter1Paid); // Call showReceipt method when button is clicked
            }
        });
        counter1Panel.add(ReceiptButton1); // Add the receipt button to the counter 1 panel

        // Counter 2 Panel
        JPanel counter2Panel = new JPanel(); // Initialize the counter 2 panel
        counter2Panel.setBackground(Color.LIGHT_GRAY); // Set background color of the counter 2 panel
        counter2Panel.setLayout(null); // Set layout manager to null for absolute positioning
        JLabel counter2Label = new JLabel("COUNTER 2"); // Initialize the label for counter 2
        counter2Label.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment of the counter 2 label
        counter2Label.setFont(new Font("Leelawadee UI", Font.BOLD, 15)); // Set font of the counter 2 label
        counter2Label.setBounds(348, 10, 87, 30); // Set position and size of the counter 2 label
        counter2Panel.add(counter2Label); // Add the counter 2 label to the counter 2 panel

        counter2TableModel = new DefaultTableModel(new Object[]{"Customer ID", "Customer Name", "Table Number", "Order ID", "Item Name", "Item Price", "Quantity", "Order Time"}, 0); // Initialize the table model for counter 2
        JTable counter2Table = new JTable(counter2TableModel); // Initialize the table for counter 2
        JScrollPane counter2ScrollPane = new JScrollPane(counter2Table); // Initialize the scroll pane for counter 2 table
        counter2ScrollPane.setBounds(10, 50, 765, 543); // Set position and size of the scroll pane
        counter2Panel.add(counter2ScrollPane); // Add the scroll pane to the counter 2 panel

        JButton PaymentButton2 = new JButton("Payment"); // Initialize the payment button for counter 2
        PaymentButton2.setBackground(new Color(230, 230, 250)); // Set background color of the payment button
        PaymentButton2.setFont(new Font("Leelawadee UI", Font.BOLD, 13)); // Set font of the payment button
        PaymentButton2.setBounds(169, 641, 180, 30); // Set position and size of the payment button
        PaymentButton2.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                processPayment(counter2Queue, counter2Paid, counter2TableModel); // Call processPayment method when button is clicked
                updateCountLabel(); // Update the count label
            }
        });
        counter2Panel.add(PaymentButton2); // Add the payment button to the counter 2 panel

        JButton ReceiptButton2 = new JButton("Receipt"); // Initialize the receipt button for counter 2
        ReceiptButton2.setBackground(new Color(230, 230, 250)); // Set background color of the receipt button
        ReceiptButton2.setFont(new Font("Leelawadee UI", Font.BOLD, 13)); // Set font of the receipt button
        ReceiptButton2.setBounds(386, 641, 180, 30); // Set position and size of the receipt button
        ReceiptButton2.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                showReceipt(counter2Paid); // Call showReceipt method when button is clicked
            }
        });
        counter2Panel.add(ReceiptButton2); // Add the receipt button to the counter 2 panel

        // Counter 3 Panel
        JPanel counter3Panel = new JPanel(); // Initialize the counter 3 panel
        counter3Panel.setBackground(Color.LIGHT_GRAY); // Set background color of the counter 3 panel
        counter3Panel.setLayout(null); // Set layout manager to null for absolute positioning
        JLabel counter3Label = new JLabel("COUNTER 3"); // Initialize the label for counter 3
        counter3Label.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment of the counter 3 label
        counter3Label.setFont(new Font("Nirmala UI", Font.BOLD, 15)); // Set font of the counter 3 label
        counter3Label.setBounds(348, 10, 87, 30); // Set position and size of the counter 3 label
        counter3Panel.add(counter3Label); // Add the counter 3 label to the counter 3 panel

        counter3TableModel = new DefaultTableModel(new Object[]{"Customer ID", "Customer Name", "Table Number", "Order ID", "Item Name", "Item Price", "Quantity", "Order Time"}, 0); // Initialize the table model for counter 3
        JTable counter3Table = new JTable(counter3TableModel); // Initialize the table for counter 3
        JScrollPane counter3ScrollPane = new JScrollPane(counter3Table); // Initialize the scroll pane for counter 3 table
        counter3ScrollPane.setBounds(10, 50, 765, 543); // Set position and size of the scroll pane
        counter3Panel.add(counter3ScrollPane); // Add the scroll pane to the counter 3 panel

        JButton PaymentButton3 = new JButton("Payment"); // Initialize the payment button for counter 3
        PaymentButton3.setBackground(new Color(230, 230, 250)); // Set background color of the payment button
        PaymentButton3.setFont(new Font("Nirmala UI", Font.BOLD, 13)); // Set font of the payment button
        PaymentButton3.setBounds(169, 641, 180, 30); // Set position and size of the payment button
        PaymentButton3.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                processPayment(counter3Queue, counter3Paid, counter3TableModel); // Call processPayment method when button is clicked
                updateCountLabel(); // Update the count label
            }
        });
        counter3Panel.add(PaymentButton3); // Add the payment button to the counter 3 panel

        JButton ReceiptButton3 = new JButton("Receipt"); // Initialize the receipt button for counter 3
        ReceiptButton3.setBackground(new Color(230, 230, 250)); // Set background color of the receipt button
        ReceiptButton3.setFont(new Font("Nirmala UI", Font.BOLD, 13)); // Set font of the receipt button
        ReceiptButton3.setBounds(386, 641, 180, 30); // Set position and size of the receipt button
        ReceiptButton3.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                showReceipt(counter3Paid); // Call showReceipt method when button is clicked
            }
        });
        counter3Panel.add(ReceiptButton3); // Add the receipt button to the counter 3 panel

        cardPanel.add(counter1Panel, "Counter 1"); // Add the counter 1 panel to the card panel
        cardPanel.add(counter2Panel, "Counter 2"); // Add the counter 2 panel to the card panel
        cardPanel.add(counter3Panel, "Counter 3"); // Add the counter 3 panel to the card panel

        JButton Counter1Button = new JButton("Counter 1"); // Initialize the counter 1 button
        Counter1Button.setBackground(Color.WHITE); // Set background color of the counter 1 button
        Counter1Button.setFont(new Font("Nirmala UI", Font.BOLD, 16)); // Set font of the counter 1 button
        Counter1Button.setHorizontalAlignment(SwingConstants.LEFT); // Set horizontal alignment of the counter 1 button
        Counter1Button.setBounds(1012, 137, 193, 107); // Set position and size of the counter 1 button
        Counter1Button.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Counter 1"); // Show the counter 1 panel when button is clicked
            }
        });
        contentPane.add(Counter1Button); // Add the counter 1 button to the content pane

        JButton Counter2Button = new JButton("Counter 2"); // Initialize the counter 2 button
        Counter2Button.setBackground(Color.WHITE); // Set background color of the counter 2 button
        Counter2Button.setFont(new Font("Nirmala UI", Font.BOLD, 16)); // Set font of the counter 2 button
        Counter2Button.setHorizontalAlignment(SwingConstants.LEFT); // Set horizontal alignment of the counter 2 button
        Counter2Button.setBounds(1012, 273, 193, 107); // Set position and size of the counter 2 button
        Counter2Button.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Counter 2"); // Show the counter 2 panel when button is clicked
            }
        });
        contentPane.add(Counter2Button); // Add the counter 2 button to the content pane

        JButton Counter3Button = new JButton("Counter 3"); // Initialize the counter 3 button
        Counter3Button.setBackground(Color.WHITE); // Set background color of the counter 3 button
        Counter3Button.setFont(new Font("Nirmala UI", Font.BOLD, 16)); // Set font of the counter 3 button
        Counter3Button.setHorizontalAlignment(SwingConstants.LEFT); // Set horizontal alignment of the counter 3 button
        Counter3Button.setBounds(1012, 407, 193, 107); // Set position and size of the counter 3 button
        Counter3Button.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Counter 3"); // Show the counter 3 panel when button is clicked
            }
        });
        contentPane.add(Counter3Button); // Add the counter 3 button to the content pane

        // Display Panel
        JPanel displayPanel = new JPanel(); // Initialize the display panel
        displayPanel.setBackground(Color.LIGHT_GRAY); // Set background color of the display panel
        displayPanel.setLayout(null); // Set layout manager to null for absolute positioning
        cardPanel.add(displayPanel, "Display Panel"); // Add the display panel to the card panel

        allReceiptsTableModel = new DefaultTableModel(new Object[]{"Counter", "Customer ID", "Customer Name", "Table Number", "Order ID", "Item Name", "Item Price", "Quantity", "Order Time"}, 0); // Initialize the table model for displaying all receipts
        JTable allReceiptsTable = new JTable(allReceiptsTableModel); // Initialize the table for displaying all receipts
        JScrollPane allReceiptsScrollPane = new JScrollPane(allReceiptsTable); // Initialize the scroll pane for the all receipts table
        allReceiptsScrollPane.setBounds(10, 10, 765, 710); // Set position and size of the scroll pane
        displayPanel.add(allReceiptsScrollPane); // Add the scroll pane to the display panel
        
        JPanel panel_2 = new JPanel(); // Initialize the right side panel
        panel_2.setBackground(new Color(230, 230, 250)); // Set background color of the right side panel
        panel_2.setBounds(1004, 38, 114, 731); // Set position and size of the right side panel
        contentPane.add(panel_2); // Add the right side panel to the content pane
        panel_2.setLayout(null); // Set layout manager to null for absolute positioning

        ShowDataButton.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                showData(); // Call showData method when button is clicked
                cardLayout.show(cardPanel, "Display Panel"); // Show the display panel
            }
        });

        DisplayButton.addActionListener(new ActionListener() { // Add action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                showAllReceipts(); // Call showAllReceipts method when button is clicked
                cardLayout.show(cardPanel, "Display Panel"); // Show the display panel
            }
        });
    }

    private void addNewCustomers() {
        String filePath = "C:\\Users\\Hakim\\Downloads\\customerList.txt"; // Path to the customer list file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { // Open the file
            String line;
            while ((line = br.readLine()) != null && customerCount < 100) { // Read each line and limit to 100 customers
                customerList.add(line); // Add the customer data to the list
                customerCount++; // Increment the customer count

                // Update table model
                String[] parts = line.split(", ");
                String custId = parts[0];
                String custName = parts[1];
                String tableNumber = parts[2];
                String[] orders = parts[3].split("\\|");
                for (String order : orders) {
                    String[] orderParts = order.split(";");
                    String orderId = orderParts[0];
                    String itemName = orderParts[1];
                    String itemPrice = orderParts[2];
                    String quantity = orderParts[3];
                    String orderTime = orderParts[4];
                }
            }
            CountLabel.setText("Count: " + customerCount); // Update the count label
            JOptionPane.showMessageDialog(this, "100 New Customers added successfully"); // Show success message
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace if an exception occurs
        }
    }

    private void showData() {
        allReceiptsTableModel.setRowCount(0); // Clear existing rows
        for (String customer : customerList) { // Iterate over customer list
            String[] parts = customer.split(", ");
            String custId = parts[0];
            String custName = parts[1];
            String tableNumber = parts[2];
            String[] orders = parts[3].split("\\|");
            for (String order : orders) {
                String[] orderParts = order.split(";");
                String orderId = orderParts[0];
                String itemName = orderParts[1];
                String itemPrice = orderParts[2];
                String quantity = orderParts[3];
                String orderTime = orderParts[4];
                allReceiptsTableModel.addRow(new Object[]{"-", custId, custName, tableNumber, orderId, itemName, itemPrice, quantity, orderTime}); // Add row to the table model
            }
        }
    }

    private void distributeToQueues() {
        int initialSize = customerList.size(); // Get the initial size of the customer list
        for (int i = 0; i < initialSize; i++) { // Iterate over the initial size of the customer list
            String customer = customerList.remove(0); // Remove the customer from the list
            String[] parts = customer.split(", ");
            String custId = parts[0];
            String custName = parts[1];
            String tableNumber = parts[2];
            String[] orders = parts[3].split("\\|");
            int totalQuantity = 0;
            for (String order : orders) {
                String[] orderParts = order.split(";");
                totalQuantity += Integer.parseInt(orderParts[3]); // Calculate the total quantity of items ordered
            }

            DefaultTableModel model = null;
            if (totalQuantity <= 5 && counter1Queue.size() < 5) { // Check if the customer can be added to counter 1 queue
                counter1Queue.add(customer);
                model = counter1TableModel;
            } else if (totalQuantity <= 5 && counter2Queue.size() < 5) { // Check if the customer can be added to counter 2 queue
                counter2Queue.add(customer);
                model = counter2TableModel;
            } else if (totalQuantity > 5 && counter3Queue.size() < 5) { // Check if the customer can be added to counter 3 queue
                counter3Queue.add(customer);
                model = counter3TableModel;
            } else {
                customerList.add(customer); // Add the customer back to the list if all queues are full
                if (counter1Queue.size() == 5 && counter2Queue.size() == 5 && counter3Queue.size() == 5) {
                    JOptionPane.showMessageDialog(this, "All counters are full!"); // Show error message if all counters are full
                    break;
                }
            }

            if (model != null) { // Update the table model
                for (String order : orders) {
                    String[] orderParts = order.split(";");
                    String orderId = orderParts[0];
                    String itemName = orderParts[1];
                    String itemPrice = orderParts[2];
                    String quantity = orderParts[3];
                    String orderTime = orderParts[4];
                    model.addRow(new Object[]{custId, custName, tableNumber, orderId, itemName, itemPrice, quantity, orderTime}); // Add row to the table model
                }
            }
        }
        updateCountLabel(); // Update the count label
    }

    private void updateCountLabel() {
        int remainingCustomers = customerList.size(); // Get the remaining number of customers
        CountLabel.setText("Count: " + remainingCustomers); // Update the count label
    }

    private void processPayment(Queue<String> queue, ArrayList<String> paidList, DefaultTableModel tableModel) {
        if (!queue.isEmpty()) { // Check if the queue is not empty
            String customer = queue.poll(); // Poll the customer from the queue
            paidList.add(customer); // Add the customer to the paid list
            String[] parts = customer.split(", ");
            String custId = parts[0];
            String custName = parts[1];
            String tableNumber = parts[2];
            String[] orders = parts[3].split("\\|");

            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) { // Remove the customer from the table model
                if (tableModel.getValueAt(i, 0).equals(custId)) {
                    tableModel.removeRow(i);
                }
            }

            for (String order : orders) { // Add the customer to the all receipts table model
                String[] orderParts = order.split(";");
                String orderId = orderParts[0];
                String itemName = orderParts[1];
                String itemPrice = orderParts[2];
                String quantity = orderParts[3];
                String orderTime = orderParts[4];
                allReceiptsTableModel.addRow(new Object[]{"Counter " + (paidList == counter1Paid ? 1 : (paidList == counter2Paid ? 2 : 3)), custId, custName, tableNumber, orderId, itemName, itemPrice, quantity, orderTime}); // Add row to the table model
            }

            JOptionPane.showMessageDialog(this, "Payment processed for customer: " + custName); // Show success message
        } else {
            JOptionPane.showMessageDialog(this, "No customers in queue!"); // Show error message if queue is empty
        }
    }

    private void showReceipt(ArrayList<String> paidList) {
        StringBuilder receipt = new StringBuilder(); // Initialize the receipt string builder
        for (String customer : paidList) { // Iterate over the paid list
            receipt.append(customer).append("<br>"); // Append customer data to the receipt
        }
        JOptionPane.showMessageDialog(this, "<html>" + receipt.toString() + "</html>", "Receipt", JOptionPane.INFORMATION_MESSAGE); // Show receipt in a message dialog
    }

    private void showAllReceipts() {
        cardLayout.show(cardPanel, "Display Panel"); // Show the display panel
    }

    private void logOut() {
        this.dispose(); // Dispose the current frame
        LoginFrame.main(null); // Call the main method of LoginFrame to return to the login screen
    }
}
