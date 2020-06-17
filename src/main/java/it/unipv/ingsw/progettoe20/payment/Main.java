package it.unipv.ingsw.progettoe20.payment;

public class Main {

	public static void main(String[] args) {
		int hour =6;
		double price_per_hour= 2.5;
		double price=hour*price_per_hour;
		
		Payment p1=new Payment(price);
		p1.setVisible(true);
		

	}

}
