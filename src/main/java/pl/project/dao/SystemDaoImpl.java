package pl.project.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.project.domain.System;
import pl.project.domain.SystemContract;

import java.util.List;

/**
 * Created by Tom on 18.01.2017.
 */
@Repository("systemDaoImpl")
public class SystemDaoImpl implements SystemDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<System> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(System.class);
        return (List<System>)criteria.list();
    }

    @Override
    public System getById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return  session.get(System.class, id);
    }

    @Override
    public void addSystem(System system) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(system);
    }

    @Override
    public void deleteById(Integer id)
    {
        Session session = this.sessionFactory.getCurrentSession();
        System system =  session.load(System.class, id);
        if (system != null) {
            session.delete(system);
        }
    }
}
