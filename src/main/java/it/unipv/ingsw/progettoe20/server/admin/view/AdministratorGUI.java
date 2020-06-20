package it.unipv.ingsw.progettoe20.server.admin.view;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ADMIN_GUI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ADMIN_MANAGEMENT;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.LEVEL_MANAGEMENT;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PARKINGLOTS_MANAGEMENT;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PRICE_MANAGEMENT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Prima schermata di interfaccia dell'amministratore. Offre la scelta tra
 * gestione delle tariffe e gestione dei parcheggi
 */

public class AdministratorGUI extends JFrame {

	private JLabel title;
	private JPanel panel;
	private JButton btnParkings, btnPrices, btnLevels;

	/**
	 * Inizializza una nuova istanza di AdministratorGUI.
	 */
	public AdministratorGUI() {
		super();
	}

	/**
	 * Restituisce il bottone btnParkings
	 *
	 * @return bottone
	 */
	public JButton getBtnParkings() {
		return btnParkings;
	}

	/**
	 * Restituisce il bottone btnPrices
	 *
	 * @return bottone
	 */
	public JButton getBtnPrices() {
		return btnPrices;
	}

	/**
	 * Restituisce il bottone btnLevels
	 *
	 * @return bottone
	 */
	public JButton getBtnLevels() {
		return btnLevels;
	}

	public void initComponents() {

		panel = new JPanel();
		title = new JLabel(ADMIN_MANAGEMENT);
		btnParkings = new JButton(PARKINGLOTS_MANAGEMENT);
		btnPrices = new JButton(PRICE_MANAGEMENT);
		btnLevels = new JButton(LEVEL_MANAGEMENT);

		// frame settings
		getContentPane().add(panel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle(ADMIN_GUI);

		// panel settings
		panel.setBackground(new Color(30, 30, 30));
		panel.setLayout(null);

		// label settings
		title.setFont(new Font(Font.MONOSPACED, 3, 30)); // 3 = bold e italic
		title.setForeground(new Color(196, 10, 255));

		// btnParkings settings
		btnParkings.setFont(new Font(Font.MONOSPACED, 1, 20)); // 1 = bold
		btnParkings.setBackground(new Color(222, 177, 255));

		// btnPrices settings
		btnPrices.setFont(new Font(Font.MONOSPACED, 1, 20));
		btnPrices.setBackground(new Color(222, 177, 255));

		// btnLevels settings
		btnLevels.setFont(new Font(Font.MONOSPACED, 1, 20));
		btnLevels.setBackground(new Color(222, 177, 255));

		// locations settings
		btnParkings.setBounds(120, 180, 400, 40);
		btnLevels.setBounds(120, 130, 400, 40);
		btnPrices.setBounds(120, 230, 400, 40);
		title.setBounds(25, 10, 500, 40);

		panel.add(title);
		panel.add(btnParkings);
		panel.add(btnLevels);
		panel.add(btnPrices);

	}

}
