package rw.ac.rca.webapp.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import rw.ac.rca.webapp.dao.StudentDAO;
import rw.ac.rca.webapp.orm.Student;


public class StudentDAOImpl extends DAO implements StudentDAO {
    public static final Logger LOG = Logger.getLogger(StudentDAOImpl.class);
    public static StudentDAOImpl instance;

    public static StudentDAOImpl getInstance() {
        if (instance == null) {
            return new StudentDAOImpl();
        } else {
            return instance;
        }
    }

    private StudentDAOImpl() {
    }

    @Override
    public void saveStudent(Student student) {
        try {
            begin();
            getSession().save(student);
            commit();

        } catch (Exception e) {
            rollback();
        }
    }

    @Override
    public Student updateStudent(Student student) {
        try {
            begin();
            getSession().update(student);
            commit();
            return student;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public void saveOrUpdateStudent(Student student) {
        try {
            begin();
            getSession().saveOrUpdate(student);
            commit();
        } catch (Exception e) {
            rollback();
        }
    }

    @Override
    public boolean deleteStudent(Student student) {
        try {
            begin();
            getSession().delete(student);
            commit();
            return true;
        } catch (Exception e) {
            rollback();
            return false;
        }
    }


    public Student getStudentById(int id) {
        try {
            begin();
            Query query = getSession().createQuery("from Student where id= :ref");
            query.setInteger("ref", id);
            Student student = (Student) query.uniqueResult();
            commit();
            return student;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    public Student searchStudentByName(String name) {
        try {
            begin();
            Query query = getSession().createQuery("from Student where firstName= :name or lastName= :name");
            query.setString("name", name);
            Student student = (Student) query.uniqueResult();
            commit();
            return student;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }


    @SuppressWarnings("unchecked")

    @Override
    public List<Student> getAllStudents() {
        try {
            begin();
            Query query = getSession().createQuery("from Student");
            List<Student> students = query.list();
            commit();
            return students;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getStudentsByInternationalStatus(boolean isInternational) {
        try {
            begin();
            Query query = getSession().createQuery("from Student where isInternational= :status");
            query.setBoolean("status", isInternational);
            List<Student> students = query.list();
            commit();
            return students;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getStudentsByPartTimeStatus(boolean isPartTime) {
        try {
            begin();
            Query query = getSession().createQuery("from Student where isPartTime= :status");
            query.setBoolean("status", isPartTime);
            List<Student> students = query.list();
            commit();
            return students;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getStudentsByRepeatingStatus(boolean isRepeating) {
        try {
            begin();
            Query query = getSession().createQuery("from Student where isRepeating= :status");
            query.setBoolean
                    ("status", isRepeating);
            List<Student> students = query.list();
            commit();
            return students;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }
}

