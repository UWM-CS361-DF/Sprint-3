import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;

	JTextArea printer;
	String[] sensorTypes = {"Gate", "Eye", "Pad", "Manual"};
	String[] sensors=new String[8];
	JTextArea display;
	public GUI(){
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1,3));
		
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Panel 1
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(4,1));
		
		JPanel p11 = new JPanel();
		TitledBorder t1 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()," ");
		p11.setBorder(t1);
		JButton power = new JButton("Power");
		power.addActionListener(new PowerListener());
		p11.add(power);
		p1.add(p11);
		
		//Panel for new run / end run
		JPanel p15 = new JPanel();
		p15.setLayout(new GridLayout(1,2));

		JPanel p10 = new JPanel();
		JButton newRun = new JButton("New Run");
		newRun.addActionListener(new NewRunListener());
		p10.add(newRun);
		p15.add(p10);
		
		JPanel p14 = new JPanel();
		JButton endRun = new JButton("End Run");
		endRun.addActionListener(new EndRunListener());
		p14.add(endRun);
		p15.add(p14);
		
		p1.add(p15);
		
		JPanel p12 = new JPanel();
		p12.setLayout(new GridLayout(3,2));
		TitledBorder t2 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "EVENT");
		t2.setTitleJustification(TitledBorder.CENTER);
		p12.setBorder(t2);
		String[] event = {"IND","PARIND","GRP","PARGRP"};
		JRadioButton[] jb = new JRadioButton[4];
		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i < 4; i++){
			JPanel p121 = new JPanel();
			jb[i] = new JRadioButton(event[i]);
			jb[i].addActionListener(new EventListener(event[i]));
			bg.add(jb[i]);
			p121.add(jb[i]);
			p12.add(p121);
		}
		p1.add(p12);
		
		JPanel p13 = new JPanel();
		TitledBorder t3 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()," ");
		p13.setBorder(t3);
		JButton swap = new JButton("SWAP");
		swap.addActionListener(new SwapListener());
		p13.add(swap);
		p1.add(p13);
		
		contentPane.add(p1);
		
		//Panel 2
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(2,1));
		
		JPanel p21 = new JPanel();
		TitledBorder t4 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),"CHRONOTIMER 1009");
		t4.setTitleJustification(TitledBorder.CENTER);
		p21.setBorder(t4);
		p21.setLayout(new GridLayout(6,1));
		
		JButton[] channel = new JButton[9];
		JRadioButton[] arm = new JRadioButton[9];
		for(int i = 0; i < 6; i++){
			JPanel p211 = new JPanel();
			p211.setLayout(new FlowLayout());
			if(i == 0){
				String[] label1 = {"             ","         1","          3","          5","         7"};
				for(int j = 0; j < 5; j++){
					JLabel n1 = new JLabel(label1[j]);
					p211.add(n1);
				}
			}
			if(i == 1){
				JLabel start = new JLabel("         Start          ");
				p211.add(start);
				for(int j = 1; j < 9; j+=2){
					channel[j] = new JButton();
					channel[j].setPreferredSize(new Dimension(15,15));
					channel[j].addActionListener(new TrigListener(j));
					JLabel empty = new JLabel("     ");
					p211.add(channel[j]);
					p211.add(empty);
				}
			}
			if(i == 2){
				JLabel start = new JLabel("Enable/Disable");
				p211.add(start);
				for(int j = 1; j < 9; j+=2){
					arm[j] = new JRadioButton();
					arm[j].addActionListener(new TogListener(j));
					JLabel empty = new JLabel("   ");
					p211.add(arm[j]);
					p211.add(empty);
				}
			}
			if(i == 3){
				String[] label2 = {"             ","          2","          4","          6","         8"};
				for(int j = 0; j < 5; j++){
					JLabel n2 = new JLabel(label2[j]);
					p211.add(n2);
				}
			}
			if(i == 4){
				JLabel finish = new JLabel("         Finish         ");
				p211.add(finish);
				for(int j = 2; j < 9; j+=2){
					channel[j] = new JButton();
					channel[j].setPreferredSize(new Dimension(15,15));
					channel[j].addActionListener(new TrigListener(j));
					JLabel empty = new JLabel("     ");
					p211.add(channel[j]);
					p211.add(empty);
				}
			}
			if(i == 5){
				JLabel start = new JLabel("Enable/Disable");
				p211.add(start);
				for(int j = 2; j < 9; j+=2){
					arm[j] = new JRadioButton();
					arm[j].addActionListener(new TogListener(j));
					JLabel empty = new JLabel("   ");
					p211.add(arm[j]);
					p211.add(empty);
				}
			}
			p21.add(p211);
		}
		p2.add(p21);
		
		JPanel p22 = new JPanel();
		p22.setLayout(new BorderLayout());
		p22.setBorder(BorderFactory.createLineBorder(Color.black));
		display = new JTextArea(250,150);
		display.setEditable(false);
		p22.add(display, BorderLayout.CENTER);
		p2.add(p22);
		contentPane.add(p2);

		ActionListener updateClockAction = new ClockListener();
		Timer t = new Timer(100, updateClockAction);
		t.start();
		
		JPanel p3 = new JPanel();
		
		p3.setLayout(new GridLayout(3,1));
		
		JPanel p31 = new JPanel();
		TitledBorder t5 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()," ");
		p31.setBorder(t5);
		JButton printerPower = new JButton("Printer Power");
		printerPower.addActionListener(new PrinterPowerListener());	   
		printer = new JTextArea(8, 16);
	    printer.setEditable(false); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane(printer);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p31.add(printerPower);
		p31.add(scroll);
		p3.add(p31);

		JPanel p32 = new JPanel();
		p32.setLayout(new GridLayout(4,3));
		String[] keypad = {"7", "8", "9", "4", "5", "6", "1","2", "3", ".", "0", "#"};	
		for(int i=0; i<keypad.length; i++){
			JButton keypadButton = new JButton(keypad[i]);
			p32.add(keypadButton);
			keypadButton.addActionListener(new DigitListener(keypad[i]));
		}
		TitledBorder t6 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()," ");
		p32.setBorder(t6);
		p3.add(p32);
		
		JPanel p33 = new JPanel();
		TitledBorder t7 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()," ");
		p33.setBorder(t7);
		p33.setLayout(new GridLayout(2,4));
		for (int i = 1; i<=8; i++) {
			JPanel channels = new JPanel();
			channels.setLayout(new GridLayout(3,1));
			JLabel channelNumber = new JLabel("" + i + "");
			JRadioButton channelToggle = new JRadioButton();
			channelToggle.addActionListener(new ChannelToggleListener(i));
			JComboBox<String> channelSensors = new JComboBox<String>(sensorTypes);
			channelSensors.addActionListener(new SensorTypeListener(i, channelSensors));
			channelSensors.setSelectedIndex(3);
			
			channels.add(channelNumber);
			channels.add(channelToggle);
			channels.add(channelSensors);
			p33.add(channels);
		}
		
		
		p3.add(p33);
		contentPane.add(p3);
		
	}
	
	private class PowerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ChronoInterface.chronoTimer.power();
		}
	}
	
	private class NewRunListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ChronoInterface.chronoTimer.newrun();
		}
	}
	
	private class EndRunListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ChronoInterface.chronoTimer.endrun();
			} catch (Exception e1) {}
		}
	}
	
	private class SensorTypeListener implements ActionListener {
		public SensorTypeListener(int i, JComboBox<String> channelSensors) {
			channelSensors.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt){
					if(evt.getStateChange()==ItemEvent.SELECTED){
						try{
							sensors[i] = evt.getItem().toString();
						}catch(Exception e){}
					}
				}
			});
		}

		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class ChannelToggleListener implements ActionListener {
		String c;
		int cInt;
		ChannelToggleListener(int i){
			cInt=i;
			c=String.valueOf(i);
		}
		public void actionPerformed(ActionEvent e) {
			JRadioButton channel = (JRadioButton) e.getSource();
			if(channel.isSelected())
				ChronoInterface.chronoTimer.conn(sensors[cInt], c);
			else
				ChronoInterface.chronoTimer.disc(c);
		}
	}	
	
	private class PrinterPowerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ChronoInterface.chronoTimer.printer();
		}
	}
	String num="";
	private class DigitListener implements ActionListener {
		String n;
		DigitListener(String s){
			n = s;
		}
		public void actionPerformed(ActionEvent e) {
			if(n.equals("#")){
				ChronoInterface.chronoTimer.num(num);
				num="";
			}
			else
				num=num+n;
				
		}
	}
	
	private class EventListener implements ActionListener{
		String evt;
		EventListener(String str){
			evt = str;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			ChronoInterface.chronoTimer.event(evt);
		}
	}
	
	private class SwapListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ChronoInterface.chronoTimer.swap();
		}
	}
	
	private class TrigListener implements ActionListener {
		String channel;
		TrigListener(int i){
			channel = String.valueOf(i);
		}
		public void actionPerformed(ActionEvent e) {
			ChronoInterface.chronoTimer.trig(channel);
		}
	}
	
	private class TogListener implements ActionListener {
		String arm;
		TogListener(int i){
			arm = String.valueOf(i);
		}

		public void actionPerformed(ActionEvent e) {
			ChronoInterface.chronoTimer.tog(arm);
		}
	}
	
	private class ClockListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(ChronoInterface.chronoTimer!=null)
				display.setText(ChronoInterface.chronoTimer.displayRun());
		}
	}
	public void output(String out){
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		      printer.append(out);
		    }
	 });
}}

