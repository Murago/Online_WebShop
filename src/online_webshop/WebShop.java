package online_webshop;

import java.util.ArrayList;
import java.util.HashMap;

public class WebShop {
	
    public static void main(String[] args) {
    	CustomerLoader cl = new CustomerLoader();
    	cl.load("customer.csv");
    	cl.log("application.log");
    	
    	HashMap<IDs, Customer> customers = cl.getCustomers();
    	
    	PaymentLoader pl = new PaymentLoader(customers);
    	pl.load("payments.csv");
    	pl.log("application.log");
    	
    	HashMap<IDs, ArrayList<Payment>> payments = pl.getPayments();
    	
    	ReportMaker rm = new ReportMaker();
    	rm.sumPaymentsByCustomer(customers, payments, "report01.csv");
    	rm.calculateTopTwo("top.csv");
    	rm.sumPaymentsByWebShop(customers, payments, "report02.csv");
    }
}