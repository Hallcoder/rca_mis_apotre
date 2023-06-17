/**
 * 
 */
package rw.ac.rca.webapp.orm;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor extends Person {

	private static final long serialVersionUID = 6073878228230771199L;
	private double salary;
	private Date remunerationDate;
	
	@ManyToMany(cascade= CascadeType.ALL, mappedBy = "instructors")
	private Set<Course> courses;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	public Date getRemunerationDate() {
		return remunerationDate;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public void setRemunerationDate(Date remunerationDate) {
		this.remunerationDate = remunerationDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Instructor() {
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void addCourse(Course course) {
        this.courses.add(course);
        course.getInstructors().add(this);
    }
    public void removeCourse(Course course) {
        this.courses.remove(course);
        course.getInstructors().remove(this);
    }

	public Instructor(String firstName, String lastName, String email, Date dateOfBirth, String phoneNumber, double salary, Date remunerationDate) {
		super(firstName, lastName, email, dateOfBirth, phoneNumber);
		this.salary = salary;
		this.remunerationDate = remunerationDate;
	}
}
