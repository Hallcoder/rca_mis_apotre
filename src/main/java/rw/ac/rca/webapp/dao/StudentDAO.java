package rw.ac.rca.webapp.dao;

import java.util.List;
import rw.ac.rca.webapp.orm.Student;

public interface StudentDAO {

    public void saveStudent(Student student);

    public Student updateStudent(Student student);

    public void saveOrUpdateStudent(Student student);

    public boolean deleteStudent(Student student);

    public static Student getStudentById(int studentId) {
        return null;
    }

    public Student searchStudentByName(String name);

    public List<Student> getAllStudents();

    public List<Student> getStudentsByInternationalStatus(boolean isInternational);

    public List<Student> getStudentsByPartTimeStatus(boolean isPartTime);

    public List<Student> getStudentsByRepeatingStatus(boolean isRepeating);
}
