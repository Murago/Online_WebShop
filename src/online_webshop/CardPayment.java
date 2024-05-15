package online_webshop;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CardPayment extends Payment {
	private String cardNumber;
	
	public CardPayment(String webshopID, String customerID, BigDecimal amount,
			String cardNumber, LocalDate dateOfPurchase) {
		super(webshopID, customerID, amount, dateOfPurchase);
		this.cardNumber = cardNumber;
	}
	public CardPayment(IDs IDs, BigDecimal amount, String cardNumber,
			LocalDate dateOfPurchase) {
		super(IDs, amount, dateOfPurchase);
		this.cardNumber = cardNumber;
	}
	public CardPayment(Payment payment, String cardNumber) {
		super(payment);
		this.cardNumber = cardNumber;
	}
	
	public PaymentMethod getPaymentMethod() {
		return PaymentMethod.card;
	}
	
	public String getCardNumber() {
		return this.cardNumber;
	}
}
