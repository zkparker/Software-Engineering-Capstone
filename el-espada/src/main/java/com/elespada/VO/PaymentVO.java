package com.elespada.VO;

public class PaymentVO {
	private String firstName;
	private String lastName;
	private String email;
	private String addressStreet;
	private String addressCity;
	private String addressState;
	private String addressZip;
	private String paymentType;
	private String nameOnCard;
	private String cardNumber;
	private String cardExpiry;
	private String cvv;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentVO [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", addressStreet=");
		builder.append(addressStreet);
		builder.append(", addressCity=");
		builder.append(addressCity);
		builder.append(", addressState=");
		builder.append(addressState);
		builder.append(", addressZip=");
		builder.append(addressZip);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", nameOnCard=");
		builder.append(nameOnCard);
		builder.append(", cardNumber=");
		builder.append(cardNumber);
		builder.append(", cardExpiry=");
		builder.append(cardExpiry);
		builder.append(", cvv=");
		builder.append(cvv);
		builder.append("]");
		return builder.toString();
	}

	
}
