package online_webshop;

import java.math.BigDecimal;

public class SumPaymentsByCustomer {
	public String name;
	public String address;
	public BigDecimal sum;
	
	public SumPaymentsByCustomer(String name, String address, BigDecimal sum) {
		this.name = name;
		this.address = address;
		this.sum = sum;
	}
}
