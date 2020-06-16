package it.unipv.ingsw.progettoe20.server.admin.view;

import javax.swing.JFrame;

import it.unipv.ingsw.progettoe20.server.admin.controller.AbstractListener;

/**
 * Classe astratta per indicare i compiti principali delle GUI
 * dell'Administrator
 */

public abstract class AbstractGUI extends JFrame {

	/**
	 * The Listener.
	 */
	protected AbstractListener listener;

	/**
	 * Inizializza una nuova istanza di AbstractGUI.
	 */

	public AbstractGUI() {
		initComponents();
		initListener();
	}

	/**
	 * Serve per l'inizializzazione delle componenti della GUI
	 */
	public abstract void initComponents();

	/**
	 * Serve per inizializzare il listener ai bottoni
	 */
	public abstract void initListener();

}
