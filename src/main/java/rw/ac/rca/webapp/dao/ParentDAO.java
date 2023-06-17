package rw.ac.rca.webapp.dao;

import rw.ac.rca.webapp.orm.Parent;

import java.util.List;

public interface ParentDAO {
    void saveParent(Parent parent);
    Parent updateParent(Parent parent);
    Parent saveOrUpdateParent(Parent parent);
    boolean deleteParent(Parent parent);
    Parent getParentById(int parentId);
    List<Parent> getAllParents();
    Parent searchParentByName(String name);
}
