package it.unipv.ingsw.progettoe20.server.admin.view;

import javax.swing.JFrame;

import it.unipv.ingsw.progettoe20.server.admin.controller.AbstractListener;

/*
 * Classe astratta per indicare i compiti delle GUI dell'Administrator
 */

/**
 * The type Abstract gui.
 */
public abstract class AbstractGUI extends JFrame {

	/**
	 * The Listener.
	 */
	protected AbstractListener listener;

	/**
	 * Instantiates a new Abstract gui.
	 */
	/*
	 * Costruttore
	 */
	public AbstractGUI() {
		initComponents();
		initListener();
	}

	/**
	 * Init components.
	 */
	/*
	 * Server per l'inizializzazione delle componenti della GUI
	 */
	public abstract void initComponents();

	/**
	 * Init listener.
	 */
	/*
	 * Serve per inizializzare il listener ai bottoni
	 */
	public abstract void initListener();

}
