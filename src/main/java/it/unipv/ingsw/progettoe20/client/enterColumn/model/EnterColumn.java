package it.unipv.ingsw.progettoe20.client.enterColumn.model;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.client.ClientConstants;

import java.io.BufferedReader;
import java.util.Observable;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * The type Enter column.
 */
public class EnterColumn extends Observable{
	private int totalLot;
	private BufferedReader in;
	private PrintWriter out;
	private boolean isConnected=false;
	private String answer;
	private Socket clientSocket;
	private String inputType;
	private  String id="";

	/**
	 * costruttore
	 *
	 * @param inputType the input type
	 */
	public EnterColumn(String inputType)  {
		this.inputType = inputType;//gli viene passato dal tester (args[0])
		checkServerConnection();
		setAvailability();
	}

	/**
	 * metodo che imposta la connessione al database
	 *
	 * @return true se il database � connesso
	 */
	public void checkServerConnection() {
		
		   try {  
			    clientSocket = new Socket(ClientConstants.HOST, ClientConstants.PORT);
	            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            isConnected = true;
	            checkInputType();
	        } catch (IOException i) {
	            isConnected = false;
	        }
	}

	/**
	 * metodo che chiude la socket
	 */
	public void closeSocket() {
	        try {
	            clientSocket.close();
	        }
	        catch (IOException i ){
	            System.out.println("Socket Error");
	        }
	        catch ( NullPointerException n) {
	            isConnected = false;
	        }
	    }

	/**
	 * metodo che manda la richiesta per la generazione del Ticket
	 *
	 * @param
	 * @return true se l'id è stato generato correttamente, false se invece non lo è
	 * @throws IOException the io exception
	 */
	public Boolean genTicket() throws IOException {
		   try {
	            out.println(Protocol.REQUEST_GENERATE_ID);
	            answer = in.readLine();
	            id=answer.substring(5,answer.length());
	            if(id.equals("?")) {
	            	return false;
	            } 
	            else{
	            System.out.println(answer);
	            return true;
	            }
	            
	        } catch (IOException i) {
	            return false;
	        } catch (NullPointerException n) {
	            isConnected = false;
	            return false;
	        }
	 }


	/**
	 * Check input type.
	 *
	 * @throws IOException the io exception
	 */
	public void checkInputType() throws IOException {
	        if (inputType.equals("cli")) {
	            cli();
	        } else System.out.println("GUI avviata");

	    }
	  /**
		* Metodo che rappresenta l'interfaccia testuale
		* @throws IOException 
		*/
	  private void cli() throws IOException {
		 String insertText = "";
		 Scanner scanner = new Scanner(System.in);
		 while (true) {
		  System.out.println("Hai scelto la modlità command line input, inserisci gen se vuoi prelevare il ticket o exit per terminare.");
		  insertText = scanner.next();
		  if (insertText.equals("exit")) break;
		  if (insertText.equals("gen")) {
		        	this.setAvailability();
		            System.out.println("Numero posti disponibili:"+this.totalLot);
		            if(genTicket()) {
		              		this.setAvailability();
		                	System.out.println("Livello associato:"+ this.id.substring(0,1));
		                	System.out.println("Numero posti disponibili aggiornato:"+this.totalLot);
		            } else
		           System.out.println("Id non generato");
		   }}
		   System.out.println("Hai terminato l'esecuzione");
		   scanner.close();
		   System.exit(0);
	     }

	/**
	 * metodo che controlla la connessione
	 *
	 * @return the is conn
	 */
	public boolean getIsConn() {
		
		return this.isConnected;
	}


	/**
	 * metodo che manda la richiesta di aggiornamento dei posti disponibili del parcheggio
	 *
	 * @return true se la richiesta � stata portata a termine correttamente, false se invece non lo è
	 */
	public Boolean setAvailability() {
		 try {
	            out.println(Protocol.REQUEST_TOTAL_AVAILABILITY);
	            answer = in.readLine();
	            
	            String stringLot=answer.substring(5,answer.length());
	            totalLot=Integer.parseInt(stringLot);
	            if(answer.equals(Protocol.RESPONSE_OK + ":"+ totalLot))
	            {return true;}
	            else {return false;}
	            
	        } catch (IOException i) {
	            return false;
	        } catch (NullPointerException n) {
	            isConnected = false;
	            return false;
	        }
	    }


	/**
	 * Gets id ticket.
	 *
	 * @return the id ticket
	 */
	public String getIdTicket() {
		int countString= answer.length();
		String id= answer.substring(5, countString);
		return id;
		
	}

	/**
	 * Gets availability.
	 *
	 * @return the availability
	 */
	public int getAvailability() {
		return this.totalLot;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.getId();
	}

	/**
	 * Sets availability.
	 *
	 * @param availability the availability
	 */
	public void setAvailability(int availability) {
	    this.totalLot=availability;
	    this.setChanged();
	    this.notifyObservers();
		
	}
	
}
