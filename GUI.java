import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame{
	ChronoInterface chronoTimer = new ChronoInterface();
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI a = new GUI();
				a.setTitle("Top View");
				a.setSize(800,600);
				a.setVisible(true);
			}
		});
	}
	
	public GUI(){
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1,3));
		
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Panel 1
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3,1));
		
		JPanel p11 = new JPanel();
		TitledBorder t1 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()," ");
		p11.setBorder(t1);
		JButton power = new JButton("Power");
		power.addActionListener(new PowerListener());
		p11.add(power);
		p1.add(p11);
		
		JPanel p12 = new JPanel();
		p12.setLayout(new GridLayout(2,2));
		TitledBorder t2 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "EVENT");
		t2.setTitleJustification(TitledBorder.CENTER);
		p12.setBorder(t2);
		String[] event = {"IND","PARIND","GRP","PARGRP"};
		JRadioButton[] jb = new JRadioButton[4];
		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i < 4; i++){
			JPanel p121 = new JPanel();
			jb[i] = new JRadioButton(event[i]);
//			if(ChronoInterface.chronoTimer.power.powerStatus){
//				jb[i].addActionListener(new EventListener(event[i]));
//			}
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
		resultPane = new ResultPane();
		resultPane.setPreferredSize(new Dimension(350,150));
		p22.add(resultPane,BorderLayout.CENTER);
		p2.add(p22);
		contentPane.add(p2);

		
		JPanel p3 = new JPanel();
		
		contentPane.add(p3);
		
	}
	
	private class PowerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			chronoTimer.power();
		}
	}
	
	private class EventListener implements ActionListener{
		String evt;
		EventListener(String str){
			evt = str;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			chronoTimer.event(evt);
		}
	}
	
	private class SwapListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			chronoTimer.swap();
		}
	}
	
	private class TrigListener implements ActionListener {
		String channel;
		Channel c;
		TrigListener(int i){
			channel = String.valueOf(i);
			c = new Channel(i);
		}
		public void actionPerformed(ActionEvent e) {
			c.trig();
			chronoTimer.trig(channel);
		}
	}
	
	private class TogListener implements ActionListener {
		String arm;
		Channel c;
		TogListener(int i){
			arm = String.valueOf(i);
			c = new Channel(i);
		}

		public void actionPerformed(ActionEvent e) {
			JRadioButton j =  (JRadioButton) e.getSource();
			if(j.isSelected()){
				c.isArmed = true;
			}
			else{
				c.isArmed = false;
			}
			chronoTimer.tog(arm);
		}
	}
	
	private ResultPane resultPane;
	
	private class ResultPane extends JPanel {
		private static final long serialVersionUID = 1L;
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.white);
		}
	}
}
