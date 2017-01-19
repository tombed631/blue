package pl.project.service;

import pl.project.domain.SystemContract;

import java.util.List;

/**
 * Created by Tom on 18.01.2017.
 */
public interface SystemContractService {


     List<SystemContract> getAllSystemContracts();
     SystemContract getSystemContract(Integer id);
     void addOrUpdateSystemContract(SystemContract systemContract);
     void deleteSystemContract(Integer id);


}
