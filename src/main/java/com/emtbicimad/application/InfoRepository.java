package com.emtbicimad.application;

import com.emtbicimad.entities.GeneralInformation;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InfoRepository extends CrudRepository<GeneralInformation, String> {
    List<GeneralInformation> findAllByTimeIsBetween(Date date1,Date date2);
    List<GeneralInformation> findAll();
}
