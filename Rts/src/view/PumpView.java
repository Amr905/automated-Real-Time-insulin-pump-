package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.time.Clock;
import java.time.LocalTime;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import model.HumanBody;
import java.awt.Color;

public class PumpView {

	public JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	HumanBody human;
	
	public PumpView(HumanBody human) {
		initialize();
		this.human = human;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private final JButton BisBtn = new JButton("");
	private final JButton CarbsBtn = new JButton("");
	JLabel SysMsg = new JLabel("Loading...");
	JLabel LastDose = new JLabel("0.0");
	LocalTime now = LocalTime.now();
	LocalTime MyTime = LocalTime.of(now.getHour(), now.getMinute());
	private final JLabel imgback = new JLabel("");
	JButton Reservoir = new JButton("");
	JLabel Clock = new JLabel(MyTime.toString());
	JLabel SysDebug = new JLabel("debug");
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 741, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Clock.setFont(new Font("Tahoma", Font.PLAIN, 27));
		Clock.setBounds(318, 117, 70, 33);
		frame.getContentPane().add(Clock);
		
		Image image = new ImageIcon(this.getClass().getResource("/circular-biscuits.png")).getImage();
		BisBtn.setIcon(new ImageIcon(image));
		BisBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Config add sugar
				human.addSuger(60);
			}
		});
		BisBtn.setBounds(501, 431, 95, 62);
		frame.getContentPane().add(BisBtn);
		
		Image image1 = new ImageIcon(this.getClass().getResource("/carbs.png")).getImage();
		CarbsBtn.setIcon(new ImageIcon(image1));
		CarbsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Config add sugar
				human.addSuger(50);
			}
		});
		CarbsBtn.setBounds(609, 431, 95, 62);
		
		frame.getContentPane().add(CarbsBtn);
		
		Image image3 = new ImageIcon(this.getClass().getResource("/res.png")).getImage();
		Reservoir.setHorizontalAlignment(SwingConstants.LEFT);
		Reservoir.setIcon(new ImageIcon(image3));
		
		Reservoir.setBounds(15, 16, 134, 67);
		frame.getContentPane().add(Reservoir);
		
		JLabel lblLastDose = new JLabel("Last Dose:");
		lblLastDose.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLastDose.setBounds(164, 144, 104, 27);
		frame.getContentPane().add(lblLastDose);
		
		
		LastDose.setFont(new Font("Tahoma", Font.PLAIN, 22));
		LastDose.setBounds(273, 144, 104, 27);
		frame.getContentPane().add(LastDose);
		SysMsg.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		SysMsg.setFont(new Font("Tahoma", Font.PLAIN, 28));
		SysMsg.setBounds(164, 175, 323, 34);
		frame.getContentPane().add(SysMsg);
		
		Image image2 = new ImageIcon(this.getClass().getResource("/device.png")).getImage();
		imgback.setIcon(new ImageIcon(image2));
		imgback.setHorizontalAlignment(SwingConstants.CENTER);
		imgback.setFont(new Font("Tahoma", Font.PLAIN, 28));
		imgback.setBounds(56, 99, 560, 237);
		
		frame.getContentPane().add(imgback);
		
		
		SysDebug.setHorizontalAlignment(SwingConstants.CENTER);
		SysDebug.setFont(new Font("Tahoma", Font.PLAIN, 28));
		SysDebug.setBounds(15, 459, 356, 34);
		frame.getContentPane().add(SysDebug);
		
		
		
		
	}
	
	public void SetClock(LocalTime time) {
		Clock.setText(time.toString());
	}
	public void SetMsg(String Msg) {
		SysMsg.setText(Msg);
	}
	public void SetDebug(String Msg) {
		SysDebug.setText(Msg);
	}
	public void SetLastDose(int LateDose) {
		LastDose.setText(Integer.toString(LateDose));
	}
}
