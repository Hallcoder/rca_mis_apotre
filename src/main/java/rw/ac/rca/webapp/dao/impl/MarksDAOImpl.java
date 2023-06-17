package rw.ac.rca.webapp.dao.impl;

import org.hibernate.Query;
import rw.ac.rca.webapp.dao.MarksDAO;
import rw.ac.rca.webapp.orm.Marks;

import java.util.List;

public class MarksDAOImpl extends DAO implements MarksDAO {

    private static MarksDAOImpl instance;

    private MarksDAOImpl() {
    }

    public static MarksDAOImpl getInstance() {
        if (instance == null) {
            return new MarksDAOImpl();
        } else {
            return instance;
        }
    }

    public void saveMarks(Marks marks) {
        try {
            begin();
            getSession().save(marks);
            commit();
        } catch (Exception e) {
            rollback();
        }
    }

    public Marks updateMarks(Marks marks) {
        try {
            begin();
            getSession().update(marks);
            commit();
            return marks;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    public Marks saveOrUpdateMarks(Marks marks) {
        try {
            begin();
            getSession().saveOrUpdate(marks);
            commit();
            return marks;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    public boolean deleteMarks(Marks marks) {
        try {
            begin();
            Query query = getSession().createQuery("from Marks where id= :ref");
            query.setInteger("ref", marks.getId());
            Marks mk = (Marks) query.uniqueResult();
            getSession().delete(mk);
            commit();
            return true;

        } catch (Exception e) {
            rollback();
            return false;
        }
    }

    public Marks getMarksById(int marksId) {
        try {
            begin();
            Query query = getSession().createQuery("from Marks where id= :ref");
            query.setInteger("ref", marksId);
            Marks marks = (Marks) query.uniqueResult();
            commit();
            return marks;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Marks> getAllMarks() {
        try {
            begin();
            Query query = getSession().createQuery("from Marks");
            List<Marks> marks = query.list();
            commit();
            return marks;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }


}
