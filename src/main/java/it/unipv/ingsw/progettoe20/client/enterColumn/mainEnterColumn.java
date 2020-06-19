package it.unipv.ingsw.progettoe20.client.enterColumn;


	import java.io.IOException;

	import javax.swing.JFrame;

import it.unipv.ingsw.progettoe20.client.ClientStrings;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
	import it.unipv.ingsw.progettoe20.client.enterColumn.controller.Controller;
import it.unipv.ingsw.progettoe20.client.enterColumn.model.EnterColumn;
import it.unipv.ingsw.progettoe20.client.enterColumn.view.EnterColumnGui;


	/**
	 * The type Test.
	 */
	public class mainEnterColumn {

		/**
		 * The entry point of application.
		 *
		 * @param args the input arguments
		 * @throws IOException the io exception
		 */
		public static void main(String[] args) throws IOException {
				 args = new String[1];
		         args[0] = "cli"; //per testare velocemente l'interfaccia testuale
				 if (args.length != 0 && args[0].equals(ClientStrings.COMMAND_CLI)) {
					 EnterColumn A1= new EnterColumn(args[0]);
					 
			        } else {
			        EnterColumn A1= new EnterColumn("");
			        EnterColumnGui gui= new EnterColumnGui(A1);
					A1.addObserver(gui);
					Controller c= new Controller(gui, A1);
					gui.setVisible(true);
					gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    gui.pack(); 
			        }
			 
			

			}
	}

