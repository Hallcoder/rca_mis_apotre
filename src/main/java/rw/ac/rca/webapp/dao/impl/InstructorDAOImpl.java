package rw.ac.rca.webapp.dao.impl;

import org.hibernate.Query;
import rw.ac.rca.webapp.dao.InstructorDAO;
import rw.ac.rca.webapp.orm.Instructor;

import java.util.List;

public class InstructorDAOImpl extends DAO implements InstructorDAO {
    private InstructorDAOImpl(){

    }
    private static InstructorDAOImpl instance;
    public static InstructorDAOImpl getInstance(){
        if (instance == null){
            return new InstructorDAOImpl();
        }else {
            return instance;
        }
    }
    @Override
    public void saveInstructor(Instructor instructor) {
        try {
            begin();
            getSession().save(instructor);
            commit();
        }catch (Exception e){
            rollback();
        }

    }

    @Override
    public Instructor updateInstructor(Instructor instructor) {
        try {
            begin();
            getSession().update(instructor);
            commit();
            return instructor;
        }catch (Exception e){
            rollback();
            return null;
        }
    }

    @Override
    public void saveOrUpdateInstructor(Instructor instructor) {
        try {
            begin();
            getSession().saveOrUpdate(instructor);
            commit();
        }catch (Exception e){
            rollback();
        }

    }

    @Override
    public boolean deleteInstructor(Instructor instructor) {
        try {
            begin();
            getSession().delete(instructor);
            commit();
            return true;
        }catch (Exception e){
            rollback();
            return false;
        }
    }

    @Override
    public Instructor getInstructorById(int instructorId) {
        try {
            begin();
            Query query = getSession().createQuery("from Instructor where id= :ref");
            query.setInteger("ref",instructorId);
            Instructor instructor = (Instructor) query.uniqueResult();
            commit();
            return instructor;
        }catch (Exception e){
            rollback();
            return null;
        }
    }

    @Override
    public List<Instructor> getAllInstructors() {
        try {
            begin();
            Query query = getSession().createQuery("from Instructor");
            List<Instructor> instructors = query.list();
            commit();
            return instructors;
        }catch (Exception e){
            rollback();
            return null;
        }

    }
}
