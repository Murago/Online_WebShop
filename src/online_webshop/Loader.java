package online_webshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public abstract class Loader {
	protected HashMap<IDs, Customer> customers;
	
	protected StringBuilder invalidLines = new StringBuilder(200);
	protected String lineSeparator = System.lineSeparator();
	
	public void load(String filename) {
		String line = "";
	    try (FileReader fr = new FileReader(filename);
	    		BufferedReader br = new BufferedReader(fr)) {
		    while ((line = br.readLine()) != null) {
		    	String[] fields = line.split(",");
		    	//Expected fields: String webshopID, String customerID, String paymentMethod,
		    	//String amount, String number, String dateOfPurchase
		    	if (validator(fields)) {
		    		loadToMemory();
		    	}
		    	else {
		    		invalidLines.append(line + lineSeparator);
		    	}
		    }
	    }
		catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void log(String filename) {
		try (FileWriter fw = new FileWriter(filename, true);
				BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write("[Log created at: " + LocalDateTime.now() + "]" + lineSeparator);
			bw.append(invalidLines);

			System.out.println("Successfully wrote to file: " + filename);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getInvalidLines() {
		return invalidLines.toString();
	}
	
	public HashMap<IDs, Customer> getCustomers() {
		return customers;
	}
	
	protected abstract boolean validator(String[] fields);
	protected abstract void loadToMemory();
}
