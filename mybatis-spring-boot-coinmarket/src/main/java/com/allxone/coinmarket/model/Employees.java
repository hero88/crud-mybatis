package com.allxone.coinmarket.model;

import java.util.Date;

import javax.validation.constraints.NegativeOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employees {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.user_id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.first_name
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.last_name
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.birthday
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private Date birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.gender
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private String gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.contact_number
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private String contactNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.email
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.department_id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private Integer departmentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.position
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private String position;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.hire_date
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private Date hireDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.termination_date
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private Date terminationDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.created_at
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.updated_at
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.id
     *
     * @return the value of employees.id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.id
     *
     * @param id the value for employees.id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.user_id
     *
     * @return the value of employees.user_id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.user_id
     *
     * @param userId the value for employees.user_id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.first_name
     *
     * @return the value of employees.first_name
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.first_name
     *
     * @param firstName the value for employees.first_name
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.last_name
     *
     * @return the value of employees.last_name
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.last_name
     *
     * @param lastName the value for employees.last_name
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.birthday
     *
     * @return the value of employees.birthday
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.birthday
     *
     * @param birthday the value for employees.birthday
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.gender
     *
     * @return the value of employees.gender
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.gender
     *
     * @param gender the value for employees.gender
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.contact_number
     *
     * @return the value of employees.contact_number
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.contact_number
     *
     * @param contactNumber the value for employees.contact_number
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.email
     *
     * @return the value of employees.email
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.email
     *
     * @param email the value for employees.email
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.department_id
     *
     * @return the value of employees.department_id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.department_id
     *
     * @param departmentId the value for employees.department_id
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.position
     *
     * @return the value of employees.position
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.position
     *
     * @param position the value for employees.position
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.hire_date
     *
     * @return the value of employees.hire_date
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.hire_date
     *
     * @param hireDate the value for employees.hire_date
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.termination_date
     *
     * @return the value of employees.termination_date
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public Date getTerminationDate() {
        return terminationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.termination_date
     *
     * @param terminationDate the value for employees.termination_date
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.created_at
     *
     * @return the value of employees.created_at
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.created_at
     *
     * @param createdAt the value for employees.created_at
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.updated_at
     *
     * @return the value of employees.updated_at
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.updated_at
     *
     * @param updatedAt the value for employees.updated_at
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}