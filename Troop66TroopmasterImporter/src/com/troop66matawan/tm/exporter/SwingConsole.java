package com.troop66matawan.tm.exporter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.troop66matawan.utils.Output;

public class SwingConsole implements Output{

	private JFrame frame;
	private JTextArea text;
	private JButton okBtn;
	
	public SwingConsole(String title) {
		initialize(title);
	}
	
	private void initialize(String title) {
		frame = new JFrame(title);
		frame.setBounds(150, 150, 300, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0,1,0,0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
	
		text = new JTextArea();
		JScrollPane scroll = new JScrollPane (text, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		
		okBtn = new JButton("Close");
		okBtn.setEnabled(false);
		okBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		panel.add(okBtn);
	}
	public void reset() {
		text.setText("");
		btnEnabled(false);
		show(false);
	}
	public void append(String data) {
		text.append(data);
	}
	
	public void btnEnabled(boolean en) {
		okBtn.setEnabled(en);
	}
	
	public void show(boolean v) {
		frame.setVisible(v);
	}
	
	public void append(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		append(sw.toString());
	}
}
