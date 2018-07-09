package com.emtbicimad.application;
import com.emtbicimad.entities.GeneralInformation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import javafx.util.converter.DateTimeStringConverter;
import jsonEntities.CountByDay;
import jsonEntities.JsonDataObjects;
import jsonEntities.JsonObjectSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import stationsLocationInfo.StationInfo;
import stationsLocationInfo.StationsInfoList;
import userConfigurationSimulator.ConfigurationUsers;
import userConfigurationSimulator.DestinationPlace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner{

    @Autowired
    private InfoRepository repo;
    @Autowired
    private JsondataRepository repoJson;
    @Autowired
    private CountByDayRepository repoCount;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
    //2017-03-21
        Petitions petitions = new Petitions();
        StationsInfoList list_stations = petitions.generateStationsInfoList("stations.txt");
        list_stations.modifyCapacity(50);
        list_stations.addBikes(25);
        petitions.generateFileConfiguration(list_stations,"stations_configuration.json");
        petitions.generateJsonRoutesSimulator(list_stations,repoJson,"USER_COMMUTER");

	    //this.readGigantJson("/Users/oscar/data_analitics_R/Mar_sept_2017/Mar_sept_2017.json"); // read and parse json of Bicimad
        /*Petitions petitions = new Petitions();
        List<HashMap<String,Integer>> days = petitions.countRegisterByHour("/Users/oscar/data_analitics_R/Mar_sept_2017/Mar_sept_2017.json");
        for(int i = 1;i<days.size();i++){
            HashMap x = days.get(i);
            int finalI = i;
            x.forEach((k, v) ->
                {
                    CountByDay day = new CountByDay();
                    day.setStation(finalI);
                    day.setDate((String) k);
                    day.setSum((Integer) v);
                    repoCount.save(day);
                });
        }*/
    }

    //@Scheduled(fixedDelay=300000)
    public void saveInformation(){
	    Petitions petitions = new Petitions();
	    try{
            repo.save(petitions.getAllStations("WEB.SERV.oscar.sanchezsa@urjc.es", "0BB5A209-4124-47BC-8233-6F340F5FA800"));
        }catch (Exception e){
	        System.err.println("Time expired in the API server");
        }
    }

    public void readGigantJson(String filepath){
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").registerTypeAdapter(JsonDataObjects.class, new JsonObjectSerializer()).create();

            String linea;
            int x = 0;
            int sum_day = 0;
            int sum_hour = 0;
            GregorianCalendar calendar_now = new GregorianCalendar();

            while((linea = br.readLine()) != null){
                //registers cleaning
                linea  = linea.replace("$","");
                JsonDataObjects json = gson.fromJson(linea,JsonDataObjects.class);
                Date date = json.getUnplugHourTime().get$date();
                calendar_now.setTime(date);
                json.setDayOfWeek(calendar_now.get(Calendar.DAY_OF_WEEK));
                json.setHour(calendar_now.get(Calendar.HOUR));
                json.setMonth(calendar_now.get(Calendar.MONTH));
                json.setDayOfMonth(calendar_now.get(Calendar.DAY_OF_MONTH));
                json.setDate(date);
                json.setUnplugHourTime(null);
                repoJson.save(json);
            }
            System.out.println("acabe");
            fr.close();
            br.close();
        }
        catch(Exception e) {
            System.out.println("ahahahhaha:"+e);
        }
    }


}
