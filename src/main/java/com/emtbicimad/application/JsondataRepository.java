package com.emtbicimad.application;

import jsonEntities.IDclass;
import jsonEntities.JsonDataObjects;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface JsondataRepository extends MongoRepository<JsonDataObjects,String> {
    List<JsonDataObjects> findByDateBetween(Date date1,Date date2);

    @Query("{date: {$gte: ?0, $lte: ?1}}")
    List<JsonDataObjects> findDocumentsBetweenDates(Date date1, Date date2);
}
