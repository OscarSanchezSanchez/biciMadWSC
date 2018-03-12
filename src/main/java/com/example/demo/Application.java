package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner{

    @Autowired
    private InfoRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
    }

    @Scheduled(fixedDelay=300000)
    public void saveInformation(){
        Petitions petitions = new Petitions();
        repo.save(petitions.getAllStations("WEB.SERV.oscar.sanchezsa@urjc.es", "0BB5A209-4124-47BC-8233-6F340F5FA800"));
    }
}
