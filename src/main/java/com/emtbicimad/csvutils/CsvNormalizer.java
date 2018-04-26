package com.emtbicimad.csvutils;

import com.emtbicimad.entities.GeneralInformation;
import com.emtbicimad.entities.Station;
import jsonEntities.FeatureDescription;
import jsonEntities.JsonDataObjects;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvNormalizer {
    //CSV file header
    public static final String HEADER_DATASET_MYSELF  = "date,hour,id_station,week_event,light,activate,no_available,total_slots,bikes_dock,free_slots,reservations_count";
    public static final String HEADER_DATASET_BICIMAD = "id;date;user_day_code;idplug_base;user_type;idunplug_base;travel_time;idunplug_station;idplug_station;ageRange;track_list";
    public static final String HEADER_FEATURES_DATASET = "num_movement,latitude,longitude,place,speed,secondsfromstart";

    //Delimiter used in CSV file
    private static final char COMMA_DELIMITER = ';';
    private static final char TWOPOINTS_DELIMETER = ':';
    private static final char NEW_LINE_SEPARATOR = '\n';

    public void createCSV(List<GeneralInformation> informationList, String filename) throws IOException {
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(filename);
        fileWriter.append(HEADER_DATASET_MYSELF.toString());
        fileWriter.append(NEW_LINE_SEPARATOR);

        for (GeneralInformation stationsInfo: informationList) {
            long time = stationsInfo.getTime().getTime();
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(time);
            List<CSVstation> stations = this.createCSVstationsList(stationsInfo,calendar);
            this.writeStationsByHour(stations,fileWriter);
        }
        fileWriter.flush();
        fileWriter.close();
        System.out.println("acabeeee");

    }

    public void createCSVBicimad(List<JsonDataObjects> dataset, String fileName) throws IOException{
        FileWriter filewriter = new FileWriter(fileName);
        filewriter.append(HEADER_DATASET_BICIMAD.toString());
        filewriter.append(NEW_LINE_SEPARATOR);
        for(JsonDataObjects movementInfo: dataset){
            this.writeMovementInformation(movementInfo,filewriter);
        }
        filewriter.flush();
        filewriter.close();
        System.out.println("se finiii");
    }

    private List<CSVstation> createCSVstationsList(GeneralInformation stationsInfo,GregorianCalendar calendar){

        List<CSVstation> stations = new ArrayList<>();
        int[] weekend = {Calendar.FRIDAY,Calendar.SATURDAY,Calendar.SUNDAY};

        for (Station item: stationsInfo.getData().getStations()) {
            //create data for hour and date
            int month = calendar.get(Calendar.MONTH)+1;
            String date = calendar.get(Calendar.DAY_OF_MONTH) + "-" + month + "-" + calendar.get(Calendar.YEAR);
            String hour = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
            Boolean weekend_event = false;
            for (int i = 0; i<weekend.length; i++){
                if (calendar.get(Calendar.DAY_OF_WEEK) == weekend[i]) {
                    weekend_event = true;
                    break;
                }
            }
            CSVstation csv_station = new CSVstation(date,hour,item.getId(), weekend_event,item.getLight(),item.getActivate(),item.getNo_available(),
                    item.getTotal_bases(),item.getDock_bikes(),item.getFree_bases(),item.getReservations_count());
            stations.add(csv_station);
        }
        return stations;
    }

    private void writeMovementInformation(JsonDataObjects movementInfo, FileWriter filewriter) throws IOException {
        try {
            filewriter.append(movementInfo.get_id().getoid());
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(String.valueOf(movementInfo.getDate().getTime()));
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(movementInfo.getUser_day_code());
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(String.valueOf(movementInfo.getIdplug_base()));
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(String.valueOf(movementInfo.getUser_type()));
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(String.valueOf(movementInfo.getIdunplug_base()));
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(String.valueOf(movementInfo.getTravel_time()));
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(String.valueOf(movementInfo.getIdunplug_station()));
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(String.valueOf(movementInfo.getIdplug_station()));
            filewriter.append(COMMA_DELIMITER);
            filewriter.append(String.valueOf(movementInfo.getAgeRange()));
            filewriter.append(COMMA_DELIMITER);
            //filewriter.append("[");
            if (movementInfo.getTrack() != null) {
                List<FeatureDescription> features = movementInfo.getTrack().getFeatures();
                for (int i = 0; i < movementInfo.getTrack().getFeatures().size();i++) {
                    filewriter.append(String.valueOf(i));//num_movement
                    filewriter.append(TWOPOINTS_DELIMETER);
                    filewriter.append(String.valueOf(features.get(i).getGeometry().getCoordinates().get(0)));//latitude
                    filewriter.append(TWOPOINTS_DELIMETER);
                    filewriter.append(String.valueOf(features.get(i).getGeometry().getCoordinates().get(1)));//longitude
                    filewriter.append(TWOPOINTS_DELIMETER);
                    filewriter.append(features.get(i).getProperties().getVar());
                    filewriter.append(TWOPOINTS_DELIMETER);
                    filewriter.append(String.valueOf(features.get(i).getProperties().getSpeed()));
                    filewriter.append(TWOPOINTS_DELIMETER);
                    filewriter.append(String.valueOf(features.get(i).getProperties().getSecondsfromstart()));
                    filewriter.append("<");
                }
            }else {
                filewriter.append("NA");
            }
            filewriter.append(NEW_LINE_SEPARATOR);
            //falta meter los tracks, nose como parsearlos para un optimo analisis en R.
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            filewriter.flush();
        }


    }

    private void writeStationsByHour(List<CSVstation> csv_stations,FileWriter fileWriter) throws IOException {

        try{

            for (CSVstation station : csv_stations) {
                fileWriter.append(station.getDate());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(station.getHour());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getId_station()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getWeek_event()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getLight()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getActivate()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getNo_available()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getTotal_bases()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getDock_bikes()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getFree_bases()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(station.getReservations_count()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileWriter.flush();
        }
    }


}
