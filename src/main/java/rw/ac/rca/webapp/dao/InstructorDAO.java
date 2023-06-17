package rw.ac.rca.webapp.dao;

import rw.ac.rca.webapp.orm.Instructor;

import java.util.List;

public interface InstructorDAO {
    public void saveInstructor(Instructor instructor);

    public Instructor updateInstructor(Instructor Instructor);

    public void saveOrUpdateInstructor(Instructor Instructor);

    public boolean deleteInstructor(Instructor Instructor);

    public  Instructor getInstructorById(int InstructorId);

    public List<Instructor> getAllInstructors();

}
