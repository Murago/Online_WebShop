package online_webshop;

public class Customer {
	private IDs IDs;
	private String name;
	private String address;
	
	public Customer(String webshopID, String customerID, String name, String address) {
		this.IDs = new IDs(webshopID, customerID);
		this.name = name;
		this.address = address;
	}
	public Customer(IDs IDs, String name, String address) {
		this.IDs = IDs;	// IDs is an Immutable Object
		this.name = name;
		this.address = address;
	}
	public Customer(Customer customer) {
		this(customer.getIDs(), customer.getName(), customer.getAddress());
	}
	public Customer() {}
	
	public IDs getIDs() {
		return this.IDs;
	}
	public void setIDs(IDs IDs) {
		this.IDs = IDs;	// IDs is an Immutable Object
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
