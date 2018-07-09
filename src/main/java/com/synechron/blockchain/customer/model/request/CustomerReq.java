/**
 * 
 */
package com.synechron.blockchain.customer.model.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author dev
 *
 */

@JsonPropertyOrder({ "mobile", "firstName", "lastName", "email" })
public class CustomerReq {

	@NotBlank(message = "Customer mobile is mandatory")
	@Digits(integer = 10, fraction = 0, message = "Mobile number should be 10 digits")
	@JsonProperty("mobile")
	private String mobile;

	@NotBlank(message = "Customer first name is mandatory")
	@Size(max = 20, message = "FirstName should not be more than 20 characters")
	@JsonProperty("firstName")
	private String firstName;

	@NotBlank(message = "Customer last name is mandatory")
	@Size(max = 20, message = "LastName should not be more than 20 characters")
	@JsonProperty("lastName")
	private String lastName;

	@NotBlank(message = "Customer email is mandatory")
	@Email(message = "Email should be in correct format")
	@JsonProperty("email")
	private String email;

	/**
	 * 
	 */
	public CustomerReq() {
		// TODO Auto-generated constructor stub
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerReq [mobile=").append(mobile).append(", firstName=").append(firstName)
				.append(", lastName=").append(lastName).append(", email=").append(email).append("]");
		return builder.toString();
	}

}
