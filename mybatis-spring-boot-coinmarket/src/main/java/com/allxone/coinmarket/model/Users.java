package com.allxone.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.allxone.coinmarket.enums.AuthenticationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

	private Long id;
	
	private String username;

	@JsonIgnore
	private String password;
	
	private String name;
	
	private String gender;
	
	private Integer age;
	
	private boolean active;
	
	private String email;
	
	private String phoneNumber;

	@JsonIgnore
	private Date emailVerificationAt;

	@JsonIgnore
	private String rememberToken;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private String address;

	private AuthenticationType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.password
	 * @param password  the value for user.password
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.name
	 * @return  the value of user.name
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.name
	 * @param name  the value for user.name
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.gender
	 * @return  the value of user.gender
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.gender
	 * @param gender  the value for user.gender
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.age
	 * @return  the value of user.age
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.age
	 * @param age  the value for user.age
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.is_active
	 * @return  the value of user.is_active
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public Boolean getIsActive() {
		return active;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.is_active
	 * @param isActive  the value for user.is_active
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setIsActive(Boolean active) {
		this.active = active;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.email
	 * @return  the value of user.email
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.email
	 * @param email  the value for user.email
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.phone_number
	 * @return  the value of user.phone_number
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.phone_number
	 * @param phoneNumber  the value for user.phone_number
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.email_verification_at
	 * @return  the value of user.email_verification_at
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public Date getEmailVerificationAt() {
		return emailVerificationAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.email_verification_at
	 * @param emailVerificationAt  the value for user.email_verification_at
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setEmailVerificationAt(Date emailVerificationAt) {
		this.emailVerificationAt = emailVerificationAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.remember_token
	 * @return  the value of user.remember_token
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public String getRememberToken() {
		return rememberToken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.remember_token
	 * @param rememberToken  the value for user.remember_token
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.created_at
	 * @return  the value of user.created_at
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.created_at
	 * @param createdAt  the value for user.created_at
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.updated_at
	 * @return  the value of user.updated_at
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.updated_at
	 * @param updatedAt  the value for user.updated_at
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.address
	 * @return  the value of user.address
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.address
	 * @param address  the value for user.address
	 * @mbg.generated  Thu Jan 25 09:56:51 ICT 2024
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", age=" + age + ", active=" + active + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", emailVerificationAt=" + emailVerificationAt + ", rememberToken=" + rememberToken
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", address=" + address + "]";
	}
	
}