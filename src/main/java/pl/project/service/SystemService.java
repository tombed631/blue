package pl.project.service;

import pl.project.domain.System;
import pl.project.domain.SystemContract;

import java.util.List;

/**
 * Created by Tom on 18.01.2017.
 */
public interface SystemService {

    List<System> getAllSystems();
    System getSystem(Integer id);
    void addOrUpdateSystem(System system);
    void deleteSystem(Integer id);


}
