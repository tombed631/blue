package pl.project.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.project.domain.SystemContract;

import java.util.List;

/**
 * Created by Tom on 18.01.2017.
 */

@Repository("systemContractDaoImpl")
public class SystemContractDaoImpl implements SystemContractDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sessionFactory)

    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<SystemContract> getAll() {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SystemContract.class);
        return (List<SystemContract>)criteria.list();
    }

    @Override
    public SystemContract getById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return  session.get(SystemContract.class, id);
    }

    @Override
    public void addSystemContract(SystemContract systemContract) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(systemContract);
        return;

    }

    @Override
    public void updateSystemContract(SystemContract systemContract) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(systemContract);
        return;
    }


    @Override
    public void deleteById(Integer id)
    {
        Session session = this.sessionFactory.getCurrentSession();
        SystemContract systemContract =  session.load(SystemContract.class, id);
        if (systemContract != null) {
            session.delete(systemContract);
        }
    }


}
