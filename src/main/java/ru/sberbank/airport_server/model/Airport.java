package ru.sberbank.airport_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airport {
    @Id
    private int id;

    private String name;
    private String city;
    private String country;
    private String IATACode;
    private String ICAOCode;
    private Double latitude;
    private Double longitude;
    private Double num1; //I didn't understand what does it mean
    private Double num2;
    private Character letter;
    private String timeZone;
    private String airport;
    private String airportClass;

}
