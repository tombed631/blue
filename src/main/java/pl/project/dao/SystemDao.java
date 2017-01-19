package pl.project.dao;

import org.hibernate.SessionFactory;
import pl.project.domain.System;

import java.util.List;

/**
 * Created by Tom on 18.01.2017.
 */
public interface SystemDao {

    void setSessionFactory(SessionFactory sessionFactory);
    List<System> getAll();
    System getById(Integer id);
    void addSystem(System systemContract);
    void deleteById(Integer id);
}
