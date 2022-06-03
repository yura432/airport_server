package ru.sberbank.airport_server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sberbank.airport_server.model.Airport;
import ru.sberbank.airport_server.model.Request;
import ru.sberbank.airport_server.model.Response;
import ru.sberbank.airport_server.repo.AirportRepo;


import java.sql.Timestamp;
import java.util.UUID;




@Service
public class AirportService {

    @Autowired
    public AirportRepo airportRepo;

    private static final String REQUEST_RECEIVED = "Received request: ";
    private static final Logger logger = Logger.getLogger(AirportService.class);

    public ResponseEntity<Response> createAirportResponse(Request request){
        Response response = new Response();
        response.setGetTimestamp(new Timestamp(System.currentTimeMillis()));

        ObjectMapper mapper = new ObjectMapper();


        try {
            logger.info(REQUEST_RECEIVED + mapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Airport airport = airportRepo.findById(request.getAirportID());
        if (airport == null){
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.setAirport(airport);

        response.setRequestUUID(request.getUuid());
        response.setUuid(UUID.randomUUID());
        response.setSendTimestamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
