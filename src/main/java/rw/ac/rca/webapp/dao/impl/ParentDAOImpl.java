package rw.ac.rca.webapp.dao.impl;

import org.hibernate.Query;
import rw.ac.rca.webapp.dao.ParentDAO;
import rw.ac.rca.webapp.orm.Parent;

import java.util.List;

public class ParentDAOImpl extends DAO implements ParentDAO {

    private static ParentDAOImpl instance;

    private ParentDAOImpl() {
    }

    public static ParentDAOImpl getInstance() {
        if (instance == null) {
            return new ParentDAOImpl();
        } else {
            return instance;
        }
    }

    public void saveParent(Parent parent) {
        try {
            begin();
            getSession().save(parent);
            commit();
        } catch (Exception e) {
            rollback();
        }
    }

    public Parent updateParent(Parent parent) {
        try {
            begin();
            getSession().update(parent);
            commit();
            return parent;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    public Parent saveOrUpdateParent(Parent parent) {
        try {
            begin();
            getSession().saveOrUpdate(parent);
            commit();
            return parent;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    public boolean deleteParent(Parent parent) {
        try {
            begin();
            Query query = getSession().createQuery("from Parent where id= :ref");
            query.setInteger("ref", parent.getId());
            Parent prnt = (Parent) query.uniqueResult();
            getSession().delete(prnt);
            commit();
            return true;

        } catch (Exception e) {
            rollback();
            return false;
        }
    }

    public Parent getParentById(int parentId) {
        try {
            begin();
            Query query = getSession().createQuery("from Parent where id= :ref");
            query.setInteger("ref", parentId);
            Parent parent = (Parent) query.uniqueResult();
            commit();
            return parent;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Parent> getAllParents() {
        try {
            begin();
            Query query = getSession().createQuery("from Parent");
            List<Parent> parents = query.list();
            commit();
            return parents;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    public Parent searchParentByName(String name) {
        try {
            begin();
            Query query = getSession().createQuery("from Parent where firstName= :name or lastName=:name");
            query.setString("name", name);
            Parent parent = (Parent) query.uniqueResult();
            commit();
            return parent;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }
}
