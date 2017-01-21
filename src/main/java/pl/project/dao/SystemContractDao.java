package pl.project.dao;

import org.hibernate.SessionFactory;
import pl.project.domain.SystemContract;

import java.util.List;

/**
 * Created by Tom on 18.01.2017.
 */
public interface SystemContractDao {

     void setSessionFactory(SessionFactory sessionFactory);
     List<SystemContract> getAll();
     SystemContract getById(Integer id);
     void addSystemContract(SystemContract systemContract);
     void updateSystemContract(SystemContract systemContract);
     void deleteById(Integer id);
}
