package online_webshop;

import java.math.BigDecimal;

public class SumPaymentsByWebShop {
	public String webshopID;
	public BigDecimal cardPaymentSum;
	public BigDecimal transferPaymentSum;
	
	public SumPaymentsByWebShop(String webshopID, BigDecimal cardPaymentSum,
			BigDecimal transferPaymentSum) {
		this.webshopID = webshopID;
		this.cardPaymentSum = cardPaymentSum;
		this.transferPaymentSum = transferPaymentSum;
	}
}
