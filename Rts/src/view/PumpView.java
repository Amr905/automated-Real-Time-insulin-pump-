package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import model.HumanBody;
import model.InsulinPumpSystem;

public class PumpView {

	public JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	HumanBody human;
	InsulinPumpSystem Controller;
	public PumpView(HumanBody human,InsulinPumpSystem Controller) {
		initialize();
		this.human = human;
		this.Controller=Controller ;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Queue <String> Buffer = new LinkedList<String>();
	private final JButton BisBtn = new JButton("");
	private final JButton CarbsBtn = new JButton("");
	JLabel SysMsg = new JLabel("System Starting...");
	JLabel LastDose = new JLabel("0.0");
	LocalTime now = LocalTime.now();
	LocalTime MyTime = LocalTime.of(now.getHour(), now.getMinute());
	private final JLabel imgback = new JLabel("");
	JButton Reservoir = new JButton("");
	JLabel Clock = new JLabel(MyTime.toString());
	JLabel SysDebug = new JLabel("debug");
	private final JLabel gifimg = new JLabel("");
	private final JLabel insState = new JLabel("");
	private final JLabel SugLvl = new JLabel("0.0");
	private final JLabel lblChange = new JLabel("Change Reservoir");
	
	JButton Start = new JButton("Start");
	private final JButton End = new JButton("End");
	private final JButton Reset = new JButton("Reset");
	private final JLabel Up = new JLabel("");
	private final JLabel Down = new JLabel("");
	private final JLabel CurSugarLevel= new JLabel("Current Sugar Level:");
	private Timer timer;
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 923, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Image image5 = new ImageIcon(this.getClass().getResource("/resov1.png")).getImage();
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reset System
			}
		});
		Image downimg = new ImageIcon(this.getClass().getResource("/down.png")).getImage();
		Down.setIcon(new ImageIcon(downimg));
		Down.setFont(new Font("Tahoma", Font.PLAIN, 27));
		Down.setBounds(527, 287, 44, 43);
		
		CurSugarLevel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		CurSugarLevel.setBounds(680, 330, 200, 43);
		frame.getContentPane().add(CurSugarLevel);
		
		
		frame.getContentPane().add(Down);
		Image upimg = new ImageIcon(this.getClass().getResource("/up.png")).getImage();
		Up.setIcon(new ImageIcon(upimg));
		Up.setIcon(new ImageIcon(upimg));
		Up.setFont(new Font("Tahoma", Font.PLAIN, 27));
		Up.setBounds(527, 165, 44, 50);
		
		frame.getContentPane().add(Up);
		Reset.setOpaque(false);
		Reset.setHorizontalAlignment(SwingConstants.RIGHT);
		Reset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Reset.setContentAreaFilled(false);
		Reset.setBorderPainted(false);
		Reset.setBounds(393, 277, 77, 62);
		
		frame.getContentPane().add(Reset);
		End.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//End System
			}
		});
		End.setFont(new Font("Tahoma", Font.PLAIN, 18));
		End.setHorizontalAlignment(SwingConstants.RIGHT);
		End.setOpaque(false);
		End.setContentAreaFilled(false);
		End.setBorderPainted(false);
		End.setBounds(306, 276, 65, 62);
		
		frame.getContentPane().add(End);
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Start System
			}
		});
		
		Start.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Start.setOpaque(false);
		Start.setContentAreaFilled(false);
		Start.setBorderPainted(false);
		Start.setBounds(165, 276, 80, 62);
		frame.getContentPane().add(Start);
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
		
		Image image3 = new ImageIcon(this.getClass().getResource("/resov1.png")).getImage();
		Reservoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Controller.changeReservoir(true);
				ResState(100);
			}
		});
		Reservoir.setIcon(new ImageIcon(image3));
		
		Reservoir.setBounds(15, 16, 125, 50);
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
		SugLvl.setHorizontalAlignment(SwingConstants.CENTER);
		SugLvl.setFont(new Font("Tahoma", Font.PLAIN, 28));
		SugLvl.setBounds(639, 369, 247, 34);
		
		frame.getContentPane().add(SugLvl);
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChange.setBounds(15, 69, 125, 27);
		frame.getContentPane().add(lblChange);
		
		int interval = 5000;
		new Timer(interval, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if (!Buffer.isEmpty()) {
		    		String s = Buffer.remove();
		    		SysMsg.setText(s);
				}
		    }
		}).start();
		
	}
	
	public void BufferEnq(String Msg) {
		Buffer.add(Msg);
	}
	
	public void SetClock(LocalTime time) {
		Clock.setText(time.toString());
	}
	public void SetMsg(String Msg) {
		//SysMsg.setText(Msg);
		BufferEnq(Msg);
		
	}
	public void SetDebug(String Msg) {
		SysDebug.setText(Msg);
	}
	public void SetLastDose(int LateDose) {
		LastDose.setText(Integer.toString(LateDose));
	}
	public void SetSugLvl(int Suglvl) {
		SugLvl.setText(Integer.toString(Suglvl));
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
		Image imageres = null;
		if (res>=75 && res<=100) {
			imageres = new ImageIcon(this.getClass().getResource("/resov1.png")).getImage();
		} else if (res>=50 && res<75) {
			imageres = new ImageIcon(this.getClass().getResource("/resov2.png")).getImage();
		} else if (res>=25 && res<50) {
			imageres = new ImageIcon(this.getClass().getResource("/resov3.png")).getImage();
		} else if (res>=0 && res<25) {
			imageres = new ImageIcon(this.getClass().getResource("/resov4.png")).getImage();
		}
		insState.setIcon(new ImageIcon(imageres));
	}
	

	
}
