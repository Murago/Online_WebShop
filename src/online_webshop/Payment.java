package online_webshop;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment {
	private IDs IDs;
	private BigDecimal amount;
	private LocalDate dateOfPurchase;

	public Payment(String webshopID, String customerID, BigDecimal amount,
			LocalDate dateOfPurchase) {
		this.IDs = new IDs(webshopID, customerID);
		this.amount = amount;
		this.dateOfPurchase = dateOfPurchase;
	}
	public Payment(IDs IDs, BigDecimal amount, LocalDate dateOfPurchase) {
		this.IDs = IDs;	// IDs is an Immutable Object
		this.amount = amount;
		this.dateOfPurchase = dateOfPurchase;
	}
	public Payment(Payment payment) {
		this(payment.getIDs(), payment.getAmount(), payment.getDateOfPurchase());
	}
	public Payment() {}
	
	public IDs getIDs() {
		return IDs;
	}
	public void setIDs(IDs IDs) {
		this.IDs = IDs;	// IDs is an Immutable Object
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public LocalDate getDateOfPurchase() {
		return this.dateOfPurchase;
	}
	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
}
