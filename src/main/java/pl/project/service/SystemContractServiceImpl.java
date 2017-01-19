package pl.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.dao.SystemContractDao;
import pl.project.domain.SystemContract;

import java.util.List;

/**
 * Created by Tom on 18.01.2017.
 */

@Service
@Transactional
public class SystemContractServiceImpl implements SystemContractService {

    @Autowired
    SystemContractDao systemContractDao;





    @Transactional
    @Override
    public List<SystemContract> getAllSystemContracts() {
        return systemContractDao.getAll();
    }

    @Transactional
    @Override
    public SystemContract getSystemContract(Integer id) {
       return systemContractDao.getById(id);
    }


    @Transactional
    @Override
    public void addSystemContract(SystemContract systemContract) {
        systemContractDao.addSystemContract(systemContract);
    }

    @Transactional
    @Override
    public void deleteSystemContract(Integer id) {
        systemContractDao.deleteById(id);
    }
}
