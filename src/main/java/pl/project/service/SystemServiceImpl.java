package pl.project.service;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.dao.SystemDaoImpl;
import pl.project.domain.System;
import pl.project.domain.SystemContract;

import java.util.List;

/**
 * Created by Tom on 19.01.2017.
 */

@Service
@Transactional
public class SystemServiceImpl implements SystemService{

    @Autowired
    SystemDaoImpl systemDao;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<System> getAllSystems() {
        return systemDao.getAll();
    }

    @Override
    public System getSystem(Integer id) {
        return systemDao.getById(id);
    }

    @Override
    public void addSystem(System system)
    {
        systemDao.addSystem(system);
    }

    @Override
    public void deleteSystem(Integer id) {
        systemDao.deleteById(id);
    }

    @Override
    public System getSystemByName(String name) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(System.class);
        cr.add(Restrictions.eq("name",name));
        if (!cr.list().isEmpty())
             return (System) cr.list().get(0); //uniqueResult();
        else return null;
    }
}
