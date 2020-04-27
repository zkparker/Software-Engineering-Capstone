/*
 * Copyright [2020] [ElEspada - Software Engineering Capstone - Springfield, IL]
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.elespada.VO;

/**
 * <b>PaymentVO.java</b>
 * <p>
 * A plain old POJO, Payment Value Object for capturing the form data. This
 * captures payments details entered by the user.
 */
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

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the addressStreet
	 */
	public String getAddressStreet() {
		return addressStreet;
	}

	/**
	 * @param addressStreet the addressStreet to set
	 */
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	/**
	 * @return the addressCity
	 */
	public String getAddressCity() {
		return addressCity;
	}

	/**
	 * @param addressCity the addressCity to set
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	/**
	 * @return the addressState
	 */
	public String getAddressState() {
		return addressState;
	}

	/**
	 * @param addressState the addressState to set
	 */
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	/**
	 * @return the addressZip
	 */
	public String getAddressZip() {
		return addressZip;
	}

	/**
	 * @param addressZip the addressZip to set
	 */
	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the nameOnCard
	 */
	public String getNameOnCard() {
		return nameOnCard;
	}

	/**
	 * @param nameOnCard the nameOnCard to set
	 */
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardExpiry
	 */
	public String getCardExpiry() {
		return cardExpiry;
	}

	/**
	 * @param cardExpiry the cardExpiry to set
	 */
	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	/**
	 * @return the cvv
	 */
	public String getCvv() {
		return cvv;
	}

	/**
	 * @param cvv the cvv to set
	 */
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((addressCity == null) ? 0 : addressCity.hashCode());
		result = (prime * result) + ((addressState == null) ? 0 : addressState.hashCode());
		result = (prime * result) + ((addressStreet == null) ? 0 : addressStreet.hashCode());
		result = (prime * result) + ((addressZip == null) ? 0 : addressZip.hashCode());
		result = (prime * result) + ((cardExpiry == null) ? 0 : cardExpiry.hashCode());
		result = (prime * result) + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = (prime * result) + ((cvv == null) ? 0 : cvv.hashCode());
		result = (prime * result) + ((email == null) ? 0 : email.hashCode());
		result = (prime * result) + ((firstName == null) ? 0 : firstName.hashCode());
		result = (prime * result) + ((lastName == null) ? 0 : lastName.hashCode());
		result = (prime * result) + ((nameOnCard == null) ? 0 : nameOnCard.hashCode());
		result = (prime * result) + ((paymentType == null) ? 0 : paymentType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PaymentVO other = (PaymentVO) obj;
		if (addressCity == null) {
			if (other.addressCity != null) {
				return false;
			}
		} else if (!addressCity.equals(other.addressCity)) {
			return false;
		}
		if (addressState == null) {
			if (other.addressState != null) {
				return false;
			}
		} else if (!addressState.equals(other.addressState)) {
			return false;
		}
		if (addressStreet == null) {
			if (other.addressStreet != null) {
				return false;
			}
		} else if (!addressStreet.equals(other.addressStreet)) {
			return false;
		}
		if (addressZip == null) {
			if (other.addressZip != null) {
				return false;
			}
		} else if (!addressZip.equals(other.addressZip)) {
			return false;
		}
		if (cardExpiry == null) {
			if (other.cardExpiry != null) {
				return false;
			}
		} else if (!cardExpiry.equals(other.cardExpiry)) {
			return false;
		}
		if (cardNumber == null) {
			if (other.cardNumber != null) {
				return false;
			}
		} else if (!cardNumber.equals(other.cardNumber)) {
			return false;
		}
		if (cvv == null) {
			if (other.cvv != null) {
				return false;
			}
		} else if (!cvv.equals(other.cvv)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (nameOnCard == null) {
			if (other.nameOnCard != null) {
				return false;
			}
		} else if (!nameOnCard.equals(other.nameOnCard)) {
			return false;
		}
		if (paymentType == null) {
			if (other.paymentType != null) {
				return false;
			}
		} else if (!paymentType.equals(other.paymentType)) {
			return false;
		}
		return true;
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
