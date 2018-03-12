package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfoRepository extends MongoRepository<GeneralInformation, String>{

}
