package com.schoolapp.service;

import com.schoolapp.entity.Dependant;
import com.schoolapp.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependantService {


    List<Dependant> getAllDependants();

    Dependant saveDependant(Dependant dependant);

    Dependant getDependantById(Integer id);

    Dependant updateDependant(Dependant dependant);

    void deleteDependantById(Integer id);

}
