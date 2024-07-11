import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Panel3Page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel3Page frame = new Panel3Page();
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
	public Panel3Page() {
		setTitle("Panel 3 System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Payment1Button = new JButton("Payment");
		Payment1Button.setBounds(302, 656, 180, 30);
		contentPane.add(Payment1Button);
		
		JButton Receipt1Button = new JButton("Receipt");
		Receipt1Button.setBounds(569, 656, 180, 30);
		contentPane.add(Receipt1Button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 1066, 534);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Counter 3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(463, 22, 111, 29);
		contentPane.add(lblNewLabel);
		
		JButton Back2Button = new JButton("Back");
		Back2Button.setBounds(443, 703, 180, 30);
		contentPane.add(Back2Button);
	}
}
