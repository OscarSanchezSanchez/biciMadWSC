package com.emtbicimad.application;

import com.emtbicimad.entities.GeneralInformation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.util.converter.DateTimeStringConverter;
import jsonEntities.JsonDataObjects;
import jsonEntities.JsonObjectSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import stationsLocationInfo.StationInfo;
import stationsLocationInfo.StationsInfoList;
import userConfigurationSimulator.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Petitions {
    private static final int TOTAL_STATIONS = 176;
    private static final String ALL_STATIONS_URL = "https://rbdata.emtmadrid.es:8443/BiciMad/get_stations";
    private static final String SINGLE_STATION = "https://rbdata.emtmadrid.es:8443/BiciMad/get_single_station";

    public GeneralInformation getSingleStation(String id_client, String pass_key, int id_station){
        RestTemplate restTemplate = new RestTemplate();
        String url = SINGLE_STATION + "/" + id_client + "/" + pass_key + "/" + id_station + "/";
        ResponseEntity<String> response  = restTemplate.getForEntity(url,String.class);
        String jsonstring =  this.clearResponse(response.getBody());
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonstring,GeneralInformation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            System.err.println("Time exprired in the API server");
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

    public List countRegister(String filepath) {
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").registerTypeAdapter(JsonDataObjects.class, new JsonObjectSerializer()).create();

            //aux variables
            String linea;
            List<Map<String, Integer>> stations = new ArrayList<>(TOTAL_STATIONS);

            GregorianCalendar calendar_now = new GregorianCalendar();
            GregorianCalendar calendar_prev = new GregorianCalendar();
            linea = br.readLine();
            linea  = linea.replace("$","");
            JsonDataObjects json = gson.fromJson(linea,JsonDataObjects.class);
            for (int i = 0;i<176;i++){
                Map<String,Integer> summary = new HashMap<>();
                stations.add(i,summary);
            }
            Date date = json.getUnplugHourTime().get$date();
            calendar_now.setTime(date);
            calendar_prev.setTime(date);
            int station = json.getIdunplug_station();
            String dateToSave = calendar_now.get(Calendar.DAY_OF_MONTH)+"-"+(calendar_now.get(Calendar.MONTH)+1)+"-"+calendar_now.get(Calendar.YEAR);
            stations.get(station).put(dateToSave,1);
            while((linea = br.readLine()) != null){
                linea  = linea.replace("$","");
                json = gson.fromJson(linea,JsonDataObjects.class);
                Date date_compare = json.getUnplugHourTime().get$date();
                calendar_now.setTime(date_compare);
                dateToSave = calendar_now.get(Calendar.DAY_OF_MONTH)+"-"+(calendar_now.get(Calendar.MONTH)+1)+"-"+calendar_now.get(Calendar.YEAR);
                if (station == json.getIdunplug_station()){
                    if (station > TOTAL_STATIONS){continue;}
                    if ((calendar_now.get(Calendar.DAY_OF_MONTH) == calendar_prev.get(Calendar.DAY_OF_MONTH)) && (calendar_now.get(Calendar.MONTH) == calendar_prev.get(Calendar.MONTH))){
                        stations.get(station).put(dateToSave,(stations.get(station).get(dateToSave) +1));
                    }else{
                        stations.get(station).put(dateToSave,1);
                    }
                }else{
                    station = json.getIdplug_station();
                    if (station > TOTAL_STATIONS){continue;}
                    if (stations.get(station).containsKey(dateToSave)){
                        stations.get(station).put(dateToSave,(stations.get(station).get(dateToSave) +1));
                    }else{
                        stations.get(station).put(dateToSave,1);
                    }
                }

                calendar_prev.setTime(date_compare);
                date.setTime(date_compare.getTime());
            }
            System.out.println("acabe");
            fr.close();
            br.close();
            return stations;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List countRegisterByHour(String filepath) {
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").registerTypeAdapter(JsonDataObjects.class, new JsonObjectSerializer()).create();

            //aux variables
            String linea;
            List<Map<String, Integer>> stations = new ArrayList<>(TOTAL_STATIONS);

            GregorianCalendar calendar_now = new GregorianCalendar();
            GregorianCalendar calendar_prev = new GregorianCalendar();
            linea = br.readLine();
            linea  = linea.replace("$","");
            JsonDataObjects json = gson.fromJson(linea,JsonDataObjects.class);
            for (int i = 0;i<176;i++){
                Map<String,Integer> summary = new HashMap<>();
                stations.add(i,summary);
            }
            Date date = json.getUnplugHourTime().get$date();
            calendar_now.setTime(date);
            calendar_prev.setTime(date);
            int station = json.getIdunplug_station();
            String dateToSave = calendar_now.get(Calendar.DAY_OF_MONTH)+"-"+(calendar_now.get(Calendar.MONTH)+1)+"-"+calendar_now.get(Calendar.YEAR) +"T"+ calendar_now.get(Calendar.HOUR_OF_DAY)+":00:00";
            stations.get(station).put(dateToSave,1);
            while((linea = br.readLine()) != null){
                linea  = linea.replace("$","");
                json = gson.fromJson(linea,JsonDataObjects.class);
                Date date_compare = json.getUnplugHourTime().get$date();
                calendar_now.setTime(date_compare);
                dateToSave = calendar_now.get(Calendar.DAY_OF_MONTH)+"-"+(calendar_now.get(Calendar.MONTH)+1)+"-"+calendar_now.get(Calendar.YEAR)+"T"+ calendar_now.get(Calendar.HOUR_OF_DAY)+":00:00";
                if (station == json.getIdunplug_station()){
                    if (station > TOTAL_STATIONS){continue;}
                    if ((calendar_now.get(Calendar.DAY_OF_MONTH) == calendar_prev.get(Calendar.DAY_OF_MONTH)) && (calendar_now.get(Calendar.MONTH) == calendar_prev.get(Calendar.MONTH)) && (calendar_now.get(Calendar.HOUR_OF_DAY) == calendar_prev.get(Calendar.HOUR_OF_DAY))){
                        stations.get(station).put(dateToSave,(stations.get(station).get(dateToSave) +1));
                    }else{
                        stations.get(station).put(dateToSave,1);
                    }
                }else{
                    station = json.getIdplug_station();
                    if (station > TOTAL_STATIONS){continue;}
                    if (stations.get(station).containsKey(dateToSave)){
                        stations.get(station).put(dateToSave,(stations.get(station).get(dateToSave) +1));
                    }else{
                        stations.get(station).put(dateToSave,1);
                    }
                }

                calendar_prev.setTime(date_compare);
                date.setTime(date_compare.getTime());
            }
            System.out.println("acabe");
            fr.close();
            br.close();
            return stations;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public StationsInfoList generateStationsInfoList(String path){
        Type listType = new TypeToken<List<ConfigurationUsers>>() {}.getType();

        // Get Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // read JSON file data as String
        String fileData = null;
        try {
            fileData = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.err.println("Failed to read the Json file");
        }

        // parse json string to object
        return gson.fromJson(fileData, StationsInfoList.class);
        //falta formatear los datos y crear el onejtp stationsinfolist

    }

    public void generateJsonRoutesSimulator(StationsInfoList stationsInfoList,JsondataRepository repoJson,String user_type){
        DateTimeStringConverter x = new DateTimeStringConverter("dd-MM-yyyy HH:mm:ss");
        Date init_date = x.fromString("23-03-2017 00:00:00");
        Date end_date = x.fromString("23-03-2017 23:59:59");
        List<JsonDataObjects> result = repoJson.findDocumentsBetweenDates(init_date,end_date);

        long initial_time = result.get(0).getDate().getTime();
        ConfigurationUsers users = new ConfigurationUsers();

        for(JsonDataObjects route: result){
            //Construct the Object to represent the userConfiguration
            long timeInstant = (route.getDate().getTime() - initial_time)/1000;
            GeoPosition init_position = this.findGeopositionById(route.getIdunplug_station(),stationsInfoList);
            DestinationPlace end_position = this.findDestinationPlaceById(route.getIdplug_station(),stationsInfoList);
            UserParameters parameters = new UserParameters(end_position);
            UserType userType = new UserType(user_type,parameters);
            UserConfiguration user = new UserConfiguration(init_position,userType,timeInstant);
            users.addUser(user);
        }
        this.generateFileConfiguration(users,"users_configuration.json");
    }

    private GeoPosition findGeopositionById(int id,StationsInfoList stationsInfoList){
        List<StationInfo> stations = stationsInfoList.getStations();
        for (int i=0;i<stations.size();i++){
            if (id == stations.get(i).getId()){
                return new GeoPosition(stations.get(i).getPosition().getLatitude(),stations.get(i).getPosition().getLongitude());
            }
        }
        return null;
    }
    private DestinationPlace findDestinationPlaceById(int id, StationsInfoList stationsInfoList){
        List<StationInfo> stations = stationsInfoList.getStations();
        for (int i=0;i<stations.size();i++){
            if (id == stations.get(i).getId()){
                return new DestinationPlace(stations.get(i).getPosition().getLatitude()+0.00000000000003,stations.get(i).getPosition().getLongitude()+0.00000000000003);
            }
        }
        return null;
    }

    private void generateFileConfiguration(ConfigurationUsers list,String filename){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String configuration = gson.toJson(list);
        this.writeFile(filename, configuration);
    }

    public void generateFileConfiguration(StationsInfoList list,String filename){
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String configuration = gson.toJson(list);
        this.writeFile(filename, configuration);
    }

    private void writeFile(String filename, String configuration) {
        FileWriter file;
        try {
            file = new FileWriter(filename);
            file.write(configuration);
            file.flush();
            file.close();
        } catch (Exception ex) {
            System.out.println("Exception  message: " + ex.getMessage());
        }
    }

}

