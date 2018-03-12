package com.example.demo;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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


    private String clearResponse(String string){
        String regex1 = "\"\\{";
        String regex2 = "\\}\"";
        return string.replaceAll("\\\\", "")
                .replaceAll(regex1, "{").replaceAll(regex2, "}");
    }
}

