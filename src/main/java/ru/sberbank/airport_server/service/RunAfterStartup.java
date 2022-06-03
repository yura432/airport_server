package ru.sberbank.airport_server.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.sberbank.airport_server.model.Airport;
import ru.sberbank.airport_server.repo.AirportRepo;

import java.io.FileReader;
import java.io.Reader;

@Service
public class RunAfterStartup {

    @Autowired
    private AirportRepo airportRepo;


    private Integer checkCSVNullToInt(String str){
        if (str == null || str.equals("\\N")){
            return null;
        }
        return Integer.parseInt(str);
    }

    private Double checkCSVNullToDouble(String str){
        if (str == null || str.equals("\\N")){
            return null;
        }
        return Double.parseDouble(str);
    }

    private Character checkCSVNullToChar(String str){
        if (str == null || str.equals("\\N")){
            return null;
        }
        return str.charAt(0);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        //System.out.println("I've been started");
        try {
            Reader in = new FileReader("src/main/resources/static/airports.dat");
            Iterable<CSVRecord> records = CSVFormat.ORACLE.parse(in);
            for (CSVRecord record : records) {
                Airport airport = new Airport(
                        checkCSVNullToInt(record.get(0)),
                        record.get(1),
                        record.get(2),
                        record.get(3),
                        record.get(4),
                        record.get(5),
                        checkCSVNullToDouble(record.get(6)),
                        checkCSVNullToDouble(record.get(7)),
                        checkCSVNullToDouble(record.get(8)),
                        checkCSVNullToDouble(record.get(9)),
                        checkCSVNullToChar(record.get(10)),
                        record.get(11),
                        record.get(12),
                        record.get(13)
                );
                airportRepo.save(airport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
