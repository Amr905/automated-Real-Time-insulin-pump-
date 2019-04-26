package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalTime;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import model.HumanBody;

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
	private final JLabel gifimg = new JLabel("");
	private final JLabel insState = new JLabel("");
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 923, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Image image5 = new ImageIcon(this.getClass().getResource("/resov2.png")).getImage();
		insState.setIcon(new ImageIcon(image5));
		insState.setFont(new Font("Tahoma", Font.PLAIN, 27));
		insState.setBounds(186, 139, 117, 33);
		
		frame.getContentPane().add(insState);
		
		
		
		Clock.setFont(new Font("Tahoma", Font.PLAIN, 27));
		Clock.setBounds(318, 139, 70, 33);
		frame.getContentPane().add(Clock);
		
		Image image = new ImageIcon(this.getClass().getResource("/circular-biscuits.png")).getImage();
		BisBtn.setIcon(new ImageIcon(image));
		BisBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Config add sugar
				BtnState(false);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				human.addSuger(60);
		
					
			}
		});
		BisBtn.setBounds(392, 431, 95, 62);
		frame.getContentPane().add(BisBtn);
		
		Image image1 = new ImageIcon(this.getClass().getResource("/carbs.png")).getImage();
		CarbsBtn.setIcon(new ImageIcon(image1));
		CarbsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Config add sugar
				BtnState(false);
				human.addSuger(50);
			}
		});
		CarbsBtn.setBounds(502, 431, 95, 62);
		
		frame.getContentPane().add(CarbsBtn);
		
		Image image3 = new ImageIcon(this.getClass().getResource("/res.png")).getImage();
		Reservoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Reservoir.setHorizontalAlignment(SwingConstants.LEFT);
		Reservoir.setIcon(new ImageIcon(image3));
		
		Reservoir.setBounds(15, 16, 134, 67);
		frame.getContentPane().add(Reservoir);
		
		JLabel lblLastDose = new JLabel("Last Dose:");
		lblLastDose.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLastDose.setBounds(186, 176, 104, 27);
		frame.getContentPane().add(lblLastDose);
		
		
		LastDose.setFont(new Font("Tahoma", Font.PLAIN, 22));
		LastDose.setBounds(295, 176, 104, 27);
		frame.getContentPane().add(LastDose);
		SysMsg.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		SysMsg.setFont(new Font("Tahoma", Font.PLAIN, 28));
		SysMsg.setBounds(175, 210, 323, 34);
		frame.getContentPane().add(SysMsg);
		
		Image image2 = new ImageIcon(this.getClass().getResource("/device.png")).getImage();
		imgback.setIcon(new ImageIcon(image2));
		imgback.setHorizontalAlignment(SwingConstants.CENTER);
		imgback.setFont(new Font("Tahoma", Font.PLAIN, 28));
		imgback.setBounds(56, 99, 587, 285);
		
		frame.getContentPane().add(imgback);
		
		
		SysDebug.setHorizontalAlignment(SwingConstants.CENTER);
		SysDebug.setFont(new Font("Tahoma", Font.PLAIN, 28));
		SysDebug.setBounds(15, 448, 356, 34);
		frame.getContentPane().add(SysDebug);
		
		Image image4 = new ImageIcon(this.getClass().getResource("/notpump.gif")).getImage();
		gifimg.setIcon(new ImageIcon(image4));
		gifimg.setHorizontalAlignment(SwingConstants.CENTER);
		gifimg.setFont(new Font("Tahoma", Font.PLAIN, 28));
		gifimg.setBounds(639, 96, 247, 201);
		
		frame.getContentPane().add(gifimg);
		
		
		
		
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
	
	public void Pumping() {
		Image image4 = new ImageIcon(this.getClass().getResource("/pump.gif")).getImage();
		gifimg.setIcon(new ImageIcon(image4));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notPumping();
	}
	public void notPumping() {
		Image image4 = new ImageIcon(this.getClass().getResource("/notpump.gif")).getImage();
		gifimg.setIcon(new ImageIcon(image4));
	}
	
	public void BtnState (boolean state) {
		BisBtn.setEnabled(state);
		CarbsBtn.setEnabled(state);
	}
	public void ResState(int res) {
		Image image4;
		if (res<= 50) {
			image4 = new ImageIcon(this.getClass().getResource("/resov1.png")).getImage();
		} else {
			image4 = new ImageIcon(this.getClass().getResource("/resov2.png")).getImage();
		}
		insState.setIcon(new ImageIcon(image4));
	}
	
}
