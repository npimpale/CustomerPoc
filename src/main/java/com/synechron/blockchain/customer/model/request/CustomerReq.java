/**
 * 
 */
package com.synechron.blockchain.customer.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dev
 *
 */

public class CustomerReq {

	@JsonProperty(index = 0, value = "mobile")
	@NotBlank(message = "Customer mobile is mandatory", groups = { Existing.class, New.class })
	@Pattern(message = "Mobile number should be 10 digits", regexp = "[0-9]{10}", groups = { Existing.class,
			New.class })
	private String mobile;

	@NotBlank(message = "Customer first name is mandatory", groups = { New.class })
	@Size(max = 20, message = "FirstName should not be more than 20 characters", groups = { Existing.class, New.class })
	@JsonProperty(index = 1, value = "firstName")
	private String firstName;

	@NotBlank(message = "Customer last name is mandatory", groups = { New.class })
	@Size(max = 20, message = "LastName should not be more than 20 characters", groups = { Existing.class, New.class })
	@JsonProperty(index = 2, value = "lastName")
	private String lastName;

	@NotBlank(message = "Customer email is mandatory", groups = { New.class })
	@Null(message = "Cannot update email", groups = { Existing.class })
	@Size(max = 30, message = "Email should not be more than 30 characters", groups = { New.class })
	@Email(message = "Email should be in correct format", groups = { New.class })
	@JsonProperty(index = 3, value = "email")
	private String email;

	/**
	 * 
	 */
	public CustomerReq() {
		// TODO Auto-generated constructor stub
	}

	public CustomerReq(String mobile, String firstName, String lastName, String email) {
		this.mobile = mobile;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public interface Existing {
	}

	public interface New {
	}

}