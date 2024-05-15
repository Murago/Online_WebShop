package online_webshop;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferPayment extends Payment {
	private String bankAccountNumber;
	
	public TransferPayment(String webshopID, String customerID, BigDecimal amount,
			String bankAccountNumber, LocalDate dateOfPurchase) {
		super(webshopID, customerID, amount, dateOfPurchase);
		this.bankAccountNumber = bankAccountNumber;
	}
	public TransferPayment(IDs IDs, BigDecimal amount, String bankAccountNumber,
			LocalDate dateOfPurchase) {
		super(IDs, amount, dateOfPurchase);
		this.bankAccountNumber = bankAccountNumber;
	}
	public TransferPayment(Payment payment, String bankAccountNumber) {
		super(payment);
		this.bankAccountNumber = bankAccountNumber;
	}
	
	public PaymentMethod getPaymentMethod() {
		return PaymentMethod.transfer;
	}
	
	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}
}
