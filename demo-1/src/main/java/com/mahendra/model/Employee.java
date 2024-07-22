package com.mahendra.model;

/// OLDER VERSION USES javax.persistence.*;
import jakarta.persistence.*;
import java.util.Date;

@Table(name="employees")
@Entity
public class Employee {
	@Id
	@Column(name="emp_no")
	private Integer empId;

	@Column(name="birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name="first_name")
	private String firstName;


	@Column(name="last_name")
	private String lastName;

	@Column(name="gender", length = 1)
	private String gender;
	
	@Column(name="hire_date")
	@Temporal(TemporalType.DATE)
	private Date joiningDate;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Employee(Integer empId, Date birthDate, String firstName, String lastName, String gender, Date joiningDate) {
		super();
		this.empId = empId;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.joiningDate = joiningDate;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


}
