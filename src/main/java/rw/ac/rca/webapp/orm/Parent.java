package rw.ac.rca.webapp.orm;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "parent")
public class Parent extends Person{
    private static final long serialVersionUID = 6073878228230771199L;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> children;

    public List<Student> getChildren() {
        return children;
    }

    public void setChildren(List<Student> children) {
        this.children = children;
    }

    public Parent(String firstName, String lastName, String email, Date dateOfBirth, String phoneNumber, List<Student> children) {
        super(firstName, lastName, email, dateOfBirth, phoneNumber);
        this.children = children;
    }
}
