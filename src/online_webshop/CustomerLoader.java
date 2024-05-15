package online_webshop;

import java.util.HashMap;

public class CustomerLoader extends Loader {

	private Customer current_customer = new Customer();
	
	public CustomerLoader() {
		this.customers = new HashMap<IDs, Customer>();
	}
	
	//Expected fields: String webshopID, String customerID, String name, String address
	@Override
	protected boolean validator(String[] fields) {
		int fieldnumber = 4;	//Expected number of fields
		if (fields.length != fieldnumber) {
			invalidLines.append("Failed to load line, the line should contain " + fieldnumber + " values:" + lineSeparator);
			return false;
		}
		for (int i = 0; i < fieldnumber; ++i) {
			if (fields[i].equals("")) {
				invalidLines.append("Failed to load line, the " + (i+1) + ". value is empty:" + lineSeparator);
				return false;
			}
		}
		current_customer.setIDs(new IDs(fields[0], fields[1]));
		if (customers.containsKey(current_customer.getIDs())) {
			invalidLines.append("Failed to load line, this webshopID and customerID pair already exist:" + lineSeparator);
			return false;
		}
		
		current_customer.setName(fields[2]);
		current_customer.setAddress(fields[3]);
		
		return true;
	}
	
	@Override
	protected void loadToMemory() {
		Customer loaded_customer = new Customer(current_customer);
		customers.put(loaded_customer.getIDs(), loaded_customer);
	}
}
