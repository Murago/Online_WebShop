package online_webshop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class PaymentLoader extends Loader {
	
	private HashMap<IDs, ArrayList<Payment>> payments;
	
	private Payment current_payment = new Payment();
	private PaymentMethod current_pm;
	private String current_number;
	
	PaymentLoader(HashMap<IDs, Customer> customers) {
		if (customers == null) {
			this.customers = new HashMap<IDs, Customer>();
		}
		else {
			this.customers = customers;
		}
		this.payments = new HashMap<IDs, ArrayList<Payment>>();
	}
	
	//Expected fields: String webshopID, String customerID, String paymentMethod,
	//String amount, String number, String dateOfPurchase
	@Override
	protected boolean validator(String[] fields) {
		int fieldnumber = 6;	//Expected number of fields
		if (fields.length != fieldnumber) {
			invalidLines.append("Failed to load line, the line should contain " + fieldnumber + " fields:" + lineSeparator);
			return false;
		}
		for (int i = 0; i < fieldnumber; ++i) {
			if (fields[i].equals("")) {
				invalidLines.append("Failed to load line, the " + (i+1) + ". field is empty:" + lineSeparator);
				return false;
			}
		}
		
		current_payment.setIDs(new IDs(fields[0], fields[1]));
		if (!customers.containsKey(current_payment.getIDs())) {
			invalidLines.append("Failed to load line, this webshopID and customerID pair does not exist:" + lineSeparator);
			return false;
		}
		
		try {
			current_pm = PaymentMethod.valueOf(fields[2]);
		}
		catch (IllegalArgumentException e) {
			invalidLines.append("Failed to load line, unsupported payment method:" + lineSeparator);
			return false;
		}
		
		try {
			current_payment.setAmount(new BigDecimal(fields[3]));
        	if (current_payment.getAmount().compareTo(new BigDecimal("0")) == -1) {
        		invalidLines.append("Failed to load line, the amount value is negative:" + lineSeparator);
        		return false;
        	}
        }
        catch (NumberFormatException e) {
        	invalidLines.append("Failed to load line, the amount value is invalid:" + lineSeparator);
        	return false;
        }
		
		current_number = fields[4];
		
		try {
			current_payment.setDateOfPurchase(LocalDate.parse(fields[5]));;
        }
        catch (DateTimeParseException e) {
        	invalidLines.append("Failed to load line, the payment date field is invalid:" + lineSeparator);
        	return false;
        }
		
		return true;
	}
	
	@Override
	protected void loadToMemory() {
		Payment loaded_payment;
		switch (current_pm) {
			case card:
				loaded_payment = new CardPayment(current_payment, current_number);
				break;
			case transfer:
				loaded_payment = new TransferPayment(current_payment, current_number);
				break;
			default:
				return;
		}
		if (payments.containsKey(loaded_payment.getIDs())) {
			ArrayList<Payment> payment_list = payments.get(loaded_payment.getIDs());
			payment_list.add(loaded_payment);
		}
		else {
			ArrayList<Payment> payment_list = new ArrayList<Payment>();
			payment_list.add(loaded_payment);
			payments.put(loaded_payment.getIDs(), payment_list);
		}
	}
	
	public HashMap<IDs, ArrayList<Payment>> getPayments() {
		return payments;
	}
}
