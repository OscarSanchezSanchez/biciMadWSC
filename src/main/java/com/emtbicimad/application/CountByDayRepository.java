package com.emtbicimad.application;

import jsonEntities.CountByDay;
import org.springframework.data.repository.CrudRepository;

public interface CountByDayRepository extends CrudRepository<CountByDay,String> {

}
