package online_webshop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ReportMaker {
	HashMap<String, SumPaymentsByCustomer> customerSum = new HashMap<>();
	
	protected String lineSeparator = System.lineSeparator();

	public void sumPaymentsByCustomer(HashMap<IDs, Customer> customers,
			HashMap<IDs, ArrayList<Payment>> payments, String filename) {
		
		if(customers == null || payments == null)
			return;
		
		for (Customer customer : customers.values()) {
			String customerID = customer.getIDs().getCustomerID();
    		ArrayList<Payment> payment_list = payments.get(customer.getIDs());
    		SumPaymentsByCustomer spbc = customerSum.get(customerID);
    		if (spbc == null) {
    			spbc = new SumPaymentsByCustomer(customer.getName(),
    					customer.getAddress(), new BigDecimal("0"));
    			customerSum.put(customerID, spbc);
    		}
    		
    		if (payment_list != null) {
    			BigDecimal sum = new BigDecimal("0");
    			for (Payment payment : payment_list) {
    				sum = sum.add(payment.getAmount());
    			}
    			spbc.sum = spbc.sum.add(sum);
    		}
    	}
		
		try (FileWriter fw = new FileWriter(filename);
				BufferedWriter bw = new BufferedWriter(fw)) {
			
			for (SumPaymentsByCustomer spbc : customerSum.values()) {
				bw.write(spbc.name + "," + spbc.address + "," + spbc.sum + lineSeparator);
			}
			System.out.println("Successfully wrote to file: " + filename);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void calculateTopTwo(String filename) {
		if (customerSum.isEmpty()) {
			return;
		}
		Iterator<SumPaymentsByCustomer> it = customerSum.values().iterator();
		SumPaymentsByCustomer first = it.next();
		if (!it.hasNext()) {
			try (FileWriter fw = new FileWriter(filename);
					BufferedWriter bw = new BufferedWriter(fw)) {
				
				bw.write(first.name + "," + first.address + "," + first.sum + lineSeparator);
				System.out.println("Successfully wrote to file: " + filename);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		SumPaymentsByCustomer second;
		SumPaymentsByCustomer next = it.next();
		if (first.sum.compareTo(next.sum) == -1) {
			second = first;
			first = next;
		}
		else {
			second = next;
		}
		
		while(it.hasNext()) {
			next = it.next();
			if (first.sum.compareTo(next.sum) == -1) {
				second = first;
				first = next;
			}
			else if (second.sum.compareTo(next.sum) == -1) {
				second = next;
			}
		}

		try (FileWriter fw = new FileWriter(filename);
				BufferedWriter bw = new BufferedWriter(fw)) {
			
			bw.write(first.name + "," + first.address + "," + first.sum + lineSeparator);
			bw.write(second.name + "," + second.address + "," + second.sum + lineSeparator);
			System.out.println("Successfully wrote to file: " + filename);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sumPaymentsByWebShop(HashMap<IDs, Customer> customers,
			HashMap<IDs, ArrayList<Payment>> payments, String filename) {
		
		if(customers == null || payments == null)
			return;
		
		HashMap<String, SumPaymentsByWebShop> webShopSum = new HashMap<>();
		
		for (Customer customer : customers.values()) {
			String webshopID = customer.getIDs().getWebshopID();
			if (!webShopSum.containsKey(webshopID)) {
				webShopSum.put(webshopID, new SumPaymentsByWebShop(webshopID, 
						new BigDecimal("0"), new BigDecimal("0")));
			}
		}
		
		for (ArrayList<Payment> payment_list : payments.values()) {
			for (Payment payment : payment_list) {
				String webshopID = payment.getIDs().getWebshopID();
				if (payment.getClass() == CardPayment.class) {
					webShopSum.get(webshopID).cardPaymentSum =
							webShopSum.get(webshopID).cardPaymentSum.add(payment.getAmount());
				}
				else {
					webShopSum.get(webshopID).transferPaymentSum =
							webShopSum.get(webshopID).transferPaymentSum.add(payment.getAmount());
				}
			}
		}
		
		try (FileWriter fw = new FileWriter(filename);
				BufferedWriter bw = new BufferedWriter(fw)) {
			
			for (SumPaymentsByWebShop webshop : webShopSum.values()) {
				bw.write(webshop.webshopID + "," + webshop.cardPaymentSum + "," +
						webshop.transferPaymentSum + lineSeparator);
			}
			
			System.out.println("Successfully wrote to file: " + filename);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
