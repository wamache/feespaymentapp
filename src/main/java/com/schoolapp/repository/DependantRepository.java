package com.schoolapp.repository;

import com.schoolapp.entity.Dependant;
import com.schoolapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DependantRepository extends JpaRepository<Dependant, Integer> {



}
