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

import com.troop66matawan.tm.exporter.SwingConsole;
import com.troop66matawan.tm.importer.AdultDataImporter;
import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.IndividualHistoryImporter;
import com.troop66matawan.tm.importer.LeadershipPositionDaysNeededImporter;
import com.troop66matawan.tm.importer.MeritBadgesEarnedImporter;
import com.troop66matawan.tm.importer.RankBadgeMatrixImporter;
import com.troop66matawan.tm.importer.ScoutDataImporter;
import com.troop66matawan.tm.importer.ScoutIndividualParticipationImporter;
import com.troop66matawan.tm.importer.ServiceProjectHoursNeededImporter;

public class ExportToFirebaseSwing {

	private JFrame frame;
	private JTextField firebasePath;
	private JTextField reportDirPath;
	private JTextField mbPath;
	private JTextField rankAdvPath;
	private JTextField scoutDataPath;
	private JTextField indivPartPath;
	private JTextField indivHistoryPath;
	private JTextField leadershipPath;
	private JTextField adultDataPath;
	private JTextField servicePath;
	private SwingConsole console;
	private JButton btnInitFirebase;
	private JButton btnImport;
	private JButton btnExportScout;
	private JButton btnExportUsers;
	File reportDir= null;
	

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
		console = new SwingConsole("Export to Firebase - console");
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

		JPanel panelDir = new JPanel();
		frame.getContentPane().add(panelDir);
		
		JLabel lblReportIdr  = new JLabel("Report Path");
		panelDir.add(lblReportIdr);
		
		reportDirPath = new JTextField();
		panelDir.add(reportDirPath);
		reportDirPath.setColumns(45);
		
		JButton btnReportPath = new JButton("Browse");
		btnReportPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					reportDir = fc.getSelectedFile();
					reportDirPath.setText(reportDir.getAbsolutePath());
				}								
			}
		});
		panelDir.add(btnReportPath);
		
		
		
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
				if (reportDir != null ) {
					fc.setCurrentDirectory(reportDir);
				}
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
				if (reportDir != null ) {
					fc.setCurrentDirectory(reportDir);
				}
				
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
				if (reportDir != null ) {
					fc.setCurrentDirectory(reportDir);
				}
				
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
				
				if (reportDir != null ) {
					fc.setCurrentDirectory(reportDir);
				}
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
				if (reportDir != null ) {
					fc.setCurrentDirectory(reportDir);
				}
				
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
				if (reportDir != null ) {
					fc.setCurrentDirectory(reportDir);
				}
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					leadershipPath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_6.add(button_5);
		
		JPanel panel_7 = new JPanel();
		frame.getContentPane().add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_7 = new JLabel("Adult Data");
		panel_7.add(label_7);
		
		adultDataPath = new JTextField();
		adultDataPath.setColumns(45);
		panel_7.add(adultDataPath);
		
		JButton button_7 = new JButton("Browse");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				if (reportDir != null ) {
					fc.setCurrentDirectory(reportDir);
				}
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					adultDataPath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_7.add(button_7);
		
		JPanel panel_Service = new JPanel();
		frame.getContentPane().add(panel_Service);
		panel_Service.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_8 = new JLabel("Service Hours");
		panel_Service.add(label_8);
		
		servicePath = new JTextField();
		servicePath.setColumns(45);
		panel_Service.add(servicePath);
		
		JButton button_8 = new JButton("Browse");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				if (reportDir != null ) {
					fc.setCurrentDirectory(reportDir);
				}
				
				int retval = fc.showOpenDialog(frame);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					servicePath.setText(file.getAbsolutePath());
				}
			}
		});
		panel_Service.add(button_8);
		
		JPanel panel_8 = new JPanel();
		frame.getContentPane().add(panel_8);
		btnInitFirebase = new JButton("Init Firebase");
		btnInitFirebase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.reset();
				ExportToFirebase.initializeFirebase(firebasePath.getText(), console);
				btnInitFirebase.setEnabled(false);
				if (!btnImport.isEnabled()) {
					btnExportScout.setEnabled(true);
					btnExportUsers.setEnabled(true);
				}
			}
		});
		
		btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.reset();
				if (doImport()) {
					btnImport.setEnabled(false);
					if (!btnInitFirebase.isEnabled()){
						btnExportScout.setEnabled(true);
						btnExportUsers.setEnabled(true);
					}
				}
			}
		});
		
		btnExportScout = new JButton("Export Scouts");
		btnExportScout.setEnabled(false);
		btnExportScout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.reset();
				ExportToFirebase.export(console);
			}
		});
		
		btnExportUsers = new JButton("Export Users");
		btnExportUsers.setEnabled(false);
		btnExportUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.reset();
				console.show(true);
				ExportToFirebase.updateUserSettings(console);
			}
		});
	
		panel_8.add(btnInitFirebase);
		panel_8.add(btnImport);
		panel_8.add(btnExportScout);
		panel_8.add(btnExportUsers);
	}

	boolean  doImport() {
		try {
			if (mbPath.getText().length() > 0) {
				MeritBadgesEarnedImporter mbi = new MeritBadgesEarnedImporter(this.mbPath.getText());
				mbi.doImport();
			}
		} catch (IOException e) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		}
		try{
			if (rankAdvPath.getText().length() > 0) {
				RankBadgeMatrixImporter rai = new RankBadgeMatrixImporter(this.rankAdvPath.getText());
				rai.doImport();
			}
		} catch (IOException e) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		}
		try {
			if (scoutDataPath.getText().length() > 0) {
				ScoutDataImporter sdi = new ScoutDataImporter(this.scoutDataPath.getText(), true);
				sdi.doImport();
			}
		}catch (IOException e) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		} catch (HeaderFormatException e) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		}
		try {
			if (adultDataPath.getText().length() > 0) {
				AdultDataImporter adi = new AdultDataImporter(this.adultDataPath.getText(), true);
				adi.doImport();
			}
		}catch (IOException e) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		} catch (HeaderFormatException e) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		}
		try {
			if (indivPartPath.getText().length() > 0) {
				ScoutIndividualParticipationImporter sipi = new ScoutIndividualParticipationImporter(this.indivPartPath.getText());
				sipi.doImport();
			}
		} catch (IOException e ) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		}
		try {
			if (indivHistoryPath.getText().length() > 0) {
				IndividualHistoryImporter sipi = new IndividualHistoryImporter(this.indivHistoryPath.getText());
				sipi.doImport();
			}
		} catch (IOException e ) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		}		
		try {
			if (leadershipPath.getText().length() > 0) {
				LeadershipPositionDaysNeededImporter lpi = new LeadershipPositionDaysNeededImporter(this.leadershipPath.getText());
				lpi.doImport();
			}
		} catch (IOException e) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		}
		try {
			if (servicePath.getText().length() > 0) {
				ServiceProjectHoursNeededImporter spi = new ServiceProjectHoursNeededImporter(this.servicePath.getText());
				spi.doImport();
			}
		} catch (IOException e) {
			console.show(true);
			console.append(e);
			console.btnEnabled(true);
			return false;
		}
		return true;
	}
	
}
