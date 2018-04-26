package com.emtbicimad.application;
import com.emtbicimad.csvutils.CsvNormalizer;
import com.emtbicimad.entities.GeneralInformation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import javafx.util.converter.DateTimeStringConverter;
import jsonEntities.IDclass;
import jsonEntities.JsonDataObjects;
import jsonEntities.JsonObjectSerializer;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

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
	    /*
	    //this.readGigantJson("/Users/oscar/data_analitics_R/Mar_sept_2017/Mar_sept_2017.json"); // read and parse json of Bicimad
	    CsvNormalizer csvcreator = new CsvNormalizer();
        GregorianCalendar calendar1 = new GregorianCalendar();
        GregorianCalendar calendar2 = new GregorianCalendar();
        calendar1.set(GregorianCalendar.YEAR, 2017);
        calendar1.set(GregorianCalendar.MONTH, 2);
        calendar1.set(GregorianCalendar.DATE, 21);
        calendar1.set(GregorianCalendar.HOUR_OF_DAY,0);
        calendar1.set(GregorianCalendar.MINUTE,0);
        calendar1.set(GregorianCalendar.SECOND,0);
        calendar2.set(GregorianCalendar.YEAR, 2017);
        calendar2.set(GregorianCalendar.MONTH, 2);
        calendar2.set(GregorianCalendar.DATE, 21);
        calendar2.set(GregorianCalendar.HOUR_OF_DAY,23);
        calendar2.set(GregorianCalendar.MINUTE,59);
        calendar2.set(GregorianCalendar.SECOND,59);

        Date date1 = calendar1.getTime();
        Date date2 = calendar2.getTime();

        // PRUEBAS CON EL FORMATO CSV PARA LOS DATOS DE BICIMAD
        List<JsonDataObjects> dataset = repoJson.findByDateBetween(date1,date2);
        while(dataset.size() > 0){
            csvcreator.createCSVBicimad(dataset,calendar1.get(GregorianCalendar.YEAR)+"-"+ calendar1.get(GregorianCalendar.MONTH)+"-"+calendar1.get(GregorianCalendar.DATE)+".csv");
            calendar1.add(GregorianCalendar.DATE,1);
            calendar2.add(GregorianCalendar.DATE,1);
        }

        // List<GeneralInformation> dataset = repo.findAll();
        List<JsonDataObjects> dataset = Petitions.getBetweenDates("2017-03-20T23:59:59.000","2017-03-21T23:59:59.000",repoJson);
        try {
            csvcreator.createCSVBicimad(dataset,"21-03-2017.csv");
        }catch (Exception ex){
            ex.printStackTrace();
        }*/
    }

    @Scheduled(fixedDelay=60000)
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

            final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").registerTypeAdapter(JsonDataObjects.class, new JsonObjectSerializer()).create();

            String linea;
            int x = 0;
            while((linea = br.readLine()) != null){
                linea  = linea.replace("$","");
                JsonDataObjects json = gson.fromJson(linea,JsonDataObjects.class);
                Date date = json.getUnplugHourTime().get$date();
                json.setDate(date);
                json.setUnplugHourTime(null);
                String result = gson.toJson(json);

                repoJson.save(json);
            }
            System.out.println("acabe");
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
