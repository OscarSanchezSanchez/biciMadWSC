package com.emtbicimad.csvutils;

import com.emtbicimad.entities.GeneralInformation;
import com.emtbicimad.entities.Station;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvNormalizer {
    //CSV file header
    public static final String HEADERS  = "date,hour,id_station,week_event,light,activate,no_available,total_slots,bikes_dock,free_slots,reservations_count";

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public void createCSV(List<GeneralInformation> informationList, String filename) throws IOException {
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(filename);
        fileWriter.append(HEADERS.toString());
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

    private List<CSVstation> createCSVstationsList(GeneralInformation stationsInfo,GregorianCalendar calendar){

        List<CSVstation> stations = new ArrayList<CSVstation>();
        int[] weekend = {5,6,7};

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
