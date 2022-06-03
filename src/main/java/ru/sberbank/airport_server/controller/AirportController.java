package ru.sberbank.airport_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sberbank.airport_server.model.Request;
import ru.sberbank.airport_server.model.Response;
import ru.sberbank.airport_server.service.AirportService;

@Controller
public class AirportController {

    @Autowired
    public AirportService airportService;

    @PostMapping ("/airport")
    public ResponseEntity<Response> getAirport(@RequestBody Request request){
        return airportService.createAirportResponse(request);
    }
}
