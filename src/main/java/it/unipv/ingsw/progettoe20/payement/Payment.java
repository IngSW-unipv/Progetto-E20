package it.unipv.ingsw.progettoe20.payement;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Payment extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //to have unique id for frame
	private JPanel contentPane; //define the content to add into the frame
	private double price; //receive the price by server to pay
	/**
	 * Create the frame payment.
	 *
	 * @param price prezzo da pagare
	 */
	public Payment(double price) {
		//initialize the value of price
		this.price=price;
		
		//set the parameters of payment window
		setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 797, 551);
		
		//parameters for panel
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//define label for Payment  with all the text
		JLabel lblNewLabel = new JLabel("Payment Module");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
		lblNewLabel.setBounds(303, 47, 281, 33);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Amount of Payment      :");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(128, 130, 236, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lbl_payment=new JLabel(price+"‎€");
		lbl_payment.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lbl_payment.setBounds(481, 138, 59, 16);
		contentPane.add(lbl_payment);
		
		
		//define label for name of different price to choose
		
		JLabel lblChoose = new JLabel("Choose Piece of Money   :");
		lblChoose.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblChoose.setBounds(117, 199, 247, 33);
		contentPane.add(lblChoose);
		
		JLabel lbl_1c = new JLabel("1\u00B0"); //uno centesimi
		lbl_1c.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lbl_1c.setBounds(380, 207, 33, 16);
		contentPane.add(lbl_1c);
		
		JLabel lbl_2c = new JLabel("2\u00B0");//due centesimi
		lbl_2c.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
		lbl_2c.setBounds(491, 207, 24, 15);
		contentPane.add(lbl_2c);
		
		JLabel lbl_1e = new JLabel("1 \u20AC");
		lbl_1e.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lbl_1e.setBounds(380, 310, 33, 16);
		contentPane.add(lbl_1e);
		
		JLabel lbl_2e = new JLabel("2\u20AC");
		lbl_2e.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lbl_2e.setBounds(482, 312, 33, 16);
		contentPane.add(lbl_2e);
		
		JLabel lbl_5c = new JLabel("5\u00B0");
		lbl_5c.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lbl_5c.setBounds(608, 206, 33, 16);
		contentPane.add(lbl_5c);
		
		JLabel lbl_10c = new JLabel("10\u00B0");
		lbl_10c.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
		lbl_10c.setBounds(380, 255, 34, 14);
		contentPane.add(lbl_10c);
		
		JLabel lbl_20c = new JLabel("20\u00B0");
		lbl_20c.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lbl_20c.setBounds(485, 255, 40, 16);
		contentPane.add(lbl_20c);
		
		JLabel lbl_50c = new JLabel("50\u00B0");
		lbl_50c.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lbl_50c.setBounds(601, 255, 40, 16);
		contentPane.add(lbl_50c);
		
		//definire i comboBox per tutti i diversi prezzi in scelta
		JComboBox comboBox_2e = new JComboBox();
		comboBox_2e.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBox_2e.setToolTipText("");
		comboBox_2e.setBounds(527, 312, 40, 16);
		contentPane.add(comboBox_2e);
		
		JComboBox comboBox_1e = new JComboBox();
		comboBox_1e.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBox_1e.setToolTipText("");
		comboBox_1e.setBounds(419, 312, 40, 16);
		contentPane.add(comboBox_1e);
		
		JComboBox comboBox_2c = new JComboBox();
		comboBox_2c.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBox_2c.setToolTipText("");
		comboBox_2c.setBounds(527, 205, 40, 16);
		contentPane.add(comboBox_2c);
		
		JComboBox comboBox_5c = new JComboBox();
		comboBox_5c.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBox_5c.setToolTipText("");
		comboBox_5c.setBounds(653, 205, 40, 16);
		contentPane.add(comboBox_5c);
	
		
		JComboBox comboBox_10c = new JComboBox();
		comboBox_10c.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBox_10c.setToolTipText("");
		comboBox_10c.setBounds(419, 255, 40, 16);
		contentPane.add(comboBox_10c);
		

		
		JComboBox comboBox_20c = new JComboBox();
		comboBox_20c.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBox_20c.setToolTipText("");
		comboBox_20c.setBounds(527, 255, 40, 16);
		contentPane.add(comboBox_20c);
		
		
		
		JComboBox comboBox_50c = new JComboBox();
		comboBox_50c.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBox_50c.setToolTipText("");
		comboBox_50c.setBounds(653, 252, 40, 16);
		contentPane.add(comboBox_50c);
		
	
		
		JComboBox comboBox_1c = new JComboBox();
		comboBox_1c.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBox_1c.setToolTipText("");
		comboBox_1c.setBounds(419, 205, 40, 16);
		contentPane.add(comboBox_1c);
		
		//definire il pulsante per il pagamento 
		JButton btn_payment = new JButton("Payment");
		//aggiungere l'evento 
		btn_payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float result=0;
				result+=Float.parseFloat((String)comboBox_1c.getSelectedItem())*0.01;
				result+=Float.parseFloat((String)comboBox_2c.getSelectedItem())*0.02;
				result+=Float.parseFloat((String)comboBox_5c.getSelectedItem())*0.05;
				result+=Float.parseFloat((String)comboBox_10c.getSelectedItem())*0.1;
				result+=Float.parseFloat((String)comboBox_20c.getSelectedItem())*0.2;
				result+=Float.parseFloat((String)comboBox_50c.getSelectedItem())*0.5;
				result+=Float.parseFloat((String)comboBox_1e.getSelectedItem())*1;
				result+=Float.parseFloat((String)comboBox_2e.getSelectedItem())*2;
				
				//caso del prezzo inserito insufficiente
				if(result<price) {
					JOptionPane.showMessageDialog(contentPane, "Not Enought Money to Proced", "Failure", JOptionPane.ERROR_MESSAGE);	
				}
				//caso del prezzo inserito coincidente
				if(result==price) {
					JOptionPane.showMessageDialog(contentPane, "Successful", "Message", JOptionPane.CLOSED_OPTION);	
				}
				//caso con il resto da dare
				if(result>price) {
					JOptionPane.showMessageDialog(contentPane, "Successful \n the rest: "+ (result-price)+" "," Message", JOptionPane.CLOSED_OPTION);	
				}
				
				
			}
		});
		//parametri del pulsante payment
		btn_payment.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		btn_payment.setBounds(316, 418, 151, 39);
		contentPane.add(btn_payment);
		
		
	}

}

