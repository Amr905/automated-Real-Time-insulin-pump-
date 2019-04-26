package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.Clock;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PumpView {

	public JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public PumpView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JLabel Clock = new JLabel("00:00");
	private final JButton BisBtn = new JButton("Biscuits");
	private final JButton CarbsBtn = new JButton("Carbs");
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 651, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Clock.setFont(new Font("Tahoma", Font.PLAIN, 27));
		Clock.setBounds(539, 16, 70, 33);
		frame.getContentPane().add(Clock);
		BisBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Config add sugar
			}
		});
		BisBtn.setBounds(369, 377, 115, 29);
		
		frame.getContentPane().add(BisBtn);
		CarbsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Config add sugar
			}
		});
		CarbsBtn.setBounds(499, 377, 115, 29);
		
		frame.getContentPane().add(CarbsBtn);
		
		JButton Reservoir = new JButton("Change Reservoir");
		Reservoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//config Change res.
			}
		});
		Reservoir.setBounds(15, 16, 157, 29);
		frame.getContentPane().add(Reservoir);
		
		JLabel lblLastDose = new JLabel("Last Dose:");
		lblLastDose.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLastDose.setBounds(15, 303, 104, 27);
		frame.getContentPane().add(lblLastDose);
		
		JLabel LastDose = new JLabel("0.0");
		LastDose.setFont(new Font("Tahoma", Font.PLAIN, 22));
		LastDose.setBounds(134, 303, 104, 27);
		frame.getContentPane().add(LastDose);
		
		JLabel SysMsg = new JLabel("Loading...");
		SysMsg.setFont(new Font("Tahoma", Font.PLAIN, 28));
		SysMsg.setBounds(260, 159, 120, 34);
		frame.getContentPane().add(SysMsg);
	}
	
	public void SetClock(String time) {
		Clock.setText(time);
	}
}
