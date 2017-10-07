package com.troop66matawan.tm;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.IndividualHistoryImporter;
import com.troop66matawan.tm.importer.LeadershipPositionDaysNeededImporter;
import com.troop66matawan.tm.importer.MeritBadgesEarnedImporter;
import com.troop66matawan.tm.importer.RankBadgeMatrixImporter;
import com.troop66matawan.tm.importer.ScoutDataImporter;
import com.troop66matawan.tm.importer.ScoutIndividualParticipationImporter;

public class ExportToFirebaseSwing {

	private JFrame frame;
	private JTextField firebasePath;
	private JTextField mbPath;
	private JTextField rankAdvPath;
	private JTextField scoutDataPath;
	private JTextField indivPartPath;
	private JTextField indivHistoryPath;
	private JTextField leadershipPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExportToFirebaseSwing window = new ExportToFirebaseSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExportToFirebaseSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Export to Firebase");
		frame.setBounds(100, 100, 778, 557);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Firebase Auth");
		panel.add(lblNewLabel);
		
		firebasePath = new JTextField();
		panel.add(firebasePath);
		firebasePath.setColumns(45);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					firebasePath.setText(file.getAbsolutePath());
				}
			}
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("Merit Badges");
		panel_1.add(label);
		
		mbPath = new JTextField();
		mbPath.setColumns(45);
		panel_1.add(mbPath);
		
		JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					mbPath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_1 = new JLabel("Rank Advancement");
		panel_2.add(label_1);
		
		rankAdvPath = new JTextField();
		rankAdvPath.setColumns(45);
		panel_2.add(rankAdvPath);
		
		JButton button_1 = new JButton("Browse");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					rankAdvPath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_2.add(button_1);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_2 = new JLabel("Scout Data");
		panel_3.add(label_2);
		
		scoutDataPath = new JTextField();
		scoutDataPath.setColumns(45);
		panel_3.add(scoutDataPath);
		
		JButton button_2 = new JButton("Browse");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					scoutDataPath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_3.add(button_2);
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_3 = new JLabel("Indiv Participation");
		panel_4.add(label_3);
		
		indivPartPath = new JTextField();
		indivPartPath.setColumns(45);
		panel_4.add(indivPartPath);
		
		JButton button_3 = new JButton("Browse");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					indivPartPath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_4.add(button_3);
		
		JPanel panel_5 = new JPanel();
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_4 = new JLabel("Indiv History");
		panel_5.add(label_4);
		
		indivHistoryPath = new JTextField();
		indivHistoryPath.setColumns(45);
		panel_5.add(indivHistoryPath);
		
		JButton button_4 = new JButton("Browse");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					indivHistoryPath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_5.add(button_4);
		
		JPanel panel_6 = new JPanel();
		frame.getContentPane().add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_5 = new JLabel("Leadership");
		panel_6.add(label_5);
		
		leadershipPath = new JTextField();
		leadershipPath.setColumns(45);
		panel_6.add(leadershipPath);
		
		JButton button_5 = new JButton("Browse");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					leadershipPath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_6.add(button_5);
		
		
		JPanel panel_8 = new JPanel();
		frame.getContentPane().add(panel_8);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doImport();
				ExportToFirebase.export(firebasePath.getText());
			}
		});
		
		panel_8.add(btnExport);
	}

	void doImport() {
		try {
			MeritBadgesEarnedImporter mbi = new MeritBadgesEarnedImporter(this.mbPath.getText());
			mbi.doImport();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try{
			RankBadgeMatrixImporter rai = new RankBadgeMatrixImporter(this.rankAdvPath.getText());
			rai.doImport();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			ScoutDataImporter sdi = new ScoutDataImporter(this.scoutDataPath.getText(), true);
			sdi.doImport();
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (HeaderFormatException e) {
			System.err.println("Unable to parse header for Scout data");
			System.exit(1);
		}
		try {
			ScoutIndividualParticipationImporter sipi = new ScoutIndividualParticipationImporter(this.indivPartPath.getText());
			sipi.doImport();
		} catch (IOException e ) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			IndividualHistoryImporter sipi = new IndividualHistoryImporter(this.indivHistoryPath.getText());
			sipi.doImport();
		} catch (IOException e ) {
			e.printStackTrace();
			System.exit(1);			
		}		
		try {
			LeadershipPositionDaysNeededImporter lpi = new LeadershipPositionDaysNeededImporter(this.leadershipPath.getText());
			lpi.doImport();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
