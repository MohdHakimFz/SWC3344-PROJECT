import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Panel2Page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel2Page frame = new Panel2Page();
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
	public Panel2Page() {
		setTitle("Panel 2 System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Counter2Label = new JLabel("Counter 2");
		Counter2Label.setHorizontalAlignment(SwingConstants.CENTER);
		Counter2Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		Counter2Label.setBounds(463, 22, 111, 29);
		contentPane.add(Counter2Label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 1066, 534);
		contentPane.add(scrollPane);
		
		JButton PayButton = new JButton("Payment");
		PayButton.setBounds(302, 656, 180, 30);
		contentPane.add(PayButton);
		
		JButton btnReceipt = new JButton("Receipt");
		btnReceipt.setBounds(569, 656, 180, 30);
		contentPane.add(btnReceipt);
		
		JButton Back1Button = new JButton("Back ");
		Back1Button.setBounds(443, 703, 180, 30);
		contentPane.add(Back1Button);
	}
}
