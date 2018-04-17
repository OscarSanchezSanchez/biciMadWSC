package com.emtbicimad.application;
import com.emtbicimad.csvutils.CsvNormalizer;
import com.emtbicimad.entities.GeneralInformation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jsonEntities.JsonDataObjects;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.FileReader;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner{

    @Autowired
    private InfoRepository repo;
    @Autowired
    private JsondataRepository repoJson;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    this.readGigantJson("/Users/oscar/data_analitics_R/Mar_sept_2017/Mar_sept_2017.json");
	    /*CsvNormalizer csvcreator = new CsvNormalizer();
        List<GeneralInformation> dataset = Petitions.getBetweenDates("19-03-2018 00:00:00.000","23-03-2018 23:59:59.000",repo);
        System.out.println(dataset.size());
        //List<GeneralInformation> dataset = repo.findAll();
        try {
            csvcreator.createCSV(dataset,"PRUEBA.csv");
        }catch (Exception ex){
            ex.printStackTrace();
        }*/
    }

    //@Scheduled(fixedDelay=60000)
    public void saveInformation(){
	    Petitions petitions = new Petitions();
	    try{
            repo.save(petitions.getAllStations("WEB.SERV.oscar.sanchezsa@urjc.es", "0BB5A209-4124-47BC-8233-6F340F5FA800"));
        }catch (Exception e){
	        e.printStackTrace();
        }
    }

    public void readGigantJson(String filepath){
        try {
            FileReader fr = new FileReader(filepath);
            FileReader fr2 = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            BufferedReader br2 = new BufferedReader(fr2);

            final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
            //final Properties properties = gson.fromJson(json, Properties.class);

            String linea;
            int x = 0;
            while((linea = br.readLine()) != null){
                linea  = linea.replace("$","");
                JsonDataObjects json = gson.fromJson(linea,JsonDataObjects.class);
                repoJson.save(json);
            }
            System.out.println("acabeeee");
            fr.close();
            fr2.close();
            br.close();
            br2.close();
        }
        catch(Exception e) {
            System.out.println("ahahahhaha:"+e);
        }
    }

}
