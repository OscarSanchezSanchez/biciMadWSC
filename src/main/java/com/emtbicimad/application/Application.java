package com.emtbicimad.application;
import com.emtbicimad.csvutils.CsvNormalizer;
import com.emtbicimad.entities.GeneralInformation;
import javafx.util.converter.DateTimeStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

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
	    CsvNormalizer csvcreator = new CsvNormalizer();
        List<GeneralInformation> dataset = Petitions.getBetweenDates("19-03-2018 00:00:00.000","23-03-2018 23:59:59.000",repo);
        System.out.println(dataset.size());
        //List<GeneralInformation> dataset = repo.findAll();
        try {
            csvcreator.createCSV(dataset,"PRUEBA.csv");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Scheduled(fixedDelay=300000)
    public void saveInformation(){
	    Petitions petitions = new Petitions();
        repo.save(petitions.getAllStations("WEB.SERV.oscar.sanchezsa@urjc.es", "0BB5A209-4124-47BC-8233-6F340F5FA800"));
    }


}
