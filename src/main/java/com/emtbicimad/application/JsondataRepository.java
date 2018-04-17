package com.emtbicimad.application;

import jsonEntities.JsonDataObjects;
import org.springframework.data.repository.CrudRepository;

public interface JsondataRepository extends CrudRepository<JsonDataObjects,String> {
}
