import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Panel1Page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel1Page frame = new Panel1Page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Panel1Page() {
		setTitle("Panel 1 System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton PaymentButton = new JButton("Payment");
		PaymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PaymentButton.setBounds(302, 656, 180, 30);
		contentPane.add(PaymentButton);
		
		JButton ReceiptButton = new JButton("Receipt");
		ReceiptButton.setBounds(569, 656, 180, 30);
		contentPane.add(ReceiptButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 1066, 534);
		contentPane.add(scrollPane);
		
		JLabel Panel1Label = new JLabel("Counter 1");
		Panel1Label.setHorizontalAlignment(SwingConstants.CENTER);
		Panel1Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		Panel1Label.setBounds(463, 22, 111, 29);
		contentPane.add(Panel1Label);
		
		JButton BackButton = new JButton("Back ");
		BackButton.setBounds(443, 703, 180, 30);
		contentPane.add(BackButton);
	}

}
