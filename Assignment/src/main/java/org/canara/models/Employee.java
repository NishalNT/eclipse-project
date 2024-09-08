package org.canara.models;

import java.util.Objects;

public class Employee {
	private String FirstName;
	private String LastName;
	private String PhoneNo;
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "Employee [FirstName=" + FirstName + ", LastName=" + LastName + ", PhoneNo=" + PhoneNo + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(FirstName, LastName, PhoneNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(FirstName, other.FirstName) && Objects.equals(LastName, other.LastName)
				&& Objects.equals(PhoneNo, other.PhoneNo);
	}
	public Employee(String FirstName, String LastName, String PhoneNo) {
		super();
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.PhoneNo = PhoneNo;
	}
	
	
}
