package rw.ac.rca.webapp.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends Person implements Serializable {

	private boolean isInternational;
	private boolean isPartTime;
	private boolean isRepeating;

	@OneToMany(mappedBy = "student")
	private List<Enrol> enrols;


	@OneToMany(mappedBy = "student")
	private List<Marks> marks;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;


	public Student() {}

	public Student(String firstName, String lastName, String email, Date dateOfBirth, String phoneNumber, boolean isInternational, boolean isPartTime, boolean isRepeating) {
		super(firstName, lastName, email, dateOfBirth, phoneNumber);
		this.isInternational = isInternational;
		this.isPartTime = isPartTime;
		this.isRepeating = isRepeating;
	}

	public boolean isInternational() {
		return isInternational;
	}

	public void setInternational(boolean international) {
		isInternational = international;
	}

	public boolean isPartTime() {
		return isPartTime;
	}

	public void setPartTime(boolean partTime) {
		isPartTime = partTime;
	}

	public boolean isRepeating() {
		return isRepeating;
	}

	public void setRepeating(boolean repeating) {
		isRepeating = repeating;
	}

	public List<Enrol> getEnrols() {
		return enrols;
	}

	public void setEnrols(List<Enrol> enrols) {
		this.enrols = enrols;
	}

	public List<Marks> getMarks() {
		return marks;
	}
	public String getName() {
		return getFirstName() + " " + getLastName();
	}


	public void setMarks(List<Marks> marks) {
		this.marks = marks;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
