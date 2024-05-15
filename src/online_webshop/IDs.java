package online_webshop;

import java.util.Objects;

public class IDs {
	private final String webshopID;
	private final String customerID;
	
	public IDs(String webshopID, String customerID) {
		this.webshopID = webshopID;
		this.customerID = customerID;
	}
	
	public String getWebshopID(){
		return this.webshopID;
	}
	public String getCustomerID(){
		return this.customerID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		IDs other = (IDs) obj;
		return this.webshopID.equals(other.getWebshopID()) && this.customerID.equals(other.getCustomerID());
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(webshopID, customerID);
	}
	
}
