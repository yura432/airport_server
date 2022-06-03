package ru.sberbank.airport_server.repo;

import org.springframework.data.repository.CrudRepository;
import ru.sberbank.airport_server.model.Airport;

public interface AirportRepo extends CrudRepository<Airport, Integer> {

    Airport findById(int id);
}
