package it.unipv.ingsw.progettoe20.server.admin.view;

import javax.swing.JFrame;

import it.unipv.ingsw.progettoe20.server.admin.controller.AbstractListener;

/*
 * Classe astratta per indicare i compiti delle GUI dell'Administrator
 */
public abstract class AbstractGUI extends JFrame {

	protected AbstractListener listener;

	/*
	 * Costruttore
	 */
	public AbstractGUI() {
		initComponents();
		initListener();
	}

	/*
	 * Server per l'inizializzazione delle componenti della GUI
	 */
	public abstract void initComponents();

	/*
	 * Serve per inizializzare il listener ai bottoni
	 */
	public abstract void initListener();

}
