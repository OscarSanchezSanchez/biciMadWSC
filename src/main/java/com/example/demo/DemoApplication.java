package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class DemoApplication {
    /*@Autowired
    private InformationRepository repository;*/
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Petitions petitions = new Petitions();
		System.out.println(petitions.getAllStations("WEB.SERV.oscar.sanchezsa@urjc.es","0BB5A209-4124-47BC-8233-6F340F5FA800"));
		//System.out.println(petitions.getSingleStation("WEB.SERV.oscar.sanchezsa@urjc.es","0BB5A209-4124-47BC-8233-6F340F5FA800",1));

	}
}
