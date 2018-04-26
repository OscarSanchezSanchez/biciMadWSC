package com.emtbicimad.application;

import com.emtbicimad.entities.GeneralInformation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.converter.DateTimeStringConverter;
import jsonEntities.JsonDataObjects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Petitions {

    private static final String ALL_STATIONS_URL = "https://rbdata.emtmadrid.es:8443/BiciMad/get_stations";
    private static final String SINGLE_STATION = "https://rbdata.emtmadrid.es:8443/BiciMad/get_single_station";

    public void getSingleStation(String id_client,String pass_key,int id_station){
        RestTemplate restTemplate = new RestTemplate();
        String url = SINGLE_STATION + "/" + id_client + "/" + pass_key + "/" + id_station + "/";
        ResponseEntity<String> response  = restTemplate.getForEntity(url,String.class);
        String jsonstring =  this.clearResponse(response.getBody());
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            GeneralInformation stationInformation = mapper.readValue(jsonstring,GeneralInformation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GeneralInformation getAllStations(String id_client,String pass_key){
        RestTemplate restTemplate = new RestTemplate();
        String url = ALL_STATIONS_URL + "/" + id_client + "/" + pass_key + "/";
        ResponseEntity<String> response  = restTemplate.getForEntity(url,String.class);
        String jsonstring =  this.clearResponse(response.getBody());
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(jsonstring,GeneralInformation.class);

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static  List<GeneralInformation> getBetweenDates(String date1, String date2, InfoRepository repo){
        DateTimeStringConverter x = new DateTimeStringConverter("dd-MM-yyyy HH:mm:ss.SSS");
        Date format_date1 = x.fromString(date1);
        Date format_date2 = x.fromString(date2);
        return repo.findAllByTimeIsBetween(format_date1,format_date2);
    }
     public static  List<JsonDataObjects> getBetweenDates(String date1, String date2, JsondataRepository repo){
        DateTimeStringConverter x = new DateTimeStringConverter("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date format_date1 = x.fromString(date1);
        Date format_date2 = x.fromString(date2);
        return repo.findByDateBetween(format_date1,format_date2);
    }

    private String clearResponse(String string){
        String regex1 = "\"\\{";
        String regex2 = "\\}\"";
        return string.replaceAll("\\\\", "")
                .replaceAll(regex1, "{").replaceAll(regex2, "}");
    }

}

