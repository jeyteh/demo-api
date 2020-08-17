package com.jey.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jey.demo.entities.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
