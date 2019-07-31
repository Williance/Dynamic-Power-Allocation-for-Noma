package com.noma.experiment;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.noma.algorithm.hillclimbing.HillClimbingRuntimeParameter;

public class HCPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	private static JButton executeButton = new JButton("execute");

	private JTextField numOfSteps = new JTextField(2);
	private JTextField zeroThreshold = new JTextField(1);
	
	private Thread saThread;
	
	public HCPanel(HillClimbingRuntimeParameter hcParameter, ThreadListener listener) {
		
		saThread=new Thread(Runner.getHCRunnable(listener::after,hcParameter));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Hill Climbing"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("Num of steps"));
		this.add(numOfSteps);
		this.add(new JLabel("zero threshold"));
		this.add(zeroThreshold);
		
		this.add(executeButton);
		updateView(hcParameter);
		executeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!saThread.isAlive()){
					listener.before();
					saThread=new Thread(Runner.getHCRunnable(listener::after, hcParameter));
					saThread.start();
				}
				
			}
		});
		updateView(hcParameter);
	}

	public void updateView(HillClimbingRuntimeParameter parameter) {
		numOfSteps.setText(String.valueOf(parameter.getNumOfSteps()));
		zeroThreshold.setText(String.valueOf(parameter.getZeroThreshold()));
	}

	public HillClimbingRuntimeParameter getParameter() {
		return new HillClimbingRuntimeParameter(Integer.valueOf(numOfSteps.getText()),
				Double.valueOf(zeroThreshold.getText()));
	}
}
