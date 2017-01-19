package pl.project.service;

import org.hibernate.SessionFactory;
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
}
